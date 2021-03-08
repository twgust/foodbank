package view;

import controller.Controller;
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
    ArrayList<IngredientAmount> ingList = new ArrayList<>();
    ArrayList<Product> prodList;
    ArrayList<Recipe> recList;
    Boolean showRecipe = false;

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

    JLabel lblIngredients = new JLabel("Ingredienser");
    JList<String> listIngredients = new JList<String>();

    JButton btnAddRecipe = new JButton("Lägg till recept");


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

    JTextField tfSeeRecipe = new JTextField();
    JButton btnSeeRecipe = new JButton("Sök på recept");

    JList<String> listSeeRecipe = new JList<String>();

    JList<String> listSeeInstructions = new JList<>();
    JList<String> listSeeIngredients = new JList<>();

    JLabel lblSeeIngredients = new JLabel("Ingredienser");
    JLabel lblSeeInstructions = new JLabel("Instruktioner");

    JButton btnChangeRecipe = new JButton("Ändra recept");
    JButton btnDeleteRecipe = new JButton("Radera recept");



    //Buttons for show/add recipe
    JButton btnShowRecipe = new JButton("Visa recept");
    JButton btnCreateRecipe = new JButton("Skapa recept");

    //GUI-stuff
    DefaultListModel listSearchModel = new DefaultListModel();
    DefaultListModel testListModel = new DefaultListModel();
    JScrollPane searchScrollPane = new JScrollPane(listSearchIngredients);
    JScrollPane instructions = new JScrollPane(instructionsArea);
    JScrollPane recipeList = new JScrollPane(listIngredients);
    JScrollPane listSeeRecipeScrollPane = new JScrollPane(listSeeRecipe);

    Controller controller = null;



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
        sepHorizontal.setBounds(550,130, 600, 100);
        sepVertical.setBounds(450, 0, 200, 800);


        //Rubrik: Lägg till recept
        lblHeadAddRecipe.setBounds(30,20,200,30);
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

            //Höger kolumn i lägg till recept
        lblSearchIngredients.setBounds(320, 50, 200, 30);
        tfSearchIngredients.setBounds(320, 70, 200, 30);

        btnSearchIngredients.setBounds(320, 100, 200, 30);

        searchScrollPane.setBounds(320, 180, 200, 300);

        lblAmount.setBounds(320, 500, 200, 30);
        tfAmount.setBounds(320, 520, 200, 30);

        btnAddIngredientToRecipe.setBounds(320, 570, 200, 35);



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



        //Rubrik: Se recept
        lblHeadSeeRecipe.setBounds(600, 190, 200, 30);
        lblHeadSeeRecipe.setForeground(Color.red);

        tfSeeRecipe.setBounds(600, 220, 200, 30);
        btnSeeRecipe.setBounds(830, 215, 200, 35);

        listSeeRecipeScrollPane.setBounds(600, 270, 300, 100);

        lblSeeIngredients.setBounds(600, 380, 220, 30);
        lblSeeInstructions.setBounds(870, 380, 220, 30);

        listSeeIngredients.setBounds(600, 420, 190, 250);
        listSeeInstructions.setBounds(870, 420, 190, 250);

        btnChangeRecipe.setBounds(940, 275, 130, 35);
        btnDeleteRecipe.setBounds(940, 325, 130, 35);


       // btnShowRecipe.setBounds(580, 25, 120, 30);
       // btnCreateRecipe.setBounds(420, 25, 120, 30);
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

            //Höger kolumn i lägg till recept
        con.add(lblSearchIngredients);
        con.add(tfSearchIngredients);
        con.add(btnSearchIngredients);

        con.add(searchScrollPane);

        con.add(lblAmount);
        con.add(tfAmount);

        con.add(btnAddIngredientToRecipe);



        //Rubrik: Lägg till livsmedel
        con.add(lblHeadAddGroceries);
        con.add(tfAddGroceries);
        con.add(lblAddGroceries);

        con.add(lblPrice);
        con.add(tfPrice);

        con.add(lblUnit);
        con.add(cbUnit);

        con.add(btnAddGroceries);



        //Rubrik: Se recept
        con.add(lblHeadSeeRecipe);

        con.add(tfSeeRecipe);
        con.add(btnSeeRecipe);

        con.add(listSeeRecipeScrollPane);

        con.add(listSeeIngredients);
        con.add(listSeeInstructions);

        con.add(lblSeeIngredients);
        con.add(lblSeeInstructions);

        con.add(btnChangeRecipe);
        con.add(btnDeleteRecipe);

        //con.add(btnShowRecipe);
       // con.add(btnCreateRecipe);

        listSearchIngredients.setModel(listSearchModel);
        listIngredients.setModel(testListModel);
    }




    /*
    Gets and validates search term for ingredient from GUI
     */
    public String getSearchRep() {
        String search = tfSearchIngredients.getText();
        if(containsOnlyLetters(search))
            return search;
        else{
            JOptionPane.showMessageDialog(null, "Sök med endast bokstäver eller bokstäver och blanksteg");
            return null;
        }
    }

    public DefaultListModel getListIngModel() {
        return listSearchModel;
    }

    public DefaultListModel getIngredientListModel() {
        return testListModel;
    }

    public JList getList() {
        return listSearchIngredients;
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
        btnShowRecipe.addActionListener(this);
        btnCreateRecipe.addActionListener(this);
        listSearchIngredients.addListSelectionListener(new ListListener());
    }

    /*
    Checks if a string contains only letters and spaces
     */
    private boolean containsOnlyLetters(String str){
        if (str == null || str.equals("")) {
            return false;
        }
        for(int i = 0; i < str.length(); i++) {
            if(!Character.isLetter(str.charAt(i)) && str.charAt(i) != ' '){
                return false;
            }
        }
        return true;
    }

    private void updateRecipeList(){
        listSearchModel.clear();
        for(int i = 0; i < recList.size(); i++){
            listSearchModel.addElement(recList.get(i).getName());
        }
    }


    //Funktionalitet knappar
    @Override
    //knapp för att söka i DB efter sökruta
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearchIngredients) {
            if (!showRecipe) {
                controller.searchIngredient();
            }
            if (showRecipe){
                recList = controller.searchRecipe(getSearchRep());
                updateRecipeList();
            }
        }

        if (e.getSource() == btnAddGroceries) {

            //Gets and verifies that ingredient name is valid
            String name = tfAddGroceries.getText();
            if(!containsOnlyLetters(name) || name.length() > 150){
                JOptionPane.showMessageDialog(null, "Namn får endast bestå av bokstäver a-ö, maxlängd 150");
                return;
            }

            //Gets and verifies that price is valid
            float price;
            try {
                price = Float.parseFloat(tfPrice.getText());
            }catch (Exception x){
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

            //Confirms action to user
            JOptionPane.showMessageDialog(null, "Ingrediens tillagd!");
        }

        //knapp som lägger till ingrediens i receptet i GUI
        if (e.getSource() == btnAddIngredientToRecipe) {

            //Gets selected ingredient name from list
            String ingredient = listSearchIngredients.getSelectedValue();

            //Gets and verifies that amount is valid
            float amount;
            try {
                amount = Float.parseFloat(tfAmount.getText());
            }catch(Exception f){
                JOptionPane.showMessageDialog(null, "Mängd skrivs in på formatet XX.XX, tex 3.25");
                return;
            }

            //Saves added ingredient in list
            int index = listSearchIngredients.getSelectedIndex();
            ingList.add(new IngredientAmount(prodList.get(index).getId(), amount));

            String unit = prodList.get(index).getUnit();

            //Displays added ingredients in GUI
            String total = amount + " " + unit + " " + ingredient ;
            testListModel.addElement(total);

            //Clear field
            tfAmount.setText("");


            }

        //knapp som lägger till ett recept i DB via GUI
            if (e.getSource() == btnAddRecipe) {

                if (!showRecipe) {
                    //Gets and verifies that recipeName is valid
                    String recipeName = tfRecipename.getText();
                    if (!containsOnlyLetters(recipeName)) {
                        JOptionPane.showMessageDialog(null, "Namn får endast bestå av bokstäver a-ö");
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

                    controller.addRecipe(recipeName, portions, description, ingList);
                    ingList.clear();

                    //Clear fields
                    tfRecipename.setText("");
                    tfPortion.setText("");
                    instructionsArea.setText("");

                    //Confirm action to user
                    JOptionPane.showMessageDialog(null, "Recept tillagt!");

                    //Test
                    System.out.println(recipeName);
                    System.out.println(portions);
                    System.out.println(description);
                    System.out.println(testListModel);          //hämtar JList med receptingredienser
                }
                if (showRecipe){
                    int index = listSearchIngredients.getSelectedIndex();
                    controller.deleteRecipe(recList.get(index).getRecipeID());
                    updateRecipeList();
                }

            }
            if(e.getSource() == btnShowRecipe){
                showRecipe = true;
                tfRecipename.setText("");
                tfPortion.setText("");
                instructionsArea.setText("");
                tfRecipename.setEnabled(false);
                tfPortion.setEnabled(false);
                instructionsArea.setEnabled(false);
                tfAmount.setEnabled(false);
                lblSearchIngredients.setText("Sök recept");
                btnAddRecipe.setText("Radera recept");

                recList = controller.getAllRecipes();
                updateRecipeList();
            }

            if(e.getSource() == btnCreateRecipe){
                showRecipe = false;
                tfRecipename.setEnabled(true);
                tfPortion.setEnabled(true);
                instructionsArea.setEnabled(true);
                tfAmount.setEnabled(true);
                lblSearchIngredients.setText("Sök ingredienser");
                btnAddRecipe.setText("Lägg till recept");
                listSearchModel.clear();
                testListModel.clear();
            }



        }

        private class ListListener implements ListSelectionListener{

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!showRecipe) {
                    int index = listSearchIngredients.getSelectedIndex();
                    if (index > -1) {
                        String unit = prodList.get(index).getUnit();
                        lblAmount.setText("Ange mängd i " + unit);
                    } else {
                        lblAmount.setText("Mängd");
                    }
                }
                if(showRecipe){
                    int index = listSearchIngredients.getSelectedIndex();
                    testListModel.clear();
                    if(index > -1){
                        Recipe recipe = recList.get(index);
                        int recipeID = recipe.getRecipeID();
                        HashMap<Product,IngredientAmount> map = controller.getRecipeIngredients(recipeID);
                        float sum = 0;
                        testListModel.addElement("Portioner: " + recipe.getPortions());
                        for(Map.Entry<Product, IngredientAmount> entry: map.entrySet()) {
                            Product prod  = entry.getKey();
                            IngredientAmount ing = entry.getValue();
                            float pricePerUnit = prod.getProd_price();
                            float amount = ing.getAmount();
                            float itemPrice = pricePerUnit * amount;
                            sum += itemPrice;
                            String itemString = prod.getProd_name() + " " + ing.getAmount() + " " + prod.getUnit() + " ~ " + itemPrice + "kr";
                            testListModel.addElement(itemString);
                        }
                        testListModel.addElement("Pris för recept: " + sum + "kr");

                    }
                }
            }
        }

    }

