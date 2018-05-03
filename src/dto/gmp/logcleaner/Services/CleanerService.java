package dto.gmp.logcleaner.Services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CleanerService {





    public List<String> getEmailsFromString(String getEmailString) {
        ArrayList<String> emails = new ArrayList<String>();
        Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(getEmailString);
        while (m.find()) {
            emails.add(m.group());
        }
        return emails;
    }
}
