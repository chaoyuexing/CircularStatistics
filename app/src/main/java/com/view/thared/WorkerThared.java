package com.view.thared;


/**
 * 工人主要是生产产品
 */
public class WorkerThared extends Thread{

//    private int productionNumber;
    public boolean isWork = true;
    private Object workerLook = new Object();
//    public WorkerThared (int productionNumber) {
//        this.productionNumber = productionNumber;
//        System.out.println(getName()+"你需要生产"+productionNumber+"个产品");
//    }

    public WorkerThared (String name) {
        setName(name);
    }
@Override
    public void run() {
        int count = 0;

        while (isWork) {
            // 每一秒生成一个产品

            if (Store.goodsList.size() < 50) {
                Store.addGoodsList((int) Math.random());
                count++;
                System.out.println(getName()+"生成了"+1+"个产品");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else  {
                synchronized (workerLook) {
                    try {
                        System.out.println(getName()+"不需要生产了");
                        workerLook.wait(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    notifyAll();
                }

            }

        }
        System.out.println(getName()+"一共生成了"+count+"个产品");
    }
}
