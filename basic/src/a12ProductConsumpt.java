
public class a12ProductConsumpt {
	public static void main(String[] args) {
		Foodie f = new Foodie();
		Cooker c = new Cooker();

		f.start();
		c.start();
	}
}

class Desk {

	// 定義一個標記，桌子上是否有漢堡 0：沒有、1：有
	// 此範例兩個線程，如果線程數量更多，可以再定義不同數字對應不同線程
	public static int foodFlag = 0;

	// 漢堡的總數量
	public static int count = 10;

	// 鎖對象
	public static final Object lock = new Object();
}

class Foodie extends Thread {
	@Override
	public void run() {

		// 1. while(true)循環
		// 2. 同步代碼塊
		// 3. 判斷共享數據是否結束，如果沒有才執行核心邏輯(最多就執行10個漢堡)
		while (true) {
			synchronized (Desk.lock) {
				if (Desk.count == 0) {
					break;
				} else {
					// 判斷桌子上是否有漢堡。
					if (Desk.foodFlag == 0) {
						// 如果沒有就等待
						try {
							Desk.lock.wait(); // 讓當前線程跟鎖進行綁定
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						// 漢堡的總數量減一
						Desk.count--;
						// 如果有就開吃
						System.out.println("吃貨在吃漢堡，還能再吃" + Desk.count + "個");
						// 叫醒等待的生產者繼續生產
						Desk.lock.notifyAll();
						// 修改桌子的狀態
						Desk.foodFlag = 0;
					}
				}
			}
		}
	}
}

class Cooker extends Thread {
	@Override
	public void run() {

		// 1. while(true)循環
		// 2. 同步代碼塊
		// 3. 判斷共享數據是否結束，如果沒有才執行核心邏輯(最多就執行10個漢堡)
		while (true) {
			synchronized (Desk.lock) {
				if (Desk.count == 0) {
					break;
				} else {
					// 判斷桌子上是否有漢堡
					if (Desk.foodFlag == 1) {
						// 如果有就等待
						try {
							Desk.lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						// 如果沒有才生產
						System.out.println("廚師生產了一個漢堡");
						// 修改桌子的狀態
						Desk.foodFlag = 1;
						// 叫醒等待的消費者
						Desk.lock.notifyAll();
					}
				}
			}
		}
	}
}