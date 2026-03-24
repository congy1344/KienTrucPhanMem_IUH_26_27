package fit.se.composite;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class Folder implements FileSystemComponent {
    private String name;
    // Điểm cốt lõi của Composite: Chứa danh sách các Component chung
    private List<FileSystemComponent> children;

    public Folder(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    // Các phương thức quản lý con (Chỉ Folder mới có)
    public void addComponent(FileSystemComponent component) {
        children.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public void showDetails(String indent) {
        DecimalFormat df = new DecimalFormat("#,###");
        System.out.println(indent + "+ [Thư mục] " + name + " | Tổng dung lượng: " + df.format(getSize()) + " KB");
        
        // Đệ quy gọi showDetails của các thành phần con với khoảng trắng thụt lề dài hơn
        for (FileSystemComponent component : children) {
            component.showDetails(indent + "    "); 
        }
    }

    @Override
    public long getSize() {
        long totalSize = 0;
        // Cộng dồn kích thước của tất cả các thành phần con bên trong
        for (FileSystemComponent component : children) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}