import React, { useEffect, useMemo, useState } from "react";
import { createRoot } from "react-dom/client";
import axios from "axios";
import "./styles.css";

const userApi = axios.create({
  baseURL: import.meta.env.VITE_USER_SERVICE_URL || "http://localhost:3001"
});
const foodApi = axios.create({
  baseURL: import.meta.env.VITE_FOOD_SERVICE_URL || "http://localhost:3002"
});
const orderApi = axios.create({
  baseURL: import.meta.env.VITE_ORDER_SERVICE_URL || "http://localhost:3003"
});
const paymentApi = axios.create({
  baseURL: import.meta.env.VITE_PAYMENT_SERVICE_URL || "http://localhost:3004"
});

function formatCurrency(value) {
  return new Intl.NumberFormat("vi-VN", { style: "currency", currency: "VND" }).format(value);
}

function readError(error, fallback) {
  return error.response?.data?.message || fallback;
}

function routeFromHash(hasSession) {
  const route = window.location.hash.replace("#/", "");
  const allowedRoutes = ["login", "foods", "cart", "checkout"];

  if (allowedRoutes.includes(route)) {
    return route;
  }

  return hasSession ? "foods" : "login";
}

function App() {
  const [page, setPage] = useState(() => routeFromHash(Boolean(localStorage.getItem("mini-food-session"))));
  const [authMode, setAuthMode] = useState("login");
  const [authForm, setAuthForm] = useState({
    name: "",
    email: "user@food.local",
    password: "123456",
    role: "USER"
  });
  const [session, setSession] = useState(() => {
    const saved = localStorage.getItem("mini-food-session");
    return saved ? JSON.parse(saved) : null;
  });
  const [foods, setFoods] = useState([]);
  const [cart, setCart] = useState(() => {
    const saved = localStorage.getItem("mini-food-cart");
    return saved ? JSON.parse(saved) : [];
  });
  const [orders, setOrders] = useState([]);
  const [selectedOrderId, setSelectedOrderId] = useState("");
  const [paymentMethod, setPaymentMethod] = useState("COD");
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const cartTotal = useMemo(
    () => cart.reduce((sum, item) => sum + item.price * item.quantity, 0),
    [cart]
  );

  const selectedOrder = orders.find((order) => order.id === selectedOrderId);

  useEffect(() => {
    loadFoods();
    loadOrders();
  }, []);

  useEffect(() => {
    function syncPageWithHash() {
      setPage(routeFromHash(Boolean(localStorage.getItem("mini-food-session"))));
    }

    window.addEventListener("hashchange", syncPageWithHash);
    return () => window.removeEventListener("hashchange", syncPageWithHash);
  }, []);

  useEffect(() => {
    localStorage.setItem("mini-food-cart", JSON.stringify(cart));
  }, [cart]);

  async function loadFoods() {
    try {
      const response = await foodApi.get("/foods");
      setFoods(response.data);
    } catch (error) {
      setMessage(readError(error, "Khong tai duoc danh sach mon an"));
    }
  }

  async function loadOrders() {
    try {
      const response = await orderApi.get("/orders");
      setOrders(response.data);
    } catch {
      setOrders([]);
    }
  }

  function navigate(nextPage) {
    setMessage("");
    setPage(nextPage);
    window.location.hash = `/${nextPage}`;
    if (nextPage === "checkout") {
      loadOrders();
    }
  }

  function updateAuthForm(event) {
    const { name, value } = event.target;
    setAuthForm((current) => ({ ...current, [name]: value }));
  }

  async function submitAuth(event) {
    event.preventDefault();
    setLoading(true);
    setMessage("");

    try {
      const endpoint = authMode === "login" ? "/login" : "/register";
      const payload =
        authMode === "login"
          ? { email: authForm.email, password: authForm.password }
          : authForm;
      const response = await userApi.post(endpoint, payload);
      setSession(response.data);
      localStorage.setItem("mini-food-session", JSON.stringify(response.data));
      navigate("foods");
      setMessage(authMode === "login" ? "Dang nhap thanh cong" : "Dang ky thanh cong");
    } catch (error) {
      setMessage(readError(error, "Khong xu ly duoc yeu cau dang nhap/dang ky"));
    } finally {
      setLoading(false);
    }
  }

  function logout() {
    setSession(null);
    localStorage.removeItem("mini-food-session");
    setCart([]);
    setSelectedOrderId("");
    navigate("login");
    setMessage("Da dang xuat");
  }

  function addToCart(food) {
    setCart((current) => {
      const existed = current.find((item) => item.foodId === food.id);

      if (existed) {
        return current.map((item) =>
          item.foodId === food.id ? { ...item, quantity: item.quantity + 1 } : item
        );
      }

      return [
        ...current,
        {
          foodId: food.id,
          name: food.name,
          price: food.price,
          quantity: 1
        }
      ];
    });
    setMessage(`${food.name} da duoc them vao gio hang`);
  }

  function changeQuantity(foodId, delta) {
    setCart((current) =>
      current
        .map((item) =>
          item.foodId === foodId ? { ...item, quantity: Math.max(0, item.quantity + delta) } : item
        )
        .filter((item) => item.quantity > 0)
    );
  }

  function removeFromCart(foodId) {
    setCart((current) => current.filter((item) => item.foodId !== foodId));
  }

  async function createOrder() {
    if (!session?.user?.id) {
      setMessage("Vui long dang nhap truoc khi dat hang");
      setPage("login");
      return;
    }

    if (cart.length === 0) {
      setMessage("Gio hang dang trong");
      return;
    }

    setLoading(true);
    setMessage("");

    try {
      const response = await orderApi.post("/orders", {
        userId: session.user.id,
        items: cart.map((item) => ({ foodId: item.foodId, quantity: item.quantity }))
      });
      setSelectedOrderId(response.data.id);
      setCart([]);
      await loadOrders();
      navigate("checkout");
      setMessage(`Tao don ${response.data.id} thanh cong`);
    } catch (error) {
      setMessage(readError(error, "Khong tao duoc don hang"));
    } finally {
      setLoading(false);
    }
  }

  async function payOrder() {
    if (!selectedOrderId) {
      setMessage("Vui long chon don hang can thanh toan");
      return;
    }

    setLoading(true);
    setMessage("");

    try {
      const response = await paymentApi.post("/payments", {
        orderId: selectedOrderId,
        method: paymentMethod
      });
      await loadOrders();
      setMessage(`Thanh toan ${response.data.payment.id} thanh cong`);
    } catch (error) {
      setMessage(readError(error, "Thanh toan that bai"));
    } finally {
      setLoading(false);
    }
  }

  return (
    <main className="shell">
      <header className="topbar">
        <button type="button" className="brand" onClick={() => navigate(session ? "foods" : "login")}>
          <span>Internal Food Ordering</span>
          <strong>Mini Food Ordering System</strong>
        </button>

        <nav className="nav">
          <button
            type="button"
            className={page === "login" ? "active" : ""}
            onClick={() => navigate("login")}
          >
            Login
          </button>
          <button
            type="button"
            className={page === "foods" ? "active" : ""}
            onClick={() => navigate("foods")}
          >
            Foods
          </button>
          <button
            type="button"
            className={page === "cart" ? "active" : ""}
            onClick={() => navigate("cart")}
          >
            Cart ({cart.reduce((sum, item) => sum + item.quantity, 0)})
          </button>
          <button
            type="button"
            className={page === "checkout" ? "active" : ""}
            onClick={() => navigate("checkout")}
          >
            Payment
          </button>
        </nav>

        <div className="session">
          {session ? (
            <>
              <span>{session.user.name}</span>
              <strong>{session.user.role}</strong>
              <button type="button" onClick={logout}>
                Logout
              </button>
            </>
          ) : (
            <span>Chua dang nhap</span>
          )}
        </div>
      </header>

      {message && <div className="notice">{message}</div>}

      {page === "login" && (
        <LoginPage
          authMode={authMode}
          authForm={authForm}
          loading={loading}
          setAuthMode={setAuthMode}
          updateAuthForm={updateAuthForm}
          submitAuth={submitAuth}
        />
      )}

      {page === "foods" && (
        <FoodsPage foods={foods} loadFoods={loadFoods} addToCart={addToCart} navigate={navigate} />
      )}

      {page === "cart" && (
        <CartPage
          cart={cart}
          cartTotal={cartTotal}
          loading={loading}
          changeQuantity={changeQuantity}
          removeFromCart={removeFromCart}
          createOrder={createOrder}
          navigate={navigate}
        />
      )}

      {page === "checkout" && (
        <CheckoutPage
          orders={orders}
          selectedOrder={selectedOrder}
          selectedOrderId={selectedOrderId}
          paymentMethod={paymentMethod}
          loading={loading}
          setSelectedOrderId={setSelectedOrderId}
          setPaymentMethod={setPaymentMethod}
          loadOrders={loadOrders}
          payOrder={payOrder}
          navigate={navigate}
        />
      )}
    </main>
  );
}

function LoginPage({ authMode, authForm, loading, setAuthMode, updateAuthForm, submitAuth }) {
  return (
    <section className="page auth-page">
      <div className="auth-copy">
        <p className="eyebrow">Account</p>
        <h1>{authMode === "login" ? "Dang nhap" : "Dang ky tai khoan"}</h1>
        <p>Su dung tai khoan demo `user@food.local` / `123456` hoac tao tai khoan moi.</p>
      </div>

      <article className="panel auth-panel">
        <div className="panel-title">
          <h2>{authMode === "login" ? "Login" : "Register"}</h2>
          <div className="segmented">
            <button
              type="button"
              className={authMode === "login" ? "active" : ""}
              onClick={() => setAuthMode("login")}
            >
              Login
            </button>
            <button
              type="button"
              className={authMode === "register" ? "active" : ""}
              onClick={() => setAuthMode("register")}
            >
              Register
            </button>
          </div>
        </div>

        <form onSubmit={submitAuth} className="form">
          {authMode === "register" && (
            <>
              <label>
                Full name
                <input name="name" value={authForm.name} onChange={updateAuthForm} />
              </label>
              <label>
                Role
                <select name="role" value={authForm.role} onChange={updateAuthForm}>
                  <option value="USER">USER</option>
                  <option value="ADMIN">ADMIN</option>
                </select>
              </label>
            </>
          )}
          <label>
            Email
            <input name="email" value={authForm.email} onChange={updateAuthForm} />
          </label>
          <label>
            Password
            <input
              name="password"
              type="password"
              value={authForm.password}
              onChange={updateAuthForm}
            />
          </label>
          <button type="submit" disabled={loading}>
            {loading ? "Processing..." : authMode === "login" ? "Login" : "Register"}
          </button>
        </form>
      </article>
    </section>
  );
}

function FoodsPage({ foods, loadFoods, addToCart, navigate }) {
  return (
    <section className="page">
      <div className="page-heading">
        <div>
          <p className="eyebrow">Menu</p>
          <h1>Danh sach mon an</h1>
        </div>
        <div className="actions">
          <button type="button" onClick={loadFoods}>
            Refresh
          </button>
          <button type="button" className="primary compact" onClick={() => navigate("cart")}>
            View Cart
          </button>
        </div>
      </div>

      <div className="foods">
        {foods.map((food) => (
          <div className="food-card" key={food.id}>
            <img src={food.imageUrl} alt={food.name} />
            <div>
              <h3>{food.name}</h3>
              <p>{food.description}</p>
              <div className="food-footer">
                <strong>{formatCurrency(food.price)}</strong>
                <button type="button" onClick={() => addToCart(food)} disabled={!food.available}>
                  Add
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </section>
  );
}

function CartPage({
  cart,
  cartTotal,
  loading,
  changeQuantity,
  removeFromCart,
  createOrder,
  navigate
}) {
  return (
    <section className="page cart-page">
      <div className="page-heading">
        <div>
          <p className="eyebrow">Cart</p>
          <h1>Gio hang</h1>
        </div>
        <button type="button" onClick={() => navigate("foods")}>
          Continue Shopping
        </button>
      </div>

      <div className="split-layout">
        <article className="panel">
          {cart.length === 0 ? (
            <p className="empty">Gio hang dang trong.</p>
          ) : (
            <div className="cart-list large">
              {cart.map((item) => (
                <div className="cart-row" key={item.foodId}>
                  <div>
                    <strong>{item.name}</strong>
                    <span>{formatCurrency(item.price)}</span>
                  </div>
                  <div className="cart-controls">
                    <div className="stepper">
                      <button type="button" onClick={() => changeQuantity(item.foodId, -1)}>
                        -
                      </button>
                      <span>{item.quantity}</span>
                      <button type="button" onClick={() => changeQuantity(item.foodId, 1)}>
                        +
                      </button>
                    </div>
                    <button type="button" className="ghost danger" onClick={() => removeFromCart(item.foodId)}>
                      Remove
                    </button>
                  </div>
                </div>
              ))}
            </div>
          )}
        </article>

        <aside className="panel summary-panel">
          <h2>Order Summary</h2>
          <div className="summary-row">
            <span>Items</span>
            <strong>{cart.reduce((sum, item) => sum + item.quantity, 0)}</strong>
          </div>
          <div className="summary-row total">
            <span>Total</span>
            <strong>{formatCurrency(cartTotal)}</strong>
          </div>
          <button type="button" className="primary" onClick={createOrder} disabled={loading || cart.length === 0}>
            Create Order
          </button>
        </aside>
      </div>
    </section>
  );
}

function CheckoutPage({
  orders,
  selectedOrder,
  selectedOrderId,
  paymentMethod,
  loading,
  setSelectedOrderId,
  setPaymentMethod,
  loadOrders,
  payOrder,
  navigate
}) {
  return (
    <section className="page checkout-page">
      <div className="page-heading">
        <div>
          <p className="eyebrow">Payment</p>
          <h1>Thanh toan</h1>
        </div>
        <div className="actions">
          <button type="button" onClick={loadOrders}>
            Reload Orders
          </button>
          <button type="button" onClick={() => navigate("cart")}>
            Back To Cart
          </button>
        </div>
      </div>

      <div className="split-layout">
        <article className="panel payment-panel">
          <label>
            Order
            <select value={selectedOrderId} onChange={(event) => setSelectedOrderId(event.target.value)}>
              <option value="">Chon don hang</option>
              {orders.map((order) => (
                <option value={order.id} key={order.id}>
                  {order.id} - {order.status} - {formatCurrency(order.totalAmount)}
                </option>
              ))}
            </select>
          </label>

          <div className="payment-methods">
            <label>
              <input
                type="radio"
                checked={paymentMethod === "COD"}
                onChange={() => setPaymentMethod("COD")}
              />
              COD
            </label>
            <label>
              <input
                type="radio"
                checked={paymentMethod === "BANKING"}
                onChange={() => setPaymentMethod("BANKING")}
              />
              Banking
            </label>
          </div>

          <button type="button" className="primary" onClick={payOrder} disabled={loading || !selectedOrderId}>
            Pay Order
          </button>
        </article>

        <aside className="panel summary-panel">
          <h2>Selected Order</h2>
          {selectedOrder ? (
            <>
              <div className="summary-row">
                <span>Order ID</span>
                <strong>{selectedOrder.id}</strong>
              </div>
              <div className="summary-row">
                <span>Status</span>
                <span className={`status ${selectedOrder.status.toLowerCase()}`}>
                  {selectedOrder.status}
                </span>
              </div>
              <div className="order-items">
                {selectedOrder.items.map((item) => (
                  <div key={item.foodId}>
                    <span>{item.name} x{item.quantity}</span>
                    <strong>{formatCurrency(item.subtotal)}</strong>
                  </div>
                ))}
              </div>
              <div className="summary-row total">
                <span>Total</span>
                <strong>{formatCurrency(selectedOrder.totalAmount)}</strong>
              </div>
            </>
          ) : (
            <p className="empty">Chua chon don hang.</p>
          )}
        </aside>
      </div>

      <div className="orders">
        {orders.map((order) => (
          <button
            type="button"
            className="order-card selectable"
            key={order.id}
            onClick={() => setSelectedOrderId(order.id)}
          >
            <div>
              <strong>{order.id}</strong>
              <span>{order.userName}</span>
            </div>
            <span className={`status ${order.status.toLowerCase()}`}>{order.status}</span>
            <p>{order.items.map((item) => `${item.name} x${item.quantity}`).join(", ")}</p>
            <b>{formatCurrency(order.totalAmount)}</b>
          </button>
        ))}
      </div>
    </section>
  );
}

createRoot(document.getElementById("root")).render(<App />);
