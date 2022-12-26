package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;

public class ElementsPage {
    protected SelenideElement cardNumber = $("[placeholder=\"0000 0000 0000 0000\"]");
    protected SelenideElement month = $("[placeholder=\"08\"]");
    protected SelenideElement year = $("[placeholder=\"22\"]");
    protected SelenideElement user = $(byText("Владелец")).parent().$(".input__control");
    protected SelenideElement cvc = $("[placeholder=\"999\"]");

    protected SelenideElement buttonContinue = $x(" //*[text()='Продолжить']");
    protected SelenideElement errorMessageCard = $(byText("Номер карты")).parent().$(".input__sub");
    protected SelenideElement errorMessageMonth = $(byText("Месяц")).parent().$(".input__sub");
    protected SelenideElement errorMessageYear = $(byText("Год")).parent().$(".input__sub");
    protected SelenideElement errorMessageUser = $(byText("Владелец")).parent().$(".input__sub");
    protected SelenideElement errorMessageCVC = $(byText("CVC/CVV")).parent().$(".input__sub");
    protected SelenideElement approvedMessage = $$(".notification__title").find(exactText("Успешно"));

    protected SelenideElement declinedMessage = $$(".notification__title").find(exactText("Ошибка"));
    protected SelenideElement closeDeclinedMessage = $$(".notification__closer").last();
    protected SelenideElement closeApprovedMessage = $$(".notification__closer").first();
}
