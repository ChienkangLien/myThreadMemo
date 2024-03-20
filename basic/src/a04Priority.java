
public class a04Priority {
	public static void main(String[] args) {
		MyPriorityRunnable mr = new MyPriorityRunnable();

		Thread t1 = new Thread(mr, "t1");
		Thread t2 = new Thread(mr, "t2");
		
		// 默認優先級5，最小1、最大10(優先級不是絕對、只是大概率)
		System.out.println(t1.getPriority());
		System.out.println(t2.getPriority());
		
		t1.setPriority(1);
		t2.setPriority(Thread.MAX_PRIORITY);

		t1.start();
		t2.start();
	}
}

class MyPriorityRunnable implements Runnable {
	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			Thread t = Thread.currentThread();
			System.out.println(t.getName() + "-" + i);
			
			// 表示出讓當前CPU的執行權，這也是大概率、不是一定
			Thread.yield();
		}
	}
}
