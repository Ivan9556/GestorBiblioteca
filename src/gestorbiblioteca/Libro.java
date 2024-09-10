/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestorbiblioteca;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.Scanner;

/**
 *
 * @author _ivn
 */
public class Libro {

    String Titulo;
    String Autor;
    int Paginas;
    int Año;
    String Editorial;

    public Libro() {
    }

    public Libro(String Titulo, String Autor, int Paginas, int Año, String Editorial) {
        this.Titulo = Titulo;
        this.Autor = Autor;
        this.Paginas = Paginas;
        this.Año = Año;
        this.Editorial = Editorial;
    }

    @Override
    public String toString() {
        return "Libro{" + "Titulo=" + Titulo + ", Autor=" + Autor + ", Paginas=" + Paginas + ", A\u00f1o=" + Año + ", Editorial=" + Editorial + '}';
    }

    public void agregarLibro(ObjectContainer bd) {
        Scanner sc = new Scanner(System.in);
        try {
            //Agregamos nuevo libro
            System.out.println("Escribe el titulo del libro");
            String Titulo = sc.nextLine();
            System.out.println("Escribe el autor del libro");
            String Autor = sc.nextLine();
            System.out.println("Escribe el numero de paginas");
            int Paginas = sc.nextInt();
            sc.nextLine();
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
            System.out.println("No se agrego correctamente");
        } finally {
            if (bd != null) {
                bd.close();
            }
        }
    }

    public void buscarLibro(ObjectContainer bd) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Introduce el titulo del libro");
            String Titulo = sc.nextLine();
            Libro ejeLibro = new Libro(Titulo, null, 0, 0, null);
            ObjectSet<Cliente> res = bd.queryByExample(ejeLibro);
            if (!res.isEmpty()) {
                while (res.hasNext()) {
                    System.out.println(res.next());
                }
            } else {
                System.out.println("No existe libro con ese titulo ");
            }
        } catch (Exception e) {
            System.out.println("No se puedo conectar a la base de datos ");
        } finally {
            if (bd != null) {
                bd.close();
            }
        }
    }

    public void eliminarLibro(ObjectContainer bd) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Introduce el titulo del libro");
            String Titulo = sc.nextLine();
            Libro ejLibro = new Libro(Titulo, null, 0, 0, null);
            ObjectSet<Libro> res = bd.queryByExample(ejLibro);
            if (!res.isEmpty()) {
                while (res.hasNext()) {
                    System.out.println(res.next());
                    System.out.println("¿Deseas eliminar ese libro? 1.Si 2.No");
                    int respuesta = sc.nextInt();
                    switch (respuesta) {
                        case 1:
                            for(Libro libro : res){
                                bd.delete(libro);
                            }
                            System.out.println("Se elimino correctamente");
                            break;
                        case 2:
                            System.out.println("No se elimino");
                            break;
                        default:
                            System.out.println("Respuesta incorrecta");
                            break;
                    }
                }
            } else {
                System.out.println("No se encontro ningun libro");
            }
        }catch (Exception e){
            System.out.println("No se puedo contectar a la base de datos");
        }finally{
            if(bd != null){
                bd.close();
            }
        }
    }
}
