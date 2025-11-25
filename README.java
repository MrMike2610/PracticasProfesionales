import java.util.ArrayList;
import java.util.Scanner;

/*
 ----------------------------------------------
   SISTEMA DE PRÁCTICAS PROFESIONALES
   UNIMINUTO – Ejemplo con Programación Orientada a Objetos
   Archivo único con todas las clases y menú
 ----------------------------------------------
*/

public class SistemaPracticas {

    // =========================================================
    //                      CLASE PERSONA
    // Base para compartir nombre y correo entre estudiantes.
    // =========================================================
    static class Persona {

        protected String nombre;
        protected String correo;

        // Constructor
        public Persona(String nombre, String correo) {
            this.nombre = nombre;
            this.correo = correo;
        }

        // Método para mostrar información común
        public String mostrarInfo() {
            return "Nombre: " + nombre + ", Correo: " + correo;
        }
    }

    // =========================================================
    //                   CLASE ESTUDIANTE
    // Hereda de Persona. Tiene código, programa, empresa y estado.
    // =========================================================
    static class Estudiante extends Persona {

        private String codigo;
        private String programa;
        private String empresa;  // Empresa asignada
        private String estado;   // Por asignar / En práctica

        // Constructor
        public Estudiante(String nombre, String correo, String codigo, String programa) {
            super(nombre, correo);
            this.codigo = codigo;
            this.programa = programa;
            this.empresa = null;
            this.estado = "Por asignar";
        }

        // Asignar empresa a un estudiante
        public void asignarEmpresa(String empresa) {
            this.empresa = empresa;
            this.estado = "En práctica";
        }

        // Mostrar toda la información completa del estudiante
        @Override
        public String mostrarInfo() {
            return super.mostrarInfo()
                    + ", Código: " + codigo
                    + ", Programa: " + programa
                    + ", Empresa: " + empresa
                    + ", Estado: " + estado;
        }
    }

    // =========================================================
    //                 CLASE EMPRESA ALIADA
    // Guarda el nombre y el sector de cada empresa.
    // =========================================================
    static class EmpresaAliada {

        private String nombre;
        private String sector;

        public EmpresaAliada(String nombre, String sector) {
            this.nombre = nombre;
            this.sector = sector;
        }

        public String mostrarInfo() {
            return nombre + " - Sector: " + sector;
        }
    }

    // =========================================================
    //                  SISTEMA PRINCIPAL
    // Maneja listas de estudiantes y empresas, y el menú.
    // =========================================================

    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private ArrayList<EmpresaAliada> empresas = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // ---------------------------------------------------------
    // REGISTRAR ESTUDIANTE
    // ---------------------------------------------------------
    public void registrarEstudiante() {
        System.out.print("Nombre del estudiante: ");
        String nombre = sc.nextLine();

        System.out.print("Correo institucional: ");
        String correo = sc.nextLine();

        System.out.print("Código estudiantil: ");
        String codigo = sc.nextLine();

        System.out.print("Programa académico: ");
        String programa = sc.nextLine();

        estudiantes.add(new Estudiante(nombre, correo, codigo, programa));

        System.out.println("\n✓ Estudiante registrado con éxito.\n");
    }

    // ---------------------------------------------------------
    // REGISTRAR EMPRESA
    // ---------------------------------------------------------
    public void registrarEmpresa() {
        System.out.print("Nombre de la empresa: ");
        String nombre = sc.nextLine();

        System.out.print("Sector empresarial: ");
        String sector = sc.nextLine();

        empresas.add(new EmpresaAliada(nombre, sector));

        System.out.println("\n✓ Empresa registrada con éxito.\n");
    }

    // ---------------------------------------------------------
    // ASIGNAR PRÁCTICA
    // ---------------------------------------------------------
    public void asignarPractica() {

        // Validación previa
        if (estudiantes.isEmpty() || empresas.isEmpty()) {
            System.out.println("Debe registrar estudiantes y empresas primero.");
            return;
        }

        // Mostrar estudiantes
        System.out.println("\n--- ESTUDIANTES ---");
        for (int i = 0; i < estudiantes.size(); i++) {
            System.out.println(i + ". " + estudiantes.get(i).mostrarInfo());
        }

        System.out.print("Seleccione estudiante: ");
        int idxEst = Integer.parseInt(sc.nextLine());

        // Mostrar empresas
        System.out.println("\n--- EMPRESAS ALIADAS ---");
        for (int i = 0; i < empresas.size(); i++) {
            System.out.println(i + ". " + empresas.get(i).mostrarInfo());
        }

        System.out.print("Seleccione empresa: ");
        int idxEmp = Integer.parseInt(sc.nextLine());

        estudiantes.get(idxEst).asignarEmpresa(empresas.get(idxEmp).mostrarInfo());

        System.out.println("\n✓ Práctica asignada correctamente.\n");
    }

    // ---------------------------------------------------------
    // LISTAR ESTUDIANTES
    // ---------------------------------------------------------
    public void listarEstudiantes() {
        System.out.println("\n--- LISTA DE ESTUDIANTES ---");
        for (Estudiante e : estudiantes) {
            System.out.println(e.mostrarInfo());
        }
        System.out.println();
    }

    // ---------------------------------------------------------
    // MENÚ PRINCIPAL
    // ---------------------------------------------------------
    public void menu() {
        while (true) {

            System.out.println("\n--- SISTEMA DE PRÁCTICAS UNIMINUTO ---");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Registrar empresa aliada");
            System.out.println("3. Asignar práctica");
            System.out.println("4. Listar estudiantes");
            System.out.println("5. Salir");

            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    registrarEstudiante();
                    break;

                case "2":
                    registrarEmpresa();
                    break;

                case "3":
                    asignarPractica();
                    break;

                case "4":
                    listarEstudiantes();
                    break;

                case "5":
                    System.out.println("Saliendo...");
                    return;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    // ---------------------------------------------------------
    // MÉTODO MAIN – Punto de inicio del programa
    // ---------------------------------------------------------
    public static void main(String[] args) {
        SistemaPracticas sistema = new SistemaPracticas();
        sistema.menu();
    }
}
