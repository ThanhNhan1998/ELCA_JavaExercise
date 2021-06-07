package com.elca.myPage;

import com.elca.exception.FileExtensionUnformatted;

import java.util.List;

public interface IReadFile<T> {

    List<T> readFromCSV(String path) throws FileExtensionUnformatted;

    List<T> readFromExcel(String path);
}
