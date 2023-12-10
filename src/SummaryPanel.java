import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * This creates the code to create the summary panel for the program
 */
public class SummaryPanel extends JPanel{
    private ArrayList<RunWalk> runWalkExecises;
    private JTextArea tarSummary;
    public SummaryPanel(ArrayList<RunWalk> runWalkExecises) {
        this.runWalkExecises = runWalkExecises;
        setupGUI();
    }

    /**
     * This sets up the interface for the summary panel
     */
    private void setupGUI() {
        setLayout(new BorderLayout());
        JLabel lblSummary = new JLabel("Exercise Summary - Total Calories Burned: " + calculateTotalCalories());
        add(lblSummary, BorderLayout.NORTH);
        tarSummary = new JTextArea(10, 55);
        tarSummary.setEditable(false);
        add(new JScrollPane(tarSummary), BorderLayout.CENTER);
    }

    /**
     * This updates the summary panel when a new exercise is inputted
     */
    public void updateList() {
        tarSummary.setText("");
        StringBuilder text = new StringBuilder();
        for (RunWalk exercise : runWalkExecises) {
            text.append(exercise).append("\n");
        }
        tarSummary.setText(text.toString());
    }

    /**
     * This calculates the total calories burned from all the exercises
     * @return
     */
    private double calculateTotalCalories() {
        double totalCalories = 0;
        for (RunWalk exercise : runWalkExecises) {
            totalCalories += exercise.getCaloriesBurned();
        }
        return totalCalories;
    }
}
