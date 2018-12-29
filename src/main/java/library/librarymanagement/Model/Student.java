package library.librarymanagement.Model;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "student")
@Entity
public class Student {
    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "mySeq", initialValue = 101, allocationSize = 1000)
    @GeneratedValue(generator = "mySeqGen")
    private int id;
    private String name;
    @OneToMany
    private List<Book> books;

}
