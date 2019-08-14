package pl.comp.models;

public class SudokuBoardDaoFactory {
    public  Dao getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }
}
