package library.librarymanagement.service;

import library.librarymanagement.Model.Book;
import library.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }

    @Override
    public Optional<Book> getBook(int bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book;
    }
    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

}
