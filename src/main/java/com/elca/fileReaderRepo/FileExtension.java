package com.elca.fileReaderRepo;

import com.elca.entity.Company;
import com.elca.exception.FileExtensionNotSupported;
import com.elca.exception.FileExtensionNotValid;

import java.util.List;

public interface FileExtension {
    List<Company> readFile(String path);
}
