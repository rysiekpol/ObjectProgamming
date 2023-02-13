#ifndef Barszcz_sosnowskiego_h
#define Barszcz_sosnowskiego_h
#include "Roslina.h"
#include <vector>
#include <algorithm>
class Barszcz : public Roslina {
protected:
	string Wypisz() const override;
public:
	Barszcz(Swiat* swiat, int x, int y, bool czyZyje);
	void akcja() override;
	vector<pair<int, int>> szukajZwierzecia(int x, int y);
	virtual bool czySmiertelnaRoslina(Organizm* atakujacy);
	Organizm* klonuj() override;
};
#endif