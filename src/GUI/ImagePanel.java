package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.swing.JPanel;

class ImagePanel extends JPanel {

    Image bgimageTask = null;
    Image bgimage = null;
    public boolean task;

    ImagePanel(String strTask, String str) {
        this.task = false;
        MediaTracker mt = new MediaTracker(this);
        bgimageTask = Toolkit.getDefaultToolkit().getImage(getClass().getResource(strTask));
        bgimage = Toolkit.getDefaultToolkit().getImage(getClass().getResource(str));
        mt.addImage(bgimage, 0);
        mt.addImage(bgimageTask, 0);
        try {
            mt.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (task) {
            super.paintComponent(g);
            int imwidth = bgimageTask.getWidth(null);
            int imheight = bgimageTask.getHeight(null);
            g.drawImage(bgimageTask, 1, 1, null);
        } else {
            super.paintComponent(g);
            int imwidth = bgimage.getWidth(null);
            int imheight = bgimage.getHeight(null);
            g.drawImage(bgimage, 1, 1, null);
        }
    }
}