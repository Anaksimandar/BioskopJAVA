package Main;

import Admin.Admin;
import Film.Film;
import Korisnik.Korisnik;
import Kupac.Kupac;
import Projekcija.Projekcija;
import Rezervacija.Rezervacija;
import Sala.Sala;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {
    public static void main(String[] args) {
        Kupac.dodaj_listu_kupaca(Kupac.ispisi_kupce());
        Film.dodaj_listu_filmova(Film.ispisi_film());
        Sala.dodaj_listu_sala(Sala.ispisi_salu());
        Projekcija.dodaj_listu_projekcija(Projekcija.ispisi_projekciju());
        Rezervacija.dodaj_listu_rezervacija(Rezervacija.ispisi_rezervacije());
        Kupac k = new Kupac();
        Kupac.dodaj_kupca(k);
        
        Film f = new Film();
        Film.dodaj_film(f);
        
        
        
        
        
        
        
       
        
        
        
        
    }
    
}
