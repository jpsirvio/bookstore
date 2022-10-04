package palvelinohjelmointi.bookstore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import palvelinohjelmointi.bookstore.domain.Book;
import palvelinohjelmointi.bookstore.domain.BookRepository;
import palvelinohjelmointi.bookstore.domain.Category;
import palvelinohjelmointi.bookstore.domain.CategoryRepository;

@DataJpaTest
class RepositoryTests {

	@Autowired
	BookRepository br;
	@Autowired
	CategoryRepository cr;
	
	@Test
	public void findBookByIdAndValidateCategory() {
		Category category = br.findById((long) 7).get().getCategory();
		System.out.println("Haetaan kirja id:llä 7 " + category);
		assertEquals(category.getName(), "Forbidden Books");
	}
	
	@Test
	public void findBookByIdAndValidateTitle() {
		Book book = br.findById((long) 7).get();
		System.out.println("Haetaan kirja id:llä 7 " + book);
		assertEquals(book.getTitle(), "TestiKirja4");
	}

	@Test
	public void findByCategoryNameShouldReturnNumberOFBooks() {
		List<Category> categories = cr.findByName("Good Books");
		assertEquals(categories.size(), 1);
	}

	@Test
	public void insertNewBook() {
		Book book = new Book("JUnit-kirja", "Junit-testi", "Junit-testi", cr.findByName("Good Books").get(0), 2000, 2000);
		br.save(book);
		assertNotNull(book.getId());
	}
	
	@Test
	public void deleteBook() {
		Book book = new Book("JUnit-kirja", "Junit-testi", "Junit-testi", cr.findByName("Good Books").get(0), 2000, 2000);
		br.deleteById(book.getId());
		assertNotNull(book.getId());		
	}

}

