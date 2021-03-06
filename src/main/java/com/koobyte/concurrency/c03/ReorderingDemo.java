package com.koobyte.concurrency.c03;

/**
 * Created by sun on 2022/1/26.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class ReorderingDemo {
	//~ Static fields/constants/initializer

	private static int x = 0, y = 0;
	private static int a = 0, b = 0;

	//~ Instance fields


	//~ Constructors


	//~ Methods

	public static void main(String[] args) throws InterruptedException {
		int i = 0;
		while (true) {
			i++;
			x = 0;
			y = 0;
			a = 0;
			b = 0;
			Thread t1 = new Thread(() -> {
				a = 1;
				x = b;
			});
			Thread t2 = new Thread(() -> {
				b = 1;
				y = a;
			});
			t1.start();
			t2.start();
			t1.join();
			t2.join();
			String result = "i = " + i + ", x = " + x + ", y = " + y;
			if (x == 0 && y == 0) {
				System.out.println(result);
				break;
			}
		}
	}
}
