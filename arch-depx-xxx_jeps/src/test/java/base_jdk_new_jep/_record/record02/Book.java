package base_jdk_new_jep._record.record02;

import java.time.Year;
import java.util.Objects;

public record Book(String id, String title, Year release, int edition) {

	public Book {
		Objects.requireNonNull(id, "id is required");
		Objects.requireNonNull(title, "title is required");
		Objects.requireNonNull(release, "release is required");
		if (edition < 1) {
			throw new IllegalArgumentException("Edition cannot be negative");
		}
	}
	
	public static void main(String[] args) {
		 //Book book = Book.builder().id("id").title("Effective Java").release(Year.of(2001)).builder();
		Book book=new Book("","",null,0);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Book book = (Book) o;
		return Objects.equals(id, book.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
	
	
}