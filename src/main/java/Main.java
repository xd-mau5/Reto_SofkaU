import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        // Creando un nuevo objeto de la clase Scanner para leer datos del usuario.
        Scanner sc = new Scanner(System.in);
// Creando un nuevo objeto de la clase Metodos.
        Metodos nave = new Metodos(0, 0, null, 0, 0, null);
        try {
            int opcion = 0;
            // Un bucle que contiene un menú para que el usuario pueda elegir una opción, y se repite hasta que el usuario elija la opción 9, la cual es salir.
            do{
                nave.LimpiarConsola();
                System.out.println("Bienvenido al sistema de naves espaciales");
                System.out.println("1. Crear Nave");
                System.out.println("2. Mostrar Nave");
                System.out.println("3. Actualizar Nave");
                System.out.println("4. Eliminar Nave");
                System.out.println("5. Despegar Nave");
                System.out.println("6. Acelerar Nave");
                System.out.println("7. Aterrizar Nave");
                System.out.println("8. Estado de la Nave");
                System.out.println("9. Filtrar por Caracteristicas");
                System.out.println("10. Salir");
    
                opcion = sc.nextInt();
                int IDNave = 0;
                int TipoNave = 0;
                String NombreNave = null;
                double EmpujeNave = 0;
                double PesoNave = 0;
                String TipoCombustible = null;
                // Una declaración de cambio que se utiliza para realizar diferentes acciones en
                // función de diferentes condiciones.
                switch (opcion){
                    // Con el caso 1, el usuario puede crear una nave.
                    case 1:
                        nave.LimpiarConsola();
                        System.out.println("Introduce el ID de la nave");
                        IDNave = sc.nextInt();
                        sc.nextLine();
                        if (nave.ValidarNave(IDNave) == false){
                            System.out.println("Introduce el tipo de nave: \n1. Lanzadera \n2. No Tripulada \n3. Tripulada");
                            TipoNave = sc.nextInt();
                            if (TipoNave < 1 || TipoNave > 3){
                                System.out.println("Introduce un tipo de nave valido");
                                Thread.sleep(1000);
                                break;
                            }
                            sc.nextLine();
                            System.out.println("Introduce el nombre de la nave");
                            NombreNave = sc.nextLine();
                            System.out.println("Introduce el empuje de la nave con unidades de empuje");
                            EmpujeNave = sc.nextDouble();
                            if (EmpujeNave < 0){
                                System.out.println("Introduce un empuje valido, no puede ser negativo");
                                Thread.sleep(1000);
                                break;
                            }
                            sc.nextLine();
                            System.out.println("Introduce la masa de la nave con unidades de masa");
                            PesoNave = sc.nextDouble();
                            if (PesoNave < 0){
                                System.out.println("Introduce una masa valida, no puede ser negativa");
                                Thread.sleep(1000);
                                break;
                            }
                            sc.nextLine();
                            System.out.println("Introduce el tipo de combustible");
                            TipoCombustible = sc.nextLine();
                            if (nave.CrearNave(IDNave, TipoNave, NombreNave, EmpujeNave, PesoNave, TipoCombustible)){
                                System.out.println("Nave creada correctamente");
                                Thread.sleep(1000);
                                nave.LimpiarConsola();
                            }else{
                                System.out.println("Error al crear la nave");
                                Thread.sleep(1000);
                                nave.LimpiarConsola();
                            }
                        }else{
                            System.out.println("La nave con ID " + IDNave + " ya existe"+
                            "\nIntroduce un ID diferente");
                            Thread.sleep(1500);
                            nave.LimpiarConsola();
                        }
                        break;
                    // Con el caso 2, el usuario puede mostrar la información de una nave.
                    case 2:
                        nave.LimpiarConsola();
                        System.out.println("Introduce el ID de la nave");
                        IDNave = sc.nextInt();
                        sc.nextLine();
                        if (nave.ValidarNave(IDNave)){
                            System.out.println(nave.MostrarNave(IDNave));
                            Thread.sleep(10000);
                            nave.LimpiarConsola();
                        }else{
                            System.out.println("La nave con ID " + IDNave + " no existe");
                            Thread.sleep(1000);
                            nave.LimpiarConsola();
                        }
                        break;
                    // Con el caso 3, el usuario puede actualizar la información de una nave.
                    case 3:
                        nave.LimpiarConsola();
                        System.out.println("Introduce el ID de la nave");
                        IDNave = sc.nextInt();
                        sc.nextLine();
                        if (nave.ValidarNave(IDNave)){
                            System.out.println("Introduce el tipo de nave: \n1. Lanzadera \n2. No Tripulada \n3. Tripulada");
                            TipoNave = sc.nextInt();
                            if (TipoNave < 1 || TipoNave > 3){
                                System.out.println("Introduce un tipo de nave valido");
                                Thread.sleep(1000);
                                break;
                            }
                            sc.nextLine();
                            System.out.println("Introduce el nombre de la nave");
                            NombreNave = sc.nextLine();
                            System.out.println("Introduce el empuje de la nave en Kg");
                            EmpujeNave = sc.nextDouble();
                            if (EmpujeNave < 0){
                                System.out.println("Introduce un empuje valido, no puede ser negativo");
                                Thread.sleep(1000);
                                break;
                            }
                            sc.nextLine();
                            System.out.println("Introduce la masa de la nave en Kg");
                            PesoNave = sc.nextDouble();
                            if (PesoNave < 0){
                                System.out.println("Introduce un peso valido, no puede ser negativo");
                                Thread.sleep(1000);
                                break;
                            }
                            sc.nextLine();
                            System.out.println("Introduce el tipo de combustible");
                            TipoCombustible = sc.nextLine();
                            if (nave.ActualizarNave(IDNave, TipoNave, NombreNave, EmpujeNave, PesoNave, TipoCombustible)){
                                System.out.println("Nave actualizada correctamente");
                                Thread.sleep(1000);
                                nave.LimpiarConsola();
                            }else{
                                System.out.println("Error al actualizar la nave");
                                Thread.sleep(1000);
                                nave.LimpiarConsola();
                            }
                        }else{
                            System.out.println("La nave con ID " + IDNave + " no existe");
                            Thread.sleep(1000);
                            nave.LimpiarConsola();
                        }
                        break;
                    // Con el caso 4, el usuario puede eliminar una nave.
                    case 4:
                        nave.LimpiarConsola();
                        System.out.println("Introduce el ID de la nave");
                        IDNave = sc.nextInt();
                        sc.nextLine();
                        if (nave.ValidarNave(IDNave)){
                            if (nave.EliminarNave(IDNave)){
                                System.out.println("Nave eliminada correctamente");
                                Thread.sleep(1000);
                                nave.LimpiarConsola();
                            }else{
                                System.out.println("Error al eliminar la nave");
                                Thread.sleep(1000);
                                nave.LimpiarConsola();
                            }
                        }else{
                            System.out.println("La nave con ID " + IDNave + " no existe");
                            Thread.sleep(1000);
                            nave.LimpiarConsola();
                        }
                        break;
                    // Con el caso 5, el usuario puede despegar una nave.
                    case 5:
                        nave.LimpiarConsola();
                        System.out.println("Introduce el ID de la nave");
                        IDNave = sc.nextInt();
                        sc.nextLine();
                        nave.Despegar(IDNave);
                        Thread.sleep(1000);
                        nave.LimpiarConsola();
                        break;
                    // Con el caso 6, el usuario puede acelerar una nave.
                    case 6:
                        nave.LimpiarConsola();
                        System.out.println("Introduce el ID de la nave");
                        IDNave = sc.nextInt();
                        sc.nextLine();
                        nave.Acelerar(IDNave);
                        Thread.sleep(1000);
                        nave.LimpiarConsola();
                        break;
                    // Con el caso 7, el usuario puede aterrizar una nave.
                    case 7:
                        nave.LimpiarConsola();
                        System.out.println("Introduce el ID de la nave");
                        IDNave = sc.nextInt();
                        sc.nextLine();
                        nave.Aterrizar(IDNave);
                        Thread.sleep(1000);
                        nave.LimpiarConsola();
                        break;
                    // Con el caso 8, el usuario puede ver el estado de una nave.
                    case 8:
                        nave.LimpiarConsola();
                        System.out.println("Introduce el ID de la nave");
                        IDNave = sc.nextInt();
                        sc.nextLine();
                        nave.Estado(IDNave);
                        Thread.sleep(2000);
                        nave.LimpiarConsola();
                        break;
                    // Con el caso 9, el usuario puede filtrar las naves por su tipo, nombre, empuje, peso o tipo de combustible.
                    case 9:
                        int OpcionFiltro;
                        String Filtro;
                        nave.LimpiarConsola();
                        System.out.println("Introduce el tipo de filtro");
                        System.out.println("1. Tipo de nave");
                        System.out.println("2. Nombre de la nave");
                        System.out.println("3. Empuje de la nave");
                        System.out.println("4. Peso de la nave");
                        System.out.println("5. Tipo de combustible");
                        OpcionFiltro = sc.nextInt();
                        sc.nextLine();
                        switch (OpcionFiltro){
                            case 1:
                                nave.LimpiarConsola();
                                System.out.println("Introduce el tipo de nave");
                                System.out.println("1. Tripulada");
                                System.out.println("2. No tripulada");
                                System.out.println("3. Carga");
                                int tipo = sc.nextInt();
                                sc.nextLine();
                                // Filtra una lista de naves espaciales por tipo de nave.
                                if (tipo == 1 || tipo == 2 || tipo == 3){
                                    String TipoNaveFiltro = String.valueOf(tipo);
                                    nave.LimpiarConsola();
                                    System.out.print(nave.FiltrarNaves("tipo", TipoNaveFiltro));
                                    Thread.sleep(6000);
                                    nave.LimpiarConsola();
                                }else{
                                    System.out.println("Introduce una opcion valida");
                                    Thread.sleep(1000);
                                    nave.LimpiarConsola();
                                }
                                break;
                            case 2:
                                // Filtra una lista de naves espaciales por nombre de la nave.
                                nave.LimpiarConsola();
                                System.out.println("Introduce el nombre de la nave");
                                Filtro = sc.nextLine();
                                System.out.println(nave.FiltrarNaves("nombre", Filtro));
                                Thread.sleep(6000);
                                nave.LimpiarConsola();
                                break;
                            case 3:
                                // Filtra una lista de naves espaciales por empuje de la nave.
                                nave.LimpiarConsola();
                                System.out.println("Introduce el empuje de la nave");
                                Filtro = sc.nextLine();
                                System.out.println(nave.FiltrarNaves("empuje", Filtro));
                                Thread.sleep(6000);
                                nave.LimpiarConsola();
                                break;
                            case 4:
                                // Filtra una lista de naves espaciales por peso de la nave.
                                nave.LimpiarConsola();
                                System.out.println("Introduce el peso de la nave");
                                Filtro = sc.nextLine();
                                System.out.println(nave.FiltrarNaves("peso", Filtro));
                                Thread.sleep(6000);
                                nave.LimpiarConsola();
                                break;
                            case 5:
                                // Filtra una lista de naves espaciales por tipo de combustible de la nave.
                                nave.LimpiarConsola();
                                System.out.println("Introduce el tipo de combustible");
                                Filtro = sc.nextLine();
                                System.out.println(nave.FiltrarNaves("combustible", Filtro));
                                Thread.sleep(6000);
                                nave.LimpiarConsola();
                                break;
                            default:
                                // Si el usuario introduce un numero que no esta entre 1 y 5, se le mostrara un mensaje de error.
                                nave.LimpiarConsola();
                                System.out.println("Introduce una opcion valida");
                                Thread.sleep(1000);
                                nave.LimpiarConsola();
                                break;
                        }
                        case 10:
                            // Con el caso 10, el usuario puede salir del programa.
                            nave.LimpiarConsola();
                            System.out.println("Saliendo del programa");
                            break;
                    default:
                        // Si el usuario introduce un numero que no esta entre 1 y 10, se le mostrara un mensaje de error.
                        nave.LimpiarConsola();
                        System.out.println("Introduce una opcion valida");
                        Thread.sleep(1000);
                        nave.LimpiarConsola();
                        break;
                }
            }while(opcion != 10);
        // Captura la excepcion de que el usuario introduzca un numero o un caracter que no sea un numero.
        } catch (InputMismatchException e) {
            nave.LimpiarConsola();
            System.out.println("Error");
            System.out.println("Introduce un numero entero");
            Thread.sleep(1000);
            nave.LimpiarConsola();
            main(args);
        // Atrapa la excepción de que el hilo se interrumpe.
        } catch (InterruptedException e) {
            nave.LimpiarConsola();
            System.out.println("Error: " + e.getMessage());
            Thread.sleep(1000);
            nave.LimpiarConsola();
            main(args);
        }
            }
}
