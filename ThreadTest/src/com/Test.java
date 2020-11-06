package com;

        /*多线程的测试*/

class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            this.setName("分线程");
            if (i%2==0){
                System.out.println(Thread.currentThread().getName()+"----->"+i);
            }
        }
    }
}
public class Test {

    public static void main(String[] args) {
        MyThread T1 = new MyThread();
        Thread.currentThread().setName("主线程");
        T1.start();
//        new Thread(){
//            @Override
//            public void run() {
//                this.setName("分线程");
//                for (int i = 0; i <100 ; i++) {
//                    if (i%2==0) {
//                        System.out.println(Thread.currentThread().getName() + "-----> " + i);
//                    }
//                }
//            }
//        }.start();

        for (int i = 0; i <100 ; i++) {
            if (i%2!=0) {
                System.out.println(Thread.currentThread().getName() + "-----> " + i);
            }
            if(i==20){
                try {
                    T1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

