public class Message {
    Handler target;//发送消息的handler
    Runnable runnable;//分线程执行
    int what;
    int arg1;
    Object obj;
    Message next;//形成链表

    public Message(Runnable runnable,Integer arg1,Object obj) {
        this.runnable = runnable;
        this.arg1 = arg1;
        this.obj = obj;
    }
}

