package com.gmpsystems.logcleaner.Config;

public class CleanerDatabaseUnit {

    public CleanerDatabaseUnit(String replaceFrom, String replaceTo) {
        ReplaceFrom = replaceFrom;
        ReplaceTo = replaceTo;
    }

    public String ReplaceFrom;
    public String ReplaceTo;
}
