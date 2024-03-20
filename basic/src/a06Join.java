
public class a06Join {
	public static void main(String[] args) throws InterruptedException {
		MyThread3 t1 = new MyThread3();
		t1.setName("t1");
		t1.start();
		
		// 表示把t這個線程(t1)，插入到當前線程(main)之前；
		// 在此t1一定會先執行完才執行main
		t1.join();
		
		for (int i = 1; i <= 100; i++) {
			System.out.println(Thread.currentThread().getName() + "-" + i);
		}
	}
}

class MyThread3 extends Thread {
	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			System.out.println(getName() + "-" + i);
		}
	}
}
