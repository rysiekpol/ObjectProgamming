#ifndef Czlowiek_h
#define Czlowiek_h
#include "Zwierze.h"
class Czlowiek : public Zwierze {
protected:
	string Wypisz() const override;
public:
	Czlowiek(Swiat *swiat, int x, int y, bool czyZyje);
	void akcja() override;
	void ustawRuch();
	void wywolajUmiejetnosc();
	Organizm* klonuj() override;
};

#endif