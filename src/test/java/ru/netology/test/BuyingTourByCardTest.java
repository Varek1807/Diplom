package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import org.junit.jupiter.api.Test;
import ru.netology.data.SQLHelper;
import com.codeborne.selenide.logevents.SelenideLogger;

import lombok.val;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static com.codeborne.selenide.Selenide.open;

import io.qameta.allure.selenide.AllureSelenide;
import ru.netology.page.StartPage;

public class BuyingTourByCardTest {
    private final static String approved = "APPROVED";
    private final static String declined = "DECLINED";


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void afterEach() {
        SQLHelper.cleanDB();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080/");

    }


    @Test
    void insertInFieldMonth00() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth00(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorMonth("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());

    }

    @Test
    void insertInFieldMonth13() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth13(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorMonth("Неверно указан срок действия карты");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldMonthLetters() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getLetters(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorMonth("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldMonthSymbol() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getTwoSymbol(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorMonth("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldMonthWhitespace() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonthWhitespace(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorMonth("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void fieldMonthEmpty() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getEmptyValue(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorMonth("Поле обязательно для заполнения");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCardNumber15Digit() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getCardNumber15Quantity(), DataHelper.getMonth(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorCardNumber("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCardNumber17Digit() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getCardNumber17Quantity(), DataHelper.getMonth(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.buyOperationSuccessful();
        assertEquals(approved, SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCardNumberChars() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getCardNumberChars(), DataHelper.getMonth(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorCardNumber("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCardNumberWhitespace() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getCardNumberWhitespace(), DataHelper.getMonth(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorCardNumber("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void fieldCardNumberEmpty() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getEmptyValue(), DataHelper.getMonth(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorCardNumber("Поле обязательно для заполнения");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldYearMoreThanCurrent6Years() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(6), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorYear("Неверно указан срок действия карты");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void fieldYearEmpty() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(), DataHelper.getEmptyValue(), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorYear("Поле обязательно для заполнения");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldYearTwoSymbol() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getTwoSymbol(), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorYear("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldYearTwoChars() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getLetters(), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorYear("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldYearThreeDigit() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYearThreeDigit(), DataHelper.getUserEng(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.buyOperationSuccessful();
        assertEquals(approved, SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCVCThreeSymbol() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getCVCSymbol());
        paymentPage.entryData(cardNumber);
        paymentPage.errorCVC("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCVCThreeChars() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getCVCChars());
        paymentPage.entryData(cardNumber);
        paymentPage.errorCVC("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCVCTwoDigit() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getCVCTwoDigit());
        paymentPage.entryData(cardNumber);
        paymentPage.errorCVC("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCVCOneDigit() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getCVCOneDigit());
        paymentPage.entryData(cardNumber);
        paymentPage.errorCVC("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCVCThreeSpaces() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getCVCWhitespace());
        paymentPage.entryData(cardNumber);
        paymentPage.errorCVC("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCVCFourDigit() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getCVCFourDigit());
        paymentPage.entryData(cardNumber);
        paymentPage.buyOperationSuccessful();
        assertEquals(approved, SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void fieldCVCEmpty() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getEmptyValue());
        paymentPage.entryData(cardNumber);
        paymentPage.errorCVC("Поле обязательно для заполнения");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void fieldUserEmpty() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getEmptyValue(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorUser("Поле обязательно для заполнения");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldUserNumbers() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getCVCFourDigit(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorUser("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldUserRu() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserRu(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorUser("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldUserOneChar() {
        val startPage = new StartPage();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserOneChar(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorUser("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldUserSymbols() {
        val startPage = new StartPage();
//        val paymentPage = new PaymentByCardPage();
//        startPage.payByCard();
        val paymentPage = startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getApprovedCardNumber(), DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserSymbols(), DataHelper.getCvc());
        paymentPage.entryData(cardNumber);
        paymentPage.errorUser("Неверный формат");
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }
}
