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

    public DashboardPage moneyTransfer(DataHelper.CardInfo cardFrom, String amount) {
        makeTransfer(cardFrom, amount);
        return new DashboardPage();
    }

    public void makeTransfer(DataHelper.CardInfo cardFrom, String amount) {
        amountField.setValue(amount);
        fromField.setValue(cardFrom.getCardNumber());
        buttonTransfer.click();
    }

    public void errorTransfer() {
        errorNote.shouldBe(visible);
    }

}
