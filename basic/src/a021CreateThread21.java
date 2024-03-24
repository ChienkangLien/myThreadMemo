
public class a021CreateThread21 {
	/*
	 * �w�q�@����MyRunnable��{Runnable����
	 * �bMyRunnable�������grun()��k
	 * �Ы�MyRunnable��������
	 * �Ы�Thread��������A��MyRunnable����@���c�y��k���Ѽ�
	 * �Ұʰ����
	 */
	public static void main(String[] args) throws InterruptedException {
		// ��e��main����
		System.out.println(Thread.currentThread().getName());
		
		// �ѷ�emain�ӥ�v
		Thread.sleep(3000);
		
		MyRunnable mr = new MyRunnable();

		Thread t1 = new Thread(mr);
		Thread t2 = new Thread(mr, "t2");

		t1.setName("t1");

		t1.start();
		t2.start();
	}
}

class MyRunnable implements Runnable {
	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			Thread t = Thread.currentThread();
			System.out.println(t.getName() + "-" + i);
			try {
				// �ѷ�e������ӥ�v
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
