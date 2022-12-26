package ru.netology.page;

import com.codeborne.selenide.Condition;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.hidden;

public class PaymentOnCreditPage extends ElementsPage {

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
