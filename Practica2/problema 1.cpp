/*
	Autor: Cortés Ortiz Sergio
	Boleta: 2018630631
	Grupo: 3CV12
*/
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>

void escribiri(int n, int contador)
{
	FILE *fp;
    fp = fopen(".\\datosi.csv", "a");
    fprintf(fp, "%d, %d\n", n, contador);
    fclose(fp);
}

void escribirr(int n, int *contador)
{
	FILE *fp;
    fp = fopen(".\\datosr.csv", "a");
    fprintf(fp, "%d, %d\n", n, *contador);
    fclose(fp);
}

int Fibonacci_i(int i)
{
	int ctri = 0;
	int n = 0, m = 1, r; ctri++; ctri++; ctri++; //declaración de variables
	
	if(i == 1 || i == 2)
	{
		ctri++; //sentencia if es verdadera
		ctri++; //operador - dentro del return
		ctri++; //sentencia return
		escribiri(i,ctri);
		return i - 1;
	}
	ctri++; //sentencia if es falsa
	for(int j = 3; j <= i; j++ )
	{
		ctri++; //sentencia for verdadera
		r = n + m; ctri++;
		n = m; ctri++;
		m = r; ctri++;
		ctri++; //incremento del for
	}
	ctri++; //sentencia for verdadero
	ctri++; //sentencia return
	escribiri(i,ctri);
	return r;  
}

int Fibonacci_r(int i, int *ctrr)
{
	if(i == 1 || i == 2)
	{
		(*ctrr)++; //sentencia if es verdadera
		(*ctrr)++; //operador - dentro del return
		(*ctrr)++; //sentencia return
		return i-1;
	}else
	{
		(*ctrr)++; //sentencia if es flasa
		(*ctrr)++; //operador + dentro del return
		(*ctrr)++; //sentencia return
		return (Fibonacci_r(i-1, ctrr) + Fibonacci_r(i-2, ctrr));
	}
	
	
}

int main()
{
	system("DEL /F /A datosi.csv");
	system("DEL /F /A datosr.csv");
	int resultadoi, resultador;
	int ctrr;
	int num;
	
	srand(getpid());
	
	
	for(int i = 1; i <= 40; i++)
    {
    	printf("%d.- ", i);
        resultadoi = Fibonacci_i(i);
        printf(" %d ,", resultadoi);
        
        ctrr=0;
        resultador = Fibonacci_r(i, &ctrr);
        printf(" %d \n", resultador);
		escribirr(i,&ctrr);
        
    }

    return 0;
}
