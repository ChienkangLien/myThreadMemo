
public class a01CreateThread1 {
	/*
	 * 定義一個類MyThread繼承Thread類
	 * 在MyThread類中重寫run()方法
	 * 創建MyThread類的物件
	 * 啟動執行緒
	 */
	public static void main(String[] args) {
		MyThread t1 = new MyThread();
		MyThread t2 = new MyThread("t2");

		// 如果不設置名字，默認名字是Thread-X(從0開始)
		// 也可繼承Thread其中的構造方法來傳入
		t1.setName("t1");

		t1.start();
		t2.start();
	}
}

class MyThread extends Thread {
	public MyThread() {
		super();
	}

	public MyThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			System.out.println(getName() + "-" + i);
		}
	}
}
