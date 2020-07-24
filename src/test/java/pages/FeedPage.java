package pages;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import models.Product;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;
@Log4j2
public class FeedPage extends BasePage {

    private static final String LOGIN_INPUT_LOCATOR = "//div[@class='pm-content']//input[@name='rehub_user_login']";
    private static final String PASSWORD_INPUT_LOCATOR = "//div[@class='pm-content']//input[@id='rehub_user_pass']";
    private static final String SUBMIT_BUTTON_LOCATOR = "//div[@class='pm-content']//button";
    private static final String WARNING_LOCATOR = "//div[@class='pm-content']//i//..";
    private static final String PRODUCT_LOCATOR = "//div[@data-template = 'compact_grid']//*[text()='%s']";
    private static final String LIKE_COUNT_LOCATOR = "//div[@data-template = 'compact_grid']//*[text()='%s']//..//..//..//../..//span[@class='thumbscount']";
    private static final String THUMB_UP_BUTTON_LOCATOR = ("//div[@data-template = 'compact_grid']//*[text()='%s']//..//..//..//../..//div[@class='btn_act_for_grid']//span[@class = 'table_cell_thumbs']//span[2]");
    private static final String THUMB_DOWN_BUTTON_LOCATOR = ("//div[@data-template = 'compact_grid']//*[text()='%s']//..//..//..//../..//div[@class='btn_act_for_grid']//span[@class = 'table_cell_thumbs']//span[1]");
    private static final String MOST_HOT_DEALS_RATE_LOCATOR = "(//div[@data-template='simplepostlist']//*[@class='temperatur'])[%s]";
    private static final String MOST_HOT_PRODUCTS_RATE_LOCATOR = "(//span[@class='thumbscount'])[%s]";


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

    public FeedPage fillInLoginFields(User user) {
        log.debug("DEBUG");
        $(By.xpath(LOGIN_INPUT_LOCATOR)).setValue(user.getUsername());
        $(By.xpath(PASSWORD_INPUT_LOCATOR)).setValue(user.getPassword());
        $(By.xpath(SUBMIT_BUTTON_LOCATOR)).click();
        return this;
    }

    public FeedPage validationOfSuccessfulLogin() {
        FeedPage feedPage = new FeedPage();
        feedPage.isPageOpened();
        assertEquals(0, $$("[data-type=login]").size(), "Кнопка логина не исчезла после входа в приложение");
        return this;
    }

    public FeedPage openDropDown() {
        log.debug("DEBUG");
        $(".user-ava-intop").click();
        return this;
    }

    public UserProfilePage openUserProfile() {
        log.debug("DEBUG");
        $(byText("Edit Profile")).click();
        return new UserProfilePage();
    }

    public FeedPage validateIncorrectLoginWarning (String expectedWarning) {
        log.debug("DEBUG");
        String actualWarning = $(By.xpath(WARNING_LOCATOR)).getText();
        assertEquals(expectedWarning, actualWarning, "Ворнинги не совпадают");
        return this;
    }

    public FeedPage clickMostHot() {
        $(byText("Most Hot")).click();
        $(byXpath("//*[@class='re_filtersort_btn resort_1 active']")).waitUntil(Condition.appears,10000);
        return this;
    }

    public FeedPage validateBestRatedProducts() {
        log.debug("DEBUG");
        for(int i = 1; i<=6; i++){
            By productLikeLocator= By.xpath(String.format(MOST_HOT_PRODUCTS_RATE_LOCATOR, i));
            String productLikesCount= $(productLikeLocator).getText();
            By dealsLikesLocator = By.xpath(String.format(MOST_HOT_DEALS_RATE_LOCATOR, i));
            String dealLikeCount = $(dealsLikesLocator).getText();
            Assert.assertEquals(productLikesCount, dealLikeCount, "Значения лайков между самими рейтинговыми продуктами и панелью MOST HOT DEALS не совпадают ");
        }
        return this;
    }

    public ProductPage openProductPage(Product product) {
        log.debug("DEBUG");
        By productPath = By.xpath(String.format(PRODUCT_LOCATOR, product.getProductName()));
        $(productPath).click();
        return new ProductPage();
    }

    public FeedPage thumbUpDown (Product product, boolean thumb) {
        isPageOpened();
        log.debug("DEBUG");
        By likeCounter = By.xpath(String.format(LIKE_COUNT_LOCATOR,product.getProductName()));
        int expectedCount = Integer.parseInt($(likeCounter).getText());
        $(likeCounter).hover();
        if (thumb=true) {
            By thumbUpXpath = By.xpath(String.format(THUMB_UP_BUTTON_LOCATOR,product.getProductName()));
            $(thumbUpXpath).doubleClick();
            expectedCount+=1;
        } else {
            By thumbDownXpath = By.xpath(String.format(THUMB_DOWN_BUTTON_LOCATOR, product.getProductName()));
            $(thumbDownXpath).click();
            expectedCount-=1;
        }
        $(".loading").waitUntil(Condition.disappears,6000);
        $(byText("Most Hot")).hover();
        int actualCount = Integer.parseInt($(likeCounter).getText());
        assertEquals(actualCount,expectedCount, "Кол-во лайков не совпадает");
        return this;
    }


}



