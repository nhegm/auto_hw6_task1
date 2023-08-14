package data;

import lombok.Value;

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

    public static TransferInfo getValidTransferInfoTo(Integer fromCard, Integer amountToTransfer) {
        String fromCardNumber = new String();
        if (fromCard == 1) {
            fromCardNumber="5559 0000 0000 0001";
        }
        if (fromCard == 2) {
            fromCardNumber="5559 0000 0000 0002";
        }
        return new TransferInfo(fromCard, amountToTransfer.toString(), fromCardNumber);
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
    public static class TransferInfo {
        private Integer fromCard;
        private String amount;
        private String fromCardNumber;
    }

}
