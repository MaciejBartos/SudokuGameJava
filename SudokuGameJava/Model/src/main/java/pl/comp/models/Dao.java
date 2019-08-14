package pl.comp.models;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Dao<T> {
    T read();

    void write(T object);
}
