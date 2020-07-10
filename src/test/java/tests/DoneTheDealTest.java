package tests;

import io.qameta.allure.Description;
import models.Product;
import org.testng.annotations.Test;
import pages.FeedPage;

public class DoneTheDealTest extends BaseTest {
    @Test
    @Description("Проверка ввода корректных кредов в окне логина. Если пользователь войдёт в приложение, то кнопка Login должна исчезнуть")
    public void correctLogin(){
        feedPage.openPage()
                .openLoginGate()
                .fillInLoginFields("eugene","password")
                .validationOfSuccessfulLogin();
    }

    @Test
    @Description("Проверка ввода некорректных кредов в окне логина. Валидация ошибки")
    public void incorrectLogin(){
        feedPage.openPage()
                .openLoginGate()
                .fillInLoginFields("eugenes","password")
                .validateIncorrectLoginWarning("Unknown username. Check again or try your email address.");
    }

    @Test
    @Description("Проверка нажатия на лайк. Сравнение кол-ва лайков до и после нажатия")
    public void thumbUp() {
        Product oil = new Product("Plant-Based Daily Superfood + Probiotics and Digestive Enzymes", "$64.56");
        feedPage
                .openPage()
                .openLoginGate()
                .fillInLoginFields("eugene", "password")
                .thumbUpDown(oil, true);
    }

    @Test
    public void thumbDown() {
        Product liquid = new Product("Liquid Calcium with Magnesium, Natural Orange Flavor", "$15.36");
        feedPage
                .openPage()
                .openLoginGate()
                .fillInLoginFields("eugene", "password")
                .thumbUpDown(liquid, false);
    }

    @Test
    @Description("Проверка добавления продукта в вишлист")
    public void addToWish() {
        Product oil = new Product("Plant-Based Daily Superfood + Probiotics and Digestive Enzymes", "$64.56");
        feedPage
                .openPage()
                .openProductPage(oil)
                .addToFavorites()
                .validateAddingToFavorites();
    }

    @Test
    @Description("Валидация деталей продукта")
    public void validateProductDetails() {
        Product oil = new Product("Plant-Based Daily Superfood + Probiotics and Digestive Enzymes", "$64.56");
        feedPage
                .openPage()
                .openProductPage(oil)
                .validateProductDetails(oil);
    }
}
