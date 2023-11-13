import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {




    @Test(dataProvider = "LoginData")
    public void loginTests(String email, String password) throws InterruptedException {
        navigateToLoginPage();
        provideEmail(email);
        providePassword(password);
        clickSubmit();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), url);




    }

    @Test
    public void loginValidEmailPassword() {
        //Steps 1
        navigateToLoginPage();
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        WebElement avatar = driver.findElement(By.cssSelector("img[class='avatar']"));
        //Expected Result
        Assert.assertTrue(avatar.isDisplayed());


    }

    @Test
    public void loginInvalidEmailValidPassword() {
        navigateToLoginPage();
        provideEmail("invalidemail@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        WebElement avatar = driver.findElement(By.cssSelector("img[class='avatar']"));
        //Expected Result
        Assert.assertTrue(avatar.isDisplayed());

    }

    @Test
    public void loginValidEmailNoPassword() throws InterruptedException  {
        navigateToLoginPage();
        provideEmail("demo@class.com");
        clickSubmit();
        WebElement avatar = driver.findElement(By.cssSelector("img[class='avatar']"));
        //Expected Result
        Assert.assertTrue(avatar.isDisplayed());
    }

    @Test(dataProvider = "excel-data")
    public void loginWithExcelData(String email, String password) throws InterruptedException {
        try {
            //Step 1:  Enter Email
            Thread.sleep(2000);
            navigateToLoginPage();
            provideEmail(email);
            //Step 2:  Enter password
            providePassword(password);
            Thread.sleep(2000);
            // WebElement txtBox = driver.findElement(By.tagName("//input[@class='gLFYf gsfi']"));
            // txtBox.sendKeys(email,password);
           // Reporter.log("KeyWord Entered is:" + email + " " + password);
            // txtBox.sendKeys(Keys.ENTER);
            //Step 3:Click on Submit Button
            clickSubmit();
            WebElement avatar = driver.findElement(By.cssSelector("img[class='avatar']"));
            //Expected Result
            Assert.assertTrue(avatar.isDisplayed());
            // Reporter.log("Successfully Logged in.");
        }catch (Exception e){
            Reporter.log("Unable to login withexcel data for an unknown reason." +e);
        }
    }
}
