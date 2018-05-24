import java.io.*;
import java.lang.String;


public class BTree {

    private static BufferedReader br;
    private BTreeNode root;
    private int t;

    public BTree(String tVal) {
        this.root = new BTreeNode(t);
        this.root.setLeaf(true);
        this.root.setN(0);
    }

    /**
     * This method searches the value `toFind` inside the Btree
     *
     * @param node
     * @param toFind
     * @return
     */
    public Pair<BTreeNode, Integer> search(BTreeNode node, String toFind) {
        int i = 0;

        while (i < node.getN() && toFind.compareTo(node.getKeys().get(i)) > 0) {
            i++;
        }
        if (i <= node.getN() && toFind.compareTo(node.getKeys().get(i)) == 0) {
            return new Pair(node, i);
        }
        if (node.isLeaf()) {
            return null;
        }
        return search(node.getChildren().get(i), toFind);
    }

    public BTreeNode insert(String toInsert) {
        if (root.getN() == (2 * t) - 1) {
            BTreeNode s = new BTreeNode(t);
            this.root = s;
            s.setLeaf(false);
            s.addChild(root);
            splitChild(s, 1, root);
            root = s;
        }
        insertNonFull(root, toInsert);
        return this.root;
    }

    private boolean insertNonFull(BTreeNode node, String toInsert) { //TODO:: check if anything initialized .... and minimum 15 lines of code.
        int i = 0;
        if (node.isLeaf()) {
            while (i < node.getN() && toInsert.compareTo(node.getKeys().get(i)) > 0) {
                i++;
            }
            LinkedList<String> keys = node.getKeys();
            keys.add(i, toInsert);
            node.insertToKeys(toInsert, i + 1);
            return true;
        } else {

            while (i < node.getN() && toInsert.compareTo(node.getKeys().get(i)) > 0) {
                i++;
            }
            if (node.getChildren().get(i).getN() == (2 * t - 1)) {
                splitChild(node, i, node.getChildren().get(i));
                if (toInsert.compareTo(node.getKeys().get(i)) > 0) {
                    i++;
                }
            }
            return insertNonFull(node.getChildren().get(i), toInsert);
        }
    }


    private void splitChild(BTreeNode father, int numOfChildren, BTreeNode richSon) {
        BTreeNode son1 = new BTreeNode(t);
        BTreeNode son2 = new BTreeNode(t);
        int i = 0;
        for (int j = 0; j < t - 1 && j < richSon.getN(); j++) {
            son1.insertToKeys(richSon.getKeys().get(j));
        }


        for (int j = t; j < richSon.getN() && j < (2 * t) - 1; j++) {
            son2.insertToKeys(richSon.getKeys().get(j));
        }
        if (!richSon.isLeaf()) {
            for (int j = 0; j < t; j++) {
                son1.insertChild(richSon.getChildren().get(j));
                son2.insertChild(richSon.getChildren().get(j + t));
            }
        }
        father.insertToKeys(richSon.getKeys().get(t - 1));

    }


    public BTree createFullTree(String input){
        System.out.println(input);

        return this;
    }

    public static boolean readFile(String filePath) {
        File file = new File(filePath);
        try {
            br = new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException e) {
            return false;

        }
        return true;


    }

    public boolean readFile(){
        String st;
        try {
            while ((st = br.readLine()) != null) {
                System.out.println(st);
                this.insert(st);
            }
        } catch (IOException e) {;
            return false;
        }
        return true;
    }

}
