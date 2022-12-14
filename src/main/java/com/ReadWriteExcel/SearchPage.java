package com.ReadWriteExcel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SearchPage  {
	
	public void searching () {
	System.setProperty("webdriver.chrome.driver", "D:\\Murugan\\Selenium driver files\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	

/* driver.get("https://adactinhotelapp.com/");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.findElement(By.name("username")).sendKeys("chennai600100");
	driver.findElement(By.name("password")).sendKeys("hotel1234");
	driver.findElement(By.name("login")).click();*/
	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	WebElement location = driver.findElement(By.id("location"));
	Select select1 = new Select(location);
	select1.selectByIndex(5);
	
	WebElement hotels = driver.findElement(By.id("hotels"));
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
	
}
