package library.librarymanagement.service;

import library.librarymanagement.Model.Book;
import library.librarymanagement.Model.Student;
import library.librarymanagement.exception.InvalidBookException;
import library.librarymanagement.exception.InvalidStudentException;
import library.librarymanagement.exception.LibraryException;
import library.librarymanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private BookService bookService;
    private ValidationService validationService;

    public ValidationService getValidationService() {
        return validationService;
    }

    @Autowired
    public void setValidationService(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              BookService bookService) {
        this.studentRepository = studentRepository;
        this.bookService = bookService;

    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    @Override
    public Optional<Student> getStudent(int id) {
        Optional<Student> student = studentRepository.findById(id);
        return student;
    }

    @Override
    public void issueBook(int studentId, int bookId) throws LibraryException {
        Optional<Student> student = getStudent(studentId);
        List<Book> books = student.orElseThrow(() -> new InvalidStudentException("Invalid Student Id")).getBooks();
        if (validationService.canBookAssignToStudent(books)) {
            Optional<Book> book = bookService.getBook(bookId);
            books.add(book.orElseThrow(() -> new InvalidBookException("Invalid Book Id")));
            student.get().setBooks(books);
            saveStudent(student.get());
        }
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void removeStudent(int studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void returnBook(int studentId, int bookId) throws LibraryException {
        Optional<Student> student = getStudent(studentId);
        List<Book> books = student.orElseThrow(
                () -> new InvalidStudentException("Invalid Student Id")).getBooks();
        Optional<Book> book = bookService.getBook(bookId);
        books.remove(book.orElseThrow(() -> new InvalidBookException("Invalid Book Id")));
        student.get().setBooks(books);
        saveStudent(student.get());
    }
}
