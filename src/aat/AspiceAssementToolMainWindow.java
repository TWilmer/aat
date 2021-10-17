package aat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AspiceAssementToolMainWindow {
    interface ToolCallbacks  {
        void actionNew();
        void actionSave();
        void actionOpen();
    }

    private JFrame frame;
    
    List<ToolCallbacks> mCallbacks;
    private RightClickModifyTree questionTree;
    public void addController(ToolCallbacks cb)
    {
        mCallbacks.add(cb);
    }
    public void removeController(ToolCallbacks cb)
    {
        mCallbacks.remove(cb);
    }
    
    public void setQuestionModel(TreeModel model)
    {
        questionTree.setModel(model);
    }


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AspiceAssementToolMainWindow window = new AspiceAssementToolMainWindow();
                    window.frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public AspiceAssementToolMainWindow() {
        mCallbacks=new LinkedList<ToolCallbacks>();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenu mnProject = new JMenu("Project");
        menuBar.add(mnProject);
        
        JMenuItem mntmNew = new JMenuItem("New");
        mntmNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(ToolCallbacks cb : mCallbacks)
                {
                    cb.actionNew();
                }
                
            }
        });
        mnProject.add(mntmNew);
        
        JMenuItem mntmOpen = new JMenuItem("Open");
        mntmOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(ToolCallbacks cb : mCallbacks)
                {
                    cb.actionOpen();
                }
            }
        });
        mnProject.add(mntmOpen);
        
        JMenuItem mntmCommit = new JMenuItem("Commit");
        mntmCommit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(ToolCallbacks cb : mCallbacks)
                {
                    cb.actionSave();
                }
            }
        });
        mnProject.add(mntmCommit);
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        
        JButton btnSave = new JButton("Save");
        panel.add(btnSave);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
        JScrollPane questionPane = new JScrollPane();
        tabbedPane.addTab("Questions", null, questionPane, null);
        
        questionTree = new RightClickModifyTree();
        questionPane.setViewportView(questionTree);
        

        
        JScrollPane setupPane = new JScrollPane();
        tabbedPane.addTab("TeamSetupAndResponsibilities", null, setupPane, null);
        
        JPanel panel_1 = new JPanel();
        setupPane.setViewportView(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        JTree teamSetupTree = new RightClickModifyTree();
        panel_1.add(teamSetupTree);
        
        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2, BorderLayout.SOUTH);
        
        JButton btnAssessTeam = new JButton("Assess Team");
        panel_2.add(btnAssessTeam);
        
        JScrollPane questionairePane = new JScrollPane();
        tabbedPane.addTab("Questionaire", null, questionairePane, null);
        
        JList questionList = new JList();
        questionairePane.setViewportView(questionList);
        
        JScrollPane reportPane = new JScrollPane();
        tabbedPane.addTab("Report", null, reportPane, null);
    }

    public void show() {
       frame.setVisible(true);
        
    }

    protected RightClickModifyTree getQuestionTree() {
        return questionTree;
    }
    public JFrame getFrame() {
        return frame;
    }
    
}
