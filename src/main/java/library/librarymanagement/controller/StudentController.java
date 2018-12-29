package library.librarymanagement.controller;

import library.librarymanagement.Model.BookStudent;
import library.librarymanagement.Model.Student;
import library.librarymanagement.exception.InvalidBookException;
import library.librarymanagement.exception.InvalidStudentException;
import library.librarymanagement.exception.LibraryException;
import library.librarymanagement.repository.BookRepository;
import library.librarymanagement.repository.StudentRepository;
import library.librarymanagement.service.BookService;
import library.librarymanagement.service.StudentService;
import library.librarymanagement.service.ValidationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StudentController {
    private StudentService studentService;
    private BookService bookService;
    private ValidationService validationService;

    public StudentController(StudentService studentService, BookService bookService, ValidationService validationService) {
        this.studentService = studentService;
        this.bookService = bookService;
        this.validationService = validationService;
    }

    @GetMapping("/students")
    public ModelAndView studentsRegistered() {
        ModelAndView modelAndView = new ModelAndView();
        List<Student> students = studentService.getAllStudents();
        modelAndView.addObject("students", students);
        modelAndView.setViewName("students");
        return modelAndView;
    }

    @GetMapping("/addStudent")
    public ModelAndView registerStudent() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(new Student());
        modelAndView.setViewName("registerstudent");
        return modelAndView;
    }

    @PostMapping("/submitStudent")
    public ModelAndView submitStudent(@ModelAttribute Student student) {
        ModelAndView modelAndView = new ModelAndView();
        studentService.saveStudent(student);
        modelAndView.addObject(new Student());
        modelAndView.setViewName("registerstudent");
        return modelAndView;
    }

    @GetMapping("/issuebook")
    public ModelAndView issueBookToStudent() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("issuebook");
        modelAndView.addObject("books", bookService.getAllBooks());
        modelAndView.addObject("students", studentService.getAllStudents());
        modelAndView.addObject(new BookStudent());
        return modelAndView;
    }

    @PostMapping("/issuebook")
    public ModelAndView issueBookToStudent(@ModelAttribute BookStudent bookStudent) {
        String validationMessage = "Book Issue Successfully";
        ModelAndView modelAndView = new ModelAndView();
        try {
            studentService.issueBook(bookStudent.getStudentId(), bookStudent.getBookId());
        } catch (LibraryException e) {
            validationMessage = e.getMessage();
        }
        modelAndView.addObject("validationMessage", validationMessage);
        return modelAndView;
    }

    @GetMapping("/returnBookToLibrary")
    public ModelAndView returnBook() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(new BookStudent());
        modelAndView.setViewName("returnbook");
        return modelAndView;
    }

    @PostMapping("/returnBookToLibrary")
    public ModelAndView returnBook(@ModelAttribute BookStudent bookStudent) {
        String validationMessage = "Book successfully Return";
        ModelAndView modelAndView = new ModelAndView();
        try {
            validationService.isBookStudentPresent(bookStudent.getStudentId(), bookStudent.getBookId());
            studentService.returnBook(bookStudent.getStudentId(), bookStudent.getBookId());
        } catch (LibraryException e) {
            validationMessage = e.getMessage();
        }
        modelAndView.addObject("validationMessage", validationMessage);
        modelAndView.setViewName("returnbook");
        return modelAndView;
    }

    @DeleteMapping("/studentId")
    public void removeStudent(@PathVariable int studentId) {
        studentService.removeStudent(studentId);
    }
}
