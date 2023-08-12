
package Datos;

public class Usuario {
    private String Login_usuario, Pass_Usuario,Nombre_Usuario,Apellidos_Usuario,Email_Usuario;
    private int Nivel_Acceso;

    public Usuario() {
    }

    public Usuario(String Login_usuario, String Pass_Usuario, String Nombre_Usuario, String Apellidos_Usuario, String Email_Usuario, int Nivel_Acceso) {
        this.Login_usuario = Login_usuario;
        this.Pass_Usuario = Pass_Usuario;
        this.Nombre_Usuario = Nombre_Usuario;
        this.Apellidos_Usuario = Apellidos_Usuario;
        this.Email_Usuario = Email_Usuario;
        this.Nivel_Acceso = Nivel_Acceso;
    }

    public String getLogin_usuario() {
        return Login_usuario;
    }

    public String getPass_Usuario() {
        return Pass_Usuario;
    }

    public String getNombre_Usuario() {
        return Nombre_Usuario;
    }

    public String getApellidos_Usuario() {
        return Apellidos_Usuario;
    }

    public String getEmail_Usuario() {
        return Email_Usuario;
    }

    public int getNivel_Acceso() {
        return Nivel_Acceso;
    }

    public void setLogin_usuario(String Login_usuario) {
        this.Login_usuario = Login_usuario;
    }

    public void setPass_Usuario(String Pass_Usuario) {
        this.Pass_Usuario = Pass_Usuario;
    }

    public void setNombre_Usuario(String Nombre_Usuario) {
        this.Nombre_Usuario = Nombre_Usuario;
    }

    public void setApellidos_Usuario(String Apellidos_Usuario) {
        this.Apellidos_Usuario = Apellidos_Usuario;
    }

    public void setEmail_Usuario(String Email_Usuario) {
        this.Email_Usuario = Email_Usuario;
    }

    public void setNivel_Acceso(int Nivel_Acceso) {
        this.Nivel_Acceso = Nivel_Acceso;
    }

    @Override
    public String toString() {
        return "Usuario{" + "Login_usuario=" + Login_usuario + ", Pass_Usuario=" + Pass_Usuario + ", Nombre_Usuario=" + Nombre_Usuario + ", Apellidos_Usuario=" + Apellidos_Usuario + ", Email_Usuario=" + Email_Usuario + ", Nivel_Acceso=" + Nivel_Acceso + '}';
    }
    
    
}