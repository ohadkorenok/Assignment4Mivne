public class HashTable {
    private int size;
    private HashList[] table;
    private float loadfactor;
    private int numberOfEntries;
    private final double A=0.618;
    public HashTable(int size){
        if(size<=0)
            throw new RuntimeException();
        this.size=size;
        numberOfEntries=0;
        loadfactor=numberOfEntries/this.size;
        table= new HashList[this.size];
    }
    public HashTable(String size){this(Integer.parseInt(size)); }
    public int getnumberOfEntries(){return numberOfEntries;}

    /**
     * Multiplication method for distributive hash-function.
     * @param k is the key.
     * @return value between the size of the array.
     */
    public int Hashfunction(int k){
        return (int)Math.floor(size*(k*A%1));
    }
    public HashList[] getTable(){return table;}

    /**
     * Updating the main-values of the hash-table.
     */
    public void updateFields(){numberOfEntries++;loadfactor=(float)numberOfEntries/size;}

    /**
     * Method who insert the element to the first place in the linked-list.
     */
    public void insert(Object element){
        if(element==null)
            throw new RuntimeException();
        if(element instanceof String){
            Word toHash=new Word((String)element);
            this.gainandChain(this.Hashfunction(toHash.getKey()),element);
        }
    }

    /**
     * The main inserting function of the hash-table, creating a Linked-List if necessery.
     * @param hashCode required for the element.
     */
    public void gainandChain(int hashCode,Object element){
        if(element==null)
            throw new RuntimeException();
        if(table[hashCode]==null)
            table[hashCode]=new HashList();
        table[hashCode].addFirst(element,hashCode);
        updateFields();
    }

    /**
     * Searching a word inside the hash-table
     * @return the HashListElement who include the element searched.
     */
    public HashListElement searchWord(String element){
        Word source=new Word(element);
        int hashCode=Hashfunction(source.getKey());
        if(table[hashCode]!=null)
            return table[hashCode].contains(element);
        else return null;
    }
}
