package rilevatore;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class PacchettoVeicoloRilevato implements java.io.Serializable{
    private String targa;
    private String marca;
    private int giorno;
    private int mese;
    private int anno;
    private int ora;
    private int minuto;
    private int secondo;
    private double latitudine;
    private double longitudine;
    public PacchettoVeicoloRilevato(String targa, String marca){
        this.targa = targa;
        this.marca = marca;
    }
    public PacchettoVeicoloRilevato(String targa, String marca, int giorno, 
	int mese, int anno, int ora, int minuto, int secondo, double latitudine, 
	double longitudine){
	this.targa = targa;
	this.marca = marca;
	this.giorno = giorno;
	this.mese = mese;
	this.anno = anno;
	this.ora = ora;
	this.minuto = minuto;
	this.secondo = secondo;
	this.latitudine = latitudine;
	this.longitudine = longitudine;
    }
    public String getTarga(){
	return targa;
    }
    public StringProperty targaProperty(){
	return new SimpleStringProperty(targa);
    }
    public String getMarca(){
	return marca;
    }
    public StringProperty marcaProperty(){
	return new SimpleStringProperty(marca);
    }
    public int getGiorno(){
	return giorno;
    }
    public IntegerProperty giornoProperty(){
	return new SimpleIntegerProperty(giorno);
    }
    public int getMese(){
	return mese;
    }
    public IntegerProperty meseProperty(){
	return new SimpleIntegerProperty(mese);
    }
    public int getAnno(){
	return anno;
    }
    public IntegerProperty annoProperty(){
	return new SimpleIntegerProperty(anno);
    }
    public int getOra(){
        return ora;
    }
    public IntegerProperty oraProperty(){
	return new SimpleIntegerProperty(ora);
    }
    public int getMinuto(){
        return minuto;
    }
    public IntegerProperty minutoProperty(){
	return new SimpleIntegerProperty(minuto);
    }
    public int getSecondo(){
        return secondo;
    }
    public IntegerProperty secondoProperty(){
	return new SimpleIntegerProperty(secondo);
    }
    public double getLatitudine(){
	return latitudine;
    }
    public DoubleProperty latitudineProperty(){
	return new SimpleDoubleProperty(latitudine);
    }
    public double getLongitudine(){
        return longitudine;
    }
    public DoubleProperty longitudineProperty(){
	return new SimpleDoubleProperty(longitudine);
    }
    public StringProperty getVehicleMainInfo(){
        return new SimpleStringProperty(targa + " - " + marca);
    }
}