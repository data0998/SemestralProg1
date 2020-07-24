package Codigo;
import java.util.*;



 public class ProyectoSemestral {
    
     
    public static void main(String[] args) {

        UtilesOficina utiles = new UtilesOficina();
        Scanner sc = new Scanner(System.in);
        int dia = 7;
        int opcion,cantidad,idProducto;

        System.out.println("Bienvenido al comecio XYZ, porfavor siga las instrucciones al pie de la letra: ");
        System.out.println("Ingrese los datos de los Proveedores " + UtilesOficina.CANT_PROVEE);

        

/* 1) Hacer el inventario Fisico*/
        utiles.set_arrProveedores();
        utiles.set_arrUtiles();

        utiles.VenceProximoMes();

 /*2) Hacer compra y venta*/
        do{
            System.out.printf("\nBienvenido al area de compra y venta de productos del dia %d.\nPorfavor ingrese la opcion:\n1) Compra\n2) Venta\n3) Siguiente Dia\n0) Salir\n",dia);
            opcion = sc.nextInt();

            utiles.ImprimirArticulos();

            if(opcion == 1){
                
                System.out.println("Hora de comprar c:, porfavor, ingresa el producto que quieras comprar");
                idProducto = sc.nextInt();
                System.out.println("Porfavor ingresar la cantidad  comprar:");
                cantidad = sc.nextInt();
               
                utiles.comprar_Util(idProducto, cantidad, dia);
            }
            else if(opcion == 2){
                System.out.println("Hora de vender c:, porfavor, ingresa el producto que quieras comprar");
                idProducto = sc.nextInt();
                System.out.println("Porfavor ingresar la cantidad  comprar:");
                cantidad = sc.nextInt();

                utiles.vender_Util(idProducto, cantidad, dia);
            }
            else if(opcion == 3){
                System.out.println("Pasamos al siguiente dia...");
                dia++;
            }
            else{
                System.out.println("Opcion Invalida");
            }

            if(dia > 7){
                System.out.println("Ya han pasado los 7 dias c:");
                break;
            }
        }while(opcion != 0);

 /*3) Hacer consultas*/
            do{
                System.out.println("Las consultas posibles son:\n1)Productos por existencia\n2)Productos que vencen en un mes\n3)Productos por proveedor\n4)Productos en punto de reorden\n5)Productos sin existencia\n6)Productos vencidos\n7)Productos dañados\n8)Productos Comprados\n9)Productos Vendidos\n0)Salir");
                System.out.printf("Opcion:");
                opcion = sc.nextInt();

                switch(opcion){
                    
                    case 1:
                        utiles.ArticuloPorExistencia();
                    break;

                    case 2:
                        utiles.VenceProximoMes();
                    break;

                    case 3:
                        utiles.ArticulosPorProveedor();
                    break;

                    case 4:
                        utiles.ArticulosPorReorden();
                    break;

                    case 5:
                        utiles.ArticulosSinStock();
                    break;

                    case 6:
                        utiles.ArticulosVencidos();
                    break;

                    case 7:
                        utiles.ArticulosDmg();
                    break;

                    case 8:
                        utiles.imprimirCompra();
                    break;

                    case 9:
                        utiles.imprimirVenta();
                    break;

                    default:
                        System.out.println("Opcion invalida");
                    break;
                }

            }while(opcion != 0);

            System.out.println("Vivan las hamburguesas");

    }
}

        

