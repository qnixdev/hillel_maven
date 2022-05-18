package hw15;

import com.hw15.FileManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManagerTest {
    @Test
    public void testFileParser() {
        FileManager fileManager = new FileManager();
        Path path = Paths.get("src/main/resources/hw15/voyna-i-mir.txt");
        fileManager.fileParser(path);

        Assert.assertTrue(new File(fileManager.buildResultPath(path)).exists());
    }
}