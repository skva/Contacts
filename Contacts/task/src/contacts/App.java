package contacts;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Contact> contacts = new ArrayList<>();

    public void startApp() {
        while (true) {
            switch (enterAction()) {
                case "add" -> addContact(enterType());
                case "remove" -> removeContact();
                case "edit" -> editContact();
                case "count" -> countContacts();
                case "info" -> {
                    printContacts();
                    printInfo();
                }
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Wrong action!");
            }
        }
    }

    private String enterAction() {
        System.out.print("Enter action (add, remove, edit, count, info, exit): ");
        return scanner.nextLine();
    }

    private String enterType() {
        System.out.print("Enter the type (person, organization): ");
        return scanner.nextLine();
    }

    private void addContact(String type) {
        if (type.equals("person")) {
            addPerson();
        } else if (type.equals("organization")) {
            addOrganization();
        } else {
            System.out.println("Wrong action!");
        }
    }

    private void addPerson() {
        Person person = new Person();
        System.out.print("Enter the name: ");
        person.setName(scanner.nextLine());
        System.out.print("Enter the surname: ");
        person.setSurname(scanner.nextLine());
        System.out.print("Enter the birth date: ");
        String birthdate = scanner.nextLine();
        if (isValidBirthDate(birthdate)) {
            person.setBirthdate(LocalDate.parse(birthdate));
        } else {
            System.out.println("Wrong date format!");
        }
        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine();
        if (isValidGender(gender)) {
            person.setGender(gender);
        } else {
            System.out.println("Wrong gender format!");
            person.setGender("[no data]");
        }
        System.out.print("Enter the number: ");
        String phone = scanner.nextLine();
        if (isValidPhone(phone)) {
            person.setPhone(phone);
        } else {
            System.out.println("Wrong number format!");
            person.setPhone("[no data]");
        }
        person.setTimeCreated(LocalDateTime.now().withSecond(0).withNano(0));
        person.setLastEdit(LocalDateTime.now().withSecond(0).withNano(0));
        contacts.add(person);
        System.out.println("The record added.\n");
    }

    private void addOrganization() {
        Organization organization = new Organization();
        System.out.print("Enter the organization name: ");
        organization.setName(scanner.nextLine());
        System.out.print("Enter the address: ");
        organization.setAddress(scanner.nextLine());
        System.out.print("Enter the number: ");
        organization.setPhone(scanner.nextLine());
        organization.setTimeCreated(LocalDateTime.now().withSecond(0).withNano(0));
        organization.setLastEdit(LocalDateTime.now().withSecond(0).withNano(0));
        contacts.add(organization);
        System.out.println("The record added.\n");
    }

    private void removeContact() {
        if (contacts.size() == 0) {
            System.out.println("No records to remove!");
        } else {
            printContacts();
            System.out.print("Select a record: ");
            contacts.remove(Integer.parseInt(scanner.nextLine()) - 1);
            System.out.println("The record removed!\n");
        }
    }

    private void editContact() {
        if (contacts.size() == 0) {
            System.out.println("No records to edit!");
        } else {
            printContacts();
            System.out.print("Select a record: ");
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (contacts.get(index).getType().equals("person")) {
                editPerson(contacts.get(index));
            } else {
                editOrganization(contacts.get(index));
            }
        }
    }

    private void editPerson(Contact contact) {
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
                if (isValidBirthDate(birthdate)) {
                    person.setBirthdate(LocalDate.parse(birthdate));
                } else {
                    System.out.println("Wrong date format!");
                }
            }
            case "gender" -> {
                System.out.print("Enter gender: ");
                String gender = scanner.nextLine();
                if (isValidGender(gender)) {
                    person.setGender(gender);
                } else {
                    System.out.println("Wrong gender format!");
                    person.setGender("[no data]");
                }
            }
            case "number" -> {
                System.out.print("Enter number: ");
                String phone = scanner.nextLine();
                if (isValidPhone(phone)) {
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

    private void editOrganization(Contact contact) {
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
                if (isValidPhone(phone)) {
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

    private void countContacts() {
        System.out.println("The Phone Book has " + contacts.size() + " records.");
    }

    private void printContacts() {
        for (Contact item : contacts) {
            if (item.getType().equals("person")) {
                System.out.println((contacts.indexOf(item) + 1) + ". " + item.getFullName());
            } else if (item.getType().equals("organization")) {
                System.out.println((contacts.indexOf(item) + 1) + ". " + item.getFullName());
            }
        }
    }

    private void printInfo() {
        System.out.print("Enter index to show info: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (contacts.get(index).getType().equals("person")) {
            System.out.println(contacts.get(index).toString());
        } else if (contacts.get(index).getType().equals("organization")) {
            System.out.println(contacts.get(index).toString());
        }
    }

    private boolean isValidPhone(String number) {
        return number.matches("\\+?(\\([0-9a-zA-Z]+\\)|[0-9a-zA-Z]+([ -][(][0-9a-zA-Z]{2,}[)])?)([ -][0-9a-zA-Z]{2,})*");
    }

    private boolean isValidBirthDate(String birthDate) {
        try {
            LocalDate.parse(birthDate);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

    private boolean isValidGender(String gender) {
        return gender.equals("M") || gender.equals("F");
    }
}
