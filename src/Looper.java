

public class Looper {
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
    static Looper myLooper(){
        return sThreadLocal.get();
    }
    Thread currantThread;
    MessageQueue messageQueue = new MessageQueue();
    static public void prepare(){
        if(sThreadLocal.get()!=null){
            throw new RuntimeException("一个线程只能有一个looper");
        }
        sThreadLocal.set(new Looper());
        sThreadLocal.get().currantThread = Thread.currentThread();
    }

    static public void loop(){
        final Looper looper = myLooper();
        final MessageQueue queue = looper.messageQueue;
        while (true){
            if(sThreadLocal.get().messageQueue!=null){
                Message message;
                message = queue.next();
                message.target.handleMessage(message);
            }else {
                //sleep
                try {
                    synchronized (sThreadLocal.get().currantThread){
                        sThreadLocal.get().currantThread.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("loop");

        }
    }

}
