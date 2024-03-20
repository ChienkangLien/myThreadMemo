import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class a03CreateThread3 {
	/*
	 * �w�q�@����MyCallable��{Callable����
	 * �bMyCallable�������gcall()��k
	 * �Ы�MyCallable��������
	 * �Ы�Future����{��FutureTask����A��MyCallable����@���c�y��k���Ѽ�
	 * �Ы�Thread��������A��FutureTask����@���c�y��k���Ѽ�
	 * �Ұʰ����
	 * �A�ե�get��k�A�N�i�H���������������᪺���G�C
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		MyCallable mc = new MyCallable();

		FutureTask<Integer> ft = new FutureTask<Integer>(mc);

		Thread t1 = new Thread(ft);
		t1.start();

		System.out.println(ft.get());
	}
}

class MyCallable implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		// �D1~100���M
		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			sum = sum + i;
		}
		return sum;
	}
}
