interface Device {
    public void turnOn();
    public void turnOff();
}

class AC implements Device{
    public void turnOn(){
        System.out.println("AC turned On ");
    }
    public void turnOff(){
        System.out.println("AC turned Off ");
    }
}
class TV implements Device{
    public void turnOn(){
        System.out.println("TV turned On ");
    }
    public void turnOff(){
        System.out.println("TV turned Off ");
    }
}

interface RemoteControl{
    public void pressButton();
}
class BasicRemote implements RemoteControl{
    Device device;
    boolean isOff = true;
    public BasicRemote(Device device){
        this.device = device;
    }
    public void pressButton(){
        if(this.isOff) this.device.turnOn(); 
        else this.device.turnOff();
        
        this.isOff = !this.isOff;
    }
}
public class Main {
    public static void main(String[] args) {
        /*
        the Bridge pattern is defined as separating an abstraction from its implementation so that the two can vary independently.
        For below example, we could create a tvRemote, acRemote as seperate classes but instead we create 2 seperate interfaces Device and Remote.
        And we bridge them to reduce the complexity.
        */
        Device myTV = new TV();
        RemoteControl remote = new BasicRemote(myTV);
        remote.pressButton(); // Output: TV turned On 
        remote.pressButton(); // Output: TV turned Off 

        Device myAC = new AC();
        RemoteControl acRemote = new BasicRemote(myAC);
        acRemote.pressButton(); // Output: AC turned On
        acRemote.pressButton(); // Output: AC turned Off 
    }
}