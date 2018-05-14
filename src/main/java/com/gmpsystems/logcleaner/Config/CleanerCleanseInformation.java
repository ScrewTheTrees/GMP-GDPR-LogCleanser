package com.gmpsystems.logcleaner.Config;

public class CleanerCleanseInformation {

    private CleanerFieldMode cleanerFieldMode = CleanerFieldMode.NONE;

    private String deleteFromField = "";
    private String replaceFromField = "";
    private String replaceToField = "";


    public CleanerFieldMode getCleanerFieldMode() {
        return cleanerFieldMode;
    }

    public void setCleanerFieldMode(CleanerFieldMode cleanerFieldMode) {
        this.cleanerFieldMode = cleanerFieldMode;
    }

    public String getDeleteFromField() {
        return deleteFromField;
    }

    public void setDeleteFromField(String deleteFromField) {
        this.deleteFromField = deleteFromField;
    }

    public String getReplaceFromField() {
        return replaceFromField;
    }

    public void setReplaceFromField(String replaceFromField) {
        this.replaceFromField = replaceFromField;
    }

    public String getReplaceToField() {
        return replaceToField;
    }

    public void setReplaceToField(String replaceToField) {
        this.replaceToField = replaceToField;
    }

}



