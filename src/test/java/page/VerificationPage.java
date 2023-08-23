package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement buttonVerify = $("[data-test-id=action-verify]");
    private SelenideElement errorNote = $("[data-test-id=error-notification] .notification__content");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getVerificationCode());
        buttonVerify.click();
        return new DashboardPage();
    }

    public void invalidVerify() {
        codeField.setValue(DataHelper.getInvalidVerificationCode());
        buttonVerify.click();
    }

    public void errorVerification() {
        errorNote.shouldBe(visible);
        errorNote.shouldHave(exactText("Ошибка! Неверно указан код! Попробуйте ещё раз."));
    }
}
