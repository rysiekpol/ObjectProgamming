#include "Barszcz_sosnowskiego.h"
#include "Swiat.h"
#include <conio.h>
Barszcz::Barszcz(Swiat* swiat, int x, int y, bool czyZyje) {
	this->polozenieX = x;
	this->polozenieY = y;
	sila = 10;
	inicjatywa = 0;
	oznaczenie = 'b';
	this->swiat = swiat;
	this->czyZyje = czyZyje;
	this->czyMozeRozmnazac = 0;
	wiek = 0;
}

void Barszcz::akcja() {
	przygotujOrganizm();
	int losuj = rand() % 100;
	if (losuj > 98) {
		Roslina::akcja();
	}
	string status;
	vector<string>* temp = &swiat->getKomentarz();
	Organizm*** graChecker = swiat->getGra();
	vector<pair<int, int>> zwierzeta = szukajZwierzecia(polozenieX, polozenieY);
	if (zwierzeta.size() > 0) {
		for (auto wsp : zwierzeta) {
			status = Wypisz() + " ZABIL " + graChecker[wsp.first][wsp.second]->Wypisz();
			graChecker[wsp.first][wsp.second]->setCzyZyje(false);
			graChecker[wsp.first][wsp.second] = NULL;
			(*temp).push_back(status);
		}
	}
}

Organizm* Barszcz::klonuj() {
	return new Barszcz(swiat, -1, -1, true);
}

vector<pair<int, int>> Barszcz::szukajZwierzecia(int x, int y) {
	vector<pair<int, int>> vect;
	Organizm*** gra = swiat->getGra();
	const std::type_info& type_info = typeid(Zwierze);
	if (x - 1 > 0 && y - 1 > 0 && typeid(gra[x - 1][y - 1]) == type_info) {
		vect.push_back(make_pair(x - 1, y - 1));
	}
	if (x - 1 > 0 && typeid(gra[x - 1][y]) == type_info) {
		vect.push_back(make_pair(x - 1, y));
	}
	if (x - 1 > 0 && y + 1 < swiat->getPoleGryY() && typeid(gra[x - 1][y + 1]) == type_info) {
		vect.push_back(make_pair(x - 1, y + 1));
	}
	if (y - 1 > 0 && typeid(gra[x][y - 1]) == type_info) {
		vect.push_back(make_pair(x, y - 1));
	}
	if (y + 1 < swiat->getPoleGryY() && typeid(gra[x][y + 1]) == type_info) {
		vect.push_back(make_pair(x, y + 1));
	}
	if (x + 1 < swiat->getPoleGryX() && y - 1 > 0 && typeid(gra[x + 1][y - 1]) == type_info) {
		vect.push_back(make_pair(x + 1, y - 1));
	}
	if (x + 1 < swiat->getPoleGryX() && typeid(gra[x + 1][y]) == type_info) {
		vect.push_back(make_pair(x + 1, y));
	}
	if (x + 1 < swiat->getPoleGryX() && y + 1 < swiat->getPoleGryY() && typeid(gra[x + 1][y + 1]) == type_info) {
		vect.push_back(make_pair(x + 1, y + 1));
	}
	return vect;
}

bool Barszcz::czySmiertelnaRoslina(Organizm* atakujacy) {
	return true;
}

string Barszcz::Wypisz() const {
	string komentarz = "Barszcz Sosnowskiego na pozycji " + to_string(polozenieX) + ":" + to_string(polozenieY) + " o sile: " + to_string(sila) + " inicjatywie: " + to_string(inicjatywa) + " wiek:" + to_string(wiek);
	return komentarz;
	//cout << "Barszcz Sosnowskiego na pozycji " << polozenieX << ":" << polozenieY << " o sile: " << sila << " inicjatywie: " << inicjatywa << " wiek:" << wiek;
}