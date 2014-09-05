/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TaskManiaGUI.java
 *
 * Created on 30/Mai/2011, 18:16:30
 */
package GUI;

import Global.Time;
import Parser.XMLParser;
import States.State.InvalidTransitionException;
import Tasks.*;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartPanel;

import javax.swing.UIManager;

/**
 *
 * @author ei07104
 */
public class TaskManiaGUI extends javax.swing.JFrame {

    Job graphic;
    boolean viewClosed = false;

    /** Creates new form TaskManiaGUI */
    public TaskManiaGUI() {


        final XMLParser parser = new XMLParser();
        graphic = new Job("root", "");


        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            public void run() {
                Component node = (Component) jTree1.getLastSelectedPathComponent();
                if(node.getClass() == Tasks.Task.class && node.getState() == "Running"){
                    try {
                        ((Task)node).pause();
                    } catch (InvalidTransitionException ex) {
                        Logger.getLogger(TaskManiaGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
               
                List<Component> tree = new ArrayList<Component>();

                int child = graphic.getChildCount();
                for (int i = 0; i < child; i++) {
                    tree.add((Component) graphic.getChildAt(i));
                }
                System.out.println(child);

                parser.saveDatabase(tree);
            }
        }));

        List<Component> database = parser.loadDatabase();
        int child = database.size();
        for (int i = 0; i < child; i++) {
            graphic.addChild(database.get(i));
        }

        try {
            String lcOSName = System.getProperty("os.name").toLowerCase();
            boolean mac = lcOSName.startsWith("mac os x");
            if (mac) {
                //colocar a barra de menu na MenuBar no mac
                System.setProperty("apple.laf.useScreenMenuBar", "true");

                // set the name of the application menu item
                //com.apple.eawt.Application macApp = com.apple.eawt.Application.getApplication();

                Image logo = new ImageIcon("logo.png").getImage();;
                //macApp.setDockIconImage(logo);
            }
            // set the look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e);
        }

        initComponents();
        setLocation(getWindowMiddle());

        updateTree(viewClosed);
        
        final Thread updater = new UpdaterThread();
        updater.setDaemon(true);
        updater.start();
    }
  
    private static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
    
    private Point getWindowMiddle() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        return new Point(middle.x - (this.getWidth() / 2), middle.y - (this.getHeight() / 2));
    }

   
    private void updateTree(boolean closed) {
        Job currentList = null;
        if (closed) {
            currentList = graphic;
        } else {
            currentList = graphic.getActiveChildren();
        }

        jTree1 = new ZebraJTree(currentList);
        jTree1.setEditable(false);
        jTree1.setShowsRootHandles(true);
        jTree1.setAutoscrolls(true);
        jTree1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTree1.setRootVisible(false);
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);
    }
    
    private void updateStatsChart() {
        Component node = (Component) jTree2.getLastSelectedPathComponent();
        if (node == null){
            node = (Component) jTree1.getLastSelectedPathComponent();
            if(node == null)
                node = null;
        }
        ((ChartPanel) jPanel1).setChart(StatsChartPanel.buildGraphic(node));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newTaskFrame = new javax.swing.JFrame();
        newTask = new ImagePanel("/images/backgroundTask.png","/images/background.png");
        newTaskNameLabel = new javax.swing.JLabel();
        newTaskNameField = new javax.swing.JTextField();
        newTaskBelongsLabel = new javax.swing.JLabel();
        newTaskDescriptionScrollPane = new javax.swing.JScrollPane();
        newTaskDescriptionTextArea = new javax.swing.JTextArea();
        newTaskDescritionLabel = new javax.swing.JLabel();
        newTaskBelongComboBox = new javax.swing.JComboBox(graphic.getJobNames(""));
        newTaskCreate = new javax.swing.JButton();
        newTaskName = new javax.swing.JLabel();
        newActionFrame = new javax.swing.JFrame();
        newAction = new ImagePanel("/images/backgroundTask.png","/images/background.png");
        newActionNameLabel = new javax.swing.JLabel();
        newActionNameField = new javax.swing.JTextField();
        newActionDeadlineLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        newActionDescriptionArea = new javax.swing.JTextArea();
        newActionDescriptionLabel = new javax.swing.JLabel();
        newActionBelongComboBox = new javax.swing.JComboBox(graphic.getJobNames(""));
        newActionButton = new javax.swing.JButton();
        newActionBelongLabel = new javax.swing.JLabel();
        newActionDeadlineField = new javax.swing.JTextField();
        newActionName = new javax.swing.JLabel();
        newJobFrame = new javax.swing.JFrame();
        newJob = new ImagePanel("/images/backgroundTask.png","/images/background.png");
        newJobNameLabel = new javax.swing.JLabel();
        newJobNameField = new javax.swing.JTextField();
        newJobBelongsLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        newJobDescriptionArea = new javax.swing.JTextArea();
        newJobDescriptionLabel = new javax.swing.JLabel();
        newJobBelongComboBox = new javax.swing.JComboBox(graphic.getJobNames(""));
        newJobButon = new javax.swing.JButton();
        newJobName = new javax.swing.JLabel();
        statsFrame = new javax.swing.JFrame();
        zebraStatsScroll = new javax.swing.JScrollPane();
        jTree2 = new ZebraJTree(graphic);
        mainStatsPanel = new javax.swing.JPanel();
        defaultStatsPane = new ImagePanel("/images/backgroundTask.png","/images/backgroundStats.png");
        defaultStatsTextScrollPane = new javax.swing.JScrollPane();
        defaultStatsText = new javax.swing.JTextArea();
        statsPane = new ImagePanel("/images/backgroundTask.png","/images/backgroundStats.png");
        jPanel1 = new ChartPanel(StatsChartPanel.buildGraphic(null));
        jLabel1 = new javax.swing.JLabel();
        tasksPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new ZebraJTree(graphic);
        mainPane = new ImagePanel("/images/backgroundTask.png","/images/background.png");
        taskPane = new ImagePanel("/images/backgroundTask.png","/images/background.png");
        playLabel = new javax.swing.JLabel();
        stopLabel = new javax.swing.JLabel();
        timeSpentLabel = new javax.swing.JLabel();
        taskStatus = new javax.swing.JLabel();
        taskName = new javax.swing.JLabel();
        pauseLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        taskTimeSpent = new javax.swing.JLabel();
        stopwatchLabel = new StopWatchLabel();
        deleteButton = new javax.swing.JLabel();
        taskDescriptionScrollPane = new javax.swing.JScrollPane();
        taskDescription = new javax.swing.JTextArea();
        taskManiaWelcome = new javax.swing.JTextField();
        logoLabel = new javax.swing.JLabel();
        actionPane = new ImagePanel("/images/backgroundTask.png","/images/background.png");
        taskStatus2 = new javax.swing.JLabel();
        statusLabel2 = new javax.swing.JLabel();
        descriptionLabel2 = new javax.swing.JLabel();
        doneButton = new javax.swing.JLabel();
        deleteButtonAction = new javax.swing.JLabel();
        actionDescriptionScrollPane = new javax.swing.JScrollPane();
        actionDescription = new javax.swing.JTextArea();
        actionLabel = new javax.swing.JLabel();
        actionName = new javax.swing.JLabel();
        jobPane = new ImagePanel("/images/backgroundTask.png","/images/background.png");
        timeSpentLabel1 = new javax.swing.JLabel();
        taskStatus1 = new javax.swing.JLabel();
        taskName1 = new javax.swing.JLabel();
        statusLabel1 = new javax.swing.JLabel();
        descriptionLabel1 = new javax.swing.JLabel();
        taskTimeSpent1 = new javax.swing.JLabel();
        deleteButtonJob = new javax.swing.JLabel();
        jobDescriptionScrollPane = new javax.swing.JScrollPane();
        jobDescription = new javax.swing.JTextArea();
        jobLabel = new javax.swing.JLabel();
        TaskManiaMenuBar = new javax.swing.JMenuBar();

        newTaskFrame.setMinimumSize(new java.awt.Dimension(350, 350));
        newTaskFrame.setResizable(false);

        newTask.setBackground(new java.awt.Color(51, 0, 51));
        newTask.setMaximumSize(new java.awt.Dimension(350, 350));
        newTask.setMinimumSize(new java.awt.Dimension(350, 350));
        newTask.setPreferredSize(new java.awt.Dimension(350, 350));
        newTask.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        newTaskNameLabel.setFont(new java.awt.Font("Myriad Pro", 1, 16));
        newTaskNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        newTaskNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        newTaskNameLabel.setText("Name:");
        newTask.add(newTaskNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 95, 30));

        newTaskNameField.setBackground(new java.awt.Color(153, 153, 153));
        newTaskNameField.setFont(new java.awt.Font("Myriad Pro", 0, 14));
        newTaskNameField.setForeground(new java.awt.Color(255, 255, 255));
        newTaskNameField.setAlignmentY(0.0F);
        newTaskNameField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        newTaskNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                newTaskNameFieldKeyReleased(evt);
            }
        });
        newTask.add(newTaskNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 90, 230, 30));

        newTaskBelongsLabel.setFont(new java.awt.Font("Myriad Pro", 1, 16));
        newTaskBelongsLabel.setForeground(new java.awt.Color(255, 255, 255));
        newTaskBelongsLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        newTaskBelongsLabel.setText("Belongs to:");
        newTask.add(newTaskBelongsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 95, 30));

        newTaskDescriptionScrollPane.setBorder(null);

        newTaskDescriptionTextArea.setBackground(new java.awt.Color(153, 153, 153));
        newTaskDescriptionTextArea.setColumns(20);
        newTaskDescriptionTextArea.setFont(new java.awt.Font("Myriad Pro", 0, 14));
        newTaskDescriptionTextArea.setForeground(new java.awt.Color(255, 255, 255));
        newTaskDescriptionTextArea.setLineWrap(true);
        newTaskDescriptionTextArea.setWrapStyleWord(true);
        newTaskDescriptionTextArea.setRows(5);
        newTaskDescriptionTextArea.setTabSize(7);
        newTaskDescriptionTextArea.setWrapStyleWord(true);
        newTaskDescriptionTextArea.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jScrollPane2.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        newTaskDescriptionScrollPane.setViewportView(newTaskDescriptionTextArea);

        newTask.add(newTaskDescriptionScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 130, 230, 120));

        newTaskDescritionLabel.setFont(new java.awt.Font("Myriad Pro", 1, 16));
        newTaskDescritionLabel.setForeground(new java.awt.Color(255, 255, 255));
        newTaskDescritionLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        newTaskDescritionLabel.setText("Description:");
        newTask.add(newTaskDescritionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 130, 95, 30));
        newTask.add(newTaskBelongComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 260, 230, -1));

        newTaskCreate.setText("Create");
        newTaskCreate.setEnabled(false);
        newTaskCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newTaskCreateMouseClicked(evt);
            }
        });
        newTask.add(newTaskCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 100, -1));

        newTaskName.setFont(new java.awt.Font("Myriad Pro", 1, 36));
        newTaskName.setForeground(new java.awt.Color(255, 255, 255));
        newTaskName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newTaskName.setText("New Task");
        newTaskName.setFocusable(false);
        newTask.add(newTaskName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 350, 60));

        newTaskFrame.getContentPane().add(newTask, java.awt.BorderLayout.CENTER);

        newTaskFrame.getAccessibleContext().setAccessibleName("newTaskFrame");

        newActionFrame.setMinimumSize(new java.awt.Dimension(350, 350));
        newActionFrame.setResizable(false);

        newAction.setBackground(new java.awt.Color(51, 0, 51));
        newAction.setMaximumSize(new java.awt.Dimension(350, 350));
        newAction.setMinimumSize(new java.awt.Dimension(350, 350));
        newAction.setPreferredSize(new java.awt.Dimension(350, 350));
        newAction.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        newActionNameLabel.setFont(new java.awt.Font("Myriad Pro", 1, 16));
        newActionNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        newActionNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        newActionNameLabel.setText("Name:");
        newAction.add(newActionNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 90, 30));

        newActionNameField.setBackground(new java.awt.Color(153, 153, 153));
        newActionNameField.setFont(new java.awt.Font("Myriad Pro", 0, 14));
        newActionNameField.setForeground(new java.awt.Color(255, 255, 255));
        newActionNameField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        newActionNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                newActionNameFieldKeyReleased(evt);
            }
        });
        newAction.add(newActionNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 240, -1));

        newActionDeadlineLabel.setFont(new java.awt.Font("Myriad Pro", 1, 16));
        newActionDeadlineLabel.setForeground(new java.awt.Color(255, 255, 255));
        newActionDeadlineLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        newActionDeadlineLabel.setText("Deadline (dd-mm-aaaa):");
        newAction.add(newActionDeadlineLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 180, 30));

        jScrollPane2.setBorder(null);

        newActionDescriptionArea.setBackground(new java.awt.Color(153, 153, 153));
        newActionDescriptionArea.setColumns(20);
        newActionDescriptionArea.setFont(new java.awt.Font("Myriad Pro", 0, 14));
        newActionDescriptionArea.setForeground(new java.awt.Color(255, 255, 255));
        newActionDescriptionArea.setLineWrap(true);
        newActionDescriptionArea.setWrapStyleWord(true);
        newActionDescriptionArea.setRows(5);
        newActionDescriptionArea.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jScrollPane2.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setViewportView(newActionDescriptionArea);

        newAction.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 240, 100));

        newActionDescriptionLabel.setFont(new java.awt.Font("Myriad Pro", 1, 16));
        newActionDescriptionLabel.setForeground(new java.awt.Color(255, 255, 255));
        newActionDescriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        newActionDescriptionLabel.setText("Description:");
        newAction.add(newActionDescriptionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 90, 30));

        newActionBelongComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newActionBelongComboBoxActionPerformed(evt);
            }
        });
        newAction.add(newActionBelongComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 240, 250, -1));

        newActionButton.setText("Create");
        newActionButton.setEnabled(false);
        newActionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newActionButtonMouseClicked(evt);
            }
        });
        newAction.add(newActionButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 110, -1));

        newActionBelongLabel.setFont(new java.awt.Font("Myriad Pro", 1, 16));
        newActionBelongLabel.setForeground(new java.awt.Color(255, 255, 255));
        newActionBelongLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        newActionBelongLabel.setText("Belongs to:");
        newAction.add(newActionBelongLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 90, 30));

        newActionDeadlineField.setBackground(new java.awt.Color(153, 153, 153));
        newActionDeadlineField.setFont(new java.awt.Font("Myriad Pro", 0, 14));
        newActionDeadlineField.setForeground(new java.awt.Color(255, 255, 255));
        newActionDeadlineField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        newAction.add(newActionDeadlineField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 160, -1));

        newActionName.setFont(new java.awt.Font("Myriad Pro", 1, 36));
        newActionName.setForeground(new java.awt.Color(255, 255, 255));
        newActionName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newActionName.setText("New Action");
        newAction.add(newActionName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 350, 60));

        newActionFrame.getContentPane().add(newAction, java.awt.BorderLayout.CENTER);

        newActionFrame.getAccessibleContext().setAccessibleName("newActionFrame");

        newJobFrame.setMinimumSize(new java.awt.Dimension(350, 350));
        newJobFrame.setResizable(false);

        newJob.setVisible(true);
        newJob.setBackground(new java.awt.Color(51, 0, 51));
        newJob.setMaximumSize(new java.awt.Dimension(350, 350));
        newJob.setMinimumSize(new java.awt.Dimension(350, 350));
        newJob.setPreferredSize(new java.awt.Dimension(350, 350));
        newJob.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        newJobNameLabel.setFont(new java.awt.Font("Myriad Pro", 1, 16));
        newJobNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        newJobNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        newJobNameLabel.setText("Name:");
        newJob.add(newJobNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 100, 30));

        newJobNameField.setBackground(new java.awt.Color(153, 153, 153));
        newJobNameField.setFont(new java.awt.Font("Myriad Pro", 0, 14));
        newJobNameField.setForeground(new java.awt.Color(255, 255, 255));
        newJobNameField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        newJobNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                newJobNameFieldKeyReleased(evt);
            }
        });
        newJob.add(newJobNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 90, 230, -1));

        newJobBelongsLabel.setFont(new java.awt.Font("Myriad Pro", 1, 16));
        newJobBelongsLabel.setForeground(new java.awt.Color(255, 255, 255));
        newJobBelongsLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        newJobBelongsLabel.setText("Belongs to:");
        newJob.add(newJobBelongsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 90, 30));

        jScrollPane4.setBorder(null);

        newJobDescriptionArea.setBackground(new java.awt.Color(153, 153, 153));
        newJobDescriptionArea.setColumns(20);
        newJobDescriptionArea.setFont(new java.awt.Font("Myriad Pro", 0, 14));
        newJobDescriptionArea.setForeground(new java.awt.Color(255, 255, 255));
        newJobDescriptionArea.setLineWrap(true);
        newJobDescriptionArea.setRows(5);
        newJobDescriptionArea.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jScrollPane2.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setViewportView(newJobDescriptionArea);

        newJob.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 140, 230, 100));

        newJobDescriptionLabel.setFont(new java.awt.Font("Myriad Pro", 1, 16));
        newJobDescriptionLabel.setForeground(new java.awt.Color(255, 255, 255));
        newJobDescriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        newJobDescriptionLabel.setText("Description:");
        newJob.add(newJobDescriptionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 100, 30));
        newJob.add(newJobBelongComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 240, -1));

        newJobButon.setText("Create");
        newJobButon.setEnabled(false);
        newJobButon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newJobButonMouseClicked(evt);
            }
        });
        newJob.add(newJobButon, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 100, -1));

        newJobName.setFont(new java.awt.Font("Myriad Pro", 1, 36));
        newJobName.setForeground(new java.awt.Color(255, 255, 255));
        newJobName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newJobName.setText("New Job");
        newJob.add(newJobName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 350, 60));

        newJobFrame.getContentPane().add(newJob, java.awt.BorderLayout.CENTER);

        newJobFrame.getAccessibleContext().setAccessibleName("newJobFrame");

        statsFrame.setTitle("TaskMania - Statistics");
        statsFrame.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        statsFrame.setIconImages(null);
        statsFrame.setMinimumSize(new java.awt.Dimension(600, 350));
        statsFrame.setResizable(false);
        statsFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                statsFrameWindowClosed(evt);
            }
        });

        zebraStatsScroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        zebraStatsScroll.setAutoscrolls(true);
        zebraStatsScroll.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        zebraStatsScroll.setPreferredSize(new java.awt.Dimension(150, 322));

        jTree2.setEditable( true );
        jTree2.setShowsRootHandles( true );
        jTree2.setAutoscrolls(true);
        jTree2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTree2.setMaximumSize(new java.awt.Dimension(150, 350));
        jTree2.setMinimumSize(new java.awt.Dimension(150, 350));
        jTree2.setPreferredSize(new java.awt.Dimension(150, 350));
        jTree2.setRootVisible(false);
        jTree2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree2MouseClicked(evt);
            }
        });
        zebraStatsScroll.setViewportView(jTree2);

        statsFrame.getContentPane().add(zebraStatsScroll, java.awt.BorderLayout.LINE_START);

        mainStatsPanel.setMinimumSize(new java.awt.Dimension(450, 350));
        mainStatsPanel.setPreferredSize(new java.awt.Dimension(450, 350));
        mainStatsPanel.setLayout(new java.awt.CardLayout());

        defaultStatsPane.setBackground(new java.awt.Color(0, 0, 0));
        defaultStatsPane.setMaximumSize(new java.awt.Dimension(350, 350));
        defaultStatsPane.setMinimumSize(new java.awt.Dimension(350, 350));
        defaultStatsPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        defaultStatsTextScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        defaultStatsTextScrollPane.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        defaultStatsTextScrollPane.setEnabled(false);
        defaultStatsTextScrollPane.setFocusable(false);
        defaultStatsTextScrollPane.setMaximumSize(new java.awt.Dimension(180, 50));
        defaultStatsTextScrollPane.setMinimumSize(new java.awt.Dimension(180, 50));
        defaultStatsTextScrollPane.setOpaque(false);
        defaultStatsTextScrollPane.setPreferredSize(new java.awt.Dimension(180, 50));
        defaultStatsTextScrollPane.setRequestFocusEnabled(false);
        defaultStatsTextScrollPane.setOpaque(false);
        defaultStatsTextScrollPane.getViewport().setOpaque(false);

        defaultStatsText.setBackground(new java.awt.Color(1, 1, 1));
        defaultStatsText.setColumns(15);
        defaultStatsText.setEditable(false);
        defaultStatsText.setFont(new java.awt.Font("Dialog", 0, 18));
        defaultStatsText.setForeground(new java.awt.Color(255, 255, 255));
        defaultStatsText.setLineWrap(true);
        defaultStatsText.setRows(3);
        defaultStatsText.setTabSize(7);
        defaultStatsText.setWrapStyleWord(true);
        defaultStatsText.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        defaultStatsText.setDisabledTextColor(new java.awt.Color(254, 254, 254));
        defaultStatsText.setEnabled(false);
        defaultStatsText.setFocusable(false);
        defaultStatsText.setMaximumSize(new java.awt.Dimension(180, 50));
        defaultStatsText.setMinimumSize(new java.awt.Dimension(180, 50));
        defaultStatsText.setOpaque(false);
        defaultStatsText.setOpaque(false);
        jScrollPane2.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        defaultStatsTextScrollPane.setViewportView(defaultStatsText);
        defaultStatsText.setText("Please select a component from the left to view the accumulated statistics so far...");

        defaultStatsPane.add(defaultStatsTextScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 430, 80));

        mainStatsPanel.add(defaultStatsPane, "card2");
        defaultStatsPane.setVisible(true);
        statsPane.setVisible(false);

        statsPane.setBackground(new java.awt.Color(0, 0, 0));
        statsPane.setMaximumSize(new java.awt.Dimension(350, 350));
        statsPane.setMinimumSize(new java.awt.Dimension(350, 350));
        statsPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        statsPane.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 430, 270));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        Component currentNode = (Component) jTree1.getLastSelectedPathComponent();
        if (currentNode != null)
        jLabel1.setText(currentNode.getName() + " Statistics");
        statsPane.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 430, 40));

        mainStatsPanel.add(statsPane, "card3");

        statsFrame.getContentPane().add(mainStatsPanel, java.awt.BorderLayout.CENTER);

        updateStatsTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TaskMania");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        tasksPanel.setMaximumSize(new java.awt.Dimension(500, 350));
        tasksPanel.setMinimumSize(new java.awt.Dimension(500, 350));
        tasksPanel.setPreferredSize(new java.awt.Dimension(500, 350));
        tasksPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(150, 322));

        jTree1.setEditable( true );
        jTree1.setShowsRootHandles( true );
        jTree1.setAutoscrolls(true);
        jTree1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTree1.setMaximumSize(new java.awt.Dimension(150, 350));
        jTree1.setMinimumSize(new java.awt.Dimension(150, 350));
        jTree1.setPreferredSize(new java.awt.Dimension(150, 350));
        jTree1.setRootVisible(false);
        jScrollPane1.setViewportView(jTree1);

        tasksPanel.add(jScrollPane1, java.awt.BorderLayout.LINE_START);

        mainPane.setBackground(new java.awt.Color(0, 0, 0));
        mainPane.setMaximumSize(new java.awt.Dimension(350, 350));
        mainPane.setMinimumSize(new java.awt.Dimension(350, 350));
        mainPane.setPreferredSize(new java.awt.Dimension(350, 350));
        mainPane.setLayout(new java.awt.CardLayout());

        taskPane.setBackground(new java.awt.Color(0, 0, 0));
        taskPane.setMaximumSize(new java.awt.Dimension(350, 350));
        taskPane.setMinimumSize(new java.awt.Dimension(350, 350));
        taskPane.setPreferredSize(new java.awt.Dimension(350, 350));
        taskPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/play.png"))); // NOI18N
        playLabel.setVisible(false);
        playLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playLabelMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playLabelMouseClicked(evt);
            }
        });
        taskPane.add(playLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 150, -1, -1));

        stopLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stop.png"))); // NOI18N
        stopLabel.setVisible(false);
        stopLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                stopLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                stopLabelMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stopLabelMouseClicked(evt);
            }
        });
        taskPane.add(stopLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, -1, -1));

        timeSpentLabel.setFont(new java.awt.Font("Myriad Pro", 1, 17));
        timeSpentLabel.setForeground(new java.awt.Color(255, 255, 255));
        timeSpentLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        timeSpentLabel.setText("Time Spent:");
        timeSpentLabel.setAlignmentY(0.0F);
        timeSpentLabel.setFocusable(false);
        timeSpentLabel.setVisible(false);
        taskPane.add(timeSpentLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 110, 22));

        taskStatus.setFont(new java.awt.Font("Myriad Pro", 0, 15));
        taskStatus.setForeground(new java.awt.Color(255, 255, 255));
        taskStatus.setFocusable(false);
        taskPane.add(taskStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 225, 230, 30));

        taskName.setBackground(new java.awt.Color(0, 0, 0));
        taskName.setFont(new java.awt.Font("Myriad Pro", 1, 20));
        taskName.setForeground(new java.awt.Color(255, 255, 255));
        taskName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        taskName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        taskPane.add(taskName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 320, 40));

        pauseLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pause.png"))); // NOI18N
        pauseLabel.setVisible(false);
        pauseLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pauseLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pauseLabelMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pauseLabelMouseClicked(evt);
            }
        });
        taskPane.add(pauseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 150, 60, 60));

        statusLabel.setFont(new java.awt.Font("Myriad Pro", 1, 17));
        statusLabel.setForeground(new java.awt.Color(255, 255, 255));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        statusLabel.setText("Status:");
        statusLabel.setVisible(false);
        taskPane.add(statusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 110, -1));

        descriptionLabel.setFont(new java.awt.Font("Myriad Pro", 1, 17));
        descriptionLabel.setForeground(new java.awt.Color(255, 255, 255));
        descriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        descriptionLabel.setText("Description:");
        descriptionLabel.setVisible(false);
        taskPane.add(descriptionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 110, -1));

        taskTimeSpent.setFont(new java.awt.Font("Myriad Pro", 0, 15));
        taskTimeSpent.setForeground(new java.awt.Color(255, 255, 255));
        taskTimeSpent.setFocusable(false);
        taskPane.add(taskTimeSpent, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 255, 230, 30));

        stopwatchLabel.setBackground(new java.awt.Color(255, 255, 255));
        stopwatchLabel.setFont(new java.awt.Font("Myriad Pro", 1, 36));
        stopwatchLabel.setForeground(new java.awt.Color(255, 255, 255));
        stopwatchLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stopwatchLabel.setFocusable(false);
        stopwatchLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        taskPane.add(stopwatchLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 59, 240, 70));

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Delete.png"))); // NOI18N
        deleteButton.setVisible(false);
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteButtonMouseClicked(evt);
            }
        });
        taskPane.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 20, 20));

        taskDescriptionScrollPane.setBorder(null);
        taskDescriptionScrollPane.setOpaque(false);
        taskDescriptionScrollPane.getViewport().setOpaque(false);

        taskDescription.setColumns(20);
        taskDescription.setEditable(false);
        taskDescription.setFont(new java.awt.Font("Myriad Pro", 0, 14));
        taskDescription.setForeground(new java.awt.Color(255, 255, 255));
        taskDescription.setLineWrap(true);
        taskDescription.setWrapStyleWord(true);
        taskDescription.setRows(4);
        taskDescription.setBorder(null);
        taskDescription.setOpaque(false);
        taskDescriptionScrollPane.setViewportView(taskDescription);

        taskPane.add(taskDescriptionScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 230, 80));

        taskManiaWelcome.setBackground(new java.awt.Color(51, 0, 51));
        taskManiaWelcome.setEditable(false);
        taskManiaWelcome.setFont(new java.awt.Font("Myriad Pro", 1, 36));
        taskManiaWelcome.setForeground(new java.awt.Color(255, 255, 255));
        taskManiaWelcome.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        taskManiaWelcome.setText("TaskMania");
        taskManiaWelcome.setAutoscrolls(false);
        taskManiaWelcome.setBorder(null);
        taskManiaWelcome.setOpaque(false);
        taskPane.add(taskManiaWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 350, 100));
        taskManiaWelcome.setVisible(true);

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png")));
        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        taskPane.add(logoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, -4, 340, 280));
        logoLabel.setVisible(true);

        mainPane.add(taskPane, "card4");

        actionPane.setBackground(new java.awt.Color(51, 0, 51));
        actionPane.setMaximumSize(new java.awt.Dimension(350, 350));
        actionPane.setMinimumSize(new java.awt.Dimension(350, 350));
        actionPane.setPreferredSize(new java.awt.Dimension(350, 350));
        actionPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        taskStatus2.setFont(new java.awt.Font("Myriad Pro", 0, 16));
        taskStatus2.setForeground(new java.awt.Color(255, 255, 255));
        taskStatus2.setFocusable(false);
        actionPane.add(taskStatus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 95, 200, 30));

        statusLabel2.setFont(new java.awt.Font("Myriad Pro", 1, 18));
        statusLabel2.setForeground(new java.awt.Color(255, 255, 255));
        statusLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        statusLabel2.setText("Status:");
        statusLabel.setVisible(false);
        actionPane.add(statusLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 120, -1));

        descriptionLabel2.setFont(new java.awt.Font("Myriad Pro", 1, 18));
        descriptionLabel2.setForeground(new java.awt.Color(255, 255, 255));
        descriptionLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        descriptionLabel2.setText("Description:");
        descriptionLabel.setVisible(false);
        actionPane.add(descriptionLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 120, -1));

        doneButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Done.png"))); // NOI18N
        doneButton.setVisible(false);
        doneButton.setAlignmentY(0.0F);
        doneButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                doneButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                doneButtonMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                doneButtonMouseClicked(evt);
            }
        });
        actionPane.add(doneButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 265, 60, 60));

        deleteButtonAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Delete.png"))); // NOI18N
        deleteButtonAction.setVisible(false);
        deleteButtonAction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteButtonActionMouseClicked(evt);
            }
        });
        actionPane.add(deleteButtonAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 20, 20));

        actionDescriptionScrollPane.setBorder(null);
        actionDescriptionScrollPane.setOpaque(false);
        actionDescriptionScrollPane.getViewport().setOpaque(false);

        actionDescription.setColumns(20);
        actionDescription.setEditable(false);
        actionDescription.setFont(new java.awt.Font("Myriad Pro", 0, 14));
        actionDescription.setForeground(new java.awt.Color(255, 255, 255));
        actionDescription.setLineWrap(true);
        actionDescription.setWrapStyleWord(true);
        actionDescription.setRows(5);
        actionDescription.setBorder(null);
        actionDescription.setOpaque(false);
        actionDescriptionScrollPane.setViewportView(actionDescription);

        actionPane.add(actionDescriptionScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 200, 120));

        actionLabel.setFont(new java.awt.Font("Myriad Pro", 1, 36));
        actionLabel.setForeground(new java.awt.Color(255, 255, 255));
        actionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        actionLabel.setText("Action");
        actionPane.add(actionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 60));

        actionName.setBackground(new java.awt.Color(0, 0, 0));
        actionName.setFont(new java.awt.Font("Myriad Pro", 1, 24));
        actionName.setForeground(new java.awt.Color(255, 255, 255));
        actionName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        actionName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        actionPane.add(actionName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 330, 40));

        mainPane.add(actionPane, "card3");

        jobPane.setBackground(new java.awt.Color(0, 0, 0));
        jobPane.setMaximumSize(new java.awt.Dimension(350, 350));
        jobPane.setMinimumSize(new java.awt.Dimension(350, 350));
        jobPane.setPreferredSize(new java.awt.Dimension(350, 350));
        jobPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        timeSpentLabel1.setFont(new java.awt.Font("Myriad Pro", 1, 18));
        timeSpentLabel1.setForeground(new java.awt.Color(255, 255, 255));
        timeSpentLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        timeSpentLabel1.setText("Time Spent:");
        timeSpentLabel1.setAlignmentY(0.0F);
        timeSpentLabel1.setFocusable(false);
        timeSpentLabel.setVisible(false);
        jobPane.add(timeSpentLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 130, 22));

        taskStatus1.setFont(new java.awt.Font("Myriad Pro", 0, 18));
        taskStatus1.setForeground(new java.awt.Color(255, 255, 255));
        taskStatus1.setFocusable(false);
        jobPane.add(taskStatus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 190, 27));

        taskName1.setBackground(new java.awt.Color(0, 0, 0));
        taskName1.setFont(new java.awt.Font("Myriad Pro", 1, 24));
        taskName1.setForeground(new java.awt.Color(255, 255, 255));
        taskName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        taskName1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jobPane.add(taskName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 330, 40));

        statusLabel1.setFont(new java.awt.Font("Myriad Pro", 1, 18));
        statusLabel1.setForeground(new java.awt.Color(255, 255, 255));
        statusLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        statusLabel1.setText("Status:");
        statusLabel.setVisible(false);
        jobPane.add(statusLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 130, -1));

        descriptionLabel1.setFont(new java.awt.Font("Myriad Pro", 1, 18));
        descriptionLabel1.setForeground(new java.awt.Color(255, 255, 255));
        descriptionLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        descriptionLabel1.setText("Description:");
        descriptionLabel.setVisible(false);
        jobPane.add(descriptionLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, -1));

        taskTimeSpent1.setFont(new java.awt.Font("Myriad Pro", 0, 18));
        taskTimeSpent1.setForeground(new java.awt.Color(255, 255, 255));
        taskTimeSpent1.setFocusable(false);
        jobPane.add(taskTimeSpent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 190, 26));

        deleteButtonJob.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Delete.png"))); // NOI18N
        deleteButtonJob.setVisible(false);
        deleteButtonJob.setText(null);
        deleteButtonJob.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteButtonJobMouseClicked(evt);
            }
        });
        jobPane.add(deleteButtonJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 20, 20));

        jobDescriptionScrollPane.setBorder(null);
        jobDescriptionScrollPane.setOpaque(false);
        jobDescriptionScrollPane.getViewport().setOpaque(false);

        jobDescription.setColumns(20);
        jobDescription.setEditable(false);
        jobDescription.setFont(new java.awt.Font("Myriad Pro", 0, 14));
        jobDescription.setForeground(new java.awt.Color(255, 255, 255));
        jobDescription.setLineWrap(true);
        jobDescription.setWrapStyleWord(true);
        jobDescription.setRows(5);
        jobDescription.setBorder(null);
        jobDescription.setOpaque(false);
        jobDescriptionScrollPane.setViewportView(jobDescription);

        jobPane.add(jobDescriptionScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 190, 200));

        jobLabel.setFont(new java.awt.Font("Myriad Pro", 1, 36));
        jobLabel.setForeground(new java.awt.Color(255, 255, 255));
        jobLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jobLabel.setText("Job");
        jobPane.add(jobLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 330, 60));

        mainPane.add(jobPane, "card5");

        tasksPanel.add(mainPane, java.awt.BorderLayout.CENTER);

        getContentPane().add(tasksPanel, "card3");

        TaskManiaMenuBar.removeAll();
        JMenu newMenu = new JMenu("New");
        JMenu viewMenu = new JMenu("View");

        JMenuItem newJobItem = new JMenuItem("Job");
        JMenuItem newActionItem = new JMenuItem("Action");
        JMenuItem newTaskItem = new JMenuItem("Task");

        JMenuItem statisticsItem = new JMenuItem("Statistics");
        JCheckBoxMenuItem closedItems = new JCheckBoxMenuItem("Closed Items",false);

        newMenu.add(newJobItem);
        newJobItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                newJobFrame.pack();

                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
                Point newLocation = new Point(middle.x - (newJobFrame.getWidth() / 2),
                    middle.y - (newJobFrame.getHeight() / 2));
                newJobFrame.setLocation(newLocation);

                newJobNameField.setText("");
                newJobDescriptionArea.setText("");

                newJobFrame.setVisible(true);

                newJobBelongComboBox.removeAllItems();
                for(String s:graphic.getJobNames("")){
                    newJobBelongComboBox.addItem(s);
                }
            }
        });

        newMenu.add(newActionItem);
        newActionItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                newActionFrame.pack();
                newActionNameField.setText("");
                newActionDescriptionArea.setText("");
                newActionFrame.setVisible(true);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
                Point newLocation = new Point(middle.x - (newActionFrame.getWidth() / 2),
                    middle.y - (newActionFrame.getHeight() / 2));
                newActionFrame.setLocation(newLocation);

                newActionBelongComboBox.removeAllItems();
                for(String s:graphic.getJobNames("")){
                    newActionBelongComboBox.addItem(s);
                }

            }
        });

        newMenu.add(newTaskItem);
        newTaskItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                newTaskFrame.pack();
                newTaskNameField.setText("");
                newTaskDescriptionTextArea.setText("");
                newTaskFrame.setVisible(true);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
                Point newLocation = new Point(middle.x - (newTaskFrame.getWidth() / 2),
                    middle.y - (newTaskFrame.getHeight() / 2));
                newTaskFrame.setLocation(newLocation);

                newTaskBelongComboBox.removeAllItems();
                for(String s:graphic.getJobNames("")){
                    newTaskBelongComboBox.addItem(s);
                }
            }
        });

        viewMenu.add(statisticsItem);
        statisticsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                statsFrame.pack();
                Component currentNode = (Component) jTree1.getLastSelectedPathComponent();
                if(currentNode == null){
                    statsPane.setVisible(false);
                    defaultStatsPane.setVisible(true);
                }else{
                    statsPane.setVisible(true);
                    defaultStatsPane.setVisible(false);

                    javax.swing.tree.TreePath path = jTree1.getPathForRow(jTree1.getSelectionRows()[0]);
                    jTree2.setSelectionPath(path);
                    //jTree2.setSelectionRow(jTree1.getSelectionRows()[0]);
                    jLabel1.setText(currentNode.getName() + " Statistics");
                    updateStatsChart();
                    //updateStatsTree();
                    statsPane.repaint();
                }
                Point windowMiddle = getWindowMiddle();
                Point statsMiddle = new Point(windowMiddle.x + 10,
                    windowMiddle.y + 20);
                statsFrame.setLocation(statsMiddle);
                statsFrame.setVisible(true);
            }
        });

        viewMenu.addSeparator();

        viewMenu.add(closedItems);
        closedItems.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                AbstractButton aButton = (AbstractButton) event.getSource();
                viewClosed = aButton.getModel().isSelected();

                updateTree(viewClosed);
            }
        });

        TaskManiaMenuBar.add(newMenu);
        TaskManiaMenuBar.add(viewMenu);
        TaskManiaMenuBar.setName("TaskMania"); // NOI18N
        setJMenuBar(TaskManiaMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	 	private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
         
           Component node = (Component) jTree1.getLastSelectedPathComponent();
           //System.out.println(node.getClass());
           String description = null;
           if (node != null) {
               if (node.getClass() == Tasks.Action.class || node.getClass() == Tasks.Job.class || node.getClass() == Tasks.Task.class) {
                   
                   logoLabel.setVisible(false);
                   taskManiaWelcome.setVisible(false);
                   taskName.setText(node.getName());
                   
                   statusLabel.setVisible(true);
                   deleteButton.setVisible(true);
                   taskStatus.setText(node.getState());
         
                   if (node.getClass() == Tasks.Task.class) {
                       taskName.setText(node.getName());
                       taskName.setVisible(true);
                       descriptionLabel.setVisible(true);
                       taskDescription.setText(node.getDescription());
                       taskDescription.setCaretPosition(0);
                       taskDescription.setVisible(true);
                       actionDescription.setCaretPosition(0);
                       
                       statusLabel.setVisible(true);
                       deleteButton.setVisible(true);
                       taskStatus.setText(node.getState());
                       jobPane.setVisible(false);
                       actionPane.setVisible(false);
                       taskPane.setVisible(true);
                       ((ImagePanel) taskPane).task = true;
         
                       taskTimeSpent.setVisible(true);
                       timeSpentLabel.setVisible(true);
                       playLabel.setVisible(true);
                       stopLabel.setVisible(true);
                       pauseLabel.setVisible(true);
                       taskStatus.setVisible(true);
                       taskTimeSpent.setText(Time.periodToFormat(((Tasks.Task) node).getElapsedTime()));
                   } else {
                       taskPane.setVisible(false);
                       if (node.getClass() == Tasks.Job.class) {
                           ((ImagePanel) jobPane).task = false;
                           jobPane.setVisible(true);
                           actionPane.setVisible(false);
                           taskPane.setVisible(false);
         
                           taskTimeSpent1.setText(Time.periodToFormat(((Tasks.Job) node).getElapsedTime()));

                           taskTimeSpent1.setVisible(true);
                           timeSpentLabel1.setVisible(true);
                           taskName1.setText(node.getName());
                           taskName1.setVisible(true);
                           jobLabel.setVisible(true);
                           
                           taskStatus1.setText(node.getState());
                           taskStatus1.setVisible(true);
         
                           descriptionLabel1.setVisible(true);
                           jobDescription.setVisible(true);
                           jobDescription.setText(node.getDescription());
                           jobDescription.setCaretPosition(0);
         
                           statusLabel1.setVisible(true);
                           deleteButtonJob.setVisible(true);
         
                       } else if (node.getClass() == Tasks.Action.class) {
                           ((ImagePanel) actionPane).task = false;
                           actionPane.setVisible(true);
                           jobPane.setVisible(false);
                           taskPane.setVisible(false);
                           actionName.setText(node.getName());
         
                           descriptionLabel2.setVisible(true);
                           actionDescription.setText(node.getDescription());
                           actionDescription.setCaretPosition(0);
                           statusLabel2.setVisible(true);
                           deleteButtonAction.setVisible(true);
                           taskStatus2.setText(node.getState());
                           actionLabel.setVisible(true);
                           actionName.setVisible(true);
                           actionDescription.setVisible(true);
                           taskStatus2.setVisible(true);
                           
                           if (((Tasks.Action) node).getState().equals("To Do")) {
                               doneButton.setVisible(true);
                           }
         
                       }
         
                   }
               }
           }
           jobPane.repaint();
           actionPane.repaint();
           taskPane.repaint();
	 }//GEN-LAST:event_jTree1MouseClicked
    
	 private void playLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playLabelMousePressed
     	playLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/playClicked.png")));        // TODO add your handling code here:
	 }//GEN-LAST:event_playLabelMousePressed
    
	private void playLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playLabelMouseReleased
    	playLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/play.png")));
	}//GEN-LAST:event_playLabelMouseReleased
    
	private void pauseLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pauseLabelMousePressed
    	pauseLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pauseClicked.png")));
	}//GEN-LAST:event_pauseLabelMousePressed
    
														private void pauseLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pauseLabelMouseReleased
                                                                                                                                pauseLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pause.png")));
													}//GEN-LAST:event_pauseLabelMouseReleased
    
													private void stopLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopLabelMousePressed
                                                                                                                        stopLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stopClicked.png")));
												}//GEN-LAST:event_stopLabelMousePressed
    
												private void stopLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopLabelMouseReleased
                                                                                                                stopLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stop.png")));
											}//GEN-LAST:event_stopLabelMouseReleased
    
											private void newTaskCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newTaskCreateMouseClicked
                                                                                                        String name = newTaskNameField.getText(), description = newTaskDescriptionTextArea.getText();
                                                                                                        String belongs = newTaskBelongComboBox.getSelectedItem().toString();
    
                                                                                                        for (int i = 0; i < belongs.length(); i++) {
                                                                                                            if (belongs.charAt(i) != ' ') {
                                                                                                                belongs = belongs.substring(i);
                                                                                                                break;
                                                                                                            }
                                                                                                        }
    
                                                                                                        Leaf folha = new Task(name, description);
                                                                                                        graphic.add(folha, belongs);
    
                                                                                                        updateTree(viewClosed);

                                                                                                        newTaskFrame.setVisible(false);
										}//GEN-LAST:event_newTaskCreateMouseClicked
    
										private void newJobButonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newJobButonMouseClicked
                                                                                                String name = newJobNameField.getText(), description = newJobDescriptionArea.getText();
                                                                                                String belongs = newJobBelongComboBox.getSelectedItem().toString();
    
                                                                                                for (int i = 0; i < belongs.length(); i++) {
                                                                                                    if (belongs.charAt(i) != ' ') {
                                                                                                        belongs = belongs.substring(i);
                                                                                                        break;
                                                                                                    }
                                                                                                }
    
                                                                                                Job folha = new Job(name, description);
                                                                                                graphic.add(folha, belongs);
         
                                                                                                updateTree(viewClosed);
                                                                                                newJobFrame.setVisible(false);
									}//GEN-LAST:event_newJobButonMouseClicked
    
									private void newActionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newActionButtonMouseClicked
                                                                                        String name = newActionNameField.getText(), description = newActionDescriptionArea.getText();
                                                                                        String belongs = newActionBelongComboBox.getSelectedItem().toString();
                                                                                        String deadline = newActionDeadlineField.getText();
    
                                                                                        for (int i = 0; i < belongs.length(); i++) {
                                                                                            if (belongs.charAt(i) != ' ') {
                                                                                                belongs = belongs.substring(i);
                                                                                                break;
                                                                                            }
                                                                                        }
    
                                                                                        Leaf folha = new Action(name, description);
                                                                                        graphic.add(folha, belongs);
                                                                                        
    
                                                                                        updateTree(viewClosed);
    
                                                                                        newActionFrame.setVisible(false);
								}//GEN-LAST:event_newActionButtonMouseClicked
    
								private void playLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playLabelMouseClicked
                                                                                Component node = (Component) jTree1.getLastSelectedPathComponent();
    
    
                                                                                if (((Tasks.Task) node).state.getName().equalsIgnoreCase("closed")) {
                                                                                    JOptionPane.showMessageDialog(null, "Can't run a previously closed task.", "Invalid Operation", JOptionPane.ERROR_MESSAGE);
                                                                                    return;
                                                                                } else if (node != null
                                                                                        && (!stopwatchLabel.isVisible() || ((Tasks.Task) node).state.getName().equalsIgnoreCase("paused"))) {
                                                                                    try {
                                                                                        ((Tasks.Task) node).start();
                                                                                        taskStatus.setText(node.getState());
                                                                                    } catch (InvalidTransitionException ex) {
                                                                                        Logger.getLogger(TaskManiaGUI.class.getName()).log(Level.SEVERE, null, ex);
                                                                                    }
                                                                                    jTree1.setEnabled(false);
                                                                                    stopwatchLabel.setVisible(true);
                                                                                }
								}//GEN-LAST:event_playLabelMouseClicked
    
								private void pauseLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pauseLabelMouseClicked
                                                                                Component node = (Component) jTree1.getLastSelectedPathComponent();
                                                                                System.out.println(node.getClass());
                                                                                if (((Tasks.Task) node).state.getName().equalsIgnoreCase("closed")) {
                                                                                            JOptionPane.showMessageDialog(null, "Can't tatspause a previously closed task.", "Invalid Operation", JOptionPane.ERROR_MESSAGE);
                                                                                            return;
                                                                                        }
                                                                                
                                                                                if (node != null && stopwatchLabel.isVisible()) {
    
                                                                                    try {
                                                                                         
                                                                                        
                                                                                        ((Tasks.Task) node).pause();
                                                                                        stopwatchLabel.setVisible(false);
                                                                                        jTree1.setEnabled(true);
                                                                                        taskStatus.setText(node.getState());
                                                                                        taskTimeSpent.setText(Time.periodToFormat(((Tasks.Task) node).getElapsedTime()));
                                                                                    } catch (InvalidTransitionException ex) {
                                                                                        Logger.getLogger(TaskManiaGUI.class.getName()).log(Level.SEVERE, null, ex);
                                                                                    }
                                                                                }
							}//GEN-LAST:event_pauseLabelMouseClicked
    
							private void stopLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopLabelMouseClicked
                                                                        Component node = (Component) jTree1.getLastSelectedPathComponent();
    
                                                                        if (((Tasks.Task) node).state.getName().equalsIgnoreCase("To Do")) {
                                                                            JOptionPane.showMessageDialog(null, "This task hasn't been initialized yet. Delete it, if redundant.", "Task To Do", JOptionPane.INFORMATION_MESSAGE);
                                                                            return;
                                                                        }
                                                                        else if (((Tasks.Task) node).state.getName().equalsIgnoreCase("Closed")) {
                                                                            JOptionPane.showMessageDialog(null, "This task is already closed.", "Task Closed", JOptionPane.INFORMATION_MESSAGE);
                                                                            return;
                                                                        } else if (node != null) {
    
                                                                            stopwatchLabel.setVisible(false);
                                                                            jTree1.setEnabled(true);
    
                                                                            try {
                                                                                System.out.print("Entrea");
                                                                                ((Tasks.Task) node).close();
                                                                                taskStatus.setText(node.getState());
                                                                                taskTimeSpent.setText(Time.periodToFormat(((Tasks.Task) node).getElapsedTime()));
                                                                            } catch (InvalidTransitionException ex) {
                                                                                Logger.getLogger(TaskManiaGUI.class.getName()).log(Level.SEVERE, null, ex);
                                                                            }
                                                                        }
						}//GEN-LAST:event_stopLabelMouseClicked
    
						private void newActionNameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newActionNameFieldKeyReleased
                                                                if (newActionNameField.getText().equals("")) {
                                                                    newActionButton.setEnabled(false);
                                                                } else {
                                                                    newActionButton.setEnabled(true);
                                                                }
					}//GEN-LAST:event_newActionNameFieldKeyReleased
    
					private void newJobNameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newJobNameFieldKeyReleased
                                                        if (newJobNameField.getText().equals("")) {
                                                            newJobButon.setEnabled(false);
                                                        } else {
                                                            newJobButon.setEnabled(true);
                                                        }
				}//GEN-LAST:event_newJobNameFieldKeyReleased
    
				private void newTaskNameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newTaskNameFieldKeyReleased
                                                if (newTaskNameField.getText().equals("")) {
                                                    newTaskCreate.setEnabled(false);
                                                } else {
                                                    newTaskCreate.setEnabled(true);
                                                }
			}//GEN-LAST:event_newTaskNameFieldKeyReleased
    
			private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
                                        Component node = (Component) jTree1.getLastSelectedPathComponent();
                                        
                                        int n;
                                        if(stopwatchLabel.isVisible()){
                                            JOptionPane.showMessageDialog(null, "Can't delete a running task. Please stop it, then delete it.", "Invalid Operation", JOptionPane.ERROR_MESSAGE);
                                            return;
                                        }
                                        if (node.getClass() == Tasks.Job.class) {
                                            n = JOptionPane.showConfirmDialog(null, "Are you sure? This will delete all the tasks of this job", "Delete", JOptionPane.YES_NO_OPTION);
                                        } else {
                                            n = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete", JOptionPane.YES_NO_OPTION);
                                        }
    
                                        if (n == 0) {
                                            graphic.deleteNode(node);
                                            updateTree(viewClosed);
    
                                            ((ImagePanel)taskPane).task = false;
                                            
                                            playLabel.setVisible(false);
                                            stopLabel.setVisible(false);
                                            pauseLabel.setVisible(false);
                                            taskName.setVisible(false);
                                            taskStatus.setVisible(false);
                                            taskTimeSpent.setVisible(false);
                                            taskDescription.setVisible(false);
                                            statusLabel.setVisible(false);
                                            timeSpentLabel.setVisible(false);
                                            descriptionLabel.setVisible(false);
                                            deleteButton.setVisible(false);
                                            logoLabel.setVisible(false);
                                            taskManiaWelcome.setVisible(false);
                                            taskName.setVisible(false);
                                        }
		}//GEN-LAST:event_deleteButtonMouseClicked
    
		private void deleteButtonJobMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonJobMouseClicked
                                Component node = (Component) jTree1.getLastSelectedPathComponent();
    
                                int n;
                                if (node.getClass() == Tasks.Job.class) {
                                    n = JOptionPane.showConfirmDialog(null, "Are you sure? This will delete all the tasks of this job", "Delete", JOptionPane.YES_NO_OPTION);
                                } else {
                                    n = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete", JOptionPane.YES_NO_OPTION);
                                }
    
                                if (n == 0) {
                                    graphic.deleteNode(node);
                                    updateTree(viewClosed);
    
                                    ((ImagePanel) jobPane).task = false;
    
                                    jobLabel.setVisible(false);
                                    taskName1.setVisible(false);
                                    taskStatus1.setVisible(false);
                                    taskTimeSpent1.setVisible(false);
                                    jobDescription.setVisible(false);
                                    statusLabel1.setVisible(false);
                                    timeSpentLabel1.setVisible(false);
                                    descriptionLabel1.setVisible(false);
                                    deleteButtonJob.setVisible(false);
                                    logoLabel.setVisible(false);
                                    taskManiaWelcome.setVisible(false);
                                }
	}//GEN-LAST:event_deleteButtonJobMouseClicked
    
	private void deleteButtonActionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonActionMouseClicked
                        Component node = (Component) jTree1.getLastSelectedPathComponent();
    
                        int n;
                        if (node.getClass() == Tasks.Job.class) {
                            n = JOptionPane.showConfirmDialog(null, "Are you sure? This will delete all the tasks of this job", "Delete", JOptionPane.YES_NO_OPTION);
                        } else {
                            n = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete", JOptionPane.YES_NO_OPTION);
                        }
    
                        if (n == 0) {
                            graphic.deleteNode(node);
                            updateTree(viewClosed);
    
                            ((ImagePanel) actionPane).task = false;
    
                            actionName.setVisible(false);
                            taskStatus2.setVisible(false);
                            actionDescription.setVisible(false);
                            statusLabel2.setVisible(false);
                            descriptionLabel2.setVisible(false);
                            deleteButtonAction.setVisible(false);
                            doneButton.setVisible(false);
                            logoLabel.setVisible(false);
                            taskManiaWelcome.setVisible(false);
                            actionLabel.setVisible(false);
                            
                        }
			}//GEN-LAST:event_deleteButtonActionMouseClicked

			private void doneButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doneButtonMousePressed
                            doneButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DoneClicked.png")));
		}//GEN-LAST:event_doneButtonMousePressed

		private void doneButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doneButtonMouseReleased
                    doneButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Done.png")));
	}//GEN-LAST:event_doneButtonMouseReleased

	private void doneButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doneButtonMouseClicked
            Component node = (Component) jTree1.getLastSelectedPathComponent();

            if (node != null) {
                ((Tasks.Action) node).close();
                taskStatus2.setText(node.getState());
                doneButton.setVisible(false);

            }
}//GEN-LAST:event_doneButtonMouseClicked

        private void jTree2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree2MouseClicked
            Component node = (Component) jTree2.getLastSelectedPathComponent();

            if (node != null) {
                defaultStatsPane.setVisible(false);
                statsPane.setVisible(true);
                jLabel1.setText(node.getName() + " Statistics");

                updateStatsChart();

                statsPane.repaint();
            }
}//GEN-LAST:event_jTree2MouseClicked

        private void newActionBelongComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newActionBelongComboBoxActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_newActionBelongComboBoxActionPerformed

        private void statsFrameWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_statsFrameWindowClosed
        }//GEN-LAST:event_statsFrameWindowClosed
    private void updateStatsTree() {
        jTree2 = new ZebraJTree(graphic);
        jTree2.setEditable(false);
        jTree2.setShowsRootHandles(true);
        jTree2.setAutoscrolls(true);
        jTree2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTree2.setRootVisible(false);
        jTree2.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree2MouseClicked(evt);
            }
        });
        zebraStatsScroll.setViewportView(jTree2);
    }
    
    private class UpdaterThread extends Thread {
        
        public void run() {

            while (true) {             
               Component node = (Component) jTree1.getLastSelectedPathComponent();
               Component statNode = (Component) jTree2.getLastSelectedPathComponent();
                if(node != null && statNode != null){
                    if(node.getState().equals("Running")){
                        try {
                            if(node.getClass() == Tasks.Task.class){
                            ((Tasks.Task) node).pause();
                            ((Tasks.Task) node).start();
                            }
                            
                            if(node.getName().equals(statNode.getName()))
                                ((ChartPanel) jPanel1).setChart(StatsChartPanel.buildGraphic(node));
                            
                            ((ChartPanel) jPanel1).setChart(StatsChartPanel.buildGraphic(statNode));

                        } catch (InvalidTransitionException ex) {
                            Logger.getLogger(TaskManiaGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                else{
                    if(node != null && statNode == null){
                        if(node.getState().equals("Running")){
                            try {
                                if(node.getClass() == Tasks.Task.class){
                                ((Tasks.Task) node).pause();
                                ((Tasks.Task) node).start();
                                }

                                
                                ((ChartPanel) jPanel1).setChart(StatsChartPanel.buildGraphic(node));

                                

                            } catch (InvalidTransitionException ex) {
                                Logger.getLogger(TaskManiaGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                try {
                    sleep(1000);
                }
                catch (InterruptedException e) {
                    // suppress
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "TaskMania");
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TaskManiaGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar TaskManiaMenuBar;
    private javax.swing.JTextArea actionDescription;
    private javax.swing.JScrollPane actionDescriptionScrollPane;
    private javax.swing.JLabel actionLabel;
    private javax.swing.JLabel actionName;
    private javax.swing.JPanel actionPane;
    private javax.swing.JPanel defaultStatsPane;
    private javax.swing.JTextArea defaultStatsText;
    private javax.swing.JScrollPane defaultStatsTextScrollPane;
    private javax.swing.JLabel deleteButton;
    private javax.swing.JLabel deleteButtonAction;
    private javax.swing.JLabel deleteButtonJob;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JLabel descriptionLabel1;
    private javax.swing.JLabel descriptionLabel2;
    private javax.swing.JLabel doneButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTree jTree1;
    private javax.swing.JTree jTree2;
    private javax.swing.JTextArea jobDescription;
    private javax.swing.JScrollPane jobDescriptionScrollPane;
    private javax.swing.JLabel jobLabel;
    private javax.swing.JPanel jobPane;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPanel mainPane;
    private javax.swing.JPanel mainStatsPanel;
    private javax.swing.JPanel newAction;
    private javax.swing.JComboBox newActionBelongComboBox;
    private javax.swing.JLabel newActionBelongLabel;
    private javax.swing.JButton newActionButton;
    private javax.swing.JTextField newActionDeadlineField;
    private javax.swing.JLabel newActionDeadlineLabel;
    private javax.swing.JTextArea newActionDescriptionArea;
    private javax.swing.JLabel newActionDescriptionLabel;
    private javax.swing.JFrame newActionFrame;
    private javax.swing.JLabel newActionName;
    private javax.swing.JTextField newActionNameField;
    private javax.swing.JLabel newActionNameLabel;
    private javax.swing.JPanel newJob;
    private javax.swing.JComboBox newJobBelongComboBox;
    private javax.swing.JLabel newJobBelongsLabel;
    private javax.swing.JButton newJobButon;
    private javax.swing.JTextArea newJobDescriptionArea;
    private javax.swing.JLabel newJobDescriptionLabel;
    private javax.swing.JFrame newJobFrame;
    private javax.swing.JLabel newJobName;
    private javax.swing.JTextField newJobNameField;
    private javax.swing.JLabel newJobNameLabel;
    private javax.swing.JPanel newTask;
    private javax.swing.JComboBox newTaskBelongComboBox;
    private javax.swing.JLabel newTaskBelongsLabel;
    private javax.swing.JButton newTaskCreate;
    private javax.swing.JScrollPane newTaskDescriptionScrollPane;
    private javax.swing.JTextArea newTaskDescriptionTextArea;
    private javax.swing.JLabel newTaskDescritionLabel;
    private javax.swing.JFrame newTaskFrame;
    private javax.swing.JLabel newTaskName;
    private javax.swing.JTextField newTaskNameField;
    private javax.swing.JLabel newTaskNameLabel;
    private javax.swing.JLabel pauseLabel;
    private javax.swing.JLabel playLabel;
    private javax.swing.JFrame statsFrame;
    private javax.swing.JPanel statsPane;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel statusLabel1;
    private javax.swing.JLabel statusLabel2;
    private javax.swing.JLabel stopLabel;
    private javax.swing.JLabel stopwatchLabel;
    private javax.swing.JTextArea taskDescription;
    private javax.swing.JScrollPane taskDescriptionScrollPane;
    private javax.swing.JTextField taskManiaWelcome;
    private javax.swing.JLabel taskName;
    private javax.swing.JLabel taskName1;
    private javax.swing.JPanel taskPane;
    private javax.swing.JLabel taskStatus;
    private javax.swing.JLabel taskStatus1;
    private javax.swing.JLabel taskStatus2;
    private javax.swing.JLabel taskTimeSpent;
    private javax.swing.JLabel taskTimeSpent1;
    private javax.swing.JPanel tasksPanel;
    private javax.swing.JLabel timeSpentLabel;
    private javax.swing.JLabel timeSpentLabel1;
    private javax.swing.JScrollPane zebraStatsScroll;
    // End of variables declaration//GEN-END:variables
}
