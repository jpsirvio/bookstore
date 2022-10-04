package palvelinohjelmointi.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import palvelinohjelmointi.bookstore.domain.Book;
import palvelinohjelmointi.bookstore.domain.BookRepository;
import palvelinohjelmointi.bookstore.domain.CategoryRepository;
import palvelinohjelmointi.bookstore.domain.UserRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository; 
	
	@Autowired
	private CategoryRepository crepository;

	@Autowired
	private UserRepository urepository;
	
	//Show all books
    @RequestMapping(value= {"/","/index"})
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        model.addAttribute("appusers", urepository.findAll());
        return "index";
    }
    
    
    // Add a new book
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value= {"/add"})
    public String addBook(Model model) {	
        model.addAttribute("book", new Book());
        model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }
      
    
    // Save a new added book
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:index";
    }
    
    
    // Delete a book
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long id, Model model) {
    	repository.deleteById(id);
        return "redirect:../index";
    }     
    
    
    // Edit a book
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value= {"/edit/{id}"}, method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long id, Model model) {	
        model.addAttribute("editBook", repository.findById(id));
        model.addAttribute("categories", crepository.findAll());
        return "editbook";
    }
    
}
