import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class a14ThreadPool {
	/*
	 * ExecutorService newCachedThreadPool()：沒有上限的線程池
	 * ExecutorService newFixedThreadPool(int nThreads)：有上限的線程池
	 */
	public static void main(String[] args) throws InterruptedException {

		ExecutorService pool1 = Executors.newScheduledThreadPool(0);

		pool1.submit(new MyRunnable2());
		pool1.submit(new MyRunnable2());
		pool1.submit(new MyRunnable2());
		pool1.submit(new MyRunnable2());
		pool1.submit(new MyRunnable2());

//		pool1.shutdown(); // 一般來說不會銷毀

		ExecutorService pool2 = Executors.newFixedThreadPool(3);
		pool2.submit(new MyRunnable2());
		pool2.submit(new MyRunnable2());
		pool2.submit(new MyRunnable2());
		pool2.submit(new MyRunnable2());
		pool2.submit(new MyRunnable2());
	}
}

class MyRunnable2 implements Runnable {
	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			Thread t = Thread.currentThread();
			System.out.println(t.getName() + "-" + i);
		}
	}
}
