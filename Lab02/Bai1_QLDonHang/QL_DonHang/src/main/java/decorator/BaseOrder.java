package decorator;

public class BaseOrder implements OrderComponent {
    @Override
    public void process() {
        System.out.println("[Decorator - Base] Mới tạo, đang kiểm tra thông tin...");
    }
}