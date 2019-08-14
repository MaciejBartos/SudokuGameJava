package pl.comp.gui;

import pl.comp.models.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.models.exceptions.NoFileSelectedException;

import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {



    @FXML
    private Label gameCheckResult;
    @FXML
    private Button checkButton;
    @FXML
    private Label authorLabel;
    @FXML
    private Button startGameButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button saveButton;
    @FXML
    private GridPane board;
    @FXML
    private ChoiceBox<String> difficultyChoiceBox;
    @FXML
    private ChoiceBox<String> languageChoiceBox;

    private SudokuBoard sudokuBoard;
    private ResourceBundle resources;
    private static final Logger LOG = LoggerFactory.getLogger(Controller.class);



    public void createGUIBoard() {
        PseudoClass right = PseudoClass.getPseudoClass("right");
        PseudoClass bottom = PseudoClass.getPseudoClass("bottom");

        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                StackPane cell = new StackPane();
                cell.getStyleClass().add("cell");
                cell.pseudoClassStateChanged(right, col == 2 || col == 5);
                cell.pseudoClassStateChanged(bottom, row == 2 || row == 5);

                TextField textField = new TextField();

                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    LOG.debug("oldValue: {}", oldValue);
                    LOG.debug("newValue: {}", newValue);

                    if (!newValue.matches("[1-9]?")) {
                        if (newValue.length() > 1) {
                            newValue = oldValue;
                        }
                        textField.setText(newValue.replaceAll("[^[1-9]]", ""));

                    }
                });

                cell.getChildren().add(textField);

                board.add(cell, col, row);
            }
        }
    }

    private void setLanguage(Locale locale) {
        resources = ResourceBundle.getBundle("LanguageBundle", locale);


        ResourceBundle authorBundle = ResourceBundle.getBundle("pl.comp.gui.bundle.AuthorsListResourceBundle", locale);
        authorLabel.setText(authorBundle.getString("authorWord") + ": " + authorBundle.getString("authorName"));
        setChoiceBoxesLanguage(resources);
        setButtonsLanguage(resources);
    }

    private void setButtonsLanguage(ResourceBundle resource) {
        startGameButton.setText(resource.getString("startButton"));
        loadButton.setText(resource.getString("loadButton"));
        saveButton.setText(resource.getString("saveButton"));
        checkButton.setText(resource.getString("checkButton"));
    }

    private void setChoiceBoxesLanguage(ResourceBundle resources) {
        difficultyChoiceBox.getItems().clear();
        difficultyChoiceBox.getItems().add(resources.getString("choiceBoxEasy"));
        difficultyChoiceBox.getItems().add(resources.getString("choiceBoxNormal"));
        difficultyChoiceBox.getItems().add(resources.getString("choiceBoxHard"));

        languageChoiceBox.getItems().clear();
        languageChoiceBox.getItems().add(resources.getString("choiceBoxLanguagePolish"));
        languageChoiceBox.getItems().add(resources.getString("choiceBoxLanguageEnglish"));

    }

    @Override
    public void initialize(URL location, ResourceBundle resourcesBundle) {
        Locale locale = new Locale("pl", "PL");

        sudokuBoard = new SudokuBoard();

        createGUIBoard();
        setLanguage(locale);
        languageChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue.equals(resources.getString("choiceBoxLanguagePolish"))) {
                    setLanguage(new Locale("pl", "PL"));
                }
                if (newValue.equals(resources.getString("choiceBoxLanguageEnglish"))) {
                    setLanguage(new Locale("en", "EN"));
                }
            } catch (NullPointerException ignore) {
            }
        });
    }

    private TextField getTextFieldFromGridPane(GridPane gridPane, int col, int row) {
        TextField textFieldTemp;
        StackPane stackPaneTemp;

        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                stackPaneTemp = (StackPane) node;
                textFieldTemp = (TextField) stackPaneTemp.getChildren().get(0);
                return textFieldTemp;
            }
        }
        return null;
    }

    public void start(ActionEvent actionEvent) {
        insertNumberDependingOnLevel();
    }

    public void putDigitOnPosition(int row, int column, String value) {
        getTextFieldFromGridPane(board, column, row).setText(value);
    }

    public void putNumbersIntoSudokuBoard(int max, int min) {
        Random randomGenerator = new Random();
        int randomNumber;
        int randomPositon;
        int numberOnPosition;

        SudokuBoard sudokuBoardTmp = new SudokuBoard();
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        backtrackingSudokuSolver.solve(sudokuBoardTmp);

        for (int row = 0; row < 9; row++) {
            randomNumber = randomGenerator.nextInt(max - min + 1) + min;
            for (int digitsInRow = 0; digitsInRow < randomNumber; digitsInRow++) {
                randomPositon = randomGenerator.nextInt(9);
                numberOnPosition = sudokuBoardTmp.getNumberFromPosition(row, randomPositon);
                this.sudokuBoard.setNumberOnPosition(row, randomPositon, numberOnPosition);
////                sudoku.setNumberOnPosition(row, randomPositon, sudokuBoard.getNumberFromPosition(row, randomPositon));
//                stackPaneTemp = (StackPane) getNodeFromGridPane(board, randomPositon, row);
//                textFieldTemp = (TextField) stackPaneTemp.getChildren().get(0);
//                textFieldTemp.setText(numberOnPosition);
////                textFieldTemp.setEditable(false);
            }

        }
        actualizeTextFieldValues();
    }

    public void actualizeSudokuBoardFieldsValues() {
        int valueFromTextField;

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {

                try {
                    valueFromTextField = Integer.parseInt(getTextFieldFromGridPane(board, column, row).getText());
                } catch (NumberFormatException e) {
                    valueFromTextField = 0;
                }
                sudokuBoard.setNumberOnPosition(row, column, valueFromTextField);
            }
        }
        sudokuBoard.showSudokuBoard();
    }

    public void actualizeTextFieldValues() {
        String value;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                value = String.valueOf(sudokuBoard.getNumberFromPosition(row, column));
                if (!value.equals("0")) {
                    getTextFieldFromGridPane(board, column, row).setText(value);
                }

            }
        }
    }

    public void cleanGridPane() {
        TextField textFieldTmp;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                textFieldTmp = getTextFieldFromGridPane(board, column, row);
                textFieldTmp.setText("");
                textFieldTmp.setEditable(true);
            }
        }
    }

    public void cleanSudokuBoard() {
        sudokuBoard = new SudokuBoard();
    }


    public void insertNumberDependingOnLevel() {
        cleanGridPane();
        cleanSudokuBoard();
        String difficulty = difficultyChoiceBox.getValue();

        try {
            if (difficulty.equals(resources.getString("choiceBoxEasy"))) {
                putNumbersIntoSudokuBoard(6, 4);
            }
            else if (difficulty.equals(resources.getString("choiceBoxNormal"))) {
                putNumbersIntoSudokuBoard(5, 3);
            }
            else if (difficulty.equals(resources.getString("choiceBoxHard"))) {
                putNumbersIntoSudokuBoard(4, 2);
            }
            else {
                LOG.debug("difficulty different!");
            }
        } catch (NullPointerException e) {
            LOG.error("difficulty exception: {}", e);
        }

    }

    public void checkIfBoardIsCorrect(ActionEvent actionEvent) {
        actualizeSudokuBoardFieldsValues();
        if (sudokuBoard.checkBoard()) {
            gameCheckResult.setText(resources.getString("gameCheckResultFine"));
            gameCheckResult.setTextFill(Color.GREEN);
        } else {
            gameCheckResult.setText(resources.getString("gameCheckResultWrong"));
            gameCheckResult.setTextFill(Color.RED);
        }
    }

    public String getFilePath() {
        FileChooser chosenFile = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("objectSave (*.set)", "*.ser");
        chosenFile.getExtensionFilters().add(filter);
        File filePath = chosenFile.showOpenDialog(null);
        return filePath.getAbsolutePath();
    }

    public void saveToFile(ActionEvent actionEvent) {
        try {
            try {
                Dao fileBoard = new SudokuBoardDaoFactory().getFileDao(getFilePath());
                actualizeSudokuBoardFieldsValues();
                fileBoard.write(sudokuBoard);
            } catch (Exception e) {
    //            e.initCause(new NullPointerException("no file chosen"));
                throw new NoFileSelectedException("No file selected during save", e);

            }
        } catch (NoFileSelectedException e) {
            LOG.error("Problem with selected file during save!: ", e);
        }
    }

    public void loadFromFile(ActionEvent actionEvent) {
        try {
            try {
                Dao fileBoard = new SudokuBoardDaoFactory().getFileDao(getFilePath());
                sudokuBoard = (SudokuBoard) fileBoard.read();
                cleanGridPane();
                actualizeTextFieldValues();
            } catch (Exception e) {
                throw new NoFileSelectedException("No file selected during load", e);
            }
        } catch (NoFileSelectedException e) {
            LOG.error("Problem with selected file during load!: ", e);
        }

    }
}
