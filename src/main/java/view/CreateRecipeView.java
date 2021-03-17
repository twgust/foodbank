package view;

import controller.Controller;
import entity.Ingredient;
import entity.IngredientAmount;
import entity.Product;
import entity.Recipe;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/*
 * Denna klass innehåller gränssnittet mot användaren som ska söka på ingredienser, lägga till ingredienser, lägga till recept och söka på recept.
 * Instruktioner ska då visas för receptet.
 */

public class CreateRecipeView extends JFrame implements ActionListener {

    Container con = getContentPane();

    //Lists containing entity objects that are used and updated continuously.
    ArrayList<Product> prodList;
    ArrayList<Recipe> recList;
    Recipe recipe;
    Ingredient ingredient;
    ArrayList<Ingredient> ingredientsList = new ArrayList<>();
    boolean changeRecipe = false;

    //Separatorer i GUI, de streckade linjerna
    JToolBar.Separator sepHorizontal = new JToolBar.Separator();
    JToolBar.Separator sepVertical = new JToolBar.Separator();


    //Rubrik: Lägg till recept
    JLabel lblHeadAddRecipe = new JLabel("Lägg till recept");

    JLabel lblRecipe = new JLabel("Receptnamn");
    JTextField tfRecipename = new JTextField();

    JLabel lblPortion = new JLabel("Antal portioner");
    JTextField tfPortion = new JTextField();

    JLabel lblInstruction = new JLabel("Tillagningsinstruktioner");
    JTextArea instructionsArea = new JTextArea("");

    JLabel lblSearchIngredients = new JLabel("Sök ingredienser");
    JTextField tfSearchIngredients = new JTextField();
    JButton btnSearchIngredients = new JButton("Sök");
    JList<String> listSearchIngredients = new JList<String>();

    JLabel lblAmount = new JLabel("Mängd");
    JTextField tfAmount = new JTextField();

    JButton btnAddIngredientToRecipe = new JButton("Lägg till ingrediens i recept");
    JButton btnChangeProductPrice = new JButton("Ändra pris på livsmedel");
    JButton btnUpdateProductPrice = new JButton("Uppdatera och spara nya priset");

    JLabel lblIngredients = new JLabel("Ingredienser");
    JList<Ingredient> listIngredients = new JList<Ingredient>();

    JButton btnAddRecipe = new JButton("Lägg till recept");
    JButton btnConfirmChangeRecipe = new JButton("Spara");
    JButton btnCancelChangeRecipe = new JButton("Avbryt");
    JButton btnChangeIngredient = new JButton("Spara ny mängd i recept");
    JButton btnDeleteIngredient = new JButton("Ta bort från recept");


    //Rubrik: Lägg till livsmedel
    JLabel lblHeadAddGroceries = new JLabel("Lägg till livsmedel");

    JLabel lblAddGroceries = new JLabel("Livsmedel");
    JTextField tfAddGroceries = new JTextField();

    JLabel lblPrice = new JLabel("Pris/enhet");
    JTextField tfPrice = new JTextField();

    JLabel lblUnit = new JLabel("Enhet");
    JTextField tfUnit = new JTextField();
    String[] units = {"kg", "lit", "st", "gram", "milligram", "dl", "ml", "matsked", "tesked", "kryddmått"};
    JComboBox<String> cbUnit = new JComboBox<>(units);

    JButton btnAddGroceries = new JButton("Lägg till livsmedel");


    //Rubrik: Se recept
    JLabel lblHeadSeeRecipe = new JLabel("Se recept");

    JTextField tfSeeRecipe = new JTextField("");
    JButton btnSeeRecipe = new JButton("Sök på recept");

    JList<String> listSeeRecipe = new JList<String>();

    JTextArea seeDescriptionTextArea = new JTextArea();
    //  JList<String> listSeeInstructions = new JList<>();
    JList<String> listSeeIngredients = new JList<>();

    JLabel lblSeeIngredients = new JLabel("Ingredienser");
    JLabel lblSeeInstructions = new JLabel("Instruktioner");

    JButton btnChangeRecipe = new JButton("Ändra recept");
    JButton btnDeleteRecipe = new JButton("Radera recept");


    //GUI-stuff
    DefaultListModel listSearchModel = new DefaultListModel();
    DefaultListModel testListModel = new DefaultListModel();
    DefaultListModel recipeListModel = new DefaultListModel();
    DefaultListModel ingredientListModel = new DefaultListModel();
    JScrollPane searchScrollPane = new JScrollPane(listSearchIngredients);
    JScrollPane instructions = new JScrollPane(instructionsArea);
    JScrollPane recipeList = new JScrollPane(listIngredients);
    JScrollPane listSeeRecipeScrollPane = new JScrollPane(listSeeRecipe);

    Controller controller;


    //GUI
    public CreateRecipeView(Controller c) {
        controller = c;

        setTitle("Recept");
        setBounds(10, 10, 1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayoutManager();
    }

    //GUI
    public void setLayoutManager() {
        con.setLayout(null);
        sepVertical.setOrientation(SwingConstants.VERTICAL);  //gör en av separatorerna vertikal
        setLayout();
        addComponents();
        btnActions();
    }

    /*
    Placerar rutans komponenter på given plats med koordinater(X,Y) och storlekar(W,H).
     */
    private void setLayout() {

        //Separatorer
        sepHorizontal.setBounds(550, 130, 600, 100);
        sepVertical.setBounds(450, 0, 200, 800);


        //Rubrik: Lägg till recept
        lblHeadAddRecipe.setBounds(30, 20, 200, 30);
        lblHeadAddRecipe.setForeground(Color.red);

        lblRecipe.setBounds(30, 50, 200, 30);
        tfRecipename.setBounds(30, 70, 200, 30);

        lblPortion.setBounds(30, 100, 200, 30);
        tfPortion.setBounds(30, 120, 200, 30);

        lblInstruction.setBounds(30, 150, 200, 30);
        instructions.setBounds(30, 180, 200, 220);

        lblIngredients.setBounds(30, 400, 280, 35);
        recipeList.setBounds(30, 430, 200, 200);
        btnAddRecipe.setBounds(30, 650, 200, 35);
        btnConfirmChangeRecipe.setBounds(30, 700, 90, 35);
        btnConfirmChangeRecipe.setEnabled(false);
        btnCancelChangeRecipe.setBounds(140, 700, 90, 35);
        btnCancelChangeRecipe.setEnabled(false);
        btnChangeIngredient.setBounds(320, 650, 200, 35);
        btnChangeIngredient.setEnabled(false);
        btnDeleteIngredient.setBounds(320, 700, 200, 35);
        btnDeleteIngredient.setEnabled(false);

        //Höger kolumn i lägg till recept
        lblSearchIngredients.setBounds(320, 50, 200, 30);
        tfSearchIngredients.setBounds(320, 70, 200, 30);

        btnSearchIngredients.setBounds(320, 100, 200, 30);

        searchScrollPane.setBounds(320, 180, 200, 300);

        lblAmount.setBounds(320, 500, 200, 30);
        tfAmount.setBounds(320, 520, 200, 30);

        btnAddIngredientToRecipe.setBounds(320, 570, 200, 35);
        btnChangeProductPrice.setBounds(320, 610, 200, 35);
        btnChangeProductPrice.setEnabled(false);


        //Rubrik: Lägg till livsmedel
        lblHeadAddGroceries.setBounds(600, 20, 200, 30);
        lblHeadAddGroceries.setForeground(Color.red);

        lblAddGroceries.setBounds(600, 50, 200, 30);
        tfAddGroceries.setBounds(600, 70, 200, 30);

        lblPrice.setBounds(600, 100, 200, 30);
        tfPrice.setBounds(600, 120, 200, 30);

        lblUnit.setBounds(830, 50, 200, 30);
        cbUnit.setBounds(830, 70, 200, 30);
        //tfEnhet.setBounds(280, 580, 200, 35);

        btnAddGroceries.setBounds(830, 115, 200, 35);
        btnUpdateProductPrice.setBounds(830, 115, 200, 35);
        btnUpdateProductPrice.setVisible(false);


        //Rubrik: Se recept
        lblHeadSeeRecipe.setBounds(600, 190, 200, 30);
        lblHeadSeeRecipe.setForeground(Color.red);

        tfSeeRecipe.setBounds(600, 220, 200, 30);
        btnSeeRecipe.setBounds(830, 215, 200, 35);

        listSeeRecipeScrollPane.setBounds(600, 270, 300, 100);

        lblSeeIngredients.setBounds(600, 380, 220, 30);
        lblSeeInstructions.setBounds(870, 380, 220, 30);

        listSeeIngredients.setBounds(600, 420, 190, 250);
        seeDescriptionTextArea.setBounds(870, 420, 190, 250);

        btnChangeRecipe.setBounds(940, 275, 130, 35);
        btnDeleteRecipe.setBounds(940, 325, 130, 35);

        instructionsArea.setLineWrap(true);
    }


    /*
    Lägger till alla komponenter i Jframen.
     */
    public void addComponents() {

        //Separatorer
        con.add(sepHorizontal);
        con.add(sepVertical);

        //Rubrik: Lägg till recept
        con.add(lblHeadAddRecipe);

        con.add(lblRecipe);
        con.add(tfRecipename);

        con.add(lblPortion);
        con.add(tfPortion);

        con.add(lblInstruction);
        con.add(instructions);

        con.add(lblIngredients);
        con.add(recipeList);

        con.add(btnAddRecipe);
        con.add(btnConfirmChangeRecipe);
        con.add(btnCancelChangeRecipe);
        con.add(btnChangeIngredient);
        con.add(btnDeleteIngredient);

        //Höger kolumn i lägg till recept
        con.add(lblSearchIngredients);
        con.add(tfSearchIngredients);
        con.add(btnSearchIngredients);

        con.add(searchScrollPane);

        con.add(lblAmount);
        con.add(tfAmount);

        con.add(btnAddIngredientToRecipe);
        con.add(btnChangeProductPrice);


        //Rubrik: Lägg till livsmedel
        con.add(lblHeadAddGroceries);
        con.add(tfAddGroceries);
        con.add(lblAddGroceries);

        con.add(lblPrice);
        con.add(tfPrice);

        con.add(lblUnit);
        con.add(cbUnit);

        con.add(btnAddGroceries);
        con.add(btnUpdateProductPrice);


        //Rubrik: Se recept
        con.add(lblHeadSeeRecipe);

        con.add(tfSeeRecipe);
        con.add(btnSeeRecipe);

        con.add(listSeeRecipeScrollPane);

        con.add(listSeeIngredients);
        con.add(seeDescriptionTextArea);

        con.add(lblSeeIngredients);
        con.add(lblSeeInstructions);

        con.add(btnChangeRecipe);
        con.add(btnDeleteRecipe);

        listSearchIngredients.setModel(listSearchModel);
        listSeeIngredients.setModel(testListModel);
        listSeeRecipe.setModel(recipeListModel);
        listIngredients.setModel(ingredientListModel);
        seeDescriptionTextArea.setLineWrap(true);
    }


    /*
    Gets and validates search term for ingredient from GUI
     */
    public String getSearchRep() {
        String search = tfSearchIngredients.getText();
        if (containsOnlyLetters(search))
            return search;
        else {
            JOptionPane.showMessageDialog(null, "Sök med endast bokstäver eller bokstäver och blanksteg");
            return null;
        }
    }

    public DefaultListModel getListIngModel() {
        return listSearchModel;
    }


    public void setProdList(ArrayList<Product> prodList) {
        this.prodList = prodList;
    }

    //ActionListener för knapparna
    public void btnActions() {
        btnSearchIngredients.addActionListener(this);
        btnAddGroceries.addActionListener(this);
        btnAddIngredientToRecipe.addActionListener(this);
        btnAddRecipe.addActionListener(this);
        btnConfirmChangeRecipe.addActionListener(this);
        btnCancelChangeRecipe.addActionListener(this);
        btnChangeIngredient.addActionListener(this);
        btnDeleteIngredient.addActionListener(this);
        btnSeeRecipe.addActionListener(this);
        btnDeleteRecipe.addActionListener(this);
        btnChangeRecipe.addActionListener(this);
        listSearchIngredients.addListSelectionListener(new IngListListener());
        listSeeRecipe.addListSelectionListener(new RecipeListListener());
        listIngredients.addListSelectionListener(new RecipeIngredientsListListener());
        btnChangeProductPrice.addActionListener(this);
        btnUpdateProductPrice.addActionListener(this);
    }

    /*
    Checks if a string contains only letters and spaces
     */
    private boolean containsOnlyLetters(String str) {
        String regex = "[a-zA-Z åäöÅÄÖ]+";
        return str.matches(regex);
    }

    private boolean containsCorrectCharactes(String description) {

       String regex ="[a-zA-Z åäöÅÄÖ0-9!.,()]+";
        return description.matches(regex);
        }
    /*
    Fills the recipe list in the GUI with the current data in recList
     */
    private void updateRecipeList() {
        recipeListModel.clear();
        for (int i = 0; i < recList.size(); i++) {
            recipeListModel.addElement(recList.get(i).getName());
        }
    }

    //Funktionalitet knappar
    @Override
    //knapp för att söka i DB efter sökruta
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearchIngredients) {
            controller.searchIngredient();
            btnChangeProductPrice.setEnabled(true);
        }

            if (e.getSource() == btnAddGroceries) {

            //Gets and verifies that ingredient name is valid
            String name = tfAddGroceries.getText();
            if (!containsOnlyLetters(name) || name.length() > 150) {
                JOptionPane.showMessageDialog(null, "Namn får endast bestå av bokstäver a-ö, maxlängd 150");
                return;
            }

            //Gets and verifies that price is valid
            float price;
            try {
                price = Float.parseFloat(tfPrice.getText());
            } catch (Exception x) {
                JOptionPane.showMessageDialog(null, "Pris skrivs in på formatet XX.XX, tex 14.99");
                return;
            }

            //Gets chosen unit from combobox
            String unit = (String) cbUnit.getSelectedItem();

            //Adds ingredient to database
            controller.addIngredient(name, price, unit);

            //Clear fields
            tfAddGroceries.setText("");
            tfPrice.setText("");
            tfUnit.setText("");
            ingredientListModel.clear();

            //Confirms action to user
            JOptionPane.showMessageDialog(null, "Ingrediens tillagd!");
        }

        //knapp som lägger till ingrediens i receptet i GUI
        if (e.getSource() == btnAddIngredientToRecipe) {
            int index = listSearchIngredients.getSelectedIndex();

            //Gets and verifies that amount is valid
            if (index > -1) {
                float amount;
                try {
                    amount = Float.parseFloat(tfAmount.getText());
                } catch (Exception f) {
                    JOptionPane.showMessageDialog(null, "Mängd skrivs in på formatet XX.XX, tex 3.25");
                    return;
                }

                Product product                     = prodList.get(index);
                IngredientAmount ingredientAmount   = new IngredientAmount(product.getId(), amount);
                Ingredient ingredientNew            = new Ingredient(product, ingredientAmount);

                ingredientsList.add(ingredientNew);
                ingredientListModel.addElement(ingredientNew);

                //Clear field
                tfAmount.setText("");
            }
        }

        //knapp som lägger till ett recept i DB via GUI
        if (e.getSource() == btnAddRecipe) {
            //Gets and verifies that recipeName is valid
            String recipeName = tfRecipename.getText();
            if (!containsOnlyLetters(recipeName)) {
                JOptionPane.showMessageDialog(null, "Insättning till DB misslyckades.\n" +
                        "Namn får bara innehålla bokstäver och blanksteg.");
                return;
            }

            //Gets and verifies  that portions is valid
            int portions;
            try {
                portions = Integer.parseInt(tfPortion.getText());
            } catch (Exception y) {
                JOptionPane.showMessageDialog(null, "Ange portioner i heltal");
                return;
            }

            String description = instructionsArea.getText();  //hämtar instruktioner
            if(!containsCorrectCharactes(description)){
                JOptionPane.showMessageDialog(null, "Otillåtna tecken i beskrivningen \n"+
                        "recept ej tillagt");
                return;
            }
            if(ingredientsList.isEmpty()){
                JOptionPane.showMessageDialog(null,"Receptet saknar ingredienser \n"+
                        "recept ej tillagt");
                return;
            }

            controller.addRecipe(recipeName, portions, description, ingredientsList);
            ingredientsList.clear();

            //Clear fields
            tfRecipename.setText("");
            tfPortion.setText("");
            instructionsArea.setText("");
            ingredientListModel.clear();

            //Confirm action to user
            JOptionPane.showMessageDialog(null, "Recept tillagt!");

            //Test
            System.out.println(recipeName);
            System.out.println(portions);
            System.out.println(description);
            System.out.println(testListModel);          //hämtar JList med receptingredienser


        }

        if (e.getSource() == btnSeeRecipe) {
            String recipeSearch = tfSeeRecipe.getText();
            if (containsOnlyLetters(recipeSearch)) {
                recList = controller.searchRecipe(recipeSearch);
                updateRecipeList();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Söktermen endast får innehålla bokstäver och blanksteg");
            }
        }

        if (e.getSource() == btnDeleteRecipe) {
            int index = listSeeRecipe.getSelectedIndex();
            if (index > -1) {
                Recipe rec = recList.get(index);
                int recID = rec.getRecipeID();
                controller.deleteRecipe(recID);
                JOptionPane.showMessageDialog(null, "Recept raderat!");
                recList = controller.getAllRecipes();
                updateRecipeList();
            } else {
                JOptionPane.showMessageDialog(null, "Ett recept måste väljas ur listan!");
            }
        }

        if (e.getSource() == btnChangeRecipe) {
            changeRecipe = true;
            int index = listSeeRecipe.getSelectedIndex();
            ingredientListModel.clear();
            ingredientsList.clear();

            if (index > -1) {
                lblHeadAddRecipe.setText("Ändra Recept");
                btnSeeRecipe.setEnabled(false);
                btnChangeRecipe.setEnabled(false);
                btnDeleteRecipe.setEnabled(false);

                recipe = recList.get(index);

                int recipeID                = recipe.getRecipeID();
                String recipeName           = recipe.getName();
                String recipeDescription    = recipe.getDescription();
                int recipePortions          = recipe.getPortions();

                tfRecipename.setText(recipeName);
                instructionsArea.setText(recipeDescription);
                tfPortion.setText(String.valueOf(recipePortions));

                // Jlist will render Object.toString() by default from ingredientListModel.
                // ingredientListModel keeps information about ingredients in current recipe under change.
                HashMap<Product, IngredientAmount> recipeIngredients = controller.getRecipeIngredients(recipeID);
                for (Map.Entry<Product, IngredientAmount> entry : recipeIngredients.entrySet()) {
                    Product product                     = entry.getKey();
                    IngredientAmount ingredientAmount   = entry.getValue();
                    Ingredient ingredient = new Ingredient(product, ingredientAmount);
                    ingredientListModel.addElement(ingredient);
                }

                btnAddRecipe.setEnabled(false);
                btnConfirmChangeRecipe.setEnabled(true);
                btnCancelChangeRecipe.setEnabled(true);
            }
        }

        if (e.getSource() == btnConfirmChangeRecipe) {
            int recipeID        = recipe.getRecipeID();
            String recipeName   = tfRecipename.getText();
            int portions;
            String description  = instructionsArea.getText();

            if (!containsOnlyLetters(recipeName)) {
                JOptionPane.showMessageDialog(null, "Namn får endast bestå av bokstäver a-ö");
                return;
            }

            try {
                portions = Integer.parseInt(tfPortion.getText());
            } catch (Exception y) {
                JOptionPane.showMessageDialog(null, "Ange portioner i heltal");
                return;
            }

            controller.editRecipe(recipeID, recipeName, portions, description, ingredientsList);
            JOptionPane.showMessageDialog(null, "Recept ändrat!");
            recList = controller.getAllRecipes();
            updateRecipeList();

           clearRecipeChangeInfo();
        }

        if (e.getSource() == btnCancelChangeRecipe) {
            clearRecipeChangeInfo();
            changeRecipe = false;
            JOptionPane.showMessageDialog(null,
                    "Observera att ändrade och borttagna ingredienser\n" +
                            "för receptet är sparade i databasen.");
        }

        if (e.getSource() == btnChangeIngredient) {
            btnChangeIngredient.setEnabled(false);
            btnDeleteIngredient.setEnabled(false);
            btnAddIngredientToRecipe.setEnabled(true);

            int indexSelected = listSearchIngredients.getSelectedIndex();
            if (indexSelected > -1) {
                int ingID       = ingredient.getIngredientAmount().getIngredientID();
                int recID       = recipe.getRecipeID();
                float amount;
                try {
                    amount = Float.parseFloat(tfAmount.getText());
                } catch (Exception f) {
                    JOptionPane.showMessageDialog(null, "Mängd skrivs in på formatet XX.XX, tex 3.25");
                    return;
                }

                Product product                     = ingredient.getProduct();
                IngredientAmount ingredientAmount   = new IngredientAmount(ingID, amount);
                Ingredient ingredientNew            = new Ingredient(product, ingredientAmount);

                String ingredientUpdated    = product.getProd_name();
                float amountBefore          = ingredient.getIngredientAmount().getAmount();
                String unit                 = ingredient.getProduct().getUnit();

                controller.editIngredientInRecipe(ingID, recID, amount);
                ingredientListModel.removeElement(ingredient);
                ingredientListModel.addElement(ingredientNew);

                tfAmount.setText("");
                tfSearchIngredients.setText("");
                listSearchModel.clear();
                JOptionPane.showMessageDialog(null, "Mängden " + ingredientUpdated +
                        " uppdaterad i " + recipe.getName() + " från " +
                        amountBefore + " " + unit + " till " + amount + " " + unit +
                        "i databasen. Detta kan ej avbrytas/ångras");
            }
        }

        if (e.getSource() == btnDeleteIngredient) {
            btnChangeIngredient.setEnabled(false);
            btnDeleteIngredient.setEnabled(false);
            btnAddIngredientToRecipe.setEnabled(true);

            int ingID                   = ingredient.getIngredientAmount().getIngredientID();
            int recID                   = recipe.getRecipeID();
            String ingredientRemoved    = ingredient.toString();
            controller.deleteIngredientFromRecipe(ingID, recID);

            ingredientListModel.removeElement(ingredient);

            tfAmount.setText("");
            JOptionPane.showMessageDialog(null, ingredientRemoved +
                    " borttaget från " + recipe.getName() +
                    " i DB. Detta kan ej avbrytas/ångras");
        }

        if (e.getSource() == btnChangeProductPrice) {
            int index = listSearchIngredients.getSelectedIndex();
            Product product = null;
            if (index > -1) {
                btnAddGroceries.setVisible(false);
                btnUpdateProductPrice.setVisible(true);
                String prodName = listSearchIngredients.getSelectedValue();
                product = controller.getProductInfo(prodName);
                tfAddGroceries.setText(product.getProd_name());
                tfPrice.setText(String.valueOf(product.getProd_price()));
                cbUnit.setSelectedItem(product.getUnit());

            } else JOptionPane.showMessageDialog(null, "Välj en ingrediens i listan");


        }
        if (e.getSource() == btnUpdateProductPrice) {

            float price;
            try {
                price = Float.parseFloat(tfPrice.getText());
            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, "Pris måste anges.\n" +
                        "Fel format har angivits. Skriv på format XX.XX, tex 3.25.");
                return;
            }
            controller.editIngredient(controller.getProductID(), tfAddGroceries.getText(),
                    price, (String) cbUnit.getSelectedItem());
            tfPrice.setText("");
            tfAddGroceries.setText("");
            btnUpdateProductPrice.setVisible(false);
            btnAddGroceries.setVisible(true);
        }
    }

    private void clearRecipeChangeInfo() {
        lblHeadAddRecipe.setText("Lägg till Recept");

        tfRecipename.setText("");
        tfPortion.setText("");
        instructionsArea.setText("");

        tfAmount.setText("");
        tfSearchIngredients.setText("");
        listSearchModel.clear();

        recipe = null;
        ingredientListModel.clear();
        ingredient = null;
        ingredientsList.clear();

        btnAddRecipe.setEnabled(true);
        btnConfirmChangeRecipe.setEnabled(false);
        btnCancelChangeRecipe.setEnabled(false);
        btnSeeRecipe.setEnabled(true);
        btnChangeRecipe.setEnabled(true);
        btnDeleteRecipe.setEnabled(true);
        btnAddIngredientToRecipe.setEnabled(true);
        btnChangeIngredient.setEnabled(false);
        btnDeleteIngredient.setEnabled(false);
    }

    /*
    Listener for the ingredient list. Changes the unit asked for depending on the items listed unit.
     */
    private class IngListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int index = listSearchIngredients.getSelectedIndex();
            if (index > -1) {
                String unit = prodList.get(index).getUnit();
                lblAmount.setText("Ange mängd i " + unit);
            } else {
                lblAmount.setText("Mängd");
            }

        }
    }

    /*
    Listener for the recipe list. Displays info about the selected recipe from the list.
     */
    private class RecipeListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int index = listSeeRecipe.getSelectedIndex();
            testListModel.clear();
            if (index > -1) {
                Recipe recipe = recList.get(index);
                int recipeID = recipe.getRecipeID();
                HashMap<Product, IngredientAmount> map = controller.getRecipeIngredients(recipeID);
                float sum = 0;
                testListModel.addElement("Portioner: " + recipe.getPortions());
                for (Map.Entry<Product, IngredientAmount> entry : map.entrySet()) {
                    Product prod = entry.getKey();
                    IngredientAmount ing = entry.getValue();
                    float pricePerUnit = prod.getProd_price();
                    float amount = ing.getAmount();
                    float itemPrice = pricePerUnit * amount;
                    sum += itemPrice;
                    String itemString = prod.getProd_name() + " " + ing.getAmount() + " " + prod.getUnit() + " ~ " + itemPrice + "kr";
                    testListModel.addElement(itemString);
                }
                String description = recipe.getDescription();
                seeDescriptionTextArea.setText(description);
                testListModel.addElement("-------------------------");
                testListModel.addElement("Pris för recept: " + sum + "kr");

            }

        }
    }

    private class RecipeIngredientsListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {

            ingredient = listIngredients.getSelectedValue();

            if (ingredient != null && !e.getValueIsAdjusting()) {
                System.out.println(ingredient.getIngredientAmount().getIngredientID());
                tfSearchIngredients.setText(ingredient.getProduct().getProd_name());
                tfAmount.setText(String.valueOf(ingredient.getIngredientAmount().getAmount()));
                controller.searchIngredient(ingredient.getIngredientAmount().getIngredientID());
                listSearchIngredients.setSelectedIndex(0);
                if (changeRecipe) {
                    btnChangeIngredient.setEnabled(true);
                    btnDeleteIngredient.setEnabled(true);
                }
            } else {
                // Do nothing.
            }
        }
    }

}

