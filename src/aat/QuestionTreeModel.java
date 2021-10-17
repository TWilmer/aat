package aat;

import java.util.LinkedList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class QuestionTreeModel implements TreeModel {
    abstract class Node {
        
        abstract public Object getChild(int index) ;


        abstract public int getChildCount();

        abstract int getIndexOfChild(Object child); 
       

        public boolean isLeaf() {
            return getChildCount()==0;
        }
        
        
        public String toString() {
            return null;
        }
        
    }
    class Question extends Node {
        String id;
        String text;
        int weight;
        public Question(String id2, String text2) {
            id=id2;
            text=text2;
                   
        }
        @Override
        public Object getChild(int index) {
            return null;
        }
        @Override
        public int getChildCount() {            
            return 0;
        }
        @Override
        int getIndexOfChild(Object child) {
            return -1;
        }
        public String toString()
        {
            return id+" "+text;
        }
     
        
        
    }
    class Practice extends  Node {
        String id;
        String name;
        
        List<Question> questions;

        public Practice(String id2, String text) {
            id=id2;
            name=text;
            questions= new LinkedList<Question>();
        }

        @Override
        public Object getChild(int index) {
           return  questions.get(index);

        }

        @Override
        public int getChildCount() {
            return questions.size();            
        }

        @Override
        int getIndexOfChild(Object child) {
            return questions.indexOf(child);
            
        }
        
        Question addQuestion(String id, String text)
        {
            Question q=new Question(id,text);
            questions.add(q);
            return q;
        }
        public String toString()
        {
            return id+" "+name;
        }

      
    }
    enum Rating {
        N,P,L,F
    }
    class Process extends Node {
        String name;
        List<Practice> practice;
        Rating rating;
        public Process(String name2) {
            practice=new LinkedList<Practice>();
            name=name2;
        }

        @Override
        public Object getChild(int index) {
           return  practice.get(index);

        }

        @Override
        public int getChildCount() {
            return practice.size();            
        }

        @Override
        int getIndexOfChild(Object child) {
            return practice.indexOf(child);
        }
        
        Practice addPractice(String id, String text)
        {
            Practice p=new Practice(id, text);
            practice.add(p);
            return p;
        }
        
        public String toString()
        {
            return name;
        }
       
        
    }
    
    
    
    class Level extends Node{
        String name;
        List<Process> processes;
        List<String> applicableTo;
        public Level(String level) {
            name=level;
            processes=new LinkedList<Process>();
            applicableTo=new LinkedList<String>();
        }
        @Override
        public Object getChild(int index) {
            // TODO Auto-generated method stub
            return processes.get(index);
        }
        @Override
        public int getChildCount() {
            return processes.size();         
        }
        @Override
        int getIndexOfChild(Object child) {
            return processes.indexOf(child);
        }
        
        Process addProcess(String name)
        {
            Process p=new Process(name);
            processes.add(p);
            return p;
        }
        public String toString()
        {
            return name;
        }
       
    }
    
    class Root  extends Node {
        List<Level> level;
        
        Root()
        {
            level=new LinkedList<Level>();
        }
        
        @Override
        public Object getChild(int index) {
            // TODO Auto-generated method stub
            return level.get(index);
        }
        @Override
        public int getChildCount() {
            return level.size();         
        }
        @Override
        int getIndexOfChild(Object child) {
            return level.indexOf(child);
        }
        
        Level addLevel(String l)
        {
            Level newLevel=new Level(l);
            level.add(newLevel);
            return newLevel;
        }
    }
    
    Root mRoot;

    @Override
    public Object getRoot() {
 
        return mRoot;
    }

    @Override
    public Object getChild(Object parent, int index) {
        Node n=(Node)parent;
        return n.getChild(index);     
    }

    @Override
    public int getChildCount(Object parent) {
        Node n=(Node)parent;
        return n.getChildCount();
    }

    @Override
    public boolean isLeaf(Object node) {
        Node n=(Node)node;
        return n.isLeaf();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        Node n=(Node)parent;
        return n.getIndexOfChild(child); 
    }

    List<TreeModelListener> mListener;
    @Override
    public void addTreeModelListener(TreeModelListener l) {
        mListener.add(l);

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
      mListener.remove(l);

    }
    public QuestionTreeModel()
    {
        mListener=new LinkedList<TreeModelListener>();
        mRoot=new Root();
     
    }
    
    public Root getRoot2()
    {
        return mRoot; 
    }

}
