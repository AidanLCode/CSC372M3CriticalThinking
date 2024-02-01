import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Main {
    // Create a field for the random orange color.
    private static Color initRandomOrange = null;


    public static void main(String[] args) {
        //Create my JFrame which will hold my JPanel and then subsequent Swing components.
        JFrame myFrame = new JFrame("Menu");
        //Set what happens when you close the window.
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Set the window size. I like medium size windows.
        myFrame.setSize(500,500);

        //Create the JMenuBar.
        JMenuBar menuBar = new JMenuBar();

        //Creates the menu for the MenuBar
        JMenu menu = new JMenu("Options");
        // Add the menu to the menuBar
        menuBar.add(menu);


        //Creates the new MenuItems for the MenuBar and adds them to the menu
        JMenuItem optionOne = new JMenuItem("Current Date and Time");
        JMenuItem optionTwo = new JMenuItem("Write TextBox Contents to log.txt");
        JMenuItem optionThree = new JMenuItem("Change Background to Orange");
        JMenuItem optionFour = new JMenuItem("Exit Program");
        menu.add(optionOne);
        menu.add(optionTwo);
        menu.add(optionThree);
        menu.add(optionFour);

        // Add the menuBar to the frame
        myFrame.setJMenuBar(menuBar);

        // Create a textArea to display information.
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        //Add scrollPane in case text gets too large.
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add pane to the frame
        myFrame.add(scrollPane);


        // Add the action listeners to each button
        // Print date and time to textArea
        optionOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime rightNow = LocalDateTime.now();
                // Format date and time
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = rightNow.format(formatter);
                textArea.setText("The current date and time is: " + formattedDateTime);
            }
        });

        // Print content of textarea to log.txt
            optionTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try (PrintWriter out = new PrintWriter("/Users/cubbyevil/Documents/Aidan College/CSU/Courses/Winter 2023/Term C/Prog II CSC372 /Module 3/Critical Thinking/Submission/log.txt")) {
                out.println(textArea.getText());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();

                }
            // Provide success message
                textArea.setText("Successfully Wrote Date and Time to log.txt");
            }
        });

            // Change background color to orange
            optionThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (initRandomOrange == null) {
                    // Create random orange color.
                    Random random = new Random();
                    int red = 255; // Orange has almost all red so set it to the max.
                    int green = 50 + random.nextInt(106); // This set the range of green from 50 to 155.
                    int blue = 0; // There is no blue in orange.

                    //Set the new color to the variable Color
                    initRandomOrange = new Color(red, green, blue);
                }
                // Set the frame to the random orange color.
                myFrame.getContentPane().setBackground(initRandomOrange);
                scrollPane.setBackground(initRandomOrange);
                textArea.setBackground(initRandomOrange);
                menuBar.setBackground(initRandomOrange);
                menu.setBackground(initRandomOrange);

            }
        });

            // Exit program
            optionFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

        myFrame.setVisible(true);


    }
}
