package Lista1Z1;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		/*
		 * ROZWIĄZANIE: Wojewodzki o Krakowie: "Prowincja"
		 * "magic plejs pomimo że prowincja" - napisał pod zdjęciem z Krakowa.
		 * 
		 */
		int liczbaKryptogramow = 20;
		String kryptogram[] = new String[liczbaKryptogramow];
		String doZdeszyfrowania = "10001101 00001000 01001001 00001011 11011110 01001111 00101001 11011110 01100101 10011110 10011111 00001011 11100000 10011011 10110001 10110110 10000100 10000010 11111000 01001100 00111101 11001110 11011100 00001101 10000011 10111011 00111010 11010001 11101110 00111101 11111010 00010010 10111000 01011001 01100010 11010100 00000110 00001011 11111110 00010011 10011111 10101110 01110001 11000101 10110001 10110110 10011111 10111110 00010000 01111110 11100010 10000000 01101010 01000001 10101110 01001111 10111011 10110010 01010000 00011000 11110110 10011000 01000001 00011001 11101100 11110111 10011100 10100100 00011010 00111100 11111101 10101010 01001001 10010011 00101101 10010110 10111100 10010011 10000100 11101101 00010001 00010111 10111111 10001101 01110001 00111100 10011111 01001100 10010110 11110101 00100110 10001101 01010011 01111100 11000001 11110101 00101101 01110000 00001111 11001011 01001110 10110110 00000011 ";
		ArrayList<ArrayList<Integer>> przewidywaneKlucze = new ArrayList<ArrayList<Integer>>();

		/*
		 * Wczytywanie kryptogramów
		 */
		for (int i = 0; i < liczbaKryptogramow; i++) {
			String fileName = "kryptogram" + (i + 1) + ".txt";
			File file = new File(fileName);
			Scanner in = new Scanner(file);
			do {
				kryptogram[i] = in.nextLine();
			} while (in.hasNextLine());
			kryptogram[i] = kryptogram[i].replace(" ", "");
		}
		doZdeszyfrowania = doZdeszyfrowania.replace(" ", "");

		//Wyszukiwanie znaków mozliwych w kluczu na poszczegolnych pozycjach	
		for (int k = 0; k < doZdeszyfrowania.length() / 8; k++) {
			przewidywaneKlucze.add(new ArrayList<Integer>());
			for (int i = 0; i < 255; i++) {
				int wIlu = 0; //W ilu kryptogramach znajduje sie znak który może być kluczem
				for (int lista = 0; lista < liczbaKryptogramow; lista++) {
					int a = i ^ Integer.parseInt(kryptogram[lista].substring(k * 8, (k * 8) + 8), 2);
					if (nalezyDoTekstu(a)) {
						wIlu++;
					} else {
						break;
					}

				}		
				if (wIlu == liczbaKryptogramow) {			
					przewidywaneKlucze.get(k).add(i);
				}
			}
		}
//Wyświetlanie wszystkich mozlowosci klucza
		int k = 0;
		double  temp = 0;
		double  komb = 1;
		for (int d = 0; d < doZdeszyfrowania.length() / 8; d++) {
			temp = 0;
			for (int i = 0; i < przewidywaneKlucze.get(k).size(); i++) {
				int a = Integer.parseInt(doZdeszyfrowania.substring(d * 8, (d * 8) + 8), 2)
						^ (int) przewidywaneKlucze.get(k).get(i);
				if (nalezyDoTekstu(a)) {
					przewidywaneKlucze.get(k).set(i,a);
					System.out.print("|" + (char) a + "|");
					temp++;
				}
			}
			System.out.println();
			System.out.println(komb *= temp);
			k++;
		}
		
	}

	public static boolean nalezyDoTekstu(int znak) {

		if (44 <= znak && znak <= 59) {
			return true;
		} else if (63 <= znak && znak <= 90) {
			return true;
		} else if (97 <= znak && znak <= 122) {
			return true;
		} else if (32 <= znak && znak <= 34) {
			return true;
		} else {
			return false;
		}
	}
	
	
}