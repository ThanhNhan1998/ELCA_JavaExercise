package com.elca.fileReaderRepo.impl;

import com.elca.entity.Company;
import com.elca.fileReaderRepo.FileExtension;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FileExcelReader implements FileExtension {

    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_NAME = 1;
    public static final int COLUMN_INDEX_FOUNDATION_DATE = 2;
    public static final int COLUMN_INDEX_CAPITAL = 3;
    public static final int COLUMN_INDEX_COUNTRY = 4;
    public static final int COLUMN_INDEX_HEADQUARTER = 5;

    private boolean isXLSXFile(String path){
        return path.endsWith("xlsx");
    }
    private boolean isXLSFile(String path){
        return path.endsWith("xls");
    }

    @Override
    public List<Company> readFile(String path){
        List<Company> companies = new ArrayList<>();
        InputStream inputStream = null;
        Workbook workbook = null;

        try {
            inputStream = new FileInputStream(new File(path));

            workbook = getWorkbook(inputStream, path);

            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()){
                Row nextRow = iterator.next();

                if (nextRow.getRowNum() == 0){
                    continue;
                }

                Iterator<Cell> cellIterator = nextRow.cellIterator();

                Company company = new Company();

                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();

                    Object cellValue = getCellValue(cell);

                    if (cellValue == null || cellValue.toString().isEmpty()){
                        continue;
                    }

                    int colIndex = cell.getColumnIndex();

                    switch (colIndex){
                        case COLUMN_INDEX_ID:
                            Double id = Double.parseDouble(String.valueOf(cellValue));
                            company.setId(id.longValue());
                            break;
                        case COLUMN_INDEX_NAME:
                            company.setName(String.valueOf(cellValue));
                            break;
                        case COLUMN_INDEX_FOUNDATION_DATE:
                            company.setFoundationDate(String.valueOf(cellValue));
                            break;
                        case COLUMN_INDEX_CAPITAL:
                            company.setCapital(Double.parseDouble(String.valueOf(cellValue)));
                            break;
                        case COLUMN_INDEX_COUNTRY:
                            company.setCountry(String.valueOf(cellValue));
                            break;
                        case COLUMN_INDEX_HEADQUARTER:
                            Double isHeadQuarter = Double.parseDouble(String.valueOf(cellValue));

                            company.setHeadQuarter(isHeadQuarter == 1.0);
                            break;
                        default:
                            break;
                    }
                }
                companies.add(company);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null){
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return companies;
    }

    private Workbook getWorkbook(InputStream inputStream, String excelFilePath) {
        Workbook workbook = null;

        try{
            if (isXLSXFile(excelFilePath)) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (isXLSFile(excelFilePath)) {
                workbook = new HSSFWorkbook(inputStream);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return workbook;
    }

    private Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;

        switch (cellType){
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            default:
                break;
        }

        return cellValue;
    }
}
