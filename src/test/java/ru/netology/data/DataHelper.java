package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker();


    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        public String cardNumber;
        public String month;
        public String year;
        public String user;
        public String cvc;
    }
    public static String getApprovedCardNumber() {
        return "4444444444444441";
    }

    public static String getDeclinedCardNumber() {
        return "4444444444444442";
    }
    public static CardInfo getCardInfo(String cardNumber, String month, String year, String user, String cvc) {
        return new CardInfo(cardNumber, month, year, user, cvc);
    }
    static Faker fakerRus = new Faker(new Locale("ru"));
    static Faker fakerEng = new Faker(new Locale("en"));
    static Calendar calendar = new GregorianCalendar();

    public static String getMonth() {
        return String.format("%02d", calendar.get(Calendar.MONTH));
    }

    public static String getYear(int shift) {
        return String.valueOf(calendar.get(Calendar.YEAR) + shift).substring(2);
    }

    public static String getUserEng() {
        return fakerEng.name().fullName();
    }

    public static String getUserRu() {

        return fakerRus.name().fullName();
    }

    public static String getCvc() {
        return String.valueOf(faker.number().numberBetween(100, 999));
    }

    public static String getMonth00() {
        return ("00");
    }

    public static String getMonth13() {
        return ("13");
    }

    public static String getLetters() {
        return ("NN");
    }

    public static String getTwoSymbol() {
        return (":%");

    }

    public static String getYearThreeDigit() {
        return ("255");
    }

    public static String getMonthWhitespace() {
        return ("  ");
    }

    public static String getCVCWhitespace() {
        return ("   ");
    }

    public static String getCardNumber15Quantity() {
        return ("444444444444441");
    }

    public static String getCardNumber17Quantity() {
        return ("44444444444444411");
    }

    public static String getCardNumberChars() {
        return ("aaaabbbbccccdddd");
    }

    public static String getCardNumberWhitespace() {
        return ("!!!!\"\"\"\"№№№№;;;;");
    }

    public static String getCVCSymbol() {
        return ("**%");
    }

    public static String getCVCChars() {
        return ("CVC");
    }

    public static String getEmptyValue() {
        return ("");
    }

    public static String getCVCOneDigit() {
        return String.valueOf(faker.number().numberBetween(0, 9));
    }

    public static String getCVCTwoDigit() {
        return String.valueOf(faker.number().numberBetween(10, 99));
    }

    public static String getCVCFourDigit() {
        return String.valueOf(faker.number().numberBetween(1000, 9999));
    }

    public static String getUserOneChar() {
        return ("A");
    }

    public static String getUserSymbols() {
        return ("@@@^^^&&&");

    }
}