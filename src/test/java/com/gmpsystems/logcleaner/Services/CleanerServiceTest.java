package com.gmpsystems.logcleaner.Services;

import com.gmpsystems.logcleaner.Config.CleanerCleanseInformation;
import com.gmpsystems.logcleaner.Config.CleanerFieldMode;
import com.gmpsystems.logcleaner.Config.CleanerFieldType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

    @Test
    void handleEmailString_Should_Remove_Email_From_String_If_Undefined() {
        ArrayList<String> list = new ArrayList<>();
        list.add("anothermail@pen.is");
        list.add("email@asd.se");
        CleanerCleanseInformation info = new CleanerCleanseInformation();
        info.setCleanerFieldMode(CleanerFieldMode.REMOVE);
        info.setFieldType(CleanerFieldType.EMAIL);
        info.setEmailReplaceUndefinedEmails(true);


        String line = "email@asd.se is totally an email";
        String line2 = " is totally an email";
        String check = service.handleEmailString(1, line, list, info, "None");

        assertEquals(check, line2);
    }

    @Test
    void handleEmailString_Should_Not_Remove_Email_From_String_If_Undefined() {
        ArrayList<String> list = new ArrayList<>();
        list.add("anothermail@pen.is");
        list.add("email@asd.se");
        CleanerCleanseInformation info = new CleanerCleanseInformation();
        info.setCleanerFieldMode(CleanerFieldMode.REPLACE);
        info.setFieldType(CleanerFieldType.EMAIL);
        info.setEmailReplaceUndefinedEmails(false);


        String line = "email@asd.se is totally an email";
        String check = service.handleEmailString(1, line, list, info, "None");

        assertEquals(check, line);
    }

    @Test
    void getWordsFromString_Should_All_Be_Detected() {
        String testString = "736433255083171855\t2016-11-19T00:49:52\t2016-11-19T00:49:52Z\t83271784\tgmp365\t54.158.5.38\tLocal7\tInfo\tapp/web.1\t2016-11-19 00:49:52 INFO  AjaxAuthenticationSuccessHandler:45 - User 'claire.delahunty@dentsuaegis.com' logged in from '204.128.220.10'";
        List<String> emails = service.getWordsFromString(testString);
        assertEquals(43, emails.size());
    }


}