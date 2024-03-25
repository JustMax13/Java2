package com.example.lab2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller {
    @FXML
    private AnchorPane background;
    @FXML
    private TextField firstLatitude, secondLatitude, firstLongitude, secondLongitude, resultValue;
    @FXML
    private Button clearButton, solveButton;

    @FXML
    public void initialize(){
        // Змінюємо кольори:
        background.setStyle("-fx-background-color: #353535;");

        firstLatitude.setStyle("-fx-background-color: #454444;");
        secondLatitude.setStyle("-fx-background-color: #454444;");
        firstLongitude.setStyle("-fx-background-color: #454444;");
        secondLongitude.setStyle("-fx-background-color: #454444;");
        resultValue.setStyle("-fx-background-color: #454444;");

        clearButton.setStyle("-fx-background-color: #2e2d2d;");
        solveButton.setStyle("-fx-background-color: #2e2d2d;");

        clearButton.setOnAction(e -> clear());
        solveButton.setOnAction(e -> solve());
    }

    public void clear(){
        firstLatitude.setText("");
        secondLatitude.setText("");
        firstLongitude.setText("");
        secondLongitude.setText("");
        resultValue.setText("");
    }
    public void solve(){
        double lat1 = 0;
        double lat2 = 0;
        double lon1 = 0;
        double lon2 = 0;
        double earthRadius = 6371e3;

        try {
            lat1 = Double.parseDouble(firstLatitude.getText());
            lat2 = Double.parseDouble(secondLatitude.getText());
            lon1 = Double.parseDouble(firstLongitude.getText());
            lon2 = Double.parseDouble(secondLongitude.getText());
        }
        catch (Exception e){
            resultValue.setText("Помилка, не вірно введені дані");
            return;
        }

        double fi_1 = lat1 * ( Math.PI / 180 );
        double fi_2 = lat2 * ( Math.PI / 180 );

        double deltaFi = (lat2 - lat1) * ( Math.PI / 180 );
        double deltaLambda = (lon2 - lon1) * ( Math.PI / 180 );

        double a = Math.pow(Math.sin(deltaFi / 2), 2) + Math.cos(fi_1) * Math.cos(fi_2)
                * Math.pow(Math.sin(deltaLambda / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double D = earthRadius * c;
        double distanceInKm = (D / 1000);
        distanceInKm = Math.round(distanceInKm * 10000) / 10000.0;

        resultValue.setText(String.valueOf(distanceInKm) + " км");
    }
}