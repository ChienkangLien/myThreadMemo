
public class a01CreateThread1 {
	/*
	 * �w�q�@����MyThread�~��Thread��
	 * �bMyThread�������grun()��k
	 * �Ы�MyThread��������
	 * �Ұʰ����
	 */
	public static void main(String[] args) {
		MyThread t1 = new MyThread();
		MyThread t2 = new MyThread("t2");

		// �p�G���]�m�W�r�A�q�{�W�r�OThread-X(�q0�}�l)
		// �]�i�~��Thread�䤤���c�y��k�ӶǤJ
		t1.setName("t1");

		t1.start();
		t2.start();
	}
}

class MyThread extends Thread {
	public MyThread() {
		super();
	}

	public MyThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			System.out.println(getName() + "-" + i);
		}
	}
}
