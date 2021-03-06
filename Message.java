public class Message {
    private String nameOfSender;
    private String nameOfRecipient;
    private String themessage;
    private HashTable table;

public Message(String sender,String recipient, String message){
    if(sender==null | recipient==null)
        throw new IllegalArgumentException();
    this.nameOfSender=sender;
    this.nameOfRecipient=recipient;
    this.themessage=message;
    table=null;
}
public String getNameOfSender(){
    return nameOfSender;
}
public String getNameOfRecipient(){
    return nameOfRecipient;
}
public String getThemessage(){
    return themessage;
}
public HashTable getTable(){return table;}
public void setHashTable(HashTable table){
    if(table==null)
        throw new RuntimeException();
    else
        this.table=table;}


}
