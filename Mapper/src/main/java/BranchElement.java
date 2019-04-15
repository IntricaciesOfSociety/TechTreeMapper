import java.util.ArrayList;
import java.util.Arrays;

public class BranchElement {

    private String id;

    private String name;

    private ArrayList<String> dependencies = new ArrayList<>();

    BranchElement(String id, String name, String ... dependentIds) {
        this.id = id;
        this.name = name;

        dependencies.addAll(Arrays.asList(dependentIds));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return "";
    }

    public ArrayList<String> getDependencies() {
        return dependencies;
    }
}
