package com.elca.service;

import com.elca.entity.Company;
import com.elca.exception.FileExtensionNotSupported;
import com.elca.exception.FileExtensionNotValid;

import java.util.List;

public interface ICompanyService {

    List<Company> findAll() throws FileExtensionNotValid, FileExtensionNotSupported;

    Double totalCapital();

    void filterAndPrint();
}
