package pagesObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    //Locators

    By userAvatarIcon = By.cssSelector("img.avatar");

    //Helpers

    public WebElement getUserAvatar(){
        return findElement(userAvatarIcon);
    }

    public WebElement hoverPlay()throws InterruptedException{
        //WebElement play = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='play-btn']")));
        WebElement play =driver.findElement(By.cssSelector("[data-testid='play-btn']"));
        actions.moveToElement(play).perform();
        return wait.until(ExpectedConditions.visibilityOf(play));
    }






}
