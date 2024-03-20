
public class a07SellTicketsDanger {
	public static void main(String[] args) {
		MyThread4 t1 = new MyThread4();
		MyThread4 t2 = new MyThread4();
		MyThread4 t3 = new MyThread4();

		t1.setName("���f1");
		t2.setName("���f2");
		t3.setName("���f3");

		t1.start();
		t2.start();
		t3.start();
	}
}

class MyThread4 extends Thread {
	// static�B��ܳo�����Ҧ�������A�@��ticket�ݩ�
	static int ticket = 0;

	@Override
	public void run() {
		while (true) {
			if (ticket < 100) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ticket++;
				System.out.println(getName() + "���b���" + ticket + "�i��");
			} else {
				break;
			}
		}
	}
}
