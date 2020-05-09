package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Count concurrences");

        TextField textFieldString = new TextField();
        TextField textFieldWord = new TextField();
        Text textString = new Text("Target:");

        Text textWord = new Text("Input:");
        Text textResult = new Text("0");

        Button button = new Button("Count");
        CountConcurrences("ordord","word");
        button.setOnAction(action -> {
            Integer temp = CountConcurrences(textFieldString.getText(), textFieldWord.getText());
            textResult.setText(temp.toString());
        });

        HBox hboxString = new HBox(textString, textFieldString);
        hboxString.setSpacing(4);
        HBox hboxWord = new HBox(textWord, textFieldWord);
        hboxWord.setSpacing(10);
        HBox hboxButton = new HBox(button, textResult);
        hboxButton.setSpacing(4);

        VBox vbox = new VBox(hboxString,hboxWord,hboxButton);
        vbox.setSpacing(10);
        vbox.translateXProperty().set(30);
        vbox.translateYProperty().set(15);
        Scene scene = new Scene(vbox, 500, 250);
        primaryStage.setScene(scene);

        scene.setFill(Color.rgb(244, 244, 244));

        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static Integer CountConcurrences(String target, String input) {
        Integer result = 0;
        boolean flag = false;
        for (int i = 0; i < target.length() - input.length() + 1 ; i++) {
            for (int j = 0, k = i; j < input.length(); j++, k++) {
                if(target.charAt(k) == input.charAt(j)){
                    flag = true;
                }
                else {
                    flag = false;
                    break;
                }
            }
            if(flag){
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
