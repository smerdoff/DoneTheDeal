package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class UserProfilePage extends BasePage {

    @Override
    public BasePage openPage() {
        isPageOpened();
        return this;
    }

    @Override
    public BasePage isPageOpened() {
        $(By.xpath("(//*[@class='delete-activity confirm'])[last()]")).waitUntil(Condition.visible,30000);
        return this;
    }

    public UserProfilePage postWhatsNew(String text) {
        int countOfPostsBefore = $$(".activity-item").size();
        $(byText("Post what's new")).click();
        $(byId("whats-new")).sendKeys(text);
        $("input[value='Post Update']").click();
        $(".loading").waitUntil(Condition.disappear, 30000);
        int countOfPostsAfter = $$(".activity-item").size();
        Assert.assertEquals(countOfPostsAfter,countOfPostsBefore+1,"Пост не добавлен");
        return this;
    }

    public UserProfilePage deleteActivity() {
        int countOfItemsBefore = $$(".activity-item").size();
        $(".delete-activity").click();
        $(".loading").waitUntil(Condition.disappear, 30000);
        refresh();
        int countOfItemsAfter = $$(".activity-item").size();
        Assert.assertEquals(countOfItemsAfter, countOfItemsBefore-1,"Активити не удалена");
        return this;
    }

    public PostPage openPostPage() {
        $(".time-since").click();
        return new PostPage();
    }


}
