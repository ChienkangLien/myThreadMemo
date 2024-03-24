
public class a022CreateThread22 {
	/*
	 * 匿名類方式啟動
	 */
	public static void main(String[] args) {

		Runnable mr = new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 100; i++) {
					Thread t = Thread.currentThread();
					System.out.println(t.getName() + "-匿名-" + i);
				}
			}
		};

		Thread t1 = new Thread(mr);
		t1.setName("t1");
		t1.start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 100; i++) {
					Thread t = Thread.currentThread();
					System.out.println(t.getName() + "-匿名-" + i);
				}
			}
		}, "t2").start();

		new Thread(() -> {
			for (int i = 1; i <= 100; i++) {
				Thread t = Thread.currentThread();
				System.out.println(t.getName() + "-匿名-" + i);
			}
		}, "t3").start();
	}
}