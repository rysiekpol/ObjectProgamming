#include "Swiat.h"
#include <Windows.h>
#define s 115
#define q 113
Swiat::Swiat() {
	poleGryX = 20;
	poleGryY = 20;
	ostatniKlawisz = -1;
	ktoraTura = 0;
	ktoraTuraUmiejetnosci = 0;
	czyUmiejetnosc = false;
	gra = new Organizm** [poleGryX];
	for (int i = 0; i < poleGryX; i++) {
		gra[i] = new Organizm*[poleGryY];
	}
};

Swiat::Swiat(int poleX, int poleY) {
	this->poleGryX = poleX;
	this->poleGryY = poleY;
	ostatniKlawisz = -1;
	ktoraTura = 0;
	ktoraTuraUmiejetnosci = 0;
	czyUmiejetnosc = false;
	gra = new Organizm** [poleX];
	for (int i = 0; i < poleX; i++) {
		gra[i] = new Organizm*[poleY];
	}
}

Swiat::Swiat(int poleX, int poleY, int ktoraTura, int ktoraTuraUmiejetnosci, bool czyUmiejetnosc) {
	this->poleGryX = poleX;
	this->poleGryY = poleY;
	ostatniKlawisz = -1;
	this->ktoraTura = ktoraTura;
	this->ktoraTuraUmiejetnosci = ktoraTuraUmiejetnosci;
	this->czyUmiejetnosc = czyUmiejetnosc;
	gra = new Organizm * *[poleX];
	for (int i = 0; i < poleX; i++) {
		gra[i] = new Organizm * [poleY];
	}
}

void Swiat::wykonajTure() {	
	while (true) {
		komentarz.clear();
		int buffer = _getch(); //first 224 number
		if (buffer == 224) {
			ostatniKlawisz = _getch();
		}
		else {
			ostatniKlawisz = buffer;
		}
		if (ostatniKlawisz == q) {
			exit(0);
		}
		system("cls");
		zapiszSwiat();
		cout << "Tura: " << ktoraTura << endl;
		wykonajAkcje();
		usunMartweOrganizmy();
		rysujSwiat();
		for (auto i : komentarz) {
			if (i != "") {
				cout << i << endl;
			}
		}
		//wypiszOrganizmy();
		ktoraTura++;
	}
}

void Swiat::zapiszSwiat() {
	if (ostatniKlawisz == s) {
		system("cls");
		string nazwa;
		cout << "Podaj nazwe do zapisania pliku" << endl;
		cin >> nazwa;
		nazwa += ".txt";
		fstream file;
		file.open(nazwa, fstream::out);
		file << poleGryX << " " << poleGryY << " " << ktoraTura << " " << ktoraTuraUmiejetnosci << " " << czyUmiejetnosc << endl;
		file << organizmy.size() << endl;
		for (int i = 0; i < (int)organizmy.size(); i++) {
			file << organizmy[i]->getOznaczenie() << " " << organizmy[i]->getPolozenieX() << " " << organizmy[i]->getPolozenieY()
				<< " " << organizmy[i]->getSila() << " " << organizmy[i]->getInicjatywa() << " " << organizmy[i]->getCzyMozeRozmnazac()
				<< " " << organizmy[i]->getWiek() << endl;
		}
		file.close();
	}
}

int Swiat::getOstatniKlawisz() const{
	return ostatniKlawisz;
}

void Swiat::rysujSwiat() {
	for (int i = 0; i < poleGryX; i++) {
		for (int j = 0; j < poleGryY; j++) {
			gra[i][j] = NULL;
		}
	}

	for (auto org : organizmy) {
		gra[org->getPolozenieX()][org->getPolozenieY()] = org;
	}

	for (int i = 0; i < poleGryX; i++) {
		for (int j = 0; j < poleGryY; j++) {
			if (gra[i][j] != NULL) {
				cout << gra[i][j]->getOznaczenie();
			}
			else {
				cout << ".";
			}
		}
		cout << endl;
	}
}

void Swiat::dodajOrganizm(Organizm *organizm, bool czyKlonowanie) {
	organizmy.push_back(organizm);
	if (!czyKlonowanie) {
		sortowanieList();
	}
}

vector<pair<int,int>> Swiat::znajdzPuste(int x, int y) {
	vector<pair<int, int>> vect;
	if (x - 1 >= 0 && y - 1 > 0 && gra[x - 1][y - 1] == NULL) {
		vect.push_back(make_pair(x - 1, y - 1));
	}
	if (x - 1 >= 0 && gra[x - 1][y] == NULL) {
		vect.push_back(make_pair(x - 1, y));
	}
	if (x - 1 >= 0 && y + 1 < poleGryY && gra[x - 1][y + 1] == NULL) {
		vect.push_back(make_pair(x - 1, y + 1));
	}
	if (y - 1 >= 0 && gra[x][y - 1] == NULL) {
		vect.push_back(make_pair(x, y - 1));
	}
	if (y + 1 < poleGryY && gra[x][y + 1] == NULL) {
		vect.push_back(make_pair(x, y + 1));
	}
	if (x + 1 < poleGryX && y - 1 >= 0 && gra[x + 1][y - 1] == NULL) {
		vect.push_back(make_pair(x + 1, y - 1));
	}
	if (x + 1 < poleGryX && gra[x + 1][y] == NULL) {
		vect.push_back(make_pair(x + 1, y));
	}
	if (x + 1 < poleGryX && y + 1 < poleGryY && gra[x + 1][y + 1] == NULL) {
		vect.push_back(make_pair(x + 1, y + 1));
	}
	return vect;
}

vector<pair<int, int>> Swiat::znajdzPelne(int x, int y) {
	vector<pair<int, int>> vect;
	if (x - 1 > 0 && y - 1 > 0 && gra[x - 1][y - 1] != NULL) {
		vect.push_back(make_pair(x - 1, y - 1));
	}
	if (x - 1 > 0 && gra[x - 1][y] != NULL) {
		vect.push_back(make_pair(x - 1, y));
	}
	if (x - 1 > 0 && y + 1 < poleGryY && gra[x - 1][y + 1] != NULL) {
		vect.push_back(make_pair(x - 1, y + 1));
	}
	if (y - 1 > 0 && gra[x][y - 1] != NULL) {
		vect.push_back(make_pair(x, y - 1));
	}
	if (y + 1 < poleGryY && gra[x][y + 1] != NULL) {
		vect.push_back(make_pair(x, y + 1));
	}
	if (x + 1 < poleGryX && y - 1 > 0 && gra[x + 1][y - 1] != NULL) {
		vect.push_back(make_pair(x + 1, y - 1));
	}
	if (x + 1 < poleGryX && gra[x + 1][y] != NULL) {
		vect.push_back(make_pair(x + 1, y));
	}
	if (x + 1 < poleGryX && y + 1 < poleGryY && gra[x + 1][y + 1] != NULL) {
		vect.push_back(make_pair(x + 1, y + 1));
	}
	return vect;
}

void Swiat::usunOrganizm(Organizm* organizm) {
	auto it = find(organizmy.begin(), organizmy.end(), organizm);
	if (it != organizmy.end()) {
		organizmy.erase(it);
	}
}

void Swiat::usunMartweOrganizmy() {
	for (auto org : organizmy) {
		if (!(org->getCzyZyje())) {
			usunOrganizm(org);
			delete org;
		}
	}
	sortowanieList();
}

void Swiat::sortowanieList() {
	sort(organizmy.begin(), organizmy.end(), [](Organizm* o1, Organizm* o2)
		{
			if ((*o1).getInicjatywa() == (*o2).getInicjatywa()) {
				return (*o1).getWiek() > (*o2).getWiek();
			}
			return (*o1).getInicjatywa() > (*o2).getInicjatywa();
		});
}

void Swiat::wypiszOrganizmy() {
	int i = 0;
	for (auto org : organizmy) {
		cout << i << " " << org->Wypisz() << endl;
		i++;
	}
}

int Swiat::getPoleGryX() const {
	return poleGryX;
}

int Swiat::getPoleGryY() const {
	return poleGryY;
}

void Swiat::wykonajAkcje() {
	int dlugosc = (int)organizmy.size();
	for (int i = 0; i < dlugosc; i++) {
		if (organizmy[i]->getCzyZyje()==true) {
			organizmy[i]->akcja();
		}
	}
}

int Swiat::getKtoraTuraUmiejetnosci() const{
	return ktoraTuraUmiejetnosci;
}

void Swiat::setKtoraTuraUmiejetnosci(int ktora) {
	ktoraTuraUmiejetnosci = ktora;
}

void Swiat::nowySwiat(int ile){
	for (int i = 0; i < poleGryX; i++) {
		for (int j = 0; j < poleGryY; j++) {
			gra[i][j] = NULL;
		}
	}
	
	if (ile > poleGryX * poleGryY) {
		cout << "Za duzo organizmow" << endl;
		exit(0);
	}
	ustawPozycje(new Czlowiek(this, -100, -100, true));
	for (int i = 0; i < ile; i++) {
		int ktory = rand() % 10;
		switch (ktory)
		{
		case 0:
			ustawPozycje(new Jagody(this, -100, -100, true));
			break;
		case 1:
			ustawPozycje(new Antylopa(this, -100, -100, true));
			break;
		case 2:
			ustawPozycje(new Lis(this, -100, -100, true));
			break;
		case 3:
			ustawPozycje(new Mlecz(this, -100, -100, true));
			break;
		case 4:
			ustawPozycje(new Owca(this, -100, -100, true));
			break;
		case 5:
			ustawPozycje(new Wilk(this, -100, -100, true));
			break;
		case 6:
			ustawPozycje(new Zolw(this, -100, -100, true));
			break;
		case 7:
			ustawPozycje(new Trawa(this, -100, -100, true));
			break;
		case 8:
			ustawPozycje(new Guarana(this, -100, -100, true));
			break;
		case 9:
			ustawPozycje(new Barszcz(this, -100, -100, true));
			break;
		default:
			break;
		}
	}

	cout << "Strzalki) - ruch czlowieka" << endl;
	cout << "Spacja) - uruchom umiejetnosc" << endl;
	cout << "W dowolnym momencie mozesz zapisac stan gry za pomoca klawisza \"s\"" << endl;
	cout << "Nacisnij dowolny klawisz aby kontynuowac" << endl;
	int klawisz = _getch();
	rysujSwiat();
	wypiszOrganizmy();
	wykonajTure();
}

void Swiat::ustawPozycje(Organizm* organizm) {
	string status;
	vector<string> temp = komentarz;
	int x = rand() % poleGryX;		
	int y = rand() % poleGryY;
	if (gra[x][y] == NULL) {
		organizm->setPolozenieX(x);
		organizm->setPolozenieY(y);
		gra[x][y] = organizm;		
		dodajOrganizm(organizm, false);
	}
	else {
		status = "Nie udalo sie dodac organizmu: " + organizm->Wypisz();
		(temp).push_back(status);
		delete organizm;
	}
}

vector<Organizm*> Swiat::getOrganizmy() {
	return organizmy;
}

Organizm ***Swiat::getGra() {
	return gra;
}

int Swiat::getUmiejetnosc() const {
	return czyUmiejetnosc;
}

void Swiat::setUmiejetnosc(bool czy) {
	czyUmiejetnosc = czy;
}

void Swiat::wczytajSwiat(ifstream &wczytanyPlik) {
	int ile, x, y, sila, inicjatywa, czyMozeRozmnazac, wiek;
	char oznaczenie;
	wczytanyPlik >> ile;
	for (int i = 0; i < ile; i++) {
		wczytanyPlik >> oznaczenie >> x >> y >> sila >> inicjatywa >> czyMozeRozmnazac >> wiek;
		switch (oznaczenie) {
		case 'A':
			ustawOrganizm(new Antylopa(this, -100,-100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
			break;
		case 'b':
			ustawOrganizm(new Barszcz(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
			break;
		case 'C':
			ustawOrganizm(new Czlowiek(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
			break;
		case 'g':
			ustawOrganizm(new Guarana(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
			break;
		case 'L':
			ustawOrganizm(new Lis(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
			break;
		case 'm':
			ustawOrganizm(new Mlecz(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
			break;
		case 'O':
			ustawOrganizm(new Owca(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
			break;
		case 't':
			ustawOrganizm(new Trawa(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
			break;
		case 'j':
			ustawOrganizm(new Jagody(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
			break;
		case 'W':
			ustawOrganizm(new Wilk(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
			break;
		case 'Z':
			ustawOrganizm(new Zolw(this, -100, -100, true), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek);
			break;
		default:
			cout << "Bledny format pliku, wychodze z programu" << endl;
			cout << oznaczenie;
			exit(0);
		}
	}
	wczytanyPlik.close();

	cout << "Strzalki) - ruch czlowieka" << endl;
	cout << "Spacja) - uruchom umiejetnosc" << endl;
	cout << "W dowolnym momencie mozesz zapisac stan gry za pomoca klawisza \"s\"" << endl;
	cout << "Nacisnij dowolny klawisz aby kontynuowac" << endl;
	int klawisz = _getch();
	rysujSwiat();
	wypiszOrganizmy();
	wykonajTure();
}

void Swiat::ustawOrganizm(Organizm* organizm, int pozycjaX, int pozycjaY, int sila, int inicjatywa, int czyMozeRozmnazac, int wiek) {
	organizm->setCzyMozeRozmnazac(czyMozeRozmnazac);
	organizm->setPolozenieX(pozycjaX);
	organizm->setPolozenieY(pozycjaY);
	organizm->setSila(sila);
	organizm->setInicjatywa(inicjatywa);
	organizm->setWiek(wiek);
	dodajOrganizm(organizm, false);
	//gra[pozycjaX][pozycjaY] = organizm;
}

vector<string> &Swiat::getKomentarz() {
	return komentarz;
}

Swiat::~Swiat() {

}
