package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
public class MoneyTransferPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement buttonTransfer = $("[data-test-id=action-transfer]");
    private SelenideElement errorNote = $("[data-test-id=error-notification]");

    public MoneyTransferPage() {
        amountField.shouldBe(visible);
    }

    public DashboardPage moneyTransferValid(DataHelper.CardInfo cardFrom) {
        amountField.setValue(DataHelper.getValidTransactionAmount(10_000).toString());
        fromField.setValue(cardFrom.getCardNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }

    public DashboardPage moneyTransfer0(DataHelper.CardInfo cardFrom) {
        amountField.setValue(DataHelper.setTransactionAmountTo0().toString());
        fromField.setValue(cardFrom.getCardNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }

    public void moneyTransferInvalid(DataHelper.CardInfo cardFrom) {
        amountField.setValue(DataHelper.getInvalidTransactionAmount(20_000).toString());
        fromField.setValue(cardFrom.getCardNumber());
        buttonTransfer.click();
    }

    public void errorTransfer() {
        errorNote.shouldBe(visible);
    }

}
