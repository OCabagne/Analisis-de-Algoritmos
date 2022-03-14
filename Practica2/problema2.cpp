/*
        Anailsis de Algoritmos - 3CV12
            Practica 2 - Problema 2
        Autor: López Cabagné Oscar Eduardo
*/

#include<stdio.h>
#include<stdlib.h>
#include<time.h>


void escribir(int n, int instrucciones)
{
    FILE *fpt;
    fpt = fopen(".\\datos.csv", "a");
    fprintf(fpt, "%d, %d\n", n, instrucciones);
    fclose(fpt);
}

bool Perfecto(int n)
{
    int cnt = 0;
    int i, sum = 0; cnt++; cnt++;
    cnt++;

    for(i = 1; i < n; i++)
    {
        cnt++; // comparacion VALIDA for
        if(n%i == 0)    // Si i es divisor de n
        {
            cnt++;  // comparacion VALIDA if
            sum += i;   cnt++;// Se suma i
        }
        cnt++; // comparacion NO VALIDA if
        cnt++; // incremento for
    }
    cnt++; // comparacion NO VALIDA for

    if(sum == n)    // Si la suma de los divisores es igual a n
    {
        cnt++;
        cnt++;  //incremento del return
        escribir(n, cnt);
        return true;    // Es numero perfecto
    }
    cnt++;

    //printf("\n>: %d NO es un numero perfecto. Sum = %d\n", n, sum);
    cnt++;  //incremento del return
    escribir(n, cnt);
    return false;
}

void MostrarPerfectos(int n)
{
    int cnt = 0;
    int i, j = 0;  cnt++; cnt++; 

    cnt++;
    for(i = 6; j < n; i++)  // Iniciamos en 6, pues es el primer número perfecto. 
    {
        cnt++;
        cnt++;
        if(Perfecto(i)) // Si i es un número perfecto
        {
            cnt++;
            printf("\n>: %d es un numero perfecto.\n", i);
            j++; cnt++;   //Incrementamos el contador de perfectos encontrados
        }
    }
    cnt++;
    //escribir(n, cnt);
}

int main()
{
    system("CLS");
    int n;
    printf("\n>: n = ");
    scanf("%d", &n);
/*
    if(Perfecto(n))
    {
        printf("\n>: %d es un numero perfecto.\n", n);
    }
*/
    MostrarPerfectos(n);

    return 0;
}
