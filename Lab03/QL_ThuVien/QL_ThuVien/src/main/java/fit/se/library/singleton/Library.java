package fit.se.library.singleton;

import fit.se.library.factory.Book;
import fit.se.library.observer.Observer;
import fit.se.library.strategy.SearchStrategy;

import java.util.ArrayList;
import java.util.List;

public class Library {
    // 1. Singleton Pattern
    private static Library instance;
    private List<Book> books;
    private List<Observer> observers;
    private SearchStrategy searchStrategy;

    private Library() {
        books = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static synchronized Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    // Observer methods
    public void addObserver(Observer observer) { observers.add(observer); }
    public void notifyObservers(String message) {
        for (Observer obs : observers) obs.update(message);
    }

    // Quản lý sách
    public void addBook(Book book) {
        books.add(book);
        notifyObservers("Sách mới đã về: " + book.getTitle());
    }

    // Strategy method
    public void setSearchStrategy(SearchStrategy strategy) {
        this.searchStrategy = strategy;
    }

    public List<Book> searchBooks(String keyword) {
        if (searchStrategy == null) throw new IllegalStateException("Chưa thiết lập chiến lược tìm kiếm");
        return searchStrategy.search(books, keyword);
    }
}