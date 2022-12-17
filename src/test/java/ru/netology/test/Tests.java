package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import org.junit.jupiter.api.Test;
import ru.netology.data.SQLHelper;
import ru.netology.page.PaymentByCardPage;
import com.codeborne.selenide.logevents.SelenideLogger;

import java.sql.SQLException;

import lombok.val;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.codeborne.selenide.Selenide.open;

import io.qameta.allure.selenide.AllureSelenide;
import ru.netology.page.StartPage;


public class Tests {
    private PaymentByCardPage cardPayment = new PaymentByCardPage();
    private final static String cardApproved = "4444444444444441";
    private final static String cardDeclined = "4444444444444442";
    private final static String approved = "APPROVED";
    private final static String declined = "DECLINED";

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }


    @AfterEach
    void afterEach() throws SQLException {
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
    void payApprovedCard() throws SQLException {
        val startPage = new StartPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        cardPayment.buyOperationSuccessful();
        assertEquals(approved, SQLHelper.getStatusFromPaymentEntity());

    }

    @Test
    void payDeclinedCard() throws SQLException {
        val startPage = new StartPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardDeclined, DataHelper.getMonth(),
                DataHelper.getYear(1), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        cardPayment.buyOperationFailed();
        assertEquals(declined, SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void payApprovedOnCredit() throws SQLException {
        val startPage = new StartPage();
        startPage.payOnCredit();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getCvc());

        cardPayment.entryData(cardNumber);
        cardPayment.buyOperationSuccessful();
        assertEquals(approved, SQLHelper.getStatusFromCreditRequestEntity());

    }

    @Test
    void payDeclinedOnCredit() throws SQLException {
        val startPage = new StartPage();
        startPage.payOnCredit();
        val cardNumber = DataHelper.getCardInfo(cardDeclined, DataHelper.getMonth(),
                DataHelper.getYear(4), DataHelper.getUserEng(), DataHelper.getCvc());

        cardPayment.entryData(cardNumber);
        cardPayment.buyOperationFailed();
        assertEquals(declined, SQLHelper.getStatusFromCreditRequestEntity());
    }
}
