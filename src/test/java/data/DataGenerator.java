package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private DataGenerator() {
    }

    public static String dateGenerator (int addDays) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String cityGenerator () {
        var cities = new String[]{"Москва", "Калуга", "Ижевск", "Владимир", "Казань", "Краснодар", "Пермь",
                "Уфа", "Чита", "Псков", "Омск", "Красноярск", "Тула", "Томск", "Тамбов", "Смоленск", "Астрахань",
                "Владивосток", "Петрозаводск", "Калининград", "Махачкала", "Барнаул"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String nameGenerator (String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String phoneGenerator (String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo userGenerator(String locale) {
            return new UserInfo(cityGenerator(), nameGenerator(locale), phoneGenerator(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
