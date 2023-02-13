#ifndef Zwierze_h
#define Zwierze_h
#include "Organizm.h"

class Zwierze : public Organizm {
public:
	void akcja() override;
	void kolizja(Organizm* broniacy) override;
	virtual ~Zwierze();
};

#endif