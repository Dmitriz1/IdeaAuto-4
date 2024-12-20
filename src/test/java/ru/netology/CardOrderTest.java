package ru.netology;

import org.junit.jupiter.api.Test;

import org.openqa.selenium.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardOrderTest {
    @Test
    public void testOrderForm() {
        open("http://localhost:9999");

        String city = "Тюмень";
        $(By.cssSelector("[data-test-id='city'] input")).setValue(city);

        LocalDate currentDate = LocalDate.now();
        LocalDate deliveryDate = currentDate.plusDays(3);
        String formattedDate = deliveryDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $(By.cssSelector("[data-test-id='date'] input")).setValue(formattedDate);

        String name = "Иванов Иван";
        $(By.cssSelector("[data-test-id='name'] input")).setValue(name);

        String phone = "+79129262754";
        $(By.cssSelector("[data-test-id='phone'] input")).setValue(phone);

        $(By.cssSelector("[data-test-id='agreement']")).click();

        $(By.className("button")).click();

        $(By.className("notification__content"))
                .shouldHave(text("Встреча успешно забронирована на " + formattedDate), Duration.ofSeconds(15))
                .shouldBe(visible);
    }
}