package pl.comp.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuBoard implements Serializable, Cloneable {

    private List<List<SudokuField>> board = new ArrayList<>(9);
    private static final Logger LOG = LoggerFactory.getLogger(SudokuBoard.class);

    /**
     * Initialization SudokuField list with unmodifiable size.
     */
    public SudokuBoard() {
        SudokuField[][] boardCopy = new SudokuField[9][9];
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                boardCopy[row][column] = new SudokuField();
            }
            board.add(Arrays.asList(boardCopy[row]));
        }
        board = Collections.unmodifiableList(board);
    }

    private boolean check(final int row, final int col) {
        return (getSudokuRow(row).verify()
                && getSudokuColumn(col).verify()
                && getSudokuBox(row, col).verify());
    }

    /**
     * Method to display current SudokuField value in board list.
     */
    public void showSudokuBoard() {
        StringBuilder sudokuBoardDisplay = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoardDisplay.append(board.get(i).get(j).getFieldValue()).append(" ");
            }
            sudokuBoardDisplay.append("\n");
        }
        LOG.info("sudokuBoard: \n{}", sudokuBoardDisplay);
    }

    /**
     * Method that return copy of board list to prevent unauthorized changes.
     *
     * @return List (This return copy of board list)
     */

    public final List<List<SudokuField>> getCopyBoard() {
        List<List<SudokuField>> boardCopy = new ArrayList<List<SudokuField>>(9);
        for (int row = 0; row < board.size(); row++) {
            List<SudokuField> boardCopyRow = new ArrayList<>(9);
            boardCopyRow.addAll(board.get(row));
            boardCopy.add(boardCopyRow);
        }
        return boardCopy;
    }

    public final int getNumberFromPosition(int row, int column) {
        return board.get(row).get(column).getFieldValue();
    }

    public final boolean setNumberOnPosition(int row, int column, int value) {
        board.get(row).get(column).setFieldValue(value);
        return check(row, column);
    }


    /**
     * Method used to check if SudokuField value stored in board List is correct.
     *
     * @return true if is correct, false if wrong
     */
    public final boolean checkBoard() {
        int maxRow = 9;
        int maxColumn = 9;
        for (int row = 0; row < maxRow; row++) {
            for (int column = 0; column < maxColumn; column++) {
                if (!check(row, column)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method return object SudokuRow which contains SudokuField objects for only one row.
     *
     * @param row position
     * @return SudokuRow
     */
    public final SudokuRow getSudokuRow(int row) {
        SudokuRow sudokuRows = new SudokuRow();
        for (int position = 0; position < 9; position++) {
            sudokuRows.setSudokuField(position, board.get(row).get(position));

        }

        return sudokuRows;
    }

    /**
     * Method return object SudokuColumn which contains SudokuField objects for only one column.
     *
     * @param column position
     * @return SudokuColumn
     */
    public final SudokuColumn getSudokuColumn(int column) {
        SudokuColumn sudokuColumns = new SudokuColumn();
        for (int position = 0; position < 9; position++) {
            sudokuColumns.setSudokuField(position, board.get(position).get(column));
        }
        return sudokuColumns;
    }

    /**
     * Method return object SudokuBox which contains SudokuField objects for only one box.
     *
     * @param row    position
     * @param column position
     * @return SudokuBox
     */

    public final SudokuBox getSudokuBox(int row, int column) {
        SudokuBox sudokuBoxes = new SudokuBox();
        int boxSize = 3;
        int boxRowNumber = row / boxSize;
        int boxColumnNumber = column / boxSize;
        int position = 0;

        for (int boxRow = 0; boxRow < boxSize; boxRow++) {
            for (int boxColumn = 0; boxColumn < boxSize; boxColumn++) {
                sudokuBoxes.setSudokuField(position,
                        board.get(boxRowNumber * 3 + boxRow).get(boxColumnNumber * 3 + boxColumn));
                position++;
            }
        }

        return sudokuBoxes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) o;
        return Objects.equal(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(board);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("board", board)
                .toString();
    }

}
