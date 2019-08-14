import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.comp.models.SudokuField;

class SudokuFieldTest {


    @Test
    void equalsSameObjectTest() {
        SudokuField sudokuField = new SudokuField();

        sudokuField.setFieldValue(2);

        Assertions.assertTrue(sudokuField.equals(sudokuField));
    }

    @Test
    void equalsDifferentObjectTest() {
        SudokuField sudokuField = new SudokuField();

        sudokuField.setFieldValue(2);

        Assertions.assertFalse(sudokuField.equals(1));
        Assertions.assertFalse(sudokuField.equals(null));
    }

    @Test
    void equalsTest() {
        SudokuField sudokuFieldFirst = new SudokuField();
        SudokuField sudokuFieldSecond = new SudokuField();

        sudokuFieldFirst.setFieldValue(2);
        sudokuFieldSecond.setFieldValue(3);

        Assertions.assertFalse(sudokuFieldFirst.equals(sudokuFieldSecond));
    }

    @Test
    void hashCodeTest() {
        SudokuField sudokuFieldFirst = new SudokuField();
        SudokuField sudokuFieldSecond = new SudokuField();

        sudokuFieldFirst.setFieldValue(2);
        sudokuFieldSecond.setFieldValue(2);

        Assertions.assertEquals(sudokuFieldFirst.hashCode(), sudokuFieldSecond.hashCode());
    }
}