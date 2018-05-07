package com.gmpsystems.logcleaner.Config;

import java.util.ArrayList;

public class CleanerCleanseInformation {

    private CleanerMode cleanerMode = CleanerMode.NONE;
    private ArrayList<CleanerUserInformation> users = new ArrayList<>();

    private CleanerFieldType deleteFromField = CleanerFieldType.NONE;
    private CleanerFieldType replaceFromField = CleanerFieldType.NONE;
    private CleanerFieldType replaceToField = CleanerFieldType.NONE;



    public CleanerMode getCleanerMode() {
        return cleanerMode;
    }

    public void setCleanerMode(CleanerMode cleanerMode) {
        this.cleanerMode = cleanerMode;
    }

    public ArrayList<CleanerUserInformation> getUsers() {
        return users;
    }

    public CleanerFieldType getReplaceToField() {
        return replaceToField;
    }

    public void setReplaceToField(CleanerFieldType replaceToField) {
        this.replaceToField = replaceToField;
    }

    public CleanerFieldType getReplaceFromField() {
        return replaceFromField;
    }

    public void setReplaceFromField(CleanerFieldType replaceFromField) {
        this.replaceFromField = replaceFromField;
    }

    public CleanerFieldType getDeleteFromField() {
        return deleteFromField;
    }

    public void setDeleteFromField(CleanerFieldType deleteFromField) {
        this.deleteFromField = deleteFromField;
    }
}



