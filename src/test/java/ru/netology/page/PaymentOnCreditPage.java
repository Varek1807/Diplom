package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentOnCreditPage extends ElementsPage {
//    private SelenideElement cardNumber = $("[placeholder=\"0000 0000 0000 0000\"]");
//    private SelenideElement month = $("[placeholder=\"08\"]");
//    private SelenideElement year = $("[placeholder=\"22\"]");
//    private SelenideElement user = $(byText("Владелец")).parent().$(".input__control");
//    private SelenideElement cvc = $("[placeholder=\"999\"]");
//
//    private SelenideElement buttonContinue = $x(" //*[text()='Продолжить']");
//    private SelenideElement errorMessageCard = $(byText("Номер карты")).parent().$(".input__sub");
//    private SelenideElement errorMessageMonth = $(byText("Месяц")).parent().$(".input__sub");
//    private SelenideElement errorMessageYear = $(byText("Год")).parent().$(".input__sub");
//    private SelenideElement errorMessageUser = $(byText("Владелец")).parent().$(".input__sub");
//    private SelenideElement errorMessageCVC = $(byText("CVC/CVV")).parent().$(".input__sub");
//    private SelenideElement approvedMessage = $$(".notification__title").find(exactText("Успешно"));
//    //private SelenideElement getMessage = $$(".notification__title").cl;
//    private SelenideElement declinedMessage = $$(".notification__title").find(exactText("Ошибка"));
//    private SelenideElement closeDeclinedMessage = $$(".notification__closer").last();
//    private SelenideElement closeApprovedMessage = $$(".notification__closer").first();

    public void entryData(DataHelper.CardInfo info) {
        cardNumber.setValue(info.getCardNumber());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        user.setValue(info.getUser());
        cvc.setValue(info.getCvc());
        buttonContinue.click();
        return;
    }

    public void buyOperationSuccessful() {
        approvedMessage.should(Condition.visible, Duration.ofSeconds(40));
        closeApprovedMessage.click();
        approvedMessage.should(Condition.hidden);
        declinedMessage.should(Condition.hidden);
    }

    public void buyOperationFailed() {
        declinedMessage.should(Condition.visible, Duration.ofSeconds(40));
        closeDeclinedMessage.click();
        declinedMessage.should(Condition.hidden);
        approvedMessage.should(Condition.hidden);
    }

    public void errorCardNumber(String textError) {
        errorMessageCard.shouldHave(Condition.text(textError)).shouldBe(Condition.visible);
        errorMessageMonth.shouldBe(hidden);
        errorMessageYear.shouldBe(hidden);
        errorMessageUser.shouldBe(hidden);
        errorMessageCVC.shouldBe(hidden);
    }

    public void errorMonth(String textError) {
        errorMessageMonth.shouldHave(Condition.text(textError)).shouldBe(Condition.visible);
        errorMessageCard.shouldBe(hidden);
        errorMessageYear.shouldBe(hidden);
        errorMessageUser.shouldBe(hidden);
        errorMessageCVC.shouldBe(hidden);
    }

    public void errorYear(String textError) {
        errorMessageYear.shouldHave(Condition.text(textError)).shouldBe(Condition.visible);
        errorMessageMonth.shouldBe(hidden);
        errorMessageCard.shouldBe(hidden);
        errorMessageUser.shouldBe(hidden);
        errorMessageCVC.shouldBe(hidden);
    }

    public void errorUser(String textError) {
        errorMessageUser.shouldHave(Condition.text(textError)).shouldBe(Condition.visible);
        errorMessageMonth.shouldBe(hidden);
        errorMessageYear.shouldBe(hidden);
        errorMessageCard.shouldBe(hidden);
        errorMessageCVC.shouldBe(hidden);
    }

    public void errorCVC(String textError) {
        errorMessageCVC.shouldHave(Condition.text(textError)).shouldBe(Condition.visible);
        errorMessageMonth.shouldBe(hidden);
        errorMessageYear.shouldBe(hidden);
        errorMessageUser.shouldBe(hidden);
        errorMessageCard.shouldBe(hidden);
    }

}
