package data;

import books.Book;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import util.iMenu;

import java.util.ArrayList;

public class Student extends User implements iMenu {
    public static ArrayList<UserStudent> arr_userStudent = new ArrayList<>();
    private Stage previousStage;

    public Student() {
        // Default constructor
    }

    public Student(Stage previousStage) {
        this.previousStage = previousStage;
    }

    // Constructor for arraylist arr_userStudent.
    public static class UserStudent {
        String nama, nim, fakultas, prodi;

        public UserStudent(String nama, String nim, String fakultas, String prodi) {
            this.nama = nama;
            this.nim = nim;
            this.fakultas = fakultas;
            this.prodi = prodi;
        }
    }

    @Override
    public void menu() {
        Stage studentMenuStage = new Stage();
        studentMenuStage.setTitle("Libra Ease - Self Services");

        //Label
        Label sceneTitle = new Label("Self Services");

        //Font style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        //Font color
        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");

        //Button
        Button borrowedBookButton = new Button("Buku Terpinjam");
        Button borrowBookButton = new Button("Pinjam Buku");
        Button returnBookButton = new Button("Kembalikan Buku");
        Button backButton = new Button("Back");

        //Grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(sceneTitle, 0, 1);

        grid.add(borrowedBookButton, 2, 1);
        grid.add(borrowBookButton, 2, 2);
        grid.add(returnBookButton, 2, 3);
        grid.add(backButton, 2, 4); // Add back button

        grid.setVgap(10);
        grid.setHgap(5);

        Scene studentMenuScene = new Scene(grid, 1360, 720);
        studentMenuStage.setScene(studentMenuScene);
        studentMenuStage.show();

        //Action button
        borrowedBookButton.setOnAction(event -> {
            showBorrowedBooks(studentMenuStage);
            studentMenuStage.close();
        });

        borrowBookButton.setOnAction(event -> {
            choiceBooks(studentMenuStage);
            studentMenuStage.close();
        });

        returnBookButton.setOnAction(event -> {
            returnBooks(studentMenuStage);
            studentMenuStage.close();
        });

        backButton.setOnAction(event -> {
            if (previousStage != null) {
                previousStage.show(); // Menampilkan kembali stage admin atau stage sebelumnya
            }
            studentMenuStage.close(); // Tutup menu siswa
        });

    }

        @Override
    public void choiceBooks(Stage previousStage) {
        super.choiceBooks();
    }

    public void showBorrowedBooks(Stage previousStage) {
        Stage showBorrowedBooksStage = new Stage();
        showBorrowedBooksStage.setTitle("Informasi Buku Yang Dipinjam");

        TableView<Book> table = new TableView<>();
        table.setPrefSize(1360, 720);

        TableColumn<Book, String> idColumn = new TableColumn<>("ID Buku");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, String> titleColumn = new TableColumn<>("Nama Buku");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Penulis");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> categoryColumn = new TableColumn<>("Kategori");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Book, Integer> durationColumn = new TableColumn<>("Durasi");
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        table.getColumns().add(idColumn);
        table.getColumns().add(titleColumn);
        table.getColumns().add(authorColumn);
        table.getColumns().add(categoryColumn);
        table.getColumns().add(durationColumn);

        for (Book a : Book.arr_borrowedBook) {
            for (Book i : Book.arr_bookList) {
                if (i.getBookId().equals(a.getBookId())) {
                    table.getItems().add(i);
                }
            }
        }

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(table, 0, 0); // Add the table to GridPane

        Button backButton = new Button("Back");
        gridPane.add(backButton, 0, 1);

        gridPane.setVgap(10);
        gridPane.setHgap(5);

        Scene scene = new Scene(gridPane, 1360, 720);
        showBorrowedBooksStage.setScene(scene);
        showBorrowedBooksStage.show();

        backButton.setOnAction(event -> {
            previousStage.show();
            showBorrowedBooksStage.close();
        });
    }

    public void returnBooks(Stage previousStage) {

        Stage returnBooksStage = new Stage();
        returnBooksStage.setTitle("UMM Library - Pengembalian Buku");

        //Label
        Label headerTitle = new Label("Pengembalian Buku");
        Label bookIdLabel = new Label("Inputkan ID buku yang ingin dikembalikan");

        //Notification Label
        Label submitSuccesLabel = new Label("Pengembalian berhasil");
        Label submitFailedLabel = new Label("Pengembalian gagal");

        //Font Style
        headerTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        bookIdLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));

        //Font Color
        headerTitle.setStyle("-fx-text-fill: #A91D3A;");
        submitSuccesLabel.setStyle("-fx-text-fill: #16FF00;");
        submitFailedLabel.setStyle("-fx-text-fill: #FF1E1E;");

        //Notification label settings
        submitSuccesLabel.setVisible(false);
        submitFailedLabel.setVisible(false);

        //Field
        TextField bookIdField = new TextField();

        //Button
        Button submitButton = new Button("Kembalikan");
        Button returnButton = new Button("Back");

        //Table label
        TableView<Book> tableView = new TableView<>();

        TableColumn<Book, String> idBookColumn = new TableColumn<>("ID Buku");
        TableColumn<Book, String> titleBookColumn = new TableColumn<>("Judul");
        TableColumn<Book, String> authorBookColumn = new TableColumn<>("Author");
        TableColumn<Book, String> categoryBookColumn = new TableColumn<>("Kategori");
        TableColumn<Book, String> durationBookColumn = new TableColumn<>("Durasi Pinjaman (Hari)");

        //Table fill
        idBookColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleBookColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorBookColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        categoryBookColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        durationBookColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        //Add books to the table
        for (Book i : Book.arr_borrowedBook) {
            tableView.getItems().add(i);
        }

        //Grid layout
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);

        grid.add(headerTitle, 2, 0);
        grid.add(tableView, 0, 1);

        grid.add(bookIdLabel, 0, 2);
        grid.add(bookIdField, 0, 3);

        grid.add(submitSuccesLabel, 5, 2);
        grid.add(submitFailedLabel, 5, 2);

        grid.add(submitButton, 4, 4);
        grid.add(returnButton, 0, 4);

        Scene returnBookScene = new Scene(grid, 1360, 720);
        returnBooksStage.setScene(returnBookScene);
        returnBooksStage.show();

        //Action button
        submitButton.setOnAction(event -> {

            boolean validasiReturnBooks = false;
            //Loop through arr_borrowedBook
            for (int i = 0; i < Book.arr_borrowedBook.size(); i++) {

                //Check if the book ID matches
                if (Book.arr_borrowedBook.get(i).getBookId().equals(bookIdField.getText())) {
                    for (Book book : Book.arr_bookList) {
                        if (book.getBookId().equals(bookIdField.getText())) {

                            int returnBook = book.getStock();   //Get current stock
                            returnBook++;                       //Increment stock by 1
                            book.setStock(returnBook);          //Update stock in arr_bookList

                            Book.arr_borrowedBook.remove(i);    //Remove book from arr_borrowedBook
                            validasiReturnBooks = true;
                            submitSuccesLabel.setVisible(true);  //Set notification success to true
                            submitFailedLabel.setVisible(false); //Set notification failed to false
                            break;
                        }
                    }
                }
            }
            if (!validasiReturnBooks) {
                submitFailedLabel.setVisible(true);
                submitSuccesLabel.setVisible(false);
            }
        });

        returnButton.setOnAction(event -> {
            previousStage.show();
            returnBooksStage.close();
        });
    }
}
