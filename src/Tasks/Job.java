package Tasks;

import Global.Interval;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.tree.TreeNode;

/**
 * Class Job, represents the composite class from the Composite Pattern.
 * Has a list of components called children.
 * 
 * @author ASSO1011D
 *
 */
public class Job implements Component {

    public String name;
    public String description;
    Component parent;
    int index;
	
    //Collection of child graphics.
    private List<Component> children = new ArrayList<Component>();

    public List<Component> getChildren() {
        return children;
    }

    public Job getActiveChildren() {
        Job newJob = new Job(name, description);
        for (Component c : children) {
            if (!c.getState().equalsIgnoreCase("closed")) {
                newJob.addActive(c);
            }
        }
        return newJob;
    }
    //Prints the graphic.

    public long getElapsedTime() {
        long time = 0;
        for (Component child : children) {
            time += child.getElapsedTime();
        }
        return time;
    }

    //Adds the graphic to the composition.
    public void addChild(Component component) {
        children.add(component);
	component.setParent(this);
        component.setIndex(children.size() + 1);
    }

    //Removes the graphic from the composition.
    public void removeChild(Component component) {
        children.remove(component);
    }

    public String[] getChildNames(String space) {
        System.out.println("Entra " + this.name + " " + this.children.size());
        ArrayList<String> names = new ArrayList<String>();
        if (this.name == null) {
            names.add("");
        } else {
            names.add(space + this.name);
        }

        for (Component child : children) {
            if (child.getClass() == Job.class) {
                System.out.println(child.getName());
                String[] temp = child.getChildNames(space + " ");
                for (int j = 0; j < temp.length; j++) {
                    names.add(temp[j]);
                }
            } else {
                if (child.getClass() == Task.class || child.getClass() == Action.class) {
                    System.out.println(child.getName());
                    names.add(space + " " + child.getName());
                }
            }
        }

        String[] strArray = new String[names.size()];
        names.toArray(strArray);
        return strArray;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public TreeNode getChildAt(int childIndex) {
        return children.get(childIndex);
    }

    public int getChildCount() {
        return children.size();
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
        return false;
    }

    public Enumeration children() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        if (getName().equalsIgnoreCase("closed")) {
            return "*" + name;
        } else {
            return name;
        }
    }

    public String[] getJobNames(String space) {

        ArrayList<String> names = new ArrayList<String>();
        if (this.name == null) {
            names.add("");
        } else {
            names.add(space + this.name);
        }

        for (Component child : children) {
            if (child.getClass() == Job.class) {

                String[] temp = child.getJobNames(space + " ");
                for (int j = 0; j < temp.length; j++) {
                    names.add(temp[j]);
                }
            }
        }

        String[] strArray = new String[names.size()];
        names.toArray(strArray);
        return strArray;
    }

    public String getState() {
        String stt = "To Do";
        if(children.isEmpty()){
            return stt;
        }
        for (Component child : children) {
            if (!child.getState().equals("To Do")) {
                stt = "Running";
            }
        }

        for (Component child : children) {
            if (!child.getState().equals("Closed")) {
                return stt;
            }
        }
        
        return "Closed";
    }

    public Job(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    @Override
    public void updateIntervals(HashMap<Component, List<Interval>> intervalsPerTask) {
        intervalsPerTask.put(this,  new ArrayList<Interval>());
        Iterator<Component> childIterator = children.iterator();
        while (childIterator.hasNext()) {
            childIterator.next().updateIntervals(intervalsPerTask);
        }
    }

    public void add(Component folha, String belongs) {
        if (this.name.equals(belongs)) {
            this.addChild(folha);
        } else {
            for (Component child : children) {
                if (child.getClass() == Job.class) {
                    if (child.getName().equals(belongs)) {
                        ((Tasks.Job) child).addChild(folha);
                    } else {
                        ((Tasks.Job) child).add(folha, belongs);
                    }

                }
            }
        }
    }

    public void addActive(Component folha) {
        if (folha.getClass() != Job.class) {
            if (!folha.getState().equalsIgnoreCase("closed")) {
                this.addChild(folha);
            }
        } else {
            Job newChild = new Job(folha.getName(),folha.getDescription());
            for (Component child : ((Job) folha).getChildren()) {
                if ((child.getClass() == Job.class && !child.getState().equalsIgnoreCase("closed")) ||
                        (!child.getState().equalsIgnoreCase("closed"))){
                    newChild.addActive(child);
                }
            }
            this.addChild(newChild);
        }
    }

    public void deleteNode(Component comp) {
        for (Component child : children) {
            if (child.getName().equals(comp.getName())) {
                children.remove(child);
                break;
            } else {
                if (child.getClass() == Tasks.Job.class) {
                    child.deleteNode(comp);
                }
            }
        }
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
