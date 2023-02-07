import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Implementa la interfaz Nave.
 * @author Omar Alejandro Izquierdo Berrio
 */
public class Metodos implements Nave {
// Conexión a la base de datos.
    Connection conn = DriverManager.getConnection("jdbc:postgresql://150.136.224.179:5432/admin_sofkau", "admin_mau5",
            "Je6NU5ov2gYZNr");
    private int IDNave;
    private int TipoNave;
    private String NombreNave;
    private double EmpujeNave;
    private double PesoNave;
    private String TipoCombustible;

    // Creando un constructor para la clase Metodos.
    public Metodos(int IDNave, int TipoNave, String NombreNave, double EmpujeNave, double PesoNave,
            String TipoCombustible) throws SQLException {
        this.IDNave = IDNave;
        this.TipoNave = TipoNave;
        this.NombreNave = NombreNave;
        this.EmpujeNave = EmpujeNave;
        this.PesoNave = PesoNave;
        this.TipoCombustible = TipoCombustible;
    }

    // Getters y Setters.
    public int getIDNave() {
        return IDNave;
    }

    public void setIDNave(int idnave) {
        IDNave = idnave;
    }

    public int getTipoNave() {
        return TipoNave;
    }

    public void setTipoNave(int tipoNave) {
        TipoNave = tipoNave;
    }

    public String getNombreNave() {
        return NombreNave;
    }

    public void setNombreNave(String nombreNave) {
        NombreNave = nombreNave;
    }

    public double getEmpujeNave() {
        return EmpujeNave;
    }

    public void setEmpujeNave(double empujeNave) {
        EmpujeNave = empujeNave;
    }

    public double getPesoNave() {
        return PesoNave;
    }

    public void setPesoNave(double pesoNave) {
        PesoNave = pesoNave;
    }

    public String getTipoCombustible() {
        return TipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        TipoCombustible = tipoCombustible;
    }

    /**
     Si el sistema operativo es Windows, ejecuta el comando "cmd /c cls" para borrar la consola. Si el sistema operativo es Linux, ejecuta el comando "clear" para borrar la consola
     */
    public void LimpiarConsola() {
        String so = System.getProperty("os.name");
        if (so.contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (so.contains("Linux")) {
            try {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * "Si la consulta devuelve un resultado, devuelve verdadero, de lo contrario, devuelve falso".
     * Este método se usa para validar si un ID de nave existe en la base de datos.
     * 
     * @param IDNave La identificación de la nave.
     * @return Un valor booleano.
     */
    public boolean ValidarNave(int IDNave) throws SQLException {
        if (conn.createStatement().executeQuery("SELECT * FROM public.naves WHERE id_nave = " + IDNave).next()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Toma 6 parámetros para crear una nave, si el ID de la nave ya existe, devuelve falso, de lo contrario, crea la nave y devuelve verdadero.
     * 
     * @param IDNave Entero
     * @param TipoNave 1 = Lanzadera, 2 = No Tripulada, 3 = Tripulada
     * @param NombreNave Cadena
     * @param EmpujeNave Cadena
     * @param PesoNave Cadena
     * @param TipoCombustible Cadena
     * @return true si la nave se crea correctamente, de lo contrario, devuelve false.
     */
    public boolean CrearNave(int IDNave, int TipoNave, String NombreNave, double EmpujeNave, double PesoNave,
            String TipoCombustible) throws SQLException {
        if (ValidarNave(IDNave)) {
            return false;
        } else {
            String SQL = "INSERT INTO public.naves(id_nave, tipo, nombre, empuje, peso, combustible) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setInt(1, IDNave);
            statement.setInt(2, TipoNave);
            statement.setString(3, NombreNave);
            statement.setDouble(4, EmpujeNave);
            statement.setDouble(5, PesoNave);
            statement.setString(6, TipoCombustible);
            statement.executeUpdate();
            return true;
        }

    }

    /**
     * Toma un ID como argumento, comprueba si el ID existe en la base de datos y, si existe
     * devuelve una cadena con la información de la nave, de lo contrario, devuelve una cadena con el mensaje "No se encontró la nave".
     * 
     * @param IDNave La identificación de la nave.
     * @return La información de la nave.
     */
    public String MostrarNave(int IDNave) throws SQLException {
        String resultado = "";
        if (ValidarNave(IDNave)) {
            String sql = "SELECT * FROM public.naves WHERE id_nave = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, IDNave);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id_nave");
                String tipo = resultSet.getString("tipo");
                String nombre = resultSet.getString("nombre");
                double empuje = resultSet.getDouble("empuje");
                double peso = resultSet.getDouble("peso");
                String combustible = resultSet.getString("combustible");
                if (tipo.equals("1")) {
                    tipo = "Lanzadera";
                } else if (tipo.equals("2")) {
                    tipo = "No Tripulada";
                } else if (tipo.equals("3")) {
                    tipo = "Tripulada";
                }
                resultado = "ID: " + id + "\nTipo: " + tipo + "\nNombre: " + nombre + "\nEmpuje: " + empuje + "\nPeso: " + peso + "\nCombustible: " + combustible;
            }
        } else {        
            resultado = "No existe la nave";
        }
        return resultado;
    }

    /**
     * Toma un nombre de columna y un valor, y devuelve una cadena con las filas que coinciden con el
     * valor de la columna
     * 
     * @param Caracteristica El nombre de la columna
     * @param Valor El valor a filtrar
     * @return Un String con la información de la tabla filtrada.
     */
    public String FiltrarNaves(String Caracteristica, String Valor) throws SQLException {
        String resultado = "";
        String sql = "SELECT * FROM public.naves WHERE " + Caracteristica + " = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        if (Valor.equals("1") || Valor.equals("2") || Valor.equals("3")) {
            statement.setInt(1, Integer.parseInt(Valor));
        } else if (Valor.matches("^\\d+$")){
            statement.setDouble(1, Double.parseDouble(Valor));
        } else {
            statement.setString(1, Valor);
        }
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id_nave");
            String tipo = resultSet.getString("tipo");
            String nombre = resultSet.getString("nombre");
            double empuje = resultSet.getDouble("empuje");
            double peso = resultSet.getDouble("peso");
            String combustible = resultSet.getString("combustible");
            if (tipo.equals("1")) {
                tipo = "Lanzadera";
            } else if (tipo.equals("2")) {
                tipo = "No Tripulada";
            } else if (tipo.equals("3")) {
                tipo = "Tripulada";
            }
            resultado += "\nID: " + id + "\nTipo: " + tipo + "\nNombre: " + nombre + "\nEmpuje: " + empuje + "\nPeso: " + peso + "\nCombustible: " + combustible + "\n";
        }
        return resultado;
    }
                        

    /**
     * Actualiza una fila en una tabla de la base de datos, toma 6 parámetros, el ID de la nave, el tipo de nave, el nombre de la nave, el empuje, el peso y el tipo de combustible, si el ID de la nave no existe, devuelve falso, de lo contrario, actualiza la nave y devuelve verdadero.
     * 
     * @param IDNave La identificación de la nave.
     * @param TipoNave 1 = Lanzadera, 2 = No Tripulada, 3 = Tripulada
     * @param NombreNave nombre del barco
     * @param EmpujeNave Empuje
     * @param PesoNave Cadena
     * @param TipoCombustible Cadena
     * @return true si la nave fue actualizada, false si no.
     */
    public boolean ActualizarNave(int IDNave, int TipoNave, String NombreNave, double EmpujeNave, double PesoNave,
            String TipoCombustible) throws SQLException {
        if (ValidarNave(IDNave)) {
            String SQL = "UPDATE public.naves SET tipo = ?, nombre = ?, empuje = ?, peso = ?, combustible = ? WHERE id_nave = ?";
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setInt(1, TipoNave);
            statement.setString(2, NombreNave);
            statement.setDouble(3, EmpujeNave);
            statement.setDouble(4, PesoNave);
            statement.setString(5, TipoCombustible);
            statement.setInt(6, IDNave);
            statement.executeUpdate();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina una fila de la tabla "naves" de la base de datos, toma un ID como argumento, si el ID no existe, devuelve falso, de lo contrario, elimina la nave y devuelve verdadero.
     * 
     * @param IDNave El ID de la nave que se va a eliminar.
     * @return true si la nave fue eliminada, false si no.
     */
    public boolean EliminarNave(int IDNave) throws SQLException {
        if (ValidarNave(IDNave)) {
            String SQL = "DELETE FROM public.naves WHERE id_nave = ?";
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setInt(1, IDNave);
            statement.executeUpdate();
            return true;
        } else {
            return false;
        }
    }
    /**
     * Muestra el estado de una nave, toma un ID como argumento, si este ID no existe, devuelve falso, de lo contrario, devuelve verdadero.
     * @param IDNave El ID de la nave.
     * @return true si la nave existe y tiene estado, false si no.
     */
    public boolean ValidarEstado(int IDNave) throws SQLException {
        if (ValidarNave(IDNave)) {
            conn.createStatement().executeQuery("SELECT * FROM public.estado_naves WHERE id = " + IDNave).next();
            return true;
        } else {
            return false;
        }
    }

    @Override
    /** Este método se encarga de despegar una nave, toma un ID como argumento, si el ID no existe, devuelve una cadena con el mensaje "No existe la nave", de lo contrario despega la nave y devuelve una cadena con el mensaje "La nave " + nombreNave + " con ID " + ID + " ha despegado".
     * @param ID El ID de la nave.
    */
    public void Despegar(int ID) {
        try {
            if (ValidarNave(ID)) {
                if (ValidarEstado(ID)) {
                    String SQL = "INSERT INTO public.estado_naves (id, estado) VALUES (?, ?)";
                    PreparedStatement statement = conn.prepareStatement(SQL);
                    statement.setInt(1, ID);
                    statement.setString(2, "Despegado");
                    statement.executeUpdate();
                    System.out.println("La nave con ID " + ID + " ha despegado");
                } else {
                    String SQL = "UPDATE public.estado_naves SET estado = ? WHERE id = ?";
                    PreparedStatement statement = conn.prepareStatement(SQL);
                    statement.setString(1, "Despegado");
                    statement.setInt(2, ID);
                    statement.executeUpdate();
                    System.out.println("La nave con ID " + ID + " ha despegado");
                }
            } else {
                System.out.println("No existe la nave");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    /** Este método se encarga de acelerar una nave, toma un ID como argumento, si el ID no existe, devuelve una cadena con el mensaje "No existe la nave", de lo contrario acelera la nave y devuelve una cadena con el mensaje "La nave " + nombreNave + " con ID " + ID + " ha acelerado".
     * @param ID El ID de la nave.
     */
    public void Acelerar(int ID) {
        try {
            if (ValidarNave(ID)) {
                if (ValidarEstado(ID)) {
                    String SQL = "INSERT INTO public.estado_naves (id, estado) VALUES (?, ?)";
                    PreparedStatement statement = conn.prepareStatement(SQL);
                    statement.setInt(1, ID);
                    statement.setString(2, "Acelerado");
                    statement.executeUpdate();
                    System.out.println("La nave con ID " + ID + " ha acelerado");
                } else {
                    String SQL = "UPDATE public.estado_naves SET estado = ? WHERE id = ?";
                    PreparedStatement statement = conn.prepareStatement(SQL);
                    statement.setString(1, "Acelerado");
                    statement.setInt(2, ID);
                    statement.executeUpdate();
                    System.out.println("La nave con ID " + ID + " ha acelerado");
                }
            } else {
                System.out.println("No existe la nave");
            }
        } catch (SQLException e) {
            System.out.println(e);
    }
}

    @Override
    /** Este método se encarga de aterrizar una nave, toma un ID como argumento, si el ID no existe, devuelve una cadena con el mensaje "No existe la nave", de lo contrario aterriza la nave y devuelve una cadena con el mensaje "La nave " + nombreNave + " con ID " + ID + " ha aterrizado".
     * @param ID El ID de la nave.
    */
    public void Aterrizar(int ID) {
        try {
            if (ValidarNave(ID)) {
                if (ValidarEstado(ID)) {
                    String SQL = "INSERT INTO public.estado_naves (id, estado) VALUES (?, ?)";
                    PreparedStatement statement = conn.prepareStatement(SQL);
                    statement.setInt(1, ID);
                    statement.setString(2, "Aterrizado");
                    statement.executeUpdate();
                    System.out.println("La nave con ID " + ID + " ha aterrizado");
                } else {
                    String SQL = "UPDATE public.estado_naves SET estado = ? WHERE id = ?";
                    PreparedStatement statement = conn.prepareStatement(SQL);
                    statement.setString(1, "Aterrizado");
                    statement.setInt(2, ID);
                    statement.executeUpdate();
                    System.out.println("La nave con ID " + ID + " ha aterrizado");
                }
            } else {
                System.out.println("No existe la nave");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    /**  Este método se encarga de mostrar el estado de una nave, toma un ID como argumento, si el ID no existe, devuelve una cadena con el mensaje "No existe la nave", de lo contrario devuelve una cadena con el mensaje "La nave " + nombreNave + " con ID " + ID + " esta " + estado.
     * @param ID El ID de la nave.
     */
    public void Estado(int ID) {
        try {
            if (ValidarNave(ID)) {
                if (ValidarEstado(ID)) {
                    String SQL = "SELECT estado FROM public.estado_naves WHERE id = ?";
                    PreparedStatement statement = conn.prepareStatement(SQL);
                    statement.setInt(1, ID);
                    ResultSet resultSet = statement.executeQuery();
                    resultSet.next();
                    String estado = resultSet.getString("estado").toLowerCase();
                    System.out.println("La nave con ID " + ID + " esta " + estado);
                }
            } else {
                System.out.println("No existe la nave");
            }
        } catch (SQLException e) {
            System.out.println("La nave con ID " + ID + " esta en el hangar");
        }
    }
}
