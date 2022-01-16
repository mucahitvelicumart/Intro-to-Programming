import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {	
	
	public static void main(String args[]) throws FileNotFoundException, IOException, ParseException  {
		People[] people = new People[3];
		people[0] = new Players("Player 1", 15000, 0, false, 0);
		people[1] = new Players("Player 2", 15000, 0, false, 0);
		people[2] = new Banker("Banker", 100000);
		Cards[] cardsOfChance = new Cards[6];
		Cards[] cardsOfCommunityChest = new Cards[11];
		JSONParser parserOfCards= new JSONParser();
		Object obj1 = parserOfCards.parse(new FileReader("list.json"));
		JSONObject card = (JSONObject) obj1;
		JSONArray chanceCard =(JSONArray) card.get("chanceList");
		JSONArray communityChestCard = (JSONArray) card.get("communityChestList");
		for (int i = 0; i < chanceCard.size(); i++) {
			JSONObject gecici_card =(JSONObject) chanceCard.get(i);
			String item = (String) gecici_card.get("item");
			cardsOfChance[i] = new Cards(item);
		}
		for (int i = 0; i < communityChestCard.size(); i++) {
			JSONObject gecici_card = (JSONObject) communityChestCard.get(i);
			String item = (String) gecici_card.get("item");
			cardsOfCommunityChest[i] = new Cards(item);
		}
		Squares[] squares = new Squares[40];
		squares[0] = new Squares(1, "Go");
		squares[2] = new Action(3,"Community Chest");
		squares[4] = new Squares(5,"Income Tax");
		squares[7] = new Action(8,"Chance");
		squares[10] = new Squares(11,"Jail");
		squares[17] = new Action(18,"Community Chest");
		squares[20] = new Squares(21, "Free Parking");
		squares[22] = new Action(23, "Chance");
		squares[30] = new Squares(31, "Go to Jail");
		squares[33] = new Action(34,"Community Chest");
		squares[36] = new Action(37, "Chance");
		squares[38] = new Squares(39,"Super Tax");
		JSONParser parserOfProperties = new JSONParser();
		Object obj = parserOfProperties.parse(new FileReader("property.json"));
		PrintWriter output = new PrintWriter("output.txt");
		JSONObject property = (JSONObject) obj;
		JSONArray lands = (JSONArray) property.get("1");
		JSONArray railroads = (JSONArray) property.get("2");
		JSONArray companies = (JSONArray) property.get("3");
		for (int i = 0; i < lands.size(); i++) {
			JSONObject land = (JSONObject) lands.get(i);
			String gecici_name = (String) land.get("name");
			String gecici_cost =  (String) land.get("cost");
			String gecici_id =  (String) land.get("id");
			squares[Integer.parseInt(gecici_id)-1]= (Lands) new Lands(Integer.parseInt(gecici_id), gecici_name, Integer.parseInt(gecici_cost));
		}
		for (int i = 0; i < railroads.size(); i++) {
			JSONObject railroad = (JSONObject) railroads.get(i);
			String gecici_name = (String) railroad.get("name");
			String gecici_cost = (String) railroad.get("cost");
			String gecici_id = (String) railroad.get("id");
			squares[Integer.parseInt(gecici_id)-1]= (Railroads) new Railroads(Integer.parseInt(gecici_id), gecici_name, Integer.parseInt(gecici_cost));
		}
		for (int i = 0; i < companies.size(); i++) {
			JSONObject company = (JSONObject) companies.get(i);
			String gecici_name = (String) company.get("name");
			String gecici_cost = (String) company.get("cost");
			String gecici_id = (String) company.get("id");
			squares[Integer.parseInt(gecici_id)-1]= (Companies) new Companies(Integer.parseInt(gecici_id), gecici_name, Integer.parseInt(gecici_cost));
		}
		File commandFile = new File(args[0]);
		BufferedReader file = new BufferedReader(new FileReader(commandFile));
		String line;
		while((line = file.readLine())!="galatasaray") {
			if (line == null) {
				People.Show(people,output);
				break;
			}
			if(people[0].money<0 || people[1].money<0) {
				People.Show(people,output);
				break;
			}
			else 
			{
				String[] commands = line.split(";");
				if (commands.length==2) {
					String playername=commands[0];
					int dice= Integer.parseInt(commands[1]);
					People playingplayer = People.findPerson(people, playername);
					People otherplayer = People.FindPerson(people, playername);
					if (((Players) playingplayer).jail==true) {
						Players.gaming(playingplayer, otherplayer, dice, people,output);
						System.out.println("in jail(count= "+(((Players) playingplayer).jail_count+1)+")");
						output.println("in jail(count= "+(((Players) playingplayer).jail_count+1)+")");
						if (((Players) playingplayer).jail_count==2) {
							((Players) playingplayer).jail=false;
							((Players) playingplayer).jail_count=0;	
						}
						else {
							((Players) playingplayer).jail_count++;
						}
					}
					else {
						((Players) playingplayer).location += dice;
						if (((Players) playingplayer).location==50) {
							((Players) playingplayer).location=10;
							((Players) playingplayer).jail =true;
							continue;
						}
						else if(((Players) playingplayer).location>39) {
							if (((Players) playingplayer).location==40) {
								Players.gaming(playingplayer, otherplayer, dice, people,output);
								System.out.println("in Go Square");
								output.println("in Go Square");
							}
							((Players) playingplayer).location-=40;
							((Players) playingplayer).money +=200;
							people[2].money-=200;
						}
						boolean cardMove = false;
						if(squares[((Players) playingplayer).location].name.equals("Community Chest")) {
							Action.communityActions(playingplayer, otherplayer, cardsOfCommunityChest, people[2],dice,people,false,output);
							cardsOfCommunityChest=Action.underDeck(cardsOfCommunityChest);
						}
						else if(squares[((Players) playingplayer).location].name.equals("Chance")) {
							cardMove=Action.chanceActions(playingplayer, otherplayer, cardsOfChance, people[2],dice,people,cardMove,output);
							cardsOfChance=Action.underDeck(cardsOfChance);
							if (squares[((Players) playingplayer).location].name.equals("Community Chest")) {
								Action.communityActions(playingplayer, otherplayer, cardsOfCommunityChest, people[2],0,people,true,output);
								cardsOfCommunityChest=Action.underDeck(cardsOfCommunityChest);
							}
						}
						if(squares[((Players) playingplayer).location] instanceof Lands) {
							if(!((Lands)squares[((Players) playingplayer).location]).owner.equals(((Players) playingplayer).name)){
								if (((Lands)squares[((Players) playingplayer).location]).owner.equals(" ")) {
									if (((Players) playingplayer).money>=((Lands)squares[((Players) playingplayer).location]).cost) {
										((Lands)squares[((Players) playingplayer).location]).owner = ((Players) playingplayer).name;
										((Players) playingplayer).money-=((Lands)squares[((Players) playingplayer).location]).cost;
										((Players) playingplayer).properties.add(((Lands)squares[((Players) playingplayer).location]).name);
										people[2].money+=((Lands)squares[((Players) playingplayer).location]).cost;
										if (cardMove==true) {
											System.out.println(((Players) playingplayer).name+" bought "+((Lands)squares[((Players) playingplayer).location]).name);
											output.println(((Players) playingplayer).name+" bought "+((Lands)squares[((Players) playingplayer).location]).name);
										}
										else {
											Players.gaming(playingplayer, otherplayer, dice, people,output);
											System.out.println("bought "+((Lands)squares[((Players) playingplayer).location]).name);
											output.println("bought "+((Lands)squares[((Players) playingplayer).location]).name);
										}
										cardMove=false;
									}
									else {
										if (cardMove==true) {
											System.out.println(((Players) playingplayer).name+" goes bankrupt");
											output.println(((Players) playingplayer).name+" goes bankrupt");
											People.Show(people,output);
											break;
										}
										else {
											Players.gaming(playingplayer, otherplayer, dice, people,output);
											System.out.println("goes bankrupt");
											output.println("goes bankrupt");
											People.Show(people,output);
											break;
										}
									}
								}
								else {
									int rent = Squares.Rent(squares, dice, playingplayer, otherplayer);
									playingplayer.money-=rent;
									otherplayer.money+=rent;
									if(cardMove==true) {
										System.out.println(((Players) playingplayer).name+" paid rent for "+((Lands)squares[((Players) playingplayer).location]).name);
										output.println(((Players) playingplayer).name+" paid rent for "+((Lands)squares[((Players) playingplayer).location]).name);
									}
									else {
										Players.gaming(playingplayer, otherplayer, dice, people,output);
										System.out.println("paid rent for "+((Lands)squares[((Players) playingplayer).location]).name);
										output.println("paid rent for "+((Lands)squares[((Players) playingplayer).location]).name);
									}
									cardMove = false;
								}
							}
							else {
								if (cardMove==true) {
									System.out.println(((Players) playingplayer).name+" has "+squares[((Players) playingplayer).location].name);
									output.println(((Players) playingplayer).name+" has "+squares[((Players) playingplayer).location].name);
								}
								else {
									Players.gaming(playingplayer, otherplayer, dice, people,output);
									System.out.println("has "+squares[((Players) playingplayer).location].name);
									output.println("has "+squares[((Players) playingplayer).location].name);
								}
							}
						}
						else if(squares[((Players) playingplayer).location] instanceof Railroads) {
							if(!((Railroads)squares[((Players) playingplayer).location]).owner.equals(((Players) playingplayer).name)){
								if (((Railroads)squares[((Players) playingplayer).location]).owner.equals(" ")) {
									if (((Players) playingplayer).money>=((Railroads)squares[((Players) playingplayer).location]).cost) {
										((Railroads)squares[((Players) playingplayer).location]).owner = ((Players) playingplayer).name;
										((Players) playingplayer).money-=((Railroads)squares[((Players) playingplayer).location]).cost;
										((Players) playingplayer).num_of_railroad+=1;
										((Players) playingplayer).properties.add(((Railroads)squares[((Players) playingplayer).location]).name);
										people[2].money+=((Railroads)squares[((Players) playingplayer).location]).cost;
										Players.gaming(playingplayer, otherplayer, dice, people,output);
										System.out.println("bought "+((Railroads)squares[((Players) playingplayer).location]).name);
										output.println("bought "+((Railroads)squares[((Players) playingplayer).location]).name);
									}
									else {
										Players.gaming(playingplayer, otherplayer, dice, people,output);
										System.out.println(" goes bankrupt");
										output.println(" goes bankrupt");
										People.Show(people,output);
										break;
									}
								}
								else {
									int rent = Squares.Rent(squares, dice, playingplayer, otherplayer);
									playingplayer.money-=rent;
									otherplayer.money+=rent;
									Players.gaming(playingplayer, otherplayer, dice, people,output);
									System.out.println("paid rent for "+((Railroads)squares[((Players) playingplayer).location]).name);
									output.println("paid rent for "+((Railroads)squares[((Players) playingplayer).location]).name);
								}
							}
							else {
								Players.gaming(playingplayer, otherplayer, dice, people,output);
								System.out.println("has "+squares[((Players) playingplayer).location].name);
								output.println("has "+squares[((Players) playingplayer).location].name);
							}
						}
						else if(squares[((Players) playingplayer).location] instanceof Companies) {
							if(!((Companies)squares[((Players) playingplayer).location]).owner.equals(((Players) playingplayer).name)){
								if (((Companies)squares[((Players) playingplayer).location]).owner.equals(" ")) {
									if (((Players) playingplayer).money>=((Companies)squares[((Players) playingplayer).location]).cost) {
										((Companies)squares[((Players) playingplayer).location]).owner = ((Players) playingplayer).name;
										((Players) playingplayer).money-=((Companies)squares[((Players) playingplayer).location]).cost;
										((Players) playingplayer).properties.add(((Companies)squares[((Players) playingplayer).location]).name);
										people[2].money+=((Companies)squares[((Players) playingplayer).location]).cost;
										Players.gaming(playingplayer, otherplayer, dice, people,output);
										System.out.println("bought "+((Companies)squares[((Players) playingplayer).location]).name);
										output.println("bought "+((Companies)squares[((Players) playingplayer).location]).name);
									}
									
									else {
										Players.gaming(playingplayer, otherplayer, dice, people,output);
										System.out.println(" goes bankrupt");
										output.println(" goes bankrupt");
										People.Show(people,output);
										break;
									}
								}
								else {
									int rent = Squares.Rent(squares, dice, playingplayer, otherplayer);
									playingplayer.money-=rent;
									otherplayer.money+=rent;
									Players.gaming(playingplayer, otherplayer, dice, people,output);
									System.out.println("paid rent for "+((Companies)squares[((Players) playingplayer).location]).name);
									output.println("paid rent for "+((Companies)squares[((Players) playingplayer).location]).name);
								}
							}
							else {
								Players.gaming(playingplayer, otherplayer, dice, people,output);
								System.out.println("has "+squares[((Players) playingplayer).location].name);
								output.println("has "+squares[((Players) playingplayer).location].name);
							}
						}
						else if(squares[((Players) playingplayer).location].name.equals("Income Tax") || squares[((Players) playingplayer).location].name.equals("Super Tax")) {
							((Players) playingplayer).money-=100;
							people[2].money +=100;
							if (cardMove==true) {
								System.out.println(((Players) playingplayer).name+" paid "+squares[((Players) playingplayer).location].name);
								output.println(((Players) playingplayer).name+" paid "+squares[((Players) playingplayer).location].name);
							}
							else {
								Players.gaming(playingplayer, otherplayer, dice, people,output);
								System.out.println("paid "+squares[((Players) playingplayer).location].name);
								output.println("paid "+squares[((Players) playingplayer).location].name);
							}
						}
						else if(squares[((Players) playingplayer).location].name.equals("Go")) {
							continue;
						}
						else if(squares[((Players) playingplayer).location].name.equals("Free Parking")) {
							Players.gaming(playingplayer, otherplayer, dice, people,output);
							System.out.println("is in Free Parking");
							output.println("is in Free Parking");
							
						}
						else if(squares[((Players) playingplayer).location].name.equals("Go to Jail")) {
							
							((Players) playingplayer).location=10;
							((Players) playingplayer).jail =true;
							Players.gaming(playingplayer, otherplayer, dice, people,output);
							System.out.println(" went to Jail");
							output.println(" went to Jail");
						}
						else if(squares[((Players) playingplayer).location].name.equals("Jail")) {
							Players.gaming(playingplayer, otherplayer, dice, people,output);
							System.out.println("went to Jail");
							output.println("went to Jail");
							((Players) playingplayer).jail =true;
						}
					}
				}
				else {
					People.Show(people,output);
				}
			}
		}
		file.close();
		output.close();
	}
}

		
