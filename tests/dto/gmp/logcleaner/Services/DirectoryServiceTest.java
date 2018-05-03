package dto.gmp.logcleaner.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DirectoryServiceTest {

    DirectoryService service;

    @BeforeEach
    void setUp() {
        service = new DirectoryService();
    }

    @Test
    void application_Can_Find_Files_In_Directories() {
        List<File> testDir = service.getAllFilesInDirectoryAndSubdirectories(new File("").getAbsolutePath());
        System.out.println(new File("").getAbsolutePath());

        assertTrue(testDir.size() > 0);
    }
}