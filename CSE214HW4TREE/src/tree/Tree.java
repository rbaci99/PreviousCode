package tree;
/**
 * Class to represent a tree and its classes
 *
 * @author Robert Bacigalupo,112145826,Rec.01
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Tree {

    TreeNode root;

    public Tree() {

    }

    /**
     * Class to add new Node to a tree
     * @param label String to represent label of new node
     * @param prompt String to represent prompt of new node to be added
     * @param message String represent message of new node to be added
     * @param parentLabel String representing label of parent to add new node to
     * @return returns true if new node was added successfully
     */
    public boolean addNode(String label, String prompt, String message, String parentLabel) {
        TreeNode ptr = root;
        TreeNode addend = new TreeNode(label, prompt, message);
        if (root != null) {
            if (ptr.getReference(parentLabel) != null) {
                ptr = ptr.getReference(parentLabel);
                addend.setParent(ptr);

                if (ptr.getLeft() == null) {
                    ptr.setLeft(addend);
                    return true;
                } else if (ptr.getMiddle() == null) {
                    ptr.setMiddle(addend);
                    return true;
                } else if (ptr.getRight() == null) {
                    ptr.setRight(addend);
                    return true;
                }
            }
        } else {
            root = addend;
            return true;
        }

        return false;

    }

    /**
     * Method to begin question session, prints root message as well as its children's prompts
     * then depending on user input outputs the correct node messages and prompts
     * Implements TreeNode method runQuestion
     * Input of  0 will end session and Input of 4 will bring user back to previous node
     */
    public void beginSession() {
        TreeNode ptr = root;
        Scanner stdin = new Scanner(System.in);
        boolean running = true;
        ptr.runQuestion();

        while (running) {

            int input = stdin.nextInt();
            try {
                if (input == 1) {
                    if (ptr.getLeft() != null) {
                        ptr = ptr.getLeft();
                        ptr.runQuestion();
                    } else {
                        throw new NullPointerException();
                    }
                }
                if (input == 2) {
                    if (ptr.getMiddle() != null) {
                        ptr = ptr.getMiddle();
                        ptr.runQuestion();
                    } else
                        throw new NullPointerException();
                }
                if (input == 3) {
                    if (ptr.getRight() != null) {
                        ptr = ptr.getRight();
                        ptr.runQuestion();
                    } else
                        throw new NullPointerException();
                }
                if (input == 0) {
                    running = false;
                    break;

                }
                if (input == 4) {
                    ptr = ptr.getParent();
                    ptr.runQuestion();
                }


            } catch (NullPointerException ex) {
                System.out.println("Invalid input,no node present try again");
                ptr.runQuestion();

            } catch (InputMismatchException ex) {
                System.out.println("Non-integer input was entered try again");
                ptr.runQuestion();
            } catch (Exception e) {
                System.out.println("Unexpected error occurred, try again");
                ptr.runQuestion();
            }
        }


    }

    /**
     * method builds tree from text file,
     * reading text file in correct format into String array
     * then into nodes of a tree building the tree
     * @param treeFile text file to build tree from
     * @return returns the tree built from the text file
     * @throws FileNotFoundException if file is not found, exception thrown, to be handled in later implementation
     * @throws IOException IOexception from fileScanner to be handled at later implementation
     */
    public static Tree buildTree(File treeFile) throws FileNotFoundException, IOException {
        BufferedReader fileScanner = new BufferedReader(new FileReader(treeFile));
        Tree tree = new Tree();


        int length = 0;
        String ln = "";
        while ((ln = fileScanner.readLine()) != null) {

            length++;


        }
        String[] strings = new String[length];

        ln = new String(Files.readAllBytes(Paths.get(treeFile.getPath())));

        strings = ln.split("\\r?\\n");


        tree.addNode(strings[0], strings[1], strings[2], "");
        for (int i = 3; i < strings.length; i++) {
            String[] arr = strings[i].split(" ");

            if (arr.length == 2 && isNumeric(arr[1])) { //checks to see if string after space is number
                System.out.println(arr[arr.length-2]);
                System.out.println("children:"+arr[1]);
                int children = Integer.parseInt(arr[1]);
                if (children >0) {
                    tree.addNode(strings[i], strings[i + 1], strings[i + 2], arr[arr.length - 2]);
                    System.out.println("Label"+strings[i]);
                } if (children >= 2) {
                    tree.addNode(strings[i + 3], strings[i + 4], strings[i + 5], arr[arr.length - 2]);
                    System.out.println("Label"+strings[i+3]);
                } if (children == 3) {

                    tree.addNode(strings[i + 6], strings[i + 7], strings[i + 8], arr[arr.length - 2]);
                    System.out.println("Label"+strings[i+6]);
                } else {

                }


            } else {

            }
        }


        fileScanner.close();
        return tree;
    }

    /**
     *
     * @return returns reference to root
     */
    public TreeNode getRoot() {
        return this.root;

    }

    /**
     * Sets root reference of a tree
     * @param root TreeNode to set root reference to
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }

    /**
     * method to check if string is an integer
     * @param str string to check if numeric
     * @return returns true if string is able to be parsed to integer
     */
    public static boolean isNumeric(String str) {
        try {
            int num = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        } catch (NullPointerException ex) {
            return false;
        }
    }


}

