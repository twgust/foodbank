package view;

import com.sun.java.accessibility.util.GUIInitializedListener;
import controller.Controller;
import entity.IngredientAmount;
import entity.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/*
 * Denna klass inneh�ller gr�nssnittet mot anv�ndaren som ska s�ka p� ingredienser, l�gga till ingredienser, l�gga till recept och s�ka p� recept.
 * Instruktioner ska d� visas f�r receptet.
 */

public class CreateRecipeView extends JFrame implements ActionListener {

    Container con = getContentPane();
    ArrayList<IngredientAmount> ingList = new ArrayList<>();
    ArrayList<Product> prodList;

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
    JButton btnSearch = new JButton("Search");
    JList<String> searchList = new JList<String>();
    JTextField tfSearchRec = new JTextField();
    JLabel lblAmt = new JLabel("M�ngd");
    JTextField tfAmt = new JTextField();
    JButton btnAddIngredientToRecipe = new JButton("L�gg till ingrediens i recept");

    //Ingredienslista och l�gg till recept
    JLabel lblIng = new JLabel("Ingredienser");
    JList<String> listIng = new JList<String>();
    JButton btnAddRecipe = new JButton("L�gg till recept");


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


        searchList.setModel(listSearchModel);
        listIng.setModel(testListModel);

    }


    public String getSearchRep() {
        return tfSearchRec.getText();
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


    //Funktionalitet knappar
    @Override
    //knapp f�r att s�ka i DB efter s�kruta
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {

            controller.getSearch();

        }

        if (e.getSource() == btnAddIng) {
            String name = tfAddIng.getText();
            if(!containsOnlyLetters(name) || name.length() > 150){
                JOptionPane.showMessageDialog(null, "Namn f�r endast best� av bokst�ver a-�, maxl�ngd 150");
                return;
            }
            float price;
            try {
                price = Float.parseFloat(tfPris.getText());
            }catch (Exception x){
                JOptionPane.showMessageDialog(null, "Pris skrivs in p� formatet XX.XX, tex 14.99");
                return;
            }
            String unit = (String) cbEnhet.getSelectedItem();

            controller.addIngredient(name, price, unit);

            tfAddIng.setText("");
            tfPris.setText("");
            tfEnhet.setText("");
        }

        //knapp som l�gger till ingrediens i receptet i GUI
        if (e.getSource() == btnAddIngredientToRecipe) {

            String ingredient = searchList.getSelectedValue();      //h�mtar markerad ingrediens

            float amount;
            try {
                amount = Float.parseFloat(tfAmt.getText());      //h�mtar m�ngd av markerad ingrediens
            }catch(Exception f){
                JOptionPane.showMessageDialog(null, "M�ngd skrivs in p� formatet XX.XX, tex 3.25");
                return;
            }

            int index = searchList.getSelectedIndex();
            ingList.add(new IngredientAmount(prodList.get(index).getId(), amount));

            String unit = prodList.get(index).getUnit();
            String total = amount + " " + unit + " " + ingredient ;             //sparar ingrediens, enhet och m�ngd

            testListModel.addElement(total);                        //l�gger till p� ny rad i inneh�ll

            tfAmt.setText("");                                      //rensar textfield m�ngd


            }

        //knapp som l�gger till ett recept i DB via GUI
            if (e.getSource() == btnAddRecipe) {

                //Gets and verifies that recipeName is valid
                String recipeName = tfRecipename.getText();
                if(!containsOnlyLetters(recipeName)){
                    JOptionPane.showMessageDialog(null, "Namn f�r endast best� av bokst�ver a-�");
                    return;
                }

                //Gets and verifies  that portions is valid
                int portions;
                try {
                    portions = Integer.parseInt(tfPortion.getText());
                }catch(Exception y){
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


        }

    }

