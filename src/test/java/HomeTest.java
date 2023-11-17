import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagesObjectModel.*;

import java.util.List;

public class HomeTest extends BaseTest{

    String newPlaylistName = "Sample Edited Playlist";

    //Test Starts Here

   /* @Test
    public void playSongWithContextClick() throws InterruptedException {

    //Login
        provideEmail("sanjeela.chitrakar@testpro.io");
        providePassword("te$t$tudent1");
        clickSubmit();

    //Choose All Songs List
        chooseAllSongsList();

    //Right(Context) Click
        contextClickFirstSong();

    //Choose Play
        choosePlayOption();

    //Assertion
        Assert.assertTrue(isSongPlaying());

    }*/

    @Test
    public void playSong() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AllSongsPage songsPage =new AllSongsPage(driver);

        //Login
        loginPage.provideEmail("sanjeela.chitrakar@testpro.io");
        loginPage.providePassword("te$t$tudent1");
        loginPage.clickSubmit();

        homePage.chooseAllSongsList();

        songsPage.contextClickFirstSong();
        songsPage.choosePlayOption();

        //Assertion
        Assert.assertTrue(songsPage.isSongPlaying());

    }

   /* @Test
    public void hoverOverPlayButton() throws InterruptedException {
        //Login
        provideEmail("sanjeela.chitrakar@testpro.io");
        providePassword("te$t$tudent1");
        clickSubmit();
        //Thread.sleep(2000);
        //Hover

        //Assertions
        Assert.assertTrue(hoverPlay().isDisplayed());
    }*/

    @Test
    public void hoverOverPlayBtn() throws InterruptedException {
        //Login
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.provideEmail("sanjeela.chitrakar@testpro.io");
        loginPage.providePassword("te$t$tudent1");
        loginPage.clickSubmit();

        Thread.sleep(2000);

        //Assertions
        Assert.assertTrue(homePage.hoverPlay().isDisplayed());
    }

    @Test
    public void countSongsInPlaylist() throws InterruptedException {
        //Login
        provideEmail("sanjeela.chitrakar@testpro.io");
        providePassword("te$t$tudent1");
        clickSubmit();
        Thread.sleep(2000);

        //choose Playlist ny Name
        choosePlaylistByName("DEMO PLAYLIST");
        Thread.sleep(2000);

        //Display All Songs
        displayAllSongs();
        Thread.sleep(2000);

        //Assertion
        Assert.assertTrue(getPlaylistDetails().contains(String.valueOf(countSongs())));
    }
    @Test
    public void renamePlaylist() throws InterruptedException {
        String updatePlaylistMsg = "Updated playlist \"Sample Edited Playlist.\"";
        //Login
        provideEmail("sanjeela.chitrakar@testpro.io");
        providePassword("te$t$tudent1");
        clickSubmit();
        Thread.sleep(2000);

        //Double Click
        doubleClickPlaylist();
        Thread.sleep(2000);

        //Enter New Name Playlist
        enterNewPlaylistName();
        Thread.sleep(2000);

        //Assert
        Assert.assertEquals(getRenamePlaylistSuccessMsg(), updatePlaylistMsg);
    }
    //Test Ends Here


    //Helper Methods Start Here

    public void doubleClickPlaylist(){
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        actions.doubleClick(playlistElement).perform();
    }

    public void enterNewPlaylistName(){
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL,"A",Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public String getRenamePlaylistSuccessMsg(){
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notification.getText();
    }

    public void choosePlaylistByName(String playlistName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'"+playlistName+"')]"))).click();
    }

    public int countSongs(){
        return driver.findElements(By.cssSelector("sections#playlistWrapper td.title")).size();
    }

    public String getPlaylistDetails(){
        return driver.findElement(By.cssSelector("span.meta.text-secondary span.meta")).getText();
    }

    public void displayAllSongs(){
        List<WebElement> songlist = driver.findElements(By.cssSelector("section#playlistWrapper td.title"));
        System.out.println("Number of Songs found: "+countSongs());
        for (WebElement e: songlist){
            System.out.println(e.getText());
        }
    }







}
