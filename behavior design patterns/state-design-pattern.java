// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class Main {
    public static void main(String[] args) {
        /*
            State Design Pattern is a behavioral design pattern that allows an object to change its behavior when its internal state changes. This pattern is particularly useful when an object's behavior depends on its state, and the state can change during the object's lifecycle.
        */
        MediaPlayer player = new MediaPlayer();

        // 1. Initial State: Ready
        player.pressPlay(); // Output: Starting music... Enjoy! (Now Playing)
        
        // 2. State: Playing
        player.pressPlay(); // Output: Pausing music. (Now Paused)
        
        // 3. State: Paused
        player.pressPlay(); // Output: Resuming music. (Now Playing)
        
        // 4. State: Playing
        player.pressStop(); // Output: Stopping music. Returning to standby. (Now Ready)
        
        // 5. State: Ready
        player.pressStop(); // Output: Already stopped. Nothing to do.
    }
}
interface State{
    public void play(MediaPlayer mp);
    public void stop(MediaPlayer mp);
}
class ReadyState implements State{
    public void play(MediaPlayer mp){
        System.out.println("Starting music... Enjoy!");
        mp.setState(new PlayingState());
    }
    
    public void stop(MediaPlayer mp){
       // nothing to do
       System.out.println("Already stopped. Nothing to do.");
    }
}
class PlayingState implements State{
    public void play(MediaPlayer mp){
        System.out.println("Pausing music.");
        mp.setState(new StopState());
    }
    
    public void stop(MediaPlayer mp){
        System.out.println("Stopping music. Returning to standby.");
        mp.setState(new ReadyState());
    }
}
class StopState implements State{
    public void play(MediaPlayer mp){
        System.out.println("Resuming music.");
        mp.setState(new PlayingState());
    }
    
    public void stop(MediaPlayer mp){
        System.out.println("Stopping music from pause. Returning to standby.");
        mp.setState(new ReadyState());
    }
}
class MediaPlayer{
    State currentState;
    
    public MediaPlayer(){
         this.currentState = new ReadyState();  
    }
    
    public void setState(State s){
        this.currentState=s;
    } 
    
    public void pressPlay(){
        currentState.play(this);
    }
    
    public void pressStop(){
        currentState.stop(this);
    }
    
}