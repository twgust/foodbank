package controller;

import view.RecipeInserterGUI;

public class Main {
    public static void main(String[] args) {
        Connector connector = new Connector();
        RecipeInserterGUI recGui = new RecipeInserterGUI(connector);
    }
}
