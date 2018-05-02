package dto.gmp.logcleaner.Services;

import dto.gmp.logcleaner.Config.LogCleanerConfig;

public class CleanerService {

    private LogCleanerConfig logCleanerConfig;

    public CleanerService(LogCleanerConfig logCleanerConfig) {
        this.logCleanerConfig = logCleanerConfig;
    }
}
