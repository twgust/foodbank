package view;
import controller.Controller;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    Controller controller;
    CreateRecipeView view = new CreateRecipeView(controller);

    @Test
    public void emptySearch() {
        assertEquals("", view.getSearchRep() == "");
    }
    @Test
    public void invalidSearch() {
        assertEquals("", view.getSearchRep());
    }
}
