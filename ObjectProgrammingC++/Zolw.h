#ifndef Zolw_h
#define Zolw_h
#include "Zwierze.h"
class Zolw : public Zwierze {
protected:
	string Wypisz() const override;
public:
	Zolw(Swiat* swiat, int x, int y, bool czyZyje);
	void akcja() override;
	virtual bool czyOdbilAtak(Organizm* atakujacy) override;
	Organizm* klonuj() override;
};

#endif