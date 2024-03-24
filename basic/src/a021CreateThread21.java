
public class a021CreateThread21 {
	/*
	 * 定義一個類MyRunnable實現Runnable介面
	 * 在MyRunnable類中重寫run()方法
	 * 創建MyRunnable類的物件
	 * 創建Thread類的物件，把MyRunnable物件作為構造方法的參數
	 * 啟動執行緒
	 */
	public static void main(String[] args) throws InterruptedException {
		// 當前由main執行
		System.out.println(Thread.currentThread().getName());
		
		// 由當前main來休眠
		Thread.sleep(3000);
		
		MyRunnable mr = new MyRunnable();

		Thread t1 = new Thread(mr);
		Thread t2 = new Thread(mr, "t2");

		t1.setName("t1");

		t1.start();
		t2.start();
	}
}

class MyRunnable implements Runnable {
	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			Thread t = Thread.currentThread();
			System.out.println(t.getName() + "-" + i);
			try {
				// 由當前執行緒來休眠
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
