package contacts;

import java.util.ArrayList;

public class Printer {

    public void countContacts(ArrayList<Contact> contacts) {
        System.out.println("The Phone Book has " + contacts.size() + " records.\n");
    }

    public void printContacts(ArrayList<Contact> contacts) {
        for (Contact item : contacts) {
            if (item.getType().equals("person")) {
                System.out.println((contacts.indexOf(item) + 1) + ". " + item.getFullName());
            } else if (item.getType().equals("organization")) {
                System.out.println((contacts.indexOf(item) + 1) + ". " + item.getFullName());
            }
        }
        System.out.println();
    }

    public void printInfo(Contact contact) {
        System.out.println(contact.toString());
    }
}
