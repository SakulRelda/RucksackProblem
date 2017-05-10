/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import basic.classes.Knapsack;
import basic.helpers.SqlHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 * FXML Controller Class which handels all the Events which are triggered by creating a new Knapsack 
 *
 * @author adlerlu
 */
public class FXMLKnapsackController implements Initializable {

    @FXML
    public Button btnKSpeichern;

    @FXML
    public Button btnClose;

    @FXML
    public TextField txtGewicht;

    @FXML
    public TextField txtName;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Saves a Knapsack to the Database
     */
    @FXML
    public void saveKnapsack() {
        if (!txtGewicht.getText().isEmpty() && !txtName.getText().isEmpty()) {
            try {
                SqlHelper sql = SqlHelper.getInstance();
                Knapsack k = new Knapsack(Integer.parseInt(txtGewicht.getText()), txtName.getText());
                sql.insertKnapsack(k);
                closeWindow();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Das Gewicht muss eine ganze Zahl sein!");
            alert.showAndWait();    
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Bitte Felder befüllen!");
            alert.showAndWait();
        }
    }

    /**
     * Closes the Window
     */
    @FXML
    public void closeWindow() {
        Window thisStage = btnClose.getScene().getWindow();
        thisStage.hide();
    }

}
