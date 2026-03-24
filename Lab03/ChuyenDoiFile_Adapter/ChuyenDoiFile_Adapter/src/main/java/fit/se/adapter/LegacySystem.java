package fit.se.adapter;

public class LegacySystem implements XmlService {
    @Override
    public void processXmlRequest(String xmlData) {
        System.out.println("[LegacySystem] Đang xử lý dữ liệu XML: " + xmlData);
        System.out.println("[LegacySystem] Xử lý thành công!\n");
    }
}