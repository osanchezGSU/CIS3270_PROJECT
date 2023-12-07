package GUI;

<<<<<<< HEAD
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
=======
public class User_Home {
>>>>>>> branch 'master' of https://github.com/osanchezGSU/CIS3270_PROJECT.git

<<<<<<< HEAD
import Database.FlightLogDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class User_Home implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ChoiceBox<String> DestinationChoiceBox;
    
    @FXML
    private ChoiceBox<String> DateChoiceBox;

    @FXML
    private ChoiceBox<String> DepartureChoiceBox;

    @FXML
    private Button LogOutButton;

    @FXML
    private Button bookButton;

    @FXML
    private Button MyBookingButton;

    @FXML
    private Label date;

    @FXML
    private Label destination;

    @FXML
    private Label from;

    FlightLogDB flightLogDB = new FlightLogDB();

    List<String> departureCities = flightLogDB.getDepartureCities();
    List<String> arrivalCities = flightLogDB.getArrivalCities();
    List<String> dates = flightLogDB.getDates();

    public void switchToMyBookings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MyBookingPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void getDepartureCities(ActionEvent event) {
        String departureCity = DepartureChoiceBox.getValue();
        from.setText(departureCity);

        updateDateChoiceBox(departureCity, DestinationChoiceBox.getValue());
    }

    @FXML
    public void getArrivalCities(ActionEvent event) {
        String arrivalCity = DestinationChoiceBox.getValue();
        destination.setText(arrivalCity);

        updateDateChoiceBox(DepartureChoiceBox.getValue(), arrivalCity);
    }

    @FXML
    public void getDates(ActionEvent event) {
        String selectedDate = DateChoiceBox.getValue();
        date.setText(selectedDate);
    }

    private void updateDateChoiceBox(String departureCity, String arrivalCity) {
        List<String> filteredDates = flightLogDB.getDatesForCities(departureCity, arrivalCity);

        DateChoiceBox.getItems().clear();
        DateChoiceBox.getItems().addAll(filteredDates);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DepartureChoiceBox.getItems().addAll(departureCities);
        DepartureChoiceBox.setOnAction(this::getDepartureCities);

        DestinationChoiceBox.getItems().addAll(arrivalCities);
        DestinationChoiceBox.setOnAction(this::getArrivalCities);

        DateChoiceBox.getItems().addAll(dates);
        DateChoiceBox.setOnAction(this::getDates);
    }
}
=======
}
>>>>>>> branch 'master' of https://github.com/osanchezGSU/CIS3270_PROJECT.git
