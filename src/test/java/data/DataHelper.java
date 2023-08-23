package data;

import lombok.Value;

import java.util.Random;

public class DataHelper {

    private DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidAuthInfoLogin() {
        return new AuthInfo("petya", "qwerty123");
    }

    public static AuthInfo getInvalidAuthInfoPassword() {
        return new AuthInfo("vasya", "qwerty124");
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static String getInvalidVerificationCode() {
        return new String("12346");
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo("5559 0000 0000 0001");
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559 0000 0000 0002");
    }

    public static Integer getValidTransactionAmount(Integer balance) {
        return new Integer(new Random().nextInt(balance));
    }

    public static Integer getInvalidTransactionAmount(Integer balance) {
        return new Integer(new Random().nextInt(balance) + balance);
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String verificationCode;
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
    }
}
