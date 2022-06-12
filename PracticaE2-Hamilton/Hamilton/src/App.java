import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.lang.model.element.Element;

/*
        Anailsis de Algoritmos - 3CV12
            Practica Extra 2 - Verificar Cilo de Hamilton
        Autor: López Cabagné Oscar Eduardo
*/

public class App 
{
    public static void main(String[] args) throws Exception
    {
        int G[][] = {
            {1,2,3,4},
            {0,2,3,4},
            {0,1,3,4},
            {0,1,2,4},
            {0,1,2,3}
        };

        String C[] = {
            "0,1,2,3,4,0",  // Correcto
            "0,1,2,3,3,0",
            "0,1,3,4,2",
            "1,2,3,4,0,1",  // Correcto
            "3,0,2,3"
        };

        Hamilton usr = new Hamilton();
        usr.setNodos(5);

        usr.Verificacion_Hamilton(G, C[4]);
    }
}

class Hamilton
{
    private int nodos;

    public int getNodos() {
        return nodos;
    }
    public void setNodos(int nodos) {
        this.nodos = nodos;
    }
    
    public Boolean Verificacion_Hamilton(int G[][], String C)
    {
        int ctr = 0;
        System.out.println(">: C = " + C); ctr++;
        int[] elementos = transformar(C); ctr++;  // Certificado separado y convertido a enteros

        int[] adyacente = new int[elementos.length - 1];ctr++;

        ctr++;
        if(elementos.length == nodos + 1)   // Si la longitud del ciclo es igual al número de nodos mas uno.
        {
            ctr++;
            if(elementos[0] == elementos[elementos.length - 1])        // Si el ciclo inicia y termina en el mismo nodo.
            {
                ctr++;
                for(int i = 0; i < elementos.length - 1; i++)   // Recorreremos cada elemento perteneciente al certificado
                {
                    ctr++;
                    adyacente = G[elementos[i]]; ctr++;

                    ctr++;
                    if(!buscar(adyacente, elementos[i + 1]))  // Si un nodo NO conecta con el siguiente
                    {
                        //System.out.println(">: Aceptado: [" + elementos[i] + "] -> [" + elementos[i+1] + "]");
                        System.out.println(">: Incorrecto: [" + elementos[i] + "] -> [" + elementos[i+1] + "]");ctr++;
                        System.out.println(">: Ciclo no valido. ");ctr++;ctr++;
                        file(elementos.length, ctr);
                        return false;
                    }

                    ctr++;
                }
                ctr++;

                System.out.println(">: Ciclo valido. ");ctr++;ctr++;    // Si todos los nodos conectan entre si de acuerdo al certificado
                file(elementos.length, ctr);
                return true;
            }
    
        }

        System.out.println(">: Ciclo no valido. ");ctr++;ctr++; // Si el certificado no cumple alguna de las condiciones
        file(elementos.length, ctr);
        return false;
    }

    private int[] transformar(String C) // Transforma un String a un arreglo de enteros.
    {
        String[] nodos = C.split(",");
        int[] elementos = new int[nodos.length];

        for(int i = 0; i < nodos.length; i++)
        {
            elementos[i] = Integer.parseInt(nodos[i]);
        }

        return elementos;
    }

    private Boolean buscar(int[] elementos, int numero)  // Buscar un entero en un arreglo de enteros.
    {
        Boolean flag = false;
        int i = 0;
        while(i < elementos.length)
        {
            if(elementos[i] == numero)
                flag = true;

            i++;
        }

        return flag;
    }

    private void file(int n, int ctr)
    {
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter("C://Users//spawe//Desktop//Archivos//Analisis-de-Algoritmos//PracticaE2-Hamilton//Hamilton//src//file.csv", true));
            out.write(n + "," + ctr + "\n");
            out.close();
            System.out.println("done!");

        } 
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}