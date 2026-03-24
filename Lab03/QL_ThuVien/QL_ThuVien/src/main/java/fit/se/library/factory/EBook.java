package fit.se.library.factory;

class EBook implements Book {
    // Tương tự PaperBook
    private String title, author, category;
    public EBook(String title, String author, String category) {
        this.title = title; this.author = author; this.category = category;
    }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public String getBookType() { return "E-Book"; }
}