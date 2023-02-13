#include "Lis.h"
#include "Swiat.h"
#include <conio.h>


Lis::Lis(Swiat* swiat, int x, int y,bool czyZyje) {
	this->polozenieX = x;
	this->polozenieY = y;
	sila = 3;
	inicjatywa = 7;
	oznaczenie = 'L';
	this->swiat = swiat;
	this->czyZyje = czyZyje;
	this->czyMozeRozmnazac = 0;
	wiek = 0;
}

void Lis::akcja() {
	int wiek = getWiek();
	this->setWiek(++wiek);
	bool checker = false;
	string status;
	vector<string> *temp = &swiat->getKomentarz();
	Organizm*** graChecker = swiat->getGra();
	vector<pair<int, int>> pola = szukajPola(polozenieX, polozenieY);
	if (pola.size() > 0) {
		int losuj = rand() % (int)pola.size();
		if (graChecker[pola[losuj].first][pola[losuj].second] == NULL) {
			graChecker[polozenieX][polozenieY] = NULL;
			status = this->Wypisz() + " PORUSZYL SIE NA POZYCJE ";
			polozenieX = pola[losuj].first;
			polozenieY = pola[losuj].second;		
			status += to_string(polozenieX) + ":" + to_string(polozenieY);
			graChecker[polozenieX][polozenieY] = this;
			(*temp).push_back(status);		
		}
		else if(graChecker[pola[losuj].first][pola[losuj].second] != NULL){
			kolizja(graChecker[pola[losuj].first][pola[losuj].second]);
		}
	}
	else {
		status = Wypisz() + " NIE PORUSZYL SIE Z POWODU BRAKU MIEJSCA";
		(*temp).push_back(status);
	}
}

vector<pair<int, int>> Lis::szukajPola(int x, int y) {
	Organizm*** gra = swiat->getGra();
	vector<pair<int, int>> vect;
	if (x - 1 > 0 && y - 1 > 0 && (gra[x - 1][y - 1] == NULL || gra[x - 1][y - 1]->getSila()<sila)) {
		vect.push_back(make_pair(x - 1, y - 1));
	}
	if (x - 1 > 0 && (gra[x - 1][y] == NULL || gra[x - 1][y]->getSila() < sila)) {
		vect.push_back(make_pair(x - 1, y));
	}
	if (x - 1 > 0 && y + 1 < swiat->getPoleGryY() && (gra[x - 1][y + 1] == NULL || gra[x - 1][y + 1]->getSila() < sila)) {
		vect.push_back(make_pair(x - 1, y + 1));
	}
	if (y - 1 > 0 && (gra[x][y - 1] == NULL || gra[x][y - 1]->getSila() < sila)) {
		vect.push_back(make_pair(x, y - 1));
	}
	if (y + 1 < swiat->getPoleGryY() && (gra[x][y + 1] == NULL || gra[x][y + 1]->getSila() < sila)) {
		vect.push_back(make_pair(x, y + 1));
	}
	if (x + 1 < swiat->getPoleGryX() && y - 1 > 0 && (gra[x + 1][y - 1] == NULL || gra[x + 1][y - 1]->getSila() < sila)) {
		vect.push_back(make_pair(x + 1, y - 1));
	}
	if (x + 1 < swiat->getPoleGryX() && (gra[x + 1][y] == NULL || gra[x + 1][y]->getSila() < sila)) {
		vect.push_back(make_pair(x + 1, y));
	}
	if (x + 1 < swiat->getPoleGryX() && y + 1 < swiat->getPoleGryY() && (gra[x + 1][y + 1] == NULL || gra[x + 1][y + 1]->getSila() < sila)) {
		vect.push_back(make_pair(x + 1, y + 1));
	}
	return vect;
}

Organizm* Lis::klonuj() {
	return new Lis(swiat, -1, -1, true);
}

string Lis::Wypisz() const {
	string komentarz = "Lis na pozycji " + to_string(polozenieX) + ":" + to_string(polozenieY) + " o sile: " + to_string(sila) + " inicjatywie: " + to_string(inicjatywa) + " wiek:" + to_string(wiek);
	return komentarz;
	//cout << "Lis na pozycji " << polozenieX << ":" << polozenieY << " o sile: " << sila << " inicjatywie: " << inicjatywa << " wiek:" << wiek;
}
