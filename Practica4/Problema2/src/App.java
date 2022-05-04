import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

public class App
{
    BufferedImage imagen = null;
    String path = "C://Users//Oscar//Desktop//ESCOM//10mo//Analisis de Algoritmos//Analisis-de-Algoritmos//Practica4/";
    int tamaño = 1024;

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
            File f_imagen = new File(path + "/copia.bmp"); // Obtiene el archivo indicado en path
            BufferedImage nueva = ImageIO.read(f_imagen);    // Abre el archivo en formato BufferedImage

            for (int h = 0; h < imagen.getHeight(); h++)
            {
                for (int w = 0; w < imagen.getWidth(); w++)
                {
                    int rgb = imagen.getRGB(h, w);
                    nueva.setRGB(w, tamaño - h, rgb);     // Asigna el valor de imagen[w][h] a nueva[h][1023-w]
                    //nueva.setRGB(w, tamaño-h, rgb);     // Asigna el valor de imagen[w][h] a nueva[h][1023-w]
                }
            }

            ImageIO.write(nueva, "bmp", new File(path + "/copia.bmp"));   // Guarda la imagen editada en un archivo nuevo.
        }
        catch(Exception e)
        {
            System.out.println(">: Ocurrió un error(pintar): " + e);
        }
    }
    
    public BufferedImage rotarDyV(BufferedImage img, int a, int b, int c, int d)
    {
        BufferedImage nueva = null;
        try 
        {
            File f_imagen = new File(path + "/copia.bmp"); // Obtiene el archivo indicado en path
            nueva = ImageIO.read(f_imagen);    // Abre el archivo en formato BufferedImage

            if(b - a == 2)   // Si ya es matriz 2x2
            {
                int rgb = img.getRGB(0, 0); // a
                nueva.setRGB(0, 0, rgb);

                rgb = img.getRGB(0, 1);     // b
                nueva.setRGB(0, 1, rgb);

                rgb = img.getRGB(1, 0);     // c
                nueva.setRGB(1, 0, rgb);

                rgb = img.getRGB(1, 1);     // d
                nueva.setRGB(1, 1, rgb);

                return nueva;
            }

            BufferedImage A = rotarDyV(img, 0, b/2, c/2, d/2); // Cuadrante Superior Izquierdo
            BufferedImage B = rotarDyV(img, a/2, 0, c/2, d/2); // Cuadrante Superior Derecho
            BufferedImage C = rotarDyV(img, a/2, b/2, 0, d/2); // Cuadrante Inferior Izquierdo
            BufferedImage D = rotarDyV(img, a/2, b/2, c/2, 0); // Cuadrante Inferior Derecho

        } 
        catch (Exception e) 
        {
            System.out.println(">: Ocurrió un error(pintar): " + e);
        }

        return nueva;
    }

    public void rotar2()
    {
        BufferedImage img = null;
        try 
        {
            File f_imagen = new File(path + "/copia.bmp"); // Obtiene el archivo indicado en path
            img = ImageIO.read(f_imagen);    // Abre el archivo en formato BufferedImage
            BufferedImage nueva = rotarDyV(img, 0, tamaño, tamaño, tamaño);
            ImageIO.write(nueva, "bmp", new File(path + "/copia.bmp"));   // Guarda la imagen editada en un archivo nuevo.
        
        } 
        catch (Exception e) 
        {
            System.out.println(">: Ocurrió un error(pintar): " + e);
        }
    }

    public static void main(String[] args)
    {
        App usr = new App();
        if(usr.read())
        {
            usr.copiar();
            usr.rotar();
        }
    }
}