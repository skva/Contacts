package contacts;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Validator {

    public boolean isValidPhone(String number) {
        return number.matches("\\+?(\\([0-9a-zA-Z]+\\)|[0-9a-zA-Z]+([ -][(][0-9a-zA-Z]{2,}[)])?)([ -][0-9a-zA-Z]{2,})*");
    }

    public boolean isValidBirthDate(String birthDate) {
        try {
            LocalDate.parse(birthDate);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

    public boolean isValidGender(String gender) {
        return gender.equals("M") || gender.equals("F");
    }
}
