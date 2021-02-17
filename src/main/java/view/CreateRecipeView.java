package view;

import entity.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jdesktop.swingx.autocomplete.AutoCompleteComboBoxEditor;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

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



    JComboBox searchBox = new JComboBox();

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
    DefaultListModel listIngModel = new DefaultListModel();



    public CreateRecipeView() {
        setTitle("Recept");
        setBounds(10, 10, 1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        setLayoutManager();
    }

    public void setLayoutManager() {
        con.setLayout(null);
        setLayout();
        addComponents();
        btnActions();
        initSearchBox();
    }
    /*
    Placerar rutans komponenter på given plats med koordinater och storlekar.
     */
    private void setLayout() {
        lblSearch.setBounds(500, 70, 200, 30);
       // btnSearch.setBounds(850, 150, 200, 30);
       // tfSearch.setBounds(850, 100, 200, 35);
        searchBox.setBounds(500, 100, 200, 35);
        searchList.setBounds(500, 220, 200, 300);
        lblAmt.setBounds(500, 550, 200, 35);
        tfAmt.setBounds(500, 580, 200, 35);
        btnAddIng.setBounds(500, 630, 200, 35);


        lblRecipe.setBounds(150, 80, 200, 30 );
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
        con.add(tfSearch);
        con.add(searchList);
        con.add(lblRecipe);
        con.add(tfRecipename);
        con.add(lblInstruction);
        con.add(instructionsArea);

        con.add(lblAmt);
        con.add(tfAmt);
        con.add(btnAddIng);

        con.add(lblIng);
        con.add(listIng);
        con.add(btnAddRecipe);
        listIng.setModel(listIngModel);







    }

    /*
    Initierar sökrutan där söning på ingredienser kommer göras. Använder java SwingX-biblioteket. (Tillagt i pom.xml som dependency)
     */
    public void initSearchBox() {
        searchBox.setEditable(true);
        con.add(searchBox);
        searchBox.setSelectedItem("");
        searchBox.addItem("Spenat");
        searchBox.addItem("Pasta");
        searchBox.addItem("Lök");
        searchBox.addItem("Stök");
        searchBox.addItem("Gök");


        AutoCompleteDecorator.decorate(searchBox);

    }

    public void btnActions() {
        btnSearch.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == searchBox) {
            

    }





    public static void main(String[] args) {
        CreateRecipeView vie = new CreateRecipeView();

    }
}
