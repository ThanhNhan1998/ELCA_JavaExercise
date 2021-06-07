package com.elca.utils.impl;

import com.elca.entity.Company;
import com.elca.exception.FileExtensionUnformatted;
import com.elca.utils.FileExtension;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileCSVReader implements FileExtension {

    @Override
    public String getFileExtensionName() {
        return "CSV";
    }

    private boolean isCSVFile(String path){
        return path.endsWith("csv");
    }

    @Override
    public List<Company> readFile(String path) throws FileExtensionUnformatted {

        if (!isCSVFile(path)){
            throw new FileExtensionUnformatted();
        }

        List<Company> companies = new ArrayList<>();
        FileReader fileReader = null;

        try{
            fileReader = new FileReader(path);

            companies = new CsvToBeanBuilder(fileReader)
                    .withType(Company.class)
                    .withSkipLines(1)
                    .build()
                    .parse();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return companies;
    }
}
