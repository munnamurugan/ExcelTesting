package com.ReadWriteExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import jxl.read.biff.BiffException;

public class AdactinHotelApp  {
	public static WebDriver driver;
	public static WebElement location;
	
	public static void loadDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver", "D:\\Murugan\\Selenium driver files\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	    driver.get(read(0,1));
	}
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
		
		if (cellType == 1) {
			String value = cell2.getStringCellValue();
			li.add(value);
		}
		
		else if (cellType == 0) {
			
			if (DateUtil.isCellDateFormatted(cell2)) {
				Date dateCellValue = cell2.getDateCellValue();
				SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
				String value = sf.format(dateCellValue);
				li.add(value);
			}
			
			else {
				double numericCellValue = cell2.getNumericCellValue();
				long l = (long) numericCellValue;
				String value = String.valueOf(l);
				li.add(value);
			}
		}
		return string;
	}
	public static void login() throws IOException {
		driver.findElement(By.name("username")).sendKeys(read(1,1));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.name("password")).sendKeys(read(2,1));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("login")).click();
	}
		
public static  void  SearchPage	() throws IOException {
    
	WebElement location = driver.findElement(By.name(read(8,1)));
	Select select1 = new Select(location);
	select1.selectByVisibleText(read(10,1));
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	WebElement hotels = driver.findElement(By.id(read(9,1)));
	Select select2 = new Select(hotels);
	select2.selectByValue("Hotel Sunshine");
	
	WebElement roomtype = driver.findElement(By.id("room_type"));
	Select select3 = new Select(roomtype);
	select3.selectByIndex(3);
	
	WebElement roomcount = driver.findElement(By.id("room_nos"));
	Select select4 = new Select(roomcount);
	select4.selectByValue("1");
	
	WebElement adult = driver.findElement(By.id("adult_room"));
	Select select5 = new Select(adult);
	select5.selectByIndex(1);
	
	WebElement child = driver.findElement(By.id("child_room"));
	Select select6 = new Select(child);
	select6.selectByIndex(0);
	
	WebElement searching = driver.findElement(By.id("Submit"));
	searching.click();
	WebElement selectbutton = driver.findElement(By.id("radiobutton_0"));
	selectbutton.click();
	
	WebElement continuebutton = driver.findElement(By.id("continue"));
	continuebutton.click();
	
}
	
public static void main(String args[]) throws BiffException, IOException{
	
	loadDriver();
	login();
	SearchPage();
}



}
