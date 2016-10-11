package mk.ukim.finki.np.av2;

import java.math.BigDecimal;

public final class BigComplex {

    private final BigDecimal real;
    private final BigDecimal imag;

    public BigComplex(BigDecimal real, BigDecimal imag) {
        this.real = real;
        this.imag = imag;
    }

    public BigComplex add(BigComplex complex) {
        BigDecimal real = this.real.add(complex.real);
        BigDecimal imag = this.real.add(complex.imag);
        return new BigComplex(real, imag);
    }

}
