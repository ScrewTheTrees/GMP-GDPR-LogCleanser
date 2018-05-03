package dto.gmp.logcleaner.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CleanerServiceTest {

    CleanerService service;

    @BeforeEach
    void setUp() {
        service = new CleanerService();
    }

    @Test
    void emailsShouldAllBeDetected() {
        String testString = "fredrik.grimmenhag@gmp-systems.com\n" +
                "clarisse.dubois@media-path.com\t-\tSenior Analyst\n" +
                "susanne.elias@media-path.com\t-\tCEO\n" +
                "balazs.kaposi@dentsuaegis.com\t-\tInvestment Director";
        List<String> emails = service.getEmailsFromString(testString);
        assertEquals(emails.size(), 4);
    }

    @Test
    void emailsShouldBe3BecauseMergedEmails() {
        String testString = "fredrik.grimmenhag@gmp-systems.comclarisse.dubois@media-path.com\t-\tSenior Analyst\n" +
                "susanne.elias@media-path.com,balazs.kaposi@dentsuaegis.com\t-\tInvestment Director";
        List<String> emails = service.getEmailsFromString(testString);
        assertEquals(emails.size(), 3);
    }
}