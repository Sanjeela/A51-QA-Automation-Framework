package pagesObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    

    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    String txtFieldEmailCssLocator = "input[type='email']";



    //Locators Using Selenium Page Factory
    @FindBy(css = "[type='email']")
    WebElement emailtxtField;
    @FindBy(css ="[type='password']")
    WebElement passwordtxtField;
    @FindBy(css = "[type='submit']")
    WebElement submitLoginButton;





    //Locators BY
    By emailField = By.cssSelector("input[type='email']");
    By passwordField = By.cssSelector("input[type='password']");
    By submitBtn = By.cssSelector("button[type='submit']");

    public void provideEmail(String email){
        findElement(emailField).sendKeys(email);
    }

    public void providePassword(String password){
        findElement(passwordField).sendKeys(password);
    }
    
    public void clickSubmit(){
        findElement(submitBtn).click();
    }
    


    //Helper Methods Using Page Factory

    public LoginPage provideEmailToLogin(String email){
        emailtxtField.sendKeys(email);
        return this;

    }

    public LoginPage providePasswordToLogin(String password){
        passwordtxtField.sendKeys(password);
        return this;
    }

    public LoginPage clickSubmitBtnToLogin(){
        submitLoginButton.click();
        return this;
    }

    public void login(){
        provideEmail("sanjeela.chitrakar@testpro.io");
        providePassword("te$t$tudent1");
        clickSubmit();
    }
}
