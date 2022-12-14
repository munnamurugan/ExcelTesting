package datadriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Example1 {
	
	public static WebDriver driver;
	
public static void datatransfer() throws IOException {
	System.setProperty("webdriver.chrome.driver", "D:\\Murugan\\Selenium driver files\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
		//driver.get("");
		List<WebElement> trows = driver.findElements(By.tagName("tr"));
		for (WebElement webElement : trows) {
			String textrow = webElement.getText();
		
			System.out.println(textrow);
		
			
	File path = new File("D:\\Murugan\\Java training class notes\\Project Class\\ticket.xlsx");
				FileInputStream fis = new FileInputStream(path); 
				XSSFWorkbook workbook = new XSSFWorkbook(fis); 
				XSSFSheet sheet = workbook.getSheetAt(0); 
	 			XSSFRow row =sheet.createRow(0); 
	 			XSSFCell cell = sheet.getRow(0).createCell(0);
				cell.setCellValue(textrow); 
				fis.close(); 
		
				FileOutputStream outputStream = new FileOutputStream(path); 
				workbook.write(outputStream); 
				outputStream.close(); 
}

}
}