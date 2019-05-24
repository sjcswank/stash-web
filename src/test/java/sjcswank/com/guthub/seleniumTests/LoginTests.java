package sjcswank.com.guthub.seleniumTests;

import static org.junit.Assert.assertEquals;
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

public class LoginTests {
	
	private WebDriver driver; // Selenium control driver
	private String baseUrl; // baseUrl of website
	
	@DataProvider(name = "LoginTest")
	public Object[][] testData() throws Exception {
		return Util.getDataFromExcel(Util.FILE_PATH, Util.SHEET_NAME,
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
	}
	
	@Test(dataProvider = "LoginTest")
	public void testCase01(String username, String password){
		
		String actualWelcome;
		String actualActive;
		
		/*
		 * select username input, clear
		 * input username
		 * select password input, clear
		 * input password
		 * click button
		 * try: check invalid
		 * catch: check valid
		 */		
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.className("btn")).click();
		
		try{
			actualWelcome = driver.findElement(By.className("welcome")).getText();
			assertEquals(Util.EXPECT_WELCOME, actualWelcome);
		}
		catch(Exception e){
			actualActive = driver.findElement(By.id("login-error")).getText();
			if(password.equals("") || username.equals("")) {
				assertEquals(Util.EXPECTED_LOGIN_ENTRY_ERROR, actualActive);
			}
			else if(username.equals("invalid")) {
				assertEquals(Util.EXPECTED_LOGIN_USER_ERROR, actualActive);
			}
			else if(password.equals("invalid")) {
				assertEquals(Util.EXPECTED_LOGIN_PASS_ERROR, actualActive);
			}
			
		}
		
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
