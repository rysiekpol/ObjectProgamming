package projekt.obsluga;

import projekt.Organizm;
import projekt.rosliny.*;
import projekt.swiat.Swiat;
import projekt.zwierzeta.*;

import java.util.zip.ZipError;

public class GeneratorOrganizmow {

    public Organizm klonowanie(Organizm org, Swiat swiat){
        if(org instanceof Wilk) {
            return new Wilk(swiat, -1, -1, true);
        }
        if(org instanceof Owca) {
            return new Owca(swiat, -1, -1, true);
        }
        if(org instanceof Lis) {
            return new Lis(swiat, -1, -1, true);
        }
        if(org instanceof Antylopa) {
            return new Antylopa(swiat, -1, -1, true);
        }
        if(org instanceof Zolw) {
            return new Zolw(swiat, -1, -1, true);
        }
        if(org instanceof Trawa) {
            return new Trawa(swiat, -1, -1, true);
        }
        if(org instanceof Mlecz) {
            return new Mlecz(swiat, -1, -1, true);
        }
        if(org instanceof Jagody) {
            return new Jagody(swiat, -1, -1, true);
        }
        if(org instanceof Barszcz) {
            return new Barszcz(swiat, -1, -1, true);
        }
        if(org instanceof Guarana) {
            return new Guarana(swiat, -1, -1, true);
        }
        return null;
    }
}
