package Codigo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ProyectoSemestral
    _arr: Arreglos
    _mat: Matrices
    _: Protejido

    private double [][] _matPrecios = new double [_cantMaxDias][_cantMaxPlatillos];

    //Producto
    ArrayList<ArrayList<Double>> _matVendedores = new ArrayList<>(_cantVendedores);
    
    //Vendedoor
    ArrayList<ArrayList<Double>> _matVentaProductos = new ArrayList<>(_cantPlatillos);

    private double [] _preciosPlatillos = new double [_cantPlatillos];

 */

class Proveedor{
    private String _nombre;

    public Proveedor(String nombre){
        this._nombre = nombre;
    }

    public String get_Nombre() {
        return _nombre;
    }

}


class Util{

    private String _proveedor;
    private String _nombre;
    /*private int _codigo;*/
    private int _restock;
    private int _cant;
    private Date _fecha;

    private int _cantVen = 0;

    private int _cantDmg;
    private int _isDmg;
    

    public Util( String nombre, String proveedor, /*int codigo*/ int restock, int cantidad,Date fecha, int isDmg, int cantDmg){
        this._nombre = nombre;
        this._proveedor = proveedor;
        this._restock = restock;
        this._cant = cantidad;
        this._fecha = fecha;
        this._isDmg = isDmg;
        this._cantDmg = cantDmg;
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
}

/*
class UtilPerecedero extends Util{
    
    private String _fecha;
    private int _codigo;
    private int _restock;
    private int _cant;

    public  UtilPerecedero(String nombre, String proveedor,int restock, int cant, String fecha){
        super(nombre,proveedor,restock,cant);
        this._fecha = fecha;
    }

    public String get_Fecha(){
        return _fecha;
    }
}

*/

class UtilesOficina{
    
    Scanner sc = new Scanner(System.in);

    private static int CANT_PROVEE = 2;
    private static int CANT_UTILES = 2;

    private Proveedor [] _arrProveedores = new Proveedor [CANT_PROVEE];

    private Util[] _arrUtiles = new Util[CANT_UTILES];

    

    public UtilesOficina(){
        this.set_arrProveedores();
        this.set_arrUtiles();

    }
    
    
    private void set_arrUtiles(){
        try{
            int idProveedor;
            String nombre;
            int restock;
            int cantidad;
            String s_fecha;
            int tipo;
            Date fecha;

            int isDmg;
            int cantDmg = 0;

            for(int i = 0; i< CANT_UTILES;i++){
                System.out.printf("Ingrese los siguientes datos del util [%d]",  (i+1));

                System.out.println("Seleccione el tipo de util:\n1) No Perecedero\n2) Perecedero");
                tipo = sc.nextInt();
                sc.nextLine();

                System.out.println("Ingrese el nombre del util:");
                nombre = sc.nextLine();

                System.out.println("Ingrese el numero de proveedor: ");
                ImprimirProveedores();
                System.out.printf("Num. Proveedor: ");
                idProveedor = sc.nextInt();

                /*System.out.println("Ingrese el Codigo del Producto:");
                codigo = sc.nextInt();*/

                System.out.println("Ingrese el punto de reorden para " + nombre + ":");
                restock = sc.nextInt();

                System.out.println("Ingrese la cantidad de " + nombre + " que existen hasta el momento: ");
                cantidad = sc.nextInt();


                System.out.println("Hubo algun articulo dañado?\n 1) Si 2) No");
                isDmg = sc.nextInt();
                

                if(isDmg == 1){
                    do{
                    System.out.println("Cuantos utiles dañados hubo? ");
                    cantDmg = sc.nextInt();
                    cantidad = cantidad - cantDmg;
                    }while(cantDmg > cantidad);
                }


                sc.nextLine();

                if(tipo == 1){
                    _arrUtiles[i] = new Util(nombre,_arrProveedores[idProveedor-1].get_Nombre(),restock, cantidad,null,isDmg,cantDmg);
                }
                else{
                    
                    System.out.println("Ingrese la fecha de vencimiento(dd/MM/yyyy)");
                    s_fecha = sc.nextLine();
                
                    fecha = new SimpleDateFormat("dd/MM/yyyy").parse(s_fecha);
                    _arrUtiles[i] = new Util(nombre,_arrProveedores[idProveedor-1].get_Nombre(),restock, cantidad,fecha,isDmg,cantDmg);  

                }
                        
            }
        }catch(ParseException e){
            System.out.println("Forma de fecha incorrecto");
        }
    }



    private void set_arrProveedores(){
        for(int i = 0; i< CANT_PROVEE;i++){
            System.out.printf("Ingrese el nombre del proveedor [%d]: ",i+1);
            _arrProveedores[i] = new Proveedor(sc.nextLine());
        }
    }

    public void ListarReorden(){
        System.out.println("Ariticulos a punto de reorden: ");
        for(int i = 0; i< CANT_UTILES;i++ ){
            if(_arrUtiles[i].get_Cantidad() <= _arrUtiles[i].get_Restock()){
                System.out.printf("\nEl %s esta en punto de restock y tiene [%d]",_arrUtiles[i].get_Nombre(),_arrUtiles[i].get_Cantidad());
            }
        }
    }

    public void ListarProductosSinStock(){
        System.out.println("Articulos sin stock: ");
        for(int i = 0; i< CANT_UTILES;i++ ){
        if(_arrUtiles[i].get_Cantidad() == 0){
            System.out.printf("\nEl %s no tiene stock", _arrUtiles[i].get_Nombre());
        }
        
        }
    }

    public void ListarProductosVencidos(){
        System.out.println("Articulos vencidos: ");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        Date fechaHoy = new Date();

        for(int i = 0; i< CANT_UTILES;i++ ){
            if(_arrUtiles[i].get_Fecha() != null){
                if(_arrUtiles[i].get_Fecha().compareTo(fechaHoy) <= 0 ){
                    System.out.printf("\nEl %s esta vencido, fecha:  %s",_arrUtiles[i].get_Nombre(),formato.format(_arrUtiles[i].get_Fecha()));
                }
            }
            else{
                continue;
            }
        }
    }

    public void ListarProductosDmg(){
        System.out.println("Arituclos dañados: ");

        for(int i = 0; i< CANT_UTILES;i++){
            if(_arrUtiles[i].get_IsDmg() == 1){
                System.out.printf("\nEl util %s tiene %d dañados :c", _arrUtiles[i].get_Nombre(),_arrUtiles[i].get_cantDmg());
            }
        }
    }




    public void ImprimirProveedores(){
        for(int i = 0; i< CANT_PROVEE;i++ ){
                System.out.printf("%d) %s\n",i+1,_arrProveedores[i].get_Nombre());
        }

    }


    public void ImprimirArticulos(){
        System.out.println("\n\t\t PRODUCTO\t| CANTIDAD\t| RESTOCK\t| PROVEEDOR");
        for(int i = 0; i< CANT_UTILES;i++ ){
            System.out.println("\n\t\t" + _arrUtiles[i].get_Nombre() +"\t\t|"+ _arrUtiles[i].get_Cantidad() +"\t\t|"+ _arrUtiles[i].get_Restock() +"\t\t|"+ _arrUtiles[i].get_Proveedor());

            if(_arrUtiles[i].get_Fecha() != null){
                System.out.println("\t\t\t\t Vencimiento:" + _arrUtiles[i].get_Fecha());
            }
        }

    }

    public void ArticuloPorExistencia(){
        System.out.println("\n\t\t NOMBRE DEL PRODUCTO \t |  CANTIDAD EXISTENTE");
        for(int i = 0; i< CANT_UTILES;i++ ){
            System.out.println("\t\t\t" +_arrUtiles[i].get_Nombre() + "\t\t | \t " + _arrUtiles[i].get_Cantidad());

        }
    }

    public void ArticulosPorProveedor(){
        for(int j=0; j<CANT_PROVEE; j++){
            System.out.println("\nPROVEEDOR \t\t PRODUCTOS");
            System.out.println(_arrProveedores[j].get_Nombre());
            for(int i = 0; i< CANT_UTILES;i++ ){
                if(_arrUtiles[i].get_Proveedor().equals(_arrProveedores[j].get_Nombre())){
                    System.out.println("\t\t\t-"+_arrUtiles[i].get_Nombre());
                }
            }
        }
    }




}





 public class ProyectoSemestral {

    public static void main(String[] args) {

        UtilesOficina utiles = new UtilesOficina();
        /*utiles.ImprimirProveedores();
        utiles.ImprimirArticulos();*/
        utiles.ListarProductosVencidos();
        utiles.ListarReorden();
        utiles.ListarProductosSinStock();
        utiles.ListarProductosDmg();


        
    }
}

/*abstract
Nombre1
Nombre2
Util1
10
20
1
Util2
10
20


*/