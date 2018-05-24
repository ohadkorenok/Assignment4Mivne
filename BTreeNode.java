import java.io.*;
public class BTreeNode {

    private int n;
    private int t;
    private boolean leaf;
    private LinkedList<String> keys;
    private LinkedList<BTreeNode> children;

    public BTreeNode(int t) {
        this.t = t;
        this.n = 0;
        this.leaf = true;
        this.keys = new LinkedList<>();
        this.children = new LinkedList<>();
    }


    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public LinkedList<String> getKeys() {
        return keys;
    }

    public void setKeys(LinkedList<String> keys) {
        this.keys = keys;
    }

    public LinkedList<BTreeNode> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<BTreeNode> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public LinkedList<BTreeNode> addChild(BTreeNode node) {
        this.children.add(node);
        return this.children;
    }

    public LinkedList<String> insertToKeys(String toInsert, int i) {
        this.keys.add(i, toInsert);
        return this.keys;
    }


    public LinkedList<String> insertToKeys(String toInsert) {
        this.keys.add(toInsert);
        return this.keys;
    }

    public LinkedList<BTreeNode> insertChild(BTreeNode toInsert, int i) {
        this.children.add(i, toInsert);
        return this.children;
    }


    public LinkedList<BTreeNode> insertChild(BTreeNode toInsert) {
        this.children.add(toInsert);
        return this.children;
    }


}


