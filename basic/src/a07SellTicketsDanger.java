
public class a07SellTicketsDanger {
	public static void main(String[] args) {
		MyThread4 t1 = new MyThread4();
		MyThread4 t2 = new MyThread4();
		MyThread4 t3 = new MyThread4();

		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");

		t1.start();
		t2.start();
		t3.start();
	}
}

class MyThread4 extends Thread {
	// static、表示這個類所有的物件，共享ticket屬性
	static int ticket = 0;

	@Override
	public void run() {
		while (true) {
			if (ticket < 100) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ticket++;
				System.out.println(getName() + "正在賣第" + ticket + "張票");
			} else {
				break;
			}
		}
	}
}
