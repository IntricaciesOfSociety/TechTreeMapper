import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);

    private static TreeBranch openBranch;

    public static void main(String[] args) {
        startInput();
    }

    private static void startInput() {

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
            default:
                System.out.println("INVALID INPUT");
                treeSelect();
                break;
        }
    }

    private static void actionSelect() {
        System.out.println("Actions: (AddElement(0), RemoveElement(1), ChangeElementDependencies(2), ElementLookup(3), RecalculateTree(4)");
        int selection = input.nextInt();
        input.nextLine();

        switch (selection) {
            case 0:
                openBranch.addElement(elementCreationInput()); break;
            case 1:
                openBranch.removeElement(elementSelectionInput()); break;
            case 2:
                openBranch.changeDependencies(elementSelectionInput()); break;
            case 3:
                elementSelectionInput(); break;
            case 4:
                openBranch.recalculateTree(); actionSelect(); break;
            default:
                System.out.println("INVALID INPUT");
                actionSelect();
                break;
        }
    }

    private static BranchElement elementCreationInput() {
        System.out.println("Input name:");
        String name = input.nextLine();

        System.out.println("Input number of dependencies:");
        int numOfDepend = input.nextInt();
        input.nextLine();

        if (numOfDepend > 0) {
            String[] dependencies = new String[numOfDepend];

            for (int i = 1; i <  numOfDepend + 1; i++) {
                System.out.println("Select dependency " + i);
                dependencies[i - 1] = elementSelectionInput().getName();
            }

            return new BranchElement(TreeBranch.calculateId(dependencies), name, dependencies);
        }
        else
            return new BranchElement(TreeBranch.calculateId(), name);
    }

    private static BranchElement elementSelectionInput() {
        System.out.println("Which element?: (ElementID(0), ElementName(1))");

        switch (input.nextInt()) {
            case 0:
                System.out.println("Input Element ID: "); break;
            case 1:
                System.out.println("Input Element Name: "); break;
            default:
                System.out.println("INVALID INPUT");
                return elementSelectionInput();
        }

        input.nextLine();
        String answer = input.nextLine();

        BranchElement foundBranch = openBranch.elementLookup(answer);
        System.out.println("Found branch: " + foundBranch);
        return foundBranch;
    }
}
