package pl.comp.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private String fileName;
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    private static final Logger LOG = LoggerFactory.getLogger(FileSudokuBoardDao.class);

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Method used to read object from file of the given name.
     * @return SudokuBoard
     */
    public SudokuBoard read() {
        SudokuBoard sudokuBoard;
        try (FileInputStream fileInputStream =
                     new FileInputStream(new File(fileName));
             ObjectInputStream objectInputStream =
                     new ObjectInputStream(fileInputStream)) {
            sudokuBoard = (SudokuBoard) objectInputStream.readObject();
        } catch (Exception e) {
            LOG.error("Read exception: {}", e);
            sudokuBoard = new SudokuBoard();
        }

        return sudokuBoard;
    }

    /**
     * Method used to write given SudokuBoard object to file of given name and save it.
     * @param object SudokuBoard object
     */
    public void write(SudokuBoard object) {
        try (FileOutputStream fileOutputStream =
                     new FileOutputStream(new File(fileName));
             ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            LOG.error("Write exception: {}", e);
        }
    }

}
