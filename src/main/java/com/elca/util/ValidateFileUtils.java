package com.elca.util;

import java.io.File;

public class ValidateFileUtils {
    public static boolean isCorrectFilePath(String path) {
        return new File(path).exists();
    }

    public static boolean isCSVFileExtension(String path){
        return path.endsWith("csv");
    }

    public static boolean isExcelFile(String path){
        return path.endsWith("xls") || path.endsWith("xlsx");
    }
}
