import java.util.ArrayList;
import java.util.Collections;

public class a05Lottery1 {
	/*
	 * 有一個抽獎池，該抽獎池中存放了獎勵的金額，該抽獎池中的獎項為
	 * {10,5,20,50,100,200,500,800,2,80,300,700};
	 * 創建兩個抽獎人員(線程)設置線程名稱分別為"抽獎員1"，"抽獎員2"
	 * 隨機從抽獎池中獲取獎項元素並打印在控制台上，格式如下:
	 * 每次抽出一個獎項就打印一個(隨機)
	 * 抽獎員1 又抽出了一個 10 元大獎
	 * 抽獎員2 又抽出了一個 700 元大獎
	 * ...
	 */
	public static void main(String[] args) {
		// 創建獎池
		ArrayList<Integer> list = new ArrayList<>();
		Collections.addAll(list, 10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700);

		MyThread2 t1 = new MyThread2(list);
		MyThread2 t2 = new MyThread2(list);

		t1.setName("抽獎員1");
		t2.setName("抽獎員2");

		t1.start();
		t2.start();
	}
}

class MyThread2 extends Thread {

	ArrayList<Integer> list;

	public MyThread2(ArrayList<Integer> list) {
		this.list = list;
	}

	@Override
	public void run() {
		// 1.循環
		while (true) {
			// 2.同步代碼塊
			synchronized (MyThread2.class) {
				// 3.判斷
				if (list.size() == 0) {
					break;
				} else {
					// 4.判斷，繼續抽獎
					Collections.shuffle(list);
					int prize = list.remove(0);
					System.out.println(getName() + "又抽出了一個" + prize + "元大獎");
				}
			}
			// 在代碼塊外睡一下，可以讓線程爭奪概率平均一些
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
