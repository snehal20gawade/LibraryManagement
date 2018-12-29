package library.librarymanagement.service;

import library.librarymanagement.configuaration.LibraryConfiguration;
import library.librarymanagement.Model.Book;
import library.librarymanagement.Model.Student;
import library.librarymanagement.exception.InvalidBookException;
import library.librarymanagement.exception.InvalidStudentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ValidationService {
    private LibraryConfiguration libraryConfiguration;
    private StudentService studentService;
    private BookService bookService;

    @Autowired
    public ValidationService(LibraryConfiguration libraryConfiguration, BookService bookService, StudentService studentService) {
        this.libraryConfiguration = libraryConfiguration;
        this.bookService = bookService;
        this.studentService = studentService;
    }


    public boolean canBookAssignToStudent(List<Book> books) {
        if (books.size() > libraryConfiguration.getMaximumBookAllocation()) {
            return false;
        } else {
            return true;
        }
    }

    public int calculateFine(LocalDate issueDate, LocalDate returnDate) {
        int fine = 0;
        Period period = Period.between(issueDate, returnDate);
        int bookReturnDays = period.getDays();
        if (bookReturnDays >= libraryConfiguration.getAssignPeriod()) {
            fine = bookReturnDays - libraryConfiguration.getAssignPeriod() * libraryConfiguration.getFinePerDayInGbp();
        }
        return fine;
    }

    public void isBookStudentPresent(int studentId, int bookId) throws InvalidBookException,InvalidStudentException{
        Optional<Book> book = bookService.getBook(bookId);
        Optional<Student> student = studentService.getStudent(studentId);
        book.orElseThrow(()-> new InvalidBookException("Invalid Book Id"));
        student.orElseThrow(()-> new InvalidStudentException("Invalid Student Id"));

    }
}
