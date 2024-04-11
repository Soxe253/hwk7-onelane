public class OneLaneBridge extends Bridge{
    private int maxCars;
    private Object lock = new Object();

     public OneLaneBridge(int maxCars){
        super();
        this.maxCars = maxCars;//set max cars
     }

    public void arrive(Car car) throws InterruptedException{
        synchronized(lock){
            while(car.getDirection() != direction || bridge.size() == maxCars){//check direction and cars on bridge
                try{ 
                    lock.wait();
                    if(bridge.size() == 0){//reset bridge direction when no cars
                        direction = car.getDirection();
                    }
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            car.setEntryTime(currentTime);
            bridge.add(car);//increment and set time and add car
            System.out.println("Bridge dir("+direction+"): "+ bridge.toString());
            currentTime++;
        }
    }

    public void exit(Car car) throws InterruptedException{
        synchronized(lock){
            while(bridge.get(0) != car){//check if the car that wants to exit is at the front
                try{
                    lock.wait();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            bridge.remove(0);//take car off
            System.out.println("Bridge dir("+direction+"): "+ bridge.toString());
            lock.notifyAll();//allow everyone else to move
        }
    }
}