
public class a09SellTicketsMethod {
	public static void main(String[] args) {
		MyRunnable1 mr = new MyRunnable1();

		Thread t1 = new Thread(mr);
		Thread t2 = new Thread(mr);
		Thread t3 = new Thread(mr);

		t1.setName("���f1");
		t2.setName("���f2");
		t3.setName("���f3");

		t1.start();
		t2.start();
		t3.start();
	}
}

class MyRunnable1 implements Runnable {
	// Runnable�@���ѼƶǤJThread�A�u�|�Ыؤ@���A���|�@�ɼƾڡA�����[�Wstatic
	int ticket = 0;

	@Override
	public void run() {
		while (true) {
			if (method()) {
				break;
			}
		}
	}

	// �D�R�A�A�ꪫ��this�A�]�N�O�ЫإX�Ӫ�MyRunnable1
	private synchronized boolean method() {
		if (ticket < 100) {
			ticket++;
			System.out.println(Thread.currentThread().getName() + "���b���" + ticket + "�i��");
			return false;
		} else {
			return true;
		}
	}
}
