import java.io.IOException;
import java.util.Iterator;


public class Trial {
    public static void main(String[] args){
        try {
            Messages messages = createArrayOfMessages();
            Iterator<Message> it=messages.iterator();
            while(it.hasNext()){
                Message curr=it.next();
                System.out.println("From:"+ curr.getNameOfSender());
                System.out.println("To:"+curr.getNameOfRecipient());
                System.out.println("Message:" + curr.getThemessage());
                System.out.println();
            }
            HashTable table=messages.createHashTable(0,16);
            System.out.println("s");
        }
        catch(Exception e) {
            System.out.println(e);
        }
        try{
            Spams sentence= createArrayOfSpam();
            Iterator<Spam> it2=sentence.iterator();
            while(it2.hasNext()){
                Spam cur=it2.next();
                System.out.println("Word: "+ cur.getWord());
                System.out.println("Percent: "+cur.getPercent());
            }
        }
        catch(Exception d) {
            System.out.println(d);
        }
    }
    private static Messages createArrayOfMessages() throws IOException{
        Messages messages = new Messages();
        messages.generateMessages(System.getProperty("user.dir")+"/messages.txt");
        return messages;
    }
    private static Spams createArrayOfSpam() throws IOException{
        Spams sentence = new Spams(System.getProperty("user.dir")+"/spam_words.txt");
        return sentence;
    }
}
