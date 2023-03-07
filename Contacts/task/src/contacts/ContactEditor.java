package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ContactEditor {
    Scanner scanner = new Scanner(System.in);
    Validator validator = new Validator();

    public void addContact(String type) {
        if (type.equals("person")) {
            addPerson();
        } else if (type.equals("organization")) {
            addOrganization();
        } else {
            System.out.println("Wrong action!");
        }
    }

    public void addPerson() {
        Person person = new Person();
        System.out.print("Enter the name: ");
        person.setName(scanner.nextLine());
        System.out.print("Enter the surname: ");
        person.setSurname(scanner.nextLine());
        System.out.print("Enter the birth date: ");
        String birthdate = scanner.nextLine();
        if (validator.isValidBirthDate(birthdate)) {
            person.setBirthdate(LocalDate.parse(birthdate));
        } else {
            System.out.println("Wrong date format!");
        }
        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine();
        if (validator.isValidGender(gender)) {
            person.setGender(gender);
        } else {
            System.out.println("Wrong gender format!");
            person.setGender("[no data]");
        }
        System.out.print("Enter the number: ");
        String phone = scanner.nextLine();
        if (validator.isValidPhone(phone)) {
            person.setPhone(phone);
        } else {
            System.out.println("Wrong number format!");
            person.setPhone("[no data]");
        }
        person.setTimeCreated(LocalDateTime.now().withSecond(0).withNano(0));
        person.setLastEdit(LocalDateTime.now().withSecond(0).withNano(0));
        App.contacts.add(person);
//        save(contacts);
        System.out.println("The record added.\n");
    }

    public void addOrganization() {
        Organization organization = new Organization();
        System.out.print("Enter the organization name: ");
        organization.setName(scanner.nextLine());
        System.out.print("Enter the address: ");
        organization.setAddress(scanner.nextLine());
        System.out.print("Enter the number: ");
        organization.setPhone(scanner.nextLine());
        organization.setTimeCreated(LocalDateTime.now().withSecond(0).withNano(0));
        organization.setLastEdit(LocalDateTime.now().withSecond(0).withNano(0));
        App.contacts.add(organization);
        System.out.println("The record added.\n");
    }

    public void editContact(Contact contact) {
        if (App.contacts.size() == 0) {
            System.out.println("No records to edit!");
        } else {
            if (App.contacts.get(App.contacts.indexOf(contact)).getType().equals("person")) {
                editPerson(contact);
            } else {
                editOrganization(contact);
            }
        }
    }

    public void editPerson(Contact contact) {
        Person person = (Person) contact;
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        switch (scanner.nextLine()) {
            case "name" -> {
                System.out.print("Enter name: ");
                person.setName(scanner.nextLine());
            }
            case "surname" -> {
                System.out.print("Enter surname: ");
                person.setSurname(scanner.nextLine());
            }
            case "birth" -> {
                System.out.print("Enter birth date: ");
                String birthdate = scanner.nextLine();
                if (validator.isValidBirthDate(birthdate)) {
                    person.setBirthdate(LocalDate.parse(birthdate));
                } else {
                    System.out.println("Wrong date format!");
                }
            }
            case "gender" -> {
                System.out.print("Enter gender: ");
                String gender = scanner.nextLine();
                if (validator.isValidGender(gender)) {
                    person.setGender(gender);
                } else {
                    System.out.println("Wrong gender format!");
                    person.setGender("[no data]");
                }
            }
            case "number" -> {
                System.out.print("Enter number: ");
                String phone = scanner.nextLine();
                if (validator.isValidPhone(phone)) {
                    person.setPhone(phone);
                } else {
                    System.out.println("Wrong number format!");
                    person.setPhone("[no data]");
                }
            }
            default -> {
                System.out.println("Wrong field!");
                return;
            }
        }
        person.setLastEdit(LocalDateTime.now().withSecond(0).withNano(0));
        System.out.println("The record updated.\n");
    }

    public void editOrganization(Contact contact) {
        Organization organization = (Organization) contact;
        System.out.print("Select a field (name, address, number): ");
        switch (scanner.nextLine()) {
            case "name" -> {
                System.out.print("Enter name: ");
                organization.setName(scanner.nextLine());
            }
            case "address" -> {
                System.out.print("Enter address: ");
                organization.setAddress(scanner.nextLine());
            }
            case "number" -> {
                System.out.println("Enter number: ");
                String phone = scanner.nextLine();
                if (validator.isValidPhone(phone)) {
                    organization.setPhone(phone);
                } else {
                    System.out.println("Wrong number format!");
                    organization.setPhone("[no data]");
                }
            }
            default -> {
                System.out.println("Wrong field!");
                return;
            }
        }
        organization.setLastEdit(LocalDateTime.now().withSecond(0).withNano(0));
        System.out.println("The record updated.\n");
    }

    public void removeContact(Contact contact) {
        if (App.contacts.size() == 0) {
            System.out.println("No records to remove!");
        } else {
            App.contacts.remove(contact);
            System.out.println("The record removed!\n");
        }
    }
}
