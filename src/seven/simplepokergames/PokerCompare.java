
package seven.simplepokergames;

import java.util.Comparator;

/**
 * @author H.Xing
 *
 */
public class PokerCompare implements Comparator{
	
	
	

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		
		if(o1 instanceof Poker && o2 instanceof Poker){
			
			Poker op1 = (Poker)o1;
			Poker op2 = (Poker)o2;
			
			if(op1.getPokerPiont()>op2.getPokerPiont()){
				return 1;			
			}
			if(op1.getPokerPiont()==op2.getPokerPiont()){
				//"♦","♣","♥","♠"
				if(op1.getPokerSuit()=="♦"){return -1;}
				
				if(op1.getPokerSuit()=="♣"&&op2.getPokerSuit()=="♦"){return 1;}
				else if(op1.getPokerSuit()=="♣"&&op2.getPokerSuit()!="♦"){return -1;}
				
				
				if(op1.getPokerSuit()=="♥"&&op2.getPokerSuit()!="♠"){return 1;}
				if(op1.getPokerSuit()=="♠"){return 1;}
			}
			
				return -1;
			
			}
		return 1;
	}
	

}
