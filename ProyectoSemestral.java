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
    private int _codigo;
    private int _restock;
    private int _cant;
    private Date _fecha;
    

    public Util( String nombre, String proveedor, /*int codigo*/ int restock, int cantidad,Date fecha){
        this._nombre = nombre;
        this._proveedor = proveedor;
        this._restock = restock;
        this._cant = cantidad;
        this._fecha = fecha;
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

            for(int i = 0; i< CANT_UTILES;i++){
                System.out.println("Ingrese los siguientes datos de su util" + (i+1));

                System.out.println("Tipo de articulo:\n1) No Perecedero\n2) Perecedero");
                tipo = sc.nextInt();
                sc.nextLine();

                System.out.println("Ingrese el nombre:");
                nombre = sc.nextLine();

                System.out.println("Id del Proveedor");
                ImprimirProveedores();
                idProveedor = sc.nextInt();

                /*System.out.println("Ingrese el Codigo del Producto:");
                codigo = sc.nextInt();*/

                System.out.println("Establezca un punto de reorden para " + nombre + ":");
                restock = sc.nextInt();

                System.out.println("Cantidad de " + nombre + " que existen hasta el momento: ");
                cantidad = sc.nextInt();
                sc.nextLine();


                if(tipo == 1){
                    _arrUtiles[i] = new Util(nombre,_arrProveedores[idProveedor-1].get_Nombre(),restock, cantidad,null);
                }
                else{
                    
                    System.out.println("Ingrese la fecha de vencimiento(dd/MM/yyyy)");
                    s_fecha = sc.nextLine();
                
                    fecha = new SimpleDateFormat("dd/MM/yyyy").parse(s_fecha);
                    _arrUtiles[i] = new Util(nombre,_arrProveedores[idProveedor-1].get_Nombre(),restock, cantidad,fecha);  

                }
                        
            }
        }catch(ParseException e){
            System.out.println("Forma de fecha incorrecto");
        }
    }



    private void set_arrProveedores(){
        for(int i = 0; i< CANT_PROVEE;i++ ){
            System.out.printf("Ingrese el siguiente valor[%d]: ",i+1);
            _arrProveedores[i] = new Proveedor(sc.nextLine());
        }
    }


    public void ImprimirProveedores(){
        for(int i = 0; i< CANT_PROVEE;i++ ){
                System.out.printf("%d) %s\n",i+1,_arrProveedores[i].get_Nombre());
        }

    }

    public void ImprimirArticulos(){
        for(int i = 0; i< CANT_UTILES;i++ ){
            
            System.out.println(_arrUtiles[i].get_Nombre() + _arrUtiles[i].get_Cantidad() + _arrUtiles[i].get_Restock() + _arrUtiles[i].get_Proveedor());
            
            if(_arrUtiles[i].get_Fecha() != null){
                System.out.println(_arrUtiles[i].get_Fecha());
            }   
        }

    }




}





 public class ProyectoSemestral {

    public static void main(String[] args) {

        UtilesOficina utiles = new UtilesOficina();
        utiles.ImprimirProveedores();
        utiles.ImprimirArticulos();

        
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