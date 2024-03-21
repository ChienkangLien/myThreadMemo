import java.util.Random;

public class a04GrabRedEnvelopes {
	/*
	 * 搶紅包也用到了多線程
	 * 假設：100塊，分成了3個包，現在有5個人去搶
	 * 其中，紅包是共享數據
	 * 5個人是5條線程
	 * 打印結果如下：
	 * XXX搶到了XXX元
	 * XXX搶到了XXX元
	 * XXX搶到了XXX元
	 * XXX沒搶到
	 * XXX沒搶到
	 */
	public static void main(String[] args) {
		MyThread1 t1 = new MyThread1();
		MyThread1 t2 = new MyThread1();
		MyThread1 t3 = new MyThread1();
		MyThread1 t4 = new MyThread1();
		MyThread1 t5 = new MyThread1();

		t1.setName("小A");
		t2.setName("小QQ");
		t3.setName("小哈哈");
		t4.setName("小黃黃");
		t5.setName("小丹丹");

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}

class MyThread1 extends Thread {

	// 共享數據
	// 100塊，分成了3個包
	static int money = 100;
	static int count = 3;

	// 最小的中獎金額
	static final int MIN = 1;

	@Override
	public void run() {
		// 同步代碼塊
		synchronized (MyThread1.class) {
			if (count == 0) {
				// 判斷，共享數據是否到了末尾（已經到末尾）
				System.out.println(getName() + "沒有搶到紅包！");
			} else {
				// 判斷，共享數據是否到了末尾（沒有到末尾）
				// 定義一個變量，表示中獎的金額
				int prize = 0;
				if (count == 1) {
					// 表示此時是最後一個紅包
					// 就無需隨機，剩餘所有的錢都是中獎金額
					prize = money;
				} else {
					// 表示第一次，第二次（隨機）
					Random r = new Random();
					// 100 元 3個包
					// 第一個紅包上限：98
					// 100 - (3-1) * 1
					int bounds = money - (count - 1) * MIN;
					prize = (r.nextInt(bounds)) + 1;
				}
				// 從money當中，去掉當前中獎的金額
				money = money - prize;
				// 紅包的個數-1
				count--;
				// 本次紅包的信息進行打印
				System.out.println(getName() + "搶到了" + prize + "元");
			}
		}
	}
}