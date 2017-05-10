/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import basic.classes.KnapsackElement;
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
 * FXML Controller Class which handels all the Events which are triggered by creating a new Knapsack Element
 *
 * @author adlerlu
 */
public class FXMLKnapsackElementController implements Initializable {

    @FXML
    public Button btnClose;

    @FXML
    public Button btnKESave;

    @FXML
    public TextField txtGewicht;

    @FXML
    public TextField txtWert;

    @FXML
    public TextField txtName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Saves a Knapsack Element
     */
    @FXML
    public void saveKnapsackElement() {
        if (!txtGewicht.getText().isEmpty() && !txtName.getText().isEmpty() && !txtWert.getText().isEmpty()) {
            try {
                SqlHelper sql = SqlHelper.getInstance();
                KnapsackElement kElem = new KnapsackElement(txtName.getText(), Integer.parseInt(txtGewicht.getText()), Integer.parseInt(txtWert.getText()));
                sql.insertKnapsackElement(kElem);
                closeWindow();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Gewicht + Wert müssen ganze Zahlen sein!");
                alert.showAndWait();
                
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Bitte Felder befüllen!");
            alert.showAndWait();
        }
    }

    /**
     * Close Window
     */
    @FXML
    public void closeWindow() {
        Window thisStage = btnClose.getScene().getWindow();
        thisStage.hide();
    }

}
