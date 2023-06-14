package test;

import data.DataGenerator;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {

    @Test
    public void planingMeetingOnNewDate() {
        open("http://localhost:9999");
        DataGenerator.UserInfo validUser= DataGenerator.Registration.userGenerator("ru");
        int daysToAddForFirstMeeting = 4;
        int daysToAddForSecondMeeting = 7;
        String firstMeetingDate = DataGenerator.dateGenerator(daysToAddForFirstMeeting);
        String secondMeetingDate = DataGenerator.dateGenerator(daysToAddForSecondMeeting);
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(firstMeetingDate);
        $("span[data-test-id=name] input").setValue(validUser.getName());
        $("[name='phone']").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $x("//*[text()=\"Запланировать\"]").click();
        $x("//*[text()=\"Успешно!\"]").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(secondMeetingDate);
        $x("//*[text()=\"Запланировать\"]").click();
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"))
                .shouldBe(visible);
        $("[data-test-id='replan-notification'] button").click();
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + secondMeetingDate))
                .shouldBe(visible);
    }
}
