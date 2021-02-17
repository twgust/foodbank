package view;

import controller.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreateRecipeView extends JFrame implements ActionListener {

    JTextField tfSearch = new JTextField();
    JButton btnSearch = new JButton("Search");
    JList<String> searchList = new JList<String>();
    DefaultListModel model = new DefaultListModel();
    Container con = getContentPane();



    public CreateRecipeView() {
        setTitle("Recpie");
        setBounds(10, 10, 1200, 800);
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
    }

    private void setLayout() {
        btnSearch.setBounds(50, 100, 120, 40);
        tfSearch.setBounds(50, 150, 150, 40);
        searchList.setBounds(50, 220, 400, 600);
    }

    public void addComponents() {
        con.add(btnSearch);
        con.add(tfSearch);
        con.add(searchList);
    }

    public void btnActions() {
        btnSearch.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            
        }

    }



    public static void main(String[] args) {
        CreateRecipeView vie = new CreateRecipeView();

    }
}
