package primitives;

public class Material {
    /**
     * gets kd
     *
     * @return
     */
    public double getKd() {
        return _Kd;
    }

    /**
     * gets ks
     *
     * @return
     */
    public double getKs() {
        return _Ks;
    }

    /**
     * gets kr
     *
     * @return
     */
    public double getKr() {
        return _Kr;
    }

    /**
     * gets kt
     *
     * @return
     */
    public double getKt() {
        return _Kt;
    }

    /**
     * gets shininess
     *
     * @return
     */
    public int getnShininess() {
        return nShininess;
    }
//    public void setnShininess(int nShininess) {
//        this.nShininess = nShininess;
//    }

    int nShininess;
    double _Kd;
    double _Ks;
    double _Kr;
    double _Kt;

    /**
     * ctor for material with params
     *
     * @param _Kd
     * @param _Ks
     * @param _Kr
     * @param _Kt
     * @param nShininess
     */
    public Material(double _Kd, double _Ks, double _Kr, double _Kt, int nShininess) {
        this._Kd = _Kd;
        this._Ks = _Ks;
        this._Kr = _Kr;
        this._Kt = _Kt;
        this.nShininess = nShininess;
    }
}
