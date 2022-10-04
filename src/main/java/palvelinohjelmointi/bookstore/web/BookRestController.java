package palvelinohjelmointi.bookstore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import palvelinohjelmointi.bookstore.domain.Book;
import palvelinohjelmointi.bookstore.domain.BookRepository;
import palvelinohjelmointi.bookstore.domain.Category;
import palvelinohjelmointi.bookstore.domain.CategoryRepository;

@RestController
public class BookRestController {
	
	@Autowired
	private BookRepository repository; 
	
	@Autowired
	private CategoryRepository crepository;
	
	// REST BOOK
	// REST return list of books
	@GetMapping("/books")
	public Iterable<Book> getBooks() {
		return repository.findAll();
	}
	
	// REST find a book
	@GetMapping("/books/{id}")
	public Optional<Book> findBookRest(@PathVariable("id") Long id) {
		return repository.findById(id);
	}

	// REST add a book
	@PostMapping("books")
	Book newBook(@RequestBody Book newBook) {
		return repository.save(newBook);
	}
	
	// REST Update book
	@PutMapping("/books/{id}")
	Book editBook(@RequestBody Book editBook, @PathVariable Long id) {
		editBook.setId(id);
		return repository.save(editBook);
	}
	
	// REST Delete book
	@DeleteMapping("/books/{id}")
	public Iterable<Book> deleteBook(@PathVariable Long id) {
		repository.deleteById(id);
		return repository.findAll();
	}
	
	// REST CATEGORIES
	// REST return list of categories
	@GetMapping("/categories")
	public Iterable<Category> getCategories() {
		return crepository.findAll();
	}
	
	// REST find a category
	@GetMapping("/categories/{id}")
	public Optional<Category> findCategoriesRest(@PathVariable("categoryId") Long categoryId) {
		return crepository.findById(categoryId);
	}

	// REST add a category
	@PostMapping("categories")
	Category newBook(@RequestBody Category newCategory) {
		return crepository.save(newCategory);
	}
	
	// REST Update category
	@PutMapping("/categories/{id}")
	Category editCategory(@RequestBody Category editCategory, @PathVariable Long categoryId) {
		editCategory.setCategoryId(categoryId);
		return crepository.save(editCategory);
	}
	
	// REST Delete category
	@DeleteMapping("/categories/{id}")
	public Iterable<Category> deleteCategory(@PathVariable Long categoryId) {
		crepository.deleteById(categoryId);
		return crepository.findAll();
	}
	
}
