package fit.se.adapter;

public class JsonToXmlAdapter implements JsonService {
    private XmlService xmlService;

    public JsonToXmlAdapter(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    @Override
    public void processJsonRequest(String jsonData) {
        System.out.println("[Adapter] Nhận dữ liệu JSON: " + jsonData);
        System.out.println("[Adapter] Đang tiến hành dịch từ JSON sang XML...");
        
        String xmlData = convertJsonToXml(jsonData);
        
        xmlService.processXmlRequest(xmlData);
    }

    private String convertJsonToXml(String jsonData) {
        // Mô phỏng: {"user": {"name": "John"}} -> <user><name>John</name></user>
        return "<user><name>John</name></user>";
    }
}