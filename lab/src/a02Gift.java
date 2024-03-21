
public class a02Gift {
	/*
	 * 有100份禮品，兩人同時發送，當剩下的禮品小於10份的時候則不再送出 模擬該過程並將線程的名字和禮物的剩餘數量打印出來
	 */
	public static void main(String[] args) {
		
		MyRunable mr = new MyRunable();
		
		Thread t1 = new Thread(mr, "窗口1");
		Thread t2 = new Thread(mr, "窗口2");
		
		t1.start();
		t2.start();
	}
}

class MyRunable implements Runnable {
	// 第二種方式實現多線程，MyRunable只創建一次，所以不需要加static
	int count = 100;

	@Override
	public void run() {
		// 1.循環
		while (true) {
			// 2.同步代碼塊
			synchronized (MyRunable.class) {
				// 3.判斷共享數據（已經到末尾）
				if (count < 10) {
					System.out.println("禮物還剩下" + count + "不再贈送");
					break;
				} else {
					// 4.判斷共享數據（沒有到末尾）
					count--;
					System.out.println(Thread.currentThread().getName() + "在贈送禮物，還剩下" + count + "個禮物!!!");
				}
			}
		}
	}
}