import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class MainFrame extends JFrame {
    public JPanel os_display;
    private int count;
    private JButton exitButton;
    private JButton procButton;
    private JButton cleanButton;
    private JButton resetButton;
    private JButton memButton;
    private JButton helpButton;
    private JTextPane mainDisplay;
    private JTextField inputField;
    private JTextPane monitorDisplay;
    private JScrollPane mdScrollPane;
    private StyledDocument mddoc = mainDisplay.getStyledDocument();
    private StyledDocument mtdoc = monitorDisplay.getStyledDocument();
    private SimpleAttributeSet keyWord = new SimpleAttributeSet();

    //The main constructor for the JFrame
    public MainFrame() {
        //Adds initial Text
        addText(mainDisplay, mddoc, "Welcome to Michael Doesn't Do Shit OS");
        addText(monitorDisplay, mtdoc, "System Resource Monitor \nTO-DO: Figure out multithreading so we can " +
                "update system info in real time \n" +
                "https://www.tutorialspoint.com/java/java_multithreading.htm \n" +
                "We may not need to do this in literal real time. The description made it sound like we can just " +
                "update this screen when the CPU processes one cycle");

        ///List of buttons
        //action for Proc button
        procButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addText(mainDisplay, mddoc, "TODO: Displaying all running processes");
            }
        });

        //action for exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //action for help button
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 4) {
                    addText(mainDisplay, mddoc, "\n\n\n\nPlease stop\n\n\n\n");
                    count=0;
                } else {
                    addText(mainDisplay, mddoc, "List of Commands:" +
                            "\n Proc: " +
                            "\n Mem: " +
                            "\n Exe: " +
                            "\n Load: " +
                            "\n Reset: ");
                }
                count++;
            }
        });

        //action for clean button
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainDisplay.setText("Welcome to Michael Doesn't Do Shit OS \n");
            }
        });

        //action for reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addText(mainDisplay, mddoc, "TODO: RESETS EVERYTHING");
            }
        });

        //input text box
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = e.getActionCommand();
                inputField.setText("");
                addText(mainDisplay, mddoc, input);
                parseCommand(input.toLowerCase());
            }
        });

        mdScrollPane = new JScrollPane(mainDisplay, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        os_display.add(mdScrollPane);
    }

    public void runGUI() {

        // Changes UI appearance to not ugly
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }

        // UI Initialize
        JFrame frame = new JFrame("MDS OS");
        frame.setContentPane(new MainFrame().os_display);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setTitle("OOOOOOS");
        frame.setSize(800, 600);
        frame.setResizable(true);
    }

    // Appends text to specified JTextPane
    public void addText(JTextPane jtp, StyledDocument sd, String s) {
        jtp.setEditable(true);
        try {
            sd.insertString(sd.getLength(), s + "\n", keyWord);
        } catch (Exception e) {
            System.out.println(e);
        }
        jtp.setEditable(false);
    }

    // Parses user inputs in JTextField
    private int parseCommand(String input) {
        // Error messages for incomplete commands
        switch (input) {
            case "exe":
                addText(mainDisplay, mddoc, "Missing arguments");
                return 0;
        }

        String[] args = input.split(" ");

        // Parse exe command
        switch (args[0]) {
            case "exe":
                addText(mainDisplay, mddoc, "Running simulation");
                return 0;
            case "proc":
                addText(mainDisplay, mddoc, "Displaying all running processes");
                return 0;
        }

        addText(mainDisplay, mddoc, "Please enter a valid command.");
        return 1;
    }
}
