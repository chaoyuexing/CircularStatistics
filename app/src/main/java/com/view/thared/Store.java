package com.view.thared;

import java.util.ArrayList;
import java.util.List;

public class Store {


    public static volatile List<Integer> goodsList = new ArrayList<>();
    public static final int goodsListMaxNum = 50;
    private static WorkerThared workerThared;
    private static WorkerThared workerThared1;
    private static WorkerThared workerThared2;
    private static WorkerThared workerThared3;

    public static void addGoodsList(int a) {
        goodsList.add(a);
        if (goodsList.size() > goodsListMaxNum) {
            System.out.println("商店中商品够用了，停止供货了小老弟");
            workerThared.isWork = false;
//            workerThared1.isWork = false;
//            workerThared2.isWork = false;
//            workerThared3.isWork = false;
        }
        System.out.println("商店中一共有"+goodsList.size()+"个产品");
    }


    public static boolean removeGoodsList(int count) {
        int saleGoodsNum = count;
        if (goodsList.size() <=0 && goodsList.size() < count) {
            System.out.println("商店暂时没有怎么多产品，请等待,苦力开始干活了");
            workerThared.isWork = true;
//            workerThared1.isWork = true;
//            workerThared2.isWork = true;
//            workerThared3.isWork = true;
            return false;
        }
        while (saleGoodsNum >= 1) {
            saleGoodsNum --;
            goodsList.remove(0);
        }
        System.out.println("商店成功卖出"+count+"产品");
        if (goodsList.size() < 10) {
            System.out.println("需要补货了，小老弟");
            workerThared.isWork = true;
//            workerThared1.isWork = true;
//            workerThared2.isWork = true;
//            workerThared3.isWork = true;
        }
        return true;
    }


    public static void main(String[] args) {
         workerThared = new WorkerThared("苦力小李");
//         workerThared1 = new WorkerThared("苦力小李1");
//         workerThared2 = new WorkerThared("苦力小李2");
//         workerThared3 = new WorkerThared("苦力小李3");
        Buyer buyer = new Buyer("大邢",20);
//        Buyer buyer1 = new Buyer("大邢1",20);
//        Buyer buyer2 = new Buyer("大邢2",20);
//        Buyer buyer3 = new Buyer("大邢3",20);
//        Buyer buyer4 = new Buyer("大邢4",20);
        workerThared.start();
        workerThared.isWork = true;
//        workerThared1.start();
//        workerThared2.start();
//        workerThared3.start();
        buyer.start();
//        buyer1.start();
//        buyer2.start();
//        buyer3.start();
//        buyer4.start();
    }

}
