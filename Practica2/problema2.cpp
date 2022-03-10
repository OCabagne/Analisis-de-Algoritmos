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
    fpt = fopen(".\\file.csv", "a");
    fprintf(fpt, "%d, %d\n", n, instrucciones);
    fclose(fpt);
}

void MostrarPerfectos(int n)
{

}

bool Perfecto(int n)
{
    int i = 1, sum = 0;
    while(i<n)
    {
        if(n%i == 0)
        {
            sum += i;
        }
        i++;
    }
    if(sum == n)
    {
        return true;
    }
    else
    {
        //printf("\n>: %d NO es un numero perfecto.\n", n);
        return false;
    }
}

int main()
{
    system("CLS");
    int i, n, j;
    printf("\n>: n = ");
    scanf("%d", &n);

    if(Perfecto(n))
    {
        printf("\n>: %d es un numero perfecto.\n", n);
    }
    
    /*
    j = 0;  
    for(i = 6; j < n; i++)  // Iniciamos en 6, pues es el primer número perfecto. 
    {
        if(Perfecto(i))
        {
            printf("\n>: %d es un numero perfecto.\n", i);
            j++;
        }
    }
    */
   
    return 0;
}
