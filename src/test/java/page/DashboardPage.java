package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item");
    private ElementsCollection buttonsTransfer = $$("[data-test-id=action-deposit]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement buttonRefresh = $("[data-test-id=action-reload]");
    public DashboardPage() {
        cards.first().shouldBe(visible);
    }

    public MoneyTransferPage moneyTransferTo1() {
        buttonsTransfer.first().click();
        return new MoneyTransferPage();
    }

    public MoneyTransferPage moneyTransferTo2() {
        buttonsTransfer.last().click();
        return new MoneyTransferPage();
    }

    public int getFirstCardBalance() {
        buttonRefresh.click();
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getLastCardBalance() {
        buttonRefresh.click();
        val text = cards.last().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
