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
	
//	@DataProvider(name = "LoginTest")
//	public Object[][] testData() throws Exception {
//		return Util.getDataFromExcel(Util.FILE_PATH, Util.SHEET_NAME,
//				Util.TABLE_NAME);
//	} 
	
	
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
	public void ImgActive(){
		//*[@id="navbar"]/ul[1]/li[1]/a/img
		//#navbar > ul.nav.navbar-nav.navbar-left > li.active.nav-btn > a > img
		
		WebElement img = driver.findElement(By.cssSelector("#navbar > ul.nav.navbar-nav.navbar-left > li.active.nav-btn > a > img"));
		String src = img.getAttribute("src");

		assertTrue(src.contains("images/home-icon.png"));
	}
	
	@Test
	public void RecentMaterialsTitle() {
		String actualMaterialsTitle = driver.findElement(By.cssSelector(".row:nth-child(1) > .col-md-12 h2")).getText();
		assertEquals(Util.EXPECT_DASH_MATERIALS_TITLE, actualMaterialsTitle);
		
	}
	
	@Test
	public void RecentProjectsTitle() {
		String actualProjectsTitle = driver.findElement(By.cssSelector(".row:nth-child(2) > .col-md-12 h2")).getText();
		assertEquals(Util.EXPECT_DASH_PROJECTS_TITLE, actualProjectsTitle);
		
	}
	
	@Test
	public void RecentLocationsTitle() {
		String actualLocationsTitle = driver.findElement(By.cssSelector(".row:nth-child(3) > .col-md-12 h2")).getText();
		assertEquals(Util.EXPECT_DASH_LOCATIONS_TITLE, actualLocationsTitle);
		
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
