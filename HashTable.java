public class HashTable {
    private int size;
    private HashList[] table;
    private float loadfactor;
    private int numberOfEntries;
    private final double A=0.618;
    public HashTable(int size){
        if(size<0)
            throw new RuntimeException();
        this.size=size;
        numberOfEntries=0;
        loadfactor=numberOfEntries/this.size;
        table= new HashList[this.size];
    }
    public int Hashfunction(int k){
        return (int)Math.floor(size*(k*A%1));
    }
    public HashList[] getTable(){return table;}
    public void updateFields(){numberOfEntries++;loadfactor=(float)numberOfEntries/size;}
    public void gainandChain(int hashCode,Object element){
        if(table[hashCode]==null)
            table[hashCode]=new HashList();
        table[hashCode].addFirst(element,hashCode);
        updateFields();
    }
    public HashListElement searchWord(String element){
        Word source=new Word(element);
        int hashCode=Hashfunction(source.getKey());
        return table[hashCode].contains(element);
    }
}
