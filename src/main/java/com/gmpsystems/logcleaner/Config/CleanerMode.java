package com.gmpsystems.logcleaner.Config;

public enum CleanerMode {
    /** Do nothing, not correctly configured */
    NONE,
    /** Removes Emails from the logs */
    REMOVE,
    /** Replace Emails from the logs with something else */
    REPLACE,

    @Deprecated
    ADD
}
