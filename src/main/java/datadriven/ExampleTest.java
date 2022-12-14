package datadriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExampleTest {
	
	public static WebDriver driver;

	public static void webPageData() throws IOException {

		System.setProperty("webdriver.chrome.driver", "D:\\Murugan\\Selenium driver files\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("https://adactinhotelapp.com/");

		String webtext = driver.findElement(By.xpath("//*[@class='build_title']")).getText();
		System.out.println(webtext);

		File path = new File("D:\\Murugan\\Java training class notes\\Project Class\\Data driven Testing.xlsx");
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(2);

		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = sheet.getRow(0).createCell(0);

		cell.setCellValue(webtext);

		fis.close();

		FileOutputStream outputStream = new FileOutputStream(path);
		workbook.write(outputStream);
		outputStream.close();
	}

	public static void main(String[] args) throws IOException {

		webPageData();

	}


}
