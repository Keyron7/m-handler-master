

public class MessageQueue {
    Message messages;
    void add(Message message){
            Message p = messages;
            message.next = p;
            messages = message;
    }//加
    Message next(){
        if (messages!= null){
        Message msg = messages;
        return msg;
        }else {
            return new Message(null,500,null);
        }
    }//取
}
