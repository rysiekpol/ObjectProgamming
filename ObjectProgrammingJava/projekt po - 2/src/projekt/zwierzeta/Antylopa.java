package projekt.zwierzeta;

import projekt.Organizm;
import projekt.obsluga.GeneratorOrganizmow;
import projekt.obsluga.Para;
import projekt.swiat.Swiat;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class Antylopa extends Zwierze {
    public Antylopa(Swiat swiat, int x, int y, boolean czyZyje){
        polozenieX = x;
        polozenieY = y;
        sila = 4;
        inicjatywa = 4;
        kolor = Color.red;
        oznaczenie = 'A';
        this.swiat = swiat;
        this.czyZyje = czyZyje;
        this.czyMozeRozmnazac = 0;
        wiek = 0;
    }

    @Override
    public void akcja() {
        przygotujOrganizm();
        Random random = new Random();
        String status = "";
        int ruch = random.nextInt(4);
        Vector<String> kom = swiat.getKomentarz();
        Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
        switch (ruch){
            case 0:
                if (polozenieX+2 < swiat.getSizeX()) {
                    if(swiatOrganizmow[polozenieX+2][polozenieY] != null){
                        swiatOrganizmow[polozenieX+2][polozenieY].kolizja(this);
                    }else{
                        swiatOrganizmow[polozenieX][polozenieY] = null;
                        status = this.Wypisz() + " PORUSZYL SIE NA POZYCJE ";
                        polozenieX+=2;
                        status +=  String.valueOf(polozenieX)+ ":" + String.valueOf(polozenieY);
                        swiatOrganizmow[polozenieX][polozenieY] = this;
                    }
                }
                break;
            case 1:
                if (polozenieX-1 > 0) {
                    if(swiatOrganizmow[polozenieX-2][polozenieY] != null){
                        swiatOrganizmow[polozenieX-2][polozenieY].kolizja(this);
                    }else{
                        swiatOrganizmow[polozenieX][polozenieY] = null;
                        status = this.Wypisz() + " PORUSZYL SIE NA POZYCJE ";
                        polozenieX-=2;
                        status +=  String.valueOf(polozenieX)+ ":" + String.valueOf(polozenieY);
                        swiatOrganizmow[polozenieX][polozenieY] = this;
                    }
                }
                break;
            case 2:
                if (polozenieY+2 < swiat.getSizeY()) {
                    if(swiatOrganizmow[polozenieX][polozenieY+2] != null){
                        swiatOrganizmow[polozenieX][polozenieY+2].kolizja(this);
                    }else{
                        swiatOrganizmow[polozenieX][polozenieY] = null;
                        status = this.Wypisz() + " PORUSZYL SIE NA POZYCJE ";
                        polozenieY+=2;
                        status +=  String.valueOf(polozenieX)+ ":" + String.valueOf(polozenieY);
                        swiatOrganizmow[polozenieX][polozenieY] = this;
                    }
                }
                break;
            case 3:
                if (polozenieY-1 > 0) {
                    if(swiatOrganizmow[polozenieX][polozenieY-2] != null){
                        swiatOrganizmow[polozenieX][polozenieY-2].kolizja(this);
                    }else{
                        swiatOrganizmow[polozenieX][polozenieY] = null;
                        status = this.Wypisz() + " PORUSZYL SIE NA POZYCJE ";
                        polozenieY-=2;
                        status +=  String.valueOf(polozenieX)+ ":" + String.valueOf(polozenieY);
                        swiatOrganizmow[polozenieX][polozenieY] = this;
                    }
                }
                break;
        }
        if(status.isEmpty()){
            kom.add(status);
        }
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        String status = "";
        Vector<String> kom = swiat.getKomentarz();
        Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
        if(kolor != atakujacy.getKolor()){
            Random random = new Random();
            int szansa = random.nextInt(100);
            if (szansa > 50) {
                Vector<Para> para = swiat.znajdzPuste(polozenieX, polozenieY);
                if(para.size()>0) {
                    int ktore = random.nextInt(para.size());
                    swiatOrganizmow[para.get(ktore).getL()][para.get(ktore).getR()] = this;
                    swiatOrganizmow[polozenieX][polozenieY] = null;
                    polozenieX = para.get(ktore).getL();
                    polozenieY = para.get(ktore).getR();
                }else{
                    status = Wypisz() + " NIE UCIEKLA Z POWODU BRAKU MIEJSCA";
                    super.kolizja(atakujacy);
                }
            }
            else {
                status = Wypisz() + " NIE UCIEKLA Z POWODU MALEJ SZANSY";
                kom.add(status);
                super.kolizja(atakujacy);
            }
        }else{
            super.kolizja(atakujacy);
        }
}

    @Override
    public String Wypisz() {
        return "Antylopa" + getKoncowka();
    }
}
