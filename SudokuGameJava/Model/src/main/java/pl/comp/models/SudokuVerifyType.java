package pl.comp.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class SudokuVerifyType implements Serializable, Cloneable {
    private List<SudokuField> sudokuFields = new ArrayList<SudokuField>(9);

    /**
     * Constructor used to initialize sudokuFields list witch constains SudokuField objects.
     */
    public SudokuVerifyType() {
        for (int i = 0; i < 9; i++) {
            sudokuFields.add(new SudokuField());
        }
    }

    /**
     * Method used to verify if inserted value for SudokuField is correct or wrong.
     * @return true if correct, false if wrong
     */
    public boolean verify() {
        List<Integer> numbers = new ArrayList<>(9);
        boolean fieldDiffFromZero;
        boolean fieldValueAlreadyInList;
        for (int i = 0; i < 9; i++) {
            fieldDiffFromZero = sudokuFields.get(i).getFieldValue() != 0;
            fieldValueAlreadyInList = numbers.contains(sudokuFields.get(i).getFieldValue());
            if (fieldDiffFromZero && fieldValueAlreadyInList) {
                return false;
            } else if (fieldDiffFromZero) {
                numbers.add(sudokuFields.get(i).getFieldValue());
            }
        }

        return true;
    }

    public void setSudokuField(int position, SudokuField sudokuField) {
        this.sudokuFields.set(position, sudokuField);
    }

    public int getSudokuFieldValue(int position) {
        return sudokuFields.get(position).getFieldValue();
    }

    public SudokuField getSudokuField(int position) throws CloneNotSupportedException {
        return (SudokuField) sudokuFields.get(position).clone();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sudokuFields", sudokuFields)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuVerifyType that = (SudokuVerifyType) o;
        return Objects.equal(sudokuFields, that.sudokuFields);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sudokuFields);
    }
}
