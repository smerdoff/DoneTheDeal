package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WishlistPage extends BasePage {

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
}
