package view;

import controller.RelationsInserter_SQL_Statements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI for inserting Relations
 */
public class RelationsInserterGUI extends JPanel
{
    private RelationsInserter_SQL_Statements stmt;

    private JButton btnLoadRecipes = new JButton("Load Recipes");
    private JComboBox<String> boxRecipes = new JComboBox<String>();

    private JLabel lblRecipeIngredients = new JLabel("Recipe Ingredients");
    private DefaultListModel<String> listmodelRecipeIngredients = new DefaultListModel<String>();
    private JList<String> listRecipeIngredients = new JList<String>(listmodelRecipeIngredients);
    private JScrollPane spRecipeIngredients = new JScrollPane(listRecipeIngredients);

    private JLabel lblDatabaseIngredients = new JLabel("Database Ingredients");
    private DefaultListModel<String> listmodelDatabaseIngredients = new DefaultListModel<String>();
    private JList<String> listDatabaseIngredients = new JList<String>(listmodelDatabaseIngredients);
    private JScrollPane spDatabaseIngredients = new JScrollPane(listDatabaseIngredients);

    private JLabel lblIngredients = new JLabel("Chosen Ingredients");
    private DefaultListModel<String> listmodelIngredients = new DefaultListModel<String>();
    private JList<String> listIngredients = new JList<String>(listmodelIngredients);
    private JScrollPane spIngredients = new JScrollPane(listIngredients);

    private JLabel lblUnits = new JLabel("Units: ");
    private JTextField tfUnits = new JTextField();
    private JButton btnAdd = new JButton("Add");
    private JButton btnRemove = new JButton("Remove");

    private JButton btnLoad = new JButton("Load Ingredients");
    private JButton btnQuery = new JButton("Send Query");
    private JCheckBox cbQuery = new JCheckBox("Ready?");

    public RelationsInserterGUI(RelationsInserter_SQL_Statements stmt)
    {
        this.stmt = stmt;

        init();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(800, 600));
    }

    private void init()
    {
        setLayout(new BorderLayout());
        add(topPanel(), BorderLayout.NORTH);
        add(midPanel(), BorderLayout.CENTER);
        add(botPanel(), BorderLayout.SOUTH);
    }

    private JPanel botPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(btnLoad);
        btnLoad.addActionListener(new RecipeIngredientsListener());
        panel.add(btnQuery);
        btnQuery.setEnabled(false);
        btnQuery.addActionListener(new QueryListener());
        panel.add(cbQuery);
        cbQuery.addActionListener(new ReadyListener());
        return panel;
    }

    private JPanel topPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(btnLoadRecipes);
        btnLoadRecipes.addActionListener(new LoadListener());
        panel.add(boxRecipes);
        boxRecipes.setPreferredSize(new Dimension(200, 25));
        boxRecipes.addActionListener(new RecipeListener());
        return panel;
    }

    private JPanel midleftPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(lblRecipeIngredients, BorderLayout.NORTH);
        panel.add(spRecipeIngredients, BorderLayout.CENTER);
        spRecipeIngredients.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        spRecipeIngredients.setPreferredSize(new Dimension(300, 600));
        return panel;
    }

    private JPanel midmidPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(lblDatabaseIngredients, BorderLayout.NORTH);
        panel.add(spDatabaseIngredients, BorderLayout.CENTER);
        spDatabaseIngredients.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        spDatabaseIngredients.setPreferredSize(new Dimension(300, 600));
        return panel;
    }

    private JPanel midPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        panel.add(midleftPanel());
        panel.add(midmidPanel());
        panel.add(midrightPanel());
        return panel;
    }

    private JPanel midrightPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(lblIngredients, BorderLayout.NORTH);
        panel.add(spIngredients, BorderLayout.CENTER);
        panel.add(midrightbotPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel midrightbotPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(lblUnits);
        panel.add(tfUnits);
        tfUnits.setPreferredSize(new Dimension(50, 25));
        panel.add(btnAdd);
        btnAdd.addActionListener(new IngredientsListener());
        panel.add(btnRemove);
        btnRemove.addActionListener(new IngredientsListener());
        return panel;
    }

    private class ReadyListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            btnQuery.setEnabled(cbQuery.isSelected());

        }

    }

    private class LoadListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            stmt.LoadRecipes();
        }
    }
    private class RecipeListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (boxRecipes.getSelectedItem() != null)
            {
                stmt.LoadRecipeIngredients(boxRecipes.getSelectedItem());
                System.out.println(boxRecipes.getSelectedItem().toString());
            }
        }
    }

    private class IngredientsListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == btnAdd)
            {
                if (!listDatabaseIngredients.isSelectionEmpty() && !tfUnits.getText().equals(""))
                {
                    java.util.List<String> receivers = listDatabaseIngredients.getSelectedValuesList();
                    for (String s : receivers)
                    {
                        if (!listmodelIngredients.contains(s))
                        {
                            listmodelIngredients.addElement(s + " - " + tfUnits.getText());
                        }
                    }
                }
            }
            if (e.getSource() == btnRemove)
            {
                if (!listIngredients.isSelectionEmpty() && !tfUnits.getText().equals(""))
                {
                    List<String> receivers = listIngredients.getSelectedValuesList();
                    for (String s : receivers)
                    {
                        listmodelIngredients.removeElement(s);
                    }
                }
            }
        }
    }

    private class RecipeIngredientsListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("HAijshfioashfiHEFI");
            stmt.parseQuery(listRecipeIngredients.getSelectedValue());
        }
    }

    private class QueryListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (!listmodelIngredients.isEmpty())
            {
                stmt.sendQuery(boxRecipes.getSelectedItem(), listmodelIngredients.toArray());
                btnQuery.setEnabled(false);
                cbQuery.setSelected(false);
            }
        }
    }

    public void setRecipes(ArrayList<String> array)
    {
        for (String s : array)
        {
            boxRecipes.addItem(s);
        }
    }

    public void updateRecipeIngredients(String[] ingredientsStringArray)
    {
        listmodelRecipeIngredients.clear();
        for (String s : ingredientsStringArray)
        {
            listmodelRecipeIngredients.addElement(s);
        }
    }

    public void updateDatabaseIngredients(String[] ingredientsStringArray)
    {
        listmodelDatabaseIngredients.clear();
        for (String s : ingredientsStringArray)
        {
            listmodelDatabaseIngredients.addElement(s);
        }
    }
}
