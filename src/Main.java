//Напишите класс, реализующий интерфейс Runnable, содержащий поле-счетчик. Метод run() должен наращивать этот счетчик.
//        Создайте пять экземпляров этого класса и запустите их в пяти потоках с разными приоритетами.
//        После 100 миллисекунд работы остановите потоки и сравните значения счетчиков.
public class Main {

    public static void main(String[] args) {
        Thread.currentThread();
//        Thread t1 = new Thread(new WorkerRunnable("one", 1));
//        Thread t2 = new Thread(new WorkerRunnable("two", 2));
//        Thread t3 = new Thread(new WorkerRunnable("three", 3));
//        Thread t4 = new Thread(new WorkerRunnable("four", 4));
//        Thread t5 = new Thread(new WorkerRunnable("fife", 5));
        WorkerRunnable t1=new WorkerRunnable("one", Thread.NORM_PRIORITY-2);
        WorkerRunnable t2=new WorkerRunnable("two", Thread.NORM_PRIORITY-1);
        WorkerRunnable t3=new WorkerRunnable("three", Thread.NORM_PRIORITY);
        WorkerRunnable t4=new WorkerRunnable("four", Thread.NORM_PRIORITY+1);
        WorkerRunnable t5=new WorkerRunnable("fife", Thread.NORM_PRIORITY+2);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        try {

            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Главный поток прерван.");
        }

        t1.stop();
        t2.stop();
        t3.stop();
        t4.stop();
        t5.stop();

    }
    static class WorkerRunnable implements Runnable {
        long counter = 0;
        String name;
        Thread t;
        private volatile boolean shouldRun = true;


        public long getCounter() {
            return counter;
        }

        public WorkerRunnable(String name, int p) {
            this.counter = counter;
            this.name = name;
            t = new Thread(this);
            t.setPriority(p);
        }

        @Override

        public void run() {
            while (shouldRun) {
                counter++;
               // System.out.println(name + ": " + counter);
            }
        }

        public void stop() {
            shouldRun = false;
            System.out.println(name + ": " + counter);
        }

        public void start() {
            t.start();
        }
    }
}
