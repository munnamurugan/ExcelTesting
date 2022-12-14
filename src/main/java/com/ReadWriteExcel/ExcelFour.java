package com.ReadWriteExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFour {
	public static String read(int row, int cell) throws IOException {
		File file = new File("D:\\Murugan\\Java training class notes\\Project Class\\Day 3 Project Class 24 Nov 2022\\HotelBooking.xlsx");
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet("Sheet1");
		Row row2 = sheet.getRow(row);
		Cell cell2 = row2.getCell(cell);
		String string = cell2.toString();
		List<String> li = new ArrayList<>();
		int cellType = cell2.getCellType();
		// celltype 1(String format cell type in excel)
		if (cellType == 1) {
			String value = cell2.getStringCellValue();
			li.add(value);
		}
		// celltype 0(mumeric format cell type in excel)
		else if (cellType == 0) {
			// only date cell values need to get below if condations
			if (DateUtil.isCellDateFormatted(cell2)) {
				Date dateCellValue = cell2.getDateCellValue();
				SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
				String value = sf.format(dateCellValue);
				li.add(value);
			}
			// only doubel to convert long and get value from string
			else {
				double numericCellValue = cell2.getNumericCellValue();
				long l = (long) numericCellValue;
				String value = String.valueOf(l);
				li.add(value);
			}
		}
		return string;
	}
public static void main(String[] args) throws IOException {
	ExcelFour excelfour = new ExcelFour();
	excelfour.read(0, 0);
}
}
