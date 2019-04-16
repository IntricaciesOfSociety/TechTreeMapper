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

    public String toString() {
        return "" + id + " " + name + " " + dependencies.toString().replaceAll("\\[", "").replaceAll("]","");
    }
}
