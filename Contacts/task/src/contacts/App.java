package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Contact> contacts = new ArrayList<>();
    Validator validator = new Validator();
    Printer printer = new Printer();
    public void mainMenu() {
        while (true) {
            switch (mainMenuAction()) {
                case "add" -> addContact(enterType());
                case "list" -> {
                    printer.printContacts(contacts);
                    listMenu();
                }
                case "search" -> searchContact();
                case "count" -> printer.countContacts(contacts);
                case "exit" -> {
                    System.exit(0);
                }
                default -> System.out.println("Wrong action!");
            }
        }
    }

    private String mainMenuAction() {
        System.out.print("[menu] Enter action (add, list, search, count, exit): ");
        return scanner.nextLine();
    }

    private void recordMenu(Contact contact) {
        System.out.print("[record] Enter action (edit, delete, menu): ");
        switch (scanner.nextLine()) {
            case "edit" -> editContact(contact);
            case "delete" -> {
                removeContact(contact);
                recordMenu(contact);
            }
            case "menu" -> mainMenu();
            default -> System.out.println("Wrong action!");
        }
    }

    private void listMenu() {
        System.out.print("Enter action ([number], back): ");
        String action = scanner.nextLine();
        Pattern digits = Pattern.compile("[0-9]");
        if (action.equals("back")) {
            mainMenu();
        } else if (digits.matcher(action).find()) {
            printer.printInfo(contacts.get(Integer.parseInt(action) - 1));
            recordMenu(contacts.get(Integer.parseInt(action) - 1));
        } else {
            System.out.println("Wrong action!");
        }
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
        contacts.add(person);
//        save(contacts);
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

    private void editContact(Contact contact) {
        if (contacts.size() == 0) {
            System.out.println("No records to edit!");
        } else {
            if (contacts.get(contacts.indexOf(contact)).getType().equals("person")) {
                editPerson(contact);
            } else {
                editOrganization(contact);
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

    private void removeContact(Contact contact) {
        if (contacts.size() == 0) {
            System.out.println("No records to remove!");
        } else {
            contacts.remove(contact);
            System.out.println("The record removed!\n");
        }
    }

    private void searchContact() {
        ArrayList<Contact> foundContacts = new ArrayList<>();
        System.out.print("Enter search query: ");
        String query = scanner.nextLine();
        for (Contact item : contacts) {
            if (item.getAllFields().contains(query.toLowerCase())) {
                foundContacts.add(item);
            }
        }
        System.out.println("Found " + foundContacts.size() + " results:");
        printer.printContacts(foundContacts);

        System.out.print("[search] Enter action ([number], back, again): ");
        String action = scanner.nextLine();
        Pattern digits = Pattern.compile("[0-9]");
        if (action.equals("back")) {
            return;
        } else if (action.equals("again")) {
            searchContact();
        } else if (digits.matcher(action).find()) {
            recordMenu(contacts.get(Integer.parseInt(action) - 1));
        } else {
            System.out.println("Wrong action!");
        }

        switch (scanner.nextLine()) {
            case "1" -> {
                System.out.println("Here will be search");
            }
            case "back" -> {
                return;
            }
            case "again" -> {
                searchContact();
            }
            default -> {
                System.out.println("Wrong action!");
                return;
            }
        }
        System.out.println("lol");
    }

//    public void save(ArrayList contacts) {
//        try (FileOutputStream fos = new FileOutputStream(FileController.name);
//             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//            oos.writeObject(contacts);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    // TODO
//    public void load() {
//        try (FileInputStream fis = new FileInputStream("phonebook");
//             ObjectInputStream ois = new ObjectInputStream(fis)) {
//            contacts = (ArrayList) ois.readObject();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
