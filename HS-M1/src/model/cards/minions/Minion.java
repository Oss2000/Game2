package model.cards.minions;

import model.cards.Card;
import model.cards.Rarity;
import model.heroes.Hero;
import exceptions.InvalidTargetException;

public class Minion extends Card implements Cloneable {
	private int attack;
	private int maxHP;
	private int currentHP;
	private boolean taunt;
	private boolean divine;
	private boolean sleeping;
	private boolean attacked;
	private MinionListener listener;

	public Minion(String name, int manaCost, Rarity rarity, int attack, int maxHP, boolean taunt, boolean divine,
			boolean charge) {
		super(name, manaCost, rarity);
		setAttack(attack);
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.taunt = taunt;
		this.divine = divine;
		if (!charge)
			this.sleeping = true;
	}

	public boolean isTaunt() {
		return taunt;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHp) {
		this.maxHP = maxHp;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
		if (this.currentHP > maxHP)
			this.currentHP = maxHP;
		else if (this.currentHP <= 0) {
			this.currentHP = 0;

		}
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
		if (this.attack <= 0)
			this.attack = 0;
	}

	public void setTaunt(boolean isTaunt) {
		this.taunt = isTaunt;
	}

	public void setDivine(boolean divine) {
		this.divine = divine;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public boolean isSleeping() {
		return sleeping;
	}

	public void setSleeping(boolean sleeping) {
		this.sleeping = sleeping;
	}

	public boolean isDivine() {
		return divine;
	}

	public void setListener(MinionListener listener) {
		this.listener = listener;
	}
	//Think about where and to whom this variable will be set.
	public void attack(Minion target){
		if(!this.divine)
		this.currentHP-=target.attack;
		
		if(!target.divine)
		target.currentHP-=this.attack;
		
		this.divine=false;
		target.divine=false;
		
		if(this.currentHP==0)
			listener.onMinionDeath(this);
		
		if(target.currentHP==0)
			listener.onMinionDeath(target);
	
			
	}
	public void attack(Hero target) throws InvalidTargetException{
		if(!this.getName().equals("Icehowl"))
		target.setCurrentHP(target.getCurrentHP()-this.attack);
		else
			throw new InvalidTargetException("ICEHOWLS CANNOT ATTACK HEROES!!");
}
	public Minion clone() throws CloneNotSupportedException{
		if(this instanceof Minion)
		{Minion x=new Minion(this.getName(),this.getManaCost(),this.getRarity(),attack,maxHP,taunt,divine,!this.sleeping);
		return x;
		}
		else
			throw new CloneNotSupportedException("CLONE TYPE IS NOT SUPPORTED!");
		
	}

}
