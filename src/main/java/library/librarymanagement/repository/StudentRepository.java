package library.librarymanagement.repository;

import library.librarymanagement.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

    Optional<Student> findById(int studentId);
    List<Student> findAll();
}
