package mappa;
public class PuntoMappa{
    public final double stop_lat;
    public final double stop_lon;
    public final String stop_name;
    public final int stop_id;
    public PuntoMappa(int stop_id, String stop_name, double stop_lat, double stop_lon){
        this.stop_lat = stop_lat;
	this.stop_lon = stop_lon;
	this.stop_name = stop_name;
	this.stop_id = stop_id;
    }
}