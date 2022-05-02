import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

public class App
{
    BufferedImage imagen = null;
    String path = "C://Users//Oscar//Desktop//ESCOM//10mo//Analisis de Algoritmos//Analisis-de-Algoritmos//Practica4/";
    int tamaño = 1023;  // Asiganda como 1024-1 para permanecer dentro de los límites durante los cálculos

    public boolean read()
    {
        try 
        {
            File f_imagen = new File(path + "/imagen.bmp"); // Obtiene el archivo indicado en path
            imagen = ImageIO.read(f_imagen);    // Abre el archivo en formato BufferedImage
            System.out.println(">: Se ha leído correctamente la imagen.");  // Confirmación
            return true;
        } 
        catch (Exception e) 
        {
            System.out.println(">: Ocurrió un error: " + e);
            return false;
        }
    }

    public void copiar()
    {
        try 
        {
            ImageIO.write(imagen, "bmp", new File(path + "/copia.bmp"));    // Crea un archivo con la imagen BufferedImage abierta anteriormente y le cambia el nombre.
            System.out.println(">: Se ha escrito correctamente la imagen.");    
        } 
        catch (Exception e) {
            System.out.println(">: Ocurrió un error: " + e);
        }
    }

    // convert BufferedImage to byte[]
    public static byte[] toByteArray(BufferedImage bi, String format) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }

    // convert byte[] to BufferedImage
    public static BufferedImage toBufferedImage(byte[] bytes)
        throws IOException {

        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(is);
        return bi;

    }

    public void rotar()
    {
        try
        {
            BufferedImage nueva = imagen;

            for (int h = 0; h < imagen.getHeight(); h++)
            {
                for (int w = 0; w < imagen.getWidth(); w++)
                {
                    int rgb = imagen.getRGB(w, h);
                    nueva.setRGB(h, tamaño-w, rgb);     // Asigna el valor de imagen[w][h] a nueva[h][1023-w]
                }
            }

            ImageIO.write(nueva, "bmp", new File(path + "/mod.bmp"));   // Guarda la imagen editada en un archivo nuevo.
        }
        catch(Exception e)
        {
            System.out.println(">: Ocurrió un error(pintar): " + e);
        }
    }

    public static void main(String[] args)
    {
        App usr = new App();
        if(usr.read())
        {
            usr.rotar();
        }
    }
}