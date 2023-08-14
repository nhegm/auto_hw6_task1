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
    @DisplayName("Should transfer ALL MONEY from 2-nd to 1-st card when login valid")
    void shouldTransferAllMoneyWhenLoginValidFrom2To1Card(){

        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
        var balance1 = dashboardPage.getFirstCardBalance();
        var balance2 = dashboardPage.getLastCardBalance();
        var moneyTransferPage = dashboardPage.moneyTransferTo1();
        moneyTransferPage.moneyTransferTo(2, balance2);

        Assertions.assertEquals(balance1 + balance2, dashboardPage.getFirstCardBalance());
        Assertions.assertEquals(0, dashboardPage.getLastCardBalance());
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
        var moneyTransferPage = dashboardPage.moneyTransferTo2();
        moneyTransferPage.moneyTransferTo(1, balance1);

        Assertions.assertEquals(0, dashboardPage.getFirstCardBalance());
        Assertions.assertEquals(balance1, dashboardPage.getLastCardBalance());
    }

    @Order(3)
    @Test
    @DisplayName("Should transfer (ALL MONEY - 1) from 2-nd to 1-st card when login valid")
    void shouldTransferWhenLoginValidFrom2To1Card(){

        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
        var balance2 = dashboardPage.getLastCardBalance();
        var moneyTransferPage = dashboardPage.moneyTransferTo1();
        moneyTransferPage.moneyTransferTo(2, balance2 - 1);

        Assertions.assertEquals(balance2 - 1, dashboardPage.getFirstCardBalance());
        Assertions.assertEquals(1, dashboardPage.getLastCardBalance());
    }

    @Order(4)
    @Test
    @DisplayName("Should transfer (ALL MONEY - 1) from 1-st to 2-nd card when login valid")
    void shouldTransferSomeWhenLoginValidFrom2To1Card(){

        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
        var balance1 = dashboardPage.getFirstCardBalance();
        var balance2 = dashboardPage.getLastCardBalance();
        var moneyTransferPage = dashboardPage.moneyTransferTo2();
        moneyTransferPage.moneyTransferTo(1, balance1 - 1);

        Assertions.assertEquals(1, dashboardPage.getFirstCardBalance());
        Assertions.assertEquals(balance2 + balance1 - 1, dashboardPage.getLastCardBalance());
    }

    @Order(5)
    @Test
    @DisplayName("Should transfer 1 rub from 2-nd to 1-st card when login valid")
    void shouldTransfer1rubWhenLoginValidFrom2To1Card(){

        Integer money = 1;
        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
        var balance1 = dashboardPage.getFirstCardBalance();
        var balance2 = dashboardPage.getLastCardBalance();
        var moneyTransferPage = dashboardPage.moneyTransferTo1();
        moneyTransferPage.moneyTransferTo(2, money);

        Assertions.assertEquals(balance1 + money, dashboardPage.getFirstCardBalance());
        Assertions.assertEquals(balance2 - money, dashboardPage.getLastCardBalance());
    }

    @Order(6)
    @Test
    @DisplayName("Should transfer 1 rub from 1-st to 2-nd card when login valid")
    void shouldTransfer1rubWhenLoginValidFrom1To2Card(){

        Integer money = 1;
        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
        var balance1 = dashboardPage.getFirstCardBalance();
        var balance2 = dashboardPage.getLastCardBalance();
        var moneyTransferPage = dashboardPage.moneyTransferTo2();
        moneyTransferPage.moneyTransferTo(1, money);

        Assertions.assertEquals(balance1 - money, dashboardPage.getFirstCardBalance());
        Assertions.assertEquals(balance2 + money, dashboardPage.getLastCardBalance());
    }

    @Order(7)
    @Test
    @DisplayName("Should not transfer 0 from 1-st to 2-nd card when login valid")
    void shouldNotTransfer0WhenLoginValidFrom1To2Card(){

        Integer money = 0;
        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
        var moneyTransferPage = dashboardPage.moneyTransferTo2();
        moneyTransferPage.moneyTransferTo(1, money);

        moneyTransferPage.errorTransfer();
    }

    @Order(8)
    @Test
    @DisplayName("Should not transfer 0 from 2-nd to 1-st card when login valid")
    void shouldNotTransfer0WhenLoginValidFrom2To1Card(){

        Integer money = 0;
        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
        var moneyTransferPage = dashboardPage.moneyTransferTo2();
        moneyTransferPage.moneyTransferTo(2, money);

        moneyTransferPage.errorTransfer();
    }

    @Order(9)
    @Test
    @DisplayName("Should not transfer sum above balance when login valid from 1-st to 2-nd card")
    void shouldNotTransferWhenLoginValidFrom1To2CardOverSum(){

        Integer money = 20_000;
        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
        var moneyTransferPage = dashboardPage.moneyTransferTo2();
        moneyTransferPage.moneyTransferTo(1, money);

        moneyTransferPage.errorTransfer();
    }

    @Order(10)
    @Test
    @DisplayName("Should not transfer sum above balance when login valid from 1-st to 2-nd card")
    void shouldNotTransferWhenLoginValidFrom2To1CardOverSum(){

        Integer money = 22_000;
        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode(authInfo));
        var moneyTransferPage = dashboardPage.moneyTransferTo1();
        moneyTransferPage.moneyTransferTo(1, money);

        moneyTransferPage.errorTransfer();
    }

    @Order(11)
    @Test
    @DisplayName("Should not login when login is not valid but password is valid")
    void shouldNotLoginWhenLoginNotValid(){

        var authInfo = DataHelper.getInvalidAuthInfoLogin();
        var loginPage = new LoginPage();
        loginPage.invalidLogin(authInfo);

        loginPage.errorLogin();
    }

    @Order(12)
    @Test
    @DisplayName("Should not login when login is valid but password is not valid")
    void shouldNotLoginWhenPasswordNotValid() {

        var authInfo = DataHelper.getInvalidAuthInfoPassword();
        var loginPage = new LoginPage();
        loginPage.invalidLogin(authInfo);

        loginPage.errorLogin();
    }

    @Order(13)
    @Test
    @DisplayName("Should not login when login and password is valid but verification code is not valid")
    void shouldNotLoginWhenVerificationCodeNotValid(){

        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.invalidVerify();

        verificationPage.errorVerification();
    }
}
