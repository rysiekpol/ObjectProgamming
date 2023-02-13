package projekt.zwierzeta;

import projekt.Organizm;
import projekt.Wypisanie;
import projekt.obsluga.GeneratorOrganizmow;
import projekt.obsluga.Para;

import java.util.Random;
import java.util.Vector;

public abstract class Zwierze extends Organizm implements Wypisanie {


    @Override
    public String Wypisz() {
        return null;
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
            case 1:
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
            case 2:
                if (polozenieY+1 < swiat.getSizeY()) {
                    if(swiatOrganizmow[polozenieX][polozenieY+1] != null){
                        swiatOrganizmow[polozenieX][polozenieY+1].kolizja(this);
                    }else{
                        swiatOrganizmow[polozenieX][polozenieY] = null;
                        status = this.Wypisz() + " PORUSZYL SIE NA POZYCJE ";
                        polozenieY++;
                        status +=  String.valueOf(polozenieX)+ ":" + String.valueOf(polozenieY);
                        swiatOrganizmow[polozenieX][polozenieY] = this;
                    }
                }
                break;
            case 3:
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
        }
        if(!status.equals("")){
            kom.add(status);
        }
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        String status = "";
        Vector<String> kom = swiat.getKomentarz();
        if(kolor == atakujacy.getKolor()){
            if(atakujacy.getCzyMozeRozmnazac() > MIN_ZYCIA_ABY_URODZIC && czyMozeRozmnazac > MIN_ZYCIA_ABY_URODZIC){
                atakujacy.setCzyMozeRozmnazac(0);
                czyMozeRozmnazac = 0;
                Organizm o = new GeneratorOrganizmow().klonowanie(atakujacy, swiat);
                Vector<Para> pustePola = swiat.znajdzPuste(polozenieX, polozenieY);
                if(pustePola.size() > 0){
                    Random random = new Random();
                    int ruch = random.nextInt(pustePola.size());
                    o.setPolozenieX(pustePola.get(ruch).getL());
                    o.setPolozenieY(pustePola.get(ruch).getR());
                    swiat.dodajOrganizm(o,true);
                    status+= o.Wypisz();
                    swiat.getSwiatOrganizmow()[pustePola.get(ruch).getL()][pustePola.get(ruch).getR()] = o;
                }else{
                    status = "BRAK MIEJSCA NA ROZMNOZENIE SIE " + Wypisz() + " i " + atakujacy.Wypisz();
                }
            }
        }else{
            Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
            if(atakujacy.getSila() >= sila){
                status = Wypisz() + " ZOSTAL ZABITY PRZEZ " + atakujacy.Wypisz();
                swiatOrganizmow[polozenieX][polozenieY] = atakujacy;
                swiatOrganizmow[atakujacy.getPolozenieX()][atakujacy.getPolozenieY()] = null;
                atakujacy.setPolozenieX(polozenieX);
                atakujacy.setPolozenieY(polozenieY);
                czyZyje = false;
            }else{
                status = atakujacy.Wypisz() + " ZOSTAL ZABITY PRZEZ " + Wypisz();
                swiatOrganizmow[atakujacy.getPolozenieX()][atakujacy.getPolozenieY()] = null;
                atakujacy.setCzyZyje(false);
            }
        }
        kom.add(status);
    }
}
