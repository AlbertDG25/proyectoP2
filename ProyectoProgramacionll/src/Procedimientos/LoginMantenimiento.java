
package Procedimientos;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Datos.Usuario;
import Datos.Usuario_datos;
import Ventamas.MenuUsuario;
import Ventamas.VentanaLoginUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class LoginMantenimiento implements KeyListener, ActionListener  {
   
    /*
        Esta clase se utiliza para controlar la ventana de Login y pasarle los
        datos al UserDao.
     */
    //Se usa para tomar controlar la ventana de Login:
    private VentanaLoginUsuario Login;

    //Almacena el login (username) ingresado por el usuario:
    private String str_login;

    //Almacena la contraseña ingresada por el usuario:
    private String password;

    //Objeto que almacena el usuario que inicia sesión:
    public static Usuario usuario;

    //Objeto para interacturar con el archivo:
    private Usuario_datos u_d;

    public LoginMantenimiento(VentanaLoginUsuario login) throws IOException {
        this.Login = login;

        //Se ponen a la escucha los objetos que tendrán eventos:
        this.Login.txt_login.addKeyListener(this);
        this.Login.txt_password.addKeyListener(this);
        this.Login.b_ingresar.addActionListener(this);

        //Se cambia el ícono del txt_passwords:
        //this.Login.txt_password.setEchoChar('*');

        u_d = new Usuario_datos();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Si se preciona la tecla Enter:
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            //Si el foco está en el txt_login:
            if (this.Login.txt_login.isFocusOwner()) {

                //Se pasa el foco al txt_password:
                this.Login.txt_password.requestFocusInWindow();

            } else if (this.Login.txt_password.isFocusOwner()) {

                try {
                    //Si el foco está en el txt_password:
                    ingresar();
                } catch (IOException ex) {
                    Logger.getLogger(LoginMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Si se preciona el boton de ingresar:
        if (e.getSource() == this.Login.b_ingresar) {
            try {
                ingresar();
            } catch (IOException ex) {
                Logger.getLogger(LoginMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getStr_login() {
        //Se retorna el login o Nombre_Usuario:
        return str_login;
    }

    public String getPassword() {
        //Se retorna la contraseña:
        return password;
    }

    public void ingresar() throws IOException {
        /*
            Esta función es la encargada de llamar a la función de comprobar campos
            y de llamar a la función que verifica si el usuario y la contraseña son correctas
            (no programada aún).
         */

        if (comprobarCampos()) {//Se llama a la función de comprobar datos:
            int r = u_d.BuscarUsuario(str_login, password);

            switch (r) {
                case -1:
                    JOptionPane.showMessageDialog(null, "El usuario no existe.");
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
                    break;
                case 1:
                    usuario = u_d.getUsuario(str_login);
                    new MenuUsuario().setVisible(true);
                    this.Login.setVisible(false);
                    break;
                default:
                    break;
            }
        }
    }

    public boolean comprobarCampos() {
        //Función que se encarga de comprar los campos de texto.

        if (this.Login.txt_login.getText().trim().equals("")
                || this.Login.txt_password.getText().trim().equals("")) {
            //Si los campos están vacíos:

            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {//Si no lo están:
            str_login = this.Login.txt_login.getText().trim();
            password = this.Login.txt_password.getText().trim();
            return true;
        }
    }
}
 

