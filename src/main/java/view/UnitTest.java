package view;
import controller.Controller;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    Controller controller;
    CreateRecipeView view = new CreateRecipeView(controller);



    @Test
    public void inputTestSearch(){
        String s = "abc";
        assertEquals(true, view.containsOnlyLetters(s) );
    }
    @Test
    public void invalidInputSearch() {
        String s = "!!??";
        assertEquals(false, view.containsOnlyLetters(s));
    }


}
