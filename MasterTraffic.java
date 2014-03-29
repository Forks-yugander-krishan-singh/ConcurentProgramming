/**
 * MasterTraffic.java
 */

/**
 * MasterTraffic extends Thread is the main 
 * controller of the  traffic.
 * It will initiate the process. 
 * 
 * @author Siddesh Pillai
 * @author Ajinkya Kale
 * 
 */
public class MasterTraffic extends Thread {
	/*
	 * A boolean flag
	 */
	private static boolean flag = true;
	/**
	 * The run method
	 */
	public void run(){
		new Traffic(1, "Green").start();
		new Traffic(2, "Red").start();
		new Traffic(3, "Green").start();
		new Traffic(4, "Red").start();
		flag = false;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]){
		/*
		 * Master Traffic object
		 */
		MasterTraffic tra = new MasterTraffic();
		tra.start();
		while (flag){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Traffic.print_signal();
		Traffic.control();
	}
}
