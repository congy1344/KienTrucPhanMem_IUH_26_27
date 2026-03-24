package fit.se.composite;

public interface FileSystemComponent {
    void showDetails(String indent);
    
    long getSize();
}