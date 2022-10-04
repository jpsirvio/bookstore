package palvelinohjelmointi.bookstore;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import palvelinohjelmointi.bookstore.web.BookController;

@SpringBootTest
class BookstoreApplicationTests {
	
	@Autowired
	BookController bc;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(bc).isNotNull();
	}

}
