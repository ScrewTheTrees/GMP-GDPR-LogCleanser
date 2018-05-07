package com.gmpsystems.logcleaner.Config;

public enum CleanerMode {
    /** Do nothing, not correctly configured */
    NONE,
    /** Unclear how this will be implemented, it probably cannot */
    ADD,
    /** Removes Emails from the logs */
    REMOVE,
    /** Replace Emails from the logs with something else */
    REPLACE,
    /** Doesn't actually replace or delete anything, it simply logs all the occurrences it finds for finding things that should be cleaned in code */
    MOCK_LOG
}
