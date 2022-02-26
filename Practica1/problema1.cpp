/*
        Anailsis de Algoritmos - 3CV12
            Practica 1 - Problema 1
        Autor: López Cabagné Oscar Eduardo
*/

#include<stdio.h>
#include<stdlib.h>
#include<time.h>

int* generarArreglo(int n)
{
    int *A;
    int i;
    A = (int*)malloc(n * sizeof(int));
    if(A == NULL)
    {
        printf(">: Ocurrio un error. Memoria insuficiente.\n");
        exit(1);
    }

    srand(time(NULL));
    for(i = 0; i < n; i++)
    {
        A[i] = (rand()%(3*n));
    }

    return A;
}

void escribir(int n, int instrucciones)
{
    FILE *fpt;
    fpt = fopen(".\\file.csv", "a");
    fprintf(fpt, "%d, %d\n", n, instrucciones);
    fclose(fpt);
}

int problema1(int n)
{
    /*
        Arreglo de enteros A[0...n], con valores random entre 0 y 3n. 
        Si un valor de la primera mitad se encuentra en la segunda mitad, se detiene.
        Se detiene en la primer coincidencia o en ninguna coincidencia.
        ctr representa al contador de instrucciones ejecutadas. Ignora su propia inicialización e incrementos.
    */
    int ctr = 0;
    int *A = generarArreglo(n); ctr++;
    int i, j; ctr++; ctr++;

    //A[n/2] = A[0];  // Forzar Mejor Caso. Siempre la primera comparación es la coincidencia.
    
    /*
    for(i = n/2; i < n; i++)        // Forzar Peor Caso.
    {
        A[i] = A[i] + (3*n);   // Forzamos que los valores en la segunda mitad estén fuera del rango de generación. 
                               // de esta forma, los valores nunca coincidirán con la primera mitad (dentro del rango).
    }
    */

    printf(">: A[%d] = ", n); ctr++;   // Imprime el arreglo original
    ctr++;
    for(i = 0; i < n; i++)
    {
        ctr++;
        printf("%d ", A[i]); ctr++;
    }
    ctr++;
    printf("\n"); ctr++;

    ctr++;
    for(i = 0; i < n/2; i++)    // Dentro de la mitad de la izquierda
    {
        ctr++;
        ctr++;
        for(j = n/2; j < n; j++)    // Dentro de la mitad de la derecha
        {
            ctr++;
            if(A[i] == A[j])    // Si un elemento de la izquierda está en la derecha
            {
                ctr++;
                printf("   A[%d] = %d && A[%d] = %d\n", i, A[i], j, A[j]); ctr++;     // Imprime la coincidencia, su valor y sus posiciones. Ej. A[1] = 5 && A[2] = 5
                ctr++;  // Incremento del return
                escribir(n, ctr);   // Se ignora el conteo de esta función ya que no pertenece a la funcionalidad del algoritmo.
                return 0;   // Encontró una coincidencia, termina su ejecución.
            }
            ctr++;
        }
        ctr++;
    }
    ctr++;

    ctr++; //Incremento del return
    escribir(n, ctr);
    return 0;   // NO encontró una coincidencia, termina su ejecución.
}

int main()
{
    system("CLS");

    for(int i = 1; i <= 100; i++)
    {
        problema1(i);
    }

    return 0;
}
