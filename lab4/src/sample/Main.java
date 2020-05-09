package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    Shower shower = new Shower();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("lab4");

        Button buttonAddMale = new Button("add male");
        buttonAddMale.setOnAction(actionEvent -> {
            if (shower.onlyMales()) {
                Human human = new Human("male", shower);
                shower.addHuman(human);
            }
        });
        Button buttonAddFemale = new Button("add female");
        buttonAddFemale.setOnAction(actionEvent -> {
            if (shower.onlyFemales()) {
                Human human = new Human("female", shower);
                shower.addHuman(human);
            }
        });
        shower.getListView().setPrefSize(400,420);
        HBox hbox = new HBox( buttonAddMale, buttonAddFemale);
        hbox.setSpacing(100);
        StackPane stackpane = new StackPane(shower.getListView());
        StackPane.setMargin(shower.getListView(), new Insets(8, 8, 8, 8));
        hbox.setAlignment(Pos.BASELINE_CENTER);
        VBox vbox = new VBox(stackpane,hbox);
        vbox.setSpacing(15);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(vbox, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        shower.close();
    }
}
