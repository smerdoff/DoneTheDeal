package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import models.Product;
import models.User;
import org.testng.annotations.Test;


public class DoneTheDealTest extends BaseTest {
    @Test()
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

    @Test(enabled = false)
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

    @Test(enabled = false)
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

    @Test(description = "Проверка лайков между самыми рейтинговыми продуктами и панелью MOST HOT DEALS")
    @Description("Проверка лайков между самыми рейтинговыми продуктами и панелью MOST HOT DEALS")
    public void likesValidation() {
        feedPage
                .openPage()
                .clickMostHot()
                .validateBestRatedProducts();
    }

    @Test(description = "кнопка должна измениться на SAVED")
    @Description("Проверка нажатия на кнопку ДОБАВИТЬ В вишлист")
    public void clickTheAddToWish() {
        Product liquid = new Product("Liquid Calcium with Magnesium, Natural Orange Flavor", "$15.36");
        feedPage
                .openPage()
                .openProductPage(liquid)
                .clickAddToFavorites()
                .saveButtonIsClicked()
                .clickAddToFavorites();
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

    @Test
    @Description("Добавление комментария")
    public void addAComment() {
        User user = new User("eugene2","password");
        Product oil = new Product("Apple Pectin, 700 mg", "$10.18");
        feedPage
                .openPage()
                .openLoginGate()
                .fillInLoginFields(user)
                .openProductPage(oil)
                .addAComment("Text");
    }

    @Test
    @Description("Проверка добавления продукта в вишлист")
    public void addToWishList() {
        Product pectin = new Product("Apple Pectin, 700 mg", "$10.18");
        feedPage
                .openPage()
                .openProductPage(pectin)
                .clickAddToFavorites()
                .openWishList()
                .validateProductIsAdded(pectin);
    }

    @Test
    @Description("Проверка удаления продукта из вишлиста")
    public void removeFromWishList() {
        Product conditioner = new Product("Honeysuckle Rose Conditioner, Moisture Intensive, Dry", "$10.40");
        feedPage
                .openPage()
                .openProductPage(conditioner)
                .clickAddToFavorites()
                .openWishList()
                .removeFromWishlist(conditioner);
    }

    @Test(description = "Валидация изменения кол-ва постов. Проверка текста в верхнем посте")
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

    @Test
    @Description("Добавление коммента к посту")
    public void addACommentToPost() {
        User user = new User("eugene2","password");
        feedPage
                .openPage()
                .openLoginGate()
                .fillInLoginFields(user)
                .openDropDown()
                .openUserProfile()
                .openPostPage()
                .addAComment("New comment233");
    }

    @Test
    @Description("Удаление коммента в посте")
    public void deleteACommentInPost() {
        User user = new User("eugene2","password");
        feedPage
                .openPage()
                .openLoginGate()
                .fillInLoginFields(user)
                .openDropDown()
                .openUserProfile()
                .openPostPage()
                .addAComment("New comment233")
                .deleteAComment();
    }

    @Test()
    @Description
    public void addPostToFavorites() {
        User user = new User("eugene2","password");
        feedPage
                .openPage()
                .openLoginGate()
                .fillInLoginFields(user)
                .openDropDown()
                .openUserProfile()
                .openPostPage()
                .clickFavorite();
    }



}
