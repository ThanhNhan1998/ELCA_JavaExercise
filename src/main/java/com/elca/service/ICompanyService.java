package com.elca.service;

import com.elca.entity.Company;
import com.elca.exception.FileExtensionUnformatted;
import java.util.List;

public interface ICompanyService {

    List<Company> findAllFromCSV();

    List<Company> findAllFromXLSX();

    List<Company> findAll(String path);

    Double totalCapital();

    void filterAndPrint();
}
