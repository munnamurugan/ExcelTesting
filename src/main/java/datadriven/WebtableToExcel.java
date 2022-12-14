package datadriven;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebtableToExcel {
	
	
	public static void main (String []args) throws IOException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Murugan\\Selenium driver files\\chromedriver.exe");
		WebDriver  driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://en.wikipedia.org/wiki/List_of_countries_and_dependencies_by_population");
		String path = "C:\\Users\\31mun\\eclipse-workspace\\Maven_Project_Demo\\datafiles\\population details.xlsx";
		ExcelUtility excelutil = new ExcelUtility(path);
		excelutil.setCellData("Sheet1", 0, 0, "country");
		excelutil.setCellData("Sheet1", 0, 0, "population");
		excelutil.setCellData("Sheet1", 0, 0, "% of world");
		excelutil.setCellData("Sheet1", 0, 0, "date");
		excelutil.setCellData("Sheet1", 0, 0, "source");
		WebElement table = driver.findElement(By.xpath("//*[@class='wikitable sortable jquery-tablesorter']/tbody"));
		int rows = table.findElements(By.xpath("tr")).size();
		for(int r=1; r<=rows; r++) {
			//(//*[@class='wikitable sortable jquery-tablesorter']/tbody/tr[2]/td[5])
			String country = table.findElement(By.xpath("tr["+r+"]/td[1]")).getText();
			String population = table.findElement(By.xpath("tr["+r+"]/td[2]")).getText();
			String percentageworld = table.findElement(By.xpath("tr["+r+"]/td[3]")).getText();
			String date = table.findElement(By.xpath("tr["+r+"]/td[4]")).getText();
			String source = table.findElement(By.xpath("tr["+r+"]/td[5]")).getText();
			System.out.println(country+population+percentageworld+date+source  );
		
			excelutil.setCellData("Sheet1", r, 0, country);
			excelutil.setCellData("Sheet1", r, 1, population);
			excelutil.setCellData("Sheet1", r, 2, percentageworld);
			excelutil.setCellData("Sheet1", r, 3, date);
			excelutil.setCellData("Sheet1", r, 4, source);
		
		
		}

		System.out.println("Web scrapping is done sucessfully");
		
		driver.close();
	}

}
