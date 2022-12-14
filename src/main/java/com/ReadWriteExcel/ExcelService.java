package com.ReadWriteExcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelService {
	
public static void writeToExcelSheet() {
	
		String [] rowheading = {"first Name","last Name","email","address"};
		List<User> users = createUserData();
	
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("User Details");
		
		Row headerRow = spreadsheet.createRow(0);
		for(int i=0; i < rowheading.length; i++ ) { 
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(rowheading[i]); 
		}
		for(int i =0; i < users.size(); i++) {
			Row dataRow = spreadsheet.createRow(i+1);
			dataRow.createCell(0).setCellValue(users.get(i).getfirstName());
			dataRow.createCell(1).setCellValue(users.get(i).getlastName());
			dataRow.createCell(2).setCellValue(users.get(i).getemail());
			dataRow.createCell(3).setCellValue(users.get(i).getaddress());
		}
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File("D:\\Murugan\\Pdf data\\User.xlsx"));
			workbook.write(out);
			out.close();
			}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException exe) {
			exe.printStackTrace();
		}
		System.out.println("Write to Excel sheet done Sucessfully================ ");
}
		public static List<User> createUserData(){
			
			List<User> users = new ArrayList<User>();
			users.add(new User("Murugan", "Manibalan","Tindivanam","mk28@gmail.com"));
			users.add(new User("Senthil", "Baskaran","Gingee","sbgingee@gmail.com"));
			users.add(new User("Paramasivam", "Kaliyavarthan","Pondicherry","parama@gmail.com"));
			users.add(new User("Sarathy", "Arumugam","Vilupuram","vijay@gmail.com"));
			users.add(new User("Ashok", "Siruvalai","Thiruvannamalai","asm10@gmail.com"));
			return users;
}	
}

	
	





