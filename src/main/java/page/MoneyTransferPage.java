package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
public class MoneyTransferPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement buttonTransfer = $("[data-test-id=action-transfer]");

    public MoneyTransferPage() {
        amountField.shouldBe(visible);
    }

    public DashboardPage moneyTransferTo1Valid(Integer moneyValue) {
        amountField.setValue(DataHelper.getValidTransferInfoToCard1(moneyValue).getAmount());
        fromField.setValue(DataHelper.getValidTransferInfoToCard1(moneyValue).getFromNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }

    public DashboardPage moneyTransferTo2Valid(Integer moneyValue) {
        amountField.setValue(DataHelper.getValidTransferInfoToCard2(moneyValue).getAmount());
        fromField.setValue(DataHelper.getValidTransferInfoToCard2(moneyValue).getFromNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }

}
