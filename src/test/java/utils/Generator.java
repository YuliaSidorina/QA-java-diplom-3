package utils;

import io.qameta.allure.Step;
import model.User;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public final class Generator {

    @Step("Генерация имён")
    public static String randomName() {
        return randomAlphabetic(5);
    }

    @Step("Генерация email")
    public static String randomEmail() {
        return randomAlphabetic(5) + "@yandex.ru";
    }

    @Step("Генерация пароля")
    public static String randomPassword(int length) {
        return randomAlphanumeric(length);
    }

    @Step("Создание пользователя")
    public static User createRandomUser() {
        return new User(
                randomName(),
                randomEmail(),
                randomPassword(6)
        );
    }
}