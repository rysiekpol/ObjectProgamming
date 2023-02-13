package projekt.zwierzeta;

import projekt.Organizm;
import projekt.Wypisanie;
import projekt.obsluga.Para;
import projekt.swiat.Swiat;

import java.awt.*;
import java.util.Vector;

public class Czlowiek extends Zwierze implements Wypisanie {
    private static final int UP = 0;
    private static final int LEFT = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;
    private static final int SPACE = 4;

    public Czlowiek(Swiat swiat, int x, int y, boolean czyZyje){
        polozenieX = x;
        polozenieY = y;
        sila = 5;
        inicjatywa = 4;
        kolor = Color.yellow;
        oznaczenie = 'C';
        this.swiat = swiat;
        this.czyZyje = czyZyje;
        this.czyMozeRozmnazac = 0;
        wiek = 0;
    }

    @Override
    public void akcja(){
        wiek++;
        ustawRuch();
        String status = "";
        Vector<String> kom = swiat.getKomentarz();
        if (swiat.isCzyUmiejetnosc()) {
            swiat.setKtoraTuraUmiejetnosci(swiat.getKtoraTuraUmiejetnosci() + 1);
            if (swiat.getKtoraTuraUmiejetnosci() < 5) {
                wywolajUmiejetnosc();
                status = Wypisz() + " WYWOLAL UMIEJETNOSC CALOPALENIE, JEST TO " + String.valueOf(swiat.getKtoraTuraUmiejetnosci())+ " TURA UMIEJETNOSCI";
            }
            else {
                status = Wypisz() + " UMIEJETNOSC SKONCZYLA SIE";
                swiat.setCzyUmiejetnosc(false);
                swiat.setKtoraTuraUmiejetnosci(0);
            }
        }
        if (!status.equals("")) {
            kom.add(status);
        }
    }

    @Override
    public String Wypisz() {
        return "Czlowiek" + getKoncowka();
    }

    private void ustawRuch(){
        Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
        int klawisz = swiat.getOstatniKlawisz();
        String status = "";
        Vector<String> kom = swiat.getKomentarz();
        switch (klawisz)
        {
            case UP:
                if (polozenieX > 0) {
                    if(swiatOrganizmow[polozenieX-1][polozenieY] != null){
                        swiatOrganizmow[polozenieX-1][polozenieY].kolizja(this);
                    }else{
                        swiatOrganizmow[polozenieX][polozenieY] = null;
                        status = this.Wypisz() + " PORUSZYL SIE NA POZYCJE ";
                        polozenieX--;
                        status +=  String.valueOf(polozenieX)+ ":" + String.valueOf(polozenieY);
                        swiatOrganizmow[polozenieX][polozenieY] = this;
                    }
                }
                break;
            case RIGHT:
                if (polozenieY+1 < swiat.getSizeY()) {
                    if(swiatOrganizmow[polozenieX][polozenieY+1] != null){
                        swiatOrganizmow[polozenieX][polozenieY+1].kolizja(this);
                    }else {
                        swiatOrganizmow[polozenieX][polozenieY] = null;
                        status = this.Wypisz() + " PORUSZYL SIE NA POZYCJE ";
                        polozenieY++;
                        status += String.valueOf(polozenieX) + ":" + String.valueOf(polozenieY);
                        swiatOrganizmow[polozenieX][polozenieY] = this;
                    }
                }
                break;
            case DOWN:
                if (polozenieX+1 < swiat.getSizeX()) {
                    if(swiatOrganizmow[polozenieX+1][polozenieY] != null){
                        swiatOrganizmow[polozenieX+1][polozenieY].kolizja(this);
                    }else{
                        swiatOrganizmow[polozenieX][polozenieY] = null;
                        status = this.Wypisz() + " PORUSZYL SIE NA POZYCJE ";
                        polozenieX++;
                        status +=  String.valueOf(polozenieX)+ ":" + String.valueOf(polozenieY);
                        swiatOrganizmow[polozenieX][polozenieY] = this;
                    }
                }
                break;
            case LEFT:
                if (polozenieY > 0) {
                    if(swiatOrganizmow[polozenieX][polozenieY-1] != null){
                        swiatOrganizmow[polozenieX][polozenieY-1].kolizja(this);
                    }else{
                        swiatOrganizmow[polozenieX][polozenieY] = null;
                        status = this.Wypisz() + " PORUSZYL SIE NA POZYCJE ";
                        polozenieY--;
                        status +=  String.valueOf(polozenieX)+ ":" + String.valueOf(polozenieY);
                        swiatOrganizmow[polozenieX][polozenieY] = this;
                    }
                }
                break;
            case SPACE:
                swiat.setCzyUmiejetnosc(true);
                break;
            default:
                break;
        }
        if (!status.equals("")) {
            kom.add(status);
        }
    }

    private void wywolajUmiejetnosc(){
        Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
        Vector<Para> pelnePola = znajdzPelne(polozenieX, polozenieY);
        String status;
        Vector<String>  kom = swiat.getKomentarz();
        if (pelnePola.size() > 0) {
            for (Para wsp : pelnePola) {
                status = Wypisz() + " ZABIL ZA POMOCA UMIEJETNOSCI " + swiatOrganizmow[wsp.getL()][wsp.getR()].Wypisz();
                swiatOrganizmow[wsp.getL()][wsp.getR()].setCzyZyje(false);
                swiatOrganizmow[wsp.getL()][wsp.getR()] = null;
                kom.add(status);
            }
        }
    }

    public Vector<Para> znajdzPelne(int x, int y) {
        Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
        int sizeX = swiat.getSizeX();
        int sizeY = swiat.getSizeY();
        Vector<Para> vect = new Vector<>();
        if (x - 1 >= 0 && y - 1 > 0 && swiatOrganizmow[x - 1][y - 1] != null) {
            vect.add(new Para(x-1, y-1));
        }
        if (x - 1 >= 0 && swiatOrganizmow[x - 1][y] != null) {
            vect.add(new Para(x-1,y));
        }
        if (x - 1 >= 0 && y + 1 < sizeY && swiatOrganizmow[x - 1][y + 1] != null) {
            vect.add(new Para(x-1,y +1));
        }
        if (y - 1 >= 0 && swiatOrganizmow[x][y - 1] != null) {
            vect.add(new Para(x,y - 1));
        }
        if (y + 1 < sizeY && swiatOrganizmow[x][y + 1] != null) {
            vect.add(new Para(x,y +1));
        }
        if (x + 1 < sizeX && y - 1 >= 0 && swiatOrganizmow[x + 1][y - 1] != null) {
            vect.add(new Para(x+1,y - 1));
        }
        if (x + 1 < sizeX && swiatOrganizmow[x + 1][y] != null) {
            vect.add(new Para(x+1,y));
        }
        if (x + 1 < sizeX && y + 1 < sizeY && swiatOrganizmow[x + 1][y + 1] != null) {
            vect.add(new Para(x+1,y + 1));
        }
        return vect;
    }
}
