
public class a03PrintNumbers {
	/*
	 * 同時開啟兩個線程，共同獲取1-100之間的所有數字
	 * 將輸出所有的奇數
	 */
	public static void main(String[] args) {
		MyRunable1 mr = new MyRunable1();

		Thread t1 = new Thread(mr, "線程A");
		Thread t2 = new Thread(mr, "線程B");

		t1.start();
		t2.start();
	}
}

class MyRunable1 implements Runnable {
	// 第二種方式實現多線程，MyRunable1只創建一次，所以不需要加static
	int number = 1;

	@Override
	public void run() {
		// 1.循環
		while (true) {
			// 2.同步代碼塊
			synchronized (MyRunable1.class) {
				// 3.判斷共享數據（已經到末尾）
				if (number > 100) {
					break;
				} else {
					// 4.判斷共享數據（沒有到末尾）
					if (number % 2 == 1) {
						System.out.println(Thread.currentThread().getName() + "打印數字" + number);
					}
					number++;
				}
			}
		}
	}
}