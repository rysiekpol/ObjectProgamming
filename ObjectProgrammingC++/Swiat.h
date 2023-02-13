#ifndef Swiat_h
#define Swiat_h

#include <list>
#include <vector>
#include <algorithm>
#include "Organizm.h"
#include "Czlowiek.h"
#include "Wilk.h"
#include "Swiat.h"
#include "Zwierze.h"
#include "Owca.h"
#include "Zolw.h"
#include "Lis.h"
#include "Antylopa.h"	
#include <conio.h>
#include <fstream>
#include "Trawa.h"
#include "Mlecz.h"
#include "Guarana.h"
#include "Wilcze_jagody.h"
#include "Barszcz_sosnowskiego.h"
class Swiat {
private:
	int poleGryX;
	int poleGryY;
	vector<Organizm*> organizmy;
	vector<string> komentarz;
	Organizm ***gra;
	int ostatniKlawisz;
	int ktoraTura;
	int ktoraTuraUmiejetnosci;
	bool czyUmiejetnosc;
public:
	Swiat();
	Swiat(int poleX, int poleY);
	Swiat(int poleX, int poleY, int ktoraTura, int ktoraTuraUmiejetnosci, bool czyUmiejetnosc);
	void ustawPozycje(Organizm* organizm);
	void ustawOrganizm(Organizm* organizm, int pozycjaX, int pozycjaY, int sila, int inicjatywa, int czyMozeRozmnazac, int wiek);
	void wykonajTure();
	void rysujSwiat();
	void dodajOrganizm(Organizm *organizm, bool czyKlonowanie);
	void usunOrganizm(Organizm* organizm);
	void usunMartweOrganizmy();
	void wypiszOrganizmy();
	void wykonajAkcje();
	void sortowanieList();
	int getPoleGryX() const;
	int getPoleGryY() const;
	int getOstatniKlawisz() const;
	int getKtoraTuraUmiejetnosci() const;
	int getUmiejetnosc() const;
	vector<string> &getKomentarz();
	void setUmiejetnosc(bool czy);
	void setKtoraTuraUmiejetnosci(int ktora);
	void nowySwiat(int ile);
	void wczytajSwiat(ifstream &wczytanyPlik);
	void zapiszSwiat();
	vector<Organizm*> getOrganizmy();
	Organizm*** getGra();
	vector<pair<int,int>> znajdzPuste(int x, int y);
	vector<pair<int,int>> znajdzPelne(int x, int y);
	~Swiat();
};

#endif