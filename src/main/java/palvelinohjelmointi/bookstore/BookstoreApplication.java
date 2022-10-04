package palvelinohjelmointi.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import palvelinohjelmointi.bookstore.domain.Book;
import palvelinohjelmointi.bookstore.domain.BookRepository;
import palvelinohjelmointi.bookstore.domain.Category;
import palvelinohjelmointi.bookstore.domain.CategoryRepository;
import palvelinohjelmointi.bookstore.domain.AppUser;
import palvelinohjelmointi.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {
	
	private static final Logger Log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	@Autowired
	BookRepository repository;
	@Autowired
	CategoryRepository crepository;
	@Autowired
	UserRepository urepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Log.info("DEMODATA");
		Log.info("create categories");
		crepository.save(new Category("Good Books"));
		crepository.save(new Category("Bad Books"));
		crepository.save(new Category("Forbidden Books"));
		
		Log.info("create books");
		repository.save(new Book("TestiKirja1","TestiKirjailija1","TestiISBN1",crepository.findByName("Good Books").get(0),2001,100));
		repository.save(new Book("TestiKirja2","TestiKirjailija2","TestiISBN2",crepository.findByName("Good Books").get(0),2002,200));
		repository.save(new Book("TestiKirja3","TestiKirjailija3","TestiISBN3",crepository.findByName("Bad Books").get(0),2003,300));
		repository.save(new Book("TestiKirja4","TestiKirjailija4","TestiISBN4",crepository.findByName("Forbidden Books").get(0),2004,400));
		
		Log.info("fetch all books");
		for (Book book : repository.findAll()) {
			Log.info(book.toString());	
		}

		Log.info("create users");
		urepository.save(new AppUser("user","$2a$10$70IwomQWlpcrwfcZpbd1QOkjGjrkge5VQUIMDqcDimZxzlTcgejBe","USER"));
		urepository.save(new AppUser("admin","$2a$10$N0unyVeR.p//fMmoenU/8OBTa6dOmLmvicckebf//j/26WKIbIaam","ADMIN"));

	}
}
