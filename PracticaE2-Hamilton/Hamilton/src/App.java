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

        usr.Verificacion_Hamilton(G, C[0]);
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
        System.out.println(">: C = " + C);
        int[] elementos = transformar(C);   // Certificado separado y convertido a enteros

        int[] adyacente = new int[elementos.length - 1];

        if(elementos.length == nodos + 1)   // Si la longitud del ciclo es igual al número de nodos mas uno.
        {
            if(elementos[0] == elementos[elementos.length - 1])        // Si el ciclo inicia y termina en el mismo nodo.
            {
                for(int i = 0; i < elementos.length - 1; i++)   // Recorreremos cada elemento perteneciente al certificado
                {
                    adyacente = G[elementos[i]];

                    if(!buscar(adyacente, elementos[i + 1]))  // Si un nodo NO conecta con el siguiente
                    {
                        //System.out.println(">: Aceptado: [" + elementos[i] + "] -> [" + elementos[i+1] + "]");
                        System.out.println(">: Incorrecto: [" + elementos[i] + "] -> [" + elementos[i+1] + "]");
                        System.out.println(">: Ciclo no valido. ");
                        return false;
                    }
                }

                System.out.println(">: Ciclo valido. ");    // Si todos los nodos conectan entre si de acuerdo al certificado
                return true;
            }
    
        }

        System.out.println(">: Ciclo no valido. "); // Si el certificado no cumple alguna de las condiciones
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

    public Boolean buscar(int[] elementos, int numero)  // Buscar un entero en un arreglo de enteros.
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
}