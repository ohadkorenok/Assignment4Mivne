public class Message {
    private String nameOfSender;
    private String nameOfRecipient;
    private String themessage;

public Message(String sender,String recipient, String message){
    if(sender==null | recipient==null)
        throw new IllegalArgumentException();
    this.nameOfSender=sender;
    this.nameOfRecipient=recipient;
    this.themessage=message;
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

}
