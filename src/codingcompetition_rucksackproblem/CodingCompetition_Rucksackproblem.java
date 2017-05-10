/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingcompetition_rucksackproblem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Starts the Application
 * @author adlerlu
 */
public class CodingCompetition_Rucksackproblem extends Application {
    
    /**
     * Starter
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/graphic/FXMLMainLayout.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("The Knapsack Problem");
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/basic/pictures/Rucksack.png")));
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
