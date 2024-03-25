import java.util.concurrent.atomic.AtomicInteger;

public class a16OptimisticLocking {
	public static void main(String[] args) {

		MyRunnable4 myRunnable = new MyRunnable4();
		for (int i = 0; i < 100; i++) {
			new Thread(myRunnable).start();
		}
	}
}

class MyRunnable4 implements Runnable {

	// 悲觀鎖
//	private int count = 0;
//
//	@Override
//	public void run() {
//		for (int i = 0; i < 100; i++) {
//			synchronized (this) {
//				System.out.println(Thread.currentThread().getName() + "count == " + ++count);
//			}
//		}
//	}

	// 樂觀鎖
	private AtomicInteger count = new AtomicInteger();

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + "count == " + count.addAndGet(1));
		}
	}
}