package com.elca.myPage;

import com.elca.constant.Constant;
import com.elca.exception.FileExtensionUnformatted;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class ReadFileUtil<T>{

    private Class<T> classT;

    public ReadFileUtil(){
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();

        classT = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    private boolean isCSVFile(String path){
        return path.endsWith("csv");
    }

    private boolean isXLSFile(String path){
        return path.endsWith("xls");
    }

    private boolean isXLSXFile(String path){
        return path.endsWith("xlsx");
    }

    public List<T> readFromCSV(String path) throws FileExtensionUnformatted {

        if (!isCSVFile(path)){
            throw new FileExtensionUnformatted();
        }

        List<T> object = new ArrayList<>();
        FileReader fileReader = null;

        try{
            fileReader = new FileReader(path);

            object = new CsvToBeanBuilder(fileReader)
                    .withType(this.classT)
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

        return object;
    }

    public List<T> readFromExcel(String path){
        List<T> listBooks = new ArrayList<>();

        try {
            InputStream inputStream = new FileInputStream(new File(Constant.excelFilePath));

            Workbook workbook = getWorkBook(inputStream, Constant.excelFilePath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }

    private Workbook getWorkBook(InputStream inputStream, String excelFilePath) {
        Workbook workbook = null;

        try{
            if (isXLSXFile(excelFilePath)){
                workbook = new XSSFWorkbook(inputStream);
            }else if(isXLSFile(excelFilePath)){
                workbook = new HSSFWorkbook(inputStream);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
