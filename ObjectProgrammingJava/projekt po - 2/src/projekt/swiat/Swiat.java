package projekt.swiat;

import projekt.Organizm;
import projekt.obsluga.Obsluga_okienka;
import projekt.obsluga.Para;
import projekt.rosliny.*;
import projekt.zwierzeta.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.chrono.ChronoZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Swiat {
    final private static int MAX_ORGANIZOW = 10;
    private int sizeX;
    private int sizeY;
    private Vector<Organizm> organizmy;
    private Vector<String> komentarz;
    private Organizm[][] swiatOrganizmow;
    private int ostatniKlawisz;
    private int ktoraTura;
    private int ktoraTuraUmiejetnosci;
    private boolean czyUmiejetnosc;

    public Swiat(){
        sizeX = 20;
        sizeY = 20;
    }

    public Swiat(int x, int y){
        sizeX = x;
        sizeY = y;
        ostatniKlawisz = -1;
        ktoraTura = 0;
        ktoraTuraUmiejetnosci = 0;
        czyUmiejetnosc = false;
        swiatOrganizmow = new Organizm[x][y];
        organizmy = new Vector<>();
        komentarz = new Vector<>();
    }

    public Swiat(int x, int y, int ktoraTura, int ktoraTuraUmiejetnosci, boolean czyUmiejetnosc){
        sizeX = x;
        sizeY = y;
        ostatniKlawisz = -1;
        this.ktoraTura = ktoraTura;
        this.ktoraTuraUmiejetnosci = ktoraTuraUmiejetnosci;
        this.czyUmiejetnosc = czyUmiejetnosc;
        swiatOrganizmow = new Organizm[x][y];
        organizmy = new Vector<>();
        komentarz = new Vector<>();
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public Vector<Organizm> getOrganizmy() {
        return organizmy;
    }

    public void setOrganizmy(Vector<Organizm> organizmy) {
        this.organizmy = organizmy;
    }

    public Organizm[][] getSwiatOrganizmow() {
        return swiatOrganizmow;
    }

    public void setSwiatOrganizmow(Organizm[][] swiatOrganizmow) {
        this.swiatOrganizmow = swiatOrganizmow;
    }

    public void ustawPozycje(Organizm organizm){
        String status = "";
        Vector<String> temp = komentarz;
        Random random = new Random();
        int x = random.nextInt(sizeX);
        int y = random.nextInt(sizeY);
        if (swiatOrganizmow[x][y] == null) {
            organizm.setPolozenieX(x);
            organizm.setPolozenieY(y);
            swiatOrganizmow[x][y] = organizm;
            dodajOrganizm(organizm, false);
        }
        else {
            status = "Nie udalo sie dodac organizmu: " + organizm.Wypisz();
            temp.add(status);
        }
    }

    public void dodajOrganizm(Organizm org, boolean czyKlonowanie){
        organizmy.add(org);
        if(!czyKlonowanie){
            sortowanieList();
        }
    }

    public void sortowanieList(){
        Comparator<Organizm> porownywarka = Comparator.comparing(Organizm::getInicjatywa).thenComparing(Organizm::getWiek).reversed();
        organizmy = organizmy.stream().sorted(porownywarka).collect(Collectors.toCollection(Vector::new));
    }

    public Vector<String> getKomentarz() {
        return komentarz;
    }

    public void wykonajTure(){
        ktoraTura++;
        if(komentarz != null){
            komentarz.clear();
        }
        wykonajAkcje();
        usunMarteOrganizmy();
    }

    public void wykonajAkcje(){
        int dlugosc = organizmy.size();
        for(int i = 0; i<dlugosc; i++){
            if(organizmy.get(i).isCzyZyje()){
                organizmy.get(i).akcja();
            }
        }
    }

    public void usunMarteOrganizmy(){
        for(int i = 0; i<organizmy.size(); i++){
            if(!organizmy.get(i).isCzyZyje()){
                organizmy.remove(organizmy.get(i));
            }
        }
        sortowanieList();
    }

    public void nowySwiat(int ile) {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                swiatOrganizmow[i][j] = null;
            }
        }

        if (ile > sizeX * sizeY) {
            System.out.println("Za duzo organizmow");
            System.exit(0);
        }
        ustawPozycje(new Czlowiek(this, -100, -100, true));
        for (int i = 0; i < ile-1; i++) {
            Random random = new Random();
            int ktory = random.nextInt(MAX_ORGANIZOW);
            switch (ktory) {
                case 4 -> ustawPozycje(new Wilk(this, -100, -100, true));
                case 1 -> ustawPozycje(new Owca(this, -100, -100, true));
                case 2 -> ustawPozycje(new Lis(this, -100, -100, true));
                case 3 -> ustawPozycje(new Antylopa(this, -100, -100, true));
                case 0 -> ustawPozycje(new Zolw(this, -100, -100, true));
                case 5 -> ustawPozycje(new Trawa(this, -100, -100, true));
                case 6 -> ustawPozycje(new Mlecz(this, -100, -100, true));
                case 7-> ustawPozycje(new Jagody(this, -100, -100, true));
                case 8-> ustawPozycje(new Barszcz(this, -100, -100, true));
                case 9->ustawPozycje(new Guarana(this, -100, -100, true));
                default -> System.exit(0);
            }
        }
    }

    public Vector<Para> znajdzPuste(int x, int y) {
        Vector<Para> vect = new Vector<>();
        if (x - 1 >= 0 && y - 1 > 0 && swiatOrganizmow[x - 1][y - 1] == null) {
            vect.add(new Para(x-1, y-1));
        }
        if (x - 1 >= 0 && swiatOrganizmow[x - 1][y] == null) {
            vect.add(new Para(x-1,y));
        }
        if (x - 1 >= 0 && y + 1 < sizeY && swiatOrganizmow[x - 1][y + 1] == null) {
            vect.add(new Para(x-1,y +1));
        }
        if (y - 1 >= 0 && swiatOrganizmow[x][y - 1] == null) {
            vect.add(new Para(x,y - 1));
        }
        if (y + 1 < sizeY && swiatOrganizmow[x][y + 1] == null) {
            vect.add(new Para(x,y +1));
        }
        if (x + 1 < sizeX && y - 1 >= 0 && swiatOrganizmow[x + 1][y - 1] == null) {
            vect.add(new Para(x+1,y - 1));
        }
        if (x + 1 < sizeX && swiatOrganizmow[x + 1][y] == null) {
            vect.add(new Para(x+1,y));
        }
        if (x + 1 < sizeX && y + 1 < sizeY && swiatOrganizmow[x + 1][y + 1] == null) {
            vect.add(new Para(x+1,y + 1));
        }
        return vect;
    }

    public int getOstatniKlawisz() {
        return ostatniKlawisz;
    }

    public void setOstatniKlawisz(int ostatniKlawisz) {
        this.ostatniKlawisz = ostatniKlawisz;
    }

    public boolean isCzyUmiejetnosc() {
        return czyUmiejetnosc;
    }

    public void setCzyUmiejetnosc(boolean czyUmiejetnosc) {
        this.czyUmiejetnosc = czyUmiejetnosc;
    }

    public int getKtoraTuraUmiejetnosci() {
        return ktoraTuraUmiejetnosci;
    }

    public void setKtoraTuraUmiejetnosci(int ktoraTuraUmiejetnosci) {
        this.ktoraTuraUmiejetnosci = ktoraTuraUmiejetnosci;
    }

    public int getKtoraTura() {
        return ktoraTura;
    }

    public void setKtoraTura(int ktoraTura) {
        this.ktoraTura = ktoraTura;
    }

    public void zapiszSwiat(String nazwa) throws IOException {
        nazwa += ".txt";

        File wyjscie = new File(nazwa);
        PrintWriter zapis = new PrintWriter(nazwa, StandardCharsets.UTF_8);
        int bolToInt = czyUmiejetnosc ? 1 : 0;
        zapis.println(sizeX + " " + sizeY + " " + ktoraTura + " " + ktoraTuraUmiejetnosci + " " + bolToInt);
        zapis.println(organizmy.size());
        for (Organizm organizm : organizmy) {
            zapis.println(organizm.getOznaczenie() + " " + organizm.getPolozenieX() + " " + organizm.getPolozenieY()
                    + " " + organizm.getSila() + " " + organizm.getInicjatywa() + " " + organizm.getCzyMozeRozmnazac()
                    + " " + organizm.getWiek());
        }
        zapis.close();
    }

    public void  wczytajSwiat(Scanner wczytanyPlik) throws FileNotFoundException {
        int ile, x, y, sila, inicjatywa, czyMozeRozmnazac, wiek;
        char oznaczenie;
        ile = wczytanyPlik.nextInt();
        for (int i = 0; i < ile; i++) {
            oznaczenie = wczytanyPlik.next().charAt(0);
            x = wczytanyPlik.nextInt();
            y = wczytanyPlik.nextInt();
            sila = wczytanyPlik.nextInt();
            inicjatywa = wczytanyPlik.nextInt();
            czyMozeRozmnazac = wczytanyPlik.nextInt();
            wiek = wczytanyPlik.nextInt();
            switch (oznaczenie) {
                case 'A' -> ustawOrganizm(new Antylopa(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
                case 'b' -> ustawOrganizm(new Barszcz(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
                case 'C' -> ustawOrganizm(new Czlowiek(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
                case 'g' -> ustawOrganizm(new Guarana(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
                case 'L' -> ustawOrganizm(new Lis(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
                case 'm' -> ustawOrganizm(new Mlecz(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
                case 'O' -> ustawOrganizm(new Owca(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
                case 't' -> ustawOrganizm(new Trawa(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
                case 'j' -> ustawOrganizm(new Jagody(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
                case 'W' -> ustawOrganizm(new Wilk(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
                case 'Z' -> ustawOrganizm(new Zolw(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
                default -> {
                    System.out.println("Bledny format pliku, wychodze z programu");
                    System.exit(0);
                }
            }
        }
        wczytanyPlik.close();
    }

    private void ustawOrganizm(Organizm organizm, int pozycjaX, int pozycjaY, int sila, int inicjatywa, int czyMozeRozmnazac, int wiek) {
        organizm.setCzyMozeRozmnazac(czyMozeRozmnazac);
        organizm.setPolozenieX(pozycjaX);
        organizm.setPolozenieY(pozycjaY);
        organizm.setSila(sila);
        organizm.setInicjatywa(inicjatywa);
        organizm.setWiek(wiek);
        swiatOrganizmow[pozycjaX][pozycjaY] = organizm;
        dodajOrganizm(organizm, false);
    }
}
