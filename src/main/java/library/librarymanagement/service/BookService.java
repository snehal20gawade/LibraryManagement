package library.librarymanagement.service;

import library.librarymanagement.Model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Optional<Book> getBook(int id);

    void saveBook(Book book);

}
