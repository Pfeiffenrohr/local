/*************************************************************************/
/*               Systemaufruf                                            */
/*       Author    : Richard Lechner                                     */
/*       company   : Cenit AG Systemhaus                                 */
/*       Datum     : 22.05.2003                                          */
/*************************************************************************/
package common;
import java.io.*;

/** Systemaufruf
 * Damit kann man Systembefehle ausführen
 * @author    : Richard Lechner
 * @version 1.0
 */
public class SystemExecute {
    /**
     * Beinhaltet die Standardausgabe
     */
  public static String returnstring;
    /**
     * Beinhaltet das Ergebnis des  Errorkanals
     */
  public static String errorstring;
    /**
     * Returncode
     */
  public static int rc;
  static DataInputStream is;
    /**
     * Aufruf eines Unix Systembefehls. Der Befehl wird in 
     * einer Kornshell susgeführt.
     * Beispiel: syscall("xterm") Aufruf: ksh -c xterm
     * Die zurückgegebenen Parameter stehen in returnstring
     * bzw errorstring
     * veraltet. bitte syscall_win oder syscall_unix verwenden
     * @param args Der Systemaufruf als String
     * @deprecated replaced by syscall_unix
     */
  public static void  syscall(String  args)  {
       String [] startcmd = new String[3];
       startcmd[0] = "ksh";
       startcmd[1] = "-c";
       startcmd[2] = args;
        try {
          Process p = Runtime.getRuntime().exec(startcmd);
                   int ch;
                if ( p == null ) {
                        System.err.println("starting process failed");
                        System.exit(1);
                }
                try {
                  p.waitFor();
               }
               catch (InterruptedException ie) {}
                is = new DataInputStream(p.getInputStream());
          StringBuffer stb= new StringBuffer();
                while (-1 != (ch = is.read())){
                     stb.append((char)ch);
                       }
                   if ((stb.length()>0)&&(stb.charAt(stb.length()-1))=='\n'
)
                       stb.setLength(stb.length()-1);
                   returnstring=stb.toString();
                   stb.setLength(0);
                   is = new DataInputStream(p.getErrorStream());
                  while (-1 != (ch = is.read())){
                    stb.append((char)ch);
                       }
                    errorstring=stb.toString();
              rc = p.exitValue();
               }
             catch (IOException ioe) {  System.out.println(ioe);}
     }
 /**
     * Aufruf eines Windows Systembefehls. Der Befehl wird in 
     * direkt ausgeführt ohne eine neue shell
     * Die zurückgegebenen Parameter stehen in returnstring
     * bzw errorstring
     * @param args Der Systemaufruf als String
     */
  public static void  syscall_win(String  args)  {
        try {
          Process p = Runtime.getRuntime().exec(args);
                   int ch;
                if ( p == null ) {
                        System.err.println("starting process failed");
                        //System.exit(1);
                }
                try {
                  p.waitFor();
               }
               catch (InterruptedException ie) {}
                is = new DataInputStream(p.getInputStream());
          StringBuffer stb= new StringBuffer();
                while (-1 != (ch = is.read())){
                     stb.append((char)ch);
                       }
                   if ((stb.length()>0)&&(stb.charAt(stb.length()-1))=='\n'
)
                       stb.setLength(stb.length()-1);
                   returnstring=stb.toString();
                   stb.setLength(0);
                   is = new DataInputStream(p.getErrorStream());
                  while (-1 != (ch = is.read())){
                    stb.append((char)ch);
                       }
                    errorstring=stb.toString();
              rc = p.exitValue();
              is.close();
               }
             catch (IOException ioe) {  System.out.println(ioe);}
     }
 
   /**
     * Aufruf eines Unix Systembefehls. Der Befehl wird in 
     * einer Kornshell susgeführt.
     * Beispiel: syscall("xterm") Aufruf: ksh -c xterm
     * Die zurückgegebenen Parameter stehen in returnstring
     * bzw errorstring
     * @param args Der Systemaufruf als String
     */
  public static void  syscall_unix(String  args)  {
       String [] startcmd = new String[3];
       startcmd[0] = "bash";
       startcmd[1] = "-c";
       startcmd[2] = args;
        try {
          Process p = Runtime.getRuntime().exec(startcmd);
                   int ch;
                if ( p == null ) {
                        System.err.println("starting process failed");
                        //System.exit(1);
                }
                try {
                  p.waitFor();
               }
               catch (InterruptedException ie) {}
                is = new DataInputStream(p.getInputStream());
          StringBuffer stb= new StringBuffer();
                while (-1 != (ch = is.read())){
                     stb.append((char)ch);
                       }
                   if ((stb.length()>0)&&(stb.charAt(stb.length()-1))=='\n'
)
                       stb.setLength(stb.length()-1);
                   returnstring=stb.toString();
                   stb.setLength(0);
                   is.close();
                   is = new DataInputStream(p.getErrorStream());
                  while (-1 != (ch = is.read())){
                    stb.append((char)ch);
                       }
                    errorstring=stb.toString();
              rc = p.exitValue();
              is.close();
              OutputStream os = p.getOutputStream();
              os.close();
              p.destroy();
               }
             catch (IOException ioe) {  System.out.println(ioe);}
       
     }
}
