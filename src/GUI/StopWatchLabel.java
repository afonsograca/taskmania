/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author AfonsoGraca
 */
import java.awt.event.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class StopWatchLabel extends JLabel
        implements ComponentListener, ActionListener {

    public long startWatch;
    private Timer timer;

    public StopWatchLabel() {
        super("", JLabel.CENTER);
        setVisible(false);
        this.addComponentListener(this);

    }

    public void actionPerformed(ActionEvent evt) {
        long elapsed = System.currentTimeMillis() - startWatch;

        DateFormat str = new SimpleDateFormat("HH:mm:ss.S");
        Calendar date = Calendar.getInstance();
        date.clear();
        date.setTimeInMillis(elapsed);
        date.set(Calendar.HOUR, date.get(Calendar.HOUR) - 1);

        setText(str.format(date.getTime()).substring(0, 10));
    }

    public void componentResized(ComponentEvent ce) {
    }

    public void componentMoved(ComponentEvent ce) {
    }

    public void componentShown(ComponentEvent ce) {
        startWatch = System.currentTimeMillis();
        timer = new Timer(100, this);
        timer.start();
    }

    public void componentHidden(ComponentEvent ce) {
        if (timer != null) {
            timer.stop();
        }
        setText("");
    }
}