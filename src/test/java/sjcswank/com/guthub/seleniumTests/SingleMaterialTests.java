package sjcswank.com.guthub.seleniumTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SingleMaterialTests {

private WebDriver driver; // Selenium control driver
private String materialUID = UUID.randomUUID().toString();
	
	@BeforeMethod
	public void setup() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Workspace\\selenium-java-3.141.59\\geckodriver-v0.24.0-win64\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		driver.manage().timeouts()
		.implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
		
		Util.login(driver);
		driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[2]/div[1]/button")).click();
		driver.findElement(By.linkText("Material")).click();
		
		driver.findElement(By.name("itemName")).clear();
		driver.findElement(By.name("itemName")).sendKeys(materialUID);
		
		driver.findElement(By.name("qty")).clear();
		driver.findElement(By.name("qty")).sendKeys("1");
		
		driver.findElement(By.id("qtyType")).click();
		driver.findElement(By.id("String")).click();
		
		driver.findElement(By.id("location")).click();
		driver.findElement(By.id("default")).click();
		
		driver.findElement(By.name("save")).click();
	}
	
	@Test
	public void titleDisplayed() {
		String actualTitle = driver.findElement(By.xpath("//*[@id='new']/div/div[1]/div/div/h2")).getText();
		assertEquals(actualTitle, materialUID);
	}
	
	@Test
	public void imgActive() throws Exception {
		WebElement img = driver.findElement(By.cssSelector("#navbar > ul.nav.navbar-nav.navbar-left > li.active.nav-btn > a > img"));
		String src = img.getAttribute("src");
		
		Util.takeSnapShot(driver, Util.SCREENSHOTS_LOCATION + "\\single-mat-active-img.png");

		assertTrue(src.contains("images/materials-icon.png"));
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
