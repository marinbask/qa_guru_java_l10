package test;

import data.BirthdayArgumentProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormForDebitCard extends TestBaseTinkoff {
    @ArgumentsSource(BirthdayArgumentProvider.class)
    @ParameterizedTest(name = "Для даты {0} текст валидации долженбыть {1}")
    void successfulSendFormForDebitCard(String birthday, String ValidationText) {
        open("/cards/debit-cards/tinkoff-black/");
        $("#form").scrollIntoView(true);
        $("div[data-qa-type=\"uikit/input.inputBox.main\"").click();
        $("input[name=\"birthdate\"]").setValue(birthday).pressEnter();
        $("div[data-qa-type=\"uikit/formRow.errorBlock\"]").shouldHave(exactText(ValidationText));
    }

    @CsvSource(value = {
            "cards/debit-cards/tinkoff-black/foreign/kg/ , Россияда жашоо жана иштөө үчүн ыңгайлуу карта",
            "cards/debit-cards/tinkoff-black/foreign/uz/ , Rossiyada yashash va ishlash uchun qulay karta",
            "cards/debit-cards/tinkoff-black/foreign/eng/ , Convenient card for your life and work in Russia",
                })
    @ParameterizedTest(name = "Для url {0} текст заголовка должен быть {1}")
    void CheckLanguage (String UrlLocal, String ValidationText) {
        open(UrlLocal);
        $("div[class=\"application\"]").shouldHave(text(ValidationText));
    }


    @ParameterizedTest(name = "Если искать {0}, то первый элемент в поисковой выдаче будет {0}")
    @ValueSource(strings = {
            "Как скачать приложение",
            "Как написать в чат",
            "Получить справку или выписку"
    })
    void checkOutPut(String textSearch){
        open("help/");
        $("[data-qa-type=\"uikit/popover.children\"]").click();
        $("[data-qa-type=\"uikit/popover.children\"] input[type=\"text\"]").setValue(textSearch).pressEnter();
        $("[data-qa-type=\"uikit/dropdown.item\"]").shouldHave(text(textSearch));
    }
}
