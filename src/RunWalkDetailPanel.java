import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class creates the section of the interface that deals with the user inputting the exercises
 */
public class RunWalkDetailPanel extends JPanel {
    private JTextField txtName;
    private JTextField txtDate;
    private JTextField txtDuration;
    private JTextField txtDistance;
    private JTextArea tarComment;

    /**
     * This returns setupgui to be used by other classes
     */
    public RunWalkDetailPanel() {
        setupGUI();
    }
    private JButton btnAddExercise;

    /**
     * This sets up the interface for the user to input the information for the exercise
     */
    private void setupGUI() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2));
        panel.add(new JLabel("Name: "));
        txtName = new JTextField(10);
        panel.add(txtName);
        panel.add(new JLabel("Date: "));
        txtDate = new JTextField(10);
        panel.add(txtDate);
        panel.add(new JLabel("Duration: "));
        txtDuration = new JTextField(10);
        panel.add(txtDuration);
        panel.add(new JLabel("Distance:"));
        txtDistance = new JTextField(10);
        panel.add(txtDistance);

        JPanel commentPanel = new JPanel();
        commentPanel.setLayout(new BorderLayout());
        commentPanel.add(new JLabel("Comment"), BorderLayout.NORTH);
        tarComment = new JTextArea(5, 10);
        commentPanel.add(new JScrollPane(tarComment), BorderLayout.CENTER);

        add(panel, BorderLayout.NORTH);
        add(commentPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        btnAddExercise = new JButton("Add Exercise");
        btnAddExercise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExerciseToSummary();
            }
        });
        buttonPanel.add(btnAddExercise);
        add(buttonPanel, BorderLayout.SOUTH);


    }

    /**
     * This adds the information the user inputs to the summary panel
     */
    private void addExerciseToSummary() {
        RunWalk exercise = createExercise();

        if (exercise != null) {
            ExerciseTrackerGUI exerciseTrackerGUI = getExerciseTrackerGUI();
            exerciseTrackerGUI.addExercise(exercise);

            exerciseTrackerGUI.summaryPanel.updateList();
            clearEntries();
        }
    }

    /**
     * This creates the summary for the exercises and has the error codes for invalid inputs
     * @return
     */
    private RunWalk createExercise() {
        String name = getName();
        String date = getDate();
        double duration = getDuration();
        double distance = getDistance();
        String comment = getComment();

        if (name.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and date are required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (!isValidDate(date)) {
            showError("Invalid date format. Please use mm/dd/yyyy.");
            return null;
        }
        if (duration <= 0) {
            showError("Duration must be a non-zero positive number.");
            return null;
        }

        RunWalk exercise = new RunWalk(name, date, duration, distance, comment);

        JOptionPane.showMessageDialog(this, "Calories Burned: " + exercise.getCaloriesBurned(), "Calories Burned", JOptionPane.INFORMATION_MESSAGE);

        return exercise;
    }

    /**
     * This checks that the user input for date is in the valid format
     * @param date this is the date the user inputted
     * @return
     */
    private boolean isValidDate(String date) {
        String dateFormatRegex = "\\d{2}/\\d{2}/\\d{4}";
        return date.matches(dateFormatRegex);
    }

    /**
     * This prints te error message that is shown from the program
     * @param message
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This function grabs the gui from the exercrise tracker clss
     * @return
     */
    private ExerciseTrackerGUI getExerciseTrackerGUI() {
        Container parent = getParent();
        while (parent != null && !(parent instanceof ExerciseTrackerGUI)) {
            parent = parent.getParent();
        }

        return (ExerciseTrackerGUI) parent;
    }

    public String getName() {
        return txtName.getText();
    }
    public String getDate() {
        return txtDate.getText();
    }
    public double getDuration() {
        try {
            return Double.parseDouble(txtDuration.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public double getDistance() {
        try {
            return Double.parseDouble(txtDistance.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public String getComment() {
        return tarComment.getText();
    }

    /**
     * This function clears the entries for the user inputs
     */
    public void clearEntries() {
        txtName.setText("");
        txtDate.setText("");
        txtDuration.setText("");
        txtDistance.setText("");
        tarComment.setText("");
    }

    /**
     * This enables the user to input entries once they are logged in
     * @param enabledYN
     */
    public void enableEntries(boolean enabledYN) {
        txtName.setEnabled(enabledYN);
        txtDate.setEnabled(enabledYN);
        txtDuration.setEnabled(enabledYN);
        txtDistance.setEnabled(enabledYN);
        tarComment.setEnabled(enabledYN);
    }


}
