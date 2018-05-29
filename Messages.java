import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Messages implements Iterable<Message> {
    Message[] messages;

    public Messages() throws IOException{
        File file = new File(System.getProperty("user.dir")+"/messages.txt");
        BufferedReader br= new BufferedReader(new FileReader(file));
        String st;
        int messagesSize=1;
        while((st=br.readLine())!=null){
            if(st.startsWith("#"))
                messagesSize++;
        }
        messages=new Message[messagesSize];
    }
    public void generateMessages(String path) throws IOException{
        File file = new File(path);
        BufferedReader br= new BufferedReader(new FileReader(file));
        String st;
        String from="",to="",message="";
        int i=0;int countingMessages=messages.length-1;
        while((st=br.readLine())!=null){
            if(st.startsWith("From:"))
                from=st.substring(5);
            else if(st.startsWith("To:"))
                to=st.substring(3);
            else if(!st.startsWith("#"))
                message+=st;
            else if((st.startsWith("#"))){
                Message massageToInput=new Message(from,to,message);
                messages[i]=massageToInput;
                i++;
                if(countingMessages!=0){
                from="";to="";message="";}
                countingMessages--;
            }
        }
        Message massageToInput=new Message(from,to,message);
        messages[i]=massageToInput;
    }
    public Iterator<Message> iterator(){
        return new MessagesIterator();
    }
    private int countWords(Message count){
        String[] toCount=count.getThemessage().split(" ");
        return toCount.length;

    }
    private int findTheFirstClosestPrime(int k){
        PrimeIterator it=new PrimeIterator();
        int prime=it.next();
        while (prime<=k)
            prime=it.next();
        return prime;
    }
    private String checkDot(String toCheck){
        String fixedWord;
        char dot=toCheck.charAt(toCheck.length()-1);
        if(dot=='.')
            fixedWord=toCheck.substring(0,toCheck.length()-1);
        else
            fixedWord=toCheck;
        return fixedWord;
    }
    public HashTable createHashTable(int i, int m){
        int n=countWords(messages[i]);
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
    public HashTable[] createHashTables(int m){
        HashTable[] outputArr=new HashTable[messages.length];
        for(int i=0;i<messages.length;i++){
            outputArr[i]=createHashTable(i,m);
        }
        return outputArr;
    }
    public String findSpams(String path,BTree btree) throws IOException{
        String output="";
        Spams spamArr=new Spams(path);
        return output;
    }
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
