package Codigo;
import java.util.*;



 public class ProyectoSemestral {
    public static void main(String[] args) {

        System.out.println("Bienvenido al comecio XYZ, porfavor siga las instrucciones al pie de la letra: ");
        System.out.println("Ingrese los datos de los Proveedores");

        UtilesOficina utiles = new UtilesOficina();

        

        /* 
        1: Hacer inventario fisico
        2: Hacer compras y ventas por dias
        3: Hacer las operaciones.
            Hay que pensar bien como organizar las compras y ventas, si por dia o por producto.
        
        */
        int idProducto =1;
        int cantCompra = 2;
        int cantVenta = 1;
        int dia = 1;

        
        

        utiles.comprar_Util(idProducto, cantCompra,dia);
        utiles.vender_Util(idProducto, cantVenta,dia);

        utiles.imprimirVenta();
        utiles.imprimirCompra();

        utiles.ListarProductosVencidos();
        utiles.ListarReorden();
        utiles.ListarProductosSinStock();
        utiles.ListarProductosDmg();
        
    }
}
