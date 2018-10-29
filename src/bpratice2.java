

import java.util.ArrayList;
import java.util.Arrays;

class Node {
    Node left;
    Node right;
    String value;
    ArrayList<Integer> docList;

    public Node(String value) {
        this.value = value;
        docList= new ArrayList<Integer>();
    }

    public void addLocation(int did){
        docList.add(did);
    }
}

class bpractice2 {
    Node root;

    public bpractice2(String[] keys) {
        Arrays.sort(keys);
        int start = 0;
        int end = keys.length - 1;
        int mid = (start + end) / 2;
        Node r = new Node(keys[mid]);
        root = r;
        add(r, keys, start, mid - 1);
        add(r, keys, mid + 1, end);
    }

    public void add(Node n, String[] A, int start, int end) {
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

    public void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.println(" Traversed " + node.value);
            printInOrder(node.right);
        }
    }

    public Node search(Node n, String key) {
        Node ne= new Node("no");
        if (n==null) return ne;
        if (n.value.compareTo(key)==0) return n;
        else if (n.value.compareTo(key)>0) return search(n.left, key);
        else return search(n.right, key);
    }


    public static void main(String[] args) {
        String[] docs = {"new home sales top forcasts",
                "home sales rise in july",
                "increase in home sales in july",
                "july new home sales rise"};
        ArrayList<String> t1= new ArrayList<String>();
        for(int i=0;i<docs.length;i++){
            String[]tokens= docs[i].split(" ");
            for(String token:tokens){
                t1.add(token);
            }
        }
        String[] myDocs= new String[t1.size()];
        for(int i=0;i<t1.size();i++) myDocs[i]=t1.get(i);

        bpractice2 bt = new bpractice2(myDocs);
        System.out.println("the search is:");
        bt.printInOrder(bt.root);

        for(int i=0;i<docs.length;i++){
            String[] tokens=docs[i].split(" ");
            for(String token:tokens){
                Node n= bt.search(bt.root, token);
                if(n.value!="no"){
                    if(!n.docList.contains(i)) n.addLocation(i);
                }
            }
        }

        Node n= bt.search(bt.root, "new");
        if(n.value=="no")System.out.println("wrong");
        System.out.println(n.docList);
    }
}
