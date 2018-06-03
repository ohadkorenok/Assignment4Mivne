public class HashList<T> {
    private HashListElement<T> first;
    private int size;

    public HashList(){first=null;size=0;}
    public int size(){return size;}
    public boolean isEmpty(){return first==null;}

    /**
     * Adding element to the first place inside the Linked-List.
     * @param element to add.
     * @param key is equal to the numeric-value that represent the element inserted before applying hash-function.
     */
    public void addFirst(T element,int key) {
        if (element == null)
            throw new RuntimeException();
        size++;
        first = new HashListElement<T>(key,element, first);
    }

    /**
     * Searching an element inside the list.
     * @return true if exist and also raising the counter of the element if its already inside.
     */
    public boolean containsandRaise(T element){
        boolean output = false;
        for(HashListElement<T> curr = first; curr != null & !output; curr = curr.getNext()) {
            output = element.equals(curr.getValue());
            if(output)
                curr.setCounter();
        }
        return output;
    }

    /**
     * Checking if an element exist in the table
     * @param element to check.
     * @return a direct adress to the object who contain the element.
     */
    public HashListElement<T> contains(T element){
        boolean output = false;
        HashListElement<T> out=null;
        for(HashListElement<T> curr = first; curr != null & !output; curr = curr.getNext()) {
            output = element.equals(curr.getValue());
            if(output)
                out=curr;
        }
        return out;
    }

}
