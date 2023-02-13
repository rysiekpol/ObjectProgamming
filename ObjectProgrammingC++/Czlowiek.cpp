#include "Czlowiek.h"
#include "Swiat.h"
#include <conio.h>

#define KEY_UP 72
#define KEY_DOWN 80
#define KEY_LEFT 75
#define KEY_RIGHT 77
#define SPACEBAR 32

Czlowiek::Czlowiek(Swiat *swiat, int x, int y, bool czyZyje) {
	this->polozenieX = x;
	this->polozenieY = y;
	sila = 5;
	inicjatywa = 4;
	oznaczenie = 'C';
	this->swiat = swiat;
	this->czyZyje = czyZyje;
	this->czyMozeRozmnazac = 0;
	wiek = 0;
}

void Czlowiek::akcja() {
	int wiek = getWiek();
	this->setWiek(++wiek);
	ustawRuch();
	string status;
	vector<string>* temp = &swiat->getKomentarz();
	//swiat->setKtoraTuraUmiejetnosci(5);
	if (swiat->getUmiejetnosc()) {
		swiat->setKtoraTuraUmiejetnosci(swiat->getKtoraTuraUmiejetnosci() + 1);
		if (swiat->getKtoraTuraUmiejetnosci() < 5) {
			wywolajUmiejetnosc();
			status = this->Wypisz() + " WYWOLAL UMIEJETNOSC CALOPALENIE, JEST TO " + to_string(swiat->getKtoraTuraUmiejetnosci()) + " TURA UMIEJETNOSCI";
		}
		else {
			status = this->Wypisz() + " UMIEJETNOSC SKONCZYLA SIE";
			swiat->setUmiejetnosc(false);
			swiat->setKtoraTuraUmiejetnosci(0);
		}
	}
	if (status != "") {
		(*temp).push_back(status);
	}
}

void Czlowiek::ustawRuch() {	
	Organizm*** graChecker = swiat->getGra();
	int klawisz = swiat->getOstatniKlawisz();
	string status;
	vector<string>* temp = &swiat->getKomentarz();
	switch (klawisz)
	{
	case KEY_UP:
		if (polozenieX > 0) {
			if (graChecker[polozenieX - 1][polozenieY] != NULL) {
				Zwierze::kolizja(graChecker[polozenieX - 1][polozenieY]);
			}
			else {
				graChecker[polozenieX][polozenieY] = NULL;
				status = this->Wypisz() + " PORUSZYL SIE NA POZYCJE ";
				polozenieX--;
				status += to_string(polozenieX) + ":" + to_string(polozenieY);
				graChecker[polozenieX][polozenieY] = this;
			}
		}
		break;
	case KEY_RIGHT:
		if (polozenieY+1 < swiat->getPoleGryY()) {
			if (graChecker[polozenieX][polozenieY + 1] != NULL) {
				Zwierze::kolizja(graChecker[polozenieX][polozenieY + 1]);
			}
			else {
				graChecker[polozenieX][polozenieY] = NULL;
				status = this->Wypisz() + " PORUSZYL SIE NA POZYCJE ";
				polozenieY++;
				status += to_string(polozenieX) + ":" + to_string(polozenieY);
				graChecker[polozenieX][polozenieY] = this;
			}
		}
		break;
	case KEY_DOWN:
		if (polozenieX + 1 < swiat->getPoleGryX()) {
			if (graChecker[polozenieX + 1][polozenieY] != NULL) {
				Zwierze::kolizja(graChecker[polozenieX + 1][polozenieY]);
			}
			else {
				graChecker[polozenieX][polozenieY] = NULL;
				status = this->Wypisz() + " PORUSZYL SIE NA POZYCJE ";
				polozenieX++;
				status += to_string(polozenieX) + ":" + to_string(polozenieY);
				graChecker[polozenieX][polozenieY] = this;
			}
		}
		break;
	case KEY_LEFT:
		if (polozenieY > 0) {
			if (graChecker[polozenieX][polozenieY - 1] != NULL) {
				Zwierze::kolizja(graChecker[polozenieX][polozenieY - 1]);
			}
			else {
				graChecker[polozenieX][polozenieY] = NULL;
				status = this->Wypisz() + " PORUSZYL SIE NA POZYCJE ";
				polozenieY--;
				status += to_string(polozenieX) + ":" + to_string(polozenieY);
				graChecker[polozenieX][polozenieY] = this;
			}
		}
		break;
	case SPACEBAR:
		swiat->setUmiejetnosc(true);
		break;
	default:
		break;
	}
	if (status != "") {
		(*temp).push_back(status);
	}
}

void Czlowiek::wywolajUmiejetnosc() {
	Organizm*** graChecker = swiat->getGra();
	vector<pair<int, int>> pelnePola = swiat->znajdzPelne(polozenieX, polozenieY);
	string status;
	vector<string>* temp = &swiat->getKomentarz();
	if (pelnePola.size() > 0) {
		for (auto wsp : pelnePola) {
			status = Wypisz() + " ZABIL ZA POMOCA UMIEJETNOSCI " + graChecker[wsp.first][wsp.second]->Wypisz();
			graChecker[wsp.first][wsp.second]->setCzyZyje(false);
			graChecker[wsp.first][wsp.second] = NULL;
			(*temp).push_back(status);
		}
	}
}

Organizm* Czlowiek::klonuj() {
	return new Czlowiek(swiat, -1, -1, true);
}

string Czlowiek::Wypisz() const {
	string komentarz = "Czlowiek na pozycji " + to_string(polozenieX) + ":" + to_string(polozenieY) + " o sile: " + to_string(sila) + " inicjatywie: " + to_string(inicjatywa) + " wiek:" + to_string(wiek);
	return komentarz;
	//cout << "Czlowiek na pozycji " << polozenieX << ":" << polozenieY << " o sile: " << sila << " inicjatywie: " << inicjatywa << " wiek:" << wiek;
}