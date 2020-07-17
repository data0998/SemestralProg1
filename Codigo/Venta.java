package Codigo;

public class Venta{
    private int _idUtil;
    private int _cantVenta;
    private int _diaVenta;


    public Venta(int idUtil, int cantVenta,int diaVenta){
        this._idUtil = idUtil;
        this._cantVenta = cantVenta;
        this._diaVenta = diaVenta;

    }

    public int get_cantVenta(){
        return _cantVenta;
    }

    public int get_diaVenta(){
        return _diaVenta;
    }


}