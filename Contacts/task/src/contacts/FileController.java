package contacts;

import java.io.File;
import java.io.IOException;

public class FileController {
    private static File file;
    public static String name;
    public void createFile(String filename) {
        if (filename != null) {
            file = new File(filename);
            try {
                if (file.createNewFile()) {

                } else {
                    // TODO load from file

                    System.out.println("open " + filename);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file = null;
        }
        name = filename;
    }
}
