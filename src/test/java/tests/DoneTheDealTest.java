package tests;

import io.qameta.allure.Description;
import models.Product;
import models.User;
import org.testng.annotations.Test;
import pages.FeedPage;

public class DoneTheDealTest extends BaseTest {
    @Test
    @Description("Проверка ввода корректных кредов в окне логина. Если пользователь войдёт в приложение, то кнопка Login должна исчезнуть")
    public void correctLogin(){
        User user = new User("eugene","password");
        feedPage.openPage()
                .openLoginGate()
                .fillInLoginFields(user)
                .validationOfSuccessfulLogin();
    }

    @Test
    @Description("Проверка ввода некорректных кредов в окне логина. Валидация ошибки")
    public void incorrectLogin(){
        User user = new User("eugenes","password");
        feedPage.openPage()
                .openLoginGate()
                .fillInLoginFields(user)
                .validateIncorrectLoginWarning("Unknown username. Check again or try your email address.");
    }

    @Test
    @Description("Проверка нажатия на лайк. Сравнение кол-ва лайков до и после нажатия")
    public void thumbUp() {
        User user = new User("eugene","password");
        Product oil = new Product("Plant-Based Daily Superfood + Probiotics and Digestive Enzymes", "$64.56");
        feedPage
                .openPage()
                .openLoginGate()
                .fillInLoginFields(user)
                .thumbUpDown(oil, true);
    }

    @Test
    @Description("Проверка нажатия на дизлайк. Сравнение кол-ва лайков до и после нажатия")
    public void thumbDown() {
        User user = new User("eugene","password");
        Product liquid = new Product("Liquid Calcium with Magnesium, Natural Orange Flavor", "$15.36");
        feedPage
                .openPage()
                .openLoginGate()
                .fillInLoginFields(user)
                .thumbUpDown(liquid, false);
    }

    @Test
    @Description("Проверка добавления продукта в вишлист")
    public void clickTheAddToWish() {
        Product liquid = new Product("Liquid Calcium with Magnesium, Natural Orange Flavor", "$15.36");
        feedPage
                .openPage()
                .openProductPage(liquid)
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
                .waitUntilProductIsSaved()
                .validateProductDetails(oil);
    }

    @Test
    @Description("Проверка добавления продукта в вишлист")
    public void wishlistValidation() {
        Product pectin = new Product("Apple Pectin, 700 mg", "$10.18");
        feedPage
                .openPage()
                .openProductPage(pectin)
                .addToFavorites()
                .waitUntilProductIsSaved()
                .openWishList()
                .validateProduct(pectin);
    }

    @Test
    @Description("Проверка удаления продукта из вишлиста")
    public void removeFromWishList() {
        Product conditioner = new Product("Honeysuckle Rose Conditioner, Moisture Intensive, Dry", "$10.40");
        feedPage
                .openPage()
                .openProductPage(conditioner)
                .addToFavorites()
                .waitUntilProductIsSaved()
                .openWishList()
                .removeFromWishlist(conditioner);
    }

    @Test(description = "Валидация изменения кол-ва постов. Проверка текста в верхнем посте" )
    @Description("Создание и отправка нового поста в UserProfile")
    public void postActivity() {
        User user = new User("eugene2","password");
        feedPage
                .openPage()
                .openLoginGate()
                .fillInLoginFields(user)
                .openDropDown()
                .openUserProfile()
                .postWhatsNew("Text for Test23.");
    }

    @Test(description = "Валидация изменения кол-ва постов" )
    @Description("Удаление поста")
    public void removeActivity() {
        User user = new User("eugene2","password");
        feedPage
                .openPage()
                .openLoginGate()
                .fillInLoginFields(user)
                .openDropDown()
                .openUserProfile()
                .deleteActivity();
    }


}
