package contacts;

public class Main {
    public static void main(String[] args) {

        FileController fileController = new FileController();
//        fileController.createFile(args[0]);
        App app = new App();
//        app.load();
        app.mainMenu();
    }
}
