package observer_02;

public interface Observable { 
    
    public void registerObserver(Observer observer);
    
    public void notifyObserver();
    
    public void unRegisterObserver(Observer observer);
    
    public Object getUpdate();
}
