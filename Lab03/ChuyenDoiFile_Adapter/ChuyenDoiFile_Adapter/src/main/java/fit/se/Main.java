package fit.se;


import fit.se.adapter.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("====== TRƯỜNG HỢP 1: Gửi XML vào Web API chỉ nhận JSON ======");
        String xmlData = "<user><name>John</name></user>";

        // Khởi tạo API nhận JSON
        JsonService modernApi = new ModernWebAPI();

        // Khởi tạo Adapter, nhét API JSON vào trong cái vỏ bọc XML
        XmlService xmlToJsonAdapter = new XmlToJsonAdapter(modernApi);

        // Hệ thống cũ cứ vô tư gọi hàm gửi XML, Adapter sẽ lo phần còn lại
        xmlToJsonAdapter.processXmlRequest(xmlData);


        System.out.println("====== TRƯỜNG HỢP 2: Gửi JSON vào Hệ thống cũ chỉ nhận XML ======");
        String jsonData = "{ \"user\": { \"name\": \"John\" } }";

        // Khởi tạo Hệ thống cũ nhận XML
        XmlService legacySystem = new LegacySystem();

        // Khởi tạo Adapter, nhét hệ thống XML vào trong vỏ bọc JSON
        JsonService jsonToXmlAdapter = new JsonToXmlAdapter(legacySystem);

        // Web mới gửi JSON, Adapter lo việc dịch ra XML
        jsonToXmlAdapter.processJsonRequest(jsonData);
    }
}