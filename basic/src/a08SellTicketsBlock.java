
public class a08SellTicketsBlock {
	public static void main(String[] args) {
		MyThread5 t1 = new MyThread5();
		MyThread5 t2 = new MyThread5();
		MyThread5 t3 = new MyThread5();

		t1.setName("���f1");
		t2.setName("���f2");
		t3.setName("���f3");

		t1.start();
		t2.start();
		t3.start();
	}
}

class MyThread5 extends Thread {
	// static�B��ܳo�����Ҧ�������A�@��ticket�ݩ�
	static int ticket = 0;

	// �ꪫ��A�@�w�n�O�ߤ@���A�[�Wstatic
	static Object obj = new Object();

	@Override
	public void run() {
		while (true) {
			
//			synchronized (obj) {
			synchronized (MyThread5.class) { // �άO�ϥ�MyThread5�������r�`�X
				if (ticket < 100) {
					ticket++;
					System.out.println(getName() + "���b���" + ticket + "�i��");
				} else {
					break;
				}
			}
		}
	}
}
