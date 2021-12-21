package Projekcija;

import Film.Film;
import Kupac.Kupac;
import Rezervacija.Rezervacija;
import Sala.Sala;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Projekcija{
    private static int ID;
    private int id_projekcije;
    private String datum_projekcije;
    private Sala sala;
    private Film film;
    private int cena_karte;
    private static List<Projekcija> lista_projekcija = new ArrayList<Projekcija>();
    public Projekcija(int id,String datum,int sala_id,int film_id,int cena_karte){
        this.id_projekcije = id;
        this.datum_projekcije = datum;
        if(Sala.vrati_listu_sala() != null){
            for(Sala s:Sala.vrati_listu_sala()){
            if(s.getId_sala()==sala_id){
                this.sala =s;
                break;
            }
        }
        }
        else{
            System.out.println("Nije moguce dodati projekciju jer nema dostupnih sala!");
        }
        if(Film.vrati_listu_filmova()!=null){
            for(Film f:Film.vrati_listu_filmova()){
                if(f.getId_filma()==film_id){
                    this.film = f;
                    
                }
        }
        }
        else{
            
            System.out.println("Nije moguce dodati projekciju jer nema dostupnih filmova!");
        }
        this.cena_karte = cena_karte;
        
    }
    public Projekcija(Sala sala, Film film) {
        if(Projekcija.lista_projekcija.size()!=0){
            Projekcija.ID = Projekcija.lista_projekcija.size();
            Projekcija.ID++;
            
        }
        else{
            Projekcija.ID =0;
            Projekcija.ID++;
        }
        Scanner skener = new Scanner(System.in);
        boolean provera_datum = false;
        boolean provera_cena = false;
        this.id_projekcije = ID;
        System.out.println("Unesite datum projekcije");
        while(!provera_datum){
            String datum = skener.next();
            if(Kupac.provera_datum(datum)){
                provera_datum = true;
                System.out.println("Datum je uspesno dodat");
                this.datum_projekcije = datum;
            }
            else{
                System.out.println("Neuspesno dodavanje datuma");
            }
        }
        skener = new Scanner(System.in);
        System.out.println("Unesite cenu projekcije");
        while(!provera_cena){
            String unos_cena = skener.next();
            try{
                int cena = Integer.parseInt(unos_cena);
                if(cena > 0){
                    this.cena_karte = cena;
                    provera_cena = true;
                    System.out.println("Uspesno ste dodali cenu projekcije");
                }
                else{
                    System.out.println("Cena projekcija mora biti pozitivan broj");
                }
            }catch(Exception e){
                System.out.println("Neispravan unos cene!");
            }
        }
        this.sala = sala;
        this.film = film;
        System.out.println("Projekcija: "+ this.projekcija_info() + " je uspesno dodata!");
    
    }
    
    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Projekcija.ID = ID;
    }

    public int getId_projekcije() {
        return id_projekcije;
    }

    public void setId_projekcije(int id_projekcije) {
        this.id_projekcije = id_projekcije;
    }

    public String getDatum_projekcije() {
        return datum_projekcije;
    }

    public void setDatum_projekcije(String datum_projekcije) {
        this.datum_projekcije = datum_projekcije;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public int getCena_karte() {
        return cena_karte;
    }

    public void setCena_karte(int cena_karte) {
        this.cena_karte = cena_karte;
    }

    public String projekcija_info(){
        return id_projekcije + " " + datum_projekcije + " " + sala.getId_sala() + " " + film.getId_filma() + " " + cena_karte;
    }
    public static void dodaj_projekciju(Projekcija nova_projekcija){
        Projekcija.lista_projekcija.add(nova_projekcija);
        System.out.println("Projekcija: " + nova_projekcija.projekcija_info()+ " je uspesno dodata.");
        Projekcija.upisi_projekciju();
        
    }
    public static void ukloni_projekciju(Projekcija projekcija){
        Projekcija.lista_projekcija.remove(projekcija);
        System.out.println("Projekcija: " +projekcija.projekcija_info()+ " je uspesno uklonjena.");
        Projekcija.upisi_projekciju();
        
    }
    public static List<Projekcija> vrati_listu_projekcija(){
        return Projekcija.lista_projekcija;
    }
    public static void upisi_projekciju(){
        String putanja = "projekcija.txt";
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(putanja);
            for(Projekcija p:Projekcija.vrati_listu_projekcija()){
                pw.println(p.projekcija_info());
            }
            pw.flush();
            pw.close();
        }catch(Exception e){
            System.out.println("Projekcija je uspesno upisana!");
        }
    }
    public static void dodaj_listu_projekcija(List<Projekcija> lista_projekcija){
        Projekcija.lista_projekcija = lista_projekcija;
    }
    public static List<Projekcija> ispisi_projekciju(){
        List<Projekcija> lista_projekcija = new ArrayList<Projekcija>();
        try{
            FileInputStream fr = new FileInputStream("projekcija.txt");
            Scanner sc = new Scanner(fr);
            while(sc.hasNext()){
                int id = sc.nextInt();
                String datum = sc.next();
                int id_sala = sc.nextInt();
                int id_film = sc.nextInt();
                int cena = sc.nextInt();
                Projekcija procitana_projekcija = new Projekcija(id,datum,id_sala,id_film,cena);
                lista_projekcija.add(procitana_projekcija);
                
            }
            fr.close();
            sc.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lista_projekcija;
        
        
    }

    
    
    
    
}
