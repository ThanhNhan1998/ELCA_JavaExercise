package com.elca.service.impl;

import com.elca.constant.Constant;
import com.elca.entity.Company;
import com.elca.enumType.FileExtensionType;
import com.elca.exception.FileExtensionNotSupported;
import com.elca.exception.FileExtensionNotValid;
import com.elca.fileReaderRepo.FileExtension;
import com.elca.fileReaderRepo.factory.FileExtensionFactory;
import com.elca.service.ICompanyService;
import com.elca.util.ValidateFileUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyService implements ICompanyService {

    private static CompanyService instance;

    private CompanyService(){

    }

    public static CompanyService getInstance(){
        if (instance == null){
            instance = new CompanyService();
        }

        return instance;
    }

    @Override
    public List<Company> findAll() throws FileExtensionNotValid, FileExtensionNotSupported{

        List<Company> companies;

        FileExtension reader;

        String filePath = Constant.filePath;

        if (!ValidateFileUtils.isCorrectFilePath(filePath)){
            throw new FileExtensionNotValid("This file path: "+filePath+ " is not valid");
        }

        if (ValidateFileUtils.isCSVFileExtension(filePath)){

            reader = FileExtensionFactory.getFileExtension(FileExtensionType.CSV);
            companies = reader.readFile(filePath);

        }else if (ValidateFileUtils.isExcelFile(filePath)){

            reader = FileExtensionFactory.getFileExtension(FileExtensionType.EXCEL);
            companies = reader.readFile(filePath);

        }else{
            throw new FileExtensionNotSupported("This file extension is not supported");
        }

        return companies;
    }

    /* Return total capital of headquarters in CH*/
    @Override
    public Double totalCapital() {

        Double sumCap = 0.0d;

        List<Company> companies = null;

        try {
            companies = this.findAll();

            companies = companies.stream()
                    .filter(item -> item.isHeadQuarter() != null)
                    .filter(Company::isHeadQuarter)
                    .filter(item -> item.getCountry().equals("CH")).collect(Collectors.toList());

            for(Company company: companies){
                sumCap += company.getCapital();
            }

        } catch (FileExtensionNotValid | FileExtensionNotSupported fileException) {
            System.out.println(fileException.getMessage());
        }

        return sumCap;
    }

    /* Print out the names of the companies which are located in CH sort by name (DESC)*/
    @Override
    public void filterAndPrint() {
        List<Company> companies = null;

        try {
            companies = this.findAll();

            companies.stream()
                    .filter(item -> item.getCountry().equals("CH"))
                    .sorted(Comparator.comparing(Company::getName).reversed())
                    .forEach(c -> System.out.println(c.getName()));

        } catch (FileExtensionNotValid | FileExtensionNotSupported fileException) {
            System.out.println(fileException.getMessage());
        }
    }
}
