import java.io.File;

class TreeBranch extends File{

    private TreeBranch(String pathname) {
        super(pathname);
    }

    static TreeBranch openTree(BranchFiles fileToOpen) {
        switch (fileToOpen) {
            case CHEMISTRY:
                return new TreeBranch("src/main/resources/Chemistry.csv");
            case CULTURAL:
                return new TreeBranch("src/main/resources/Cultural.csv");
            case MATERIAL:
                return new TreeBranch("src/main/resources/Material.csv");
            case MATHMATICS:
                return new TreeBranch("src/main/resources/Mathematics.csv");
            case PHYSICS:
                return new TreeBranch("src/main/resources/Physics.csv");
            case SOCIETAL:
                return new TreeBranch("src/main/resources/Societal.csv");
        }
        System.out.println("FILE NOT FOUND " + fileToOpen.name());
        return null;
    }
}
