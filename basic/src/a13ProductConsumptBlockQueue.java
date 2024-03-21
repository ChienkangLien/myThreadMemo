import java.util.concurrent.ArrayBlockingQueue;

public class a13ProductConsumptBlockQueue {
	public static void main(String[] args) {

		// 創建阻塞隊列的，指定容量為 1
		ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(1);

		FoodieBQ f = new FoodieBQ(arrayBlockingQueue);
		CookerBQ c = new CookerBQ(arrayBlockingQueue);

		f.start();
		c.start();
	}
}

class DeskBQ {
	// 漢堡的總數量
	public static int count = 10;
}

class FoodieBQ extends Thread {

	private ArrayBlockingQueue<String> queue;

	public FoodieBQ(ArrayBlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {

		// 不斷從隊列獲取漢堡
		while (true) {
			if (DeskBQ.count == 0) {
				break;
			} else {
				try {
					String food = queue.take(); // 底層以實現鎖跟等待機制
					DeskBQ.count--;
					System.out.println("吃貨吃了一個" + food + "，還能再吃" + DeskBQ.count + "個");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class CookerBQ extends Thread {

	private ArrayBlockingQueue<String> queue;

	public CookerBQ(ArrayBlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {

		// 不斷向隊列放入漢堡
		while (true) {
			if (DeskBQ.count == 0) {
				break;
			} else {
				try {
					queue.put("漢堡"); // 底層以實現鎖跟等待機制
					System.out.println("廚師放入一個漢堡");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}