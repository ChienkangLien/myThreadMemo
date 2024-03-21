public class a01TicketSales {
	/*
	 * 一共有1000張電影票，可以在兩個窗口領取，假設每次領取的時間為3毫秒
	 * 模擬賣票過程並打印剩餘電影票的數量
	 */
	public static void main(String[] args) {

		MyThread t1 = new MyThread();
		MyThread t2 = new MyThread();

		t1.setName("窗口1");
		t2.setName("窗口2");

		t1.start();
		t2.start();
	}
}

class MyThread extends Thread {
	// 第一種方式實現多線程，MyThread會創建多次，所以需要加static
	static int ticket = 1000;

	@Override
	public void run() {
		// 1.循環
		while (true) {
			// 2.同步代碼塊
			synchronized (MyThread.class) {
				// 3.判斷共享數據（已經到末尾）
				if (ticket == 0) {
					break;
				} else {
					// 4.判斷共享數據（沒有到末尾）
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ticket--;
					System.out.println(getName() + "在賣票，還剩下" + ticket + "張票!!!");
				}
			}
		}
	}
}