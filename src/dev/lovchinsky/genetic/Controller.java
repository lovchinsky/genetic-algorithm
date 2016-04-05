package dev.lovchinsky.genetic;

import java.net.URL;
import java.util.ResourceBundle;
import dev.lovchinsky.genetic.algorithm.Algorithm;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;


public class Controller {

    private TravellingSalesmanProblem travellingSalesmanProblem;

    private CustomCanvas customCanvas;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton annealingSelector;

    @FXML
    private ToggleGroup choosingToggle;

    @FXML
    private ToggleGroup crossingToggle;

    @FXML
    private Pane displayPane;

    @FXML
    private RadioButton greedyMutator;

    @FXML
    private RadioButton inbreeding;

    @FXML
    private ToggleGroup mutationToggle;

    @FXML
    private TextField numberOfCitiesText;

    @FXML
    private TextField numberOfIterationText;

    @FXML
    private RadioButton outbreeding;

    @FXML
    private RadioButton panximia;

    @FXML
    private Slider probabilityOfMutationSlider;

    @FXML
    private RadioButton reducedSurrogateCrossover;

    @FXML
    private RadioButton rouletteWheelSelector;

    @FXML
    private ToggleGroup selectionToggle;

    @FXML
    private RadioButton singlePointCrossover;

    @FXML
    private RadioButton singlePointMutator;

    @FXML
    private TextField sizeOfPopulationText;

    @FXML
    private RadioButton tournamentSelector;

    @FXML
    private RadioButton twoPointCrossover;

    @FXML
    void generateCities(ActionEvent event) {
        if(numberOfCitiesText.getText().isEmpty()) {
            warning("Для того, щоб згенерувати нові міста, потрібно ввести їх кількість");
        } else {
            try {
                int numberOfCities = Integer.parseInt(numberOfCitiesText.getText());
                if(numberOfCities >= 2) {
                    travellingSalesmanProblem.generateRandomCities(numberOfCities);
                    travellingSalesmanProblem.drawCities();
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                warning("Числа в полях вводу повинні бути від 2 до 2147483647");
            }
        }
    }

    private void warning(String contextText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Попередження");
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void navigate(ActionEvent event) {
        if(sizeOfPopulationText.getText().isEmpty()) {
            warning("Для того, щоб прокласти маршрут, потрібно ввести величину популяції");
            return;
        }
        if(numberOfIterationText.getText().isEmpty()) {
            warning("Для того, щоб прокласти маршрут, потрібно ввести кількість ітерацій");
            return;
        }
        if(numberOfCitiesText.getText().isEmpty()) {
            warning("Для того, щоб згенерувати нові міста, потрібно ввести їх кількість");
            return;
        }

        try {
            int sizeOfPopulation = Integer.valueOf(sizeOfPopulationText.getText());
            int numberOfIteration = Integer.valueOf(numberOfIterationText.getText());

            if(sizeOfPopulation >= 1 && numberOfIteration >= 1) {
                travellingSalesmanProblem.setAlgorithm(sizeOfPopulation, numberOfIteration,
                        probabilityOfMutationSlider.getValue(),
                        (Algorithm.Selection) selectionToggle.getSelectedToggle().getUserData(),
                        (Algorithm.Choosing) choosingToggle.getSelectedToggle().getUserData(),
                        (Algorithm.Crossing) crossingToggle.getSelectedToggle().getUserData(),
                        (Algorithm.Mutation) mutationToggle.getSelectedToggle().getUserData());

                try {
                    travellingSalesmanProblem.decide();
                } catch (Exception e) {
                    warning(e.getMessage());
                }
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            warning("Числа в полях вводу повинні бути від 1 до 2147483647");
        }
    }

    @FXML
    void initialize() {
        customCanvas = new CustomCanvas(displayPane.getPrefWidth(), displayPane.getPrefHeight());
        displayPane.getChildren().add(customCanvas);

        setValidate(numberOfCitiesText);
        setValidate(sizeOfPopulationText);
        setValidate(numberOfIterationText);

        initSelectionToggle();
        initChoosingToggle();
        initCrossingToggle();
        initMutationToggle();
    }

    private void setValidate(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("^[1-9]*|[1-9][0-9]*")) {
                textField.setText(oldValue);
            }
        });
    }

    private void initToggle(Toggle toggle, Enum e) {

    }

    private void initSelectionToggle() {
        ObservableList<Toggle> toggles = selectionToggle.getToggles();
        Algorithm.Selection[] values = Algorithm.Selection.values();
        for (int i = 0, size = toggles.size(); i < size; i++) {
            toggles.get(i).setUserData(values[i]);
        }
    }

    private void initChoosingToggle() {
        ObservableList<Toggle> toggles = choosingToggle.getToggles();
        Algorithm.Choosing[] values = Algorithm.Choosing.values();
        for (int i = 0, size = toggles.size(); i < size; i++) {
            toggles.get(i).setUserData(values[i]);
        }
    }

    private void initCrossingToggle() {
        ObservableList<Toggle> toggles = crossingToggle.getToggles();
        Algorithm.Crossing[] values = Algorithm.Crossing.values();
        for (int i = 0, size = toggles.size(); i < size; i++) {
            toggles.get(i).setUserData(values[i]);
        }
    }

    private void initMutationToggle() {
        ObservableList<Toggle> toggles = mutationToggle.getToggles();
        Algorithm.Mutation[] values = Algorithm.Mutation.values();
        for (int i = 0, size = toggles.size(); i < size; i++) {
            toggles.get(i).setUserData(values[i]);
        }
    }

    public void setTravellingSalesmanProblem(TravellingSalesmanProblem travellingSalesmanProblem) {
        travellingSalesmanProblem.setArea(customCanvas.getWidth(), customCanvas.getHeight());
        travellingSalesmanProblem.setDisplay(customCanvas);
        this.travellingSalesmanProblem = travellingSalesmanProblem;
    }
}
