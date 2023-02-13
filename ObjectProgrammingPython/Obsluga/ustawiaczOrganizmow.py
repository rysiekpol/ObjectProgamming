import Zwierzeta
import Rosliny


class UstawiaczOrganizmow:

    def klonowanie(self, nazwa, swiat, x, y):
        if nazwa == "Wilk":
            return Zwierzeta.wilk.Wilk(swiat, x, y, True)
        if nazwa == "Owca":
            return Zwierzeta.owca.Owca(swiat, x, y, True)
        if nazwa == "Antylopa":
            return Zwierzeta.antylopa.Antylopa(swiat, x, y, True)
        if nazwa == "Lis":
            return Zwierzeta.lis.Lis(swiat, x, y, True)
        if nazwa == "Zolw":
            return Zwierzeta.zolw.Zolw(swiat, x, y, True)
        if nazwa == "Trawa":
            return Rosliny.trawa.Trawa(swiat, x, y, True)
        if nazwa == "Mlecz":
            return Rosliny.mlecz.Mlecz(swiat, x, y, True)
        if nazwa == "Guarana":
            return Rosliny.guarana.Guarana(swiat, x, y, True)
        if nazwa == "Jagody":
            return Rosliny.jagody.Jagody(swiat, x, y, True)
        if nazwa == "Barszcz":
            return Rosliny.barszcz.Barszcz(swiat, x, y, True)
        if nazwa == "Cyber owca":
            return Zwierzeta.cyberOwca.CyberOwca(swiat, x, y, True)
