package com.gmpsystems.logcleaner.Config;

import java.util.ArrayList;

public class CleanerCleanseInformation {

    private CleanerFieldMode cleanerFieldMode = CleanerFieldMode.NONE;

    private String replaceFromField = "";
    private String replaceToField = "";

    private CleanerFieldType fieldType = CleanerFieldType.NONE;

    private boolean outputToGzip = false;

    private ArrayList<CleanerDatabaseUnit> users = new ArrayList<>();

    private boolean emailReplaceUndefinedEmails = false;
    private String emailReplaceUndefinedString = "";


    public CleanerFieldMode getCleanerFieldMode() {
        return cleanerFieldMode;
    }

    public void setCleanerFieldMode(CleanerFieldMode cleanerFieldMode) {
        this.cleanerFieldMode = cleanerFieldMode;
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


    public boolean isOutputToGzip() {
        return outputToGzip;
    }

    public void setOutputToGzip(boolean outputToGzip) {
        this.outputToGzip = outputToGzip;
    }

    public CleanerFieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(CleanerFieldType fieldType) {
        this.fieldType = fieldType;
    }

    public ArrayList<CleanerDatabaseUnit> getUsers() {
        return users;
    }

    public boolean isEmailReplaceUndefinedEmails() {
        return emailReplaceUndefinedEmails;
    }

    public void setEmailReplaceUndefinedEmails(boolean emailReplaceUndefinedEmails) {
        this.emailReplaceUndefinedEmails = emailReplaceUndefinedEmails;
    }

    public String getEmailReplaceUndefinedString() {
        return emailReplaceUndefinedString;
    }

    public void setEmailReplaceUndefinedString(String emailReplaceUndefinedString) {
        this.emailReplaceUndefinedString = emailReplaceUndefinedString;
    }
}



