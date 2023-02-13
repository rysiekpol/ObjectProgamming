#ifndef Antylopa_h
#define Antylopa_h
#include "Zwierze.h"
class Antylopa : public Zwierze {
protected:
	string Wypisz() const override;
public:
	Antylopa(Swiat* swiat, int x, int y, bool czyZyje);
	void akcja() override;
	bool czyUciekl() override;
	Organizm* klonuj() override;
};

#endif