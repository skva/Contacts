package contacts;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    Scanner scanner = new Scanner(System.in);
    public static ArrayList<Contact> contacts = new ArrayList<>();
    Printer printer = new Printer();
    ContactEditor contactEditor = new ContactEditor();
    public void mainMenu() {
        while (true) {
            switch (mainMenuAction()) {
                case "add" -> contactEditor.addContact(enterType());
                case "list" -> {
                    printer.printContacts(contacts);
                    listMenu();
                }
                case "search" -> searchContact();
                case "count" -> printer.countContacts(contacts);
                case "exit" -> System.exit(0);
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
            case "edit" -> contactEditor.editContact(contact);
            case "delete" -> {
                contactEditor.removeContact(contact);
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
        if (!action.equals("back")) {
            if (action.equals("again")) {
                searchContact();
            } else if (digits.matcher(action).find()) {
                recordMenu(contacts.get(Integer.parseInt(action) - 1));
            } else {
                System.out.println("Wrong action!");
            }
        }
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
