package contacts;

import java.time.LocalDate;

public class Person extends Contact{
    private String surname;
    private LocalDate birthdate;
    private String gender;

    public Person() {
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getType() {
        return "person";
    }

    @Override
    public String getFullName() {
        return getName() + " " + getSurname();
    }

    @Override
    public String toString() {
        String birthDate = getBirthdate() == null ? "[no data]" : getBirthdate().toString();
        return "Name: " + getName() + "\n"
                + "Surname: " + getSurname() + "\n"
                + "Birth date: " + birthDate + "\n"
                + "Gender: " + getGender() + "\n"
                + "Number: " + getPhone() + "\n"
                + "Time created: " + getTimeCreated() + "\n"
                + "Time last edit: " + getLastEdit() + "\n";
    }
}
