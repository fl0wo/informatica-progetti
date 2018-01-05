package observer_01;

import java.util.Observable;
import java.util.Observer;

public class ObserverExample implements Observer {
    
    private ObservableDemo weatherUpdate ;

    // This method is called whenever the observed object is changed.
    @Override
    public void update(Observable observable, Object arg) {
        // cast
        weatherUpdate = (ObservableDemo) observable;
        System.out.println("Weather Report Live. Its " + weatherUpdate.getWeather());
    }
	    
    // test
    public static void main(String[] args) {
        ObservableDemo observable = new ObservableDemo(null);
        ObserverExample observer = new ObserverExample();
        
        // Adds an observer to the set of observers for this object, 
        // provided that it is not the same as some observer already in the set. 
        // The order in which notifications will be delivered to multiple observers is not specified. See the class comment.
        observable.addObserver(observer);
        observable.setWeather("Bright and sunny...Let's play cricket!! ");
        observable.setWeather("Raining Heavily!..Let's have hot Pakodas!!");
    }
}
