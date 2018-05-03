package dto.gmp.logcleaner.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LogCompressionServiceTest {

    LogCompressionService service;

    @BeforeEach
    void setUp() {
        service = new LogCompressionService();
    }

    @Test
    void applicationCanFindFilesInDirectories() {
        List<File> testDir = service.getAllFilesInDirectory(new File("").getAbsolutePath());
        System.out.println(new File("").getAbsolutePath());

        assertTrue(testDir.size() > 0);
    }
}