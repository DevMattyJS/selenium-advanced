package helpers;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    private Workbook data;

    public ExcelReader(String dataPath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(dataPath);
        this.data = new XSSFWorkbook(fileInputStream);
    }

    public Workbook getData() {
        return data;
    }

    public Sheet getSheetByName(String sheetName) {
        return data.getSheet(sheetName);
    }
}
