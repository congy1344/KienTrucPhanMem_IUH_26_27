package fit.se;


import fit.se.observer.Investor;
import fit.se.observer.Stock;

public class Main {
    public static void main(String[] args) {
        System.out.println("====== HỆ THỐNG THEO DÕI CỔ PHIẾU (OBSERVER PATTERN) ======\n");

        // 1. Tạo các mã cổ phiếu (Subject)
        Stock vcb = new Stock("VCB", 85000);
        Stock fpt = new Stock("FPT", 110000);

        // 2. Tạo các nhà đầu tư (Observer)
        Investor inv1 = new Investor("Nguyễn Văn A");
        Investor inv2 = new Investor("Trần Thị B");

        // 3. Đăng ký theo dõi
        vcb.registerObserver(inv1); // A theo dõi VCB
        vcb.registerObserver(inv2); // B cũng theo dõi VCB
        fpt.registerObserver(inv1); // A theo dõi thêm cả FPT

        // 4. Bắt đầu mô phỏng thị trường biến động
        System.out.println("--- Sàn giao dịch: VCB tăng giá ---");
        // Khi gọi setPrice, nó sẽ TỰ ĐỘNG gửi tin nhắn cho A và B
        vcb.setPrice(86500);

        System.out.println("\n--- Sàn giao dịch: FPT giảm giá ---");
        // Chỉ gửi tin nhắn cho A, vì B không theo dõi FPT
        fpt.setPrice(108000);

        System.out.println("\n--- Nhà đầu tư A chốt lời, hủy theo dõi VCB ---");
        vcb.removeObserver(inv1); // A rút lui

        System.out.println("--- Sàn giao dịch: VCB tiếp tục tăng giá ---");
        // Lúc này chỉ còn B nhận được thông báo
        vcb.setPrice(87000);
    }
}