import java.util.concurrent.locks.ReentrantLock;

public class a10SellTicketsLock {
	public static void main(String[] args) {
		MyThread6 t1 = new MyThread6();
		MyThread6 t2 = new MyThread6();
		MyThread6 t3 = new MyThread6();

		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");

		t1.start();
		t2.start();
		t3.start();
	}
}

class MyThread6 extends Thread {
	// static、表示這個類所有的物件，共享ticket屬性
	static int ticket = 0;

	static private ReentrantLock lock = new ReentrantLock();

	@Override
	public void run() {
		while (true) {
			lock.lock();
			
			// 需使用try/finally以免break出去沒解鎖造成死循環
			try {
				if (ticket < 100) {
					ticket++;
					System.out.println(getName() + "正在賣第" + ticket + "張票");
				} else {
					break;
				}
			} finally {
				lock.unlock();
			}
		}
	}
}
