package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.FeedPage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


@Listeners(TestListener.class)
public class BaseTest {
    FeedPage feedPage;

    @BeforeMethod
    public void setupBrowser() {
        Configuration.headless =false;
        Configuration.startMaximized=true;
        Configuration.timeout= 10000;
        Configuration.clickViaJs = false;
        Configuration.browser = "chrome";
        feedPage = new FeedPage();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() { getWebDriver().quit();}

}
