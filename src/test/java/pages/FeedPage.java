package pages;

import com.codeborne.selenide.Condition;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class FeedPage extends BasePage {

    private static final String LOGIN_INPUT_LOCATOR = "//div[@class='pm-content']//input[@name='rehub_user_login']";
    private static final String PASSWORD_INPUT_LOCATOR = "//div[@class='pm-content']//input[@id='rehub_user_pass']";
    private static final String SUBMIT_BUTTON_LOCATOR = "//div[@class='pm-content']//button";
    private static final String WARNING_LOCATOR = "//div[@class='pm-content']//i//..";


    @Override
    public FeedPage openPage() {
        open("http://donethedeal.com/");
        isPageOpened();
        return this;
    }

    @Override
    public BasePage isPageOpened() {
        $(byName("subscribe")).waitUntil(Condition.visible, 30000);
        return this;
    }

    public FeedPage openLoginGate() {
        $("[data-type=login]").click();
        return this;
    }

    public FeedPage fillInLoginFields(String login, String password) {
        $(By.xpath(LOGIN_INPUT_LOCATOR)).setValue(login);
        $(By.xpath(PASSWORD_INPUT_LOCATOR)).setValue(password);
        $(By.xpath(SUBMIT_BUTTON_LOCATOR)).click();
        return this;
    }

    public FeedPage validationOfSuccessfulLogin() {
        FeedPage feedPage = new FeedPage();
        feedPage.isPageOpened();
        Assert.assertEquals(0, $$("[data-type=login]").size(), "Кнопка логина не исчезла после входа в приложение");
        return this;
    }

    public FeedPage validateIncorrectLoginWarning () {
        String expectedWarning = $(By.xpath(WARNING_LOCATOR)).getText();
        Assert.assertEquals("Unknown username. Check again or try your email address.", expectedWarning, "Ворнинги не совпадают");
        return this;
    }

    public WishlistPage openWishList () {
        $(byText("Your wishlist")).click();
        return new WishlistPage();
    }


}



