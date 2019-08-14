import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.comp.models.BacktrackingSudokuSolver;
import pl.comp.models.SudokuBoard;

public class Test {
    private static final Logger LOG = LoggerFactory.getLogger(Test.class);

    @org.junit.jupiter.api.Test
    void test() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();

        backtrackingSudokuSolver.solve(sudokuBoard);
        sudokuBoard.showSudokuBoard();
    }
}
