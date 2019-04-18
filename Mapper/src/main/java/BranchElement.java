import java.util.ArrayList;
import java.util.Arrays;

public class BranchElement {

    private int id;

    private String name;

    private ArrayList<String> dependencies = new ArrayList<>();

    BranchElement(int id, String name, String ... dependencies) {
        this.id = id;
        this.name = name;
        this.dependencies.addAll(Arrays.asList(dependencies));
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    public ArrayList<String> getDependencies() {
        return dependencies;
    }

    void addDependency(String newDependency) {
        dependencies.add(newDependency);
    }

    void setId(int newId) {
        id = newId;
    }

    public String toString() {
        if (!dependencies.isEmpty())
            return "" + id + "," + name + "," + dependencies.toString().replaceAll("\\[", "").replaceAll("]","").replaceAll(" ","") + "\n";
        else
            return "" + id + "," + name + "\n";
    }

    void removeStrayDependencies() {
        ArrayList<String> activeDependencies = new ArrayList<>();

        for (int i = 0; i < TreeBranch.fullTree.size(); i++) {
            if (this.dependencies.contains(TreeBranch.fullTree.get(i).getName()))
                activeDependencies.add(TreeBranch.fullTree.get(i).getName());
        }

        dependencies = activeDependencies;
    }
}
