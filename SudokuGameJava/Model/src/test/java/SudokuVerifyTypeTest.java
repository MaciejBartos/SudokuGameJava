import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.comp.models.SudokuField;
import pl.comp.models.SudokuVerifyType;

class ConcreteImplementation extends SudokuVerifyType {

}

class SudokuVerifyTypeTest {

    @Test
    void verifyFalseTest() {
        ConcreteImplementation sudokuVerifyType = new ConcreteImplementation();
        SudokuField sudokuField = new SudokuField();

        sudokuField.setFieldValue(3);

        sudokuVerifyType.setSudokuField(0, sudokuField);
        sudokuVerifyType.setSudokuField(1, sudokuField);

        Assertions.assertFalse(sudokuVerifyType.verify());
    }

    @Test
    void verifyTrueTest() {
        ConcreteImplementation sudokuVerifyType = new ConcreteImplementation();
        SudokuField sudokuFieldFirst = new SudokuField();
        SudokuField sudokuFieldSecond = new SudokuField();

        sudokuFieldFirst.setFieldValue(3);
        sudokuFieldSecond.setFieldValue(4);

        sudokuVerifyType.setSudokuField(0, sudokuFieldFirst);
        sudokuVerifyType.setSudokuField(1, sudokuFieldSecond);

        Assertions.assertTrue(sudokuVerifyType.verify());
    }

    @Test
    void equalsSameObjectTest() {
        ConcreteImplementation sudokuVerifyType = new ConcreteImplementation();
        SudokuField sudokuField = new SudokuField();
        sudokuField.setFieldValue(3);
        sudokuVerifyType.setSudokuField(1, sudokuField);

        Assertions.assertTrue(sudokuVerifyType.equals(sudokuVerifyType));
    }

    @Test
    void equalsDifferentObjectTest() {
        ConcreteImplementation sudokuVerifyType = new ConcreteImplementation();
        SudokuField sudokuField = new SudokuField();
        sudokuField.setFieldValue(3);

        sudokuVerifyType.setSudokuField(0, sudokuField);

        Assertions.assertFalse(sudokuVerifyType.equals(1));
        Assertions.assertFalse(sudokuVerifyType.equals(null));
    }

    @Test
    void equalsTest() {
        ConcreteImplementation sudokuVerifyTypeFirst = new ConcreteImplementation();
        ConcreteImplementation sudokuVerifyTypeSecond = new ConcreteImplementation();
        SudokuField sudokuFieldFirst = new SudokuField();
        SudokuField sudokuFieldSecond = new SudokuField();

        sudokuFieldFirst.setFieldValue(2);
        sudokuFieldSecond.setFieldValue(3);

        sudokuVerifyTypeFirst.setSudokuField(0, sudokuFieldFirst);
        sudokuVerifyTypeSecond.setSudokuField(1, sudokuFieldSecond);

        Assertions.assertFalse(sudokuVerifyTypeFirst.equals(sudokuVerifyTypeSecond));
    }

    @Test
    void hashCodeTest() {
        ConcreteImplementation sudokuVerifyTypeFirst = new ConcreteImplementation();
        ConcreteImplementation sudokuVerifyTypeSecond = new ConcreteImplementation();
        SudokuField sudokuFieldFirst = new SudokuField();
        SudokuField sudokuFieldSecond = new SudokuField();

        sudokuFieldFirst.setFieldValue(2);
        sudokuFieldSecond.setFieldValue(2);

        sudokuVerifyTypeFirst.setSudokuField(0, sudokuFieldFirst);
        sudokuVerifyTypeSecond.setSudokuField(0, sudokuFieldSecond);

        Assertions.assertEquals(sudokuVerifyTypeFirst.hashCode(), sudokuVerifyTypeSecond.hashCode());
    }

    @Test
    void toStringTest() {
        ConcreteImplementation sudokuVerifyType = new ConcreteImplementation();
        SudokuField sudokuField = new SudokuField();

        sudokuField.setFieldValue(3);

        sudokuVerifyType.setSudokuField(0, sudokuField);

        StringBuilder expected = new StringBuilder();
        expected.append("ConcreteImplementation{sudokuFields=[");
        for (int i = 0; i < 9; i++) {
            try {
                expected.append(sudokuVerifyType.getSudokuField(i).toString());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            if (i < 8) {
                expected.append(", ");
            }
        }
        expected.append("]}");

        Assertions.assertEquals(sudokuVerifyType.toString(), expected.toString());
    }
}