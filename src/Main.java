
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factura factura = new Factura();
        List<Producto> productosDisponibles = new ArrayList<>();

        boolean salir = false;

        while (!salir) {
            System.out.println("Menú Principal");
            System.out.println("a. Ingresar producto");
            System.out.println("b. Facturar producto");
            System.out.println("c. Imprimir factura");
            System.out.println("d. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion.toLowerCase()) {
                case "a":
                    System.out.println("Ingrese los datos del producto:");
                    System.out.print("Nombre: ");
                    String nombreProducto = scanner.nextLine();
                    System.out.print("Precio normal: ");
                    double precioNormal = scanner.nextDouble();
                    System.out.print("Precio al por mayor: ");
                    double precioMayorista = scanner.nextDouble();
                    System.out.print("Cantidad desde la cual aplica precio mayorista: ");
                    int cantidadMayorista = scanner.nextInt();
                    scanner.nextLine();

                    Producto nuevoProducto = new Producto(nombreProducto, precioNormal, precioMayorista, cantidadMayorista);
                    productosDisponibles.add(nuevoProducto);

                    System.out.print("¿Desea ingresar otro producto? (S/N): ");
                    String continuarIngresando = scanner.nextLine();
                    if (continuarIngresando.equalsIgnoreCase("n")) {
                        System.out.println();
                    }
                    break;

                case "b":
                    if (productosDisponibles.isEmpty()) {
                        System.out.println("No hay productos disponibles para facturar.");
                        System.out.println();
                        break;
                    }

                    System.out.println("Seleccione un producto:");

                    for (int i = 0; i < productosDisponibles.size(); i++) {
                        Producto producto = productosDisponibles.get(i);
                        int numeroProducto = i + 1;
                        System.out.println(numeroProducto + ". " + producto.getNombre() + " (Precio normal: $" + producto.getPrecioNormal()
                                + ") (Precio al por mayor, más de " + producto.getCantidadMayorista() + " unidades: $" + producto.getPrecioMayorista() + ")");
                    }

                    int seleccionProducto = scanner.nextInt();
                    scanner.nextLine();

                    if (seleccionProducto >= 1 && seleccionProducto <= productosDisponibles.size()) {
                        Producto productoSeleccionado = productosDisponibles.get(seleccionProducto - 1);
                        System.out.print("Ingrese la cantidad de unidades: ");
                        int cantidadUnidades = scanner.nextInt();
                        scanner.nextLine();

                        factura.agregarProducto(productoSeleccionado, cantidadUnidades);

                        System.out.print("¿Desea facturar otro producto? (S/N): ");
                        String continuarFacturando = scanner.nextLine();
                        if (continuarFacturando.equalsIgnoreCase("n")) {
                            System.out.println();
                        }
                    } else {
                        System.out.println("Selección inválida.");
                        System.out.println();
                    }
                    break;

                case "c":
                    if (factura.getProductos().isEmpty()) {
                        System.out.println("No hay productos en la factura.");
                        System.out.println();
                        break;
                    }

                    System.out.print("Ingrese el nombre del comprador: ");
                    String nombreComprador = scanner.nextLine();
                    factura.setNombreComprador(nombreComprador);

                    System.out.print("Ingrese el número de cédula del comprador: ");
                    String cedulaComprador = scanner.nextLine();
                    factura.setCedulaComprador(cedulaComprador);

                    factura.getDescuento();
                    factura.imprimirFactura();
                    System.out.println();
                    break;
                case "d":
                    salir = true;
                    break;

                default:
                    System.out.println("Opción inválida.");
                    System.out.println();
                    break;
            }
        }

        scanner.close();
    }

}