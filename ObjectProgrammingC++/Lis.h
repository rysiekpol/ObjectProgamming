#ifndef Lis_h
#define Lis_h
#include "Zwierze.h"
#include <vector>
#include <algorithm>
class Lis : public Zwierze {
protected:
	string Wypisz() const override;
public:
	Lis(Swiat* swiat, int x, int y, bool czyZyje);
	void akcja() override;
	vector<pair<int, int>> szukajPola(int x, int y);
	Organizm* klonuj() override;
};

#endif