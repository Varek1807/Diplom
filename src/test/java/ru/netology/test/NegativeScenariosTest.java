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
import static org.junit.jupiter.api.Assertions.assertNull;
import static com.codeborne.selenide.Selenide.open;

import io.qameta.allure.selenide.AllureSelenide;
import ru.netology.page.StartPage;

public class NegativeScenariosTest {

    private PaymentByCardPage cardPayment = new PaymentByCardPage();
    private final static String cardApproved = "4444444444444441";
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

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080/");

    }


    @Test
    void insertInFieldMonth00() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth00(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorMonth();
        assertNull(SQLHelper.getStatusFromPaymentEntity());

    }

    @Test
    void insertInFieldMonth13() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth13(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorMonth();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldMonthLetters() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getLetters(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorMonth();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldMonthSymbol() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getTwoSymbol(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorMonth();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldMonthWhitespace() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonthWhitespace(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorMonth();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void fieldMonthEmpty() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getEmptyValue(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorMonth();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCardNumber15Digit() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getCardNumber15Quantity(), DataHelper.getMonth(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorCardNumber();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCardNumber17Digit() throws SQLException {
        val startPage = new StartPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getCardNumber17Quantity(), DataHelper.getMonth(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        cardPayment.buyOperationSuccessful();
        assertEquals(approved, SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCardNumberChars() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getCardNumberChars(), DataHelper.getMonth(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorCardNumber();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCardNumberWhitespace() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getCardNumberWhitespace(), DataHelper.getMonth(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorCardNumber();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void fieldCardNumberEmpty() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(DataHelper.getEmptyValue(), DataHelper.getMonth(),
                DataHelper.getYear(2), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorCardNumber();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldYearMoreThanCurrent6Years() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYear(6), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorYear();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void fieldYearEmpty() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(), DataHelper.getEmptyValue(), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorYear();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldYearTwoSymbol() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getTwoSymbol(), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorYear();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldYearTwoChars() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getLetters(), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorYear();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldYearThreeDigit() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYearThreeDigit(), DataHelper.getUserEng(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.buyOperationSuccessful();
        assertEquals(approved, SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCVCThreeSymbol() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getCVCSymbol());
        cardPayment.entryData(cardNumber);
        paymentPage.errorCVC();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCVCThreeChars() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getCVCChars());
        cardPayment.entryData(cardNumber);
        paymentPage.errorCVC();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCVCTwoDigit() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getCVCTwoDigit());
        cardPayment.entryData(cardNumber);
        paymentPage.errorCVC();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCVCOneDigit() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getCVCOneDigit());
        cardPayment.entryData(cardNumber);
        paymentPage.errorCVC();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCVCThreeSpaces() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getCVCWhitespace());
        cardPayment.entryData(cardNumber);
        paymentPage.errorCVC();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldCVCFourDigit() throws SQLException {
        val startPage = new StartPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getCVCFourDigit());
        cardPayment.entryData(cardNumber);
        cardPayment.buyOperationSuccessful();
        assertEquals(approved, SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void fieldCVCEmpty() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserEng(), DataHelper.getEmptyValue());
        cardPayment.entryData(cardNumber);
        paymentPage.errorCVC();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void fieldUserEmpty() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getEmptyValue(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorUser();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldUserNumbers() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getCVCFourDigit(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorUser();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldUserRu() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserRu(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorUser();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldUserOneChar() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserOneChar(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorUser();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }

    @Test
    void insertInFieldUserSymbols() throws SQLException {
        val startPage = new StartPage();
        val paymentPage = new PaymentByCardPage();
        startPage.payByCard();
        val cardNumber = DataHelper.getCardInfo(cardApproved, DataHelper.getMonth(),
                DataHelper.getYear(3), DataHelper.getUserSymbols(), DataHelper.getCvc());
        cardPayment.entryData(cardNumber);
        paymentPage.errorUser();
        assertNull(SQLHelper.getStatusFromPaymentEntity());
    }
}
