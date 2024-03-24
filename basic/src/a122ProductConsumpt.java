import java.util.ArrayList;
import java.util.List;

public class a122ProductConsumpt {
	/*
	 * 3個生產者線程，負責生產包子，每個線程每次只能生產1個包子放在桌子上
	 * 2個消費者線程負責吃包子，每人每次只能從桌子上拿1個包子吃
	 */
	public static void main(String[] args) {
		Desk1 desk = new Desk1();

		// 創建3個生產者線程（3個廚師）
		new Thread(() -> {
			while (true) {
				desk.put();
			}
		}, "廚師1").start();

		new Thread(() -> {
			while (true) {
				desk.put();
			}
		}, "廚師2").start();

		new Thread(() -> {
			while (true) {
				desk.put();
			}
		}, "廚師3").start();

		// 創建2個消費者線程（2個吃貨）
		new Thread(() -> {
			while (true) {
				desk.get();
			}
		}, "吃貨1").start();

		new Thread(() -> {
			while (true) {
				desk.get();
			}
		}, "吃貨2").start();
	}
}

class Desk1 {
	private List<String> list = new ArrayList<>();

	// 放1個包子的方法
	// 廚師1 廚師2 廚師3
	public synchronized void put() {
		try {
			String name = Thread.currentThread().getName();
			// 判斷是否有包子。
			if (list.size() == 0) {
				list.add(name + "做的肉包子");
				System.out.println(name + "做了一個肉包子~~");
				Thread.sleep(20);

				// 喚醒別人, 等待自己
				this.notifyAll();
				this.wait();
			} else {
				// 有包子了，不做了。
				// 喚醒別人, 等待自己
				this.notifyAll();
				this.wait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 吃貨1 吃貨2
	public synchronized void get() {
		try {
			String name = Thread.currentThread().getName();
			if (list.size() == 1) {
				// 有包子，吃了
				System.out.println(name + "吃了：" + list.get(0));
				list.clear();
				Thread.sleep(10);
				this.notifyAll();
				this.wait();
			} else {
				// 沒有包子
				this.notifyAll();
				this.wait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}