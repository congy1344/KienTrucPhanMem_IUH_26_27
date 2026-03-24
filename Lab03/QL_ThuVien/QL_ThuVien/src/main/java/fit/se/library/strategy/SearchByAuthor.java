package fit.se.library.strategy;

import fit.se.library.factory.Book;

import java.util.List;
import java.util.stream.Collectors;

public class SearchByAuthor implements SearchStrategy {
    public List<Book> search(List<Book> books, String keyword) {
        return books.stream()
            .filter(b -> b.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }
}