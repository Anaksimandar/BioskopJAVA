package Korisnik;
import Kupac.Kupac;

public abstract class Korisnik {
    protected String ime;
    protected String prezime;
    public Korisnik(){
        
    }
    public Korisnik(String ime, String prezime) {
        if(Kupac.provera_string(ime)){
            this.ime = ime;
        }
        else{
            System.out.println("Neispravan unos imena.");
        }
        if(Kupac.provera_string(prezime)){
            this.prezime = prezime;
        }
        else{
            System.out.println("Neispravan unos prezimena.");
        }
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    
    
    
    
    
}
