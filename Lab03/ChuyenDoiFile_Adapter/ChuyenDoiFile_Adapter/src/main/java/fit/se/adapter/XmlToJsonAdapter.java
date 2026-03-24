package fit.se.adapter;

public class XmlToJsonAdapter implements XmlService {
    private JsonService jsonService;

    public XmlToJsonAdapter(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @Override
    public void processXmlRequest(String xmlData) {
        System.out.println("[Adapter] Nhận dữ liệu XML: " + xmlData);
        System.out.println("[Adapter] Đang tiến hành dịch từ XML sang JSON...");
        
        // Giả lập logic chuyển đổi (Thực tế bạn sẽ dùng các thư viện như Jackson/Gson)
        String jsonData = convertXmlToJson(xmlData);
        
        // Gọi dịch vụ Web mới bằng dữ liệu đã chuyển đổi
        jsonService.processJsonRequest(jsonData);
    }

    private String convertXmlToJson(String xmlData) {
        // Mô phỏng: <user><name>John</name></user> -> {"user": {"name": "John"}}
        return "{ \"user\": { \"name\": \"John\" } }"; 
    }
}