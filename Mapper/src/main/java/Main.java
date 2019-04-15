import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);

    private static TreeBranch openBranch;

    public static void main(String[] args) {
        startInput();
    }

    private static void startInput() {

        boolean running = true;

        while (true) {
            treeSelect();
            actionSelect();
        }

    }

    private static void treeSelect() {
        System.out.println("Input tree to modify: (Chemistry(0), Cultural(1), Material(2), Mathmatics(3), Physics(4), Societal(5))");
        switch (input.nextInt()) {
            case 0:
                openBranch = TreeBranch.openTree(BranchFiles.CHEMISTRY); break;
            case 1:
                openBranch = TreeBranch.openTree(BranchFiles.CULTURAL); break;
            case 2:
                openBranch = TreeBranch.openTree(BranchFiles.MATERIAL); break;
            case 3:
                openBranch = TreeBranch.openTree(BranchFiles.PHYSICS); break;
            case 4:
                openBranch = TreeBranch.openTree(BranchFiles.SOCIETAL); break;
        }
    }

    private static void actionSelect() {
        System.out.println("Actions: (AddElement(0), RemoveElement(1), ChangeElementDependencies(2), ElementLookup(3)");
        switch (input.nextInt()) {
            case 0:
                TreeBranch.addElement(elementInput()); break;
            case 1:
                TreeBranch.removeElement(elementInput()); break;
            case 2:
                TreeBranch.getDependencies(elementInput()); break;
            case 3:
                elementInput(); break;

        }
    }

    private static BranchElement elementInput() {
        System.out.println("Which element?: (FindByElementID(0), FindByElementName(1))");

        switch (input.nextInt()) {
            case 0:
                System.out.println("Input Element ID: "); break;
            case 1:
                System.out.println("Input Element Name: "); break;
        }

        input.nextLine();
        String answer = input.nextLine();
        if (answer.length() != 5) {
            System.out.println("INVALID INPUT");
            return elementInput();
        }

        return TreeBranch.elementLookup(answer);

    }
}
