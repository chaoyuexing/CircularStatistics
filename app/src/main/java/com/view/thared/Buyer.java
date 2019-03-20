package com.view.thared;

public class Buyer extends Thread {

    private Object buyerLook = new Object();
    private int count;
    private int buyNum = 1;

    public Buyer(String name,int count) {
        setName(name);
        this.count = count;
    }

    @Override
    public void run() {
        while (count >= 1) {
            count -= buyNum;
            if (Store.removeGoodsList(buyNum)) {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("停2秒，放到口袋里在拿下一个");
            } else {
                System.out.println("没货了，我先等10秒");
                try {
                    synchronized (buyerLook) {
                        buyerLook.wait(10000);
                        buyerLook.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
