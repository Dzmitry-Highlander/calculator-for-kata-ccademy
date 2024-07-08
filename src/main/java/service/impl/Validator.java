package service.impl;

import service.api.IValidator;
import service.enums.EErrors;
import service.enums.ENotations;
import service.exception.ValidationException;

import java.util.Arrays;
import java.util.Objects;

public class Validator implements IValidator {
    private final String[] arabicArray = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private final String[] romanArray = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    private final String[] operandsArray = new String[] {"+", "-", "*", "/"};

    @Override
    public String[] validation(String input) {
        String[] validated = new String[2];

        String[] validationArray = input.toUpperCase().split(" ");

        if (validationArray.length != 3) {
            error();
        }

        for (String number : arabicArray) {
            if (Objects.equals(validationArray[0], number)) {
                validated[1] = ENotations.ARABIC.getNotation();

                break;
            }
        }

        for (String number : romanArray) {
            if (Objects.equals(validationArray[0], number)) {
                validated[1] = ENotations.ROMAN.getNotation();

                break;
            }
        }

        if (Objects.equals(validated[1], ENotations.ARABIC.getNotation())) {
            validated[1] = ENotations.ERROR.getNotation();

            for (String number : arabicArray) {
                if (Objects.equals(validationArray[2], number)) {
                    validated[1] = ENotations.ARABIC.getNotation();

                    break;
                }
            }

            if (Objects.equals(validated[1], ENotations.ARABIC.getNotation())) {
                validated[0] = Arrays.toString(validationArray);
            } else {
                error();
            }

            validated[1] = ENotations.ERROR.getNotation();

            for (String operand : operandsArray) {
                if (Objects.equals(validationArray[1], operand)) {
                    validated[1] = ENotations.ARABIC.getNotation();

                    break;
                }
            }

            if (Objects.equals(validated[1], ENotations.ARABIC.getNotation())) {
                validated[0] = Arrays.toString(validationArray);
            } else {
                error();
            }
        } else if (Objects.equals(validated[1], ENotations.ROMAN.getNotation())) {
            validated[1] = ENotations.ERROR.getNotation();

            for (String number : romanArray) {
                if (Objects.equals(validationArray[2], number)) {
                    validated[1] = ENotations.ROMAN.getNotation();

                    break;
                }
            }

            if (Objects.equals(validated[1], ENotations.ROMAN.getNotation())) {
                validated[0] = Arrays.toString(validationArray);
            } else {
                error();
            }

            validated[1] = ENotations.ERROR.getNotation();

            for (String operand : operandsArray) {
                if (Objects.equals(validationArray[1], operand)) {
                    validated[1] = ENotations.ROMAN.getNotation();

                    break;
                }
            }

            if (Objects.equals(validated[1], ENotations.ROMAN.getNotation())) {
                validated[0] = Arrays.toString(validationArray);
            } else {
                error();
            }
        } else {
            error();
        }

        return validated;
    }

    private void error() {
        try {
            throw new ValidationException(EErrors.InputError.getMessage());
        } catch (ValidationException e) {
            System.out.println(e.getItem());

            System.exit(404);
        }
    }
}
