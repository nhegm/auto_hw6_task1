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

    public static TransferInfo getValidTransferInfoToCard2(Integer amountToTransfer) {
        return new TransferInfo(amountToTransfer.toString(), "5559 0000 0000 0001");
    }

    public static TransferInfo getValidTransferInfoToCard1(Integer amountToTransfer) {
        return new TransferInfo(amountToTransfer.toString(), "5559 0000 0000 0002");
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
        private String amount;
        private String fromNumber;
    }

}
