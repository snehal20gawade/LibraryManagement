package library.librarymanagement.service;

import library.librarymanagement.Model.Student;
import library.librarymanagement.exception.InvalidBookException;
import library.librarymanagement.exception.InvalidStudentException;
import library.librarymanagement.exception.LibraryException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {

    List<Student> getAllStudents();

    Optional<Student> getStudent(int id);

    void issueBook(int studentId, int bookId) throws LibraryException;

    void saveStudent(Student student);

    void removeStudent(int studentId);

    void returnBook(int studentId,int bookId)throws LibraryException;

}
