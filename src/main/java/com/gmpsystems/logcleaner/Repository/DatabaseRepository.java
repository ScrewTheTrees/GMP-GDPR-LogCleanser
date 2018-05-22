package com.gmpsystems.logcleaner.Repository;

import com.gmpsystems.logcleaner.Config.CleanerCleanseInformation;
import com.gmpsystems.logcleaner.Config.CleanerDatabaseUnit;

import java.util.ArrayList;

public interface DatabaseRepository {
    ArrayList<CleanerDatabaseUnit> getUsers(CleanerCleanseInformation cleanerCleanseInformation);
}
