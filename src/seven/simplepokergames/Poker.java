
package seven.simplepokergames;

/**
 * @author H.Xing
 *
 */
public class Poker {

	private String pokerSuit;//扑克花色
	private String pokerName;//扑克名称
	private int pokerPiont;//扑克点数
	private Player player;//玩家
	

	private Poker() {
		super();
		
	}
	
	
	public String getPokerSuit() {
		return pokerSuit;
	}


	public String getPokerName() {
		return pokerName;
	}


	public int getPokerPiont() {
		return pokerPiont;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	


	public Poker(String pokerSuit, String pokerName, int pokerPiont) {
		super();
		this.pokerSuit = pokerSuit;
		this.pokerName = pokerName;
		this.pokerPiont = pokerPiont;
	}


	public Poker(String pokerSuit, String pokerName, int pokerPiont, Player player) {
		super();
		this.pokerSuit = pokerSuit;
		this.pokerName = pokerName;
		this.pokerPiont = pokerPiont;
		this.player = player;
	}



	


	
	
}
