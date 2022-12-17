package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class StartPage {
    private SelenideElement buy = $x(" //*[text()='Купить']");
    private SelenideElement buyOnCredit = $x(" //*[text()='Купить в кредит']");
    private SelenideElement paymentByCard = $x(" //*[text()='Оплата по карте']");
    private SelenideElement paymentOnCredit = $x(" //*[text()='Кредит по данным карты']");

    public PaymentByCardPage payByCard() {
        buy.click();
        paymentByCard.shouldBe(visible, Duration.ofSeconds(15));
        return new PaymentByCardPage();
    }

    public PaymentOnCreditPage payOnCredit() {
        buyOnCredit.click();
        paymentOnCredit.shouldBe(visible, Duration.ofSeconds(15));
        return new PaymentOnCreditPage();
    }
}
