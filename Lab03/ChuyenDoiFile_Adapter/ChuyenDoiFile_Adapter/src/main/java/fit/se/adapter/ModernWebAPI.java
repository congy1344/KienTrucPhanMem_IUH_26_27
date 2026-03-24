package fit.se.adapter;

public class ModernWebAPI implements JsonService {
    @Override
    public void processJsonRequest(String jsonData) {
        System.out.println("[ModernWebAPI] Đang xử lý dữ liệu JSON: " + jsonData);
        System.out.println("[ModernWebAPI] Xử lý thành công!\n");
    }
}