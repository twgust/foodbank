package view;

import com.sun.java.accessibility.util.GUIInitializedListener;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/*
 * Denna klass innehåller gränssnittet mot användaren som ska söka på ingredienser, lägga till ingredienser, lägga till recept och söka på recept.
 * Instruktioner ska då visas för receptet.
 */

public class CreateRecipeView extends JFrame implements ActionListener {

    Container con = getContentPane();

    //Namn på recept och instruktioner
    JLabel lblRecipe = new JLabel("Receptnamn");
    JTextField tfRecipename = new JTextField();
    JLabel lblPortion = new JLabel("Antal portioner");
    JTextField tfPortion = new JTextField();
    JLabel lblInstruction = new JLabel("Tillagningsinstruktioner");
    JTextArea instructionsArea = new JTextArea("");

    //Lägg till livsmedel
    JLabel lblAddIng = new JLabel("Ingrediens");
    JTextField tfAddIng = new JTextField("Ingrediens här");
    JLabel lblPris = new JLabel("Pris/enhet");
    JTextField tfPris = new JTextField("Pris här");
    JLabel lblEnhet = new JLabel("Enhet");
    JTextField tfEnhet = new JTextField("Enhet här");
    JButton btnAddIng = new JButton("Lägg till Ingrediens i DB");

    //Sök på ingrediens
    JLabel lblSearch = new JLabel("Sök ingredienser");
    JTextField tfSearch = new JTextField();
    JButton btnSearch = new JButton("Search");
    JList<String> searchList = new JList<String>();
    JTextField tfSearchRec = new JTextField();
    JLabel lblAmt = new JLabel("Mängd");
    JTextField tfAmt = new JTextField();
    JButton btnAddIngredientToRecipe = new JButton("Lägg till ingrediens i recept");

    //Ingredienslista och lägg till recept
    JLabel lblIng = new JLabel("Ingredienser");
    JList<String> listIng = new JList<String>();
    JButton btnAddRecipe = new JButton("Lägg till recept");


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
    Placerar rutans komponenter på given plats med koordinater(X,Y) och storlekar(W,H).
     */
    private void setLayout() {

        //Namn på recept och instruktioner
        lblRecipe.setBounds(60, 85, 200, 30);
        tfRecipename.setBounds(60, 110, 200, 30);
        lblPortion.setBounds(60, 135, 200, 30);
        tfPortion.setBounds(60, 160, 200, 30);
        lblInstruction.setBounds(60, 190, 200, 30);
        instructionsArea.setBounds(60, 220, 200, 300);

        //Lägg till ingrediens i DB
        lblAddIng.setBounds(60, 550, 200, 35);
        tfAddIng.setBounds(60, 580, 200, 35);
        lblPris.setBounds(60, 620, 200, 35);
        tfPris.setBounds(60, 650, 200, 35);
        lblEnhet.setBounds(280, 550, 200, 35);
        tfEnhet.setBounds(280, 580, 200, 35);
        btnAddIng.setBounds(280, 650, 200, 35);


        //Sök på ingrediens
        lblSearch.setBounds(340, 85, 200, 30);
        tfSearchRec.setBounds(340, 110, 200, 30);
        btnSearch.setBounds(340, 160, 200, 30);
        searchScrollPane.setBounds(340, 220, 200, 300);

        lblAmt.setBounds(580, 220, 200, 35);
        tfAmt.setBounds(580, 250, 200, 35);

        btnAddIngredientToRecipe.setBounds(580, 300, 200, 35);


        //Ingredienslista och lägg till recept
        lblIng.setBounds(800, 100, 280, 35);
        listIng.setBounds(800, 130, 280, 400);
        btnAddRecipe.setBounds(850, 550, 200, 35);

    }


    /*
    Lägger till alla komponenter i Jframen.
     */
    public void addComponents() {

        //Namn på recept och instruktioner
        con.add(lblRecipe);
        con.add(tfRecipename);
        con.add(lblPortion);
        con.add(tfPortion);
        con.add(lblInstruction);
        con.add(instructionsArea);

        //Lägg till ingrediens i DB
        con.add(tfAddIng);
        con.add(lblAddIng);
        con.add(lblPris);
        con.add(tfPris);
        con.add(lblEnhet);
        con.add(tfEnhet);
        con.add(btnAddIng);

        //Sök på ingrediens
        con.add(lblSearch);
        con.add(tfSearchRec);
        con.add(btnSearch);
        con.add(searchScrollPane);

        con.add(lblAmt);
        con.add(tfAmt);
        con.add(btnAddIngredientToRecipe);

        //Lägg till recept
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

    //ActionListener för knapparna
    public void btnActions() {
        btnSearch.addActionListener(this);
        btnAddIng.addActionListener(this);
        btnAddIngredientToRecipe.addActionListener(this);
        btnAddRecipe.addActionListener(this);
    }


    //Funktionalitet knappar
    @Override
    //knapp för att söka i DB efter sökruta
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            controller.getSearch();
        }

        if (e.getSource() == btnAddIng) {
            String prod_name = tfAddIng.getText();
            String price = tfPris.getText();
            String unit = tfEnhet.getText();
            int price2 = Integer.parseInt(price);
            try {
                controller.addIngredient(prod_name, price2, unit);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        //knapp som lägger till ingrediens i receptet i GUI
        if (e.getSource() == btnAddIngredientToRecipe) {

            String ingredient = searchList.getSelectedValue();      //hämtar markerad ingrediens

            String amount = tfAmt.getText();                        //hämtar mängd av markerad ingrediens
            int amount2 = Integer.parseInt(amount);

            String total = amount2 + " " + ingredient ;             //sparar ingrediens + mängd

            testListModel.addElement(total);                        //lägger till på ny rad i innehåll

            tfAmt.setText("");                                      //rensar textfield mängd

            }

        //knapp som lägger till ett recept i DB via GUI
            if (e.getSource() == btnAddRecipe) {

                String recipeName = tfRecipename.getText();     //hämtar receptnamn

                String portions = tfPortion.getText();          //hämtar antal portioner
                int portions2 = Integer.parseInt(portions);

                String description = instructionsArea.getText();  //hämtar instruktioner

                //Test
                System.out.println(recipeName);
                System.out.println(portions2);
                System.out.println(description);
                System.out.println(testListModel);          //hämtar JList med receptingredienser

            }


        }

    }

