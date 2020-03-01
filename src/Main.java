public class Main {
    public static void main(String[] args) {
        Looper.prepare();
        Handler handler = new MHandler();
        int num1 = 1;
        int num2 = 2;
        String s1 = "main";
        String s2 = "son";
        handler.sendMessage(new Message(new Runnable() {
            @Override
            public void run() {
            }
        }, num1, s1));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i = 0;i<50;i++) {
                    handler.sendMessage(new Message(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(Thread.currentThread());
                            System.out.println(Looper.myLooper().messageQueue.next().next.arg1);
                            System.out.println(Looper.myLooper().messageQueue.next().next.obj);//主线程传入的消息
                            System.out.println(Looper.myLooper().messageQueue.next().arg1);
                            System.out.println(Looper.myLooper().messageQueue.next().obj);//子线程传入的消息
                        }
                    },num2,s2));
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendMessage(new Message(new Runnable() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                },null,null));
            }
        }).start();

        Looper.loop();
    }
}
