package com.gmpsystems.logcleaner.Config;

public class CleanerCleanseInformation {

    private CleanerFieldMode cleanerFieldMode = CleanerFieldMode.NONE;
    //private ArrayList<CleanerUserInformation> users = new ArrayList<>();

    private CleanerFieldType deleteFromField = CleanerFieldType.NONE;
    private CleanerFieldType replaceFromField = CleanerFieldType.NONE;
    private CleanerFieldType replaceToField = CleanerFieldType.NONE;



    public CleanerFieldMode getCleanerFieldMode() {
        return cleanerFieldMode;
    }

    public void setCleanerFieldMode(CleanerFieldMode cleanerFieldMode) {
        this.cleanerFieldMode = cleanerFieldMode;
    }

    /*public ArrayList<CleanerUserInformation> getUsers() {
        return users;
    }*/

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



