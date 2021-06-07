package com.elca.utils.factory;

import com.elca.enumType.FileExtensionType;
import com.elca.exception.FileExtensionUnSupported;
import com.elca.utils.FileExtension;
import com.elca.utils.impl.FileCSVReader;
import com.elca.utils.impl.FileExcelReader;

public class FileExtensionFactory {

    private FileExtensionFactory(){}

    public static final FileExtension getFileExtension(FileExtensionType extensionType) throws FileExtensionUnSupported {
        switch (extensionType){
            case CSV:
                return new FileCSVReader();
            case EXCEL:
                return new FileExcelReader();
            default:
                throw new FileExtensionUnSupported();
        }
    }
}
