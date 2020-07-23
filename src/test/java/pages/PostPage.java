package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PostPage extends BasePage {

    @Override
    public BasePage openPage() {
        isPageOpened();
        return this;
    }

    @Override
    public BasePage isPageOpened() {
        $(byText("Delete")).waitUntil(Condition.visible,30000);
        return this;
    }

    public PostPage addAComment(String text) {
        $(".acomment-reply").click();
        $("textarea").sendKeys(text);
        $(byName("ac_form_submit")).click();
        $(".loading").waitUntil(Condition.disappear, 30000);
        refresh();
        String textOfLastComment = $(By.xpath("(//div[@class='acomment-content'])[last()]")).getText();
        Assert.assertEquals(textOfLastComment, text,"Коммент не добавлен");
        return this;
    }

    public PostPage deleteAComment() {
        int countOfComments = $$(".acomment-meta").size();
        $(byXpath("(//div[@class='acomment-options']//a[text()='Delete'])[last()]")).click();
        $(".loading").waitUntil(Condition.disappears, 30000);
        int countOfCommentsAfterDeleting = $$(".acomment-meta").size();
        Assert.assertEquals(countOfCommentsAfterDeleting,countOfComments-1,"Коммент не удален");
        return this;
    }

    public PostPage clickFavorite() {
        if($(".fav").isDisplayed()==false){
            $(".unfav").click();
        }
        $(".fav").click();
        $(".loading").waitUntil(Condition.disappears, 30000);
        String actualValueOfButton = $(".unfav").getText();
        Assert.assertEquals(actualValueOfButton, "Remove Favorite", "Пост не добавлен в фавориты или кнопка не изменена");
        return this;
    }


}
