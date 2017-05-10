/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import basic.algorithms.ApproximateAlgorithm;
import basic.algorithms.GreedyAlgorithm;
import basic.algorithms.ProfiIndexAlgorithm;
import basic.classes.Knapsack;
import basic.classes.KnapsackElement;
import basic.helpers.SqlHelper;
import basic.helpers.StandardHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller Class for the Central View 
 *
 * @author adlerlu
 */
public class FXMLMainLayoutController implements Initializable {

    @FXML
    private TableView tableKnapsackElements;

    @FXML
    private TableView tableKnapsack;

    @FXML
    private TableView tableKECalc;

    @FXML
    private TableView tableKCalc;

    @FXML
    private Button btnKEAdd;

    @FXML
    private Button btnKEDelete;
    
    @FXML
    private Button btnRandomKnapsackGenerator;
    
    @FXML
    private Button btnRandomElementGenerator;

    @FXML
    private Button btnKAdd;

    @FXML
    private Button btnKDelete;

    @FXML
    private Button btnCalculate;

    @FXML
    private ComboBox cmbAlgorithm;

    @FXML
    private TabPane mainTab;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComboBox();
        initTabs();
        refreshKnapsackElementTable(SqlHelper.getInstance());
    }
    
    /**
     * Button Click Event
     * Executes the Algorithm
     */
    @FXML
    public void execAlgorithm(){
        String cmbVal = cmbAlgorithm.getSelectionModel().getSelectedItem().toString();
        Knapsack getSack = (Knapsack) tableKCalc.getSelectionModel().getSelectedItem();
        Knapsack sack=new Knapsack(getSack.getMaximumMass(), getSack.getName());
        List<KnapsackElement> elements=tableKECalc.getSelectionModel().getSelectedItems();;
        switch(cmbVal){
            case "Greedy":
                GreedyAlgorithm greedy = new GreedyAlgorithm(sack,elements);
                sack = greedy.startAlgorithm();
                break;
            case "Index":
                ArrayList<KnapsackElement> ee = new ArrayList<>(elements);
                ProfiIndexAlgorithm index = new ProfiIndexAlgorithm(sack, ee);
                sack = index.startAlgorithm();
                break;
            case "Approximation":
                ApproximateAlgorithm approximation = new ApproximateAlgorithm(sack, elements);
                sack = approximation.startAlgorithm();
                break;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Lösung des "+cmbVal+" Algorithmus");
        alert.setHeaderText("Rucksack-Infos (Wert: "+sack.getWorthValue()+"; Maximales Gewicht: "+sack.getMaximumMass()+"; Freies Gewicht: "+sack.getFreeMass()+")");
        Label lbl = new Label("Gegenstände des Rucksacks: ");
        
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sack.getContainedElements().size(); i++) {
            KnapsackElement elem = sack.getContainedElements().get(i);
            String output = "Eingepackter Gegenstand: "+elem.getElementName()+ "(Wert: "+elem.getWorthValue() +"; Gewicht"+elem.getMassValue()+"; Profit Index"+elem.getProfitIndex()+")\n";
            builder.append(output);
        }   
        
        TextArea area = new TextArea(builder.toString());
        area.setEditable(false);
        area.setWrapText(true);
        area.setMaxHeight(Double.MAX_VALUE);
        area.setMaxWidth(Double.MAX_VALUE);
        GridPane.setVgrow(area, Priority.ALWAYS);
        GridPane.setHgrow(area, Priority.ALWAYS);
        
        GridPane elementStack = new GridPane();
        elementStack.setMaxWidth(Double.MAX_VALUE);
        elementStack.add(lbl, 0, 0);
        elementStack.add(area, 0, 1);
        
        alert.getDialogPane().setExpandableContent(elementStack);
        alert.showAndWait();
    }
    
    /**
     * Generates a Random Knapsack
     */
    @FXML
    public void generateRandomKnapsack(){
        SqlHelper sql = SqlHelper.getInstance();
        int randomMaxWeight = (int) (Math.random()*(1000-1))+1;
        Knapsack sack = new Knapsack(randomMaxWeight, "Autogen. Rucksack");
        sql.insertKnapsack(sack);
        refreshKnapsackTable(sql);
    }
    
    /**
     * Generates a Random Knapsack Element
     */
    @FXML
    public void generateRandomElement(){
        SqlHelper sql = SqlHelper.getInstance();
        int randomWort = (int) (Math.random()*(100-1))+1;
        int randomWeight = (int) (Math.random()*(100-1))+1;
        KnapsackElement element = new KnapsackElement("Autogen. Gegenstand", randomWeight, randomWort);
        sql.insertKnapsackElement(element);
        refreshKnapsackElementTable(sql);
    }

    /**
     * Button Click Event (Add Knapsack Element)
     */
    @FXML
    public void addKnapsackElement() {
        try{
            Stage mainStage = (Stage) btnKAdd.getScene().getWindow();
            //Create a new Parent Object with the Registry.fxml
            Parent p = FXMLLoader.load(getClass().getResource("/graphic/FXMLKnapsackElement.fxml"));
            Scene s = new Scene(p);
            //New Stage to Show the FXML
            Stage st = new Stage();
            //Deactivate Events from the MainWindow
            st.initModality(Modality.WINDOW_MODAL);
            //Unpack the StageStyle Header
            st.initStyle(StageStyle.TRANSPARENT);
            st.initOwner(mainStage);
            st.setScene(s);
            st.showAndWait();

            if (!st.isShowing()) {
                refreshKnapsackElementTable(SqlHelper.getInstance());
            }
        }catch(IOException ex){
            Logger.getLogger(FXMLMainLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Button Click Event (Add Knapsack)
     */
    @FXML
    public void addKnapsack() {
        try {
            Stage mainStage = (Stage) btnKAdd.getScene().getWindow();
            //Create a new Parent Object with the Registry.fxml
            Parent p = FXMLLoader.load(getClass().getResource("/graphic/FXMLKnapsack.fxml"));
            Scene s = new Scene(p);
            //New Stage to Show the FXML
            Stage st = new Stage();
            //Deactivate Events from the MainWindow
            st.initModality(Modality.WINDOW_MODAL);
            //Unpack the StageStyle Header
            st.initStyle(StageStyle.TRANSPARENT);
            st.initOwner(mainStage);
            st.setScene(s);
            st.showAndWait();

            if (!st.isShowing()) {
                refreshKnapsackTable(SqlHelper.getInstance());
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Button Click Event (Delete Knapsack Element)
     */
    @FXML
    public void deleteKnapsackElement() {
        KnapsackElement e = (KnapsackElement) tableKnapsackElements.getSelectionModel().getSelectedItem();
        if (e != null) {
            SqlHelper sql = SqlHelper.getInstance();
            sql.deleteInKnapsackElements(Integer.toString(e.getId()));
            StandardHelper.knapsackElements.remove(e);
            ObservableList<KnapsackElement> kElements = FXCollections.observableArrayList(StandardHelper.knapsackElements);
            tableKnapsackElements.setItems(kElements);
        }
    }

    /**
     * Button Click Event (Delete Knapsack)
     */
    @FXML
    public void deleteKnapsack() {
        Knapsack e = (Knapsack) tableKnapsack.getSelectionModel().getSelectedItem();
        if (e != null) {
            SqlHelper sql = SqlHelper.getInstance();
            sql.deleteInKnapsack(Integer.toString(e.getId()));
            StandardHelper.knapsacks.remove(e);
            ObservableList<Knapsack> knapsacks = FXCollections.observableList(StandardHelper.knapsacks);
            tableKnapsack.setItems(knapsacks);
        }
    }

    /**
     * Sets the possible Algorithms to the Combo Box
     */
    private void initComboBox() {
        ObservableList<String> algorithms
                = FXCollections.observableArrayList(
                        "Greedy",
                        "Index",
                        "Approximation"
                );
        cmbAlgorithm.setItems(algorithms);
        cmbAlgorithm.getSelectionModel().select(0);
    }

    /**
     * Initialize the Tabs and holds the values actually by adding a Item
     * Property Listener
     */
    private void initTabs() {

        tableKCalc.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableKECalc.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Maximize the Table to the full Width
        tableKCalc.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableKECalc.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableKnapsackElements.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableKnapsack.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        mainTab.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) -> {
            int index = mainTab.getSelectionModel().getSelectedIndex();
            SqlHelper sql = SqlHelper.getInstance();
            switch (index) {
                case 0:
                    refreshKnapsackElementTable(sql);
                    break;
                case 1:
                    refreshKnapsackTable(sql);
                    break;
                case 2:
                    refreshCalTables(sql);
                    break;
                case 3:
                    break;
            }
        });
    }

    /**
     * Handels the Refresh of the Knapsack Table
     *
     * @param sql
     */
    private void refreshKnapsackTable(SqlHelper sql) {
        StandardHelper.knapsacks = sql.getKnapsack();
        ObservableList<Knapsack> knapsacks = FXCollections.observableList(StandardHelper.knapsacks);
        tableKnapsack.setItems(knapsacks);
        TableColumn<Knapsack, String> nCol = new TableColumn<>("Name:");
        nCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Knapsack, Integer> maxMassCol = new TableColumn<>("Maximales Gewicht:");
        maxMassCol.setCellValueFactory(new PropertyValueFactory("maximumMass"));
        tableKnapsack.getColumns().setAll(nCol, maxMassCol);
    }

    /**
     * Handels the Refresh of the Knapsack Element Table
     *
     * @param sql
     */
    private void refreshKnapsackElementTable(SqlHelper sql) {
        StandardHelper.knapsackElements = sql.getKnapsackElements();
        ObservableList<KnapsackElement> kElements = FXCollections.observableArrayList(StandardHelper.knapsackElements);
        tableKnapsackElements.setItems(kElements);

        TableColumn<KnapsackElement, String> nameCol = new TableColumn<>("Name:");
        nameCol.setCellValueFactory(new PropertyValueFactory("elementName"));
        TableColumn<KnapsackElement, Integer> worthCol = new TableColumn<>("Wert:");
        worthCol.setCellValueFactory(new PropertyValueFactory("worthValue"));
        TableColumn<KnapsackElement, Integer> weightCol = new TableColumn<>("Gewicht:");
        weightCol.setCellValueFactory(new PropertyValueFactory("massValue"));
        TableColumn<KnapsackElement, Double> profIndxCol = new TableColumn<>("Profit Index:");
        profIndxCol.setCellValueFactory(new PropertyValueFactory("profitIndex"));
        tableKnapsackElements.getColumns().setAll(nameCol, worthCol, weightCol, profIndxCol);
    }

    /**
     * Handels the Refresh of the Calculation Knapsack & Calculation Knapsack Element Table
     * @param sql 
     */
    private void refreshCalTables(SqlHelper sql) {
        StandardHelper.knapsacks = sql.getKnapsack();
        StandardHelper.knapsackElements = sql.getKnapsackElements();

        ObservableList<Knapsack> knapscks = FXCollections.observableList(StandardHelper.knapsacks);
        ObservableList<KnapsackElement> kElems = FXCollections.observableList(StandardHelper.knapsackElements);

        tableKCalc.setItems(knapscks);
        TableColumn<Knapsack, String> nkCol = new TableColumn<>("Name:");
        nkCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Knapsack, Integer> maxkMassCol = new TableColumn<>("Maximales Gewicht:");
        maxkMassCol.setCellValueFactory(new PropertyValueFactory("maximumMass"));
        tableKCalc.getColumns().setAll(nkCol, maxkMassCol);

        tableKECalc.setItems(kElems);
        TableColumn<KnapsackElement, String> nameKECol = new TableColumn<>("Name:");
        nameKECol.setCellValueFactory(new PropertyValueFactory("elementName"));
        TableColumn<KnapsackElement, Integer> worthKECol = new TableColumn<>("Wert:");
        worthKECol.setCellValueFactory(new PropertyValueFactory("worthValue"));
        TableColumn<KnapsackElement, Integer> weightKECol = new TableColumn<>("Gewicht:");
        weightKECol.setCellValueFactory(new PropertyValueFactory("massValue"));
        TableColumn<KnapsackElement, Double> profKEIndxCol = new TableColumn<>("Profit Index:");
        profKEIndxCol.setCellValueFactory(new PropertyValueFactory("profitIndex"));
        tableKECalc.getColumns().setAll(nameKECol, worthKECol, weightKECol, profKEIndxCol);
    }

}
