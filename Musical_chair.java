/**
 * Musical_chair.java
 */
import java.util.ArrayList;

/**
 * Musical_chair class extends Thread plays the musical chair game.
 * 
 * @author Siddesh Pillai
 * 
 * 
 */
public class Musical_chair extends Thread {

	/**
	 * This is an object which is shared amongst all the objects of the class.
	 */
	private static Object a = new Object();
	/**
	 * Index is to identify the person
	 */
	private int index;
	/**
	 * Check is to distinguish which one is out
	 */
	private boolean check = false;
	/**
	 * seating keeps a track of how many seats
	 */
	private static int seating = 0;
	/**
	 * occupied keeps a track of how many seats needed to be occupied
	 */
	private static int occupied = 0;
	/**
	 * This is array of persons who playing in the game
	 */
	private static ArrayList<Musical_chair> chairs = new ArrayList<Musical_chair>();
	/**
	 * This is used to rehash the array of person playing the game.
	 */
	public static ArrayList<Musical_chair> chairs2 = new ArrayList<Musical_chair>();

	/**
	 * Print the seating arrangements
	 * 
	 * @param x
	 */
	private void print_seats() {

		occupied = 0;
		if (seating > 2) {
			seating = seating - 1;
			for (int count = 0; count < chairs.size(); count++) {
				if (chairs.get(count).check == false) {
					System.out.println("\t" + (chairs.get(count).index + 1)
							+ " is out");
				}
			}
			System.out.println();
			System.out.println(seating + " are Playing..");
			System.out.print("===" + "  ");
			for (int count = 0; count < chairs.size(); count++) {
				if (chairs.get(count).check == true) {

					System.out.print((chairs.get(count).index + 1) + "  ");
					chairs2.add(chairs.get(count));
				}
			}

			System.out.println();
			chairs.clear();
		} else {
			System.out.println("Ok, and the winner is: "
					+ (chairs.get(0).index + 1));
			System.exit(0);
		}
	}

	/**
	 * To String method prints the person
	 */
	public String toString() {
		return "Person " + (this.index);
	}

	/**
	 * This is constructor for the class
	 * 
	 * @param index
	 *            assigns to player
	 */
	public Musical_chair(int index) {
		this.index = index;
		this.check = true;
	}

	public Musical_chair() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * The run method
	 */
	public void run() {
		/**
		 * Synchronized on Object
		 */
		synchronized (a) {

			if (this.check == true && occupied < seating - 1) {
				chairs.add(this);
				occupied++;
			} else {
				this.check = false;
				chairs.add(this);
				occupied++;
			}
		}
	}

	/**
	 * The main method Read from the command line number of players
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		/**
		 * Number is the number of players
		 */
		String number = args[0];
		/**
		 * Object of the class created only to call the 
		 * print function since it is not static
		 */
		Musical_chair abc = new Musical_chair();

		/**
		 * Parse the string and stores in players the number of players playing
		 */
		int players = Integer.parseInt(number);
		/**
		 * Assigning players to the seating
		 */
		seating = players;
		System.out.println(seating + " are Playing");

		for (int i = 0; i < seating; i++) {
			new Musical_chair(i).start();
		}
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		abc.print_seats();
		System.out.println();

		while (seating > 1) {
			try {
				sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int count2 = 0; count2 < chairs2.size(); count2++) {
				new Musical_chair(chairs2.get(count2).index).start();
			}
			chairs2.clear();
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			abc.print_seats();
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (seating == 1) {
			abc.print_seats();
		}
	}
}