package Monde;

import java.util.ArrayList;

import Mobs.Arbre;
import Mobs.Braconnier;
import Mobs.M;
import Mobs.M1;
import Mobs.M2;
import Mobs.Pomme;

public class Monde {
	private static int dx;
	private static int dy;
	private static ArrayList<Object> carte_Ag =new ArrayList<>();
	private static ArrayList<Arbre> carte_Ab=new ArrayList<>();
	private static ArrayList<Pomme> carte_P=new ArrayList<>();
	private static int direction;
	private int cpt = 0;
	
	
	public static ArrayList<Object> getcarte_Ag() {
		return carte_Ag;
	}
	public static ArrayList<Arbre> getcarte_Ab() {
		return carte_Ab;
	}
	public static ArrayList<Pomme> getcarte_P() {
		return carte_P;
	}

	public Monde(int x, int y, double taux_agent,double percolation_Ab) {//Initialisation de la liste des agents à mettre dans le monde
		dx=x;
		dy=y;
		for (int i=0;i<dx;i++) {
			for(int j=0;j<dy;j++) {
					if(Terrain.getTerrain()[i][j][1] > 10) {
						if(Math.random() < percolation_Ab) {
						Terrain.getTerrain()[i][j][2]=1;
						Arbre arbres = new Arbre(i, j);
						carte_Ab.add(arbres);
					}
				}
			}
		}
		for (int i=0;i<dx;i++) {
			for(int j=0;j<dy;j++) {
					if(Terrain.getTerrain()[i][j][1] > 10 && Terrain.getTerrain()[i][j][2]==0) {
						if(Math.random() < taux_agent) {
						M1 monstre = new M1(i, j);
						carte_Ag.add(monstre);
					}
				}
			}
		}
		/*for (int i=0;i<nb_Ag;i++) {
			double p1 =  Math.random();
			if (p1 <= 0.99) {
				int x1= (int) (Math.random()*dx);
				int y1 =(int) (Math.random()*dy);
				if(Terrain.getTerrain()[x1][y1][1] > 10 && Terrain.getTerrain()[x1][y1][2] == 0) {
					M1 monstre = new M1(x1, y1);
					carte_Ag.add(monstre);
				}
				else {
					i--;
				}
			}else {
				int x1= (int) (Math.random()*dx);
				int y1 =(int) (Math.random()*dy);
				M2 monstre = new M2(x1, y1);
				carte_Ag.add(monstre);
			}
		}*/
		/*
		carte_Ag.add(new M1(10,10));
		carte_Ab.add(new Arbre(11,10));
		carte_Ab.add(new Arbre(9,10));
		carte_Ab.add(new Arbre(10,9));
		carte_Ab.add(new Arbre(10,11));*/
		/*for (int i=0;i<carte_Ag.size();i++) {
			if (carte_Ag.get(i) instanceof M1)	
				((M1) carte_Ag.get(i)).setSens();
		}*/
		//carte_Ag.add(new M1(6,6));
		//carte_Ag.add(new M2(5,5));
	}
	
	public void pomme_pop(int cpt) { //fait apparaitre des pomme sur la carte_A
		if (cpt % 2 == 0) {
			int x1,y1;
			boolean bool_A;
			do {
				bool_A=false;
				x1= (int) (Math.random()*Monde.getDx());
				y1 =(int) (Math.random()*Monde.getDy());
				if(Terrain.getTerrain()[x1][y1][1] <= 10) {
					bool_A=true;
					break;
				}
				for (int i=0;i<Monde.getcarte_Ab().size();i++) {
					if (Monde.getcarte_Ab().get(i).getX() == x1 && Monde.getcarte_Ab().get(i).getY() == y1) {
						bool_A=true;
						break;
					}
				}
			}while(bool_A);
			if(Terrain.getTerrain()[x1][y1][1] > 10) {
				Pomme apple = new Pomme(x1, y1);
				carte_P.add(apple);
			}else {
				pomme_pop(cpt);
			}
		}
	}
	public static int compteM() {
		int cpt=0;
		for (int i=0;i<carte_Ag.size();i++) {
			//System.out.println(""+carte_A.get(i).getClass());
			if (carte_Ag.get(i) instanceof M)
				cpt+=1;
		}
		return cpt;
	
	}
	public static void detail() {
		System.out.println("Taille :"+carte_Ag.size());//pour debuger lors des erreurs
		for (int i=0;i<carte_Ag.size();i++) {
			System.out.println(""+carte_Ag.get(i).getClass());
			/*
			if (carte_A.get(i) instanceof M)
				System.out.println(""+carte_A.get(i).getClass()+ " x="+(((M)carte_A.get(i)).getX())+ " y="+(((M)carte_A.get(i)).getY()));
			if (carte_A.get(i) instanceof Arbre)
				System.out.println(""+carte_A.get(i).getClass()+ " x="+(((Arbre)carte_A.get(i)).getX())+ " y="+(((Arbre)carte_A.get(i)).getY()));
			*/
		}
	}
	
	public void Refresh() {
		for (int i=0;i<carte_Ag.size();i++) {
			if(i<carte_Ag.size()) {
				if (carte_Ag.get(i) instanceof M1) {
					if(Terrain.getTerrain()[((M1)carte_Ag.get(i)).getX()][((M1)carte_Ag.get(i)).getY()][1] <= 10) {
						carte_Ag.remove(i);
					}
				}
			}
		}
		for (int i=0;i<carte_Ag.size();i++) {
			if(i<carte_Ag.size()) {
				if (carte_Ag.get(i) instanceof M) {
					if(((M)carte_Ag.get(i)).getSens() >= 0 && ((M)carte_Ag.get(i)).getSens() <= 3) {
						((M) carte_Ag.get(i)).move(dx, dy);
					}
					else {
					}
					((M) carte_Ag.get(i)).setSens();
				}
				/*if (carte_Ag.get(i) instanceof Braconnier) {
					((Braconnier) carte_Ag.get(i)).move(dx, dy);
				}*/
			}
		}
		for (int i=0;i<carte_Ab.size();i++) {
			if(i<carte_Ab.size()) {
				if (carte_Ab.get(i).isEnfeu()) {	
					int x = carte_Ab.get(i).getX();
					int y = carte_Ab.get(i).getY();
					Terrain.getTerrain()[x][y][2] = 0;
					carte_Ab.remove(i);
					
				}
			}
		}
		for (int i=0;i<carte_P.size();i++) {
			((Pomme) carte_P.get(i)).pourrir();
		}
	}
	
	public static void grandir() {
		for (int i=0;i<carte_Ag.size();i++) {
			if (carte_Ag.get(i) instanceof M)
				((M) carte_Ag.get(i)).setStep();
		}
	}
	
	public void depart_feu() {
		int cpt;
		for (int i=0;i<carte_Ab.size();i++) {
			for (int j=0;j<carte_Ag.size();j++) {
				cpt=0;
				if (carte_Ag.get(j) instanceof M1 && ((M) carte_Ag.get(j)).getX()==((Arbre) carte_Ab.get(i)).getX()-1 && ((M) carte_Ag.get(j)).getY()==((Arbre) carte_Ab.get(i)).getY())
					cpt+=1;
				if (carte_Ag.get(j) instanceof M1 && ((M) carte_Ag.get(j)).getX()==((Arbre) carte_Ab.get(i)).getX()+1 && ((M) carte_Ag.get(j)).getY()==((Arbre) carte_Ab.get(i)).getY())
					cpt+=1;
				if (carte_Ag.get(j) instanceof M1 && ((M) carte_Ag.get(j)).getX()==((Arbre) carte_Ab.get(i)).getX() && ((M) carte_Ag.get(j)).getY()==((Arbre) carte_Ab.get(i)).getY()+1)
					cpt+=1;
				if (carte_Ag.get(j) instanceof M1 && ((M) carte_Ag.get(j)).getX()==((Arbre) carte_Ab.get(i)).getX() && ((M) carte_Ag.get(j)).getY()==((Arbre) carte_Ab.get(i)).getY()-1)
					cpt+=1;
				if (cpt ==1)// && Math.random()<0.05)
					((Arbre) carte_Ab.get(i)).setEnfeu(true);
				if (cpt ==2)// && Math.random()<0.01)
					((Arbre) carte_Ab.get(i)).setEnfeu(true);
				if (cpt ==3)// && Math.random()<0.15)
					((Arbre) carte_Ab.get(i)).setEnfeu(true);
				if (cpt ==4)// && Math.random()<0.20)
					((Arbre) carte_Ab.get(i)).setEnfeu(true);
			}
						
		}
	}
	
	public void propagation_F() {
		//System.out.println(""+carte_Ag_depart.toString());
		//System.out.println(""+carte_Ag.toString());
		
		int a;
		for (int i=0;i<carte_Ab.size();i++) {
			if (((Arbre) carte_Ab.get(i)).isEnfeu()) {
				for (int j=0;j<carte_Ab.size();j++) {
					if (((Arbre) carte_Ab.get(j)).getX()==((Arbre) carte_Ab.get(i)).getX()-1 && ((Arbre) carte_Ab.get(j)).getY()==((Arbre) carte_Ab.get(i)).getY())
						((Arbre) carte_Ab.get(j)).setBrulé(true);
					
					if (((Arbre) carte_Ab.get(j)).getX()==((Arbre) carte_Ab.get(i)).getX()+1 && ((Arbre) carte_Ab.get(j)).getY()==((Arbre) carte_Ab.get(i)).getY())
						((Arbre) carte_Ab.get(j)).setBrulé(true);
					
					if (((Arbre) carte_Ab.get(j)).getX()==((Arbre) carte_Ab.get(i)).getX() && ((Arbre) carte_Ab.get(j)).getY()==((Arbre) carte_Ab.get(i)).getY()+1)
						((Arbre) carte_Ab.get(j)).setBrulé(true);
					
					if (((Arbre) carte_Ab.get(j)).getX()==((Arbre) carte_Ab.get(i)).getX() && ((Arbre) carte_Ab.get(j)).getY()==((Arbre) carte_Ab.get(i)).getY()-1)
						((Arbre) carte_Ab.get(j)).setBrulé(true);
				}
			}
		}
		
	}
	
	public void enfeu() {
		for (int i=0;i<carte_Ab.size();i++) {
			if (((Arbre) carte_Ab.get(i)).isBrulé()) {
				((Arbre) carte_Ab.get(i)).setEnfeu(true);
			}
		}
	}
	
	public static int getDirection() {
		return direction;
	}
	

	public static int getDx() {
		return dx;
	}

	public static int getDy() {
		return dy;
	}
	
}