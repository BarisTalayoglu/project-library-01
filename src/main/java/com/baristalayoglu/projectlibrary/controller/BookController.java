package com.baristalayoglu.projectlibrary.controller;

import com.baristalayoglu.projectlibrary.dao.BookRepository;
import com.baristalayoglu.projectlibrary.entity.Book;
import com.baristalayoglu.projectlibrary.entity.Category;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookRepository bookRepository;



    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookController() {
    }

    @GetMapping("/list")
    public String listBooks(Model theModel){
        List<Book> books = bookRepository.findAll();
        theModel.addAttribute("books",books);

        return "list-books";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Book newBook = new Book();
        theModel.addAttribute("book",newBook);

        return "book-form";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book theBook){
        bookRepository.save(theBook);
        return "redirect:/books/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id")int theId, Model theModel){
        Optional<Book> theBook = bookRepository.findById(theId);
        theModel.addAttribute("book",theBook);

        return "book-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        bookRepository.deleteById(id);
        return "redirect:/books/list";
    }

    @GetMapping("/order")
    public String orderBook(@RequestParam("id")int id, Model theModel){
        Optional<Book> theBook = bookRepository.findById(id);
        theBook.get().setBookStatus(false);
        theModel.addAttribute("book",theBook);

        return "book-order";
    }

    @GetMapping("/reciept")
    public String getReciept(@ModelAttribute("book")Book theBook, Model theModel){
        bookRepository.save(theBook);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Calendar futureDate = Calendar.getInstance();
        futureDate.add(Calendar.DATE, 14);
        theModel.addAttribute("futureDate",futureDate.getTime());
        theModel.addAttribute("book",theBook);
        theModel.addAttribute("date",date);
        return "book-reciept";
    }

    @ModelAttribute("categories")
    public List<Category> getCategories(){
        List<Category> categoryList = new ArrayList<>();
        List<Book> bookList = bookRepository.findAll();
        Category comedyCategory = new Category("Comedy");
        Category dramaCategory = new Category("Drama");
        Category adventureCategory = new Category("Adventure");
        Category childrenCategory = new Category("Children");
        Category crimeCategory = new Category("Crime");

        for(Book book: bookList){
            if(book.getBookType().equals("Adventure")) {
                adventureCategory.getBooks().add(book);
            }
            else if(book.getBookType().equals("Drama")) {
                dramaCategory.getBooks().add(book);
            }
            else if(book.getBookType().equals("Comedy")) {
                comedyCategory.getBooks().add(book);
            }
            else if(book.getBookType().equals("Children")) {
                childrenCategory.getBooks().add(book);
            }
            else if(book.getBookType().equals("Crime")) {
                crimeCategory.getBooks().add(book);
            }
        }
        categoryList.add(comedyCategory);
        categoryList.add(dramaCategory);
        categoryList.add(adventureCategory);
        categoryList.add(childrenCategory);
        categoryList.add(crimeCategory);


        return categoryList;
    }

   /* @PostConstruct
    public void populateCategoryList(){
        List<Book> bookList = bookRepository.findAll();
        Category comedyCategory = new Category();
        Category dramaCategory = new Category();
        Category adventureCategory = new Category();
        Category childrenCategory = new Category();
        Category crimeCategory = new Category();

        for(Book book: bookList){
            if(book.getBookType().equals("Comedy")) {
                comedyCategory.setCategoryName("Comedy");
                comedyCategory.getBooks().add(book);
            }
            else if(book.getBookType().equals("Drama")) {
                comedyCategory.setCategoryName("Drama");
                comedyCategory.getBooks().add(book);
            }
            else if(book.getBookType().equals("Adventure")) {
                comedyCategory.setCategoryName("Adventure");
                comedyCategory.getBooks().add(book);
            }
            else if(book.getBookType().equals("Children")) {
                comedyCategory.setCategoryName("Children");
                comedyCategory.getBooks().add(book);
            }
            else if(book.getBookType().equals("Crime")) {
                comedyCategory.setCategoryName("Crime");
                comedyCategory.getBooks().add(book);
            }
        }
        categoryList.add(comedyCategory);
        categoryList.add(dramaCategory);
        categoryList.add(adventureCategory);
        categoryList.add(childrenCategory);
        categoryList.add(crimeCategory);
    }
    */


}
