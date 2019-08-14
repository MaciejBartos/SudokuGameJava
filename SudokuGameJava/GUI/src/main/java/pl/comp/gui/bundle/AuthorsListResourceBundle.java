package pl.comp.gui.bundle;

import java.util.ListResourceBundle;

public class AuthorsListResourceBundle extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"authorWord", "Autor"},
                {"authorName", "Maciej Bartos"}
        };
    }
}
