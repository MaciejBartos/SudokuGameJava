import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.comp.models.*;


class FileSudokuBoardDaoTest {

    @Test
    void testFileSavingAndReading(){
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuBoard sudokuBoardFromFile;
        Dao fileSudokuBoardDao = new SudokuBoardDaoFactory().getFileDao("SudokuBoardClassSave.ser");

        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        backtrackingSudokuSolver.solve(sudokuBoard);

        fileSudokuBoardDao.write(sudokuBoard);

        sudokuBoardFromFile = (SudokuBoard) fileSudokuBoardDao.read();

        Assertions.assertEquals(sudokuBoard, sudokuBoardFromFile);
    }

}