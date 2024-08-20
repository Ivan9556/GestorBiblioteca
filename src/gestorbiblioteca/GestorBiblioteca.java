/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestorbiblioteca;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.Scanner;

/**
 *
 * @author _ivn
 */
public class GestorBiblioteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Método entrada teclado
        Scanner sc = new Scanner(System.in);
        //Variables
        int opcion;
        ObjectContainer bd = null;
        //Inicio del programa
        System.out.println("Bienvenido a la Bibliteca");
        do {
            System.out.println("¿Que deseas hacer? 1.Agregar 2.Eliminar 3.Buscar 4.Salir");
            opcion = sc.nextInt();
            //Selección
            switch (opcion) {
                case 1:
                    try {
                        //Nos conectamos a la base de datos, si no existe la crea
                        bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "ArchivoGuardado.txt");
                        //Agregamos el libro
                        sc.nextLine();
                        System.out.println("Escribe el titulo del libro");
                        String Titulo = sc.nextLine();
                        System.out.println("Escribe el autor del libro");
                        String Autor = sc.nextLine();
                        System.out.println("Escribe el numero de paginas");
                        int Paginas = sc.nextInt();
                        System.out.println("Escribe el año del libro");
                        int Año = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Escribe la editorial");
                        String Editorial = sc.nextLine();
                        //Comprobamos que el libro no exista
                        Libro libroEjemplo = new Libro(Titulo, Autor, Paginas, Año, Editorial);
                        ObjectSet<Libro> res = bd.queryByExample(libroEjemplo);
                        //El método isEmpty(), devuelve true si resultado está vacío, por lo que no existe libro.
                        if (res.isEmpty()) {
                            //Objeto nuevo
                            Libro nuevoLibro = new Libro(Titulo, Autor, Paginas, Año, Editorial);
                            //Introducimos el libreo en la base de datos
                            bd.store(nuevoLibro);
                            System.out.println("Se ha guardado correctamente");
                        } else {
                            System.out.println("Ya existe en la base de datos");
                        }
                    } catch (Exception e) {
                        System.out.println("No se ha podido conectar a la base de datos");
                    } finally {
                        if (bd != null) {
                            bd.close();
                        }
                    }
                    break;
                case 2:
                    try {
                        //Nos conectamos a la base de datos, si no existe la crea
                        bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "ArchivoGuardado.txt");
                        //Agregamos el libro
                        sc.nextLine();
                        System.out.println("Escribe el titulo del libro");
                        String Titulo = sc.nextLine();
                        System.out.println("Escribe el autor del libro");
                        String Autor = sc.nextLine();
                        Libro libroEjemplo = new Libro(Titulo, Autor, 0, 0, null);
                        ObjectSet<Libro> res = bd.queryByExample(libroEjemplo);
                        //Utilizamos la inversa del método isEmpty para que devuelva flase en caso de existencias
                        if (!res.isEmpty()) {
                            while (res.hasNext()) {
                                System.out.println(res.next());
                            }
                            System.out.println("Hay existencias");
                            System.out.println("¿Deseas borrar el libro? 1.Si 2.No");
                            opcion = sc.nextInt();
                            if (opcion == 1) {
                                /*Utilizamos el bucle for-each para asignar la variable libro de tipo Libro(clase) 
                               a cada resultado de nuestra variable "res" en base al ejemplo.
                                 */
                                for (Libro libro : res) {
                                    bd.delete(libro);
                                }
                                System.out.println("Se elimino el libro correctamente");
                            } else {
                                System.out.println("No se elimino el libro");
                            }
                        } else {
                            System.out.println("No existe libro");
                        }

                    } catch (Exception e) {
                        System.out.println("No se ha podido conectar a la base de datos");
                    } finally {
                        if (bd != null) {
                            bd.close();
                        }
                    }

                    break;
                case 3:
                    try {
                        //Conectamos con la base de datos
                        bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "ArchivoGuardado.txt");
                        //Agregamos el libro
                        sc.nextLine();
                        System.out.println("Escribe el titulo del libro");
                        String Titulo = sc.nextLine();
                        System.out.println("Escribe el autor del libro");
                        String Autor = sc.nextLine();
                        //Hacemos la consulta utilizando un objeto de ejemplo 
                        Libro libroEjemplo = new Libro(Titulo, Autor, 0, 0, null);
                        ObjectSet<Libro> res = bd.queryByExample(libroEjemplo);
                        if (!res.isEmpty()) {
                            while (res.hasNext()) {
                                System.out.println(res.next());
                            }
                        } else {
                            System.out.println("No hay existencias");
                        }
                    } catch (Exception e) {
                        System.out.println("No se ha podido conectar a la base de datos");
                    } finally {
                        bd.close();
                    }
                    break;
                case 4:
                    System.out.println("¡Hasta pronto!");
                    break;
                    
                default:
                    System.out.println("No has elegido ninguna opcion");
                    
            }

        } while (opcion != 4);
    }

}
