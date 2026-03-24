package fit.se;

import fit.se.composite.FileItem;
import fit.se.composite.FileSystemComponent;
import fit.se.composite.Folder;

public class Main {
    public static void main(String[] args) {
        System.out.println("====== HỆ THỐNG QUẢN LÝ THƯ MỤC (COMPOSITE PATTERN) ======\n");

        // 1. Tạo các tập tin riêng lẻ (Leaf)
        FileSystemComponent file1 = new FileItem("document.docx", 1500);
        FileSystemComponent file2 = new FileItem("logo.png", 2048);
        FileSystemComponent file3 = new FileItem("system_config.xml", 256);
        FileSystemComponent file4 = new FileItem("game_data.bin", 512000);

        // 2. Tạo thư mục và thêm các thành phần vào (Composite)
        Folder docsFolder = new Folder("Tài liệu cá nhân");
        docsFolder.addComponent(file1);

        Folder imagesFolder = new Folder("Hình ảnh");
        imagesFolder.addComponent(file2);

        // Tạo thư mục Hệ thống chứa file và thư mục con khác
        Folder systemFolder = new Folder("Hệ thống Windows");
        systemFolder.addComponent(file3);

        Folder gamesFolder = new Folder("Trò chơi");
        gamesFolder.addComponent(file4);
        systemFolder.addComponent(gamesFolder); // Thư mục chứa thư mục

        // 3. Tạo thư mục Gốc (Root) và gom tất cả lại
        Folder rootFolder = new Folder("Ổ đĩa C:");
        rootFolder.addComponent(docsFolder);
        rootFolder.addComponent(imagesFolder);
        rootFolder.addComponent(systemFolder);

        // 4. Client chỉ cần thao tác với thành phần ngoài cùng
        // Hệ thống sẽ tự động duyệt cây (Tree) để in ra toàn bộ
        rootFolder.showDetails("");
    }
}