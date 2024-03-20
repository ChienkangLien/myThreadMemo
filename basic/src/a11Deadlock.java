
public class a11Deadlock {
	public static void main(String[] args) {
		MyThread7 t1 = new MyThread7();
		MyThread7 t2 = new MyThread7();

		t1.setName("t1");
		t2.setName("t2");

		t1.start();
		t2.start();
	}
}

class MyThread7 extends Thread {
	static Object objA = new Object();
	static Object objB = new Object();

	@Override
	public void run() {
		while (true) {
			if ("t1".equals(getName())) {
				synchronized (objA) {
					System.out.println("t1����FA��A�ǳƮ�B��");
					synchronized (objB) {
						System.out.println("t1����FB��A���槹�@��");
					}
				}
			} else if ("t2".equals(getName())) {
				synchronized (objB) {
					System.out.println("t2����FB��A�ǳƮ�A��");
					synchronized (objA) {
						System.out.println("t2����FA��A���槹�@��");
					}
				}
			}
		}
	}
}
