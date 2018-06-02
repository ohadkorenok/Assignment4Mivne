import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Messages implements Iterable<Message> {
    Message[] messages;

    public Messages(){
        try {
            File file = new File(System.getProperty("user.dir") + "/messages.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int messagesSize = 1;
            while ((st = br.readLine()) != null) {
                if (st.startsWith("#"))
                    messagesSize++;
            }
            messages = new Message[messagesSize];
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    /**
    * @param: path for file
     * Organizing each message and split them into array.
     */
    public void generateMessages(String path){
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            String from = "", to = "", message = "";
            int i = 0;
            int countingMessages = messages.length - 1;
            while ((st = br.readLine()) != null) {
                if (st.startsWith("From:"))
                    from = st.substring(5);
                else if (st.startsWith("To:"))
                    to = st.substring(3);
                else if (!st.startsWith("#"))
                    message += st;
                else if ((st.startsWith("#"))) {
                    Message massageToInput = new Message(from, to, message);
                    messages[i] = massageToInput;
                    i++;
                    if (countingMessages != 0) {
                        from = "";
                        to = "";
                        message = "";
                    }
                    countingMessages--;
                }
            }
            Message massageToInput = new Message(from, to, message);
            messages[i] = massageToInput;
        }
        catch(IOException e)
        {System.out.println(e);}
    }
    public Iterator<Message> iterator(){
        return new MessagesIterator();
    }

    /**
     *
     * @param toCheck a word with dot in the end.
     * @return the word without the dot.
     */
    private String checkDot(String toCheck){
        String fixedWord;
        char dot=toCheck.charAt(toCheck.length()-1);
        if(dot=='.')
            fixedWord=toCheck.substring(0,toCheck.length()-1);
        else
            fixedWord=toCheck;
        return fixedWord;
    }

    /**
     *
     * @param i is the index in the Messages array.
     * @param m is the outside paramater for the size of the hashtable.
     * @return hashtable for messages[i].
     */
    public HashTable createHashTable(int i, int m){
        HashTable tableForSentence=new HashTable(m);
        String fixedWord;
        String[] splitting=messages[i].getThemessage().split(" ");
        for(int j=0;j<splitting.length;j++){
            fixedWord=checkDot(splitting[j]);
            Word input=new Word(fixedWord);
            int hashCode=tableForSentence.Hashfunction(input.getKey());
            if(tableForSentence.getTable()[hashCode]!=null) {
                if (!tableForSentence.getTable()[hashCode].containsandRaise(input.getWord())) // Assuming the same words have the same generative key
                    tableForSentence.gainandChain(hashCode,input.getWord());
                else
                    tableForSentence.updateFields();
            }
            else
                tableForSentence.gainandChain(hashCode,input.getWord());
        }
        return tableForSentence;
    }

    /**
     * Creating HashTables for each message in messages array.
     * @param m is the size paramater.
     */
    public void createHashTables(String m){
        for(int i=0;i<messages.length;i++){
            messages[i].setHashTable(createHashTable(i,Integer.parseInt(m)));
        }
    }
    /**
     * Checking if both sender and recipient are friends with the friend's Btree.
     */
    private boolean checkifFriends(int i,BTree btree){
        boolean found=true;
        String strToCheck1=messages[i].getNameOfRecipient()+" & "+ messages[i].getNameOfSender();
        String strToCheck2=messages[i].getNameOfSender()+" & "+messages[i].getNameOfRecipient();
        Pair toSearch=btree.search(strToCheck1);
        Pair toSearch2=btree.search(strToCheck2);
        if(toSearch ==null && toSearch2==null)
            found=false;
        return found;
    }

    /**
     *
     * @param path for reading the spam words.
     * @param btree for understanding friends relations.
     * @return Which message contain the spam words.
     */
    public String findSpams(String path,BTree btree){
        String output = "";
        boolean foundSpam=false;
            Spams spamArr = new Spams(path);
            for(int i=0;i<messages.length;i++){
                if (!checkifFriends(i,btree)){
                    Iterator<Spam> it=spamArr.iterator();
                    while(it.hasNext()&!foundSpam){
                        Spam obj=it.next();
                        HashListElement toSearch=messages[i].getTable().searchWord(obj.getWord());
                        if(toSearch!=null){
                            if((toSearch.getCounter()/(double)messages[i].getTable().getnumberOfEntries())*100 >= obj.getPercent()){
                                output+=(i+",");foundSpam=true;}
                        }}}foundSpam=false;}
            return BTree.shave(output, ',');
    }

    /**
     *
     * Generic private iterator class.
     */
    private class MessagesIterator<T> implements Iterator<T>{
        private int index;
        public MessagesIterator(){
            index=0;
        }
        public boolean hasNext(){
            return index<messages.length;
        }
        public T next(){
            if(hasNext()){
                int currindex=index;
                this.index++;
                return (T)messages[currindex];
            }
            throw new NoSuchElementException();
        }
    }

}
