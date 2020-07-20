package pages;

import com.codeborne.selenide.Condition;
import models.Product;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage extends BasePage {
    private final static String PRODUCT_NAME_LOCATOR = "//div[@class = 'single_top_main']//h1";
    private final static String PRODUCT_PRICE_LOCATOR = ".rh_regular_price";

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

    public ProductPage addToFavorites() {
        $(".heartplus").click();
        return this;
    }

    public ProductPage validateAddingToFavorites() {
        $(".loading").waitUntil(Condition.disappear, 30000);
        String actualValueOfButton = $(".heartplus").getText();
        Assert.assertEquals("Saved", actualValueOfButton, "текст не совпадает");
        return this;
    }

    public ProductPage validateProductDetails(Product product) {
        String actualName = $(By.xpath(PRODUCT_NAME_LOCATOR)).getText();
        String actualPrice = $(PRODUCT_PRICE_LOCATOR).getText();
        Assert.assertEquals(actualName, product.getProductName(), "Имена не совпадают");
        Assert.assertEquals(actualPrice, product.getPrice(), "Цены не совпадают");
        return this;
    }

}


