import java.util.concurrent.locks.ReentrantLock;

public class a10SellTicketsLock {
	public static void main(String[] args) {
		MyThread6 t1 = new MyThread6();
		MyThread6 t2 = new MyThread6();
		MyThread6 t3 = new MyThread6();

		t1.setName("���f1");
		t2.setName("���f2");
		t3.setName("���f3");

		t1.start();
		t2.start();
		t3.start();
	}
}

class MyThread6 extends Thread {
	// static�B��ܳo�����Ҧ�������A�@��ticket�ݩ�
	static int ticket = 0;

	static private ReentrantLock lock = new ReentrantLock();

	@Override
	public void run() {
		while (true) {
			lock.lock();
			
			// �ݨϥ�try/finally�H�Kbreak�X�h�S����y�����`��
			try {
				if (ticket < 100) {
					ticket++;
					System.out.println(getName() + "���b���" + ticket + "�i��");
				} else {
					break;
				}
			} finally {
				lock.unlock();
			}
		}
	}
}
