#include "Antylopa.h"
#include "Swiat.h"
#include <conio.h>

Antylopa::Antylopa(Swiat* swiat, int x, int y, bool czyZyje) {
	this->polozenieX = x;
	this->polozenieY = y;
	sila = 4;
	inicjatywa = 4;
	oznaczenie = 'A';
	this->swiat = swiat;
	this->czyZyje = czyZyje;
	this->czyMozeRozmnazac = 0;
	wiek = 0;
}

void Antylopa::akcja() {
	int wiek = getWiek();
	this->setWiek(++wiek);
	int movement = rand() % 4;
	string status;
	vector<string>* temp = &swiat->getKomentarz();
	Organizm*** graChecker = swiat->getGra();
	switch (movement) {
	case 0:
		if (polozenieX + 2 < swiat->getPoleGryX()) {
			if (graChecker[polozenieX + 2][polozenieY] != NULL) {
				kolizja(graChecker[polozenieX + 2][polozenieY]);
			}
			else {
				graChecker[polozenieX][polozenieY] = NULL;
				status = this->Wypisz() + " PORUSZYLA SIE NA POZYCJE ";
				polozenieX += 2;
				status += to_string(polozenieX) + ":" + to_string(polozenieY);
				graChecker[polozenieX][polozenieY] = this;
			}
		}
		break;
	case 1:
		if (polozenieX-2 >= 0) {
			if (graChecker[polozenieX - 2][polozenieY] != NULL) {
				kolizja(graChecker[polozenieX - 2][polozenieY]);
			}
			else {
				graChecker[polozenieX][polozenieY] = NULL;
				status = this->Wypisz() + " PORUSZYLA SIE NA POZYCJE ";
				polozenieX -= 2;
				status += to_string(polozenieX) + ":" + to_string(polozenieY);
				graChecker[polozenieX][polozenieY] = this;
			}
		}
		break;
	case 2:
		if (polozenieY + 2 < swiat->getPoleGryY()) {
			if (graChecker[polozenieX][polozenieY + 2] != NULL) {
				kolizja(graChecker[polozenieX][polozenieY + 2]);
			}
			else {
				graChecker[polozenieX][polozenieY] = NULL;
				status = this->Wypisz() + " PORUSZYLA SIE NA POZYCJE ";
				polozenieY += 2;
				status += to_string(polozenieX) + ":" + to_string(polozenieY);
				graChecker[polozenieX][polozenieY] = this;
			}
		}
		break;
	case 3:
		if (polozenieY - 2>= 0) {
			if (graChecker[polozenieX][polozenieY - 2] != NULL) {
				kolizja(graChecker[polozenieX][polozenieY - 2]);
			}
			else {
				graChecker[polozenieX][polozenieY] = NULL;
				status = this->Wypisz() + " PORUSZYLA SIE NA POZYCJE ";
				polozenieY -= 2;
				status += to_string(polozenieX) + ":" + to_string(polozenieY);
				graChecker[polozenieX][polozenieY] = this;
			}
		}
		break;
	default:
		break;
	}
	if (status != "") {
		(*temp).push_back(status);
	}
}

bool Antylopa::czyUciekl() {
	int szansa = rand() % 100;
	vector<string> *temp = &swiat->getKomentarz();
	string status;
	if (szansa > 50) {
		return true;
	}
	else {
		status = Wypisz() + " NIE UCIEKLA Z POWODU MALEJ SZANSY";
		(*temp).push_back(status);
		return false;
	}
}

Organizm* Antylopa::klonuj() {
	return new Antylopa(swiat, -1, -1, true);
}

string Antylopa::Wypisz() const {
	string komentarz = "Antylopa na pozycji " + to_string(polozenieX) + ":" + to_string(polozenieY) + " o sile: " + to_string(sila) + " inicjatywie: " + to_string(inicjatywa) + " wiek:" + to_string(wiek);
	return komentarz;
	//cout << "Antylopa na pozycji " << polozenieX << ":" << polozenieY << " o sile: " << sila << " inicjatywie: " << inicjatywa << " wiek:" << wiek;
}
