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

    public DashboardPage moneyTransferTo(Integer cardNumber, Integer moneyValue) {
        amountField.setValue(DataHelper.getValidTransferInfoTo(cardNumber, moneyValue).getAmount());
        fromField.setValue(DataHelper.getValidTransferInfoTo(cardNumber, moneyValue).getFromCardNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }

    public MoneyTransferPage errorTransfer() {
        errorNote.shouldBe(visible);
        return new MoneyTransferPage();
    }

}
