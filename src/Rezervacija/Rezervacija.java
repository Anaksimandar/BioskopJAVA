package Rezervacija;

import Interfejsi.Cena_projekcije;
import Kupac.Kupac;
import Projekcija.Projekcija;
import Sala.Sala;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rezervacija implements Cena_projekcije {
    private static int ID;
    private int id_rezervacije;
    private Kupac id_kupca;
    private Projekcija id_projekcije;
    private int ukupna_cena;
    private int broj_karata;
    private static List<Rezervacija> lista_rezervacija = new ArrayList<Rezervacija>();
    public Rezervacija(int id_rezervacije, int id_kupca, int id_projekcija, int ukupna_cena,int broj_karata){
        this.id_rezervacije = id_rezervacije;
        if(Kupac.vrati_listu_kupaca()!=null){
            for(Kupac k: Kupac.vrati_listu_kupaca()){
                if(k.getId_kupca() == id_kupca){
                    this.id_kupca = k;
                }
            }
        }
        else{
            System.out.println("Nije moguce dodati rezervaciju jer nema dostupnih kupaca.");
        }
        if(Projekcija.vrati_listu_projekcija()!=null){
            for(Projekcija p: Projekcija.vrati_listu_projekcija()){
                if(p.getId_projekcije() == id_projekcija){
                    this.id_projekcije = p;
                }
            }
        }
        else{
            System.out.println("Nije moguce dodati rezervaciju jer nema dostupnih projekcija.");
        }
        this.ukupna_cena = ukupna_cena;
        this.broj_karata = broj_karata;
    }
    public Rezervacija(Kupac id_kupca, Projekcija id_projekcije) {
        if(Rezervacija.lista_rezervacija.size()!=0){
            Rezervacija.ID = Rezervacija.lista_rezervacija.size();
            Rezervacija.ID++;
            
        }
        else{
            Rezervacija.ID =0;
            Rezervacija.ID++;
        }
        this.id_kupca = id_kupca;
        this.id_rezervacije = Rezervacija.ID;
        this.id_projekcije = id_projekcije;
        boolean provera_broj_karata = false;
        Scanner skener = new Scanner(System.in);
        System.out.println("Dodajte broj karata:");
        while(!provera_broj_karata){
            try{
                int broj = Integer.parseInt(skener.next());
                
                if(Rezervacija.dostupne_karte(broj,this.id_projekcije.getSala())<0){
                System.out.println("Trenutno nemamo na raspolaganju zeljeni broj karata");
                }
                else{
                    System.out.println("Uspesan unos broja karata!");
                    this.broj_karata = broj;
                    System.out.println("Broj karata pre: " + this.getId_projekcije().getSala().getBroj_mesta());
                    
                    this.getId_projekcije().getSala().setBroj_mesta(this.getId_projekcije().getSala().getBroj_mesta()-broj);
                    this.ukupna_cena = this.cena_projekcije(broj_karata);
                    System.out.println("Trenutni broj karata: " + this.getId_projekcije().getSala().getBroj_mesta());
                    provera_broj_karata = true;
                }
                    
                }catch(Exception e){
                    System.out.println("Neuspesan unos broja karata!");
                }
        }
        
        
    }
    public static int dostupne_karte(int broj_karata, Sala s1){
        int dostupne_karte = s1.getBroj_mesta()-broj_karata;
        return dostupne_karte;
    }
    public String rezervacija_info(){
        return id_rezervacije+" " +id_kupca.getId_kupca()+" "+id_projekcije.getId_projekcije()+" "+ukupna_cena+" "+broj_karata;
    }
    public Kupac getId_kupca() {
        return id_kupca;
    }

    public void setId_kupca(Kupac kupac) {
        this.id_kupca = kupac;
    }

    public int getId_rezervacije() {
        return id_rezervacije;
    }

    public void setId_rezervacije(int id_rezervacije) {
        this.id_rezervacije = id_rezervacije;
    }

    public Projekcija getId_projekcije() {
        return id_projekcije;
    }

    public void setId_projekcije(Projekcija id_projekcije) {
        this.id_projekcije = id_projekcije;
    }

    public int getUkupna_cena() {
        return ukupna_cena;
    }

    public void setUkupna_cena(int ukupna_cena) {
        this.ukupna_cena = ukupna_cena;
    }

    public int getBroj_karata() {
        return broj_karata;
    }

    public void setBroj_karata(int broj_karata) {
        this.broj_karata = broj_karata;
    }
    public static void dodaj_rezervaciju(Rezervacija nova_rezervacija){
        Rezervacija.lista_rezervacija.add(nova_rezervacija);
        System.out.println(nova_rezervacija.rezervacija_info()+ " je uspesno dodata.");
        Rezervacija.upisi_rezervaciju();
    }
    public static void ukloni_rezervaciju(Rezervacija rezervacija){
        Rezervacija.lista_rezervacija.remove(rezervacija);
        System.out.println(rezervacija.rezervacija_info()+ " je uspesno uklonjena.");
        Rezervacija.upisi_rezervaciju();
    }
    public static List<Rezervacija> vrati_listu_rezervacija(){
        return Rezervacija.lista_rezervacija;
    }
    public static void dodaj_listu_rezervacija(List<Rezervacija> lista_rezervacija){
        Rezervacija.lista_rezervacija = lista_rezervacija;
    }
    
    @Override
    public int cena_projekcije(int broj_karata) {
        int ukupna_cena = this.id_projekcije.getCena_karte()*broj_karata;
        return ukupna_cena;
    }
    public static void upisi_rezervaciju(){
        String putanja = "rezervacija.txt";
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(putanja);
            for(Rezervacija r:Rezervacija.vrati_listu_rezervacija()){
                pw.println(r.rezervacija_info());
            }
            pw.flush();
            pw.close();
            
        }catch(Exception e){
            System.out.println("Rezervacija je uspesno dodata!");
        }
        
    }
    public static List<Rezervacija> ispisi_rezervacije(){
        String putanja = "rezervacija.txt";
        List<Rezervacija> lista_rezervacija = new ArrayList<Rezervacija>();
        try{
            FileInputStream fis = new FileInputStream(putanja);
            Scanner sc = new Scanner(fis);
            while(sc.hasNext()){
                int id_rezervacija = sc.nextInt();
                int id_kupac = sc.nextInt();
                int id_projekcija = sc.nextInt();
                int cena = sc.nextInt();
                int broj_karata = sc.nextInt();
                Rezervacija nova_rezervacija = new Rezervacija(id_rezervacija,id_kupac,id_projekcija,cena,broj_karata);
                lista_rezervacija.add(nova_rezervacija);
                
            }
            fis.close();
            sc.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lista_rezervacija;
    }
    
    
    
}
