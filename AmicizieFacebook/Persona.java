package amiciziefacebook.src;

public class Persona {

    private String nome;
    private String cognome;
    private String data;

    public Persona(String nome, String cognome, String data) {
        this.nome = nome;
        this.cognome = cognome;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Persona [nome=" + nome + ", cognome=" + cognome + ", data=" + data + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {      // Se sono uguali
            return true;
        }
        if (obj == null) {      // se ho come parametro un null
            return false;
        }
        if (getClass() != obj.getClass()) { // Se non sono due persone
            return false;
        }
        Persona other = (Persona) obj;  
        if (cognome == null) { 
            if (other.cognome != null) {
                return false;
            }
        } else if (!cognome.equals(other.cognome)) {
            return false;
        }
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        if (nome == null) {
            if (other.nome != null) {
                return false;
            }
        } else if (!nome.equals(other.nome)) {
            return false;
        }
        return true;
    }

}
