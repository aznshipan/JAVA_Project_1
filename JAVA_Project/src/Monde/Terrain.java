package Monde;

import Mobs.M;
import Mobs.M1;
import Mobs.M2;

import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;


import javax.imageio.ImageIO;

public class Terrain {
	private static int[][][] terrain;
	private int dx;
	private int dy;
	private  BufferedImage image;
	
	public Terrain(int dx,int dy) {
		try {
			image = ImageIO.read(new File("Bruit_de_Perlin220x220.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dx=dx;
		this.dy=dy;
		terrain = new int[dx][dy][3];
		for (int i=0;i<dx;i++) {
			for (int j=0;j<dy;j++) {
				Color c = new Color(image.getRGB(i, j));
				int couleur = c.getRed();
				terrain[i][j][0]=couleur; //Altitude
				terrain[i][j][1]=couleur; //Type de terrain(biome)
				terrain[i][j][2]=0;
				
				System.out.print(" "+ terrain[i][j][1]);
			}
			System.out.println("");
		}
	}
	public int cpt;
	public void Stockage_passage() {
		for (int i=0;i<Monde.getcarte_Ag().size();i++) {
			if (Monde.getcarte_Ag().get(i) instanceof M1)
				terrain[((M) Monde.getcarte_Ag().get(i)).getX()][((M) Monde.getcarte_Ag().get(i)).getY()][1]-=1;
			if (Monde.getcarte_Ag().get(i) instanceof M2)
				terrain[((M) Monde.getcarte_Ag().get(i)).getX()][((M) Monde.getcarte_Ag().get(i)).getY()][1]+=1;
		}
	}
	public void afficher() {
		for (int i=0;i<dx;i++) {
			for (int j=0;j<dy;j++) {
				System.out.print(""+terrain[i][j]+" ");
			}
			System.out.println("");
		}
	}
	public static int[][][] getTerrain() {
		return terrain;
	}
	public void eruption() {
		for(int i = 0; i < dx ; i++) {
			for(int j = 0; j < dy; j++) {
				if(this.getTerrain()[i][j][0] >=247) { //Pour un haut altitude
					this.getTerrain()[i][j][1] =  255; //Pour debuter ecoulement de lave 
				}	
			}
		}
	}
	public void propagation_lave() {
		for(int i = 0; i < dx ; i++) {
			for(int j = 0; j < dy; j++) {
				if(this.getTerrain()[i][j][0] >= 237 && (this.getTerrain()[i-1][j][1] == 255 || this.getTerrain()[i+1][j][1] == 255|| this.getTerrain()[i][j+1][1] == 255|| this.getTerrain()[i][j-1][1] == 255 )) {
					if(Math.random() <= 0.05) {
						this.getTerrain()[i][j][1] = 255; //coulé de lave
					}
				}
			}
		}
	}
	public void partir_lave() {
		for(int i = 0; i < dx ; i++) {
			for(int j = 0; j < dy; j++) {
				if(this.getTerrain()[i][j][1] == 255 && this.getTerrain()[i][j][0] < 240) {
					this.getTerrain()[i][j][1]=207; //devient de la terre
				}
				else {
					if(this.getTerrain()[i][j][1] == 255 && this.getTerrain()[i][j][0] >= 239) {	
						this.getTerrain()[i][j][1]=this.getTerrain()[i][j][0];
					}
				}
			}
		}
	}
	
}