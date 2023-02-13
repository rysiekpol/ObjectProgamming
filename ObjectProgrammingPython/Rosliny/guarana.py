import random

from Rosliny.roslina import Roslina


class Guarana(Roslina):
    def __init__(self, Swiat, x, y, czyZyje):
        super(Guarana, self).__init__(Swiat, x, y, czyZyje)
        self.sila = 0
        self.inicjatywa = 0
        self.kolor = "#8C0017"
        self.oznaczenie = "g"
        self.czyMozeRozmnazac = 0
        self.wiek = 0

    def akcja(self):
        self.przygotujOrganizm()
        losuj = random.randint(0,100)
        if losuj > 97:
            super().akcja()

    def kolizja(self, atakujacy):
        status = ""
        kom = self.swiat.getKomentarz()
        swiatOrganizmow = self.swiat.getSwiatOrganizmow()
        swiatOrganizmow[self.polozenieX][self.polozenieY] = atakujacy
        swiatOrganizmow[atakujacy.getPolozenieX()][atakujacy.getPolozenieY()] = None
        atakujacy.setPolozenieX(self.polozenieX)
        atakujacy.setPolozenieY(self.polozenieY)
        atakujacy.setSila(atakujacy.getSila()+3)
        self.czyZyje = False
        status = atakujacy.Wypisz() + " ZJADL " + self.Wypisz() + " I ZWIEKSZYL SILE O 3"
        kom.append(status)

    def Wypisz(self):
        return "Guarana" + self.koncowka()



