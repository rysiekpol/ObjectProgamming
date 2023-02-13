import random

from Rosliny.roslina import Roslina


class Trawa(Roslina):
    def __init__(self, Swiat, x, y, czyZyje):
        super(Trawa, self).__init__(Swiat, x, y, czyZyje)
        self.sila = 0
        self.inicjatywa = 0
        self.kolor = "lightgreen"
        self.oznaczenie = "t"
        self.czyMozeRozmnazac = 0
        self.wiek = 0

    def akcja(self):
        self.przygotujOrganizm()
        losuj = random.randint(0,100)
        if losuj > 95:
            super().akcja()

    def Wypisz(self):
        return "Trawa" + self.koncowka()



