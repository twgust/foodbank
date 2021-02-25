package view;

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

    JLabel lblSearch = new JLabel("Sök ingredienser");
    JTextField tfSearch = new JTextField();
    JButton btnSearch = new JButton("Search");
    JList<String> searchList = new JList<String>();


    JTextField tfSearchRec = new JTextField();

    JTextField tfRecipename = new JTextField();
    JLabel lblRecipe = new JLabel("Namn på recept");
    JLabel lblInstruction = new JLabel("Tillagningsinstruktioner");
    JTextArea instructionsArea = new JTextArea("");

    JLabel lblAmt = new JLabel("Mängd");
    JTextField tfAmt = new JTextField();
    JButton btnAddIng = new JButton("Lägg till Ingrediens i DB");

    JLabel lblAddIng = new JLabel("Ingrediens");
    JTextField tfAddIng = new JTextField("Ingrediens här");

    JLabel lblPris = new JLabel("Pris/enhet");
    JTextField tfPris = new JTextField("Pris här");

    JLabel lblEnhet = new JLabel("Enhet");
    JTextField tfEnhet = new JTextField("Enhet här");

    JLabel lblIng = new JLabel("Ingredienser");
    JList<String> listIng = new JList<String>();
    JButton btnAddRecipe = new JButton("Lägg till recept");

    JButton btnAddIngredientToRecipe = new JButton("Lägg till ingrediens i recept");

    DefaultListModel listSearchModel = new DefaultListModel();
    JScrollPane searchScrollPane = new JScrollPane(searchList);


    Controller controller = null;


    public CreateRecipeView(Controller c) {
        controller = c;

        setTitle("Recept");
        setBounds(10, 10, 1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayoutManager();
    }

    public void setLayoutManager() {
        con.setLayout(null);
        setLayout();
        addComponents();
        btnActions();

    }

    /*
    Placerar rutans komponenter på given plats med koordinater och storlekar.
     */
    private void setLayout() {

        //Namn på recept och instruktioner
        lblRecipe.setBounds(60, 90, 200, 30);
        tfRecipename.setBounds(60, 120, 200, 30);
        lblInstruction.setBounds(60, 190, 200, 30);
        instructionsArea.setBounds(60, 220, 200, 300);

        //Lägg till ingrediens i DB
        lblAddIng.setBounds(60, 550, 200, 35);
        tfAddIng.setBounds(60,580, 200, 35);
        lblPris.setBounds(60, 620, 200, 35);
        tfPris.setBounds(60, 650, 200, 35);
        lblEnhet.setBounds(280, 550, 200, 35);
        tfEnhet.setBounds(280, 580, 200, 35);
        btnAddIng.setBounds(280, 650, 200, 35);


        //Sök på ingrediens
        lblSearch.setBounds(340, 90, 200, 30);
        tfSearchRec.setBounds(340, 120, 200, 30);
        btnSearch.setBounds(340, 170, 200, 30);
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
    Lägger till alla komponenter i Jframen. Uppdelat för varje "Sektion" Vänster -> höger.
     */
    public void addComponents() {

        //Namn på recept och instruktioner
        con.add(lblRecipe);
        con.add(tfRecipename);
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
    }


    public String getSearchRep() {
        return tfSearchRec.getText();
    }
    public DefaultListModel getListIngModel() {
        return listSearchModel;
    }
    public JList getList() {
        return searchList;
    }



    public void btnActions() {
        btnSearch.addActionListener(this);
        btnAddIng.addActionListener(this);
        btnAddRecipe.addActionListener(this);
    }


    @Override
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

        if (e.getSource() == btnAddRecipe) {

            
        }


    }

}
