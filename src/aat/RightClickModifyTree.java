package aat;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

public class RightClickModifyTree extends JTree {
    RightClickModifyTree()
    {
        super();
        
        addMouseListener(new MouseListener() {
            
            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
               if((e.getModifiers() & MouseEvent.BUTTON3)==MouseEvent.BUTTON3)
               {
                   
                    TreePath path = RightClickModifyTree.this.getPathForLocation(e.getX(), e.getY());
                    if (path != null) {
                        System.out.println(path.getLastPathComponent().toString());
                    }
                }
                
            }
        });
    }
}
