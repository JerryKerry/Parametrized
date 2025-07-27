import helper.LanguageHelper;
import com.codeborne.selenide.Configuration;
import data.EnumLanguageHosting;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class HostingLanguageTest extends LanguageHelper {


    @BeforeEach
    void setUp() {
        open("https://www.inmotionhosting.com/");
        Configuration.browser = "chrome";
        Configuration.pageLoadTimeout = 100000;
    }

    @EnumSource(EnumLanguageHosting.class)
    @ParameterizedTest
    @Tag("Smoke")
    void EnumLanguageHosting(EnumLanguageHosting language) {
        $("span.wglanguage-name").hover().click();
        $$("ul li a").findBy(text(language.name)).click();
        $("#dedicatedServersDropDown").shouldHave(text(language.button));
    }

    @ValueSource(strings = {"Nederlands","Español"})
    @ParameterizedTest
    @Tag("Smoke")
    void valueSourceLanguages(String language) {
        $("span.wglanguage-name").hover().click();
        $$("ul li a").findBy(text(language)).click();
        $("span.wglanguage-name").shouldHave(text(language));
    }

    @MethodSource("languageDropDown")
    @ParameterizedTest
    @Tag("Smoke")
    void hostingLanguageDropDown(String language, List<String> buttonsRent){
        $("span.wglanguage-name").hover().click();
        $$("ul li a").findBy(text(language)).click();
        List<String>  actualItems  = $$("#navbarNavDropdown > ul > li > a")
                .filter(visible)
                .texts()
                .stream()
                .filter(buttonsRent::contains)
                .sorted()
                .collect(Collectors.toList());

        List<String> expectedSorted  = buttonsRent.stream().sorted().collect(Collectors.toList());
        assertEquals(expectedSorted , actualItems);

    }
}
