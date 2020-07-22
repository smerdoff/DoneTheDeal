package pages;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import models.Product;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;


public class WishlistPage extends BasePage {
    private static final String REMOVE_BUTTON = "//*[text() = ' %s ']//../..//*[@class='alreadywish heartplus']";


    @Override
    public BasePage openPage() {
        open("https://donethedeal.com/your-favorite-posts/");
        isPageOpened();
        return this;
    }

    @Override
    public BasePage isPageOpened() {
        $(byName("subscribe")).waitUntil(Condition.visible, 30000);
        return this;
    }

    public WishlistPage validateProductIsAdded(Product product) {
        Product expectedProduct = Product.builder()
                .productName($(By.xpath("//div[@class='rowdisplay']//div[3]//a")).getText())
                .price($(".rh_regular_price").getText())
                .build();
        Assert.assertEquals(expectedProduct, product, "Детали продукта не совпадают");
        By removeButton = By.xpath(String.format(REMOVE_BUTTON, product.getProductName()));
        $(removeButton).click();
        $(".loading").waitUntil(Condition.disappear, 30000);
        return this;
    }

    public WishlistPage removeFromWishlist(Product product) {
        int countOfProductsBefore= $$(".rowdisplay").size();
        By removeButton = By.xpath(String.format(REMOVE_BUTTON, product.getProductName()));
        $(removeButton).click();
        $(".loading").waitUntil(Condition.disappear, 30000);
        refresh();
        int countOfProductsAfter= $$(".rowdisplay").size();
        Assert.assertEquals(countOfProductsAfter,countOfProductsBefore-1,"Продукт не был удалён");
        return this;
    }
}
