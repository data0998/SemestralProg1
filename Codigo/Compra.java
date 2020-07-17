package Codigo;
import java.util.*;


public class Compra{

    private int _idUtil;
    private int _cantidad;
    private int _diaCompra;
    private int _cantDmg;
    public Compra(int idUtil, int cantidad, int cantDmg, Date fechaVenc,int diaCompra){
        this._idUtil = idUtil;
        this._cantidad = cantidad;
        this._cantDmg = cantDmg;
        this._diaCompra = diaCompra;

    }

    public int get_cantidad(){
        return _cantidad;
    }

    public int get_diaCompra(){
        return _diaCompra;
    }




}
