package contacts;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBook {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Contact> contacts = new ArrayList<>();

    public void startApp() {
        while (true) {
            switch (enterAction()) {
                case "add" -> addContact();
                case "remove" -> removeContact();
                case "edit" -> editContact();
                case "count" -> countContacts();
                case "list" -> printContacts();
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Wrong action!");
            }
        }
    }

    private String enterAction() {
        System.out.print("Enter action (add, remove, edit, count, list, exit): ");
        return scanner.nextLine();
    }

    private void addContact() {
        Contact contact = new Contact();
        System.out.print("Enter the name of the person: ");
        contact.setName(scanner.nextLine());
        System.out.print("Enter the surname of the person: ");
        contact.setSurname(scanner.nextLine());
        System.out.print("Enter the number: ");
        String number = scanner.nextLine();
        if (hasCorrectFormat(number)) {
            contact.setPhone(number);
        } else {
            System.out.println("Wrong number format!");
            contact.setPhone("[no number]");
        }
        System.out.println("The record added.");
        contacts.add(contact);
    }

    private void removeContact() {
        if (contacts.size() == 0) {
            System.out.println("No records to remove!");
        } else {
            printContacts();
            System.out.print("Select a record: ");
            contacts.remove(Integer.parseInt(scanner.nextLine()) - 1);
            System.out.println("The record removed!");
        }
    }

    private void editContact() {
        if (contacts.size() == 0) {
            System.out.println("No records to edit!");
        } else {
            printContacts();
            System.out.print("Select a record: ");
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            System.out.print("Select a field (name, surname, number): ");
            String field = scanner.nextLine();
            switch (field) {
                case "name" -> {
                    System.out.print("Enter name: ");
                    contacts.get(index).setName(scanner.nextLine());
                }
                case "surname" -> {
                    System.out.print("Enter surname: ");
                    contacts.get(index).setSurname(scanner.nextLine());
                }
                case "number" -> {
                    System.out.print("Enter number: ");
                    String number = scanner.nextLine();
                    if (hasCorrectFormat(number)) {
                        contacts.get(index).setPhone(number);
                    } else {
                        System.out.println("Wrong number format!");
                        contacts.get(index).setPhone("[no number]");
                    }
                }
                default -> {
                    System.out.println("Wrong field!");
                    return;
                }
            }
            System.out.println("The record updated!");
        }
    }

    private void countContacts() {
        System.out.println("The Phone Book has " + contacts.size() + " records.");
    }

    private void printContacts() {
        for (Contact item : contacts) {
            System.out.println((contacts.indexOf(item) + 1) + ". " + item.getName() + " " + item.getSurname() + ", "+ item.getPhone());
        }
    }

    private boolean hasNumber() {
        return true;
    }

    private boolean hasCorrectFormat(String number) {
        return number.matches("\\+?(\\([0-9a-zA-Z]+\\)|[0-9a-zA-Z]+([ -][(][0-9a-zA-Z]{2,}[)])?)([ -][0-9a-zA-Z]{2,})*");
    }
}
