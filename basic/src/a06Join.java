
public class a06Join {
	public static void main(String[] args) throws InterruptedException {
		MyThread3 t1 = new MyThread3();
		t1.setName("t1");
		t1.start();
		
		// ��ܧ�t�o�ӽu�{(t1)�A���J���e�u�{(main)���e�F
		// �b��t1�@�w�|�����槹�~����main
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
