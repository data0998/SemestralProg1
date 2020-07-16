package Codigo;
import java.util.*;



 public class ProyectoSemestral {
    public static void main(String[] args) {

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

        UtilesOficina utiles = new UtilesOficina();
        

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
