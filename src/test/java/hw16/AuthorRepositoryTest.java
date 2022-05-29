package hw16;

import com.hw16.model.Author;
import com.hw16.repository.AuthorRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class AuthorRepositoryTest {
    private final Author author = new Author("Volodymyr", "Zelenskyy");

    @Test
    public void testSave() {
        var repository = new AuthorRepository();
        repository.save(this.author);

        Assert.assertTrue(
            repository.findOneByName(this.author.getLastName()).isPresent()
        );
    }

    @Test
    public void testFindOneByName() {
        var repository = new AuthorRepository();
        repository.save(this.author);

        Optional<Author> author = repository.findOneByName(this.author.getLastName());
        Assert.assertTrue(author.isPresent());
    }

    @Test
    public void testRemove() {
        var repository = new AuthorRepository();

        repository.save(this.author);
        Optional<Author> existAuthor = repository.findOneByName(this.author.getLastName());
        Assert.assertTrue(existAuthor.isPresent());

        repository.remove(this.author);
        Optional<Author> author = repository.findOneByName(this.author.getLastName());
        Assert.assertTrue(author.isEmpty());
    }

    @After
    public void after() {
        var repository = new AuthorRepository();
        Optional<Author> author = repository.findOneByName(this.author.getLastName());

        if (author.isPresent()) {
            repository.remove(this.author);
        }
    }
}