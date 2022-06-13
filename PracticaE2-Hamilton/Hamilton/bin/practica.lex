\documentclass[12pt,twoside]{article}
\usepackage{amsmath, amssymb}
\usepackage{amsmath}
\usepackage[active]{srcltx}
\usepackage{amssymb}
\usepackage{amscd}
\usepackage{makeidx}
\usepackage{amsthm}
\usepackage{algpseudocode}
\usepackage{algorithm}

\usepackage{fancyhdr}
\usepackage{graphics}
%----------------------------------------------------------------------------------------------
\usepackage{amsmath, amssymb}
\usepackage{amsmath}
\usepackage[active]{srcltx}
\usepackage{amssymb}
\usepackage{amscd}
\usepackage{makeidx}
\usepackage[dvips]{graphicx}

\renewcommand{\baselinestretch}{1}
\setcounter{page}{1}
\setlength{\textheight}{21.6cm}
\setlength{\textwidth}{14cm}
\setlength{\oddsidemargin}{1cm}
\setlength{\evensidemargin}{1cm}
\pagestyle{myheadings}
\thispagestyle{empty}
\markboth{\small{Pr\'actica Extra. L\'opez Cabagn\'e Oscar Eduardo}}{\small{.}}
\date{}
\begin{document}


\begin{figure}[h]
\vspace{-3cm} \hspace{-2cm} \setlength{\unitlength}{1mm}
\begin{picture}(15,25)(-10,0)
\includegraphics[width=16cm,height=3cm]{titulo.PNG}
\end{picture}
\end{figure}


\vspace{0cm}

\centerline{\bf An\'alisis de Algoritmos, Sem: 2022-2, 3CV12, Pr\'actica Extra, Fecha: 13-06-2022}

\centerline{}

%\centerline{}


\begin{center}
\Large{\textsc{Pr\'actica Extra: Verificaci\'on Hamilton}}
\end{center}
\centerline{}
\centerline{\bf {L\'opez Cabagn\'e Oscar Eduardo}}
\centerline{}
\centerline{$olopezc1402@alumno.ipn.mx$}



\newtheorem{Theorem}{\quad Theorem}[section]

\newtheorem{Definition}[Theorem]{\quad Definition}

\newtheorem{Corollary}[Theorem]{\quad Corollary}

\newtheorem{Lemma}[Theorem]{\quad Lemma}

\newtheorem{Example}[Theorem]{\quad Example}

\bigskip

\textbf{Resumen:} En esta practica extra veremos el funcionamiento, importancia y aplicaci\'on del agorlitmo de Dijkstra. Para ver el funcionamiento se realizar\'an algunas pruebas sobre un grafo conocido y comprobaremos los resultados obtenidos. 

\vspace{5mm} %5mm vertical space

{\bf Palabras Clave:} Java, An\'alisis, Gr\'afica, Algoritmo, Grafo, Hamilton.

\newpage

\section{Introducci\'on}

Los problemas P y NP son dos grupos en los que se encuentran problemas decidibles, es decir, problemas para cualquiera de los dos tipos existe un algoritmo que lo resuelve en un tiempo finito. Para un problema se puede encontrar una soluci\'on o verificar si una propuesta es soluci\'on.

\vspace{5mm} %5mm vertical space

Bajo esta idea, podemos decir que existen problemas que son f\'aciles de resolver y sencillos de verificar, es decir, los problemas $P$.Este tipo de problemas suelen tener soluci\'on y verificaci\'on en orden polinomial para una entrada N. 

\vspace{5mm} %5mm vertical space

Se les considera "sencillos" porque, al tener orden de complejidad polinomial, su ejecuci\'on no consumir\'a una alta cantidad de recursos computacionales ni de tiempo. 

\vspace{5mm} %5mm vertical space

Por otro lado, existen los problemas sencillos de verificar pero complejos de resolver. Estos son los problemas NP. Un ejemplo que nos ayuda siempre a entender este tipo de problemas ser\'a un rompecabezas. Nos puede llevar varias horas o incluso d\'ias resolverlo por completo, pero basta con darle un vistazo para verificarlo. 

\vspace{5mm} %5mm vertical space

Sabiendo esto, podemos tambi\'en decir que $P \subset NP$, pues ambos tienen un algoritmo de complejidad polinomica que verifica sus soluciones.

\vspace{5mm} %5mm vertical space

\begin{figure}[htb]
\centering
\includegraphics[width=0.5\textwidth]{p_np.png}
\caption{Problemas P y NP}
\end{figure}

\newpage

Una de las preguntas del milenio es: ¿$P = NP$?. De ser afirmativo, significar\'ia entonces que todo problema con verificaci\'on polinomial tambi\'en se puede resolver en tiempo polinomial. De ser negativo, implicar\'a que existen algoritmos para los que no hay una soluci\'on polinomial.  

\vspace{5mm} %5mm vertical space

Aunque hasta el d\'ia de hoy nadie ha podido demostrar ninguno de los dos casos, la creencia popular es que $P \neq NP$. 

\vspace{5mm} %5mm vertical space

En los 70's se descubri\'o la existencia de un tercer tipo de problemas. Los $NP-C$ (NP-Completos).

\vspace{5mm} %5mm vertical space

Se dice que un problema es NP-C si cumple con:

\begin{itemize}
    \item $C$ es $NP$.
    \item Todo problema $NP$ se puede reducir a $C$ en tiempo polinomial.
\end{itemize}

\vspace{5mm} %5mm vertical space

Se dice que un problema $L$ se reduce a $L'$ si $L'$ tiene mayor complejidad que $L$, de manera que el algoritmo que resuelve $L'$ tambi\'en resuelva $L$.

\vspace{5mm} %5mm vertical space

\begin{figure}[htb]
\centering
\includegraphics[width=0.6\textwidth]{p_vs_np.png}
\caption{P vs NP}
\end{figure}

\vspace{5mm} %5mm vertical space

En la figura 2, a la izquierda vemos la distribuci\'on de los problemas si $P \neq NP$. A la derecha vemos el caso de que $P = NP$.

\newpage

\section{Conceptos B\'asicos}

\vspace{5mm} %5mm vertical space

En teor\'ia de grafos, se le llamao "ciclo" a un camino cuyo punto de inicio y fin es el mismo nodo. El ciclo de un grafo con $n$ vertices se denota como $C_n$.

\vspace{5mm} %5mm vertical space

Un grafo suele representarse como un conjunto de puntos (vértices o nodos) conectados por líneas (aristas o arcos).

\vspace{5mm} %5mm vertical space

\begin{figure}[htb]
\centering
\includegraphics[width=0.6\textwidth]{ciclo.png}
\caption{Ejemplo de un ciclo en un grafo.}
\end{figure}

\vspace{5mm} %5mm vertical space

{\bf Ciclo hamiltoniano:}
Es ciclo hamiltoniano cuando tiene inicio y final en un mismo nodo y, adem\'as, pasa por todos los nodos una sola vez. Lleva el nombre en honor a William Rowan Hamilton, quien invent\'o un juego donde se deb\'ia encontrar un ciclo hamiltoniano en las aristas de un dodecaedro. Este problema fue resuelto utilizando cuaterniones.

\vspace{5mm} %5mm vertical space

Este problema es un conocido problema NP-C. 

\vspace{5mm} %5mm vertical space

Para determinar si un grafo es Hamiltoniano o no, se debe aplicar el Teorema de Dirac, que dice:

\vspace{5mm} %5mm vertical space

"Sea $G = (V,E)$ un grafo conexo con $|V| \geq 3$. Si $deg(v) ≥ |V|/2$ para todo $v \subset V$, entonces $G$ es hamiltoniano."

\vspace{5mm} %5mm vertical space

En terminos m\'as simples, si:

\begin{itemize}
    \item  Un grafo $G$, con sus vértices y aristas, conexo, y con un total de vértices mayor o igual a 3. 
    \item Si el grado de cada uno de los vértices de este grafo es mayor o igual que la mitad del número total de vértices.
\end{itemize}

\vspace{5mm} %5mm vertical space

Se cumple, entonces es un grafo Hamiltoniano.

\begin{figure}[htb]
\centering
\includegraphics[width=0.6\textwidth]{ejemplo.png}
\caption{Ejemplo de un ciclo Hamiltoniano.}
\end{figure}

\vspace{5mm} %5mm vertical space

\newpage

\textbf{Problema 1. Verificar Hamiltoniano.}:

\vspace{5mm} %5mm vertical space

Como se explico anteriormente, el algoritmo para encontrar ciclos Hamiltonianos tiene complejidad NP-C, por lo que en esta pr\'actica nos enfocaremos \'unicamente a verificar certificados (cadenas candidatas a ser soluci\'on) de ciclos Hamiltonianos.

\vspace{5mm} %5mm vertical space

Para llevar a cabo esta tarea ser\'a necesario encontrar y definir las caracter\'isticas que todo ciclo Hamiltoniano tiene:

\vspace{5mm} %5mm vertical space

\begin{itemize}
    \item  Un certificado debe tener longitud $n + 1$. Donde $n$: Numero total de nodos. Ser\'a necesario sumar uno para contabilizar el regreso al nodo inicial.
    \item El primer y \'ultimo elemento de un certificado deber\'an ser iguales. As\'i verificamos que sea un ciclo.
    \item El nodo actual debe estar conectado con el nodo siguiente de acuerdo al certificado. Es decir, el certificado debe ser validado por el grafo.
\end{itemize}

\vspace{5mm} %5mm vertical space

Como paso incial, se propone el siguiente grafo:

\vspace{0cm}

\begin{figure}[htb]
\centering
\includegraphics[width=0.6\textwidth]{grafo.png}
\caption{Grafo propuesto.}
\end{figure}

\newpage

Para poder utilizar este grafo dentro de nuestro programa, se defini\'o su tabla de adyacencias como se muestra a continuaci\'on.

\vspace{0cm}

\begin{figure}[htb]
\centering
\includegraphics[width=0.4\textwidth]{grafo_codigo.png}
\caption{Tabla de adyacencias.}
\end{figure}

\vspace{0cm}

\vspace{5mm} %5mm vertical space

Luego, para agilizar las pruebas, se propone una serie de certificados diferentes:

\vspace{0cm}

\begin{figure}[htb]
\centering
\includegraphics[width=0.6\textwidth]{certificados.png}
\caption{Certificados propuestos}
\end{figure}

\vspace{0cm}

\vspace{5mm} %5mm vertical space

Tal como se muestra en la figura 7, se proponen dos certificados previamente determinados correctos y tres incorrectos. Se explicar\'an las variaciones de cada uno en su respectiva prueba.

\newpage

Se defini\'o una clase Hamilton, donde se encuentran los m\'etodos necesarios para resolver el problema y el algoritmo principal.

\vspace{0cm}

\begin{figure}[htb]
\centering
\includegraphics[width=0.9\textwidth]{Hamilton.png}
\caption{Clase Hamilton}
\end{figure}

\vspace{0cm}

El m\'etodo $transformar(C)$ nos ayudar\'a a convertir un String a un arreglo de enteros, separ\'andolos por comas y obteniendo acceso a cada uno de forma individual.

\vspace{5mm} %5mm vertical space

El m\'etodo $buscar(elementos, numero)$ nos ayudar\'a a verificar si un valor entero se encuentra dentro de un arreglo de otros enteros.

\vspace{5mm} %5mm vertical space

Finalmente, el m\'etodo $Verificar_Hamilton(G, C)$ ser\'a el encargado de realizar el an\'alisis principal.

\newpage

\vspace{0cm}

\begin{figure}[htb]
\centering
\includegraphics[width=0.8\textwidth]{algoritmo.png}
\caption{M\'etodo $Verificar_ Hamilton(G, C)$}
\end{figure}

\vspace{0cm}

En la figura 9 vemos el m\'etodo principal. Vemos c\'omo se realizan las validaciones de los puntos caracter\'sticos, buscando primero que el certificado inicie y finalice con el mismo elemento, y luego que tenga la longitud $n+1$, para finalmente verificar que el grafo puede ir de un nodo a otro tal como se indica en el certificado. 

\vspace{5mm} %5mm vertical space

Si una de las condiciones no se cumple, autom\'aticamente se da como "Certificado No Valido". Si cumple con todos los criterios y el grafo admite el certificado, entonces se trata de un "Certificado Valido".

\newpage

\section{Experimentaci\'on y Resultados}
\textbf{Problema 1. Verificar Hamiltoniano.}
Para realizar las pruebas utilizaremos una serie de certificados previamente creados, con el fin de ver varias situaciones diferentes.

\vspace{5mm} %5mm vertical space

En el primer caso veremos un certificado v\'alido que inicia y termina en el nodo 0.

\vspace{0cm}

\begin{figure}[htb]
\centering
\includegraphics[width=0.5\textwidth]{prueba1.png}
\caption{Certificado V\'alido con inicio en nodo 0}
\end{figure}

\vspace{0cm}

Tal como se esperaba, y como podemos comprobar con el diagrama, el certificado es v\'alido y no encontr\'o ning\'un problema con el grafo.

\vspace{5mm} %5mm vertical space

En el segundo caso veremos un certificado que no corresponde al grafo dado.

\vspace{0cm}

\begin{figure}[htb]
\centering
\includegraphics[width=0.5\textwidth]{prueba5.png}
\caption{Certificado NO V\'alido con el grafo.}
\end{figure}

\vspace{0cm}

Vemos que, a pesar de tener la longitud correcta y el mismo elemento al principio y al final, el programa encontr\'o un error con el grafo. En particular, el nodo 3 no puede ir a \'el mismo, como se indica en el certificado, por lo tanto, se determina como "No Valido" y deja de analizar el resto de instrucciones.

\newpage

\vspace{5mm} %5mm vertical space

Ahora veremos un certificado que no cumple con la longitud necesaria, y que tampoco cumple la condici\'on de iniciar y terminar con el mismo elemento.

\vspace{0cm}

\begin{figure}[htb]
\centering
\includegraphics[width=0.5\textwidth]{prueba2.png}
\caption{Certificado NO V\'alido.}
\end{figure}

\vspace{0cm}

Como era de esperarse, al no cumplir con al menos una de las primeras condiciones, descarta toda otra posibilidad y lo declara como "No Valido".

\vspace{5mm} %5mm vertical space

Nuevamente veremos un certificado correcto, pero en esta ocasi\'on comenzar\'a desde el nodo 1.

\vspace{0cm}

\begin{figure}[htb]
\centering
\includegraphics[width=0.5\textwidth]{prueba4.png}
\caption{CCertificado V\'alido con inicio en nodo 1}
\end{figure}

\vspace{0cm}

Vemos que, a pesar de iniciar desde un nodo diferente, no se encuentra ning\'un problema ni error. Se declara como "Valido".

\vspace{5mm} %5mm vertical space

Finalmente veremos un \'ultimo certificado no v\'alido. En esta ocasi\'on se trata de un certificado que cumple con el primer y \'ultimo elementos iguales, pero no con la longitud esperada.

\vspace{0cm}

\begin{figure}[htb]
\centering
\includegraphics[width=0.5\textwidth]{prueba3.png}
\caption{Certificado NO V\'alido por longitud.}
\end{figure}

\vspace{0cm}

Podemos ver que, a pesar de cumplir otras condiciones, como no cumple con una ya no realiza el an\'alisis del resto y lo declara como "No Valido".

\newpage

Ahora veremos el comportamiento de estos experimentos en forma gr\'afica, con el fin de confirmar que nuestro algoritmo tenga la complejidad lineal esperada.

\vspace{0cm}

\begin{figure}[htb]
\centering
\includegraphics[width=0.4\textwidth]{datos.png}
\caption{Datos de las gr\'aficas obtenidas.}
\end{figure}

\vspace{0cm}

\vspace{0cm}

\begin{figure}[htb]
\centering
\includegraphics[width=0.5\textwidth]{abajo.png}
\caption{Gr\'afica obtenida y propuesta de acotamiento por debajo}
\end{figure}

\vspace{0cm}

En la figura 16, en color verde observamos los puntos obtenidos para las pruebas realizadas anteriormente. En color negro tenemos la funci\'on propuesta de acotamiento por debajo $f(x) = 9x - 50$. Adem\'as, se propone $b_0 = 6$. 

\newpage

\vspace{0cm}

\begin{figure}[htb]
\centering
\includegraphics[width=0.5\textwidth]{arriba.png}
\caption{Gr\'afica obtenida y propuesta de acotamiento por arriba}
\end{figure}

\vspace{0cm}

En la figura 17, en color verde observamos los puntos obtenidos para las pruebas realizadas anteriormente. En color rojo tenemos la funci\'on propuesta de acotamiento por arriba $f(x) = 9x$. Adem\'as, se propone $b_0 = 4$. 

\vspace{0cm}

\begin{figure}[htb]
\centering
\includegraphics[width=0.5\textwidth]{general.png}
\caption{Gr\'afica general}
\end{figure}

\vspace{0cm}

Finalmente, en la figura 18, en color verde observamos los puntos obtenidos para las pruebas realizadas anteriorment, en color negro tenemos la funci\'on propuesta de acotamiento por debajo $f(x) = 9x - 50$, en color rojo tenemos la funci\'on propuesta de acotamiento por arriba $f(x) = 9x$. Y se propone un $b_0 = 6$. Por lo tanto, se concluye que el algoritmo implementado si tiene complejidad lineal.

\newpage

\section{Conclusiones}
Esta pr\'actica result\'o ser la m\'as sencilla de todo el curso, sin embargo resulta tambi\'en una de las m\'as importantes, pues logramos comprender los problemas P y NP, sus caracter\'isticas, sus diferencias y similitudes, adem\'as de que pudimos realizar un programa que verifique los resultados de un problema NP-C tal como la teor\'ia lo especifica.

\vspace{5mm} %5mm vertical space

\textbf{Conclusiones L\'opez Cabagn\'e Oscar Eduardo.} A pesar de ser una de las pr\'acticas m\'as sencillas, tambi\'en fue una de las que m\'as me gust\'o, pues m\'as all\'a del algoritmo, pude comprender mejor c\'omo todos los temas que vimos nos llevan a comprender porqu\'e esta pr\'actica se comporta de esta manera y la importancia que puede llegar a tener.

\vspace{5mm} %5mm vertical space

\begin{figure}[htb]
\centering
\includegraphics[width=0.5\textwidth]{Oscar.PNG}
\caption{L\'opez Cabagn\'e Oscar Eduardo}
\end{figure}

\newpage

\section{Bibliograf\'ia}

\begin{itemize}
    \item \textbf{Que significa un problema P o NP. Recuperado 12 de Junio de 2022, de https://geeks.ms/etomas/2019/02/02/que-significa-un-problema-p-o-np/}.
    
    \item \textbf{Camino, Ciclo y Bucle. Recuperado 12 de Junio de 2022, de http://163.10.22.82/OAS/estructuras_de_grafos/camino_ciclo_y_bucle.html}.
    
    \item \textbf{Camino hamiltoniano. Recuperado 12 de Junio de 2022, de http://i3campus.co/CONTENIDOS/wikipedia/content/a/camino_hamiltoniano.html}.
    
    \item\textbf{Teor\'ia de Grafos. Recuperado 12 de Junio de 2022, de https://www.unipamplona.edu.co/unipamplona/portalIG/home_23/recursos/general/11072012/grafo3.pdf.}.
    
\end{itemize}

\medskip

\end{document}
