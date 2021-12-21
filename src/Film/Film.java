package Film;

import Kupac.Kupac;
import Sala.Sala;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Film {
    private static int ID;
    private int id_filma;
    private String naziv;
    private String zanr;
    private int duzina;
    private static List<Film> lista_filmova = new ArrayList<Film>();
    public Film(int id,String naziv,String zanr, int duzina){
        this.id_filma = id;
        this.naziv = naziv;
        this.zanr = zanr;
        this.duzina = duzina;
        
    }
    public Film() {
        if(Film.lista_filmova.size()!=0){
            Film.ID = Film.lista_filmova.size();
            Film.ID++;
            
        }
        else{
            Film.ID =0;
            Film.ID++;
        }
        this.id_filma = Film.ID;
        boolean provera_naziv = false;
        boolean provera_zanr = false;
        boolean provera_duzina = false;
        Scanner skener = new Scanner(System.in);
        System.out.println("Unesite naziv filma:");
        while(!provera_naziv){
            String naziv = skener.nextLine();
            if(Kupac.provera_string(naziv)){
                System.out.println("Uspesno ste dodali naziv filma.");
                naziv = naziv.replace(" ","_");
                this.naziv = naziv;
                provera_naziv = true;
            }
            else{
                System.out.println("Neuspesno dodavanje imena!");
            }
        }
        skener = new Scanner(System.in);
        System.out.println("Unesite zanr filma:");
        while(!provera_zanr){
            String zanr = skener.nextLine();
            if(Kupac.provera_string(zanr)){
                System.out.println("Uspesno ste dodali zanr filma.");
                zanr = zanr.replace(" ","_");
                this.zanr = zanr;
                provera_zanr = true;
            }
            else{
                System.out.println("Neuspesno dodavanje zanra!");
            }
        }
        System.out.println("Unesite duzinu filma:");
        while(!provera_duzina){
            try{
                int duzina = Integer.parseInt(skener.next());
                if(duzina > 0){
                    this.duzina = duzina;
                    System.out.println("Uspesno ste dodali duzinu filma!");
                    provera_duzina = true;
                }
                else{
                    System.out.println("Duzina filma mora da bude pozitivan broj!");
                }
            }catch(Exception e){
                System.out.println("Neuspesno dodavanje duzine filma!");
            }
        }
        System.out.println("Film: " + this.film_info() + " je uspesno dodat!");
        
    }

    
    public String film_info(){
        return this.id_filma + " " + this.naziv + " " + this.zanr + " " + this.duzina;
    }
    public static int getID() {
        return ID;
    }

    public int getId_filma() {
        return id_filma;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getZanr() {
        return zanr;
    }

    public int getDuzina() {
        return duzina;
    }

    public static void setID(int ID) {
        Film.ID = ID;
    }

    public void setId_filma(int id_filma) {
        this.id_filma = id_filma;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public void setDuzina(int duzina) {
        this.duzina = duzina;
    }
    public static void dodaj_film(Film novi_film){
        Film.lista_filmova.add(novi_film);
        System.out.println("Film: " + novi_film.film_info()+ " je uspesno dodat.");
        Film.upisi_film();
    }
    public static void ukloni_film(Film film){
        Film.lista_filmova.remove(film);
        System.out.println("Film: " + film.film_info()+ "  je uspesno uklonjen.");
        Film.upisi_film();
    }
    public static List<Film> vrati_listu_filmova(){
        return Film.lista_filmova;
    }
    public String format_sat_min(){
        int duzina = this.duzina;//120
        int sati = 0;
        int minuti = 0;
        while(duzina >= 60){
            duzina-=60;
            sati++;
            
        }
        if(duzina < 60 && duzina >0){
            minuti = duzina;
            duzina -=duzina;
        }
            
        
        return "Sati: " + sati + " minuta: " + minuti;
    }
    public static void dodaj_listu_filmova(List<Film> lista_filmova){
        Film.lista_filmova = lista_filmova;
    }
    public static void upisi_film(){
        String putanja ="film.txt";
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(putanja);
            for(Film f:Film.vrati_listu_filmova()){
                pw.println(f.film_info());
        }
        pw.flush();
        pw.close();
        }catch(Exception e){
            System.out.println("Doslo je do greske!");
        }
        System.out.println("Filmovi su uspesno upisani!");
    }
    public static List<Film> ispisi_film(){
        List<Film> lista_filmova = new ArrayList<Film>();
        try{
            FileInputStream fr = new FileInputStream("film.txt");
            Scanner sc = new Scanner(fr);
            while(sc.hasNext()){
                int id = sc.nextInt();
                String naziv = sc.next();
                if(naziv.contains(" ")){
                    naziv = naziv.replace("_", " ");
                }
                String zanr = sc.next();
                if(zanr.contains(" ")){
                    zanr = zanr.replace("_"," ");
                }
                int duzina =  sc.nextInt();
                Film procitan_film = new Film(id,naziv,zanr,duzina);
                lista_filmova.add(procitan_film);
                
                
            }
            fr.close();
            sc.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lista_filmova;
        
        
    }
    
    
    
}
