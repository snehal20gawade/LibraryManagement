package library.librarymanagement.service;

import library.librarymanagement.configuaration.LibraryConfiguration;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDate;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {
    private ValidationService validationService;
    @Mock
    private LibraryConfiguration bookIssueCondition;
    @Mock
    private BookService bookService;
    @Mock
    private StudentService studentService;
    @Before
    public void setUp() {
        validationService = new ValidationService(bookIssueCondition,bookService,studentService);
    }

    @Test
    public void canBookAssignToStudent() {


    }

    @Test
    public void calculateFineBeforeDueDate() {
        LocalDate issueDate = LocalDate.of(2018, 12, 15);
        LocalDate returnDate = LocalDate.of(2018, 12, 25);
        int expectedFine = 0;
        int actualFine = validationService.calculateFine(issueDate, returnDate);
        assertThat(actualFine, CoreMatchers.equalTo(expectedFine));
    }

    @Test
    public void calculateFineOverDueDate() {
        when(bookIssueCondition.getAssignPeriod()).thenReturn(21);
        when(bookIssueCondition.getFinePerDayInGbp()).thenReturn(1);
        LocalDate issuedate = LocalDate.of(2018, 12, 1);
        LocalDate returndate = LocalDate.of(2018, 12, 25);
        int expectedFine = 3;
        int actualfine = validationService.calculateFine(issuedate, returndate);
        assertThat(actualfine, CoreMatchers.equalTo(expectedFine));
    }

}