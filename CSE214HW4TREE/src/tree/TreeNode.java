package tree;

/**
 * Class to represent node of tree
 *
 * @author Robert Bacigalupo,112145826,Rec.01
 */
public class TreeNode {
    TreeNode left;// reference to left child node
    TreeNode right;// reference to right child node
    TreeNode middle;//reference to middle child node
    TreeNode parent;//reference to parent node
    private String label;//label of node
    private String message;// message to be printed to the screen w
    private String prompt;// prompt representing the node as an option when printed


    public TreeNode() {

    }

    public TreeNode(String label, String prompt, String message) {
        this.label = label;
        this.prompt = prompt;
        this.message = message;

    }

    /**
     * method to return TreeNode with corresponding label
     *
     * @param label String to search for
     * @return returns TreeNode with correct label or null
     */
    public TreeNode getReference(String label) {
        TreeNode ptr = this;
        TreeNode ans = new TreeNode();
        TreeNode ans2 = new TreeNode();
        if (this.getLabel().equalsIgnoreCase(label)) {
            return ptr;

        } else {
            if (ptr.getLeft() != null)
                ans = ptr.getLeft().getReference(label);
            if (ptr.getMiddle() != null && ans == null)
                ans2 = ptr.getMiddle().getReference(label);
            if (ptr.getRight() != null && ans == null && ans2 == null)
                ptr.getRight().getReference(label);
        }
        return null;


    }

    @Override
    public String toString() {
        return String.format("Label: " + this.label + "\n" + " Prompt: " + this.prompt + "\n Message: " + this.message);


    }

    /**
     * prints tree in pre-Order fashion
     */
    public void preOrder() {
        if (this != null) {
            System.out.println(this);
        }
        if (this.getLeft() != null)
            this.getLeft().preOrder();
        if (this.getMiddle() != null)
            this.getMiddle().preOrder();
        if (this.getRight() != null)
            this.getRight().preOrder();

    }

    /**
     * method to be used in Tree Method beginSession
     * prints TreeNode message as well as its children prompts
     */
    public void runQuestion() {
        System.out.println(this.message + "\n");
        if (this.getLeft() != null) {
            System.out.println("1.)" + this.getLeft().getPrompt());
        }
        if (this.getMiddle() != null) {
            System.out.println("2.)" + this.getMiddle().getPrompt());
        }
        if (this.getRight() != null) {
            System.out.println("3.)" + this.getRight().getPrompt());
        }
        if (this.getParent() != null) {
            System.out.println("4.)go back");
        }
        System.out.println("0.) Exit");

    }

    public TreeNode getLeft() {
        return this.left;
    }

    public TreeNode getParent() {
        return this.parent;
    }

    public TreeNode getRight() {
        return this.right;
    }

    public TreeNode getMiddle() {
        return this.middle;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void setMiddle(TreeNode middle) {
        this.middle = middle;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getLabel() {
        return this.label;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPrompt() {
        return this.prompt;
    }

    /**
     * method to check if TreeNode is leaf (doesn't have any children)
     *
     * @return returns True if all children references are null
     */
    public boolean isLeaf() {
        return (this.left == null && this.middle == null && this.right == null);
    }

}
