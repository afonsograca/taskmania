package Tasks;

import Global.Interval;
import java.util.HashMap;
import java.util.List;
import javax.swing.tree.TreeNode;

/**
 * Interface Component, component interface for the Composite Pattern.
 * 
 * @author ASSO1011D
 *
 */

public interface Component extends TreeNode{
	
	//Prints the graphic.
    public long getElapsedTime();

    public String getName();
    public String getDescription();
    public String[] getChildNames(String space);
    public String[] getJobNames(String space);
    public String getState();
    public void updateIntervals(HashMap<Component, List<Interval>> intervalsPerTask);
    public void deleteNode(Component comp);
    public Component getParent();
    public void setParent(Component parent);
    @Override
    public boolean equals(Object obj);
    @Override
    public int hashCode();

    public void setIndex(int i);

}
