package org.gaugekit.excel.io;

import com.thoughtworks.gauge.Table;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gaugekit.core.io.file.FileUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class ExcelReader {

    private static DataFormatter dataFormatter = new DataFormatter();

    private ExcelReader() {
    }


    public static Table readTable(String path, String sheetName) {
        return readTable(FileUtils.resolveDataFile(path), sheetName);
    }

    public static Table readTable(Path file, String excelFile) {
        try {
            Workbook workbook = new XSSFWorkbook(new FileInputStream(file.toFile()));
            Sheet sheet = workbook.getSheet(excelFile);
            if (sheet == null) {
                throw new RuntimeException(String.format("Sheet with name '%s' does not exist", excelFile));
            }
            List<String> headers = toTableRow(sheet.getRow(0));
            validateHeaders(headers);
            Table table = new Table(headers);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                table.addRow(toTableRow(sheet.getRow(i)));
            }
            return table;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> toTableRow(Row row) {
        List<String> tableRow = new ArrayList<String>();
        for (int i = 0; i < row.getLastCellNum(); i++) {
            tableRow.add(dataFormatter.formatCellValue(row.getCell(i)));
        }
        return tableRow;
    }

    private static void validateHeaders(List<String> headers) {
        for (int i = 0; i < headers.size(); i++) {
            String header = headers.get(i);
            if (header == null || header.equals("")) {
                throw new RuntimeException(String.format("header at index '%s' is empty", i));
            }
        }
    }

}