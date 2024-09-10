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
public class Cliente {

    private String Nombre;
    private String Apellidos;
    private String DNI;
    private int nLibros;

    public Cliente() {
    }

    public Cliente(String Nombre, String Apellidos, String DNI) {
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.DNI = DNI;
    }

    @Override
    public String toString() {
        return "Cliente{" + "Nombre=" + Nombre + ", Apellidos=" + Apellidos + ", DNI=" + DNI + '}';
    }

    public void agregarCliente(ObjectContainer bd) {
        Scanner sc = new Scanner(System.in);
        try {
            //Agregamo nuevo cliente 
            System.out.println("Escribe el nombre del cliente");
            String Nombre = sc.nextLine();
            System.out.println("Escribe los apellidos del cliente");
            String Apellidos = sc.nextLine();
            System.out.println("Escribe el DNI del cliente");
            String DNI = sc.nextLine();
            Cliente ejCliente = new Cliente(Nombre, Apellidos, DNI);
            ObjectSet<Cliente> res = bd.queryByExample(ejCliente);
            if (res.isEmpty()) {
                Cliente nuevoCliente = new Cliente(Nombre, Apellidos, DNI);
                bd.store(nuevoCliente);
                System.out.println("Se agrego correctamente");
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

    public void buscarCliente(ObjectContainer bd) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Escribe el DNI del cliente ");
            String DNI = sc.nextLine();
            Cliente ejeCliente = new Cliente(null, null, DNI);
            ObjectSet<Cliente> res = bd.queryByExample(ejeCliente);
            if (!res.isEmpty()) {
                while (res.hasNext()) {
                    System.out.println(res.next());
                }
            } else {
                System.out.println("No existe cliente con ese DNI");
            }
        } catch (Exception e) {
            System.out.println("No se puedo conectar a la base de datos");
        } finally {
            if (bd != null) {
                bd.close();
            }
        }
    }

    public void eleminarCliente(ObjectContainer bd) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Escribe el DNI del cliente");
            String DNI = sc.nextLine();
            Cliente ejCliente = new Cliente(null, null, DNI);
            ObjectSet<Cliente> res = bd.queryByExample(ejCliente);
            if (!res.isEmpty()) {
                while (res.hasNext()) {
                    System.out.println(res.next());
                }
                System.out.println("¿Deseas eliminar dicho cliente 1.Si 2.No?");
                int respuesta = sc.nextInt();
                switch (respuesta) {
                    case 1:
                        for (Cliente cliente : res){
                        bd.delete(cliente);
                        System.out.println("Se elimino correctamente");
                        }
                        break;
                    case 2:
                        System.out.println("No se elimino");
                        break;
                    default:
                        System.out.println("Respuesta incorrecta");
                        break;
                }
            } else {
                System.out.println("No existe en la base de datos");
            }
        } catch (Exception e) {
            System.out.println("No se puedo contectar a la base de datos");
        } finally {
            if (bd != null) {
                bd.close();
            }
        }
    }
}
