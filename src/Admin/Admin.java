package Admin;

import Film.Film;
import Korisnik.Korisnik;
import Kupac.Kupac;
import Projekcija.Projekcija;
import Rezervacija.Rezervacija;
import Sala.Sala;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Admin extends Korisnik{
    private String lozinka;
    private boolean dozvoljen_pristup = false;
    
    public Admin() {
        boolean provera_ime = false;
        boolean provera_prezime = false;
        boolean provera_lozinka = false;
        Scanner skener = new Scanner(System.in);
        System.out.println("Unesite ime:");
        while(!provera_ime){
            String ime = skener.next();
            if(Kupac.provera_string(ime)){
                this.ime = ime;
                System.out.println("Ime je uspesno dodato!");
                provera_ime = true;
            }
            else{
                System.out.println("Neispravan unos imena!");
            }
        }
        System.out.println("Unesite prezime:");
        while(!provera_prezime){
            String prezime = skener.next();
            if(Kupac.provera_string(prezime)){
                provera_prezime = true;
                this.prezime = prezime;
                System.out.println("Prezime je uspesno dodato!");
            }
            else{
                System.out.println("Neispravan unos prezimena!");
            }
        }
        System.out.println("Unesite lozinku:");
        while(!provera_lozinka){
            String lozinka = skener.next();
            System.out.println(lozinka);
            if(lozinka.equals("admin123")){
                
                provera_lozinka = true;
                this.dozvoljen_pristup = true;
                System.out.println("Dobrodosli " + this.ime);
            }
            else{
                System.out.println("Pogresna lozinka");
            }
        }
        
        
        
        
    }
    
    public  String prikazi_kupce(){
        if(dozvoljen_pristup && Kupac.vrati_listu_kupaca()!=null){
            String lista_kupaca = "";
            for(Kupac k : Kupac.vrati_listu_kupaca()){
                lista_kupaca += k.kupac_info()+"\n";
            
        }
        return lista_kupaca;
        }
        else{
            return "Nemate dozvolu za prikaz kupaca!";
        }
        
    }
    public  String prikazi_sale(){
        if(dozvoljen_pristup && Sala.vrati_listu_sala()!= null ){
            String lista_sala = "";
        for(Sala s : Sala.vrati_listu_sala()){
            lista_sala += s.sala_info()+"\n";
            
        }
        return lista_sala;
        }
        else{
            return "Nemate dozvolu za prikaz sala!";
        }
    }
    public  String prikazi_projekcije(){
        if(dozvoljen_pristup && Projekcija.vrati_listu_projekcija() !=null){
            String lista_projekcija = "";
        for(Projekcija p : Projekcija.vrati_listu_projekcija()){
            lista_projekcija += p.projekcija_info()+"\n";
            
        }
        return lista_projekcija;
        }
        else{
            return "Nemate dozvolu za prikaz projekcija!";
        }
    }
    public  String prikazi_filmove(){
        if(dozvoljen_pristup && Film.vrati_listu_filmova() != null){
            String lista_filmova = "";
        for(Film f : Film.vrati_listu_filmova()){
            lista_filmova += f.film_info()+"\n";
            
        }
        return lista_filmova;
        }
        else{
            return "Nemate dozvolu za prikaz filmova!";
        }
    }
    public  String prikazi_rezervacije(){
        if(dozvoljen_pristup && Rezervacija.vrati_listu_rezervacija() != null){
            String lista_rezervacija = "";
        for(Rezervacija r: Rezervacija.vrati_listu_rezervacija()){
            lista_rezervacija += r.rezervacija_info()+"\n";
            
        }
        return lista_rezervacija;
        }
        else{
            return "Nemate dozvolu za prikaz rezervacija!";
        }
    }
    public  void izmeni_kupca(Kupac kupac){
        if(dozvoljen_pristup){
            String stari_kupac = kupac.kupac_info();
        boolean provera_ime = false;
        boolean provera_prezime = false;
        boolean provera_telefon = false;
        boolean provera_datum = false;
        boolean provera_lozinka = false;
        boolean provera_mail = false;
        Scanner skener = new Scanner(System.in);
        System.out.println("Unesite novo ime kupca");
        while(!provera_ime){
            String ime = skener.nextLine();
            if(Kupac.provera_string(ime)){
                provera_ime = true;
                kupac.setIme(ime);
                System.out.println("Ime kupca je uspesno izmenjeno!");
            }
            else{
                System.out.println("Neuspesna izmena imena!");
            }
            
        }
        System.out.println("Unesite novo prezime kupca");
        while(!provera_prezime){
            String prezime = skener.nextLine();
            if(Kupac.provera_string(prezime)){
                provera_prezime = true;
                kupac.setPrezime(prezime);
                System.out.println("Prezime kupca je uspesno izmenjeno!");
                
            }
            else{
                System.out.println("Neuspesna izmena prezimena!");
            }
        }
        System.out.println("Unesite nov broj telefona kupca");
        while(!provera_telefon){
            String telefon = skener.nextLine();
            if(Kupac.provera_telefon(telefon)){
                provera_telefon = true;
                kupac.setTelefon(telefon);
                System.out.println("Telefon kupca je uspesno izmenjen!");
            }
            else{
                System.out.println("Neuspesna izmena telefona!");
                
                
            }
        }
        System.out.println("Unesite novi datum kupca");
        while(!provera_datum){
            String datum = skener.nextLine();
            if(Kupac.provera_datum(datum)){
                provera_datum = true;
                kupac.setDatum_rodjenja(datum);
                System.out.println("Datum rodjenja kupca je uspesno izmenjen!");
            }
            else{
                System.out.println("Neuspesna izmena datuma rodjenja");
            }
        }
        System.out.println("Unesite novi mejl kupca");
        while(!provera_mail){
            String mail = skener.nextLine();
            if(Kupac.provera_mail(mail)&&Kupac.autentican_mail(mail)){
                provera_mail = true;
                kupac.setMail(mail);
                System.out.println("Mejl kupca je uspesno izmenjen");
            }
            else{
                System.out.println("Neuspesna izmena mejla kupca");
            }
        }
        System.out.println("Unesite novu lozinku kupca");
        while(!provera_lozinka){
            String lozinka = skener.next();
            provera_lozinka = true;
            kupac.setLozinka1(lozinka);
            kupac.setLozinka2(lozinka);
            System.out.println("Lozinka kupca je uspesno izmenjena");
        }
        System.out.println("Stari kupac: " + stari_kupac+ "\n" +"Novi kupac: " + kupac.kupac_info());
        Kupac.upisi_kupca();
                
        }
        else{
            System.out.println("Nemate dozvolu da menjate korisnika.");
        }
        
        
    }
    public void izmeni_salu(Sala sala){
        if(dozvoljen_pristup){
            String stara_sala = sala.sala_info();
            System.out.println("Unesite novi broj sale");
            Scanner skener= new Scanner(System.in);
        
            boolean broj_mesta = false;
            boolean broj_sale = false;
            int novi_broj_mesta = 0;
            int novi_broj_sale = 0;
            while(!broj_sale){
            
                try{
                novi_broj_sale = Integer.parseInt(skener.next());
                broj_sale = true;
            
            
            }catch(Exception e){
                System.out.println("Morate uneti broj!");
                broj_sale = false;
            }
        
            }
            System.out.println("Unesite broj mesta");
            while(!broj_mesta){
            
            try{
                novi_broj_mesta = Integer.parseInt(skener.next());
                broj_mesta= true;
            
            
            }catch(Exception e){
                System.out.println("Morate uneti broj!");
                broj_mesta = false;
            }
            }
            if(broj_mesta && broj_sale && novi_broj_mesta>1&&novi_broj_sale>1){
                sala.setBroj_mesta(novi_broj_mesta);
                sala.setBroj_sale(novi_broj_sale);
                System.out.println("Stara sala: " + stara_sala+" je uspesno izmenjena!\br"+ "Nova sala: " + sala.sala_info());
                Sala.upisi_salu();
            }
        
        
        
        
            else{
            System.out.println("Uneti podaci moraju biti celi brojevi!");
            }
        }
        else{
            System.out.println("Nemate dozvolu da menjate sale!");
        }
        
        
        
    }
    public void izmeni_projekciju(Projekcija projekcija,Sala nova_sala, Film novi_film){
        if(dozvoljen_pristup){
            String stara_projekcija = projekcija.projekcija_info();
            projekcija.setFilm(novi_film);
            projekcija.setSala(nova_sala);
            boolean provera_datum = false;
            boolean provera_cene = false;
            Scanner skener = new Scanner(System.in);
            System.out.println("Unesite novi datum projekcije");
            while (!provera_datum) {
                String datum = skener.nextLine();

                if (Kupac.provera_datum(datum)) {
                    provera_datum = true;
                    projekcija.setDatum_projekcije(datum);
                    System.out.println("Uspesno ste promenili datum!");
                } else {
                    System.out.println("Neuspesna izmena datuma!");
                }
            }
            System.out.println("Unesite novu cenu projekcije");
            while (!provera_cene) {
                String broj = skener.next();
                try {
                    int cena = Integer.parseInt(broj);
                    if (cena >= 0) {
                        provera_cene = true;
                        System.out.println("Uspesno ste promenili cenu projekcije");
                    } else {
                        System.out.println("Cena projekcije mora biti pozitivan broj!");
                    }
                } catch (Exception e) {
                    System.out.println("Morate uneti broj!");

                }
            }
            System.out.println("Projekcija: " + stara_projekcija + " je uspesno izmenjena!\nNova projekcija: " + projekcija.projekcija_info());
            Projekcija.upisi_projekciju();
        }
        else{
            System.out.println("Nemate dozvolu da menjate projekcije!");
        }
    }
    public void izmeni_film(Film film){
        if(dozvoljen_pristup){
            String stari_film = film.film_info();
        boolean provera_naziv = false;
        boolean provera_zanr = false;
        boolean provera_duzina = false;
        Scanner skener = new Scanner(System.in);
        System.out.println("Unesite novi naziv filma.");
        while(!provera_naziv){
            String naziv = skener.nextLine();
            if(Kupac.provera_string(naziv)){
                
                film.setNaziv(naziv);
                System.out.println("Naziv je uspesno izmenjen!");
                provera_naziv= true;
            }
            else{
                provera_naziv = false;
                System.out.println("Neispravan unos naziva filma!");
            }
           
        }
        System.out.println("Unesite novi zanr.");
        while(!provera_zanr){
            String naziv = skener.nextLine();
            if(Kupac.provera_string(naziv)){
                
                film.setZanr(naziv);
                System.out.println("Zanr je uspesno izmenjen!");
                provera_zanr= true;
            }
            else{
                provera_zanr = false;
                System.out.println("Neispravan unos zanra filma!");
            }
           
        }
        System.out.println("Unesite novu duzinu filma.");
        while(!provera_duzina){
            try{
                int duzina = Integer.parseInt(skener.nextLine());
                film.setDuzina(duzina);
                System.out.println("Duzina filma je uspesno izmenjena!");
                provera_duzina = true;
            }catch(Exception e){
                provera_duzina = false;
                System.out.println("Neispravan unos duzine filma!");
            }
            
           
        }
        
        System.out.println("Film: " + stari_film + " je uspesno izmenjen!\br"+ "Novi film: " + film.film_info());
        Film.upisi_film();
        }
        else{
            System.out.println("Nemate dozvolu da menjate filmove!");
        }
        
    }
    public void izmeni_rezervaciju(Rezervacija rezervacija,Kupac novi_kupac, Projekcija nova_projekcija){
        String stara_rezervacija = rezervacija.rezervacija_info();
        rezervacija.setId_kupca(novi_kupac);
        System.out.println("Uspesna izmena kupca!");
        rezervacija.setId_projekcije(nova_projekcija);
        System.out.println("Uspesna izmena projekcije!");
        boolean provera_broj_mesta= false;
        
        Scanner skener = new Scanner(System.in);
        System.out.println("Unesite broj mesta:");
        while(!provera_broj_mesta){
            try{
                int broj = Integer.parseInt(skener.next());
                if(broj >= 0){
                    System.out.println("Broj mesta je uspesno izmenjen!");
                    rezervacija.setBroj_karata(broj);
                    provera_broj_mesta = true;
                }
                else{
                    System.out.println("Broj mesta mora biti pozitivan broj!");
                }
            
            }catch(Exception e){
                System.out.println("Neuspesan unos broja mesta!");
            }
        }
        rezervacija.setUkupna_cena(rezervacija.getBroj_karata()*rezervacija.getId_projekcije().getCena_karte());
        System.out.println("Cena karata je uspesno izmenjena");
        System.out.println("Rezervacija: " + stara_rezervacija + " je uspesno izmenjena!\nNova rezervacija: " + rezervacija.rezervacija_info());
        Rezervacija.upisi_rezervaciju();
        }
}

    

   
    
    

