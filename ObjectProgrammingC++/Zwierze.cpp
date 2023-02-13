#include "Zwierze.h"
#include "Swiat.h"
#include <typeinfo>
#include <windows.h>


void Zwierze::akcja() {
	przygotujOrganizm();
	int movement = rand() % 4;
	Organizm*** graChecker = swiat->getGra();
	string status;
	vector<string> *temp = &swiat->getKomentarz();
	switch (movement) {
	case 0:
		if (polozenieX+1 < swiat->getPoleGryX()) {
			if (graChecker[polozenieX + 1][polozenieY] != NULL) {
				kolizja(graChecker[polozenieX + 1][polozenieY]);
			}
			else {
				graChecker[polozenieX][polozenieY] = NULL;
				status = this->Wypisz() + " PORUSZYL SIE NA POZYCJE ";
				//cout << *this << " PORUSZYL SIE NA POZYCJE ";
				polozenieX++;
				status +=  to_string(polozenieX) + ":" + to_string(polozenieY);
				graChecker[polozenieX][polozenieY] = this;
			}
		}
		break;
	case 1:
		if (polozenieX > 0) {
			if (graChecker[polozenieX - 1][polozenieY] != NULL) {
				kolizja(graChecker[polozenieX - 1][polozenieY]);
			} else{
				graChecker[polozenieX][polozenieY] = NULL;
				status = this->Wypisz() + " PORUSZYL SIE NA POZYCJE ";
				polozenieX--;
				status += to_string(polozenieX) + ":" + to_string(polozenieY);
				graChecker[polozenieX][polozenieY] = this;
			}
		}
		break;
	case 2:
		if (polozenieY+1 < swiat->getPoleGryY()) {
			if (graChecker[polozenieX][polozenieY + 1] != NULL) {
				kolizja(graChecker[polozenieX][polozenieY + 1]);
			} else{
				graChecker[polozenieX][polozenieY] = NULL;
				status = this->Wypisz() + " PORUSZYL SIE NA POZYCJE ";
				polozenieY++;
				status += to_string(polozenieX) + ":" + to_string(polozenieY);
				graChecker[polozenieX][polozenieY] = this;
			}
		}
		break;
	case 3:
		if (polozenieY > 0) {
			if (graChecker[polozenieX][polozenieY - 1] != NULL) {
				kolizja(graChecker[polozenieX][polozenieY - 1]);
			} else{
				graChecker[polozenieX][polozenieY] = NULL;
				status = this->Wypisz() + " PORUSZYL SIE NA POZYCJE ";
				polozenieY--;
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

void Zwierze::kolizja(Organizm* broniacy) {
	string status;
	vector<string> *temp = &swiat->getKomentarz();
	if (broniacy->getOznaczenie() == oznaczenie) {
		if (broniacy->getCzyMozeRozmnazac()>10 && czyMozeRozmnazac>10) {
			broniacy->setCzyMozeRozmnazac(0);
			czyMozeRozmnazac = 0;
			Organizm* o = klonuj();
			Organizm*** graChecker = swiat->getGra();
			bool checker = false;
			int notFound = 0;
			vector<pair<int, int>> pustePola = swiat->znajdzPuste(broniacy->getPolozenieX(), broniacy->getPolozenieY());
			if (pustePola.size() > 0) {
				status = this->Wypisz() + " i " + broniacy->Wypisz() + " ROZMNOZYLY SIE: ";
				//cout << *this << " i " << *broniacy << " ROZMNOZYLY SIE: ";
				int losuj = rand() % (int)pustePola.size();
				o->setPolozenieX(pustePola[losuj].first);
				o->setPolozenieY(pustePola[losuj].second);
				swiat->dodajOrganizm(o, true);
				status += o->Wypisz();
				graChecker[pustePola[losuj].first][pustePola[losuj].second] = o;
				(*temp).push_back(status);
			}
			else {
				status = "BRAK MIEJSCA NA ROZMNOZENIE SIE " + this->Wypisz() + " i " + broniacy->Wypisz();
				//cout << "BRAK MIEJSCA NA ROZMNOZENIE SIE " << *this << " i " << *broniacy << endl;
				delete o;
				(*temp).push_back(status);

			}
		}
	}
	else {
		if (!broniacy->czyOdbilAtak(this)) {
			if (!broniacy->czyZwiekszylAtak(this) && !broniacy->czySmiertelnaRoslina(this)) {
				if (broniacy->czyUciekl()){
					Organizm*** graChecker = swiat->getGra();
					vector<pair<int, int>> pustePola = swiat->znajdzPuste(broniacy->getPolozenieX(), broniacy->getPolozenieY());
					if (pustePola.size() > 0) {
						status += broniacy->Wypisz();
						int losuj = rand() % (int)pustePola.size();
						graChecker[polozenieX][polozenieY] = NULL;
						polozenieX = pustePola[losuj].first;
						polozenieY = pustePola[losuj].second;
						status += " UCIEKL/A NA POZYCJE" + to_string(polozenieX) + ":" + to_string(polozenieY);
						graChecker[polozenieX][polozenieY] = this;
						(*temp).push_back(status);
					}
					else {
						status = broniacy->Wypisz() + " NIE UCIEKL/A Z POWODU BRAKU PUSTYCH POL";
						(*temp).push_back(status);
					}
				}
				else {
					Organizm*** pozycjaDoUsuniecia = swiat->getGra();
					if (sila >= broniacy->getSila()) {
						status = broniacy->Wypisz() + " ZOSTAL USUNIETY PRZEZ " + this->Wypisz();
						pozycjaDoUsuniecia[broniacy->getPolozenieX()][broniacy->getPolozenieY()] = this;						
						pozycjaDoUsuniecia[polozenieX][polozenieY] = NULL;
						polozenieX = broniacy->getPolozenieX();
						polozenieY = broniacy->getPolozenieY();
						broniacy->setCzyZyje(false);
						(*temp).push_back(status);
					}
					else {
						status = this->Wypisz() + " ZOSTAL USUNIETY PRZEZ " + broniacy->Wypisz();
						pozycjaDoUsuniecia[polozenieX][polozenieY] = NULL;
						czyZyje = false;
						(*temp).push_back(status);
					}
				}
			}
			else {
				if (!broniacy->czySmiertelnaRoslina(this)) {
					status = this->Wypisz() + " ZJADL " + broniacy->Wypisz();
					Organizm*** pozycjaDoUsuniecia = swiat->getGra();
					pozycjaDoUsuniecia[broniacy->getPolozenieX()][broniacy->getPolozenieY()] = this;
					pozycjaDoUsuniecia[polozenieX][polozenieY] = NULL;
					polozenieX = broniacy->getPolozenieX();
					polozenieY = broniacy->getPolozenieY();
					broniacy->setCzyZyje(false);
					(*temp).push_back(status);
				}
				else {
					status = broniacy->Wypisz() + " USUNELA " + this->Wypisz();
					Organizm*** pozycjaDoUsuniecia = swiat->getGra();
					pozycjaDoUsuniecia[this->getPolozenieX()][this->getPolozenieY()] = NULL;
					this->setCzyZyje(false);
					(*temp).push_back(status);
				}
			}
		}
	}
}

Zwierze::~Zwierze() {};