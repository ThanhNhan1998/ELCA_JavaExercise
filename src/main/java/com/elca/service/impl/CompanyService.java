package com.elca.service.impl;

import com.elca.constant.Constant;
import com.elca.enumType.FileExtensionType;
import com.elca.exception.FileExtensionUnformatted;
import com.elca.entity.Company;
import com.elca.service.ICompanyService;
import com.elca.utils.FileExtension;
import com.elca.utils.factory.FileExtensionFactory;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
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
    public List<Company> findAllFromCSV(){

        List<Company> companies = new ArrayList<>();

        FileExtension csvReader = null;

        try {
            csvReader = FileExtensionFactory.getFileExtension(FileExtensionType.CSV);
            companies = csvReader.readFile(Constant.csvFilePath);

        }catch (FileExtensionUnformatted fileUnformatted) {
            System.out.println(fileUnformatted.getMessage());
        } finally {
            return companies;
        }
    }

    @Override
    public List<Company> findAllFromXLSX(){

        List<Company> companies = new ArrayList<>();

        FileExtension xlsxReader = null;

        try {
            xlsxReader = FileExtensionFactory.getFileExtension(FileExtensionType.CSV);
            companies = xlsxReader.readFile(Constant.csvFilePath);

        }catch (FileExtensionUnformatted fileUnformatted) {
            System.out.println(fileUnformatted.getMessage());
        } finally {
            return companies;
        }
    }

    @Override
    public List<Company> findAll(String path) {

        List<Company> companies = new ArrayList<>();

        return null;
    }

    @Override
    public Double totalCapital() {

        Double sumCap = 0.0d;

        List<Company> companies = null;

        companies = this.findAllFromCSV();

        companies = companies.stream()
                .filter(item -> item.isHeadQuarter() != null)
                .filter(item -> item.isHeadQuarter())
                .filter(item -> item.getCountry().equals("CH")).collect(Collectors.toList());

        for(Company company: companies){
            sumCap += company.getCapital();
        }

        return sumCap;
    }

    @Override
    public void filterAndPrint() {
        List<Company> companies = null;

        companies = this.findAllFromCSV();

        companies.stream()
                .filter(item -> item.getCountry().equals("CH"))
                .sorted(Comparator.comparing(Company::getName).reversed())
                .forEach(c -> System.out.println(c.getName()));
    }
}
