package ru.akhitev.prepare_for_sertificate.specialist.threads;

/**
 * Created by hitev on 18.06.14.
 */
class Data{
    int count=0;
    static int countSt=0;
}
class MyThread implements Runnable{
    AnotherData obj;
    MyThread(AnotherData d){
        obj=d;
        new Thread(this).start();
    }
    // Методы, реализующий накладку, если не сделать синхронизацию
    void Add(){
        try{
            Thread.sleep(10);
            int n=obj.count;
            n++;
            Thread.sleep(10);
            obj.count=n;
        }catch(InterruptedException ex){ }
    }
    // Методы, реализующий накладку, если не сделать синхронизацию
    static void AddStatic(){
        try{
            Thread.sleep(10);
            int n= AnotherData.countSt;
            n++;
            Thread.sleep(10);
            AnotherData.countSt=n;
        }catch(InterruptedException ex){ }
    }
    public void run(){
        for(int i=0;i<10;i++) Add();
        for(int i=0;i<10;i++) AddStatic();
    }
}

public class Main5 {
    public static void main(String[] args) throws Exception{
        // Обид объект на оба потока
        AnotherData d = new AnotherData();
        //MyThread t1 = new MyThread(d);
        //MyThread t2 = new MyThread(d);
        //SynchronyzedMethodsMyThread t1 = new SynchronyzedMethodsMyThread(d);
        //SynchronyzedMethodsMyThread t2 = new SynchronyzedMethodsMyThread(d);
        SynchronyzedBothConstructionsMyThread t1 = new SynchronyzedBothConstructionsMyThread(d);
        SynchronyzedBothConstructionsMyThread t2 = new SynchronyzedBothConstructionsMyThread(d);
        Thread.sleep(1000);
        System.out.println(d.count);
        System.out.println(AnotherData.countSt);
    }
}

class SynchronyzedMethodsMyThread implements Runnable{
    AnotherData obj;
    SynchronyzedMethodsMyThread(AnotherData d){
        obj=d;
        new Thread(this).start();
    }
    // Методы, реализующий накладку, если не сделать синхронизацию
    synchronized void Add(){
        try{
            Thread.sleep(10);
            int n=obj.count;
            n++;
            Thread.sleep(10);
            obj.count=n;
        }catch(InterruptedException ex){ }
    }
    // Методы, реализующий накладку, если не сделать синхронизацию
    synchronized static void AddStatic(){
        try{
            Thread.sleep(10);
            int n= AnotherData.countSt;
            n++;
            Thread.sleep(10);
            AnotherData.countSt=n;
        }catch(InterruptedException ex){ }
    }
    public void run(){
        for(int i=0;i<10;i++) Add();
        for(int i=0;i<10;i++) AddStatic();
    }
}

class SynchronyzedBothConstructionsMyThread implements Runnable{
    AnotherData obj;
    SynchronyzedBothConstructionsMyThread(AnotherData d){
        obj=d;
        new Thread(this).start();
    }
    // Методы, реализующий накладку, если не сделать синхронизацию
    void Add(){
        try{
            synchronized (obj){
                Thread.sleep(10);
                int n=obj.count;
                n++;
                Thread.sleep(10);
                obj.count=n;
            }
        }catch(InterruptedException ex){ }
    }
    // Методы, реализующий накладку, если не сделать синхронизацию
    synchronized static void AddStatic(){
        try{
            Thread.sleep(10);
            int n= AnotherData.countSt;
            n++;
            Thread.sleep(10);
            AnotherData.countSt=n;
        }catch(InterruptedException ex){ }
    }
    public void run(){
        for(int i=0;i<10;i++) Add();
        for(int i=0;i<10;i++) AddStatic();
    }
}