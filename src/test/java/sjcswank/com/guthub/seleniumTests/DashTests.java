package sjcswank.com.guthub.seleniumTests;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import sjcswank.com.guthub.seleniumTests.Util;

public class DashTests {
	
	private WebDriver driver; // Selenium control driver
	private String baseUrl; // baseUrl of website
	
	@DataProvider(name = "RecentTitlesTest")
	public Object[][] recentTitlesTestData() throws Exception {
		return Util.getDataFromExcel(Util.RECENT_TITLES_FILE_PATH, Util.SHEET_NAME,
				Util.TABLE_NAME);
	} 
	
	@DataProvider(name = "NewItemDropdownTest")
	public Object[][] NewItemDropdownTestData() throws Exception {
		return Util.getDataFromExcel(Util.NEW_ITEM_DROPDOWN_FILE_PATH, Util.SHEET_NAME,
				Util.TABLE_NAME);
	} 
	
	@BeforeMethod
	public void setup() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Workspace\\selenium-java-3.141.59\\geckodriver-v0.24.0-win64\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		baseUrl = Util.BASE_URL;
		driver.manage().timeouts()
		.implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
		driver.get(baseUrl + "/login/");
		
		//login
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(Util.USER_NAME);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(Util.PASSWD);
		driver.findElement(By.className("btn")).click();
	}
	
	@Test
	public void ImgActive() throws Exception {
		WebElement img = driver.findElement(By.cssSelector("#navbar > ul.nav.navbar-nav.navbar-left > li.active.nav-btn > a > img"));
		String src = img.getAttribute("src");
		
		Util.takeSnapShot(driver, Util.SCREENSHOTS_LOCATION + "\\dash-active-img.png");

		assertTrue(src.contains("images/home-icon.png"));
	}
	
	@Test(dataProvider="RecentTitlesTest")
	public void RecentTitles(String selector, String expectedTitle) throws Exception {
		String actualTitle = driver.findElement(By.cssSelector(".row:nth-child(" + selector + ") > .col-md-12 h2")).getText();
		Util.takeSnapShot(driver, Util.SCREENSHOTS_LOCATION + "\\dash-recent-" + expectedTitle + ".png");
		assertEquals(expectedTitle, actualTitle);
		
	}
	
	@Test(dataProvider="NewItemDropdownTest")
	public void NewItemDropdown(String type, String expectedTitle) throws Exception {
		driver.findElement(By.xpath("//button[contains(.,'Add New ')]")).click();
		driver.findElement(By.linkText(type)).click();
		String actualTitle = driver.findElement(By.cssSelector("h2")).getText();
		
		Util.takeSnapShot(driver, Util.SCREENSHOTS_LOCATION + "\\dash-new-item-" + type + ".png");
		
		assertEquals(expectedTitle, actualTitle);
		
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
