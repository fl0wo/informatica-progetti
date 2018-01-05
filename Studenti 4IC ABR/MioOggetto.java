/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studenti4icabr;

/**
 *
 * @author Florian
 */
public class MioOggetto implements Comparable{
    private String nome,cognome;
    //private Color color;
    
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public MioOggetto(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return "Cognome: " + cognome + "\tNome: " + nome;
    }

    @Override
    public int compareTo(Object o) {
        MioOggetto alunno = (MioOggetto)o;
        int risultato = this.getCognome().compareTo(alunno.getCognome());
        if(risultato==0){   // I due cognomi sono uguali...
            risultato = this.getNome().compareTo(alunno.getNome()); // Se continua ad essere 0 hanno sia il nome che il cognome uguale
        }
        return risultato;
    }
}
