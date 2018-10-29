import java.util.*;

/**
 *
 * @author
 * a node in a binary search tree
 */
class BTNode{
    Node left, right;
    String term;
    ArrayList<Integer> docLists;

    /**
     * Create a tree node using a term and a document list
     * @param term the term in the node
     * @param docList the ids of the documents that contain the term
     */
    public Node(String term)
    {
        this.term = term;
        this.docLists = docList;
    }

}

/**
 *
 * @author qyuvks
 * Binary search tree structure to store the term dictionary
 */
class BinaryTree {

    /**
     * insert a node to a subtree
     * @param node root node of a subtree
     * @param iNode the node to be inserted into the subtree
     */

    Node root;

    public BinaryTree(String[] keys) {
        Arrays.sort(keys);
        int start = 0;
        int end = keys.length - 1;
        int mid = (start + end) / 2;
        Node r = new Node(keys[mid]);
        root = r;
        add(r, keys, start, mid - 1);
        add(r, keys, mid + 1, end);
    }
    public void add(Node n, String[] A, int start, int end)
    {
        //TO BE COMPLETED
        if (start <= end) {
            int mid = (start + end) / 2;
            if (A[mid].compareTo(n.value)<0) {
                n.left = new Node(A[mid]);
                add(n.left, A, start, mid - 1);
                add(n.left, A, mid + 1, end);
            } else if(A[mid].compareTo(n.value)>0){
                n.right = new Node(A[mid]);
                add(n.right, A, start, mid - 1);
                add(n.right, A, mid + 1, end);
            }
            else if(A[mid].compareTo(n.value)==0){
                System.out.println("its a match");
            }
        }
    }

    /**
     * Search a term in a subtree
     * @param n root node of a subtree
     * @param key a query term
     * @return tree nodes with term that match the query term or null if no match
     */
    public BTNode search(BTNode n, String key)
    {
        //TO BE COMPLETED
        Node ne= new Node("no");
        if (n==null) return ne;
        if (n.value.compareTo(key)==0) return n;
        else if (n.value.compareTo(key)>0) return search(n.left, key);
        else return search(n.right, key);
    }

    /**
     * Do a wildcard search in a subtree
     * @param n the root node of a subtree
     * @param key a wild card term, e.g., ho (terms like home will be returned)
     * @return tree nodes that match the wild card
     */
    public ArrayList<BTNode> wildCardSearch(BTNode n, String key)
    {
        //TO BE COMPLETED
    }

    /**
     * Print the inverted index based on the increasing order of the terms in a subtree
     * @param node the root node of the subtree
     */
    public void printInOrder(BTNode node)
    {

        //TO BE COMPLETED
    }
}

