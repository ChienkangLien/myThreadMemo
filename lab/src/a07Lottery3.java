import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class a07Lottery3 {
	/*
	 * 在上一題基礎上繼續完成如下需求：
	 * 印出誰產生了最大獎項,該獎項金額為多
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 創建獎池
		ArrayList<Integer> list = new ArrayList<>();
		Collections.addAll(list, 10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700);

		MyCallable mc = new MyCallable(list);

		// 線程一
		FutureTask<Integer> ft1 = new FutureTask<>(mc);
		// 線程二
		FutureTask<Integer> ft2 = new FutureTask<>(mc);

		Thread t1 = new Thread(ft1);
		Thread t2 = new Thread(ft2);

		t1.setName("抽獎員1");
		t2.setName("抽獎員2");

		t1.start();
		t2.start();
		Integer max1 = ft1.get();
		Integer max2 = ft2.get();

		if (max1 == null) {
			System.out.println("在此次抽獎過程中,抽獎員2中產生了最大獎項,該獎項金額為" + max2 + "元");
		} else if (max2 == null) {
			System.out.println("在此次抽獎過程中,抽獎員1中產生了最大獎項,該獎項金額為" + max1 + "元");
		} else if (max1 > max2) {
			System.out.println("在此次抽獎過程中,抽獎員1中產生了最大獎項,該獎項金額為" + max1 + "元");
		} else if (max1 < max2) {
			System.out.println("在此次抽獎過程中,抽獎員2中產生了最大獎項,該獎項金額為" + max2 + "元");
		} else {
			System.out.println("兩者的最大獎項是一樣的,為" + max1 + "元");
		}
	}
}

class MyCallable implements Callable<Integer> {

	ArrayList<Integer> list;

	public MyCallable(ArrayList<Integer> list) {
		this.list = list;
	}

	@Override
	public Integer call() throws Exception {

		// 每個線程會有自己的boxList，互不干擾
		ArrayList<Integer> boxList = new ArrayList<>();

		// 1.循環
		while (true) {
			// 2.同步代碼塊
			synchronized (MyCallable.class) {
				// 3.判斷
				if (list.size() == 0) {
					printResult(boxList);
					break;
				} else {
					// 4.判斷，繼續抽獎
					Collections.shuffle(list);
					int prize = list.remove(0);
					boxList.add(prize);
				}
			}
			// 在代碼塊外睡一下，可以讓線程爭奪概率平均一些
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 把集合中的最大值返回
		if (boxList.size() == 0) {
			return null;
		} else {
			return Collections.max(boxList);
		}
	}

	private void printResult(ArrayList<Integer> list) {
		System.out.println(Thread.currentThread().getName() + list);

		int maxPrize = list.stream().mapToInt(Integer::intValue).max().orElse(0);
		int totalAmount = list.stream().mapToInt(Integer::intValue).sum();

		System.out.println("最高獎項: " + maxPrize);
		System.out.println("總計額: " + totalAmount);
	}

}
