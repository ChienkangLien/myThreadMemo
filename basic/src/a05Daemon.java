
public class a05Daemon {
	/*
	 * 當非守護線程結束了，那守護線程也沒有存在的必要
	 */
	public static void main(String[] args) {
		MyThread1 t1 = new MyThread1();
		MyThread2 t2 = new MyThread2();

		t1.setName("非守護");
		t2.setName("守護");

		// 默認false
		t2.setDaemon(true);
		
		t1.start();
		t2.start();
	}
}

class MyThread1 extends Thread {
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(getName() + "-" + i);
		}
	}
}
class MyThread2 extends Thread {
	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			System.out.println(getName() + "-" + i);
		}
	}
}
