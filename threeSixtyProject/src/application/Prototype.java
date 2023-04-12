package application;

import javafx.util.Callback;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

//import 

public class Prototype extends Application {
	
	private boolean isEditMode = false;
	
    public void start(Stage primaryStage) {
    	
    	ObservableList<Effort> effort1 = FXCollections.observableArrayList(
    			new Effort("Eating", 30, 25),
                new Effort("Gaming", 45, 40),
                new Effort("Sleeping", 50, 45)
        );
    	
    	ObservableList<Effort> effort2 = FXCollections.observableArrayList(
    			new Effort("Sneezing", 30, 25),
                new Effort("Burping", 45, 40),
                new Effort("Farting", 50, 45)
        );
    	
    	Rectangle blurredBackground = new Rectangle();
        blurredBackground.widthProperty().bind(primaryStage.widthProperty());
        blurredBackground.heightProperty().bind(primaryStage.heightProperty());
        blurredBackground.setFill(Color.WHITE);
        blurredBackground.setEffect(new GaussianBlur(5));

        // Create a Pane with a semi-transparent background
        Pane backgroundPane = new Pane();
        backgroundPane.getStyleClass().add("background-pane");

        // Add your content (e.g., a VBox) here
        VBox content = new VBox(10);
        content.setPadding(new Insets(10, 10, 10, 10));
        // Add content elements...

        // Create a StackPane containing the blurred background, background pane, and content
        StackPane root = new StackPane(blurredBackground, backgroundPane, content);

        // Label
        Label taskLabel = new Label("Task:");
        // TextField
        TextField taskTextField = new TextField();
        
        // Label
        Label timeLabel = new Label("Time:");
        // TextField
        TextField timeTextField = new TextField();
        
        ListView<Effort> listView = new ListView<>();
        listView.setCellFactory(new Callback<>() {
        	
            @Override
            public ListCell<Effort> call(ListView<Effort> listView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Effort effort, boolean empty) {
                        super.updateItem(effort, empty);
                        if (effort == null || empty) {
                            setText(null);
                        } else {
                            setText(String.format("Task: %s | Last Time Spent: %.2f | Average Time Spent: %.2f",
                                    effort.getTaskType(), effort.getLastTimeSpent(), effort.getAverageTimeSpent()));
                        }
                    }
                };
            }
        });
        
        // ComboBox
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Effort 1", "Effort 2"); 
     // Add a listener to ComboBox's value property
        comboBox.valueProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("Effort 1")) {
                    listView.setItems(effort1);
                } else if (newValue.equals("Effort 2")) {
                    listView.setItems(effort2);
                }
            }
        });
        
        //set initial value of dropdown select after listener logic is rendered to trigger load default selection with listener function
        comboBox.setValue("Effort 1");
        
        // Button
        Button addButton = new Button("Add Task");
        Button editButton = new Button("Edit task");
        
        addButton.setOnAction(event -> {
            String newTask = taskTextField.getText();
            String taskTime = timeTextField.getText();
            double taskTimeNumber = Double.parseDouble(taskTime);
            
            if (!newTask.isBlank() && !taskTime.isBlank()) {
            	Effort newEffort = new Effort(newTask, taskTimeNumber, 0.00);
            	
            	effort1.add(newEffort);
                taskTextField.clear();
                timeTextField.clear();
            }
            else {
            	System.out.println("Empty string");
            }
        });
        
        editButton.setOnAction(event -> {
            String newTask = taskTextField.getText();
            String taskTime = timeTextField.getText();
            double taskTimeNumber = Double.parseDouble(taskTime);
            
            if (!newTask.isBlank() && !taskTime.isBlank()) {
            	Effort newEffort = new Effort(newTask, taskTimeNumber, 0.00);
            	
            	effort1.add(newEffort);
                taskTextField.clear();
                timeTextField.clear();
            }
            else {
            	System.out.println("Empty string");
            }
        });

        content.getChildren().addAll(taskLabel, taskTextField, timeLabel, timeTextField, addButton, editButton, comboBox, listView);
        
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        primaryStage.setTitle("Prototype");
        primaryStage.show();
    }

	public static void main(String[] args) {
		launch(args);
	}
    
}
