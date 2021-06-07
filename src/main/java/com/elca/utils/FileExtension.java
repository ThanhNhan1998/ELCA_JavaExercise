package com.elca.utils;

import com.elca.entity.Company;
import com.elca.exception.FileExtensionUnformatted;

import java.util.List;

public interface FileExtension {

    String getFileExtensionName();

    List<Company> readFile(String path) throws FileExtensionUnformatted;
}
