import sys

from Obsluga.swiat import Swiat
from Obsluga.obslugaOkienka import ObslugaOkienka
from PyQt5.QtWidgets import (QApplication)

def main():
    swiat = Swiat(5,5)
    okno = ObslugaOkienka(swiat)
    okno.show()
    return okno

if __name__ == '__main__':
    app = QApplication(sys.argv)
    aplikacja = main()
    sys.exit(app.exec_())

