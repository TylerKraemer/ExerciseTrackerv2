import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class calculates the calories burned for the runwalk exercise
 */
public class RunWalk extends Exercise {
    private double distance;

    public RunWalk(String name, String date, double duration, double distance, String comment) {
        super(name, date, duration, comment);
        this.distance = distance;
    }

    @Override
    public double getCaloriesBurned() {
        return distance / getDuration() * 9000;
    }

    @Override
    public String getType() {
        return "runwalk";
    }

    @Override
    public String toStringCustomInfo() {
        return String.format("%.2f", distance);
    }
}


