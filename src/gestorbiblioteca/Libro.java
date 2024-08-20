/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestorbiblioteca;

/**
 *
 * @author _ivn
 */
public class Libro {

    String Nombre;
    String Autor;
    int Paginas;
    int Año;
    String Editorial;

    public Libro() {

    }

    public Libro(String Nombre, String Autor, int Paginas, int Año, String Editorial) {
        this.Nombre = Nombre;
        this.Autor = Autor;
        this.Paginas = Paginas;
        this.Año = Año;
        this.Editorial = Editorial;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getAutor() {
        return Autor;
    }

    public int getPaginas() {
        return Paginas;
    }

    public int getAño() {
        return Año;
    }

    public String getEditorial() {
        return Editorial;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setAutor(String autor) {
        this.Autor = autor;
    }

    public void setPaginas(int Paginas) {
        this.Paginas = Paginas;
    }

    public void setAño(int año) {
        this.Año = año;
    }

    public void setEditorial(String Editorial) {
        this.Editorial = Editorial;
    }

    @Override
    public String toString() {
        return "Libro{" + "Nombre=" + Nombre + ", Autor=" + Autor + ", Paginas=" + Paginas + ", A\u00f1o=" + Año + ", Editorial=" + Editorial + '}';
    }


}
