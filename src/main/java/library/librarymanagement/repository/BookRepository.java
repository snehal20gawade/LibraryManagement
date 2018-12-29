package library.librarymanagement.repository;

import library.librarymanagement.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer>{
    Optional<Book> findById(int bookId);
    List<Book> findAll();
}
