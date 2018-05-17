/*---------------------------------------
 Genuine author: <Nitzan Cohen>, I.D.: <203980750> , <Ohad Koren>, I.D.: <311116198>
 Date: 03-04-2018
---------------------------------------*/
import java.util.Iterator;
import java.util.NoSuchElementException;
public class LinkedList <T> implements List<T> {

    // ---------------------- fields ----------------------
    private Link<T> first;
    private Link<T> last;
    private int size;

    // ---------------------- constructors ----------------------
    public LinkedList(){
        first = null;
        last=null;
        size=0;
    }

    // ---------------------- methods ----------------------

    //Returns the number of elements in this list
    public int size() {
        return size;
    }

    //Returns true if this list contains no elements.
    public boolean isEmpty() {
        return first == null;
    }

    //Adds element to the beginning of this list
    public void addFirst(T element) {
        if (element == null)
            throw new NullPointerException();
        Link<T> toAdd=new Link<T>(element,first,null);
        if(first!=null)
            first.setPrev(toAdd);
        else
            last=toAdd;
        first = toAdd;
        size++;
    }
    public T removeLast(){
        if(last!=null) {
            T ans=last.getData();
            if (last.getPrev() == null & last.getNext() == null)
                last = first = null;
            else if (last.getPrev() != null) {
                last = last.getPrev();
                last.setNext(null);
            }
            size--;
            return ans;
        }
        return null;
    }
    //Removes first element of this list
    public T remove(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        Link<T> current = first;
        Link<T> prev = current;
        while (index > 0) {
            index = index - 1;
            prev = current;
            current = current.getNext();
        }
        T ans = (T) current.getData();
        if (first == current) {
            first = first.getNext();
            if(first.getNext()==null)
                last=first;
        }
        else {
            prev.setNext(current.getNext());
            if(current.getNext()!=null)
                current.getNext().setPrev(prev);
            else
                last=prev;
        }
        size--;
        return ans;
    }

    //Returns the element at the specified position in this list.
    public T get(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        Link<T> current = first;
        while (index > 0) {
            index = index - 1;
            current = current.getNext();
        }
        return (T) current.getData();
    }

    //Returns a String representing this object
    public String toString() {
        String output = "<";
        Link<T> current = first;
        while (current != null) {
            output = output + current.toString();
            current = current.getNext();
            if(current != null)
                output = output + ", ";
        }
        output = output + ">";
        return output;
    }

    //Remove the first Link which contains toRemove, if such one exists
    public boolean remove(T toRemove) {
        Link<T> current = first;
        Link<T> prev = current;
        boolean removed = false;
        while (current != null & !removed) {
            if ((current.getData()).equals(toRemove)) {
                // if the first link should be removed
                if (first == current) {
                    first = first.getNext();
                    if(first!=null)
                        first.setPrev(null);
                }
                else {
                    prev.setNext(current.getNext());
                    if(current.getNext()!=null)
                        current.getNext().setPrev(prev);
                }
                removed = true;
                size--;
            }
            else {
                prev = current;
                current = current.getNext();
            }
        }
        return removed;
    }
    public T specialRemove(Link<T> cur){ // remove a specific object with his address.
        if(cur==null)
            throw new NullPointerException();
        else{
            T data=cur.data;
            if(cur.getPrev()==null){
                first=first.getNext();
                if(first!=null)
                    first.setPrev(null);
                else
                    last=null;
            }
            else if(cur.getNext() ==null & cur.getPrev()!=null){
                last=last.getPrev();
                last.setNext(null);
                cur.setPrev(null);
            }
            else{
                cur.getPrev().setNext(cur.getNext());
                cur.getNext().setPrev(cur.getPrev());
            }
            size--;
            return data;
        }
    }
    //Returns true if this list contains the specified element
    public boolean contains(T element){
        boolean output = false;
        for(Link<T> curr = first; curr != null & !output; curr = curr.getNext())
            output = element.equals(curr.getData());
        return output;
    }


    //Replaces the element at the specified position in this list with the specified element
    public T set(int index, T element){
        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());

        Link<T> current = first;
        while (index > 0) {
            index = index - 1;
            current = current.getNext();
        }
        T prev = current.getData();
        current.setData(element);
        return prev;
    }

    //Inserts the specified element at the specified position in this list
    public void add(int index, T element) {
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        if (element == null)
            throw new NullPointerException();
        if(index == 0) {
            addFirst(element);
        }
        else {
            Link<T> prev = null ;
            Link<T> curr = first ;
            for(int i = 0; i < index; i = i + 1) {
                prev = curr ;
                curr = curr.getNext() ;
            }
            Link<T> toAdd = new Link<>(element, curr,prev);
            prev.setNext(toAdd);
            if(curr!=null)
                curr.setPrev(toAdd);
            size++;
        }
    }

    //Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
    public int indexOf(T element){
        int output = -1;
        int index = 0;
        for(Link<T> curr = first; curr != null & output == -1; curr = curr.getNext())
            if( curr.getData().equals(element) )
                output = index;
            else
                index = index + 1;
        return output;
    }

    //Appends the specified element to the end of this list
    public boolean add(T element) {
        if(element == null)
            throw new NullPointerException();
        Link<T> newLink = new Link<>(element);
        if(isEmpty()){
            first = newLink;
        }
        else {
            Link<T> current = first;
            while (current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(newLink);
            newLink.setPrev(current);
        }
        size++;
        return true;
    }

    public Link<T> specialAdd(int index,T element) { // same as add, just returning the pointer itself.
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        if (element == null)
            throw new NullPointerException();
        if(index == 0) {
            addFirst(element);
            return first;
        }
        else {
            Link<T> prev = null ;
            Link<T> curr = first ;
            for(int i = 0; i < index; i = i + 1) {
                prev = curr ;
                curr = curr.getNext() ;
            }
            Link<T> toAdd = new Link<>(element, curr,prev);
            prev.setNext(toAdd);
            if(curr!=null)
                curr.setPrev(toAdd);
            size++;
            return toAdd;
        }
    }

    //Returns Iterator of this List
    public Iterator<T> iterator(){
        return new LinkedListIterator<>(first);
    }

    private static class LinkedListIterator<T> implements Iterator<T> {
        private Link<T> current ;
        public LinkedListIterator (Link<T> start) {
            current = start ;
        }
        public boolean hasNext() {
            return current != null ;
        }
        public T next() {
            if (!hasNext ())
                throw new NoSuchElementException() ;
            T data = current.getData() ;
            current = current.getNext() ;
            return data ;
        }

        //DO NOT REMOVE OR CHANGE THIS MEHTOD – IT IS REQUIRED
        public void remove() {
            return;
        }
    }
    public static class Link <E>{
        // ---------------------- fields ----------------------
        private E data;
        private Link<E> next;
        private Link<E> prev;

        // ---------------------- constructors ----------------------
        public Link(E data, Link<E> next,Link<E> prev) {
            this.data = data;
            this.next = next;
            this.prev=prev;
        }

        public Link(E data) {
            this(data, null,null);
        }

        // ---------------------- Methods ----------------------
        public Link<E> getNext() {
            return next;
        }

        public void setNext(Link<E> next){
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public E setData(E data) {
            E tmp = this.data;
            this.data = data;
            return tmp;
        }
        public Link<E> getPrev(){return prev;}
        public void setPrev(Link<E> prevtoPut){this.prev=prevtoPut;}
        public String toString() {
            return data.toString();
        }
    }
}