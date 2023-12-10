import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

/**
 * This class creates the interface for the program to run in and handles the menu bar functions
 */
public class ExerciseTrackerGUI extends JFrame {
    private ArrayList<RunWalk> runWalkExercises;
    private RunWalkDetailPanel runWalkDetailPanel;
    public SummaryPanel summaryPanel;
    private String username;
    private String password;
    private boolean loggedIn;

    /**
     * This function initializes the login information such as is logged in, username, and password
     */
    public ExerciseTrackerGUI() {
        this.username = "healthy";
        this.password = "donut";
        this.loggedIn = false;

        runWalkExercises = new ArrayList<>();
        runWalkDetailPanel = new RunWalkDetailPanel();
        summaryPanel = new SummaryPanel(runWalkExercises);
        setupGUI();
    }

    /**
     * This function sets up the menu bar for the interface and organizes it
     */
    private void setupGUI() {
        setTitle("Exercise Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem miLogin = new JMenuItem("Login");
        miLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                showLoginDialog();
            }
        });
        JMenuItem miLogout = new JMenuItem("Log out");
        miLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        JMenuItem miExit = new JMenuItem("Exit");
        miExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JMenuItem miSave = new JMenuItem("Save");
        miSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });
        menuFile.add(miLogin);
        menuFile.add(miLogout);
        menuFile.addSeparator();
        menuFile.add(miSave);
        menuFile.addSeparator();
        menuFile.add(miExit);

        JMenu menuHelp = new JMenu("Help");
        JMenuItem miAbout = new JMenuItem("About");
        miAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAboutDialog();
            }
        });
        menuHelp.add(miAbout);
        menuBar.add(menuHelp);
        menuFile.add(miLogin);
        menuBar.add(menuFile);
        setJMenuBar(menuBar);
        container.add(runWalkDetailPanel, BorderLayout.WEST);
        container.add(summaryPanel, BorderLayout.EAST);

        enableEntries(false);
        setVisible(true);
    }

    /**
     * This prints the about dialog when the about button is clicked
     */
    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this, "ExerciseTrackerV2 - CPSC 24500 Fall 2023", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This updates the summary panel when the add exercise button is clicked
     * @param exercise these are the exercises the user enters
     */
    public void addExercise(Exercise exercise) {
        runWalkExercises.add((RunWalk) exercise);
        summaryPanel.updateList();
    }

    /**
     * This opens the dialog to ask the user for the username and password using the login form class
     */
    private void showLoginDialog() {
        LoginForm loginForm = new LoginForm(this, "Login", true, username, password);
        loginForm.setVisible(true);
        if (loginForm.isLoggedIn()) {
            enableEntries(true);
        }
    }

    /**
     * This logs out the user when the logout button is pressed
     */
    private void logout() {
        loggedIn = false;
        enableEntries(false);
    }

    /**
     * This enables the text boxes to be filled out or not based on the log in status
     * @param enableYN this is what must be true to allow the user to input entries
     */
    private void enableEntries(boolean enableYN) {
        runWalkDetailPanel.enableEntries(enableYN);
    }

    /**
     * This prints the error messages if the user incorrectly tries to save the file
     */
    private void saveToFile() {
        if (loggedIn = false) {
            JOptionPane.showMessageDialog(this, "Please log in before saving.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getPath();
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

                for (RunWalk exercise : runWalkExercises) {
                    writer.write(exercise.toString());
                    writer.newLine();
                }

                writer.close();
                JOptionPane.showMessageDialog(this, "Exercises saved to file successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving to file: " + e.getMessage());
            }
        }
    }

    /**
     * This is the main function that brings everything all together and allows the program to run
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
                    public void run() {
                new ExerciseTrackerGUI();
            }
        });
    }


}
