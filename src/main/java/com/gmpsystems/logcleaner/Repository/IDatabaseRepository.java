package com.gmpsystems.logcleaner.Repository;

import com.gmpsystems.logcleaner.Config.CleanerCleanseInformation;
import com.gmpsystems.logcleaner.Config.CleanerDatabaseUnit;

import java.util.ArrayList;

public interface IDatabaseRepository {
    ArrayList<CleanerDatabaseUnit> getUsers(CleanerCleanseInformation cleanerCleanseInformation);
}
