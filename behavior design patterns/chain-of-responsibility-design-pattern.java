public class Main {
    public static void main(String[] args) {
        /*
        The Chain of Responsibility design pattern is a behavioral design pattern that allows an object to pass a request along a chain of handlers. Each handler in the chain decides either to process the request or to pass it along the chain to the next handler.
        */
        // Create the handlers
        Approver supervisor = new Supervisor();
        Approver manager = new Manager();
        Approver hr = new HR();

        // Create the chain: Supervisor -> Manager -> Hr
        supervisor.setNextApprover(manager);
        manager.setNextApprover(hr);

        // Test different requests
        supervisor.approve(5);
        supervisor.approve(2);
        supervisor.approve(50);
        supervisor.approve(26);
        
        /* OUTPUT:
        Approved by manager
        Approved by supervisor
        Leave Rejected
        Approved by HR
        */
    }
}

interface Approver{
    void approve(int days);
    void setNextApprover(Approver ap);
}

class Supervisor implements Approver{
    Approver nextApprover = null;
    public void approve(int days){
        if(days<3){
            System.out.println("Approved by supervisor");
        }
        else if(this.nextApprover !=null){
            this.nextApprover.approve(days);
        }
        else{
            System.out.println("Leave Rejected");
        }
    }
    
    public void setNextApprover(Approver ap){
        this.nextApprover = ap;
    }
}

class Manager implements Approver{
    Approver nextApprover = null;
    public void approve(int days){
        if(days<7){
            System.out.println("Approved by manager");
        }
        else if(this.nextApprover !=null){
            this.nextApprover.approve(days);
        }
        else{
            System.out.println("Leave Rejected");
        }
    }
    
    public void setNextApprover(Approver ap){
        this.nextApprover = ap;
    }
}

class HR implements Approver{
    Approver nextApprover = null;
    public void approve(int days){
        if(days<30){
            System.out.println("Approved by HR");
        }
        else if(this.nextApprover !=null){
            this.nextApprover.approve(days);
        }
        else{
            System.out.println("Leave Rejected");
        }
    }
    
    public void setNextApprover(Approver ap){
        this.nextApprover = ap;
    }
}