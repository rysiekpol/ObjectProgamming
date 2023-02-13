import Zwierzeta
import Rosliny


class generatorOrganizmow:

    def klonowanie(self, organizm, swiat):
        if type(organizm) is Zwierzeta.wilk.Wilk:
            return Zwierzeta.wilk.Wilk(swiat, -1, -1, True)
        if type(organizm) is Zwierzeta.owca.Owca:
            return Zwierzeta.owca.Owca(swiat, -1, -1, True)
        if type(organizm) is Zwierzeta.antylopa.Antylopa:
            return Zwierzeta.antylopa.Antylopa(swiat, -1, -1, True)
        if type(organizm) is Zwierzeta.lis.Lis:
            return Zwierzeta.lis.Lis(swiat, -1, -1, True)
        if type(organizm) is Zwierzeta.zolw.Zolw:
            return Zwierzeta.zolw.Zolw(swiat, -1, -1, True)
        if type(organizm) is Rosliny.trawa.Trawa:
            return Rosliny.trawa.Trawa(swiat, -1, -1, True)
        if type(organizm) is Rosliny.mlecz.Mlecz:
            return Rosliny.mlecz.Mlecz(swiat, -1, -1, True)
        if type(organizm) is Rosliny.guarana.Guarana:
            return Rosliny.guarana.Guarana(swiat, -1, -1, True)
        if type(organizm) is Rosliny.jagody.Jagody:
            return Rosliny.jagody.Jagody(swiat, -1, -1, True)
        if type(organizm) is Rosliny.barszcz.Barszcz:
            return Rosliny.barszcz.Barszcz(swiat, -1, -1, True)
        if type(organizm) is Zwierzeta.cyberOwca.CyberOwca:
            return Zwierzeta.cyberOwca.CyberOwca(swiat, -1, -1, True)
