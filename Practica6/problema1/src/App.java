/*
        Anailsis de Algoritmos - 3CV12
            Practica 6 - Cadena Común Más Larga (LCS)
        Autor: López Cabagné Oscar Eduardo
*/

import java.io.*;

public class App
{
  public static void main(String args[]) throws Exception
  {
    problema1 usr = new problema1();
    String file_1 = "hello";
    String file_2 = "hello_2";

    usr.tokenizar(file_1);
    usr.tokenizar(file_2);

    String str1 = usr.generateString(file_1);
    String str2 = usr.generateString(file_2);
    String LCS;
          
    int p = str1.length();
    int q = str2.length();
    int cadena;
    float resultado;
          
    LCS = usr.findLengthOfLCS(str1, str2, p, q);  
    cadena = LCS.length();

    if(p > q)
    {
      resultado = (cadena*100)/p;
    }
    else
    {
      resultado = (cadena*100)/q;
    }
          
    System.out.println(">: Str1.length = " + p);
    System.out.println(">: Str2.length = " + q);
    System.out.println(">: LCD.length = " + cadena);
    System.out.println(">: Coincidencia = " + resultado + "%");

  }
}

 
class problema1
{
  public void tokenizar(String name) throws Exception
  {
    FileReader fileReader = new FileReader("C://Users//Oscar//Desktop//ESCOM//10mo//Analisis de Algoritmos//Analisis-de-Algoritmos//Practica6//" + name + ".java");
    StreamTokenizer tokenizer = new StreamTokenizer(fileReader);

    File output = new File("C://Users//Oscar//Desktop//ESCOM//10mo//Analisis de Algoritmos//Analisis-de-Algoritmos//Practica6//"+ name + "_tok.txt");
    FileWriter writer = new FileWriter(output);

    tokenizer.parseNumbers();
    tokenizer.wordChars('_', '_');
    tokenizer.eolIsSignificant(true);
    tokenizer.ordinaryChars(0, ' ');
    tokenizer.slashSlashComments(true);
    tokenizer.slashStarComments(true);
  
    int tok = tokenizer.nextToken();
    boolean variable = false;
    boolean end = false;
    int i = 0;
  
    while (tok != StreamTokenizer.TT_EOF)
    {
      tok = tokenizer.nextToken();
      switch (tok)
      {
      
          case StreamTokenizer.TT_NUMBER:
            double n = tokenizer.nval;
            //System.out.println(n);
            writer.write(""+n);
            writer.flush();
            break;
      
          case StreamTokenizer.TT_WORD:
            String word = tokenizer.sval;
            
            if(variable == true && end == false)
            {
                word = "<var" + i + ">";
                i++;
            }

            if(word.equals("int") || word.equals("String") || word.equals("char") || word.equals("float"))
            {
                variable = true;
                end = false;
            }

            //System.out.println(word);
            writer.write(word);
            writer.flush();
            break;
      
          case '"':
            String doublequote = tokenizer.sval;
            //System.out.println(doublequote);
            writer.write(doublequote);
            writer.flush();
            break;
      
          case '\'':
            String singlequote = tokenizer.sval;
            //System.out.println(singlequote);
            writer.write(singlequote);
            writer.flush();
            break;
        
          case StreamTokenizer.TT_EOL:
            break;
          
          case StreamTokenizer.TT_EOF:
            break;

          default:
            char character = (char) tokenizer.ttype;
            if(character == ';')
            {
                end = true;
                variable = false;
            }
            //System.out.println(character);
            writer.write(""+character);
            writer.flush();
            break;
        }
    }
    writer.close();
    fileReader.close();

    System.out.println(">: Archivo: " + name + " Tokenizado en " + name +"_tok.txt");
  }

  public String generateString(String name) throws Exception
  {
    String content = "";
    FileReader fileReader = new FileReader("C://Users//Oscar//Desktop//ESCOM//10mo//Analisis de Algoritmos//Analisis-de-Algoritmos//Practica6//"+ name + "_tok.txt");
    StreamTokenizer tokenizer = new StreamTokenizer(fileReader);

    tokenizer.parseNumbers();
    tokenizer.wordChars('_', '_');
    tokenizer.eolIsSignificant(true);
    tokenizer.ordinaryChars(0, ' ');
    tokenizer.slashSlashComments(true);
    tokenizer.slashStarComments(true);
  
    int tok = tokenizer.nextToken();
  
    while (tok != StreamTokenizer.TT_EOF)
    {
      tok = tokenizer.nextToken();
      switch (tok)
      {
      
          case StreamTokenizer.TT_NUMBER:
            double n = tokenizer.nval;
            //System.out.println(n);
            content = content + n;
            break;
      
          case StreamTokenizer.TT_WORD:
            String word = tokenizer.sval;
            //System.out.println(word);
            content = content + word;
            break;
      
          case '"':
            String doublequote = tokenizer.sval;
            //System.out.println(doublequote);
            content = content + doublequote;
            break;
      
          case '\'':
            String singlequote = tokenizer.sval;
            //System.out.println(singlequote);
            content = content + singlequote;
            break;
        
          case StreamTokenizer.TT_EOL:
            break;
          
          case StreamTokenizer.TT_EOF:
            break;

          default:
            char character = (char) tokenizer.ttype;
            //System.out.println(character);
            content = content + character;
            break;
        }
    }
    fileReader.close();
    return content;
  }

  public String findLengthOfLCS(String str1, String str2, int p, int q)
  {  
    // create a matrix which act as a table for LCS  
    int[][] tableForLCS = new int[p + 1][q + 1];  

    // fill the table in the bottom up way  
    for (int i = 0; i <= p; i++)
    {  
        for (int j = 0; j <= q; j++)
        {  
            if (i == 0 || j == 0)  
                tableForLCS[i][j] = 0;  // Fill each cell corresponding to first row and first column with 0  
            else if (str1.charAt(i - 1) == str2.charAt(j - 1))  
                tableForLCS[i][j] = tableForLCS[i - 1][j - 1] + 1;  // add 1 in the cell of the previous row and column and fill the current cell with it   
            else  
                tableForLCS[i][j] = Math.max(tableForLCS[i - 1][j], tableForLCS[i][j - 1]); //find the maximum value from the cell of the previous row and current column and the cell of the current row and previous column    
        }  
    }  
  
    int index = tableForLCS[p][q];  
    int temp = index;  

    char[] longestCommonSubsequence = new char[index + 1];  
    longestCommonSubsequence[index] = '\0';  

    int i = p, j = q;  
    String lcs ="";  
    while (i > 0 && j > 0)
    {  
        if (str1.charAt(i - 1) == str2.charAt(j - 1))
        {  
              
            longestCommonSubsequence[index - 1] = str1.charAt(i - 1);  
            i--;  
            j--;  
            index--;  
        }  
        else if (tableForLCS[i - 1][j] > tableForLCS[i][j - 1])  
            i--;  
        else  
            j--;  
    }  
      
    for (int k = 0; k <= temp; k++)  
        lcs = lcs + longestCommonSubsequence[k];  
          
    return lcs;  
  }  
}
