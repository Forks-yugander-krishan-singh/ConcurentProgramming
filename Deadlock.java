/**
 * Deadlock.java
 */

/**
 * Deadlock class extends Thread implements the program where deadlock occurs
 * with respect to resources.
 * 
 * @author Siddesh Pillai
 * 
 * 
 */
public class Deadlock extends Thread {
	/**
	 * This is the resource which acts as a lock
	 */
	public static Object Lock1 = new Object();
	/**
	 * This is the resource which acts as a lock
	 */
	public static Object Lock2 = new Object();
	/**
	 * This is the common resource
	 */
	private static Object shared = new Object();

	/**
	 * This keeps the index of the thread generated
	 */
	private int index;
	/**
	 * Flag
	 */
	private static boolean flag = false;

	/**
	 * Parameterized constructor initializes the index
	 * 
	 * @param index
	 */
	public Deadlock(int index) {
		this.index = index;
	}

	/**
	 * Synchronized method 1
	 */
	public synchronized void method1() {

		System.out.println("Thread " + this.index + " in method 1 ");

		synchronized (Lock1) {
			System.out.println("Thread " + this.index + " aquired lock1 ");
			synchronized (shared) {
				if (!flag) {
					System.out.println("Thread " + this.index
							+ " acquired the shared resource inside method 1");
					flag = true;
					try {
						shared.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("Thread " + this.index
							+ " acquired the shared resource inside method 1");
					shared.notify();
				}

				synchronized (Lock2) {
					System.out.println("Thread " + this.index
							+ " aquired lock2 ");

				}
			}
		}
	}

	/**
	 * Synchronized method 2
	 */
	public synchronized void method2() {

		System.out.println("Thread " + this.index + " in method 2 ");

		synchronized (Lock2) {
			System.out.println("Thread " + this.index + " aquired lock2 ");
			synchronized (shared) {
				if (!flag) {
					flag = true;
					System.out.println("Thread " + this.index
							+ " acquired the shared resource inside method 2");
					try {
						
						shared.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("Thread " + this.index
							+ " acquired the shared resource inside method 2");
					shared.notify();
				}
			}
			synchronized (Lock1) {
				System.out.println("Thread " + this.index + " aquired lock1 ");
			}
		}
	}

	/**
	 * The run method
	 */
	public void run() {
		if (index == 1) {
			method1();
		} else {
			method2();
		}
	}

	/**
	 * The main method
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		/**
		 * Object of the class
		 */
		new Deadlock(1).start();
		/**
		 * Object of the class
		 */
		new Deadlock(2).start();
	}
}