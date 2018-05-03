package dto.gmp.logcleaner.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CleanerServiceTest {

    CleanerService service;

    @BeforeEach
    void setUp() {
        service = new CleanerService();
    }

    @Test
    void getEmailsFromString_Should_All_Be_Detected() {
        String testString = "fredrik.grimmenhag@gmp-systems.com\n" +
                "clarisse.dubois@media-path.com\t-\tSenior Analyst\n" +
                "susanne.elias@media-path.com\t-\tCEO\n" +
                "balazs.kaposi@dentsuaegis.com\t-\tInvestment Director";
        List<String> emails = service.getEmailsFromString(testString);
        assertEquals(emails.size(), 4);
    }

    @Test
    void getEmailsFromString_Should_Be_2_Because_Of_Merged_Invalid_Emails() {
        String testString = "fredrik.grimmenhag@gmp-systems.comclarisse.dubois@media-path.com\t-\tSenior Analyst\n" +
                "susanne.elias@media-path.com,balazs.kaposi@dentsuaegis.com\t-\tInvestment Director";
        List<String> emails = service.getEmailsFromString(testString);
        assertEquals(emails.size(), 2);
    }

    @Test
    void getFileExtension_should_return_file_extension() {
        String testString = "D:\\freddev\\dev\\GMP-GDPR-LogCleanser\\out\\production\\GMP-GDPR.LogCleanser\\output\\LocalLogZips\\get.gz.txt";
        String s = null;
        try {
            s = service.getFileExtension(testString);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
        assertEquals(s, ".txt");
    }

    @Test
    void getFileExtension_should_throw_exception() {

        assertThrows(Exception.class, () -> {
            String testString = "D:\\freddev\\dev\\GMP-GDPR-LogCleanser\\out\\production\\GMP-GDPR.LogCleanser\\output\\LocalLogZips\\notAFile";
            String s = service.getFileExtension(testString);
        });
    }


}