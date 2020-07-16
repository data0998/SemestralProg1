package Codigo;
import java.util.*;

public class Util {
    private String _proveedor;
    private String _nombre;
    /*private int _codigo;*/
    private int _restock;
    private int _cant;
    private Date _fecha;

    private int _cantVen = 0;

    private int _tipo;
    private int _cantDmg;
    private int _isDmg;
    

    public Util( String nombre, String proveedor, /*int codigo*/ int restock, int cantidad,Date fecha, int isDmg, int cantDmg,int tipo){
        this._nombre = nombre;
        this._proveedor = proveedor;
        this._restock = restock;
        this._cant = cantidad;
        this._fecha = fecha;
        this._isDmg = isDmg;
        this._cantDmg = cantDmg;
        this._tipo = tipo;
    }

    public String get_Nombre() {
        return _nombre;
    }

    public String get_Proveedor() {
        return _proveedor;
    }

    public int get_Restock() {
        return _restock;
    }

    public int get_Cantidad() {
        return _cant;
    }

        public Date get_Fecha(){
        return _fecha;
    }

    /* Si es 1 hay articulos dañados, si es 0 no hay articulos dañaos, de lo contrario 2*/
    public void set_IsDmg(String s_isDmg){
        _isDmg = s_isDmg.equals("S")? 1: s_isDmg.equals("N")?2:0; 
    }

    public int get_IsDmg(){
        return _isDmg; 
    }

    public int get_cantDmg(){
    return _cantDmg;
    }

    public void comprar_Producto(int cantidad){
        _cant += cantidad;

    }

    public void vender_Producto(int cantidad){
        _cant -= cantidad;

    }

    public int get_tipo() {
        return _tipo;
    }

    public void aum_cantDmg(int cantDmg){
        _cantDmg += cantDmg;
    }

    public void aum_cantVen(int cantVen){
        _cantVen += cantVen;
    }
}


