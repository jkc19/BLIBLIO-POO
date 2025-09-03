import java.util.Scanner;

public class Biblioteca {

    private AlumnoDAO alumnoDAO;
    private DocenteDAO docenteDAO;
    private DispositivoDAO dispositivoDAO;
    private static MultimediaDAO multimediaDAO;

    private static Administrador valerio = new Administrador(1, "Franco Valerio", "admin1@gmail.com", "admin123", false, "Administrador Supremo");

    public static void subirActualizaciones() {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        DocenteDAO docenteDAO = new DocenteDAO();
        DispositivoDAO dispositivoDAO = new DispositivoDAO();
        SalaEstudioDAO salaDAO = new SalaEstudioDAO();

        // Guardar usuarios
        for (Usuario u : valerio.getUsuariosRegistrados()) {
            if (u instanceof Alumno) {
                alumnoDAO.insertarAlumno((Alumno) u);
            } else if (u instanceof Docente) {
                docenteDAO.insertarDocente((Docente) u);
            }
        }

        // Guardar dispositivos
        for (Dispositivo d : Dispositivo.listaDispositivos) {
            dispositivoDAO.guardarOActualizar(d);
        }

        // Guardar salas
        for (SalaEstudio s : SalaEstudio.espacios) {
            salaDAO.insertar(s);
        }

        // Guardar multimedia
        for (Multimedia m : Multimedia.listaRecursos) {
            multimediaDAO.guardarTodosLosRecursos(Multimedia.listaRecursos);
        }

        System.out.println("Datos actualizados en la base de datos.");
    }


    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);

        System.out.println("Ingrese su contraseña si es Admin o si ingresa como usuario (docente/alumno)");
        String respuesta= leer.nextLine();
        if(respuesta.equalsIgnoreCase("admin123")){
            valerio.gestionarUsuario();
            valerio.gestionarDispositivosSalaEstudio();
            valerio.gestionarMultimedia();
        }else if(respuesta.equalsIgnoreCase("Docente")){
            Docente d1 = new Docente(1, "Juan Carlos", "JuanCarlos@gmail.com", "DocenteJuanCarlos", false, "Base de Datos");
            d1.menuDocente();
        }else if (respuesta.equalsIgnoreCase("Alumno")){
            Alumno a1 = new Alumno(1, "Piero Garcia", "PieroGarcia@gmail.com", "AlumnoPiero", false, "Ing. Sistemas e Informatica", 5);
            a1.menuAlumno();
        }else {
            System.out.println("Respuesta invalida, saliendo del sistema");
        }

        subirActualizaciones();

    }
}
