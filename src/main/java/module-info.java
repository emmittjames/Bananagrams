module com.example.bananagrams {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bananagrams to javafx.fxml;
    exports com.example.bananagrams;
}