package sjcswank.com.guthub.seleniumTests;

import static org.testng.Assert.assertEquals;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewProjectTests {
	private WebDriver driver; // Selenium control driver
	
	@BeforeMethod
	public void setup() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Workspace\\selenium-java-3.141.59\\geckodriver-v0.24.0-win64\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		driver.manage().timeouts()
		.implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
		
		Util.login(driver);
		driver.get(Util.BASE_URL +"/" + Util.USER_NAME + "/newproject/");
	}
	
	@DataProvider(name = "newProjectTest")
	public Object[][] newMaterialTestData() throws Exception {
		return Util.getDataFromExcel(Util.NEW_PROJECT_FILE_PATH, Util.SHEET_NAME,
				Util.TABLE_NAME);
	} 
	
	@Test(dataProvider="newProjectTest")
	public void newProject(String name, String quantity, String location) {

		driver.findElement(By.name("itemName")).clear();
		driver.findElement(By.name("itemName")).sendKeys(name);
		
		driver.findElement(By.name("qty")).clear();
		driver.findElement(By.name("qty")).sendKeys(quantity);
		
		if(!(location==null) & !(location=="")){
			driver.findElement(By.id("location")).click();
			driver.findElement(By.id(location)).click();
		}
		
		
		driver.findElement(By.name("save")).click();
		
		try {
			String actualName = driver.findElement(By.xpath("/html/body/section[2]/div/div[1]/div/h2")).getText();
			assertEquals(name, actualName);
		}
		catch(Exception e) {
			String actualError = driver.findElement(By.id("error")).getText();
			
			if(name=="" || name==null){
				assertEquals(Util.EXPECTED_NEW_PROJ_NAME_ERROR, actualError);
			}
			else if(quantity=="0" || quantity=="" || quantity==null){
				assertEquals(Util.EXPECTED_NEW_PROJ_QTY_ERROR, actualError);
			}
		}
	}
	
	@Test
	public void cancelTest(){
		driver.findElement(By.name("cancel")).click();
		
		String expectedTitle = Util.EXPECTED_PROJ_TITLE;
		String actualTitle = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[1]/div/h2")).getText();
		
		assertEquals(expectedTitle, actualTitle);
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
