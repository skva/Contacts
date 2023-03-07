package contacts;

public class Main {
    public static void main(String[] args) {

        FileController fileController = new FileController();
        if (args.length > 1) {
            fileController.createFile(args[0]);
        }

        App app = new App();
        app.mainMenu();
    }
}
