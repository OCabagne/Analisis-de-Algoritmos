/*
        Anailsis de Algoritmos - 3CV12
            Practica 3 - Problema 1
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

int Division1(int n, int div, int res)
{
    int cnt = 0;
    int q = 0; cnt++;
    while (n >= div)
    {
        cnt++;
        n = n - div; cnt++;
        q = q + 1; cnt++;
    }
    cnt++;

    res = n; cnt++;
    cnt++;

    escribir(n, cnt);
    return q;
}

int Division2(int n, int div, int res)
{
    int cnt = 0;
    int dd = div; cnt++;
    int q = 0; cnt++;
    int r = n; cnt++;
    while (dd <= n)
    {
        cnt++;
        dd = 2*dd; cnt++;
    }
    cnt++;

    while (dd > div)
    {
        cnt++;
        dd = dd/2; cnt++;
        q = 2*q; cnt++;
        if(dd <= r)
        {
            cnt++;
            r = r - dd; cnt++;
            q = q + 1; cnt++;
        }
        cnt++;
    }
    cnt++;
    cnt++;
    escribir(n, cnt);
    return q;
}

int Division3(int n, int div)
{
    int cnt = 0;
    if(div > n )
    {
        cnt++; cnt++;
        escribir(n, cnt);
        return 0;
    }
    else
    {
        cnt++;
        cnt++; cnt++;
        return 1+Division3(n-div, div);
    }
}

int main()
{
    system("CLS");
    int q, i;
    int n = 100; 

    for(i = 1; i <= n; i++)
    {
        q = Division1(i, (n+1)-i, 0);

        //q = Division2(i, (n+1)-i, 0);

        //q = Division3(i, (n+1)-i);

        printf("\n>: Resultado: %d\n", q);
    }

    return 0;
}
