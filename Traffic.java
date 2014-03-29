/**
 * Traffic.java
 */
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Traffic extends Thread is the slave of the master
 * controller of the MasterTraffic. 
 * It is responsible to generate the patterns in which 
 * signals can be played. 
 * 
 * @author Siddesh Pillai
 * @author Ajinkya Kale
 * 
 */
public class Traffic extends Thread {
	/*
	 * To keep a traffic of signal post
	 */
	private Integer id;
	/*
	 * Is the color of the signal
	 */
	private String signal;
	/*
	 * Used a Hashmap to store the signal and their 
	 * states in a pair
	 */
	public static HashMap<Integer, String> col = new HashMap<Integer, String>();

	/**
	 * Default Constructor
	 */
	public Traffic() {

	}

	/**
	 * Parametrized constructor
	 * @param id
	 * @param color
	 */
	public Traffic(int id, String color) {
		this.id = id;
		this.signal = color;
	}

	/**
	 * The run method for the child/slave
	 */
	public void run() {
		/*
		 * Synchronized on col which is the hashmap
		 */
		synchronized (col) {
			col.put(this.id, this.signal);
		}
	}

	/**
	 * Its a print routine 
	 */
	public static void print_signal() {
		System.out.println("-------------------");
		System.out.println("Signal Post 1 " + col.get(1));
		System.out.println("Signal Post 2 " + col.get(2));
		System.out.println("Signal Post 3 " + col.get(3));
		System.out.println("Signal Post 4 " + col.get(4));
	}

	/**
	 * The control method is responsible to
	 * check the pattern in which the signal will
	 * be displayed
	 */
	public static void control() {
		Traffic b = new Traffic();
		while (true) {
			if (col.get(1).equals("Green") && col.get(3).equals("Green")
					&& col.get(2).equals("Red") && col.get(4).equals("Red")) {
				col.clear();
				new Traffic(1, "Yellow").start();
				new Traffic(2, "Red").start();
				new Traffic(3, "Yellow").start();
				new Traffic(4, "Red").start();
				try {
					b.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println();
				print_signal();
				System.out.println();
			}
			if (col.get(1).equals("Yellow") && col.get(3).equals("Yellow")
					&& col.get(2).equals("Red") && col.get(4).equals("Red")) {
				col.clear();
				new Traffic(1, "Red").start();
				new Traffic(2, "Green").start();
				new Traffic(3, "Red").start();
				new Traffic(4, "Green").start();
				try {
					b.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println();
				print_signal();
				System.out.println();
			}

			if (col.get(1).equals("Red") && col.get(3).equals("Red")
					&& col.get(2).equals("Green") && col.get(4).equals("Green")) {
				col.clear();
				new Traffic(1, "Red").start();
				new Traffic(2, "Yellow").start();
				new Traffic(3, "Red").start();
				new Traffic(4, "Yellow").start();
				try {
					b.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println();
				print_signal();
				System.out.println();
			}
			if (col.get(1).equals("Red") && col.get(3).equals("Red")
					&& col.get(2).equals("Yellow")
					&& col.get(4).equals("Yellow")) {
				col.clear();
				new Traffic(1, "Green").start();
				new Traffic(2, "Red").start();
				new Traffic(3, "Green").start();
				new Traffic(4, "Red").start();
				try {
					b.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println();
				print_signal();
				System.out.println();
			}
		}
	}
}
