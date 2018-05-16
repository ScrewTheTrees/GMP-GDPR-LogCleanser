package com.gmpsystems.logcleaner.Config;

import org.bson.Document;

import java.util.ArrayList;

public class CleanerCleanseInformation {

    private CleanerFieldMode cleanerFieldMode = CleanerFieldMode.NONE;

    private String deleteFromField = "";
    private String replaceFromField = "";
    private String replaceToField = "";

    private CleanerFieldType fieldType = CleanerFieldType.NONE;

    private boolean outputToGzip = false;

    private ArrayList<Document> users;

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

    public ArrayList<Document> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Document> users) {
        this.users = users;
    }
}



