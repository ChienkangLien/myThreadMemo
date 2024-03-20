
public class a08SellTicketsBlock {
	public static void main(String[] args) {
		MyThread5 t1 = new MyThread5();
		MyThread5 t2 = new MyThread5();
		MyThread5 t3 = new MyThread5();

		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");

		t1.start();
		t2.start();
		t3.start();
	}
}

class MyThread5 extends Thread {
	// static、表示這個類所有的物件，共享ticket屬性
	static int ticket = 0;

	// 鎖物件，一定要是唯一的，加上static
	static Object obj = new Object();

	@Override
	public void run() {
		while (true) {
			
//			synchronized (obj) {
			synchronized (MyThread5.class) { // 或是使用MyThread5本身的字節碼
				if (ticket < 100) {
					ticket++;
					System.out.println(getName() + "正在賣第" + ticket + "張票");
				} else {
					break;
				}
			}
		}
	}
}
