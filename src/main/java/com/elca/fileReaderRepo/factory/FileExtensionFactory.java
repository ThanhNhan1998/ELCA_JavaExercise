package com.elca.fileReaderRepo.factory;

import com.elca.enumType.FileExtensionType;
import com.elca.fileReaderRepo.FileExtension;
import com.elca.fileReaderRepo.impl.FileCSVReader;
import com.elca.fileReaderRepo.impl.FileExcelReader;

public class FileExtensionFactory {

    private FileExtensionFactory(){}

    public static FileExtension getFileExtension(FileExtensionType extensionType){
        switch (extensionType){
            case CSV:
                return new FileCSVReader();
            case EXCEL:
                return new FileExcelReader();
            default:
                return null;
        }
    }
}
