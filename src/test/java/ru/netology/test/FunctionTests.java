package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import org.junit.jupiter.api.Test;
import ru.netology.data.SQLHelper;
import com.codeborne.selenide.logevents.SelenideLogger;

import lombok.val;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.codeborne.selenide.Selenide.open;

import io.qameta.allure.selenide.AllureSelenide;
import ru.netology.page.StartPage;


public class FunctionTests {
    private final static String approved = "APPROVED";
    private final static String declined = "DECLINED";

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }


    @AfterEach
    void afterEach()  {
        SQLHelper.cleanDB();


    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080/");

    }

    @AfterAll
    public static void afterAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void payApprovedCard() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.buyOperationSuccessful();
        assertEquals(approved, SQLHelper.getStatusFromPaymentEntity());

    }

    @Test
    void payDeclinedCard() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getDeclinedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(1), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.buyOperationFailed();
        assertEquals(declined, SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void payApprovedOnCredit() {
        val startPage = new StartPage();
        val paymentPage = startPage.payOnCredit();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getCvc());

        paymentPage.entryData(cardNumber);
        paymentPage.buyOperationSuccessful();
        assertEquals(approved, SQLHelper.getStatusFromCreditRequestEntity());

    }

    @Test
    void payDeclinedOnCredit() {
        val startPage = new StartPage();
        val paymentPage = startPage.payOnCredit();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getDeclinedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(4), DataHelper.getUserEng(), DataHelper.getCvc());

        paymentPage.entryData(cardNumber);
        paymentPage.buyOperationFailed();
        assertEquals(declined, SQLHelper.getStatusFromCreditRequestEntity());
    }
}
