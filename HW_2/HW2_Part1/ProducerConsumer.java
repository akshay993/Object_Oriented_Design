//Created By: Akshay Chopra

// HW2:  Outline of Producer-Consumer:

import java.util.Random;

public class ProducerConsumer {
	public static void main(String[] args) {
		DropBox db = new DropBox(5);
		Producer p = new Producer(db);
		Consumer c = new Consumer(db);
		p.start();
		c.start();

	}
}

class Producer extends Thread {
	private DropBox db;

	public Producer(DropBox db) {
		this.db = db;
	}

	public void run() {
		for (int i = 0; i < 20; i++) {
			db.put(i);
			try {
				Thread.sleep(new Random().nextInt(100));
			} catch (Exception e) {
			}
		}
	}
}

class Consumer extends Thread {
	private DropBox db;
	int value;

	public Consumer(DropBox db) {
		this.db = db;
	}

	public void run() {
		while (true) {
			value = db.get();
			try {
				Thread.sleep(new Random().nextInt(500));
			} catch (Exception e) {
			}
		}
	}
}

class DropBox {
	// to be filled in by you
	private int data[];
	private int p = 0;
	private int g = 0;
	private int count = 0;

	public DropBox(int n) {
		data = new int[n];
	}

	public boolean empty() {
		if (count == 0)
			return true;
		else
			return false;
	}

	public boolean full() {
		if (count == 5)
			return true;
		else
			return false;
	}

	public synchronized int get() {
		int d = 0;

		try {
			while (empty())
				wait();

			if (g == 5)
				g = 0;
			d = data[g];
			System.out.println("Value get: "+d);

			g++;

			count--;

			notify();

		} catch (InterruptedException e) {

		}

		return d;
	}

	public synchronized void put(int v) {
		try {

			while (full())
				wait();

			if (p == 5)
				p = 0;
			data[p] = v;
			System.out.println("Value put: "+data[p]);

			p++;

			count++;

			notify();

		} catch (InterruptedException e) {

		}

	}

}
