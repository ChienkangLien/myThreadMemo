
public class a05Daemon {
	/*
	 * ��D�u�@�u�{�����F�A���u�@�u�{�]�S���s�b�����n
	 */
	public static void main(String[] args) {
		MyThread1 t1 = new MyThread1();
		MyThread2 t2 = new MyThread2();

		t1.setName("�D�u�@");
		t2.setName("�u�@");

		// �q�{false
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
