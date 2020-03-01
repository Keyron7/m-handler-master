public abstract class Handler {
    private final Looper mLooper;
    Handler(){
        mLooper = Looper.myLooper();
    }
    void sendMessage(Message message){
        message.target = this;
        synchronized (mLooper){
            mLooper.messageQueue.add(message);
        }
        synchronized (mLooper.currantThread) {
            mLooper.currantThread.notify();
        }
    }
    abstract void handleMessage(Message message);

}
