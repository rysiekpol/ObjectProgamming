package projekt.obsluga;

import projekt.swiat.Swiat;

public class ProjektJava {
    public static void main(String[] args) {
        Obsluga_okienka obsluga = new Obsluga_okienka(new Swiat(5,5));
        obsluga.OknoStartowe();
    }
}
