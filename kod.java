import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Teapot
{
	private Punkt punkty[][][];
	private FileWriter fileWriter;
	private File fileReader;
	private int iloscNumber;
	
	public Teapot(String fileReader, String fileWriter)
	{
		this.fileReader = new File(fileReader);
		try {
			this.fileWriter = new FileWriter(fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void Wspolrzedne()
	{
	    try {
			Scanner sc = new Scanner(fileReader);
			String ilosc = sc.next();
			iloscNumber = Integer.parseInt(ilosc);
			punkty = new Punkt[iloscNumber][4][4];
			for(int i = 0; i < iloscNumber; i++)
			{
				for(int j = 0; j < 4; j++)
				{
					for(int k = 0; k < 4; k++)
					{
						punkty[i][j][k] = new Punkt(Double.parseDouble(sc.next()), Double.parseDouble(sc.next()), Double.parseDouble(sc.next()));
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}    
	}
	
	public static double factorial(int n)
	{
	    long fact = 1;
	    for (int i = 2; i <= n; i++)
	    {
	        fact = fact * i;
	    }
	    return fact;
	}
	
	public static double Bernstein(int i, int n, double t)
	{
		double fac1 = factorial(n) / (factorial(n - i) * factorial(i));
		double fac2 = Math.pow((1 -t), (n - i));
		double fac3 = Math.pow(t, i);
		return fac1 * fac2 * fac3;
	}
	
	
	
	public void Oblicz() throws IOException
	{
		double punktX;
		double punktY;
		double punktZ;
		
		
		
		try {
			fileWriter.write("x, y, z" + System.lineSeparator());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int i = 0; i < iloscNumber; i++)
		{
			for(double j = 0.0; j <= 1.0; j += 0.03)
			{
				for(double k = 0.0; k <= 1.0; k += 0.03)
				{
					punktX = 0;
					punktY = 0;
					punktZ = 0;
					
					for(int l = 0; l < 4; l++)
					{
						for(int m = 0; m < 4; m++)
						{
							punktX += punkty[i][m][l].x * Bernstein(m, 3, k) * Bernstein(l, 3, j);
							punktY += punkty[i][m][l].y * Bernstein(m, 3, k) * Bernstein(l, 3, j);
							punktZ += punkty[i][m][l].z * Bernstein(m, 3, k) * Bernstein(l, 3, j);
						
						}
					}
						fileWriter.write(punktX + ", " + punktY + ", " + punktZ + System.lineSeparator());
					}
				}
		}
	}
	
	
	
	
	public static void main(String args[]) throws IOException
	{
		Teapot teapot = new Teapot("E:\\Przedmioty\\Grafika i Komunikacja czlowiek-komputer\\Laboratoria 4\\Teapot.txt", "E:\\Przedmioty\\Grafika i Komunikacja czlowiek-komputer\\Laboratoria 4\\TeapotOutPut.txt");
		teapot.Wspolrzedne();
		teapot.Oblicz();

			
		Teapot teacup = new Teapot("E:\\Przedmioty\\Grafika i Komunikacja czlowiek-komputer\\Laboratoria 4\\TeaCup.txt", "E:\\Przedmioty\\Grafika i Komunikacja czlowiek-komputer\\Laboratoria 4\\TeaCupOutPut.txt");
		teacup.Wspolrzedne();
		teacup.Oblicz();
		
		Teapot teaspoon = new Teapot("E:\\Przedmioty\\Grafika i Komunikacja czlowiek-komputer\\Laboratoria 4\\Teaspoon.txt", "E:\\Przedmioty\\Grafika i Komunikacja czlowiek-komputer\\Laboratoria 4\\TeaspoonOutPut.txt");
		teaspoon.Wspolrzedne();
		teaspoon.Oblicz();
	}
	
	
	
	
	// Klasa punkt
	public class Punkt
	{
		public double x = 0;
		public double y = 0;
		public double z = 0;
		
		public Punkt(double x, double y, double z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}
