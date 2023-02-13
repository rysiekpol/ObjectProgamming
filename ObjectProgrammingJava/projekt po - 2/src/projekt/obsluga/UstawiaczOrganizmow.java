package projekt.obsluga;

import projekt.Organizm;
import projekt.rosliny.*;
import projekt.swiat.Swiat;
import projekt.zwierzeta.*;

public class UstawiaczOrganizmow {

    public Organizm klonowanie(String nazwa, Swiat swiat, int x, int y){
        if(nazwa.equals("Wilk")) {
            return new Wilk(swiat, x, y, true);
        }
        if(nazwa.equals("Owca")) {
            return new Owca(swiat, x, y, true);
        }
        if(nazwa.equals("Lis")) {
            return new Lis(swiat, x, y, true);
        }
        if(nazwa.equals("Antylopa")) {
            return new Antylopa(swiat, x, y, true);
        }
        if(nazwa.equals("Zolw")) {
            return new Zolw(swiat, x, y, true);
        }
        if(nazwa.equals("Trawa")) {
            return new Trawa(swiat, x, y, true);
        }
        if(nazwa.equals("Mlecz")) {
            return new Mlecz(swiat, x, y, true);
        }
        if(nazwa.equals("Jagody")) {
            return new Jagody(swiat, x, y, true);
        }
        if(nazwa.equals("Barszcz")) {
            return new Barszcz(swiat, x, y, true);
        }
        if(nazwa.equals("Guarana")) {
            return new Guarana(swiat, x, y, true);
        }
        return null;
    }
}
