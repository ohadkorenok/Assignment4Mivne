import java.io.*;
public class BTree {

    private static BufferedReader br;
    private BTreeNode root;
    private int t;

    public BTree(String tVal) {
        this.t = Integer.parseInt(tVal);
        this.root = new BTreeNode(t);
        this.root.setLeaf(true);
        this.root.setN(0);
    }

    /**
     * This method searches the value `toFind` inside the Btree
     *
     * @param node BtreeNode Object
     * @param toFind String to find inside the keys of the tree
     * @return a Pair object, with BtreeNode that contains the element and integer represents the index inside the node's
     *  keys.
     */
    public Pair<BTreeNode, Integer> search(BTreeNode node, String toFind) {
        int i = 0;

        while (i < node.getN() && toFind.compareTo(node.getKeys().get(i)) > 0) {
            i++;
        }
        if (i < node.getN()) {
            if (toFind.compareTo(node.getKeys().get(i)) == 0) {
                return new Pair(node, i);
            }
        }
        if (node.isLeaf()) {
            return null;
        }
        return search(node.getChildren().get(i), toFind); }

    /**
     * Search function on the Btree .
     * @param toFind String to find inside the Btree's keys.
     * @return Pair Object.
     */
    public Pair<BTreeNode, Integer> search(String toFind) {
        return search(this.root, toFind);
    }

    /**
     * This method inserts a new value to the tree.
     * @param toInsert String
     * @return the root (BtreeNode object)
     */
    public BTreeNode insert(String toInsert) {
        BTreeNode r = root;
        if (root.getN() == (2 * t) - 1) {
            BTreeNode s = new BTreeNode(t);
            this.root = s;
            s.setLeaf(false);
            s.addChild(r);
            splitChild(s, 0, r);
            r = s;
        }
        insertNonFull(r, toInsert);
        return this.root;
    }

    /**
     * This method inserts a new value to the tree knowing that it's not full.
     * @param node BtreeNode, that represents the root of sub tree.
     * @param toInsert String.
     * @return boolean according to the success.
     */
    private boolean insertNonFull(BTreeNode node, String toInsert) {
        int i = 0;
        if (node.isLeaf()) {
            while (i < node.getN() && toInsert.compareTo(node.getKeys().get(i)) > 0) { i++; }
            node.insertToKeys(toInsert, i);
            return true; } else { while (i < node.getN() && toInsert.compareTo(node.getKeys().get(i)) > 0) {
                i++; }
            if (node.getChildren().get(i).getN() == (2 * t - 1)) {
                splitChild(node, i, node.getChildren().get(i));
                if (toInsert.compareTo(node.getKeys().get(i)) > 0) {
                    i++;
                }
            }
            return insertNonFull(node.getChildren().get(i), toInsert); } }

    private void splitChild(BTreeNode father, int index, BTreeNode richSon) {
        BTreeNode son1 = new BTreeNode(t);
        BTreeNode son2 = new BTreeNode(t);
        int i = 0;
        for (int j = 0; j < t - 1 && j < richSon.getN(); j++) {
            son1.insertToKeys(richSon.getKeys().get(j));
            son2.insertToKeys(richSon.getKeys().get(j + t));
        }
        son1.setN(t - 1);
        son2.setN(t - 1);

        if (!richSon.isLeaf()) {
            for (int j = 0; j < t; j++) {
                son1.insertChild(richSon.getChildren().get(j));
                son2.insertChild(richSon.getChildren().get(j + t));
            }
        }
        father.insertToKeys(richSon.getKeys().get(t - 1));
        father.insertChild(son1, index);
        father.getChildren().set(index + 1, son2);
    }

    public BTree createFullTree(String input) {
        readFile(input);
        insertFileToTree();
        return this;
    }

    /**
     * This method reads the file and adds a BufferedReader static object represents the file.
     *
     * @param filePath String
     * @return boolean if file exists and has permissions.
     */
    public static boolean readFile(String filePath) {
        File file = new File(filePath);
        try {
            br = new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException e) {
            return false;

        }
        return true;
    }

    /**
     * This method inserts the contents of the file (that was opened to read mode in `br` variable) to the Tree.
     *
     * @return boolean according to the success.
     */
    public boolean insertFileToTree() {
        String st;
        try {
            while ((st = br.readLine()) != null) {
                this.insert(st);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * This method cuts the last letter of the string if it's the char that given as argument.
     *
     * @param st String
     * @param c char to look for at the end
     * @return the object st without the last letter, if the last character is c.
     */
    public static String shave(String st, char c) {
        if (st.charAt(st.length() - 1) == c) {
            st = st.substring(0, st.length() - 1);
        }
        return st;
    }

    /**
     * This method gives a representation to the tree, in string, according to our given conditions.
     * We use Queue that we've built for this task.
     * Few levels here:
     * 1. Initializing string.
     * 2. building the specific string for each level (through our concatenate level)
     * 3. updating the Linked list, that represents our level (for pushing it to the queue).
     * pushing it to the queue.
     * 4. writing this beautiful string to the output.txt
     * @return String, according to our given conditions.
     */
    public String toString() {
        String st = "";
        QueueAsLinkedList<LinkedList<LinkedList<BTreeNode>>> q = new QueueAsLinkedList<>();
        BTreeNode r = root;
        LinkedList<LinkedList<BTreeNode>> level = new LinkedList<>();
        st = concatenateKeysAndBuildChildrenList(st, r, level);
        st += "#";
        q.enqueue(level);
        while (!q.isEmpty()) {
            level = q.dequeue();
            st = concatenateLevel(st, level, q);
            st += '#'; }
        st = shave(st, '#');
        writeToOutput(st, System.getProperty("user.dir") + "/output.txt");
        return st; }

    /**
     * This method writes the desired string to the file in the filepath (if we have permissions of course).
     *
     * @param st
     * @param filePath
     * @return
     */
    private boolean writeToOutput(String st, String filePath) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(st);
            bw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * In this frame, build a new level. For each level, we build a linked list, that contains children objects
     * (for every node, we have a linked list that represents the children).
     * So it's a linked list looks like this:
     * [ childrenOfNode1, childrenOfNode2, childrenOfNode3 ]
     * after every iteration (we go into a smaller level, concatenateChildrenOfSameFather) , we add the '^' sign.
     * for more info about the inner functions, read their documentation below.
     *
     * @param st String
     * @param level level to digest,
     * @param q level to push to the queue.
     * @return
     */
    private String concatenateLevel(String st, LinkedList<LinkedList<BTreeNode>> level, QueueAsLinkedList<LinkedList<LinkedList<BTreeNode>>> q) {
        if (!level.isEmpty()) {
            LinkedList<LinkedList<BTreeNode>> levelToPush = new LinkedList<>();
            for (LinkedList<BTreeNode> children :
                    level) {
                st = concatenateChildrenOfSameFather(st, children, levelToPush);
                st += '^';
            }
            st = shave(st, '^');
            if (!levelToPush.isEmpty()) {
                q.enqueue(levelToPush); }}
        return st; }

    /**
     * Here we take care on a smaller level, children of same father.
     * @param st String
     * @param childrenOfSameFather children object , LinkedList<BtreeNode>
     * @param levelToPush Linked list of children objects.
     * @return String with our special conditions for children from the same father.
     */
    private String concatenateChildrenOfSameFather(String st, LinkedList<BTreeNode> childrenOfSameFather, LinkedList<LinkedList<BTreeNode>> levelToPush) {
        for (BTreeNode child :
                childrenOfSameFather) {
            st = concatenateKeysAndBuildChildrenList(st, child, levelToPush); // attention!!! changes also the `level to push`.
            st += '|';
        }
        st = shave(st, '|');
        return st;
    }

    /**
     *
     * @param st String
     * @param node Specific node
     * @param levelToPush Like before.
     * @return String with our special conditions for keys inside the node, here, we add for our 'level to push' all of
     * the children
     */
    private String concatenateKeysAndBuildChildrenList(String st, BTreeNode node, LinkedList<LinkedList<BTreeNode>> levelToPush) {

        for (String key : node.getKeys()
                ) {
            st += key + ",";
        }
        st = shave(st, ',');
        LinkedList<BTreeNode> children = node.getChildren();
        if (!children.isEmpty()) {
            levelToPush.add(children);
        }
        return st;
    }

}
