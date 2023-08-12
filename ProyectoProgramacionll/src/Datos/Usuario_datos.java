
package Datos;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Usuario_datos {
    
    private BufferedReader bleer;
    private BufferedWriter bescribir;
    private String fichero = "USUARIOS.txt";

    public Usuario_datos() throws IOException {

    }
    
    //funcion para buscar usuario en el archivo
    public int BuscarUsuario(String Login_usuario, String Pass_Usuario)throws IOException{
        
       int  r = -1;
       
        try {
            //Se abre el archivo en modo de lectura:
            bleer = new BufferedReader(new FileReader(fichero));

            //Se crea un ciclo que leer el archivo línea por línea:
            String linea = bleer.readLine();
            while (linea != null) {
                String[] datos = linea.split(";");//Se separan los datos

                //Si el dato #0 (login) es igual al login pasado como parámetro:
                if (datos[0].equals(Login_usuario)) {

                    //Si el dato #1 (password) es igual al pass pasado como parámetro:
                    if (datos[1].equals(Pass_Usuario)) {
                        r = 1;//Retorna 1.
                    } else {//Si no:
                        r = 0;//Retorna 0.
                    }
                    break;//Rompe el ciclo.
                }
                linea = bleer.readLine();//Lee la siguiente línea.
            }

            //Si no se encontró el usuario:
            if (linea == null) {
                r = -1;//Retorna -1.
            }

        } catch (FileNotFoundException e) {
            bescribir = new BufferedWriter(new FileWriter(fichero));
            bescribir.close();
            r = -1;
        } finally {
            bleer.close();
        }
        return r;//Retorna la respuesta.
    }
    
    
    //Funcion para guardar el usuario
    public boolean Guardar_Usuario(Usuario usuario)throws IOException{
    
    boolean r = false;
    
    if(BuscarUsuario(usuario.getLogin_usuario(),"")== -1){
        
        try {
                //Se abre el archivo en modo de lectura:
                bescribir = new BufferedWriter(new FileWriter(fichero,true));

                //Se escriben los datos:
                bescribir.write(usuario.toString()+"\r\n");

                //Se limpia el buffer:
                bescribir.flush();

                //Se asigna true a la variable de retorno:
                r = true;
            } catch (IOException e) {
                //Se imprime el mensaje de error:
                System.err.println("Error al guardar al usuario:\n" + e.getMessage());

                //Se asigna false a la variable de retorno:
                r = false;
            } finally {
                //Se cierra el archivo:
                bescribir.close();
            }
        } else {
            //Si existe el usuario en el registro, se retorna false:
            JOptionPane.showMessageDialog(null, "El usuario ya existe.");
            r = false;
    
    }

    return r;
    }
    
    
    //funcion para obtener los datos del usuario mediante login y contraseña
    public Usuario getUsuario(String Login_usuario)throws IOException {
    
        Usuario usuario = null;
        
        try {
            //Se abre el archivo en modo de lectura:
            bleer = new BufferedReader(new FileReader(fichero));

            //Se crea un ciclo que lee el archivo línea por línea:
            String line = bleer.readLine();
            while (line != null) {

                //Se almacenan los datos en un vector de Strings:
                String[] datos = line.split(";");

                //Si los datos coinciden:
                if (datos[0].equals(Login_usuario)) {

                    //Se asignan los datos al objeto:
                    usuario = new Usuario();
                    usuario.setLogin_usuario(datos[0]);
                    usuario.setPass_Usuario(datos[1]);
                    usuario.setNombre_Usuario(datos[2]);
                    usuario.setApellidos_Usuario(datos[3]);
                    usuario.setEmail_Usuario(datos[4]);
                    usuario.setNivel_Acceso(Integer.parseInt(datos[5]));

                    //Se rompe el ciclo while:
                    break;
                }

                //Se lee la siguiente línea:
                line = bleer.readLine();
            }
        } catch (IOException e) {
            //Se imprime un mensaje de error:
            System.err.println("Error al obtener el usuario:\n" + e.getMessage());
        } finally {
            //Se cierra el archivo.
            bleer.close();
        }
        //Se retorna el objeto:
        return usuario;
    }

    public ArrayList<Usuario> getAllUsers() throws IOException {
        //Función para retorna una lista con todos los usuarios:
        ArrayList<Usuario> list = new ArrayList();

        try {
            bleer = new BufferedReader(new FileReader(fichero));

            String line = bleer.readLine();
            while (line != null) {
                String[] datos = line.split(";");

                Usuario user = new Usuario();
                user.setLogin_usuario(datos[0]);
                user.setPass_Usuario("");
                user.setNombre_Usuario(datos[2]);
                user.setApellidos_Usuario(datos[3]);
                user.setEmail_Usuario(datos[4]);
                user.setNivel_Acceso(Integer.parseInt(datos[5]));
                list.add(user);

                line = bleer.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error al obtener los usuarios:\n" + e.getMessage());
        }
        return list;
    }
    
     public boolean modifyUser(Usuario usuario) throws IOException {
       
    boolean r = false;//Variable de retorno.
        try {
            //Si el usuario existe:
            if (BuscarUsuario(usuario.getLogin_usuario(), "") == 0) {
                //Se abre el archivo en modo de lectura:
                bleer = new BufferedReader(new FileReader(fichero));

                //Variable que almacena los datos del archivo:
                String datos_a = "";

                //Variable que almacena la línea leída:
                String line = bleer.readLine();
                while (line != null) {
                    //Ciclo que recorre el archivo.

                    //Se guardan los datos de la línea en un vector de String:
                    String datos[] = line.split(";");

                    //Si el dato #0 del vector (login) es diferente al del que se quiere cambiar:
                    if (!datos[0].equals(usuario.getLogin_usuario())) {
                        //Se guarda la línea en el String de datos:
                        datos_a += line+"\r\n";
                    } else {
                        //Si el dato #0 del vector (login) es igual al del que se quiere cambiar:

                        //Se almacena en el String de datos el usuario con los datos modificados:
                        datos_a += usuario.toString() + "\r\n";
                    }
                    //Se lee la siguiente línea:
                    line = bleer.readLine();
                }

                //Se abre el archivo en modo de escritura:
                //(Al hacer esto se borran todos los datos del archivo)
                bescribir = new BufferedWriter(new FileWriter(fichero));

                //Se escriben los datos del String de datos:
                bescribir.write(datos_a);

                //Se guardan en el archivo
                bescribir.flush();

                //La variable de retorno es igual a true:
                r = true;
            } else {
                //Si el usuario no existe:

                //La variable de retorno será igual a falso:
                r = false;
            }
        } catch (Exception ex) {
            //Se capturan los posibles errores:
            System.err.println("Error al modificar el registro:\n" + ex.getMessage());

            //La variable de retorno será igual a falso:
            r = false;
        } finally {
            //Se cierra los objetos de lectura y escritura:
            bleer.close();
            bescribir.close();
        }

        //Se retorna la variable:
        return r;
    }
     
}
