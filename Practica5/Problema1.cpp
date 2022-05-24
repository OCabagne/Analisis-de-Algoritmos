/*
        Anailsis de Algoritmos - 3CV12
            Practica 5 - Granjero
        Autor: López Cabagné Oscar Eduardo
*/

#include<stdio.h>
#include<stdlib.h>
#include<time.h>

int divide(int *array, int start, int end)
{
    int left;
    int right;
    int pivot;
    int temp;

    pivot = array[start];
    left = start;
    right = end;

    // Mientras no se cruzen los índices
    while (left < right)
    {
        while (array[right] > pivot)
        {
            right--;
        }

        while ((left < right) && (array[left] <= pivot))
        {
            left++;
        }

        // Si todavía no se cruzan los indices seguimos intercambiando
        if (left < right)
        {
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;
        }
    }

    // Los índices ya se han cruzado, ponemos el pivot en el lugar que le corresponde
    temp = array[right];
    array[right] = array[start];
    array[start] = temp;

    // La nueva posición del pivot
    return right;
}

void quicksort(int *array, int start, int end)
{
    int pivot;

    if (start < end)
    {
        pivot = divide(array, start, end);

        // lista de menores
        quicksort(array, start, pivot - 1);

        // lista de mayores
        quicksort(array, pivot + 1, end);
    }
}

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
    A[0] = 0;

    quicksort(A, 0, n-1);

    return A;
}

int* generarArreglo(int n, int x)
{
    int *A;
    int i;
    A = (int*)malloc(n * sizeof(int));
    if(A == NULL)
    {
        printf(">: Ocurrio un error. Memoria insuficiente.\n");
        exit(1);
    }
    A[0] = 0;
    srand(time(NULL));
    for(i = 1; i < n; i++)
    {
        A[i] = 0;
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
        ctr representa al contador de instrucciones ejecutadas. Ignora su propia inicialización e incrementos.
    */
    int ctr = 0;
    int *A = generarArreglo(n); ctr++;
    //int A[] = {0, 29, 36, 50, 52, 66, 71, 85, 100, 117, 127, 129};
    int *S = generarArreglo(n, 0); ctr++; // Conjunto Solución. Inicializado en solo cero
    int i, j = 0, r = 30, f; ctr++; ctr++; ctr++; ctr++;

    //printf("\n>: Valor de r = ");
    //scanf("%d", &r);

    printf("\n>: A[%d] = ", n);   // Imprime el arreglo original
    for(i = 0; i < n; i++)
    {
        printf("%d ", A[i]);
    }

    ctr++;
    for(i = 1; i < n; i++)      // Algoritmo del Granjero
    {
        ctr++;
        f = S[j] + r; ctr++;
        j++; ctr++;
        while(A[i] <= f)
        {   ctr++;
            S[j] = A[i];ctr++;
            i++;ctr++;
        }
        ctr++; ctr++;
    }
    ctr++;

    printf("\n>: S[%d] = 0 ", n);   // Imprime el conjunto solución
    for(i = 0; i < n; i++)
    {
        if(S[i] == 0)
            printf("");
        else
            printf("%d ", S[i]);
    }

    ctr++; //Incremento del return
    escribir(n, ctr);
    return 0;
}

int main()
{
    system("CLS");
    
    for(int i = 0; i < 100; i++)
            problema1(i);

    return 0;
}
