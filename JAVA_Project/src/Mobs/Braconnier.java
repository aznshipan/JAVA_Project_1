package Mobs;

import Monde.Monde;

public class Braconnier {
	private int x;
	private int y;
	private int sens;
	public Braconnier(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void move(int dx, int dy) {
		if (this.sens == 0) {
			this.x=(this.x-1+dx)%dx;
		}
		if (this.sens == 1) {
			this.x=(this.x+1+dx)%dx;
		}
		if (this.sens == 2) {
			this.y=(this.y+1+dy)%dy;
		}
		if (this.sens == 3) {
			this.y=(this.y-1+dy)%dy;
		}
		chasser();
	}
	
	public void setSens() {
		if (Monde.testC((this.x-1+Monde.getDx())%Monde.getDx(), this.y, Mobs.Arbre.class).size() != 0 && 
				Monde.testC((this.x+1+Monde.getDx())%Monde.getDx(), this.y, Mobs.Arbre.class).size() != 0
				&& Monde.testC(this.x, (this.y-1+Monde.getDy())%Monde.getDy(), Mobs.Arbre.class).size() != 0
				&& Monde.testC(this.x, (this.y+1+Monde.getDy())%Monde.getDy(), Mobs.Arbre.class).size() != 0) {
			
			Monde.getCarte().remove(this);
			return ;
		}
		
		for(int m=  0; m < Monde.getCarte().size();m++) {
			if((Monde.getCarte().get(m) instanceof M && ((M) Monde.getCarte().get(m)).getX() == this.x-1 && ((M) Monde.getCarte().get(m)).getY() == this.y) ||
					(Monde.getCarte().get(m) instanceof M && ((M) Monde.getCarte().get(m)).getX() == this.x+1 && ((M) Monde.getCarte().get(m)).getY() == this.y) ||
					(Monde.getCarte().get(m) instanceof M && ((M) Monde.getCarte().get(m)).getX() == this.x && ((M) Monde.getCarte().get(m)).getY() == this.y+1) ||
					(Monde.getCarte().get(m) instanceof M && ((M) Monde.getCarte().get(m)).getX() == this.x && ((M) Monde.getCarte().get(m)).getY() == this.y-1)) {
				
				if(Monde.getCarte().get(m) instanceof M && ((M) Monde.getCarte().get(m)).getX() == this.x-1 && ((M) Monde.getCarte().get(m)).getY() == this.y) {
					this.sens = ((M)Monde.getCarte().get(m)).getSens();
					return ;
				}
				if(Monde.getCarte().get(m) instanceof M && ((M) Monde.getCarte().get(m)).getX() == this.x+1 && ((M) Monde.getCarte().get(m)).getY() == this.y) {
					this.sens = ((M)Monde.getCarte().get(m)).getSens();
					return ;
				}
				if(Monde.getCarte().get(m) instanceof M && ((M) Monde.getCarte().get(m)).getX() == this.x && ((M) Monde.getCarte().get(m)).getY() == this.y+1) {
					this.sens = ((M)Monde.getCarte().get(m)).getSens();
					return ;
				}
				if(Monde.getCarte().get(m) instanceof M && ((M) Monde.getCarte().get(m)).getX() == this.x && ((M) Monde.getCarte().get(m)).getY() == this.y-1) {
					this.sens = ((M)Monde.getCarte().get(m)).getSens();
					return ;
				}
			}
		}
		int x1,y1;
		do {
			this.sens = (int)(Math.random()*4);
			x1=this.x;
			y1=this.y;
			if (this.sens == 0) {
				x1=(x1-1+Monde.getDx())%Monde.getDx();
			}
			if (this.sens == 1) {
				x1=(x1+1+Monde.getDx())%Monde.getDx();
			}
			if (this.sens == 2) {
				y1=(y1+1+Monde.getDy())%Monde.getDy();
			}
			if (this.sens == 3) {
				y1=(y1-1+Monde.getDy())%Monde.getDy();
			}
		}while(Monde.testC(x1, y1,Mobs.Arbre.class).size() ==1);
	}
	
	public int getSens() {
		return this.sens;
	}
	
	public static void chasser() {
		for (int c=0;c<Monde.getCarte().size();c++) {

			if (Monde.getCarte().get(c) instanceof Braconnier) {
				for(int i = ((Braconnier)Monde.getCarte().get(c)).getX() - 1; i <= ((Braconnier)Monde.getCarte().get(c)).getX() + 1; i++) { //Parcours voisin selon voisinage de Moore
					for(int j = ((Braconnier)Monde.getCarte().get(c)).getY() - 1; j <= ((Braconnier)Monde.getCarte().get(c)).getY() + 1; j++) {
						for(int m=0; m < Monde.getCarte().size();m++) {
							if (Monde.getCarte().get(m) instanceof M1 && ((M1) Monde.getCarte().get(m)).getX() == i && ((M1) Monde.getCarte().get(m)).getY() == j) {// si M1 voisin
								if (Math.random() <0.6)
									Monde.getCarte().remove(Monde.getCarte().get(m));
									//System.out.println("OK"+Monde.getCarte().size());
									return ;
							}
							if (Monde.getCarte().get(m) instanceof M2 && ((M2) Monde.getCarte().get(m)).getX() == i && ((M2) Monde.getCarte().get(m)).getY() == j) {// si M2 voisin
								if (Math.random() <0.6)
									Monde.getCarte().remove(Monde.getCarte().get(m));
									//System.out.println("OK"+Monde.getCarte().size());
									return ;
							}
						}
					}
				}
			}
		}
	}
}
