package library.librarymanagement.controller;

import library.librarymanagement.Model.Book;
import library.librarymanagement.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name",
            required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/{bookId}")
    public ModelAndView findBook(@PathVariable int bookId) {
        ModelAndView model = new ModelAndView();
        Optional<Book> book = bookService.getBook(bookId);
        model.addObject(book);
        model.setViewName("book");
        return model;
    }

    @GetMapping("/books")
    public ModelAndView getAllBook() {
        ModelAndView modelAndView = new ModelAndView();
        List<Book> books = bookService.getAllBooks();
        modelAndView.addObject("books", books);
        modelAndView.setViewName("books");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addBook() {
        ModelAndView modelAndView = new ModelAndView();
        Book book = new Book();
        modelAndView.addObject(book);
        modelAndView.setViewName("storebook");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView storeBook(@ModelAttribute Book book) {
        ModelAndView modelAndView = new ModelAndView();
        bookService.saveBook(book);
        modelAndView.setViewName("storebook");
        modelAndView.addObject(new Book());
        return modelAndView;
    }
}
