package com.view.kong.kim;

public class EnergySystem {


    private final double[] energyBoxes;

    public EnergySystem(int n, double initialEnergy) {
        energyBoxes = new double[n];
        for (int i = 0; i < energyBoxes.length; i++) {
            energyBoxes[i] = initialEnergy;
        }
    }



    public void transfer(int form,int to,double amount) {
        if (energyBoxes[form] < amount)
            return;
        System.out.println(Thread.currentThread().getName());
        energyBoxes[form] -= amount;
//        System.out.println("从%d转移%10.2f单位能量到%d",form,amount,to);
        energyBoxes[form] += amount;
//        System.out.println("能量总和：%10.2f%n",getTotalEnergies());

    }

    /**
     *
     * @return
     */
    public double getTotalEnergies() {
        double sum = 0;
        for (double amount:energyBoxes) {
            sum += amount;
        }
        return sum;
    }

    /**
     * 返回盒子的长度
     * @return
     */
    public int getBoxAmount() {
        return energyBoxes.length;
    }

}
