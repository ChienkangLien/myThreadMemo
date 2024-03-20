
public class a11Deadlock {
	public static void main(String[] args) {
		MyThread7 t1 = new MyThread7();
		MyThread7 t2 = new MyThread7();

		t1.setName("t1");
		t2.setName("t2");

		t1.start();
		t2.start();
	}
}

class MyThread7 extends Thread {
	static Object objA = new Object();
	static Object objB = new Object();

	@Override
	public void run() {
		while (true) {
			if ("t1".equals(getName())) {
				synchronized (objA) {
					System.out.println("t1拿到了A鎖，準備拿B鎖");
					synchronized (objB) {
						System.out.println("t1拿到了B鎖，執行完一輪");
					}
				}
			} else if ("t2".equals(getName())) {
				synchronized (objB) {
					System.out.println("t2拿到了B鎖，準備拿A鎖");
					synchronized (objA) {
						System.out.println("t2拿到了A鎖，執行完一輪");
					}
				}
			}
		}
	}
}
