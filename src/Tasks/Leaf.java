package Tasks;

import Global.Interval;
import States.*;
import States.State.InvalidTransitionException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import javax.swing.tree.TreeNode;

/**
 * Class Leaf, represents the leaf class from the Composite Pattern. 
 * Can be subdivided in Task and Action.
 * 
 * @author ASSO1011D
 *
 */
public class Leaf implements Component {

    public String description;
    public String name;
    public State state;
	Component parent;
        int index;

    public Leaf() {
        state = new ToDo();
        this.name = "";
        this.description = "";
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state.getName();
    }

    @Override
    public long getElapsedTime() {
        return 0;
    }

    public void close() throws InvalidTransitionException {
    }

    public String[] getChildNames(String space) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TreeNode getChildAt(int childIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getChildCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Component getParent() {
	        return parent;
	}

    public int getIndex(TreeNode node) {
        return index;
    }

    public boolean getAllowsChildren() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isLeaf() {
        return true;
    }

    public Enumeration children() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        if (state.getName().equalsIgnoreCase("closed")) {
            return "*" + name;
        } else {
            return name;
        }
    }

    public String getDescription() {
        return description;
    }

    public String[] getJobNames(String space) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateIntervals(HashMap<Component, List<Interval>> intervalsPerTask) {
    }

    public void deleteNode(Component comp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	public void setParent(Component parent) {
	        this.parent = parent;
	}
        
    @Override
    public boolean equals(Object obj){
        return this.name == ((Component)obj).getName();
    }
    
    @Override
    public int hashCode(){
        return name.hashCode();
    }

    public void setIndex(int i) {
        this.index = i;
    }
}
