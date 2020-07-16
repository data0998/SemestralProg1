package Codigo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UtilesOficina{
    
    Scanner sc = new Scanner(System.in);

    public static int CANT_PROVEE = 2;
    public static int CANT_UTILES = 2;

    private static int MAX_DIAS = 5;

    public Proveedor [] _arrProveedores = new Proveedor [CANT_PROVEE];

    public Util[] _arrUtiles = new Util[CANT_UTILES];

    ArrayList<ArrayList<Compra>> _matCompras = new ArrayList<>(MAX_DIAS);
    ArrayList<ArrayList<Venta>> _matVentas = new ArrayList<>(MAX_DIAS);
    

    public UtilesOficina(){
        this.set_arrProveedores();
        this.set_arrUtiles();
        this.set_matCompras();
        this.set_matVentas();

    }

    private void set_matCompras(){
        for(int i=0; i < MAX_DIAS;i++) {
            _matCompras.add(new ArrayList());
        }
    }

    private void set_matVentas(){
        for(int i=0; i < MAX_DIAS;i++) {
            _matVentas.add(new ArrayList());
        }
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
            Date fechaHoy = new Date();

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


                System.out.println("Hay algun articulo dañado?\n 1) Si 2) No");
                isDmg = sc.nextInt();
                

                if(isDmg == 1){
                    
                    System.out.println("Cuantos utiles dañados hubo? ");
                    cantDmg = sc.nextInt();
                    cantidad = cantidad - cantDmg;
                    
                }


                

                if(tipo == 1){
                    _arrUtiles[i] = new Util(nombre,_arrProveedores[idProveedor-1].get_Nombre(),restock, cantidad,null,isDmg,cantDmg,tipo);
                }
                else if(tipo == 2){
                    sc.nextLine();
                    System.out.println("Ingrese la fecha de vencimiento(dd/MM/yyyy)");
                    s_fecha = sc.nextLine();
                
                    fecha = new SimpleDateFormat("dd/MM/yyyy").parse(s_fecha);
                    
                    if(fecha.compareTo(fechaHoy) <= 0 ){
                        cantidad = 0;
                        _arrUtiles[i].aum_cantVen(cantidad);
                    }
                    
                    _arrUtiles[i] = new Util(nombre,_arrProveedores[idProveedor-1].get_Nombre(),restock, cantidad,fecha,isDmg,cantDmg,tipo);  

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


    public int isDmg(int cantidad){
        int isDmg;
        int cantDmg = 0;
        System.out.println("Hay algun articulo dañado?\n 1) Si 2) No");
        isDmg = sc.nextInt();                
        if(isDmg == 1){
            do{
            System.out.println("Cuantos utiles dañados hubo? ");
            cantDmg = sc.nextInt();
            }while(cantDmg > cantidad);
        }

        return cantDmg;
    }

    
    public void comprar_Util(int idProducto, int cantCompra,int diaCompra){
        int cantDmg = 0;
        Date fecha = null;
        String s_fecha;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        Date fechaHoy = new Date();

        idProducto = idProducto -1;

        cantDmg = isDmg(cantCompra);

        cantCompra = cantCompra - cantDmg;


        if(_arrUtiles[idProducto].get_tipo()== 2){
            sc.nextLine();
            System.out.println("Ingrese la fecha de vencimiento(dd/MM/yyyy)");
            s_fecha = sc.nextLine();
            try{
                fecha = new SimpleDateFormat("dd/MM/yyyy").parse(s_fecha);
            }catch(ParseException e){
                System.out.println("Forma de fecha incorrecto");
            }

            if(fecha.compareTo(fechaHoy) <= 0 ){
                cantCompra = 0;
            }

            _arrUtiles[idProducto].aum_cantVen(cantCompra);
        }

        _arrUtiles[idProducto].comprar_Producto(cantCompra);
        _arrUtiles[idProducto].aum_cantDmg(cantDmg);        
        _matCompras.get(idProducto).add(new Compra(idProducto,cantCompra,cantDmg,fecha,diaCompra));
    }

        public void imprimirCompra(){
        int cantidadTotal = 0;
        for (int i = 0; i < CANT_UTILES;i++) {
            int edgeCount = _matCompras.get(i).size();
            cantidadTotal = 0;
            for (int j = 0; j < edgeCount; j++) {

                System.out.printf("Compras de %s\n",_arrUtiles[i].get_Nombre());
                System.out.printf("Compra[%d]: %d | Dia: %d\n",j+1,_matCompras.get(i).get(j).get_cantidad(),_matCompras.get(i).get(j).get_diaCompra());
                cantidadTotal += _matCompras.get(i).get(j).get_cantidad(); 
            }
            System.out.printf("Total %d\n",cantidadTotal);
            
        }
    }


    public void vender_Util(int idProducto, int cantVenta,int diaCompra){
        if(_arrUtiles[idProducto-1].get_Cantidad() < cantVenta){
            System.out.println("La cantidad a comprar supera el stock actual");
        }
        else{
        _arrUtiles[idProducto-1].vender_Producto(cantVenta);
        _matVentas.get(idProducto-1).add(new Venta(idProducto,cantVenta,diaCompra));
        }
    }

    public void imprimirVenta(){
        int cantidadTotal = 0;
        for (int i = 0; i < CANT_UTILES;i++) {
            int edgeCount = _matVentas.get(i).size();
            cantidadTotal = 0;
            for (int j = 0; j < edgeCount; j++) {

                System.out.printf("Ventas de %s\n",_arrUtiles[i].get_Nombre());
                System.out.printf("Venta[%d]: %d | Dia: %d \n",j+1,_matVentas.get(i).get(j).get_cantVenta(),_matVentas.get(i).get(j).get_diaVenta());
                cantidadTotal += _matVentas.get(i).get(j).get_cantVenta(); 
            }
            System.out.printf("Total %d\n",cantidadTotal);
            
        }
    }





    




}