package test;

import data.DataHelper;
import org.junit.jupiter.api.*;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MoneyTransferTest {

    @BeforeEach
    void openTestPage() {
        open("http://localhost:9999");
    }

    @Order(1)
    @Test
    @DisplayName("Should transfer SOME MONEY from 2-nd to 1-st card when login valid")
    void shouldTransferMoneyWhenLoginValidFrom2To1Card(){

        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
        var balance1 = dashboardPage.getFirstCardBalance();
        var balance2 = dashboardPage.getLastCardBalance();
        var moneyTransferPage = dashboardPage.moneyTransferTo1();
        moneyTransferPage.moneyTransferValid(DataHelper.getSecondCardInfo());
        var balance1New = dashboardPage.getFirstCardBalance();
        var balance2New = dashboardPage.getLastCardBalance();

        Assertions.assertEquals(balance1New - balance2, balance1 - balance2New);
    }

    @Order(2)
    @Test
    @DisplayName("Should transfer ALL MONEY from 1-st to 2-nd card when login valid")
    void shouldTransferAllMoneyWhenLoginValidFrom1To2Card(){

        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
        var balance1 = dashboardPage.getFirstCardBalance();
        var balance2 = dashboardPage.getLastCardBalance();
        var moneyTransferPage = dashboardPage.moneyTransferTo1();
        moneyTransferPage.moneyTransferValid(DataHelper.getFirstCardInfo());
        var balance1New = dashboardPage.getFirstCardBalance();
        var balance2New = dashboardPage.getLastCardBalance();

        Assertions.assertEquals(balance2New - balance1, balance2 - balance1New);
    }

    @Order(3)
    @Test
    @DisplayName("Should not login when login is not valid but password is valid")
    void shouldNotLoginWhenLoginNotValid(){

        var authInfo = DataHelper.getInvalidAuthInfoLogin();
        var loginPage = new LoginPage();
        loginPage.invalidLogin(authInfo);

        loginPage.errorLogin();
    }

    @Order(4)
    @Test
    @DisplayName("Should not login when login is valid but password is not valid")
    void shouldNotLoginWhenPasswordNotValid() {

        var authInfo = DataHelper.getInvalidAuthInfoPassword();
        var loginPage = new LoginPage();
        loginPage.invalidLogin(authInfo);

        loginPage.errorLogin();
    }

    @Order(5)
    @Test
    @DisplayName("Should not login when login and password is valid but verification code is not valid")
    void shouldNotLoginWhenVerificationCodeNotValid(){

        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.invalidVerify();

        verificationPage.errorVerification();
    }

    @Order(6)
    @Test
    @DisplayName("Should not transfer 0 from 1-st to 2-nd card when login valid")
    void shouldNotTransfer0WhenLoginValidFrom1To2Card(){

        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
        var moneyTransferPage = dashboardPage.moneyTransferTo2();
        moneyTransferPage.moneyTransfer0(DataHelper.getFirstCardInfo());

        moneyTransferPage.errorTransfer();
    }

    @Order(7)
    @Test
    @DisplayName("Should not transfer 0 from 2-nd to 1-st card when login valid")
    void shouldNotTransfer0WhenLoginValidFrom2To1Card(){

        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
        var moneyTransferPage = dashboardPage.moneyTransferTo1();
        moneyTransferPage.moneyTransfer0(DataHelper.getSecondCardInfo());

        moneyTransferPage.errorTransfer();
    }

    @Order(8)
    @Test
    @DisplayName("Should not transfer sum above balance when login valid from 1-st to 2-nd card")
    void shouldNotTransferWhenLoginValidFrom1To2CardOverSum(){

        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
        var moneyTransferPage = dashboardPage.moneyTransferTo2();
        moneyTransferPage.moneyTransferInvalid(DataHelper.getFirstCardInfo());

        moneyTransferPage.errorTransfer();
    }

    @Order(9)
    @Test
    @DisplayName("Should not transfer sum above balance when login valid from 1-st to 2-nd card")
    void shouldNotTransferWhenLoginValidFrom2To1CardOverSum(){

        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
        var moneyTransferPage = dashboardPage.moneyTransferTo1();
        moneyTransferPage.moneyTransferInvalid(DataHelper.getSecondCardInfo());

        moneyTransferPage.errorTransfer();
    }
}
