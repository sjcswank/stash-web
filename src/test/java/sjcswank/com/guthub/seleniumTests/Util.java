package sjcswank.com.guthub.seleniumTests;

import java.io.File;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class Util {
	public static final int WAIT_TIME = 10; // Delay time to wait for the website to launch completely
	public static final String BASE_URL = "http://localhost:8080";

	// Valid account for login
	public static final String USER_NAME = "tester1";
	public static final String PASSWD = "tester1";

	public static final String VALID = "valid";
	public static final String INVALID = "invalid";

	// Expected Login output
	public static final String EXPECT_WELCOME = "Welcome tester1!";
	public static final String EXPECTED_LOGIN_PASS_ERROR = "Incorrect Password";
	public static final String EXPECTED_LOGIN_USER_ERROR = "Incorrect Username";
	public static final String EXPECTED_LOGIN_ENTRY_ERROR = "All Values Required";
	
	
	//Expected Dashboard Titles
	public static final String EXPECT_DASH_MATERIALS_TITLE = "Recent Materials";
	public static final String EXPECT_DASH_PROJECTS_TITLE = "Recent Projects";
	public static final String EXPECT_DASH_LOCATIONS_TITLE = "Recent Locations";
	
	
	
	

	/* You can change the Path of FireFox base on your environment here */
	public static final String FIREFOX_PATH = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";

	// You can change the information of your data file here
	public static final String FILE_PATH = "src/test/java/testData/loginData.xls"; // File Path
	public static final String SHEET_NAME = "Data"; // Sheet name
	public static final String TABLE_NAME = "testData"; // Name of data table
	
	public static String[][] getDataFromExcel(String xlFilePath,
			String sheetName, String tableName) throws Exception {
		// Declare a 2 dimensions array to store all the test data read from
		// excel
		String[][] tabArray = null;

		// get the workbook file. Provide the path of excel file
		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
		// get the sheet name
		Sheet sheet = workbook.getSheet(sheetName);

		int startRow, startCol, endRow, endCol, ci, cj;

		// find cell position which contain first appear table name
		Cell tableStart = sheet.findCell(tableName);
		// Row position of FIRST appear table name
		startRow = tableStart.getRow();
		// Col position of FIRST appear table name
		startCol = tableStart.getColumn();

		// find cell position which contain last appear table name
		Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1,
				100, 64000, false);
		// Row position of LAST appear table name
		endRow = tableEnd.getRow();
		// Col position of LAST appear table name
		endCol = tableEnd.getColumn();

		tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
		ci = 0;

		// Store all data in an array
		for (int i = startRow + 1; i < endRow; i++, ci++) {
			cj = 0;
			for (int j = startCol + 1; j < endCol; j++, cj++) {
				//Get content of each cell and store to each array element.
				tabArray[ci][cj] = sheet.getCell(j, i).getContents();
			}
		}

		return (tabArray);
	}

}