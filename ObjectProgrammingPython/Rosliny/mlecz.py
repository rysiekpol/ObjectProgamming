import random

from Rosliny.roslina import Roslina


class Mlecz(Roslina):
    def __init__(self, Swiat, x, y, czyZyje):
        super(Mlecz, self).__init__(Swiat, x, y, czyZyje)
        self.sila = 0
        self.inicjatywa = 0
        self.kolor = "lightblue"
        self.oznaczenie = "m"
        self.czyMozeRozmnazac = 0
        self.wiek = 0

    def akcja(self):
        self.przygotujOrganizm()
        for i in range(3):
            losuj = random.randint(0,100)
            if losuj > 96:
                super().akcja()

    def Wypisz(self):
        return "Mlecz" + self.koncowka()



