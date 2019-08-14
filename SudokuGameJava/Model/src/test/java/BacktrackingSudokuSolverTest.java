import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.comp.models.BacktrackingSudokuSolver;
import pl.comp.models.SudokuBoard;
import pl.comp.models.SudokuField;

import java.util.List;


class BacktrackingSudokuSolverTest {

    @Test
    void fillBoardTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard();

        backtrackingSudokuSolver.solve(sudokuBoard);

        Assertions.assertTrue(sudokuBoard.checkBoard());
    }

    @Test
    void filledBoardGenerateDifferentBoardsTest() {
        int sudokuSize = 9;
        boolean areDifferent = false;
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoardFirst = new SudokuBoard();
        SudokuBoard sudokuBoardSecond = new SudokuBoard();
        backtrackingSudokuSolver.solve(sudokuBoardFirst);
        backtrackingSudokuSolver.solve(sudokuBoardSecond);

        List<List<SudokuField>> sudokuFirstBoardCopy = sudokuBoardFirst.getCopyBoard();
        List<List<SudokuField>> sudokuSecondBoardCopy = sudokuBoardSecond.getCopyBoard();


        for (int row = 0; row < sudokuSize; row++) {
            if (sudokuFirstBoardCopy.get(row).equals(sudokuSecondBoardCopy.get(row))) {
                areDifferent = true;
            }
        }
        Assertions.assertFalse(areDifferent);
    }
}