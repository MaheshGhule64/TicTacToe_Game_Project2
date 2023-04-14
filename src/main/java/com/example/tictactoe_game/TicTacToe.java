package com.example.tictactoe_game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {

    private int playerXScore = 0, playerOScore = 0;
     private boolean playerXTurn = true;
    private Label playerXScoreLabel, playerOScoreLabel;
    private Button [][]buttons = new Button[3][3];
    private BorderPane createContent(){
        BorderPane root = new BorderPane();
        // title
        Label titleLabel = new Label("Tic Tac Toe");
        titleLabel.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold;");

        root.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);


        // game Board

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                Button button = new Button("");
                button.setPrefSize(100, 100);
                button.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold;");
                button.setOnAction(event->buttonClicked(button));
                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }
        }

        root.setCenter(gridPane);
        gridPane.setAlignment(Pos.CENTER);

        // score
        HBox scoreBoard = new HBox(20);
        playerXScoreLabel = new Label("player X : 0");
        playerXScoreLabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");
        playerOScoreLabel = new Label("player O : 0");
        playerOScoreLabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");
        scoreBoard.getChildren().addAll(playerXScoreLabel, playerOScoreLabel);

        root.setBottom(scoreBoard);
        scoreBoard.setAlignment(Pos.CENTER);
        return root;
    }

    private void buttonClicked(Button button){
        if(button.getText().equals("")){
            if(playerXTurn){
                button.setText("X");
            }
            else{
                button.setText("O");
            }
            playerXTurn = !playerXTurn;
            checkWinner();
        }
    }

    private void checkWinner(){
        // row
        for(int row=0; row<3; row++){
            if(buttons[row][0].getText().equals(buttons[row][1].getText()) && buttons[row][1].getText().equals(buttons[row][2].getText())
                    && !buttons[row][0].getText().isEmpty()){
                // winner
                String winner = buttons[row][0].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        // col
        for(int col=0; col<3; col++){
            if(buttons[0][col].getText().equals(buttons[1][col].getText()) && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty()){
                // winner
                String winner = buttons[0][col].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        // Diagonal

        if(buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) && !buttons[0][0].getText().isEmpty()){
            String winner = buttons[0][0].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

        if(buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()) && !buttons[2][0].getText().isEmpty()){
            String winner = buttons[2][0].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

        boolean flag = true;
        for(Button [] row : buttons){
            for(Button button : row){
                if(button.getText().isEmpty()){
                    flag = false;
                    break;
                }
            }
        }
        if(flag==true){
            showTieDialog();
            resetBoard();
            return;
        }
    }

    private void updateScore(String winner){
        if(winner=="X"){
            playerXScore++;
            playerXScoreLabel.setText("Player X : " + playerXScore);
        }
        else{
            playerOScore++;
            playerOScoreLabel.setText("player O : " + playerOScore);
        }
    }
    private void resetBoard(){
        for(Button [] row : buttons){
            for(Button button : row){
                button.setText("");
            }
        }
    }

    private void showWinnerDialog(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Congrutulation! " + winner + "  you won the Game");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void showTieDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Game Over! It's a Tie");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Let's Play");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}