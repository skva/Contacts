package contacts;

import java.io.*;
import java.util.ArrayList;

public class FileController {
    public static File file;

    public void createFile(String filename) {
        file = new File(filename);
        try {
            if (file.createNewFile()) {
                System.out.println("open " + filename);
            } else {
                load(filename);
                System.out.println("open " + filename);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(ArrayList contacts) {
        if (FileController.file != null) {
            try (FileOutputStream fos = new FileOutputStream(FileController.file.getName());
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(contacts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void load(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            App.contacts = (ArrayList) ois.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
