package ru.netology;

import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CardOrderTest {
    @Test
    public void testOrderForm() {
        // Открытие страницы приложения
        open("http://localhost:9999");

        $("[data-test-id='city']").setValue("Тюмень");

        String date = "31.12.2024";
        $("input[name='date']").setValue(date);

        $("[data-test-id='name']").setValue("Иванов Иван");
        $("[data-test-id='phone']").setValue("+79129262754");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();

        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + date));
    }
}