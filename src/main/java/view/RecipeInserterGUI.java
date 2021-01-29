package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

import controller.Connector;
import controller.RecipeInserter_SQL_Statements;

public class RecipeInserterGUI extends JPanel
{
    private static final Dimension tfDimensions = new Dimension(200, 25);

    private RecipeInserter_SQL_Statements stmt;

    private JLabel lblCategories = new JLabel("Kategori: ");
    private String[] categories = {"Kött", "Kyckling", "Fisk", "Pasta"};
    private JComboBox<String> boxCategories = new JComboBox<String>(categories);

    private JLabel lblTitle = new JLabel("Titel: ");
    private JTextField tfTitle = new JTextField("");

    private JLabel lblDescription = new JLabel("Beskrivning: ");
    private JTextField tfDescription = new JTextField("");

    private JLabel lblPortions = new JLabel("Portioner: ");
    private String[] portions = {"2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private JComboBox<String> boxPortions = new JComboBox<String>(portions);

    private JLabel lblLink = new JLabel("Länk: ");
    private JTextField tfLink = new JTextField("");

    private JLabel lblImage = new JLabel("Bildlänk: ");
    private JTextField tfImage = new JTextField("");

    private JLabel lblIngredients = new JLabel("Ingredienser", SwingUtilities.CENTER);
    private JTextArea taIngredients = new JTextArea("");

    private JLabel lblInstructions = new JLabel("Instruktioner", SwingUtilities.CENTER);
    private JTextArea taInstructions = new JTextArea("");

    private JButton btnSubmit = new JButton("Submit");
    private JLabel lblStatus = new JLabel("Ready to submit");

    public RecipeInserterGUI(RecipeInserter_SQL_Statements stmt)
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

    private JPanel toptopPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(lblCategories);
        panel.add(boxCategories);
        panel.add(lblTitle);
        panel.add(tfTitle);
        panel.add(lblDescription);
        panel.add(tfDescription);
        tfDescription.setPreferredSize(tfDimensions);

        return panel;
    }

    private JPanel topbotPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(lblPortions);
        panel.add(boxPortions);
        tfTitle.setPreferredSize(tfDimensions);
        panel.add(lblLink);
        panel.add(tfLink);
        tfLink.setPreferredSize(tfDimensions);
        panel.add(lblImage);
        panel.add(tfImage);
        tfImage.setPreferredSize(tfDimensions);
        return panel;
    }

    private JPanel topPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3));
        panel.add(toptopPanel());
        panel.add(topbotPanel());
        return panel;
    }

    private JPanel midleftPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(lblIngredients, BorderLayout.NORTH);
        panel.add(taIngredients, BorderLayout.CENTER);
        taIngredients.setLineWrap(true);
        taIngredients.setWrapStyleWord(true);
        taIngredients.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return panel;
    }

    private JPanel midrightPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(lblInstructions, BorderLayout.NORTH);
        panel.add(taInstructions, BorderLayout.CENTER);
        taInstructions.setLineWrap(true);
        taInstructions.setWrapStyleWord(true);
        taInstructions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return panel;
    }

    private JPanel midPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(midleftPanel());
        panel.add(midrightPanel());
        return panel;
    }

    private JPanel botPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(btnSubmit);
        btnSubmit.addActionListener(new SubmitListener());
        panel.add(lblStatus);
        return panel;
    }

    public void setStatusText(String string)
    {
        lblStatus.setText(string);
    }
    // listener for "add" button
    // and query for adding a recipe to the DB
    // the submit() function is invoked when "add" button is pressed, submit() then invokes the query() func
    // inside the Connector class which in turn INSERTS the users recipe into the DB

    private class SubmitListener implements ActionListener
    {

        public void submit(int category, String title, String description, int portions, String link, String imageLink,
                           String ingredients, String instructions)
        {
            System.out.println("Formatting strings...");
            String ingredientsFix;
            String instructionsFix;
            ingredientsFix = ingredients.replaceAll("\n", "\\\\");
            instructionsFix = instructions.replaceAll("\n", "\\\\");
            System.out.println("Formatted strings");
            System.out.println(
                    "Issuing statement to MySQL database: " + MessageFormat.format("{0} {1} {2} {3} {4} {5} {6} {7}",
                            category, title, description, portions, link, imageLink, ingredientsFix, instructionsFix));

            stmt.query(category, title, description, portions, link, imageLink, ingredientsFix, instructionsFix);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            submit(boxCategories.getSelectedIndex(), tfTitle.getText(), tfDescription.getText(),
                    boxPortions.getSelectedIndex(), tfLink.getText(), tfImage.getText(), taIngredients.getText(),
                    taInstructions.getText());
        }

    }
}
