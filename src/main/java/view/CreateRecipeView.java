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
 * Denna klass inneh�ller gr�nssnittet mot anv�ndaren som ska s�ka p� ingredienser, l�gga till ingredienser, l�gga till recept och s�ka p� recept.
 * Instruktioner ska d� visas f�r receptet.
 */

public class CreateRecipeView extends JFrame implements ActionListener {

    Container con = getContentPane();
    ArrayList<IngredientAmount> ingList = new ArrayList<>();
    ArrayList<Product> prodList;
    ArrayList<Recipe> recList;
    Boolean showRecipe = false;

    //Namn p� recept och instruktioner
    JLabel lblRecipe = new JLabel("Receptnamn");
    JTextField tfRecipename = new JTextField();
    JLabel lblPortion = new JLabel("Antal portioner");
    JTextField tfPortion = new JTextField();
    JLabel lblInstruction = new JLabel("Tillagningsinstruktioner");
    JTextArea instructionsArea = new JTextArea("");

    //L�gg till livsmedel
    JLabel lblAddIng = new JLabel("Ingrediens");
    JTextField tfAddIng = new JTextField("Ingrediens h�r");
    JLabel lblPris = new JLabel("Pris/enhet");
    JTextField tfPris = new JTextField("Pris h�r");
    JLabel lblEnhet = new JLabel("Enhet");
    JTextField tfEnhet = new JTextField("Enhet h�r");
    String[] enheter = {"kg", "lit", "st", "gram", "milligram", "dl", "ml", "matsked", "tesked", "kryddm�tt"};
    JComboBox<String> cbEnhet = new JComboBox<>(enheter);
    JButton btnAddIng = new JButton("L�gg till Ingrediens i DB");

    //S�k p� ingrediens
    JLabel lblSearch = new JLabel("S�k ingredienser");
    JTextField tfSearch = new JTextField();
    JButton btnSearch = new JButton("S�k");
    JList<String> searchList = new JList<String>();
    JTextField tfSearchRec = new JTextField();
    JLabel lblAmt = new JLabel("M�ngd");
    JTextField tfAmt = new JTextField();
    JButton btnAddIngredientToRecipe = new JButton("L�gg till ingrediens i recept");

    //Ingredienslista och l�gg till recept
    JLabel lblIng = new JLabel("Ingredienser");
    JList<String> listIng = new JList<String>();
    JButton btnAddRecipe = new JButton("L�gg till recept");

    //Buttons for show/add recipe
    JButton btnShowRecipe = new JButton("Visa recept");
    JButton btnCreateRecipe = new JButton("Skapa recept");

    //GUI
    DefaultListModel listSearchModel = new DefaultListModel();
    DefaultListModel testListModel = new DefaultListModel();
    JScrollPane searchScrollPane = new JScrollPane(searchList);

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
        setLayout();
        addComponents();
        btnActions();

    }

    /*
    Placerar rutans komponenter p� given plats med koordinater(X,Y) och storlekar(W,H).
     */
    private void setLayout() {

        //Namn p� recept och instruktioner
        lblRecipe.setBounds(60, 85, 200, 30);
        tfRecipename.setBounds(60, 110, 200, 30);
        lblPortion.setBounds(60, 135, 200, 30);
        tfPortion.setBounds(60, 160, 200, 30);
        lblInstruction.setBounds(60, 190, 200, 30);
        instructionsArea.setBounds(60, 220, 200, 300);

        //L�gg till ingrediens i DB
        lblAddIng.setBounds(60, 550, 200, 35);
        tfAddIng.setBounds(60, 580, 200, 35);
        lblPris.setBounds(60, 620, 200, 35);
        tfPris.setBounds(60, 650, 200, 35);
        cbEnhet.setBounds(280, 580, 200, 35);
        lblEnhet.setBounds(280, 550, 200, 35);
        //tfEnhet.setBounds(280, 580, 200, 35);
        btnAddIng.setBounds(280, 650, 200, 35);


        //S�k p� ingrediens
        lblSearch.setBounds(340, 85, 200, 30);
        tfSearchRec.setBounds(340, 110, 200, 30);
        btnSearch.setBounds(340, 160, 200, 30);
        searchScrollPane.setBounds(340, 220, 200, 300);

        lblAmt.setBounds(580, 220, 200, 35);
        tfAmt.setBounds(580, 250, 200, 35);

        btnAddIngredientToRecipe.setBounds(580, 300, 200, 35);


        //Ingredienslista och l�gg till recept
        lblIng.setBounds(800, 100, 280, 35);
        listIng.setBounds(800, 130, 280, 400);
        btnAddRecipe.setBounds(850, 550, 200, 35);

        //Show/create recipe
        btnShowRecipe.setBounds(580, 25, 120, 30);
        btnCreateRecipe.setBounds(420, 25, 120, 30);
    }


    /*
    L�gger till alla komponenter i Jframen.
     */
    public void addComponents() {

        //Namn p� recept och instruktioner
        con.add(lblRecipe);
        con.add(tfRecipename);
        con.add(lblPortion);
        con.add(tfPortion);
        con.add(lblInstruction);
        con.add(instructionsArea);

        //L�gg till ingrediens i DB
        con.add(tfAddIng);
        con.add(lblAddIng);
        con.add(lblPris);
        con.add(tfPris);
        con.add(lblEnhet);
        con.add(cbEnhet);
        con.add(btnAddIng);

        //S�k p� ingrediens
        con.add(lblSearch);
        con.add(tfSearchRec);
        con.add(btnSearch);
        con.add(searchScrollPane);

        con.add(lblAmt);
        con.add(tfAmt);
        con.add(btnAddIngredientToRecipe);

        //L�gg till recept
        con.add(lblIng);
        con.add(listIng);
        con.add(btnAddRecipe);

        //show/create buttons
        con.add(btnShowRecipe);
        con.add(btnCreateRecipe);

        searchList.setModel(listSearchModel);
        listIng.setModel(testListModel);

    }


    /*
    Gets and validates search term for ingredient from GUI
     */
    public String getSearchRep() {
        String search = tfSearchRec.getText();
        if(containsOnlyLetters(search))
            return search;
        else{
            JOptionPane.showMessageDialog(null, "S�k med endast bokst�ver eller bokst�ver och blanksteg");
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
        return searchList;
    }



    public void setProdList(ArrayList<Product> prodList) {
        this.prodList = prodList;
    }

    //ActionListener f�r knapparna
    public void btnActions() {
        btnSearch.addActionListener(this);
        btnAddIng.addActionListener(this);
        btnAddIngredientToRecipe.addActionListener(this);
        btnAddRecipe.addActionListener(this);
        btnShowRecipe.addActionListener(this);
        btnCreateRecipe.addActionListener(this);
        searchList.addListSelectionListener(new ListListener());
    }

    public void setTfSearchRec(String s) {
        tfSearchRec.setText(s);
    }
    /*
    Checks if a string contains only letters and spaces
     */
    public boolean containsOnlyLetters(String str){
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
    //knapp f�r att s�ka i DB efter s�kruta
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            if (!showRecipe) {
                controller.searchIngredient();
            }
            if (showRecipe){
                recList = controller.searchRecipe(getSearchRep());
                updateRecipeList();
            }
        }

        if (e.getSource() == btnAddIng) {

            //Gets and verifies that ingredient name is valid
            String name = tfAddIng.getText();
            if(!containsOnlyLetters(name) || name.length() > 150){
                JOptionPane.showMessageDialog(null, "Namn f�r endast best� av bokst�ver a-�, maxl�ngd 150");
                return;
            }

            //Gets and verifies that price is valid
            float price;
            try {
                price = Float.parseFloat(tfPris.getText());
            }catch (Exception x){
                JOptionPane.showMessageDialog(null, "Pris skrivs in p� formatet XX.XX, tex 14.99");
                return;
            }

            //Gets chosen unit from combobox
            String unit = (String) cbEnhet.getSelectedItem();

            //Adds ingredient to database
            controller.addIngredient(name, price, unit);

            //Clear fields
            tfAddIng.setText("");
            tfPris.setText("");
            tfEnhet.setText("");

            //Confirms action to user
            JOptionPane.showMessageDialog(null, "Ingrediens tillagd!");
        }

        //knapp som l�gger till ingrediens i receptet i GUI
        if (e.getSource() == btnAddIngredientToRecipe) {

            //Gets selected ingredient name from list
            String ingredient = searchList.getSelectedValue();

            //Gets and verifies that amount is valid
            float amount;
            try {
                amount = Float.parseFloat(tfAmt.getText());
            }catch(Exception f){
                JOptionPane.showMessageDialog(null, "M�ngd skrivs in p� formatet XX.XX, tex 3.25");
                return;
            }

            //Saves added ingredient in list
            int index = searchList.getSelectedIndex();
            ingList.add(new IngredientAmount(prodList.get(index).getId(), amount));

            String unit = prodList.get(index).getUnit();

            //Displays added ingredients in GUI
            String total = amount + " " + unit + " " + ingredient ;
            testListModel.addElement(total);

            //Clear field
            tfAmt.setText("");


            }

        //knapp som l�gger till ett recept i DB via GUI
            if (e.getSource() == btnAddRecipe) {

                if (!showRecipe) {
                    //Gets and verifies that recipeName is valid
                    String recipeName = tfRecipename.getText();
                    if (!containsOnlyLetters(recipeName)) {
                        JOptionPane.showMessageDialog(null, "Namn f�r endast best� av bokst�ver a-�");
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

                    String description = instructionsArea.getText();  //h�mtar instruktioner

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
                    System.out.println(testListModel);          //h�mtar JList med receptingredienser
                }
                if (showRecipe){
                    int index = searchList.getSelectedIndex();
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
                tfAmt.setEnabled(false);
                lblSearch.setText("S�k recept");
                btnAddRecipe.setText("Radera recept");

                recList = controller.getAllRecipes();
                updateRecipeList();
            }

            if(e.getSource() == btnCreateRecipe){
                showRecipe = false;
                tfRecipename.setEnabled(true);
                tfPortion.setEnabled(true);
                instructionsArea.setEnabled(true);
                tfAmt.setEnabled(true);
                lblSearch.setText("S�k ingredienser");
                btnAddRecipe.setText("L�gg till recept");
                listSearchModel.clear();
                testListModel.clear();
            }



        }

        private class ListListener implements ListSelectionListener{

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!showRecipe) {
                    int index = searchList.getSelectedIndex();
                    if (index > -1) {
                        String unit = prodList.get(index).getUnit();
                        lblAmt.setText("Ange m�ngd i " + unit);
                    } else {
                        lblAmt.setText("M�ngd");
                    }
                }
                if(showRecipe){
                    int index = searchList.getSelectedIndex();
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
                        testListModel.addElement("Pris f�r recept: " + sum + "kr");

                    }
                }
            }
        }

    }

