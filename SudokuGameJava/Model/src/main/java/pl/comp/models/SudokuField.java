package pl.comp.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    private int value;

    public SudokuField() {
        this.value = 0;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
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
        SudokuField that = (SudokuField) o;
        return value == that.value;

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public int compareTo(SudokuField o) {
        return Integer.compare(this.getFieldValue(), o.getFieldValue());
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
