/*
	Autor: Cortés Ortiz Sergio
	Boleta: 2018630631
	Grupo: 3CV12
*/
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>

int obtenernumeros(int i, int *m, int *n)
{
	int a,b,rand1;
	int fibo[15]={1,2,5,13,34,89,233,610,1597,4181,10946,28657,75025,196418,514229};
	
	srand(getpid()+(2*i));
	
	do
	{
		rand1 = rand() % 14;
		
		//printf("rand1 = %d\n", rand1);
		
		*m = fibo[rand1]; //Generar m en el peor de los casos
		*n = fibo[rand1+1]; //Generar n en el peor de los casos
		//*m = rand();
		//*n = rand(); 
		//*m = rand(); //Generar m para el mejor de los casos
		//*n = *m; //En el mejor de los caso n es igual a m
	}while(*m == *n); //Para el mejor de los casos de debe de cambia a *m != *n
}

void escribir(int n, int contador)
{
	FILE *fp;
    fp = fopen(".\\datos.csv", "a");
    fprintf(fp, "%d, %d\n", n, contador);
    fclose(fp);
}

int problema2(int i)
{
	int ctr = 0;
	int m,n; ctr++; ctr++;
	int r; ctr++;
	
	obtenernumeros(i,&m,&n); ctr++;
	printf("m = %d  n = %d\n", m, n); ctr++;
	
	while(n != 0)
	{
		ctr++;
		r = m%n; ctr++;
		m = n; ctr++;
		n = r; ctr++;
		
	}
	ctr++;
	
	printf("En la ejecucion numero %d m es igual a: %d \n", i, m); ctr++;
	//printf("%d\n", ctr);
	escribir(i,ctr);
}

int main()
{
	system("DEL /F /A datos.csv");
	for(int i = 1; i <= 100; i++)
    {
        problema2(i);
    }

    return 0;
}
