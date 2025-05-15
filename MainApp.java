import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.server.SocketSecurityException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;

import javax.sound.sampled.SourceDataLine;

public class Main
{
    public static void main(String[] args) 
    {
        int array[] = {9,1,8,2,7,3,6,4,5};

        bubbleSort(array);

        for(int i : array){
            System.out.print(i);
        }
    }
    public static void bubbleSort(int array[]){

        for(int i = 0; i < array.length - 1; i++){
            for(int j = 0; j < array.length - i - 1; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
}
