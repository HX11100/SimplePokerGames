
package java.season.three;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author H.Xing
 *
 */
public class PokerGameOfMap {

	
	List<Poker> listPokers = new ArrayList<Poker>();//创建扑克列表
	Map<Player,List<Poker>> pokerGames = new HashMap<Player,List<Poker>>();//玩家ID/姓名，与相应手牌形成键值对
	List<Player> listPlayers = new ArrayList<Player>();//创建玩家
	List<Poker> listPokersByPlay1 = new ArrayList<Poker>();//玩家1 手牌
	List<Poker> listPokersByPlay2 = new ArrayList<Poker>();//玩家2 手牌
	List<Poker> startPokersGames = new ArrayList<Poker>();//将两玩家信息及大的手牌放同一集合

	
	
	public static void main(String[] args) {
		
		PokerGameOfMap pg = new PokerGameOfMap();
		pg.createPoker();//创建扑克
		pg.shuffle();//洗牌
		pg.createPlay();//创建玩家
		pg.dealPoker();//发牌
		pg.startGame();//开始游戏，比较大小
		
		
		
	}
	
	//	1.创建扑克（扑克集合）；
	public void createPoker(){
		
		String[] pokers = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		Integer[] pokPiont = {2,3,4,5,6,7,8,9,10,11,12,13,14};
		String[] pokSuit={"♦","♣","♥","♠"};
		
		System.out.println("--------------------------------------创建扑克--------------------------------------");
//		循环花色
		for (String poks : pokSuit) {
			//循环扑克配花色并存入listPokers中
			for (int i = 0; i < pokers.length; i++) {
				listPokers.add(new Poker(poks,pokers[i], pokPiont[i]));//将各张"扑克"及"点数"依次存入列表
				
			}
			
		}
		System.out.println("------------------------------------创建扑克成功------------------------------------");
		//遍历扑克打印显示
		int i=0;
		for (Poker list : listPokers) {
			
			System.out.print("|"+list.getPokerSuit()+list.getPokerName()+"|"+"\t");
			i++;
			if(i%13==0){//以13张为一行打印显示
				System.out.println();
			}
		}
		
		//按点数及花色大小排序
		int j=0;
		Collections.sort(listPokers, new PokerCompare());
		System.out.println("====================List<Poker> listPokers 按点数及花色大小排序====================");
		for (Poker list : listPokers) {
			
			System.out.print("|"+list.getPokerSuit()+list.getPokerName()+"|"+"\t");
			j++;
			if(j%12==0){//以12张为一行打印显示
				System.out.println();
			}
		}
		System.out.println();
		
	}
	
	
	//	2.洗牌（打乱顺序）；
	public void shuffle(){
		
		System.out.println("--------------------------------------开始洗牌--------------------------------------");
		Collections.shuffle(listPokers);
		int i=0;
		for (Poker list : listPokers) {
			System.out.print("|"+list.getPokerSuit()+list.getPokerName()+"|"+"\t");
			i++;
			if(i%13==0){//以13张为一行打印显示
				System.out.println();
			}
		}
		System.out.println("--------------------------------------洗牌结束--------------------------------------");
	}

	
	//	3.创建玩家（ID，姓名，手牌）；
	public void createPlay(){
		
		Scanner input = new Scanner(System.in);
		System.out.println("--------------------------------------创建玩家--------------------------------------");
		for (int i = 1; i < 3; i++) {
			try {
				System.out.println("请输入第{"+i+"}个玩家ID:");
				Integer id = input.nextInt();
//				System.out.println("玩家为---------："+listPlayers.contains(new Player(id, null)));
				if(!listPlayers.contains(new Player(id, null))){
					System.out.println("请输入玩家名称:");
					String name = input.next();
					Player pl = new Player(id, name);
					listPlayers.add(pl);
					System.out.println("欢迎玩家："+name);
				}else{
					
					System.out.println("---------该ID已存在，请重新输入-----------");
//					input = new Scanner(System.in);
					i--;
					continue;
				}
				
				
			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println("★★☆★★请输入阿拉伯数字★★☆★★");
				input = new Scanner(System.in);
				i--;
				continue;
			}
		}
	}
	
	
	//	4.发牌（单张发牌，依次拿牌，每人两张）；
	public void dealPoker(){
		
		System.out.println("--------------------------------------开始发牌--------------------------------------");
		int i=0;
		//遍历玩家
		while(i<4){
			for (Player player : listPlayers) {
				
				for (int j = i; j < listPokers.size(); j++) {
					
					if(i%2==0){
						listPokersByPlay1.add(listPokers.get(j));
						pokerGames.put(player, listPokersByPlay1);
						System.out.println("玩家:"+player.getName()+"--拿牌:|"+listPokers.get(j).getPokerSuit()+listPokers.get(j).getPokerName()+"|");
						System.out.println();
						break;
					}if(i%2!=0){
						listPokersByPlay2.add(listPokers.get(j));
						pokerGames.put(player, listPokersByPlay2);
						System.out.println("玩家:"+player.getName()+"--拿牌:|"+listPokers.get(j).getPokerSuit()+listPokers.get(j).getPokerName()+"|");
						System.out.println();
						break;
						
					}
				}
				i++;
			}	
		}
		System.out.println("--------------------------------------发牌结束--------------------------------------");
		
	}
	

	//	5.游戏（不同花色比点数，同点数比花色）；
	public void startGame(){
		
		//通过遍历keySet()获取玩家手牌及手牌信息
		System.out.println("**************************************开始游戏**************************************");
		for (Player p : listPlayers) {
			List<Poker> list = pokerGames.get(p);
			Collections.sort(list, new PokerCompare());//按点数大小排序
			System.out.print("玩家("+p.getName()+")最大的手牌为:");
			System.out.println("|"+list.get(list.size()-1).getPokerSuit()+list.get(list.size()-1).getPokerName()+"|");
			
			//将两玩家及手牌信息放入集合中
			startPokersGames.add(new Poker(list.get(list.size()-1).getPokerSuit(), list.get(list.size()-1).getPokerName(), list.get(list.size()-1).getPokerPiont(),p));
			
			Collections.sort(startPokersGames, new PokerCompare());	
			
			
		}
		System.out.println();
		System.out.println("★☆★☆★☆★☆★☆★☆★☆★玩家-"+startPokersGames.get(startPokersGames.size()-1).getPlayer().getName()+"-获胜!★☆★☆★☆★☆★☆★☆★☆★");
		
		
		System.out.println("------------------------------------玩家各自手牌------------------------------------");
		for (Player lp : listPlayers) {
			
			List<Poker> playPoker = pokerGames.get(lp);
			Collections.sort(playPoker, new PokerCompare());//按点数大小排序
			System.out.print("玩家("+lp.getName()+"):");
			for (Poker poker : playPoker) {
				System.out.print("|"+poker.getPokerSuit()+poker.getPokerName()+"|");
			}
			System.out.println();
		}
	
	}
		

	
}
