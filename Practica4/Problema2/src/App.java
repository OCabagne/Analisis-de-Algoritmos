import javax.imageio.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.image.*;

public class App
{
    BufferedImage imagen = null;
    BufferedImage copia = null;
    String path = "C://Users//Oscar//Desktop//ESCOM//10mo//Analisis de Algoritmos//Analisis-de-Algoritmos//Practica4/";
    int tamaño = 1024;

    public BufferedImage read()
    {
        BufferedImage imagen = null;
        try 
        {
            File f_imagen = new File(path + "/imagen.bmp"); // Obtiene el archivo indicado en path
            imagen = ImageIO.read(f_imagen);    // Abre el archivo en formato BufferedImage
            System.out.println(">: Se ha leído correctamente la imagen.");  // Confirmación
            tamaño = imagen.getWidth();
            return imagen;
        } 
        catch (Exception e) 
        {
            System.out.println(">: Ocurrió un error: " + e);
            return imagen;
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
    
    public BufferedImage unir(BufferedImage B, int a, int b, int c)
    {
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < B.getHeight(); i++)
        {
            for(int j = 0; j < B.getWidth(); j++)
            {
                queue.add(B.getRGB(i, j));
            }
        }

        // ----------------------------------------------------------------

        for(int i = a; i < b; i++)
        {
            for(int j = a; j < b; j++)
            {
                imagen.setRGB(i, j, queue.remove());
            }
        }

        return imagen;
    }

    public BufferedImage rotarDyV(int a1, int a2, int b1, int b2)
    {
        BufferedImage nueva = null;
        try 
        {
            if(b2 - b1 <= 2)   // Si ya es matriz 2x2
            {
                b2--;
                int rgb = imagen.getRGB(a1, a2); // a  0,0 -> 1,0
                nueva.setRGB(1, 0, rgb);

                rgb = imagen.getRGB(a1, b2);     // b  0,1 -> 0,0
                nueva.setRGB(0, 0, rgb);

                rgb = imagen.getRGB(b2, a1);     // c  1,0 -> 1,1
                nueva.setRGB(1, 1, rgb);

                rgb = imagen.getRGB(b2, b2);     // d    1,1 -> 0,1
                nueva.setRGB(0, 1, rgb);

                return nueva;
            }

            BufferedImage A = rotarDyV(a1, a2, b1, b2/2);
            BufferedImage B = rotarDyV(a1, b2/2, b1, b2);
            BufferedImage C = rotarDyV(b2/2, a2, b2/2, b2/2);
            BufferedImage D = rotarDyV(b2/2, b2/2, b2/2, b2);

            nueva = this.unir(A, a2, b2, b2+1);
            nueva = this.unir(B, a2, b2, b2+1);
            nueva = this.unir(C, a2, b2, b2+1);
            nueva = this.unir(D, a2, b2, b2+1);

        } 
        catch (Exception e) 
        {
            System.out.println(">: Ocurrió un error(pintar): " + e);
        }

        return nueva;
    }

    public void rotar2()
    {
        try 
        {
            BufferedImage nueva = rotarDyV(0, 0, tamaño, tamaño);
            ImageIO.write(nueva, "bmp", new File(path + "/DyV.bmp"));   // Guarda la imagen editada en un archivo nuevo.
        
        } 
        catch (Exception e) 
        {
            System.out.println(">: Ocurrió un error: " + e);
        }
    }

    public static void main(String[] args)
    {
        App usr = new App();
        usr.read();
        usr.copiar();
        usr.rotarDyV(0, 0, 0, 1024);  // (0, tamaño, tamaño) [0,0]; [0,4]
    }
}