package Sala;


import Kupac.Kupac;
import Rezervacija.Rezervacija;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sala {
    private static int ID;
    private int id_sale;
    private int broj_sale;
    private int broj_mesta;
    private static List<Sala> lista_sala = new ArrayList<Sala>();
    public Sala(int id, int broj_sale,int broj_mesta){
        this.id_sale = id;
        this.broj_sale = broj_sale;
        this.broj_mesta = broj_mesta;
    }
    public Sala() {
        if(Sala.lista_sala.size()!=0){
            Sala.ID = Sala.lista_sala.size();
            Sala.ID++;
            
        }
        else{
            Sala.ID =0;
            Sala.ID++;
        }
        this.id_sale = Sala.ID;
        Scanner skener = new Scanner(System.in);
        boolean provera_broj_sale = false;
        boolean provera_broj_mesta = false;
        System.out.println("Unesite broj sale:");
        while(!provera_broj_sale){
            try{
                int broj_sale = Integer.parseInt(skener.next());
                if(broj_sale >0){
                    System.out.println("Uspesno ste dodali broj sale!");
                    this.broj_sale = broj_sale;
                    provera_broj_sale = true;
                }
                else{
                    System.out.println("Broj sale mora da bude pozitivan broj veci od 0");
                }
            }catch(Exception e){
                System.out.println("Greska! Morate uneti broj.");
            }
        }
        skener = new Scanner(System.in);
        System.out.println("Unesite broj mesta:");
        while(!provera_broj_mesta){
            try{
                int broj_mesta = Integer.parseInt(skener.next());
                if(broj_mesta >=0){
                    System.out.println("Uspesno ste dodali broj mesta!");
                    this.broj_mesta = broj_mesta;
                    provera_broj_mesta = true;
                }
                else{
                    System.out.println("Broj mesta mora da bude pozitivan broj.");
                }
            }catch(Exception e){
                System.out.println("Greska! Morate uneti broj.");
            }
        }
        System.out.println("Sala:" + this.sala_info() + " je uspesno dodata.");
    }
    
    public String sala_info(){
        return id_sale +" "+ broj_sale + " "+ broj_mesta;
    }
    
    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Sala.ID = ID;
    }

    public int getId_sala() {
        return id_sale;
    }

    public void setId_sala(int id_sala) {
        this.id_sale = id_sala;
    }

    public int getBroj_sale() {
        return this.broj_sale;
    }

    public void setBroj_sale(int broj_sale) {
        this.broj_sale = broj_sale;
    }

    public int getBroj_mesta() {
        return this.broj_mesta;
    }

    public void setBroj_mesta(int broj_mesta) {
        this.broj_mesta = broj_mesta;
    }
    public static void dodaj_salu(Sala nova_sala){
        Sala.lista_sala.add(nova_sala);
        System.out.println("Sala: " + nova_sala.sala_info()+ " je dodata.");
        Sala.upisi_salu();
    }
    public static void ukloni_salu(Sala sala){
        Sala.lista_sala.remove(sala);
        System.out.println("Sala: " + sala.sala_info()+ " je uspesno uklonjena.");
        Sala.upisi_salu();
    }
    public static List<Sala> vrati_listu_sala(){
        return Sala.lista_sala;
    }
    public static void dodaj_listu_sala(List<Sala> lista_sala){
        Sala.lista_sala = lista_sala;
    }
    public static void upisi_salu(){
        String putnja = "sala.txt";
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(putnja);
            for(Sala s:Sala.vrati_listu_sala()){
                pw.println(s.sala_info());
            }
            pw.flush();
            pw.close();
            System.out.println("Sale su uspesno upisane!");
        }catch(Exception e){
            System.out.println("Greska!");
        }
    }
    public static List<Sala> ispisi_salu(){
        List<Sala> lista_sala = new ArrayList<Sala>();
        try{
            FileInputStream fr = new FileInputStream("sala.txt");
            Scanner sc = new Scanner(fr);
            while(sc.hasNext()){
                int id = sc.nextInt();
                int broj_sale = sc.nextInt();
                int broj_mesta = sc.nextInt();
                Sala procitana_sala = new Sala(id,broj_sale,broj_mesta);
                lista_sala.add(procitana_sala);
                
            }
            fr.close();
            sc.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lista_sala;
        
        
    }    
    
    
}
