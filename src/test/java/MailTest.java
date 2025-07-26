import com.codeborne.selenide.Configuration;
import data.EnumLanguageHosting;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;

public class MailTest {

    @BeforeEach
    void setUp() {
        open("https://www.inmotionhosting.com/");
        Configuration.browser = "chrome";
        Configuration.pageLoadTimeout = 100000;
    }


    @EnumSource(EnumLanguageHosting.class)
    @ParameterizedTest
    @Tag("Smoke")
    void mailPrivateAndBusinessClient(EnumLanguageHosting language) {
        $("span.wglanguage-name").hover().click();
        $$("ul li a").findBy(text(language.name)).click();
        $("#dedicatedServersDropDown").shouldHave(text(language.button));
    }



    static Stream<Arguments> languageDropDown() {
        return Stream.of(
                Arguments.of(
                        Languages.Nederlands("Dedicated servers","WordPress", "Web"))
//                Arguments.of(
//                        Languages.Español,
//                        List.of("Stays", "Flight", "Flight + Hotel", "Car rental", "Attractions", "Airport taxis")),
//                Arguments.of(
//                        Languages.Français,
//                        List.of("Pobyty", "Loty", "Lot + Hotel", "Wynajem samochodu", "Atrakcje", "Taksówki lotniskowe"))
        );
    }

    @MethodSource("languageDropDown")
    @ParameterizedTest
    @Tag("")
    void hostingLanguageDropDown(Languages language, List<String> buttonsRent){
        $("span.wglanguage-name").hover().click();
        $$("ul li a").findBy(text(language.name)).click();
        $("#dedicatedServersDropDown").shouldHave(text(language.button));
    }
    }
}