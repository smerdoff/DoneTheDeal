package pages;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
@Log4j2
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
        log.debug("DEBUG");
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
        log.debug("DEBUG");
        log.debug("DEBUG");
        int countOfComments = $$(".acomment-meta").size();
        $(byXpath("(//div[@class='acomment-options']//a[text()='Delete'])[last()]")).click();
        $(".loading").waitUntil(Condition.disappears, 30000);
        int countOfCommentsAfterDeleting = $$(".acomment-meta").size();
        Assert.assertEquals(countOfCommentsAfterDeleting,countOfComments-1,"Коммент не удален");
        return this;
    }

    public PostPage clickFavorite() {
        log.debug("DEBUG");
        if($(".fav").isDisplayed()==false){
            $(".unfav").click();
            $(".loading").waitUntil(Condition.disappears, 30000);
        }
        $(".fav").click();
        $(".loading").waitUntil(Condition.disappears, 30000);
        refresh();
        String actualValueOfButton = $(".unfav").getText();
        Assert.assertEquals(actualValueOfButton, "Remove Favorite", "Пост не добавлен в фавориты или кнопка не изменена");
        return this;
    }


}
