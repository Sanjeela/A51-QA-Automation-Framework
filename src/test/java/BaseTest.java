import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;




public class BaseTest {
    //DataProviders Start Here
    @DataProvider(name = "LoginData")
    public Object[][] getDataFromDataProvider() {
        return new Object[][]{
                {"sanjeela.chitrakar@testpro.io", "te$t$tudent1"},
                {"invalidemail@class.com", "te$t$tudent"},
                {"demo@class.com", "InvalidPassword"},
                {"", ""}
        };
    }


    @DataProvider(name = "excel-data")
    public Object[][] excelDP() throws IOException {
        Object[][] arrObj = getExcelData("./src/test/resources/test.xlsx", "Sheet1");
        return arrObj;
    }


    //DataProviders End Here


    //References Start Here



    public static String url = null;
    public static WebDriver driver;
    public WebDriverWait wait;

    public Actions actions;



    //References End Here


    @BeforeSuite
    static void setupClass() {
        //WebDriverManager.chromedriver().setup();
       // WebDriverManager.firefoxdriver().setup();
    }

    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String BaseURL) {
        driver = pickBrowser(System.getProperty("browser"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        driver.manage().window().maximize();
        navigateToLoginPage(BaseURL);

    }

    public static WebDriver pickBrowser(String browser) {
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver(edgeOptions);
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(options);
        }

    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    public void navigateToLoginPage(){
        driver.get(url);}

    public void navigateToLoginPage(String BaseURL){
        driver.get(BaseURL);}


    public void provideEmail(String email){
        //Instead of this:
        //WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        //Do this:
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
        emailField.clear();
        emailField.sendKeys(email);

    }

    public void providePassword(String password){
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        passwordField.clear();
        passwordField.sendKeys(password);

    }

    public void clickSubmit(){
        WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
        submit.click();
    }
    public String [][] getExcelData(String fileName, String sheetName){
        String [][] data = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = wb.getSheet(sheetName);
            XSSFRow row = sheet.getRow(0);

            int numOfRows = sheet.getPhysicalNumberOfRows();
            int numOfColumns = row.getLastCellNum();

            XSSFCell cell;

           data = new String[numOfRows-1][numOfColumns];

           for(int i = 1; i < numOfRows; i++){
               for (int j = 0; j < numOfColumns; j++){
                   row = sheet.getRow(i);
                   cell = row.getCell(j);
                   data[i-1][j] = cell.getStringCellValue();
               }
           }

        } catch(Exception e){
            System.out.println("Something went wrong." +e);
        }
        return data;
    }
}