package data;

import com.main.LibrarySystem;
import util.iMenu;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Random;

public class Admin extends User implements iMenu {

    //=================================== ATRIBUT =====================================
    public static String adminusername = "admin";
    public static String adminpassword = "admin";

    //=================================== Main & Start Method ==================================

    //======================================= MENU Method =======================================
    @Override
    public void menu() {
        Stage adminMenuStage = new Stage();
        adminMenuStage.setTitle("UMM Library - Admin Menu");

        // Label
        Label sceneTitle = new Label("Menu Admin");

        // Font Style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        // Font Color
        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");

        // Button
        Button addStudentButton = new Button("Tambah Mahasiswa");
        Button displayStudentButton = new Button("Daftar Mahasiswa");
        Button addBookButton = new Button("Tambah Buku");
        Button displayBookButton = new Button("Daftar Buku");
        Button logoutButton = new Button("Logout");
        Button backButton = new Button("Back"); // Tambahkan tombol "Back"

        // Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(sceneTitle, 0, 1);

        grid.add(addStudentButton, 2, 0);
        grid.add(displayStudentButton, 2, 1);
        grid.add(addBookButton, 2, 2);
        grid.add(displayBookButton, 2, 3);
        grid.add(logoutButton, 2, 4);

        grid.setVgap(10);
        grid.setHgap(6);

        Scene scene = new Scene(grid, 1360, 720);
        adminMenuStage.setScene(scene);
        adminMenuStage.show();

        // Action Button
        addStudentButton.setOnAction(event -> {
            addstudent();
            adminMenuStage.close();
        });

        displayStudentButton.setOnAction(event -> {
            displaystudent();
            adminMenuStage.close();
        });

        addBookButton.setOnAction(event -> {
            inputBook();
            adminMenuStage.close();
        });

        logoutButton.setOnAction(event -> {
            LibrarySystem librarySystemObj = new LibrarySystem();
            librarySystemObj.start(new Stage());
            adminMenuStage.close();
        });

    }

    //===================================== Other Method =======================================
    public void addstudent() {

        // Membuat form baru
        Stage addStudentStage = new Stage();
        addStudentStage.setTitle("Tambah Mahasiswa");

        // Label
        Label sceneTitle = new Label("Tambah Mahasiswa");
        Label nameLabel = new Label("Nama");
        Label nimLabel = new Label("NIM");
        Label fakultasLabel = new Label("Fakultas");
        Label jurusanLabel = new Label("Jurusan");

        // Notification Label
        Label submitFailed = new Label("NIM harus 15 digit!");
        submitFailed.setVisible(false);

        // Field
        TextField nameField = new TextField();
        TextField nimField = new TextField();
        TextField fakultasField = new TextField();
        TextField jurusanField = new TextField();

        // Font Style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        nameLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        nimLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        fakultasLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        jurusanLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));

        // Font Color
        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");
        submitFailed.setStyle("-fx-text-fill: #FF1E1E;");

        // Button
        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back");

        // Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(sceneTitle, 0, 0);

        grid.add(nameLabel, 0, 1);
        grid.add(nimLabel, 0, 2);
        grid.add(fakultasLabel, 0, 3);
        grid.add(jurusanLabel, 0, 4);

        grid.add(nameField, 1, 1);
        grid.add(nimField, 1, 2);
        grid.add(fakultasField, 1, 3);
        grid.add(jurusanField, 1, 4);

        grid.add(submitButton, 1, 5);
        grid.add(backButton, 1, 6);  // Tambahkan tombol kembali

        grid.add(submitFailed, 0, 5);

        grid.setVgap(10);
        grid.setHgap(5);

        Scene scene = new Scene(grid, 1360, 720);
        addStudentStage.setScene(scene);
        addStudentStage.show();

        // Action Button
        submitButton.setOnAction(event -> {
            if (nimField.getText().length() == 15) {
                Admin adminObj = new Admin();

                Student.arr_userStudent.add(new Student.UserStudent(nameField.getText(), nimField.getText(), fakultasField.getText(), jurusanField.getText()));
                adminObj.menu();
                addStudentStage.close();

            } else {
                submitFailed.setVisible(true);
            }
        });

        backButton.setOnAction(event -> {
            menu();  // Kembali ke menu admin
            addStudentStage.close();
        });
    }

    public void displaystudent() {
        // Membuat stage baru
        Stage displayStudentStage = new Stage();
        displayStudentStage.setTitle("Libra Ease - Library Services");

        // Label
        Label sceneTitle = new Label("Library Services");

        // Font Style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        // Font Color
        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");

        // Membuat ListView untuk menampilkan mahasiswa
        ListView<String> listView = new ListView<>();

        for (Student.UserStudent i : Student.arr_userStudent) {
            String studentInfo = "Nama     : " + i.nama + "\n" +
                    "NIM      : " + i.nim + "\n" +
                    "Fakultas : " + i.fakultas + "\n" +
                    "Prodi    : " + i.prodi + "\n" +
                    "===========================";
            listView.getItems().add(studentInfo);
        }

        // Button
        Button backButton = new Button("Back");

        // Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(sceneTitle, 0, 0);
        grid.add(listView, 0, 1);
        grid.add(backButton, 0, 2); // Posisi tombol "Back" diubah ke (0, 2)
        grid.setVgap(5);

        Scene scene = new Scene(grid, 1360, 720);
        displayStudentStage.setScene(scene);
        displayStudentStage.show();

        // Action Button untuk "Back"
        backButton.setOnAction(event -> {
            menu(); // Kembali ke menu admin
            displayStudentStage.close(); // Menutup stage saat ini
        });
    }

    @Override
    public void inputBook() {
        super.inputBook();
    }

    @Override
    public void choiceBooks(Stage stage) {
        // Implement the choiceBooks method here
        // This method needs to be implemented as it is abstract in the User class
        // Add the appropriate functionality for the admin to choose books
    }

    public String generateId() {
        Random random = new Random();

        StringBuilder idTengah = new StringBuilder();
        StringBuilder idAkhir = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            idTengah.append(random.nextInt(10));
            idAkhir.append(random.nextInt(10));
        }
        return ("UMM-" + idTengah + "-" + idAkhir);
    }

}
