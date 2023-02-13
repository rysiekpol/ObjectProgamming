#include <iostream>
#include <fstream>
#include <string>
#include <ctime>
#include "Czlowiek.h"
#include "Wilk.h"
#include "Organizm.h"
#include "Swiat.h"
#include "Zwierze.h"
#include "Owca.h"
#include "Zolw.h"
#include "Lis.h"
#include "Antylopa.h"	
#include <conio.h>

#include "Trawa.h"
#include "Mlecz.h"
#include "Guarana.h"
#include "Wilcze_jagody.h"
#include "Barszcz_sosnowskiego.h"

int main()
{
	string posX;
	string posY;
	cout << "Maciej Szefler s188614" << endl;
	cout << "Witaj w \"Grze w Zycie\"" << endl;
	cout << "n - nowa gra" << endl;
	cout << "w - wczytaj gre" << endl;
	char k;
	cin >> k;
	system("cls");
	srand(time(0));
	if (k == 'n') {
		cout << "Wpisz rozmiar x i y gry w formacie \"x y\" lub wpisz litere \"d\" aby ustawic domyslny rozmiar" << endl;
		cin >> posX;
		if (posX == "d") {
			posX = "20";
			posY = "20";
		}
		else {
			cin >> posY;
		}
		cout << "Wpisz liczbe organizmow" << endl;
		int ile;
		cin >> ile;
		system("cls");
		Swiat swiat(stoi(posX), stoi(posY));
		swiat.nowySwiat(ile);
	}
	else if (k == 'w') {
		string nazwa;
		cout << "Podaj nazwe zapisanego pliku" << endl;
		cin >> nazwa;
		ifstream wczytanyPlik;
		nazwa += ".txt";
		wczytanyPlik.open(nazwa);
		if (!wczytanyPlik) {
			cout << "Nie udalo sie wczytac pliku" << endl;
			return 0;
		}
		int x, y, ktoraT, ktoraU;
		bool czyUmiejetnosc;
		wczytanyPlik >> x >> y >> ktoraT >> ktoraU >> czyUmiejetnosc;
		Swiat swiat(x, y, ktoraT, ktoraU, czyUmiejetnosc);
		swiat.wczytajSwiat(wczytanyPlik);
	}	
}