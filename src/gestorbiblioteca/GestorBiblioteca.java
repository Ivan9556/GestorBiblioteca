/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestorbiblioteca;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import java.util.Scanner;

/**
 *
 * @author _ivn
 */
public class GestorBiblioteca {

    public static void main(String[] args) {
        //Entrada teclado
        Scanner sc = new Scanner(System.in);
        //Variables
        Libro libro = new Libro();
        Cliente cliente = new Cliente();
        int opcion;
        ObjectContainer bd = null;
       //Inicio del programa
        System.out.println("Bienvenido a la Bibliteca.e");
        System.out.println("Para interactuar con el gestor utilice los numeros del teclado y pulse enter.");
        System.out.println("");
        try {
            do {
                System.out.println("¿Que deseas hacer? 1.Agregar 2.Eliminar 3.Buscar 4.Salir");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "BaseDatos");
                        try {
                            System.out.println("Desas agregar 1.Cliente 2.Libro 3.Volver");
                            opcion = sc.nextInt();
                            switch (opcion) {
                                case 1:
                                    cliente.agregarCliente(bd);
                                    break;
                                case 2:
                                    libro.agregarLibro(bd);
                                    break;
                                case 3:
                                    break;
                                default:
                                    System.out.println("Respuesta incorrecta");
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println("No se pudo conectar a la base de datos");
                        } finally {
                            if (bd != null) {
                                bd.close();
                            }
                        }
                        break;
                    case 2:
                        bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "BaseDatos");
                        try {
                            System.out.println("Deseas eliminar 1.Cliente 2.Libro 3.Volver");
                            opcion = sc.nextInt();
                            switch (opcion) {
                                case 1:
                                    cliente.eleminarCliente(bd);
                                    break;
                                case 2:
                                    libro.eliminarLibro(bd);
                                    break;
                                case 3:
                                    break;
                                default:
                                    System.out.println("Respuesta incorrecta");
                            }
                        } catch (Exception e) {
                            System.out.println("No se pudo conectar a la base de datos");
                        } finally {
                            if (bd != null) {
                                bd.close();
                            }
                        }
                        break;
                    case 3:
                        bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "BaseDatos");
                        try {
                            System.out.println("Deseas buscar 1.Cliente 2.Libro 3.Volver");
                            opcion = sc.nextInt();
                            switch (opcion) {
                                case 1:
                                    cliente.buscarCliente(bd);
                                    break;
                                case 2:
                                    libro.buscarLibro(bd);
                                    break;
                                case 3:
                                    break;
                                default:
                                    System.out.println("Error de seleccion");
                            }
                        } catch (Exception e) {
                            System.out.println("No se pudo conectar a la base de datos");
                        } finally {
                            if (bd != null) {
                                bd.close();
                            }
                        }
                        break;
                    case 4:
                        System.out.println("¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("No has elegido ninguna opcion");
                        break;
                }
            } while (opcion != 4);

        } catch (Exception e) {
            System.out.println("Error al pulsar la tecla ");
        }
    }
}
