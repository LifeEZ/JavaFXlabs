package test;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.*;

import java.util.Vector;
//Реализовать Классы Самолёт, Пассажир, Пилот, Стюардесса.
//Метод пассажира Зайти на самолёт() получает у класса самолёт номер свободного места и запоминает его.
//Метод Управлять самолётом() класса Пилот вызывает методы Потерять высоту() и Набрать высоту() класса Самолёт().
//Метод разносить еду() класса стюардесса выводит на экран текст.
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        Vector<MainPassenger> MainPassengers = new Vector<>();
        Vector<FirstClassPassenger> FirstClassPassengers = new Vector<>();

        Aircraft aircraft = new Aircraft();


        Pilot pilot = new Pilot();
        Stewardess stewardess = new Stewardess();
        aircraft.NewStaffMember(pilot);
        aircraft.NewStaffMember(stewardess);


        Vector<Button> buttons = new Vector<>();
        buttons.add(new Button("Serve Coffee"));//0
        buttons.add(new Button("Serve Food"));//1
        buttons.add(new Button("Aviate"));//2
        buttons.add(new Button("Add main passenger"));//3
        buttons.add(new Button("Add first class passenger"));//4
        buttons.add(new Button("Complete registration"));//5
        buttons.add(new Button("Get off plane"));//6
        for (Button button : buttons) {
            button.setMaxWidth(300);
        }



        Label StewardessName = new Label("Stewardess");
        Label MealsServed = new Label("");
        Label ServeAction = new Label(stewardess.ServeCoffee());
        ServeAction.setVisible(false);
        //stewardess "serve coffee"
        buttons.elementAt(0).setDisable(true);
        buttons.elementAt(0).setOnAction(e -> {
            ServeAction.setVisible(true);
            buttons.elementAt(0).setDisable(true);
            if(FirstClassPassengers.size()>0) {
                buttons.elementAt(1).setDisable(false);
            }
            else {
                buttons.elementAt(2).setDisable(false);
            }
        });
        //stewardess "serve food"
        buttons.elementAt(1).setDisable(true);
        buttons.elementAt(1).setOnAction(e -> {
            MealsServed.setText("Served meals: " + stewardess.ServeFood(FirstClassPassengers));
            MealsServed.setVisible(true);
            buttons.elementAt(1).setDisable(true);
            buttons.elementAt(2).setDisable(false);
        });
        VBox StewardessVBox = new VBox(StewardessName, buttons.elementAt(0), buttons.elementAt(1), ServeAction, MealsServed);
        StewardessVBox.setSpacing(5);
        StewardessVBox.setPrefSize(400, 450);
        StewardessVBox.setAlignment(Pos.BASELINE_CENTER);



        Label PilotName = new Label("Pilot");
        Label FlightStatus = new Label("");
        Label Height = new Label("");
        Height.setVisible(false);
        FlightStatus.setVisible(false);
        //pilot "aviate"
        buttons.elementAt(2).setDisable(true);
        buttons.elementAt(2).setOnAction(e -> {
            Height.setVisible(true);
            pilot.Aviate(aircraft);
            FlightStatus.setVisible(true);
            buttons.elementAt(2).setDisable(true);
            if (aircraft.GetFlightStatus()) {
                FlightStatus.setText("Flight is in progress...");
                buttons.elementAt(0).setDisable(false);
            }
            else {
                FlightStatus.setText("Flight is over.");
                buttons.elementAt(6).setVisible(true);
            }
            Height.setText("Height: " + aircraft.GetHeight());
        });
        VBox PilotVBox = new VBox(PilotName, buttons.elementAt(2), FlightStatus, Height);
        PilotVBox.setSpacing(5);
        PilotVBox.setPrefSize(400, 450);
        PilotVBox.setAlignment(Pos.BASELINE_CENTER);




        Label PassengersName = new Label("Passengers");
        Label FreeSeats = new Label("Free seats: " + aircraft.GetFreeSeats());
        Label PassengersCount = new Label("Passengers: " + aircraft.GetPassengers().size());
        //"add passenger"
        buttons.elementAt(3).setOnAction(e -> {
            if (MainPassengers.size() != 50) {
                MainPassenger passenger = new MainPassenger();
                MainPassengers.add(passenger);
                aircraft.NewPassenger(passenger);
                passenger.GetOnPlain(aircraft);
                FreeSeats.setText("Free seats: " + aircraft.GetFreeSeats());
                PassengersCount.setText("Passengers: " + aircraft.GetPassengers().size());
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Main class seats are full!");
                alert.setHeaderText(null);
                if(FirstClassPassengers.size() == 20) {
                    alert.setContentText("Complete registration to continue! All seats are on full.");
                }
                else {
                    alert.setContentText("Main class seats are full!");
                }
                alert.show();
            }
        });
        Label FCFreeSeats = new Label("Free first class seats: " + aircraft.GetFCFreeSeats());
        Label FCPassengersCount = new Label("Passengers: " + aircraft.GetFCPassengers().size());
        //"add first class passenger"
        buttons.elementAt(4).setOnAction(e -> {
            if (FirstClassPassengers.size() != 20) {
                FirstClassPassenger passenger = new FirstClassPassenger();
                FirstClassPassengers.add(passenger);
                aircraft.NewFCPassenger(passenger);
                passenger.GetOnPlain(aircraft);
                FCFreeSeats.setText("Free first class seats: " + aircraft.GetFCFreeSeats());
                FCPassengersCount.setText("Passengers: " + aircraft.GetFCPassengers().size());
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("First class seats are full!");
                alert.setHeaderText(null);
                if(MainPassengers.size() == 50) {
                    alert.setContentText("Complete registration to continue! All seats are full.");
                }
                else {
                    alert.setContentText("First class seats are full!");
                }
                alert.show();
            }
        });
        //"Complete registration"
        buttons.elementAt(5).setOnAction(e -> {
            if (aircraft.GetFreeSeats() == 50 || aircraft.GetFCFreeSeats() == 20) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No passengers!");
                alert.setHeaderText(null);
                alert.setContentText("Can't complete registration without passengers");
                alert.show();
            }
            else {
                buttons.elementAt(3).setDisable(true);
                buttons.elementAt(4).setDisable(true);
                buttons.elementAt(5).setDisable(true);
                buttons.elementAt(2).setDisable(false);
            }
        });
        //"get off plane"
        Label FlightOver = new Label("Flight is over.");
        Label Thanks = new Label("Thank you for using our company.");
        FlightOver.setVisible(false);
        Thanks.setVisible(false);
        buttons.elementAt(6).setVisible(false);
        buttons.elementAt(6).setOnAction(e -> {

            FlightOver.setVisible(true);
            Thanks.setVisible(true);
            buttons.elementAt(6).setDisable(true);
            for (MainPassenger i : MainPassengers) {
                i.GetOffPlane(aircraft);
            }
            for (FirstClassPassenger i : FirstClassPassengers) {
                i.GetOffPlane(aircraft);
            }
            FreeSeats.setText("Free seats: " + aircraft.GetFreeSeats());
            PassengersCount.setText("Passengers: " + aircraft.GetPassengers().size());
            FCFreeSeats.setText("Free first class seats: " + aircraft.GetFCFreeSeats());
            FCPassengersCount.setText("Passengers: " + aircraft.GetFCPassengers().size());

        });
        VBox PassengerVBox = new VBox(PassengersName, buttons.elementAt(3), FreeSeats, PassengersCount,
                                      buttons.elementAt(4), FCFreeSeats, FCPassengersCount,
                                      buttons.elementAt(5), buttons.elementAt(6),
                                      FlightOver, Thanks);
        PassengerVBox.setSpacing(5);
        PassengerVBox.setPrefSize(400, 450);
        PassengerVBox.setAlignment(Pos.BASELINE_CENTER);



        Separator separator1 = new Separator();
        separator1.setOrientation(Orientation.VERTICAL);
        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);



        HBox MainHBox = new HBox(PassengerVBox, separator1, PilotVBox, separator2, StewardessVBox);
        Scene scene = new Scene(MainHBox, 1200, 450);



        primaryStage.setTitle("Lab2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
