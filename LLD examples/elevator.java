import java.util.*;

// 1. The Strategy (The "Brain")
interface NextStopStrategy {
    Integer determineNextStop(int currentFloor, boolean movingUp, TreeSet<Integer> upQueue, TreeSet<Integer> downQueue);
}

class LookStrategy implements NextStopStrategy {
    @Override
    public Integer determineNextStop(int currentFloor, boolean movingUp, TreeSet<Integer> upQueue, TreeSet<Integer> downQueue) {
        if (movingUp) {
            // 1. Look for the next floor above us
            Integer next = upQueue.ceiling(currentFloor);
            if (next != null) return next;
            
            // 2. If no more floors above, pick the HIGHEST floor in the down queue to start the trip back
            return downQueue.isEmpty() ? null : downQueue.last();
        } else {
            // 1. Look for the next floor below us
            Integer next = downQueue.floor(currentFloor);
            if (next != null) return next;
            
            // 2. If no more floors below, pick the LOWEST floor in the up queue
            return upQueue.isEmpty() ? null : upQueue.first();
        }
    }
}

class ShortestSeekStrategy implements NextStopStrategy {
    @Override
    public Integer determineNextStop(int currentFloor, boolean movingUp, TreeSet<Integer> upQueue, TreeSet<Integer> downQueue) {
        // Combine all requests to find the absolute closest
        TreeSet<Integer> allRequests = new TreeSet<>(upQueue);
        allRequests.addAll(downQueue);

        if (allRequests.isEmpty()) return null;

        Integer higher = allRequests.ceiling(currentFloor);
        Integer lower = allRequests.floor(currentFloor);

        if (higher == null) return lower;
        if (lower == null) return higher;

        // Choose the one with the minimum absolute distance
        return (higher - currentFloor) < (currentFloor - lower) ? higher : lower;
    }
}

interface ElevatorObserver {
    void update(int floor, String direction);
}
class FloorDisplay implements ElevatorObserver {
    private int floorNumber;

    public FloorDisplay(int floorNumber) { this.floorNumber = floorNumber; }

    @Override
    public void update(int currentFloor, String direction) {
        System.out.println("[Display Floor " + floorNumber + "]: Elevator is at " + 
                           currentFloor + " going " + direction);
    }
}

class InternalDisplay implements ElevatorObserver {
    @Override
    public void update(int currentFloor, String direction) {
        System.out.println("[Inside Elevator]: Currently at " + currentFloor + " (" + direction + ")");
    }
}

// 2. The Elevator
class Elevator {
    private int currentFloor = 0;
    private boolean movingUp = true;
    private final List<ElevatorObserver> observers = new ArrayList<>(); // Observer List
    private final TreeSet<Integer> upQueue = new TreeSet<>();
    private final TreeSet<Integer> downQueue = new TreeSet<>();
    private NextStopStrategy strategy;

    public Elevator(NextStopStrategy strategy) {
        this.strategy = strategy;
    }

    // Observer Methods
    public void addObserver(ElevatorObserver observer) { observers.add(observer); }
    
    private void notifyObservers() {
        String dir = movingUp ? "UP" : "DOWN";
        for (ElevatorObserver obs : observers) {
            obs.update(currentFloor, dir);
        }
    }

    // Helper for the test case
    public void setCurrentFloor(int floor, boolean movingUp) {
        this.currentFloor = floor;
        this.movingUp = movingUp;
    }

    public void addRequest(int floor) {
        if (floor > currentFloor) upQueue.add(floor);
        else if (floor < currentFloor) downQueue.add(floor);
    }

    public void run() {
        while (!upQueue.isEmpty() || !downQueue.isEmpty()) {
            Integer nextStop = strategy.determineNextStop(currentFloor, movingUp, upQueue, downQueue);
            if (nextStop == null) break;
            
            movingUp = nextStop > currentFloor;
            currentFloor = nextStop;

            notifyObservers(); // Notify everyone!
            
            upQueue.remove(currentFloor);
            downQueue.remove(currentFloor);
        }
        System.out.println("All stops reached.\n");
    }
}

// 3. The Interview Demo
public class Main {
    public static void main(String[] args) {
        /* DESIGN PATTERNS

        1) State Pattern (Behavioral)
        Where: The ElevatorState interface and classes like IdleState and MovingState. 
        Why: It manages the elevator's behavior based on its status.
        
        2. Strategy Pattern (Behavioral)
        Where: The NextStopStrategy interface and the LookStrategy vs. ShortestSeekStrategy classes. 
        Why: It separates what the elevator does (move to a floor) from how it decides which floor is next (the algorithm).

         3. Observer Object Pattern (Behavioral)
        Where: The internal and external displays. 
        Why: We are having internal and external displays updated on floor change 
        */
        System.out.println("--- LOOK STRATEGY ---");
        // Logic: "I am moving UP. I will finish all UP requests (Floor 7) 
        // before I turn around to get Floor 4."
        Elevator e1 = new Elevator(new LookStrategy());
        e1.addObserver(new InternalDisplay());
        e1.addObserver(new FloorDisplay(0)); // Display at Ground Floor
        e1.setCurrentFloor(5, true); // At 5, moving UP
        e1.addRequest(7); 
        e1.addRequest(4); 
        e1.run(); // Output will be: 7 -> 4

        System.out.println("--- SSTF STRATEGY ---");
        // Logic: "I don't care about direction. Floor 4 is only 1 floor away, 
        // but Floor 7 is 2 floors away. I'm turning around immediately!"
        Elevator e2 = new Elevator(new ShortestSeekStrategy());
        e2.addObserver(new InternalDisplay());
        e2.addObserver(new FloorDisplay(0)); // Display at Ground Floor
        e2.setCurrentFloor(5, true); 
        e2.addRequest(7);
        e2.addRequest(4);
        e2.run(); // Output will be: 4 -> 7

        /* 
        OUTPUT

        --- LOOK STRATEGY ---
        [Inside Elevator]: Currently at 7 (UP)
        [Display Floor 0]: Elevator is at 7 going UP
        [Inside Elevator]: Currently at 4 (DOWN)
        [Display Floor 0]: Elevator is at 4 going DOWN
        All stops reached.

        --- SSTF STRATEGY ---
        [Inside Elevator]: Currently at 4 (DOWN)
        [Display Floor 0]: Elevator is at 4 going DOWN
        [Inside Elevator]: Currently at 7 (UP)
        [Display Floor 0]: Elevator is at 7 going UP
        All stops reached.
         */
    }
}