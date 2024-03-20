
public class a04Priority {
	public static void main(String[] args) {
		MyPriorityRunnable mr = new MyPriorityRunnable();

		Thread t1 = new Thread(mr, "t1");
		Thread t2 = new Thread(mr, "t2");
		
		// �q�{�u����5�A�̤p1�B�̤j10(�u���Ť��O����B�u�O�j���v)
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
			
			// ��ܥX����eCPU�������v�A�o�]�O�j���v�B���O�@�w
			Thread.yield();
		}
	}
}
