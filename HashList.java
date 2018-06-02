public class HashList<T> {
    private HashListElement<T> first;
    private int size;

    public HashList(){first=null;size=0;}
    public int size(){return size;}
    public boolean isEmpty(){return first==null;}
    public void addFirst(T element,int key) {
        if (element == null)
            throw new NullPointerException();
        size++;
        first = new HashListElement<T>(key,element, first);
    }
    public boolean containsandRaise(T element){
        boolean output = false;
        for(HashListElement<T> curr = first; curr != null & !output; curr = curr.getNext()) {
            output = element.equals(curr.getValue());
            if(output)
                curr.setCounter();
        }
        return output;
    }
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
