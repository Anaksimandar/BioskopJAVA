package Kupac;


import Film.Film;
import Korisnik.Korisnik;
import Rezervacija.Rezervacija;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kupac extends Korisnik{
    private static int ID;
    private int id_kupca;
    private String datum_rodjenja;
    private String telefon;
    private String lozinka1;
    private String lozinka2;
    private String mail;
    private boolean registrovan;
    private boolean prijavljen;
    private List<Rezervacija> lista_rezervacija = new ArrayList<Rezervacija>();
    private static List<Kupac> lista_kupaca = new ArrayList<Kupac>();
    
    public Kupac(int id,String ime, String prezime,String datum, String telefon,String lozinka1,String lozinka2,String mail){
        if(Kupac.lista_kupaca.size()!=0){
            Kupac.ID = Kupac.lista_kupaca.size();
            Kupac.ID++;
            
        }
        else{
            Kupac.ID =0;
            Kupac.ID++;
        }
        this.id_kupca = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datum_rodjenja = datum;
        this.telefon = telefon;
        this.lozinka1 = lozinka1;
        this.lozinka2 = lozinka2;
        this.mail = mail;
    }
    
    public Kupac() {
        Kupac.ID++;
        this.id_kupca = ID;
        boolean provera_ime = false;
        boolean provera_prezime = false;
        boolean provera_datum = false;
        boolean provera_telefon = false;
        boolean provera_lozinka1 = false;
        boolean provera_lozinka2 = false;
        boolean provera_mail = false;
        Scanner skener = new Scanner(System.in);
        System.out.println("Unesite Vase ime:");
        while(!provera_ime){
            String ime = skener.next();
            if(Kupac.provera_string(ime)){
                System.out.println("Ime je dodato");
                this.ime = ime;
                provera_ime = true;
            }
            else{
                System.out.println("Neuspesno dodavanje imena");
            }
        }
        skener = new Scanner(System.in);
        System.out.println("Unesite Vase prezime:");
        while(!provera_prezime){
            String prezime = skener.next();
            if(Kupac.provera_string(prezime)){
                System.out.println("Prezime je dodato");
                this.prezime = prezime;
                provera_prezime = true;
            }
            else{
                System.out.println("Neuspesno dodavanje prezimena");
            }
        }
        skener = new Scanner(System.in);
        System.out.println("Unesite datum rodjenja:");
        while(!provera_datum){
            String datum = skener.next();
            if(Kupac.provera_datum(datum)){
                System.out.println("Datum je uspesno dodat!");
                this.datum_rodjenja = datum;
                provera_datum = true;
            }
            else{
                System.out.println("Neuspesno dodavanje datuma");
            }
        }
        skener = new Scanner(System.in);
        System.out.println("Unesite broj telefona:");
        while(!provera_telefon){
            String telefon = skener.next();
            if(Kupac.provera_telefon(telefon)){
                System.out.println("Broj telefona je uspesno dodat!");
                this.telefon = telefon;
                provera_telefon = true;
            }
            else{
                System.out.println("Neuspesno dodavanje broj telefona!");
            }
        }
        skener = new Scanner(System.in);
        System.out.println("Unesite lozinku:");
        while(!provera_lozinka1){
            String lozinka1 = skener.next();
            System.out.println("Lozinka je uspesno dodata!");
                this.lozinka1 = lozinka1;
                provera_lozinka1 = true;
        }
        skener = new Scanner(System.in);
        System.out.println("Potvrdite lozinku:");
        while(!provera_lozinka2){
            String lozinka2 = skener.next();
            if(lozinka2.equals(this.lozinka1)){
                System.out.println("Lozinka je uspesno dodata!");
                this.lozinka2 = lozinka2;
                provera_lozinka2 = true;
            }
            else{
                System.out.println("Lozinke se ne slazu!");
            }
        }
        skener = new Scanner(System.in);
        System.out.println("Unesite mejl:");
        while(!provera_mail){
            String mail = skener.next();
            if(Kupac.provera_mail(mail)){
                System.out.println("Email adresa je uspesno dodata!");
                this.mail = mail;
                provera_mail = true;
            }
            else{
                System.out.println("Neuspesno dodavanje email-a!");
            }
        }
        if(provera_lozinka1&&provera_lozinka2&&provera_mail){
            this.registrovan = true;
            System.out.println("Uspesno ste registrovani!");
            System.out.println(this.kupac_info());
        }
        
        
            
        
    }
    public void prijavi_se(String unet_email,String uneta_lozinka){
        if(this.registrovan){
            if(this.mail == unet_email && this.lozinka1 == uneta_lozinka){
                this.prijavljen = true;
                System.out.println("Uspesno ste prijavljeni");
            }
            else{
                System.out.println("Neuspesna prijava");
            }
            
        }
        else{
            this.prijavljen = false;
            System.out.println("Morate se registrovati");
        }
    }
        
        
    
    public static boolean provera_string(String uzorak){
        if(uzorak.isEmpty()){
            return false;
        }
        for(char c: uzorak.toCharArray()){
            if(Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
    public static boolean provera_mail(String provera_email){
        Pattern patern_mail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patern_mail.matcher(provera_email);
        return  matcher.find();
        
    }
    public static boolean autentican_mail(String mail){
        for(Kupac k : Kupac.vrati_listu_kupaca()){
            if(k.getMail() == mail){
                return false;
            }
            
        }
        return true;
    }
    public static boolean provera_lozinke(String loz1, String loz2){
        if(!loz1.isEmpty() && !loz2.isEmpty() && loz1.length()>6 && loz2.length()>6&& loz1 == loz2){
            return true;
        }
        return false;
    }
    public static boolean registacija(boolean ime,boolean telefon, boolean prezime,boolean email1, boolean email2, boolean lozinka, boolean datum_rodjenja){
        if(ime && prezime && lozinka && email1 && email2&& datum_rodjenja){
            return true;
        }
        return false;
        
    }
    
    public static boolean provera_telefon(String provera_telefon){
        Pattern ptrn = Pattern.compile("^[0-9]{9,10}$");
        Matcher mat = ptrn.matcher(provera_telefon);
        return mat.find();
    }
    public int getId_kupca() {
        return id_kupca;
    }

    public void setId_kupca(int id_kupca) {
        this.id_kupca = id_kupca;
    }

    public String getDatum_rodjenja() {
        return datum_rodjenja;
    }

    public void setDatum_rodjenja(String datum_rodjenja) {
        this.datum_rodjenja = datum_rodjenja;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getLozinka1() {
        return lozinka1;
    }

    public void setLozinka1(String lozinka1) {
        this.lozinka1 = lozinka1;
    }

    public String getLozinka2() {
        return lozinka2;
    }

    public void setLozinka2(String lozinka2) {
        this.lozinka2 = lozinka2;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
   

    
    @Override
    public String getIme() {
        return ime;
    }

    @Override
    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public String getPrezime() {
        return prezime;
    }
    @Override
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    
    public String kupac_info(){
        return "id_kupca: " + this.id_kupca +" Ime: "+this.ime +" Prezime: " + this.prezime+ " datum_rodjenja: " + datum_rodjenja + " telefon: " + telefon + " email: " + mail+" lozinka: "+lozinka1;
    }
    public String upisi_info(){
        return this.id_kupca+" "+this.ime+" "+this.prezime+" "+this.datum_rodjenja+" "+this.telefon+" "+this.lozinka1+" "+this.mail;
    }
    public static void dodaj_kupca(Kupac novi_kupac){
        boolean postoji_mejl= false;
        if(Kupac.vrati_listu_kupaca().size()!=0){
            for(Kupac k : Kupac.vrati_listu_kupaca()){
                if(k.getMail() == novi_kupac.getMail()){
                    postoji_mejl = true;
                }
            }
            if(postoji_mejl){
                System.out.println("Greska pri dodavanju kupca, mejl vec postoji!");
            }
            else{
                Kupac.lista_kupaca.add(novi_kupac);
                Kupac.upisi_kupca();
                System.out.println("Kupac je uspesno dodat!");
            }
        }
        else{
            Kupac.lista_kupaca.add(novi_kupac);
            Kupac.upisi_kupca();
            System.out.println("Kupac je uspesno dodat!");
        }
    }
    public static void ukloni_kupca(Kupac kupac){
        Kupac.lista_kupaca.remove(kupac);
        System.out.println("Kupac: " + kupac.kupac_info()+ " je uspesno uklonjen.");
        Kupac.upisi_kupca();
    }
    public static List<Kupac> vrati_listu_kupaca(){
      
        return Kupac.lista_kupaca;
       
    }

    
    public static boolean provera_datum(String datum) {
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        
        try{
            
            Date provera = format.parse(datum);
            
            return true;
        }catch(Exception e){
            return false;
        }
        
                
    }

    
    public void dodaj_rezervaciju(Rezervacija nova_rezervacija){
        if(this.prijavljen){
            this.lista_rezervacija.add(nova_rezervacija);
            System.out.println(nova_rezervacija.rezervacija_info()+" je uspesno dodata.");
            
        }
        else{
            System.out.println("Morate se prijaviti pre nego sto rezervisete projekciju");
        }
    }
    public static void upisi_kupca(){
        String putanja = "kupac.txt";
        try{
            PrintWriter pw = new PrintWriter(putanja);
            for(Kupac k: Kupac.vrati_listu_kupaca()){
                pw.println(k.upisi_info());
            }
            pw.flush();
            pw.close();
            System.out.println("Uspesno su uspisani!");
        }catch(Exception e){
            System.out.println("Doslo je do greske!");
        }
    }
    public static void dodaj_listu_kupaca(List<Kupac>lista){
        Kupac.lista_kupaca = lista;
    }
    public static List<Kupac> ispisi_kupce(){
        List<Kupac> lista_kupaca = new ArrayList<Kupac>();
        try{
            FileInputStream fr = new FileInputStream("kupac.txt");
            Scanner sc = new Scanner(fr);
            while(sc.hasNext()){
                int id = sc.nextInt();
                String ime = sc.next();
                String prezime = sc.next();
                String datum = sc.next();
                String telefon = sc.next();
                String lozinka = sc.next();
                String mail = sc.nextLine();
                Kupac procitan_kupac = new Kupac(id,ime,prezime,datum,telefon,lozinka,lozinka,mail);
                lista_kupaca.add(procitan_kupac);
                
            }
            fr.close();
            sc.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lista_kupaca;
        
        
    }    
    
}