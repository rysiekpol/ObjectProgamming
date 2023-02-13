import random

from Obsluga.para import Para
from Zwierzeta.zwierze import Zwierze


class Zolw(Zwierze):
    def __init__(self, Swiat, x, y, czyZyje):
        super(Zolw, self).__init__(Swiat, x, y, czyZyje)
        self.sila = 2
        self.inicjatywa = 1
        self.kolor = "green"
        self.oznaczenie = "Z"
        self.czyMozeRozmnazac = 0
        self.wiek = 0

    def akcja(self):
        szansa = random.randint(0, 100)
        if szansa > 75:
            super().akcja()
        else:
            self.przygotujOrganizm()

    def kolizja(self, atakujacy):
        status = ""
        kom = self.swiat.getKomentarz()
        swiatOrganizmow = self.swiat.getSwiatOrganizmow()
        if not atakujacy.getSila() < 5 or type(atakujacy) is Zolw:
            super().kolizja(atakujacy)
        else:
            status = self.Wypisz() + " ODBIL ATAK " + atakujacy.Wypisz()
        kom.append(status)

    def Wypisz(self):
        return "Zolw" + self.koncowka()
