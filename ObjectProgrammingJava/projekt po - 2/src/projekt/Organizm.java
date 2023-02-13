package projekt;

import projekt.swiat.Swiat;

import java.awt.*;

public abstract class Organizm implements Wypisanie{
    protected int sila;
    protected int wiek;
    protected int inicjatywa;
    protected int polozenieX;
    protected int polozenieY;
    protected char oznaczenie;
    protected boolean czyZyje;
    protected int czyMozeRozmnazac;
    protected Color kolor;
    protected Swiat swiat;
    final static protected int MIN_ZYCIA_ABY_URODZIC = 5;
    public abstract String Wypisz();

    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public int getInicjatywa() {
        return inicjatywa;
    }

    public void setInicjatywa(int inicjatywa) {
        this.inicjatywa = inicjatywa;
    }

    public int getPolozenieX() {
        return polozenieX;
    }

    public void setPolozenieX(int polozenieX) {
        this.polozenieX = polozenieX;
    }

    public int getPolozenieY() {
        return polozenieY;
    }

    public void setPolozenieY(int polozenieY) {
        this.polozenieY = polozenieY;
    }

    public char getOznaczenie() {
        return oznaczenie;
    }

    public void setOznaczenie(char oznaczenie) {
        this.oznaczenie = oznaczenie;
    }

    public boolean isCzyZyje() {
        return czyZyje;
    }

    public void setCzyZyje(boolean czyZyje) {
        this.czyZyje = czyZyje;
    }

    public int getCzyMozeRozmnazac() {
        return czyMozeRozmnazac;
    }

    public void setCzyMozeRozmnazac(int czyMozeRozmnazac) {
        this.czyMozeRozmnazac = czyMozeRozmnazac;
    }

    public boolean czyOdbilAtak(Organizm atakujacy){
        return false;
    };

    public abstract void akcja();
    public abstract void kolizja(Organizm atakujacy);

    public void przygotujOrganizm(){
        wiek++;
        czyMozeRozmnazac++;
    }

    public Color getKolor() {
        return kolor;
    }

    public void setKolor(Color color) {
        kolor = color;
    }

    protected String getKoncowka(){
        return " na pozycji " + String.valueOf(polozenieX)
                + ":" + String.valueOf(polozenieY) + " o sile: " + String.valueOf(sila)
                + " inicjatywie: " + String.valueOf(inicjatywa) + " wiek:" + String.valueOf(wiek);
    }
}
