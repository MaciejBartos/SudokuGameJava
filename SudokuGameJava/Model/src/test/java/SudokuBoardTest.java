import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.comp.models.*;

import java.util.List;


class SudokuBoardTest {

    @Test
    void checkTrueTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        backtrackingSudokuSolver.solve(sudokuBoard);


        Assertions.assertTrue(sudokuBoard.checkBoard());
    }

    @Test
    void checkFalseTest() {
        int number;
        SudokuBoard sudokuBoard = new SudokuBoard();
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        backtrackingSudokuSolver.solve(sudokuBoard);

        number = sudokuBoard.getNumberFromPosition(0, 0);
        sudokuBoard.setNumberOnPosition(0, 0, (number + 1) % 9 + 1);

        Assertions.assertFalse(sudokuBoard.checkBoard());
    }

    @Test
    void getSudokuRowTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        backtrackingSudokuSolver.solve(sudokuBoard);

        SudokuRow sudokuRow = sudokuBoard.getSudokuRow(0);
        List<List<SudokuField>> sudokuField = sudokuBoard.getCopyBoard();

        for (int i = 0; i < 9; i++) {
            Assertions.assertEquals(sudokuRow.getSudokuFieldValue(i), sudokuField.get(0).get(i).getFieldValue());
        }

    }

    @Test
    void getSudokuColumnTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        backtrackingSudokuSolver.solve(sudokuBoard);

        SudokuColumn sudokuColumn = sudokuBoard.getSudokuColumn(0);
        List<List<SudokuField>> sudokuField = sudokuBoard.getCopyBoard();

        for (int i = 0; i < 9; i++) {
            Assertions.assertEquals(sudokuColumn.getSudokuFieldValue(i), sudokuField.get(i).get(0).getFieldValue());
        }

    }

    @Test
    void getSudokuBoxTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        backtrackingSudokuSolver.solve(sudokuBoard);

        SudokuBox sudokuBox = sudokuBoard.getSudokuBox(0, 0);
        List<List<SudokuField>> sudokuField = sudokuBoard.getCopyBoard();

        int count = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Assertions.assertEquals(sudokuBox.getSudokuFieldValue(count), sudokuField.get(i).get(j).getFieldValue());
                count++;
            }
        }

    }

    @Test
    void verifyTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        backtrackingSudokuSolver.solve(sudokuBoard);

        SudokuColumn sudokuColumn = sudokuBoard.getSudokuColumn(0);
        Assertions.assertTrue(sudokuColumn.verify());
    }

    @Test
    void toStringTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();

        sudokuBoard.setNumberOnPosition(0,0, 2);

        String expected = "";
        expected += "SudokuBoard{board=" + sudokuBoard.getCopyBoard().toString() + "}";

        Assertions.assertEquals(sudokuBoard.toString(), expected);
    }

    @Test
    void equalsSameObjectTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();

        sudokuBoard.setNumberOnPosition(0,0,2);

        Assertions.assertTrue(sudokuBoard.equals(sudokuBoard));
    }

    @Test
    void equalsDifferentObjectTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();

        sudokuBoard.setNumberOnPosition(0,0,2);

        Assertions.assertFalse(sudokuBoard.equals(4));
        Assertions.assertFalse(sudokuBoard.equals(null));
    }

    @Test
    void equalsTest() {
        SudokuBoard sudokuBoardFirst = new SudokuBoard();
        SudokuBoard sudokuBoardSecond = new SudokuBoard();

        sudokuBoardFirst.setNumberOnPosition(0,0,2);
        sudokuBoardSecond.setNumberOnPosition(0,0,2);

        Assertions.assertTrue(sudokuBoardFirst.equals(sudokuBoardSecond));
    }

    @Test
    void hashCodeTest() {
        SudokuBoard sudokuBoardFirst = new SudokuBoard();
        SudokuBoard sudokuBoardSecond = new SudokuBoard();

        sudokuBoardFirst.setNumberOnPosition(0,0,2);
        sudokuBoardSecond.setNumberOnPosition(0,0,2);

        Assertions.assertEquals(sudokuBoardFirst.hashCode(), sudokuBoardSecond.hashCode());
    }

    @Test
    void showSudokuBoardTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        backtrackingSudokuSolver.solve(sudokuBoard);

        sudokuBoard.showSudokuBoard();
    }
}