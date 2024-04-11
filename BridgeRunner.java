/**
 * Runs all threads
 */

public class BridgeRunner {

	public static void main(String[] args) {

		// TODO - check command line inputs
		int maxCars = Integer.parseInt(args[0]);
		int numCars = Integer.parseInt(args[1]);
		if(numCars <= 0 || maxCars <= 0){
			System.out.println("Error: bridge limit and/or num cars must be positive.");
			return;
		}
		// TODO - instantiate the bridge
		OneLaneBridge bridge = new OneLaneBridge(maxCars);
		// TODO - allocate space for threads
		Thread[] cars = new Thread[numCars];
		// TODO - start then join the threads
		//create and start threads
		for(int i = 0; i < numCars; i++){
			cars[i] = new Thread(new Car(i, bridge));
			cars[i].start();
		}
		//join threads
		for(int i = 0; i < numCars; i++){
			try{
				cars[i].join();
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		System.out.println("All cars have crossed!!");
	}

}