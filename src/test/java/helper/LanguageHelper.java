package helper;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class LanguageHelper {
    static Stream<Arguments> languageDropDown() {
        return Stream.of(
                Arguments.of("Nederlands", List.of("VPS Hosting", "Dedicated servers","WordPress","Web Hosting",
                       "Website Diensten")),
                Arguments.of("Español", List.of("VPS Hosting","Servidores dedicados","WordPress","Web Hosting"
                        ,"Servicios web"))
        );
    }
}
