package org.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import com.excelreading.ReadExcelOne;

public class ReadExcelEx {
	
	public static String reusableExcel(int rowNumber, int cellNumber) throws IOException {
		File file = new File("D:\\Murugan\\Pdf data\\Employee details.xlsx");
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet("Employee details");
		Row row = sheet.getRow(rowNumber);
		Cell cell = row.getCell(cellNumber);

		int cellType = cell.getCellType();
		String value = null;
		if (cellType == 1) {
			value = cell.getStringCellValue();

		} else if (cellType == 0) {
			if (DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
				value = sd.format(dateCellValue);

			} else {
				double numericCellValue = cell.getNumericCellValue();
				long l = (long) numericCellValue;
				value = String.valueOf(l);
		}
		}
		return value;	
}
	public static void main(String[] args) throws IOException {
		ReadExcelEx readexcelex = new ReadExcelEx();
		
		String text = readexcelex.reusableExcel(1,6);
		System.out.println(text);		
}					
}
					

