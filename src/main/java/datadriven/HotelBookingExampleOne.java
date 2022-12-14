package datadriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class HotelBookingExampleOne {
	public static WebDriver driver;

	public static void loadDriver() throws IOException {

		System.setProperty("webdriver.chrome.driver", "D:\\Murugan\\Selenium driver files\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(excel(0, 1));
	}

	public static String excel(int rowNo, int cellNo) throws IOException {

		File file = new File(
				"D:\\Murugan\\Java training class notes\\Project Class\\Day 3 Project Class 24 Nov 2022\\HotelBooking.xlsx");
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet("Sheet1");
		Row rows = sheet.getRow(rowNo);
		Cell cells = rows.getCell(cellNo);

		int cellType = cells.getCellType();
		String value = null;
		if (cellType == 1) {
			value = cells.getStringCellValue();

		} else if (cellType == 0) {
			if (DateUtil.isCellDateFormatted(cells)) {
				Date dateCellValue = cells.getDateCellValue();
				SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
				value = dateformat.format(dateCellValue);

			} else {
				double numericCellValue = cells.getNumericCellValue();
				long l = (long) numericCellValue;
				value = String.valueOf(l);

			}
		}
		return value;

	}

	public static void loginPage() throws IOException {

		driver.findElement(By.name("username")).sendKeys(excel(1, 1));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.name("password")).sendKeys(excel(2, 1));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("login")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement location = driver.findElement(By.id("location"));
		Select select1 = new Select(location);
		select1.selectByVisibleText("London");

		WebElement hotels = driver.findElement(By.id("hotels"));
		Select select2 = new Select(hotels);
		select2.selectByVisibleText("Hotel Sunshine");

		WebElement roomtype = driver.findElement(By.id("room_type"));
		Select select3 = new Select(roomtype);
		select3.selectByVisibleText("Deluxe");

		WebElement roomcount = driver.findElement(By.id("room_nos"));
		Select select4 = new Select(roomcount);
		select4.selectByVisibleText("1 - One");

		WebElement adult = driver.findElement(By.id("adult_room"));
		Select select5 = new Select(adult);
		select5.selectByVisibleText("1 - One");

		WebElement child = driver.findElement(By.id("child_room"));
		Select select6 = new Select(child);
		select6.selectByVisibleText("0 - None");

		WebElement searching = driver.findElement(By.id("Submit"));
		searching.click();

		WebElement selectbutton = driver.findElement(By.id("radiobutton_0"));
		selectbutton.click();

		WebElement continuebutton = driver.findElement(By.id("continue"));
		continuebutton.click();

	}

	public static void bookinPage() throws IOException {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.id("first_name")).sendKeys(excel(4, 1));

		driver.findElement(By.id("last_name")).sendKeys(excel(5, 1));

		driver.findElement(By.id("address")).sendKeys(excel(6, 1));

		driver.findElement(By.id("cc_num")).sendKeys(excel(7, 1));

		WebElement CreditCardType = driver.findElement(By.id("cc_type"));
		Select select7 = new Select(CreditCardType);
		select7.selectByVisibleText("VISA");

		WebElement selectmonth = driver.findElement(By.id("cc_exp_month"));
		Select select8 = new Select(selectmonth);
		select8.selectByVisibleText("October");

		WebElement selectyear = driver.findElement(By.id("cc_exp_year"));
		Select select9 = new Select(selectyear);
		select9.selectByVisibleText("2022");

		driver.findElement(By.id("cc_cvv")).sendKeys(excel(8, 1));

		driver.findElement(By.id("book_now")).click();

		driver.findElement(By.id("my_itinerary")).click();
	}
	public static void printPageFromWeb() throws IOException {
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		/*List<WebElement> webtext = driver.findElements(By.tagName("tr"));
		for (WebElement webElement : webtext) {
			String data = webElement.getText();
			System.out.println(data);*/
		String data = driver.findElement(By.xpath("//*[@id=\"booked_form\"]/table/tbody/tr[2]/td/table/tbody/tr[1]/td[2]")).getText();
		System.out.println(data);
			File path = new File("D:\\Murugan\\Java training class notes\\Project Class\\Data driven Testing.xlsx");
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);

			XSSFRow row = sheet.createRow(0);
			XSSFCell cell = sheet.getRow(0).createCell(0);

			cell.setCellValue(data);

			fis.close();

			FileOutputStream outputStream = new FileOutputStream(path);
			workbook.write(outputStream);
			outputStream.close();
		}
//	}

	public static void main(String[] args) throws IOException {
		loadDriver();
		loginPage();
		bookinPage();
		printPageFromWeb();

	}

}
