package com.gmpsystems.logcleaner.Config;

import java.util.ArrayList;

public class CleanerCleanseInformation {

    private CleanerMode cleanerMode = CleanerMode.NONE;
    private ArrayList<CleanerUserInformation> users = new ArrayList<>();








    public CleanerMode getCleanerMode() {
        return cleanerMode;
    }

    public void setCleanerMode(CleanerMode cleanerMode) {
        this.cleanerMode = cleanerMode;
    }

    public ArrayList<CleanerUserInformation> getUsers() {
        return users;
    }
}



