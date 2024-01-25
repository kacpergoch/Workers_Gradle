package workers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import workers.controller.InputValidation;

import static org.junit.jupiter.api.Assertions.*;

public class TestInputValidation {
    @ParameterizedTest
    @ValueSource(strings = {
            "Celestyn",
            "JULIUSZ",
            "Liwia",
            "Oktawia",
            "wladyslawa",
            "leopold"
    })
    private void StringValidationShouldNotThrow(String string){
        assertDoesNotThrow(() -> InputValidation.validateString(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "6elestyn",
            "123456789",
            "?tawia",
            "leopold6789",
            "............",
            "JUL1USZ"
    })
    private void StringValidationShouldThrow(String string){
        assertThrows(IllegalArgumentException.class ,() -> InputValidation.validateString(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "0",
            "123456789",
            "00001",
            "100000000"
    })
    private void IntValidationShouldNotThrow(String string){
        assertDoesNotThrow(() -> InputValidation.validateInt(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "..............",
            "-123456789",
            "0.123141",
            "JUL1USZ"
    })
    private void IntValidationShouldThrow(String string){
        assertThrows(IllegalArgumentException.class ,() -> InputValidation.validateInt(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "69082198128",
            "79122497827",
            "67041674645",
            "76060646179",
            "94081749693",
            "94091192577",
            "61122287511",
            "72010324634",
            "69082313475",
            "76082999925"
    })
    private void PeselValidationShouldNotThrow(String string){
        assertDoesNotThrow(() ->InputValidation.validatePesel(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "00001000000",
            "11111111111",
            "0",
            "33333333333",
            "4444444444",
            "55555555555",
            "16666666666",
            "77777777777",
            "888888888888",
            "99999999999"
    })
    private void PeselValidationShouldThrow(String string) {
        assertThrowsExactly(IllegalArgumentException.class, () -> InputValidation.validatePesel(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "111-111-22-111",
            "222-123-67-076",
            "727-255-14-900",
            "567-284-11-889"
    })
    private void CardValidationShouldNotThrow(String string){
        assertDoesNotThrow(() -> InputValidation.validateCard(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "0",
            "11-111-111-111",
            "123-443-123-451",
            "133?311-33-665"
    })
    private void CardValidationShouldThrow(String string){
        assertThrows(IllegalArgumentException.class ,() -> InputValidation.validateCard(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123123123",
            "123456789",
            "111111111",
            "999999999"
    })
    private void PhoneValidationShouldNotThrow(String string){
        assertDoesNotThrow(() -> InputValidation.validatePhone(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "12345678",
            "-12345678",
            "123f56789",
            "abc456def"
    })
    private void PhoneValidationShouldThrow(String string){
        assertThrows(IllegalArgumentException.class ,() -> InputValidation.validatePhone(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1",
            "10",
            "50",
            "100"
    })
    private void CommissionValidationShouldNotThrow(String string){
        assertDoesNotThrow(() -> InputValidation.validateCommission(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "0",
            "-13",
            "150",
            "ad1",
            "10S"
    })
    private void CommissionValidationShouldThrow(String string){
        assertThrows(IllegalArgumentException.class ,() -> InputValidation.validateCommission(string));
    }
}
