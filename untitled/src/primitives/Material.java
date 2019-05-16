package primitives;

public class Material {
    public double get_Kd() {
        return _Kd;
    }

    public double get_Ks() {
        return _Ks;
    }

    double _Kd;
    double _Ks;

    public Material(double _Kd, double _Ks) {
        this._Kd = _Kd;
        this._Ks = _Ks;
    }

}
