import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args){
/*
        The Observer Design Pattern is a behavioral pattern used to establish a "one-to-many" dependency between objects. When one object (the Subject) changes state, all its dependents (Observers) are notified and updated automatically.
        */
        NewsAgency agency = new NewsAgency();

        NewsChannel sportsChannel = new NewsChannel("Sports Network");
        NewsChannel techChannel = new NewsChannel("Tech Today");

        // Subscribing channels
        agency.register(sportsChannel);
        agency.register(techChannel);

        // New event occurs
        agency.setNews("India wins the Cricket World Cup!");
        
        // Unregistering one observer
        agency.unregister(techChannel);
        
        // Another event
        agency.setNews("New AI model released by Google.");
    }
}

interface Observer{
    void update(String news);
}
interface Subject{
    void register(Observer obj);
    void unregister(Observer obj);
    void notifyObservers();
}
class NewsAgency implements Subject{
    private List<Observer> observers = new ArrayList<>();
    private String news;

    public void setNews(String news) {
        this.news = news;
        notifyObservers(); // Automatically notify when news changes
    }

    @Override
    public void register(Observer obj) {
        observers.add(obj);
    }

    @Override
    public void unregister(Observer obj) {
        observers.remove(obj);
    }

    @Override
    public void notifyObservers() {
        for (Observer obj : observers) {
            obj.update(this.news);
        }
    }
}
class NewsChannel implements Observer{
    String name = "";

    public NewsChannel(String name){
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(this.name + " broadcasting: " + news);
    }
}
