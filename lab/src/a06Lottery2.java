import java.util.ArrayList;
import java.util.Collections;

public class a06Lottery2 {
	/*
	 * 在上一題基礎上繼續完成如下需求：
	 * 每次抽的過程中，不打印，抽完時一次性打印(隨機)
	 * 印出個別產生了多少獎項、最高獎項、總計
	 */
	public static void main(String[] args) {
		// 創建獎池
		ArrayList<Integer> list = new ArrayList<>();
		Collections.addAll(list, 10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700);

		MyThread3 t1 = new MyThread3(list);
		MyThread3 t2 = new MyThread3(list);

		t1.setName("抽獎員1");
		t2.setName("抽獎員2");

		t1.start();
		t2.start();
	}
}

class MyThread3 extends Thread {

	ArrayList<Integer> list;

	// 如果線程一多，逐一添加屬性不實際
//	// 線程一
//	static ArrayList<Integer> list1 = new ArrayList<>();
//	// 線程二
//	static ArrayList<Integer> list2 = new ArrayList<>();

	public MyThread3(ArrayList<Integer> list) {
		this.list = list;
	}

	@Override
	public void run() {
		
		// 每個線程會有自己的boxList，互不干擾
		ArrayList<Integer> boxList = new ArrayList<>();
		
		// 1.循環
		while (true) {
			// 2.同步代碼塊
			synchronized (MyThread3.class) {
				// 3.判斷
				if (list.size() == 0) {
//					if ("抽獎員1".equals(getName())) {
//						printResult(list1);
//					} else {
//						printResult(list2);
//					}
					
					printResult(boxList);
					break;
				} else {
					// 4.判斷，繼續抽獎
					Collections.shuffle(list);
					int prize = list.remove(0);
//					if ("抽獎員1".equals(getName())) {
//						list1.add(prize);
//					} else {
//						list2.add(prize);
//					}
					
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
	}
	
	private void printResult(ArrayList<Integer> list) {
		System.out.println(getName() + list);
		
		int maxPrize = list.stream().mapToInt(Integer::intValue).max().orElse(0);
        int totalAmount = list.stream().mapToInt(Integer::intValue).sum();

        System.out.println("最高獎項: " + maxPrize);
        System.out.println("總計額: " + totalAmount);
	}
}
