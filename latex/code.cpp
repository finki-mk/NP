#include <iostream>
#include <cmath>
using namespace std;

class Tocka {
private:
	double x, y;
public:
	Tocka(double xx = 0, double yy = 0) {
		x = xx;
		y = yy;
	}
	double distance(const Tocka &t) const {
		return sqrt((x - t.x) * (x - t.x) + (y - t.y) * (y - t.y));
	}
	void move(double dx, double dy) {
		x += dx;
		y += dy;
	}
	friend ostream & operator<<(ostream &out, const Tocka &t) {
		return out << '(' << t.x << ',' << t.y << ')';
	}
};
class Poligon {
private:
	Tocka *tocki;
	int broj;
public:
	Poligon() {
		broj = 0;
		tocki = new Tocka[broj];
	}
	Poligon(Tocka *t, int m) {
		broj = m;
		tocki = new Tocka[m];
		for (int i = 0; i < broj; i++)
			tocki[i] = t[i];
	}
	Poligon(const Poligon &p) {
		broj = p.broj;
		tocki = new Tocka[broj];
		for (int i = 0; i < broj; i++)
			tocki[i] = p.tocki[i];
	}
	Poligon operator=(const Poligon &p) {
		if (this == &p)
			return *this;
		else {
			broj = p.broj;
			delete[] tocki;
			tocki = new Tocka[broj];
			for (int i = 0; i < broj; i++)
				tocki[i] = p.tocki[i];
		}
		return *this;
	}

	void move(double dx, double dy) {
		for (int i = 0; i < broj; i++)
			tocki[i].move(dx, dy);
	}
	double perimetar() const {
		double p = 0;
		for (int i = 0; i < broj - 1; i++)
			p += tocki[i].distance(tocki[i + 1]);
		p += tocki[0].distance(tocki[broj - 1]);
		return p;
	}

	~Poligon() {
		delete[] tocki;
	}
	bool operator==(const Poligon &p) {
		return (perimetar() == p.perimetar());
	}
	bool operator!=(const Poligon &p) {
		return (perimetar() != p.perimetar());
	}
	bool operator<(const Poligon &p) {
		return (perimetar() < p.perimetar());
	}
	bool operator>=(const Poligon &p) {
		return (perimetar() >= p.perimetar());
	}
	friend ostream& operator<<(ostream &out, const Poligon &p) {
		for (int i = 0; i < p.broj; i++)
			out << "->" << p.tocki[i];
		return out;
	}
};

int main() {
	Tocka t[5] = { Tocka(-2, 0), Tocka(2, 0), Tocka(2, 2), Tocka(0, 3), Tocka(
			-2, 2) };
	Poligon pentagon(t, 5), p2;
	cout << pentagon << endl;
	cout << pentagon.perimetar() << endl;
	p2 = pentagon;
	p2.move(2, 2);
	cout << p2 << endl;
	cout << (pentagon == p2 ? "da" : "ne") << endl;
	return 0;
}
