import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Spams implements Iterable<Spam> {
    private Spam[] arr;
    public Spams(String path){
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int arrSize = 0;
            while ((st = br.readLine()) != null) {
                arrSize++;
            }
            arr = new Spam[arrSize];
            this.generateSpam(path);
        }
        catch(IOException e){System.out.println(e);}
    }

    /**
     * The main method who create for each spam-word a Spam encapsulation and then inserting to Spams array.
     * @param path to read from text file.
     */
    public void generateSpam(String path){
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int i = 0;
            int indexOfspace;
            while ((st = br.readLine()) != null) {
                indexOfspace = st.indexOf(" ");
                int percent = Character.getNumericValue(st.charAt(indexOfspace + 1));
                Spam word = new Spam(st.substring(0, indexOfspace), percent);
                arr[i] = word;
                i++;
            }
        }
        catch(IOException e){System.out.println(e);}
    }
    public Iterator<Spam> iterator(){
        return new SpamWordIterator();
    }

    /**
     * Iterator for iterating each Spam-word inside Spams.
     */
    private class SpamWordIterator<T> implements Iterator<T>{
        private int index;
        public SpamWordIterator(){
            index=0;
        }
        public boolean hasNext(){
            return index<arr.length;
        }
        public T next(){
            if(hasNext()){
                int currindex=index;
                this.index++;
                return (T)arr[currindex];
            }
            throw new NoSuchElementException();
        }
    }
}
