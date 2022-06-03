package hw16;

import com.hw16.model.Author;
import com.hw16.model.Book;
import com.hw16.repository.AuthorRepository;
import com.hw16.repository.BookRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BookRepositoryTest {
    private final Author author = new Author("Terry", "Pratchett");
    private final Book book = new Book("Good Omens", this.author);

    @Test
    public void testSave() {
        var repository = new BookRepository();
        repository.save(this.book);

        Assert.assertTrue(
            repository.findOneByTitle(this.book.getTitle()).isPresent()
        );
    }

    @Test
    public void testFindOneByTitle() {
        var repository = new BookRepository();
        repository.save(this.book);

        Optional<Book> book = repository.findOneByTitle(this.book.getTitle());
        Assert.assertTrue(book.isPresent());
    }

    @Test
    public void testFindByAuthor() {
        List<Book> books = Arrays.asList(
            new Book("Test-1", this.author),
            new Book("Test-2", this.author),
            new Book("Test-3", this.author)
        );
        var repository = new BookRepository();

        for (Book book : books) {
            repository.save(book);
        }

        List<Book> booksByAuthor = repository.findByAuthor(this.author);
        boolean isAllBookInListThisAuthor = true;

        for (Book b : booksByAuthor) {
            if (!b.getAuthor().equals(this.author)) {
                isAllBookInListThisAuthor = false;
            }

            repository.remove(b);
        }

        Assert.assertTrue(isAllBookInListThisAuthor);
    }

    @Test
    public void testRemove() {
        var repository = new BookRepository();

        repository.save(this.book);
        Optional<Book> existBook = repository.findOneByTitle(this.book.getTitle());
        Assert.assertTrue(existBook.isPresent());

        repository.remove(this.book);
        Optional<Book> book = repository.findOneByTitle(this.book.getTitle());
        Assert.assertTrue(book.isEmpty());
    }

    @After
    public void after() {
        var bookRepository = new BookRepository();
        Optional<Book> book = bookRepository.findOneByTitle(this.book.getTitle());

        if (book.isPresent()) {
            bookRepository.remove(this.book);

            var authorRepository = new AuthorRepository();
            Optional<Author> author = authorRepository.findOneByName(this.author.getLastName());

            if (author.isPresent()) {
                authorRepository.remove(this.author);
            }
        }
    }
}