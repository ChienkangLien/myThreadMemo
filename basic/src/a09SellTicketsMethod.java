
public class a09SellTicketsMethod {
	public static void main(String[] args) {
		MyRunnable1 mr = new MyRunnable1();

		Thread t1 = new Thread(mr);
		Thread t2 = new Thread(mr);
		Thread t3 = new Thread(mr);

		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");

		t1.start();
		t2.start();
		t3.start();
	}
}

class MyRunnable1 implements Runnable {
	// Runnable作為參數傳入Thread，只會創建一次，不會共享數據，不須加上static
	int ticket = 0;

	@Override
	public void run() {
		while (true) {
			if (method()) {
				break;
			}
		}
	}

	// 非靜態，鎖物件為this，也就是創建出來的MyRunnable1
	private synchronized boolean method() {
		if (ticket < 100) {
			ticket++;
			System.out.println(Thread.currentThread().getName() + "正在賣第" + ticket + "張票");
			return false;
		} else {
			return true;
		}
	}
}
