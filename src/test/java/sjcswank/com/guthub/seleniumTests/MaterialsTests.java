package sjcswank.com.guthub.seleniumTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MaterialsTests {
	private WebDriver driver; // Selenium control driver
	
	@BeforeMethod
	public void setup() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Workspace\\selenium-java-3.141.59\\geckodriver-v0.24.0-win64\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		driver.manage().timeouts()
		.implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
		
		Util.login(driver);
		driver.findElement(By.id("matsLink")).click();
	}
	
	@Test
	public void imgActive() throws Exception {
		WebElement img = driver.findElement(By.cssSelector("#navbar > ul.nav.navbar-nav.navbar-left > li.active.nav-btn > a > img"));
		String src = img.getAttribute("src");
		
		Util.takeSnapShot(driver, Util.SCREENSHOTS_LOCATION + "\\mats-active-img.png");

		assertTrue(src.contains("images/materials-icon.png"));
	}
	
	@Test
	public void titleDisplayed() {
		String expectedTitle = Util.EXPECTED_MATS_TITLE;
		String actualTitle = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[1]/div/h2")).getText();
		
		assertEquals(expectedTitle, actualTitle);
	}
	
	@Test
	public void addMaterialButton() {
		//click add new material button
		driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[2]/div[1]/a/button")).click();
		
		String expectedTitle = Util.EXPECTED_NEW_MATS_TITLE;
		String actualTitle = driver.findElement(By.xpath("/html/body/section[2]/div/div/div/div/div/h2")).getText();
		
		assertEquals(expectedTitle, actualTitle);
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
