package Sprite;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Mobs.Arbre;
import Mobs.Braconnier;
import Mobs.M1;
import Mobs.M2;
import Mobs.Pomme;
import Monde.Monde;

public class SpriteDemo extends JPanel {


	private JFrame frame;
	
	private Image waterSprite;
	private Image grassSprite;
	private Image treeSprite;
	private Image tSprite;
	private Image PokemonFeu;
	private Image PokemonFeuEvolue;
	private Image[][] PokemonFeuMove;
	private Image[][] PokemonFeuEvolueMove;
	private Image PokemonEau;
	private Image PokemonEauEvolue;
	private Image[][] PokemonEauMove;
	private Image Apple;
	private Image ApplePourri;
	private Image Chasseur;
	public static int dx;
	public static int dy;
	private int spriteLength = 40;
	private static int pas = 0;
	private static int marcher = 0;
	private static int cpt_pas = 0;
	private static M1 Hericendre;
	private static M2 Carapuce;
	private static int step;

	public SpriteDemo()
	{
		try
		{
			waterSprite = ImageIO.read(new File("water.png"));
			treeSprite = ImageIO.read(new File("arbref.png"));
			grassSprite = ImageIO.read(new File("grass.png"));
			tSprite = ImageIO.read(new File("tree.png"));
			PokemonFeu = ImageIO.read(new File("hericendre.png"));
			PokemonFeuEvolue = ImageIO.read(new File("FeurissonTrans.png")); 
			PokemonEau = ImageIO.read(new File("carapuce.png"));
			PokemonEauEvolue = ImageIO.read(new File("CarabaffeTrans.png")); 
			Apple = ImageIO.read(new File("pomme.png"));
			ApplePourri = ImageIO.read(new File("pommeP.png"));
			Chasseur = ImageIO.read(new File("chasseur.png"));
			
			PokemonFeuMove = new Image[4][8]; //Hericendre
			PokemonFeuMove[0][0] = ImageIO.read(new File("Hericendre_walkdown1.png"));
			PokemonFeuMove[0][1] = ImageIO.read(new File("Hericendre_walkdown2.png"));  //Deplacement vers le bas
			PokemonFeuMove[0][2] = ImageIO.read(new File("Hericendre_walkdown3.png"));
			PokemonFeuMove[0][3] = ImageIO.read(new File("Hericendre_walkdown4.png"));
			PokemonFeuMove[0][4] = ImageIO.read(new File("Hericendre_walkdown1.png"));
			PokemonFeuMove[0][5] = ImageIO.read(new File("Hericendre_walkdown2.png"));  //Deplacement vers le bas
			PokemonFeuMove[0][6] = ImageIO.read(new File("Hericendre_walkdown3.png"));
			PokemonFeuMove[0][7] = ImageIO.read(new File("Hericendre_walkdown4.png"));
			
			PokemonFeuMove[1][0] = ImageIO.read(new File("Hericendre_walkup1.png"));
			PokemonFeuMove[1][1] = ImageIO.read(new File("Hericendre_walkup2.png"));  //Deplacement vers le haut
			PokemonFeuMove[1][2] = ImageIO.read(new File("Hericendre_walkup3.png"));
			PokemonFeuMove[1][3] = ImageIO.read(new File("Hericendre_walkup4.png"));
			PokemonFeuMove[1][4] = ImageIO.read(new File("Hericendre_walkup1.png"));
			PokemonFeuMove[1][5] = ImageIO.read(new File("Hericendre_walkup2.png"));  //Deplacement vers le haut
			PokemonFeuMove[1][6] = ImageIO.read(new File("Hericendre_walkup3.png"));
			PokemonFeuMove[1][7] = ImageIO.read(new File("Hericendre_walkup4.png"));
			
			PokemonFeuMove[2][0] = ImageIO.read(new File("Hericendre_walkleft1.png"));
			PokemonFeuMove[2][1] = ImageIO.read(new File("Hericendre_walkleft2.png"));  //Deplacement vers la gauche
			PokemonFeuMove[2][2] = ImageIO.read(new File("Hericendre_walkleft3.png"));
			PokemonFeuMove[2][3] = ImageIO.read(new File("Hericendre_walkleft4.png"));
			PokemonFeuMove[2][4] = ImageIO.read(new File("Hericendre_walkleft1.png"));
			PokemonFeuMove[2][5] = ImageIO.read(new File("Hericendre_walkleft2.png"));  //Deplacement vers la gauche
			PokemonFeuMove[2][6] = ImageIO.read(new File("Hericendre_walkleft3.png"));
			PokemonFeuMove[2][7] = ImageIO.read(new File("Hericendre_walkleft4.png"));
			
			PokemonFeuMove[3][0] = ImageIO.read(new File("Hericendre_walkright1.png"));
			PokemonFeuMove[3][1] = ImageIO.read(new File("Hericendre_walkright2.png"));  //Deplacement vers la droite
			PokemonFeuMove[3][2] = ImageIO.read(new File("Hericendre_walkright3.png"));
			PokemonFeuMove[3][3] = ImageIO.read(new File("Hericendre_walkright4.png"));
			PokemonFeuMove[3][4] = ImageIO.read(new File("Hericendre_walkright1.png"));
			PokemonFeuMove[3][5] = ImageIO.read(new File("Hericendre_walkright2.png"));  //Deplacement vers la droite
			PokemonFeuMove[3][6] = ImageIO.read(new File("Hericendre_walkright3.png"));
			PokemonFeuMove[3][7] = ImageIO.read(new File("Hericendre_walkright4.png"));
			
			
			
			PokemonFeuEvolueMove = new Image[4][8]; //Feurisson
			PokemonFeuEvolueMove[0][0] = ImageIO.read(new File("Feurisson_walkdown1.png"));
			PokemonFeuEvolueMove[0][1] = ImageIO.read(new File("Feurisson_walkdown2.png"));
			PokemonFeuEvolueMove[0][2] = ImageIO.read(new File("Feurisson_walkdown3.png"));
			PokemonFeuEvolueMove[0][3] = ImageIO.read(new File("Feurisson_walkdown4.png")); //deplacement vers le bas
			PokemonFeuEvolueMove[0][4] = ImageIO.read(new File("Feurisson_walkdown1.png"));
			PokemonFeuEvolueMove[0][5] = ImageIO.read(new File("Feurisson_walkdown2.png"));
			PokemonFeuEvolueMove[0][6] = ImageIO.read(new File("Feurisson_walkdown3.png"));
			PokemonFeuEvolueMove[0][7] = ImageIO.read(new File("Feurisson_walkdown4.png"));
			
			PokemonFeuEvolueMove[1][0] = ImageIO.read(new File("Feurisson_walkup1.png"));
			PokemonFeuEvolueMove[1][1] = ImageIO.read(new File("Feurisson_walkup2.png"));
			PokemonFeuEvolueMove[1][2] = ImageIO.read(new File("Feurisson_walkup3.png"));
			PokemonFeuEvolueMove[1][3] = ImageIO.read(new File("Feurisson_walkup4.png")); //deplacement vers le haut
			PokemonFeuEvolueMove[1][4] = ImageIO.read(new File("Feurisson_walkup1.png"));
			PokemonFeuEvolueMove[1][5] = ImageIO.read(new File("Feurisson_walkup2.png"));
			PokemonFeuEvolueMove[1][6] = ImageIO.read(new File("Feurisson_walkup3.png"));
			PokemonFeuEvolueMove[1][7] = ImageIO.read(new File("Feurisson_walkup4.png"));
			
			PokemonFeuEvolueMove[2][0] = ImageIO.read(new File("Feurisson_walkleft1.png"));
			PokemonFeuEvolueMove[2][1] = ImageIO.read(new File("Feurisson_walkleft2.png"));
			PokemonFeuEvolueMove[2][2] = ImageIO.read(new File("Feurisson_walkleft3.png"));
			PokemonFeuEvolueMove[2][3] = ImageIO.read(new File("Feurisson_walkleft4.png")); //deplacement vers la gauche
			PokemonFeuEvolueMove[2][4] = ImageIO.read(new File("Feurisson_walkleft1.png"));
			PokemonFeuEvolueMove[2][5] = ImageIO.read(new File("Feurisson_walkleft2.png"));
			PokemonFeuEvolueMove[2][6] = ImageIO.read(new File("Feurisson_walkleft3.png"));
			PokemonFeuEvolueMove[2][7] = ImageIO.read(new File("Feurisson_walkleft4.png"));
			
			PokemonFeuEvolueMove[3][0] = ImageIO.read(new File("Feurisson_walkright1.png"));
			PokemonFeuEvolueMove[3][1] = ImageIO.read(new File("Feurisson_walkright2.png"));
			PokemonFeuEvolueMove[3][2] = ImageIO.read(new File("Feurisson_walkright3.png"));
			PokemonFeuEvolueMove[3][3] = ImageIO.read(new File("Feurisson_walkright4.png")); //deplacement vers la droite
			PokemonFeuEvolueMove[3][4] = ImageIO.read(new File("Feurisson_walkright1.png"));
			PokemonFeuEvolueMove[3][5] = ImageIO.read(new File("Feurisson_walkright2.png"));
			PokemonFeuEvolueMove[3][6] = ImageIO.read(new File("Feurisson_walkright3.png"));
			PokemonFeuEvolueMove[3][7] = ImageIO.read(new File("Feurisson_walkright4.png"));
			
			
			
			PokemonEauMove = new Image[4][8];  //carapuce
			PokemonEauMove[0][0] = ImageIO.read(new File("Carapuce_walkdown1.png"));
			PokemonEauMove[0][1] = ImageIO.read(new File("Carapuce_walkdown2.png"));
			PokemonEauMove[0][2] = ImageIO.read(new File("Carapuce_walkdown3.png"));
			PokemonEauMove[0][3] = ImageIO.read(new File("Carapuce_walkdown4.png"));
			PokemonEauMove[0][4] = ImageIO.read(new File("Carapuce_walkdown1.png"));  //deplacement vers le bas
			PokemonEauMove[0][5] = ImageIO.read(new File("Carapuce_walkdown2.png"));
			PokemonEauMove[0][6] = ImageIO.read(new File("Carapuce_walkdown3.png"));
			PokemonEauMove[0][7] = ImageIO.read(new File("Carapuce_walkdown4.png"));
			
			PokemonEauMove[1][0] = ImageIO.read(new File("Carapuce_walkup1.png"));
			PokemonEauMove[1][1] = ImageIO.read(new File("Carapuce_walkup2.png"));
			PokemonEauMove[1][2] = ImageIO.read(new File("Carapuce_walkup3.png"));
			PokemonEauMove[1][3] = ImageIO.read(new File("Carapuce_walkup4.png"));
			PokemonEauMove[1][4] = ImageIO.read(new File("Carapuce_walkup1.png"));  //deplacement vers le haut
			PokemonEauMove[1][5] = ImageIO.read(new File("Carapuce_walkup2.png"));
			PokemonEauMove[1][6] = ImageIO.read(new File("Carapuce_walkup3.png"));
			PokemonEauMove[1][7] = ImageIO.read(new File("Carapuce_walkup4.png"));
			
			PokemonEauMove[2][0] = ImageIO.read(new File("Carapuce_walkleft1.png"));
			PokemonEauMove[2][1] = ImageIO.read(new File("Carapuce_walkleft2.png"));
			PokemonEauMove[2][2] = ImageIO.read(new File("Carapuce_walkleft3.png"));
			PokemonEauMove[2][3] = ImageIO.read(new File("Carapuce_walkleft4.png"));
			PokemonEauMove[2][4] = ImageIO.read(new File("Carapuce_walkleft1.png"));  //deplacement vers la gauche
			PokemonEauMove[2][5] = ImageIO.read(new File("Carapuce_walkleft2.png"));
			PokemonEauMove[2][6] = ImageIO.read(new File("Carapuce_walkleft3.png"));
			PokemonEauMove[2][7] = ImageIO.read(new File("Carapuce_walkleft4.png"));
			
			PokemonEauMove[3][0] = ImageIO.read(new File("Carapuce_walkright1.png"));
			PokemonEauMove[3][1] = ImageIO.read(new File("Carapuce_walkright2.png"));
			PokemonEauMove[3][2] = ImageIO.read(new File("Carapuce_walkright3.png"));
			PokemonEauMove[3][3] = ImageIO.read(new File("Carapuce_walkright4.png"));
			PokemonEauMove[3][4] = ImageIO.read(new File("Carapuce_walkright1.png"));  //deplacement vers la droite
			PokemonEauMove[3][5] = ImageIO.read(new File("Carapuce_walkright2.png"));
			PokemonEauMove[3][6] = ImageIO.read(new File("Carapuce_walkright3.png"));
			PokemonEauMove[3][7] = ImageIO.read(new File("Carapuce_walkright4.png"));
			
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

		frame = new JFrame("World of Sprite");
		frame.add(this);
		frame.setSize(1200,665);
		frame.setVisible(true);
	}

	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		for ( int i1 = 0 ; i1 < dx ; i1++ ) {
			for ( int j1 = 0 ; j1 < dy ; j1++ ) {
					g2.drawImage(grassSprite,spriteLength*i1,spriteLength*j1,spriteLength,spriteLength, frame);
				try {
					if (Monde.testC(i1, j1) instanceof Arbre) {
						g2.drawImage(tSprite,spriteLength*i1,spriteLength*j1-30,spriteLength,spriteLength+30, frame);
					}
					if (Monde.testC(i1, j1)instanceof Pomme) {
						g2.drawImage(grassSprite,spriteLength*i1,spriteLength*j1,spriteLength,spriteLength, frame);
						if ((((Pomme) Monde.testC(i1, j1)).isEstPourrie() == false))
							g2.drawImage(Apple,spriteLength*i1,spriteLength*j1,spriteLength-10,spriteLength-10, frame);
						if ((((Pomme) Monde.testC(i1, j1)).isEstPourrie() == true))
							g2.drawImage(ApplePourri,spriteLength*i1,spriteLength*j1,spriteLength-10,spriteLength-10, frame);
					}
					}catch(Exception e) {
						
					}
					
				}
		}
		for ( int i = 0 ; i < dx ; i++ )
			for ( int j = 0 ; j < dy ; j++ )
			{
				try {
						if (Monde.testC(i, j) instanceof M1) {
							Hericendre = (M1)(Monde.testC(i, j));
								
							if(cpt_pas % 8 == 0) {
								Hericendre.setSens();
								}
							if(Hericendre.getEvolution() == false) {
							
								if ( Hericendre.getSens() == 0 ) { //va a gauche
									g2.drawImage(PokemonFeuMove[2][pas],spriteLength*i - this.marcher,spriteLength*j,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 1 ) { //va a droite
									g2.drawImage(PokemonFeuMove[3][pas],spriteLength*i + this.marcher,spriteLength*j,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 2 ) { //va en bas
									g2.drawImage(PokemonFeuMove[0][pas],spriteLength*i ,spriteLength*j + this.marcher,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 3 ) { //va en haut
									g2.drawImage(PokemonFeuMove[1][pas],spriteLength*i ,spriteLength*j - this.marcher,spriteLength,spriteLength, frame);
								}
							}
							else {
								
								if ( Hericendre.getSens() == 0 ) { //va a gauche
									g2.drawImage(PokemonFeuEvolueMove[2][pas],spriteLength*i - this.marcher,spriteLength*j,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 1 ) { //va a droite
									g2.drawImage(PokemonFeuEvolueMove[3][pas],spriteLength*i + this.marcher,spriteLength*j,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 2 ) { //va en bas
									g2.drawImage(PokemonFeuEvolueMove[0][pas],spriteLength*i  ,spriteLength*j + this.marcher,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 3 ) { //va en haut
									g2.drawImage(PokemonFeuEvolueMove[1][pas],spriteLength*i ,spriteLength*j - this.marcher,spriteLength,spriteLength, frame);
								}
							}
							
							
							
						}
					/*	if (Monde.testC(i, j) instanceof M2) {
							if (((M2) Monde.testC(i, j)).getNb_evolution() == 0)
								g2.drawImage(PokemonEau,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
							if (((M2) Monde.testC(i, j)).getNb_evolution() == 1)
								g2.drawImage(PokemonEauEvolue,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						}
						if (Monde.testC(i, j) instanceof Arbre) {
							g2.drawImage(tSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						}
						if (Monde.testC(i, j)instanceof Pomme) {
							g2.drawImage(grassSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
							if ((((Pomme) Monde.testC(i, j)).isEstPourrie() == false))
								g2.drawImage(Apple,spriteLength*i,spriteLength*j,spriteLength-10,spriteLength-10, frame);
							if ((((Pomme) Monde.testC(i, j)).isEstPourrie() == true))
								g2.drawImage(ApplePourri,spriteLength*i,spriteLength*j,spriteLength-10,spriteLength-10, frame);
						}
						if (Monde.testC(i, j) instanceof Braconnier)
							g2.drawImage(Chasseur,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);*/

				}catch(Exception E) {
				}
			}
	
	for ( int i = 0 ; i < dx ; i++) {
		for ( int j = 0 ; j < dy ; j++ )
		{
			if (Monde.testC(i, j) instanceof Arbre) {
				g2.drawImage(tSprite,spriteLength*i,spriteLength*j-30,spriteLength,spriteLength+30, frame);
			}
		}
	}
}

	public static void main(String[] args) {
		
		Monde monde = new Monde(dx=30,dy=15,50);
		SpriteDemo a =new SpriteDemo();
		cpt_pas = 0;
		marcher = 0;
		step = 0;
		while(true) {
			if(pas < 7) {
				a.repaint();
	            pas += 1;
			}
			else {
				a.repaint();
				pas = 0;
			}
			if(cpt_pas % 8 == 0) {
				monde.pomme_pop(step);
				Pomme.duree();
				Pomme.delete();
				monde.Refresh();
				cpt_pas = 0;
				marcher = 0;
			}
			marcher += 5 ;
			//Braconnier.chasser();
			try{
				Thread.sleep(10); // en ms
			}catch(Exception e){
				e.printStackTrace();
			}
			cpt_pas += 1;
			step++;
		}
	}
}
