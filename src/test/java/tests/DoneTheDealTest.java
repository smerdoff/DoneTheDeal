package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.FeedPage;

public class DoneTheDealTest extends BaseTest {
    @Test
    @Description("Проверка ввода корректных кредов в окне логина. Если пользователь войдёт в приложение, то кнопка Login должна исчезнуть")
    public void correctLogin(){
        feedPage.openPage()
                .openLoginGate()
                .fillInLoginFields("eugene","password")
                .validationOfSuccessfulLogin()
                ;
    }

    @Test
    @Description("Проверка ввода некорректных кредов в окне логина. Валидация ошибки")
    public void incorrectLogin(){
        feedPage.openPage()
                .openLoginGate()
                .fillInLoginFields("eugenes","password")
                .validateIncorrectLoginWarning()
        ;
    }
}
