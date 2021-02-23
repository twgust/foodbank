package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    JLabel lblRecipe = new JLabel("Benämning");
    JLabel lblInstruction = new JLabel("Tillagningsinstruktioner");
    JTextArea instructionsArea = new JTextArea("");

    JLabel lblAmt = new JLabel("Mängd");
    JTextField tfAmt = new JTextField();
    JButton btnAddIng = new JButton("Lägg till Ingredienser");

    JLabel lblIng = new JLabel("Ingredienser");
    JList<String> listIng = new JList<String>();
    JButton btnAddRecipe = new JButton("Lägg till Recept");

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
        lblSearch.setBounds(500, 70, 200, 30);
         btnSearch.setBounds(500, 150, 200, 30);
        // tfSearch.setBounds(850, 100, 200, 35);
        tfSearchRec.setBounds(500, 100, 200, 35);
        searchScrollPane.setBounds(500, 220, 200, 300);
        lblAmt.setBounds(500, 550, 200, 35);
        tfAmt.setBounds(500, 580, 200, 35);
        btnAddIng.setBounds(500, 630, 200, 35);


        lblRecipe.setBounds(150, 80, 200, 30);
        tfRecipename.setBounds(150, 120, 200, 30);
        lblInstruction.setBounds(150, 170, 200, 30);
        instructionsArea.setBounds(150, 220, 200, 300);

        lblIng.setBounds(800, 100, 280, 35);
        listIng.setBounds(800, 130, 280, 400);
        btnAddRecipe.setBounds(800, 560, 200, 35);
    }


    /*
    Lägger till alla komponenter i Jframen. Uppdelat för varje "Sektion" Vänster -> höger.
     */
    public void addComponents() {
        con.add(lblSearch);
        con.add(btnSearch);
       // con.add(tfSearch);
       // con.add(searchList);
        con.add(searchScrollPane);
        con.add(lblRecipe);
        con.add(tfRecipename);
        con.add(lblInstruction);
        con.add(instructionsArea);

        con.add(lblAmt);
        con.add(tfAmt);
        con.add(btnAddIng);

        con.add(tfSearchRec);
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            controller.getSearch();
        }

        if (e.getSource() == btnAddIng) {

        }


    }

}
