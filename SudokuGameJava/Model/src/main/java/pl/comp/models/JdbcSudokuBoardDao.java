package pl.comp.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private final String filename;
    private Connection connection  = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Maciej\\Desktop\\DataBase\\SudokuBoard.db");
    private Statement statement = connection.createStatement();



    public JdbcSudokuBoardDao(String filename) throws SQLException {
        this.filename = filename;
        statement.setQueryTimeout(30);
    }

    @Override
    public SudokuBoard read() {
        return
    }

    @Override
    public void write(SudokuBoard object) {

    }
}
