#include "Roslina.h"
#include "Swiat.h"

void Roslina::akcja() {
	if (czyMozeRozmnazac > 10) {
		czyMozeRozmnazac = 0;
		string status;
		vector<string> *temp = &swiat->getKomentarz();
		vector<pair<int, int>> puste = swiat->znajdzPuste(polozenieX, polozenieY);
		Organizm*** graChecker = swiat->getGra();
		Organizm* o = klonuj();
		if ((int)puste.size() > 0) {
			int losuj = rand() % puste.size();
			o->setPolozenieX(puste[losuj].first);
			o->setPolozenieY(puste[losuj].second);
			graChecker[puste[losuj].first][puste[losuj].second] = o;
			swiat->dodajOrganizm(o, true);
			status = this->Wypisz() + " ROZPYLIL SIE NA POZYCJE " + to_string(puste[losuj].first) + ":" + to_string(puste[losuj].second);
			(*temp).push_back(status);
		}
		else {
			status = this->Wypisz() + " NIE ROZPYLIL SIE Z POWODU BRAKU MIEJSCA";
			(*temp).push_back(status);
			delete o;
		}
	}
}

void Roslina::kolizja(Organizm* atakujacy) {};

Roslina::~Roslina() {};