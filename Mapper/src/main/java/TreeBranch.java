import java.io.*;
import java.util.ArrayList;

class TreeBranch extends File {

    static ArrayList<BranchElement> fullTree = new ArrayList<>();

    private static int elementCounter = 0;

    private TreeBranch(String pathname) {
        super(pathname);
        populateTree();
    }

    static int calculateId() {
        return 0;
    }

    static int calculateId(String[] dependencies) {
        return 0;
    }

    void recalculateTree() {
        System.out.println("Rewriting tree ... ... ...");

        try (PrintWriter writer = new PrintWriter(this)) {
            for (BranchElement element : fullTree) {
                element.setId(elementCounter);

                element.removeStrayDependencies();
                System.out.print(element);

                writer.write(element.toString());
                elementCounter++;
            }
            elementCounter = 0;

            System.out.println("Writing tree done!");
        } catch (FileNotFoundException e) {
            System.out.println("Cannot write to file");
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

    void addElement(BranchElement element) {
        if (element != null) {
            fullTree.add(element.getId(), element);
        }
        recalculateTree();
    }

    void removeElement(BranchElement element) {
        if (element != null) {
            fullTree.remove(element);
            System.out.println("Successfully removed: " + element);
        }
        recalculateTree();
    }

    BranchElement elementLookup(String elementName) {
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

    void getDependencies(BranchElement element) {

    }

    void changeDependencies(BranchElement branchElement) {

    }
}
