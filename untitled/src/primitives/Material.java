package primitives;

public class Material {
    public double getKd() {
        return _Kd;
    }

    public double getKs() {
        return _Ks;
    }
    public double getKr() {
        return _Kr;
    }

    public double getKt() {
        return _Kt;
    }

    double _Kd;
    double _Ks;
    double _Kr;
    double _Kt;
    int nShininess;

    public Material(double _Kd, double _Ks, double _Kr, double _Kt,int nShininess) {
        this._Kd = _Kd;
        this._Ks = _Ks;
        this._Kr = _Kr;
        this._Kt = _Kt;
        this.nShininess = nShininess;
    }

}
