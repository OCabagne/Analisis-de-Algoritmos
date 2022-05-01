/*
        Anailsis de Algoritmos - 3CV12
            Practica 4 - Problema 1
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

int main()
{
    system("CLS");
    system("DEL /F /A datos.csv");

    return 0;
}
