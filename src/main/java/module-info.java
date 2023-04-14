module com.example.tictactoe_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictactoe_game to javafx.fxml;
    exports com.example.tictactoe_game;
}