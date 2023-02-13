import random

from Rosliny.roslina import Roslina


class Jagody(Roslina):
    def __init__(self, Swiat, x, y, czyZyje):
        super(Jagody, self).__init__(Swiat, x, y, czyZyje)
        self.sila = 99
        self.inicjatywa = 0
        self.kolor = "#46008C"
        self.oznaczenie = "j"
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
        status = atakujacy.Wypisz() + " ZJADL " + self.Wypisz() + " I ZGINAL"
        swiatOrganizmow[atakujacy.getPolozenieX()][atakujacy.getPolozenieY()] = None
        atakujacy.setCzyZyje(False)
        kom.append(status)

    def Wypisz(self):
        return "Wilcze jagody" + self.koncowka()



