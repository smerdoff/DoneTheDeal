package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import lombok.extern.log4j.Log4j2;
import models.Product;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ProductPage extends BasePage {
    private final static String PRODUCT_NAME_LOCATOR = "//div[@class = 'single_top_main']//h1";
    private final static String PRODUCT_PRICE_LOCATOR = ".rh_regular_price";
    private final static String TEXT_OF_LAST_COMMENT = "(//div[@class='commbox']//div[@class = 'comment-content'])[last()]";

    @Override
    public BasePage openPage() {
        isPageOpened();
        return this;
    }

    @Override
    public BasePage isPageOpened() {
        $(byName("subscribe")).waitUntil(Condition.visible, 30000);
        return this;
    }

    public ProductPage clickAddToFavorites() {
        $(".heartplus").click();
        $(".loading").waitUntil(Condition.disappear, 30000);
        return this;
    }

    public WishlistPage openWishList () {
        $(byText("Your wishlist")).click();
        return new WishlistPage();
    }

    public ProductPage saveButtonIsClicked() {
        String actualValueOfButton = $(".heartplus").getText();
        if (actualValueOfButton == "Save") {
            clickAddToFavorites();
        }
        Assert.assertEquals(actualValueOfButton, "Saved", "текст не совпадает");
        return this;
    }

    public ProductPage validateProductDetails(Product product) {
        log.debug("DEBUG");
        String actualName = $(By.xpath(PRODUCT_NAME_LOCATOR)).getText();
        String actualPrice = $(PRODUCT_PRICE_LOCATOR).getText();
        Assert.assertEquals(actualName, product.getProductName(), "Имена не совпадают");
        Assert.assertEquals(actualPrice, product.getPrice(), "Цены не совпадают");
        return this;
    }

    public ProductPage addAComment(String text) {
        log.debug("DEBUG");
        String random = Double.toString(Math.random());
        String sentString = text + random;
        $("[id=comment]").sendKeys(sentString);
        $("[id=submit]").click();
        String textOfComment= $(By.xpath(TEXT_OF_LAST_COMMENT)).getText();
        Assert.assertEquals(textOfComment,sentString, "Комментарии отличаются");
        return this;
    }

}


