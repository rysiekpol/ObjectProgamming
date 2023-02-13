import sys

from PyQt5 import sip, QtWidgets, QtCore, QtGui
from PyQt5.Qt import Qt
from PyQt5.QtWidgets import *
from PyQt5.QtGui import *
from PyQt5.QtCore import *

from Obsluga.ustawiaczOrganizmow import UstawiaczOrganizmow
from Obsluga.swiat import Swiat


class ObslugaOkienka(QDialog):
    def __init__(self, Swiat):
        super().__init__()
        self.Swiat = Swiat
        self.setWindowTitle('Maciej Szefler s188614')
        self.setGeometry(300, 200, 1000, 600)
        self.setFixedHeight(620)
        self.setFixedWidth(1000)
        self.menuBar = QMenuBar()
        self.plik = QMenu("Plik")
        self.plik.addAction('Nowy', lambda: self.nowySwiat())
        self.plik.addAction('Wczytaj', lambda: self.wczytajSwiat())
        self.plik.addAction('Zapisz', lambda: self.zapiszSwiat())
        self.plik.addAction('Wyjdz', lambda: sys.exit())
        self.menuBar.addMenu(self.plik)
        self.GlowneOkno()

    def GlowneOkno(self):
        self.hbox = QHBoxLayout()
        self.hbox.addWidget(self.PlanszaZGra())
        self.hbox.addWidget(self.Komentarz())
        self.hbox.setMenuBar(self.menuBar)
        self.setLayout(self.hbox)
        self.show()

    def PlanszaZGra(self):
        label = QLabel(self)
        label.setFixedWidth(400)
        label.setFixedHeight(580)
        grid = QGridLayout()

        grid.setSpacing(0)
        grid.setContentsMargins(0, 0, 0, 0)
        grid.setHorizontalSpacing(0)
        grid.setVerticalSpacing(0)
        swiatOrganizmow = self.Swiat.getSwiatOrganizmow()
        sizePolicy = QSizePolicy(QSizePolicy.Minimum, QSizePolicy.Minimum)
        for x in range(self.Swiat.getSizeX()):
            for y in range(self.Swiat.getSizeY()):
                przycisk = QPushButton(self)
                sizePolicy.setHeightForWidth(przycisk.sizePolicy().hasHeightForWidth())
                przycisk.setSizePolicy(sizePolicy)
                if swiatOrganizmow[x][y] is not None:
                    przycisk.setStyleSheet(
                        "background-color: " + swiatOrganizmow[x][y].getKolor() + "; border: 1px solid white;")
                else:
                    przycisk.clicked.connect(lambda state, xx=x, yy=y: self.handleButton(xx, yy))
                grid.addWidget(przycisk, x, y)

        label.setLayout(grid)
        return label

    def Komentarz(self):
        font = QtGui.QFont()
        font.setPointSize(10)

        label2 = QTextEdit(self)
        label2.setFixedWidth(520)
        label2.setFixedHeight(580)
        # label2.setStyleSheet("background-color: gray;")
        komentarz = "Tura: " + str(self.Swiat.getKtoraTura()) + "\n\n"
        for kom in self.Swiat.getKomentarz():
            if (kom != ""):
                komentarz += kom + '\n'
        label2.setText(komentarz)
        label2.setVerticalScrollBarPolicy(Qt.ScrollBarAsNeeded)
        label2.setHorizontalScrollBarPolicy(Qt.ScrollBarAsNeeded)
        label2.setLineWrapMode(QTextEdit.NoWrap)
        label2.setReadOnly(True)
        label2.setFont(font)
        return label2

    def nowySwiat(self):
        while True:
            widget = QWidget()
            swiatX, czy1 = QtWidgets.QInputDialog.getInt(widget, 'Podaj dane', 'Podaj rozmiar X swiata:')
            swiatY, czy2 = QtWidgets.QInputDialog.getInt(widget, 'Podaj dane', 'Podaj rozmiar Y swiata:')
            ile, czy3 = QtWidgets.QInputDialog.getInt(widget, 'Podaj dane', 'Podaj ilosc organizmow na swiecie:')
            if czy1 and czy2 and czy3:
                if 0 < swiatX <= 50 and 0 < swiatY <= 50 and 0 < ile:
                    self.Swiat = Swiat(swiatX, swiatY)
                    self.Swiat.nowySwiat(ile)
                    self.deleteLayout(self.layout())
                    self.GlowneOkno()
                    break

    def zapiszSwiat(self):
        widget = QWidget()
        swiatX, czy1 = QtWidgets.QInputDialog.getText(widget, 'Podaj dane', 'Podaj nazwe pliku do zapisania:')
        if czy1:
            self.Swiat.zapiszSwiat(swiatX)

    def keyPressEvent(self, e):
        if e.key() == QtCore.Qt.Key_Up:
            self.Swiat.setOstatniKlawisz(0)
        elif e.key() == QtCore.Qt.Key_Down:
            self.Swiat.setOstatniKlawisz(1)
        elif e.key() == QtCore.Qt.Key_Left:
            self.Swiat.setOstatniKlawisz(2)
        elif e.key() == QtCore.Qt.Key_Right:
            self.Swiat.setOstatniKlawisz(3)
        elif e.key() == QtCore.Qt.Key_Space:
            self.Swiat.setOstatniKlawisz(4)
        else:
            self.Swiat.setOstatniKlawisz(-1)
        self.Swiat.wykonajTure()
        self.deleteLayout(self.layout())
        self.GlowneOkno()

    def handleButton(self, x, y):
        self.vBox = QVBoxLayout()
        self.combobox1 = QComboBox()
        self.panelWithOptions = QLabel()
        self.combobox1.setGeometry(300,200,120,30)
        self.combobox1.move(self.rect().center())
        self.combobox1.addItem('Wilk')
        self.combobox1.addItem('Owca')
        self.combobox1.addItem('Cyber owca')
        self.combobox1.addItem('Antylopa')
        self.combobox1.addItem('Lis')
        self.combobox1.addItem('Zolw')
        self.combobox1.addItem('Trawa')
        self.combobox1.addItem('Mlecz')
        self.combobox1.addItem('Guarana')
        self.combobox1.addItem('Jagody')
        self.combobox1.addItem('Barszcz')
        self.button = QPushButton("Ok!")
        self.button.clicked.connect(lambda state, xx=x, yy=y: self.acceptOption(xx, yy))
        self.vBox.addWidget(self.combobox1)
        self.vBox.addWidget(self.button)
        self.panelWithOptions.setLayout(self.vBox)
        self.panelWithOptions.show()

    def acceptOption(self, x, y):
        swiatOrganizmow = self.Swiat.getSwiatOrganizmow()
        swiatOrganizmow[x][y] = UstawiaczOrganizmow().klonowanie(self.combobox1.currentText(), self.Swiat, x, y)
        self.Swiat.dodajOrganizm(swiatOrganizmow[x][y], False)
        self.panelWithOptions.setVisible(False)
        self.deleteLayout(self.layout())
        self.GlowneOkno()

    def deleteLayout(self, layout):
            if layout is not None:
                while layout.count():
                    item = layout.takeAt(0)
                    widget = item.widget()
                    if widget is not None:
                        widget.deleteLater()
                    else:
                        self.deleteLayout(item.layout())
                sip.delete(layout)

    def wczytajSwiat(self):
        widget = QWidget()
        wczytaj, czy1 = QtWidgets.QInputDialog.getText(widget, 'Podaj dane', 'Podaj nazwe pliku do wczytania:')
        wczytaj += ".txt"
        plik = open(wczytaj, "r")
        linie = plik.read().splitlines()
        plik.close()
        glownaLinia = linie[0].split()
        x = int(glownaLinia[0])
        y = int(glownaLinia[1])
        ktoraTura = int(glownaLinia[2])
        ktoraTuraUmiejetnosci = int(glownaLinia[3])
        czyUmiejetnosc = int(glownaLinia[4])
        intToBol = True
        if czyUmiejetnosc == 0:
            intToBol = False
        self.Swiat = Swiat(x, y, ktoraTura, ktoraTuraUmiejetnosci, czyUmiejetnosc)
        self.Swiat.wczytajSwiat(linie)
        self.deleteLayout(self.layout())
        self.GlowneOkno()
