package tree;
/**
 * Class to implement Tree methods on user input
 *
 * @author Robert Bacigalupo,112145826,Rec.01
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class TreeDriver {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        Tree tree = new Tree();
        boolean running = true;

        while (running) {
            printMenu();
            try {
                String input = stdin.nextLine();
                /**
                 * Switch case for user input
                 * after printing menu, appropriate method is implemented according to user input
                 */
                switch (input.toUpperCase()) {
                    case "L":
                        System.out.println("Enter file");
                        String file = stdin.nextLine();
                        File treeFile = new File(file);
                        tree = Tree.buildTree(treeFile);
                        break;
                    case "H":
                        tree.beginSession();
                        break;
                    case "T":
                        tree.getRoot().preOrder();
                        break;
                    case "Q":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid menu option");
                        break;
                }

            } catch (FileNotFoundException ex) {
                System.out.println("File was not found");
            } catch (IOException ex) {
                System.out.println("IO exception occurred");

            } catch (NullPointerException ex) {


                System.out.println("Null pointer exception occurred");
            } catch (Exception ex) {
                System.out.println("Unexpected error occurred");
            }


        }
        System.out.println("program ended");
    }

    public static void printMenu() {
        System.out.println("L(Load input file and build tree)\n" + "H(Start help session");
        System.out.println("T(Traverse the tree in pre-order\n" + "Q(Quit)");
    }

}
