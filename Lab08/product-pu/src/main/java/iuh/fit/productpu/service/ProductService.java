package iuh.fit.productpu.service;

import iuh.fit.productpu.entity.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // Lấy 1 sản phẩm
    public Product getStock(String productId) {
        Object raw = redisTemplate.opsForHash().get("products", productId);
        return toProduct(raw);
    }

    // Lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        return redisTemplate.opsForHash().values("products")
                .stream()
                .map(this::toProduct)
                .toList();
    }

    /** Map từ bất kỳ class nào (kể cả iuh.fit.inventoryservice.entity.Product) sang Product của mình */
    private Product toProduct(Object raw) {
        if (raw == null) return null;
        if (raw instanceof Product p) return p;

        // Fallback: dùng reflection để map theo field name
        try {
            Class<?> clazz = raw.getClass();
            String id    = (String) clazz.getMethod("getId").invoke(raw);
            String name  = (String) clazz.getMethod("getName").invoke(raw);
            double price = (double) clazz.getMethod("getPrice").invoke(raw);
            int stock    = (int)    clazz.getMethod("getStock").invoke(raw);
            Product p = new Product();
            p.setId(id);
            p.setName(name);
            p.setPrice(price);
            p.setStock(stock);
            return p;
        } catch (Exception e) {
            throw new RuntimeException("Cannot map object to Product: " + raw.getClass().getName(), e);
        }
    }
}