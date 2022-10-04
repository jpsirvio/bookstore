package palvelinohjelmointi.bookstore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(min = 1, max = 50, message = "value is not valid")
	private String title, author, isbn;
	
	@Min( value = 1000, message = "min value is 1900")
	@Max ( value = 5000, message = "max value is 2023")
	private int bookYear;
	
	@Min( value = 0, message = "min value is 0")
	private int price;
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;
	
	public Book(String title, String author, String isbn, Category category, int bookYear, int price) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.category = category;
		this.bookYear = bookYear;
		this.price = price;
	}
	public Book() {
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getBookYear() {
		return bookYear;
	}
	public void setBookYear(int bookYear) {
		this.bookYear = bookYear;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	@Override
	public String toString() {
		if(this.category != null)
			return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", bookYear="
				+ bookYear + ", price=" + price + ", category=" + category + "]";
		else
			return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", bookYear="
			+ bookYear + ", price=" + price + "]";
	} 
	
}
