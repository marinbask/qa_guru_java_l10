package data;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class BirthdayArgumentProvider implements ArgumentsProvider {
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return Stream.of(
                Arguments.of(
                        today.minusYears(100).minusDays(1).format(formatter) ,
                        "Вам должно быть от 14 до 100 лет"
                ),
                Arguments.of(today.minusYears(14).plusDays(1).format(formatter),
                        "Вам должно быть от 14 до 100 лет"),
                Arguments.of(
                        today.plusDays(1).format(formatter) ,
                        "Вам должно быть от 14 до 100 лет"
                ),
                Arguments.of("43.11.2011" , "Поле заполнено некорректно")
        );
    }
}

