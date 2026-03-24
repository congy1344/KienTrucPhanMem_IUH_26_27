package fit.se.composite;

import java.text.DecimalFormat;

public class FileItem implements FileSystemComponent {
    private String name;
    private long size;

    public FileItem(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void showDetails(String indent) {
        DecimalFormat df = new DecimalFormat("#,###");
        System.out.println(indent + "- [Tập tin] " + name + " (" + df.format(size) + " KB)");
    }

    @Override
    public long getSize() {
        return size;
    }
}