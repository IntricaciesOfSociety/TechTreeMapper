import java.io.*;
import java.util.ArrayList;

class TreeBranch extends File{

    private static ArrayList<BranchElement> fullTree = new ArrayList<>();

    private TreeBranch(String pathname) {
        super(pathname);
        populateTree();
    }

    static int calculateId() {
        return 1;
    }

    static int calculateId(String[] dependencies) {
        return 1;
    }

    private static void recalculateTree() {
        System.out.println("Rewriting tree ... ... ...");

        for (BranchElement element : fullTree) {
            System.out.println(element);
        }
    }

    private void populateTree() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(this));

            String line = br.readLine();
            while (line != null) {
                String[] values = line.split("[,]");

                BranchElement newElement = new BranchElement(Integer.parseInt(values[0]), values[1]);
                if (values.length > 2) {
                    for (int i = 2; i < values.length; i++) {
                        newElement.addDependency(values[i]);
                    }
                }

                fullTree.add(newElement);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("FILE NOT FOUND (or parse error)????");
        }
    }

    static TreeBranch openTree(BranchFiles fileToOpen) {
        switch (fileToOpen) {
            case CHEMISTRY:
                return new TreeBranch("Mapper/src/main/resources/Chemistry.csv");
            case CULTURAL:
                return new TreeBranch("Mapper/src/main/resources/Cultural.csv");
            case MATERIAL:
                return new TreeBranch("Mapper/src/main/resources/Material.csv");
            case MATHMATICS:
                return new TreeBranch("Mapper/src/main/resources/Mathematics.csv");
            case PHYSICS:
                return new TreeBranch("Mapper/src/main/resources/Physics.csv");
            case SOCIETAL:
                return new TreeBranch("Mapper/src/main/resources/Societal.csv");
        }
        System.out.println("FILE NOT FOUND " + fileToOpen.name());
        return null;
    }

    static void addElement(BranchElement element) {
        if (element != null) {
            fullTree.add(element.getId(), element);
        }
        recalculateTree();
    }

    static void removeElement(BranchElement element) {
        if (element != null) {
            fullTree.remove(element);
            System.out.println("Successfully removed: " + element);
        }
        recalculateTree();
    }

    static BranchElement elementLookup(String elementName) {
        try {
            int id = Integer.parseInt(elementName);

            return fullTree.get(id);
        } catch (NumberFormatException ignored) {

            for (BranchElement branchElement : fullTree) {
                if (branchElement.getName().equals(elementName))
                    return branchElement;
            }
            System.out.println("Cannot find element: " + elementName);
            return null;
        }
    }

    static void getDependencies(BranchElement element) {

    }
}
