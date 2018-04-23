package dto.gmp.logcleaner.Config;

public class LogCleanerConfig {

    private CleanerMode cleanerMode = CleanerMode.NONE;




    public CleanerMode getCleanerMode() {
        return cleanerMode;
    }

    public void setCleanerMode(CleanerMode cleanerMode) {
        this.cleanerMode = cleanerMode;
    }
}


