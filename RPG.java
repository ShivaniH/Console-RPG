/*OOPM mini-project, text-based RPG.
Batch C12, roll no.s 38, 39, 41, 42.*/

import java.io.*;
import java.util.*;

abstract class Characters {    //The base class for players and enemies.
    String name;
    int HP,MP;        //Health points, magic points
    int attack,defense;   
    int magattk;	//magic attack.
	abstract String SetName(int x);
	abstract int SetHP (int x);
	abstract int SetMP (int x);
	abstract int SetAttack (int x);
	abstract int SetMagattk(int x);
}

class Players extends Characters {    
    /*
	  The first derived class, for characters that
      the user can play as.
    */
	int maxHP, maxMP;
    int NumofHealthPotions;	//Number of health potions that the player has.
	int exp;					//Experience points
	Players () {}
    Players(int j)
    {
        //This constructor sets values of attributes for all players.
		
            name = SetName(j);
            HP = SetHP(j);
            MP = SetMP(j);
			maxHP=HP;
			maxMP=MP;
            attack = SetAttack(j);
            defense = SetDefense(j);
            magattk = SetMagattk(j);
			exp=0;
			NumofHealthPotions=3;
    }
	String SetName (int x)
    {
        String[] Name = {"Yui","Akari","Terra","Nyanne","May"};      
        return(Name[x]);
    }
    int SetHP (int x)
    {
        int[] hp ={190,130,250,160,250};
        return(hp[x]);       
    }
    int SetMP (int x)
    {
        int[] mp={300,250,130,90,110};
        return(mp[x]);
    }
    int SetAttack (int x)
    {
        int[] attack={67,61,82,79,88};
        return(attack[x]);
    }
    int SetDefense(int x)
    {
        int[] defense={24,37,26,29,33};
        return(defense[x]);
    }
    int SetMagattk(int x)
    {
        int[] Magattk={95,92,62,60,73};
        return(Magattk[x]);
    }
	
}

class Enemies extends Characters {
	/* The second derived class, for enemies that the user would
	   encounter during the game, inside dungeons.
	*/
	Enemies() {}
	Enemies(int m)
    {
        //This constructor sets values of attributes for all enemies.
		
            name = SetName(m);
            HP = SetHP(m);
            MP = SetMP(m);
            attack = SetAttack(m);
            magattk = SetMagattk(m);   
	}
	
	String SetName (int x)
    {
        String[] Name = {"Envy","Greed","Wrath","Gluttony","Sloth","Ankoku"};
        return(Name[x]);
    }
    int SetHP (int x)
    {
        int[] hp ={125,50,200,100,150,450};
        return(hp[x]);       
    }
    int SetMP (int x)
    {
        int[] mp={200,65,160,140,100,300};
        return(mp[x]);
    }
    int SetAttack (int x)
    {
        int[] attack={70,74,72,78,80,85};
        return(attack[x]);
    }
    int SetMagattk(int x)
    {
        int[] Magattk={82,77,85,80,73,90};
        return(Magattk[x]);
    }
	
	public void Bossfight(Players pl,Enemies mon)		//final fight.
	{
		Scanner input = new Scanner(System.in);
		int what; boolean res;
		System.out.println(". . . I'm Ankoku, the guardian of Queen Hikari's treasure. Who are you, why're  you here?"+
		"\n1.I'm "+pl.name+". I'm here to get that treasure!"+"\n2.I'm "+pl.name+". Who's Queen Hikari?"+"\n(What will you say? (1/2))");
		what=input.nextInt();
		
		switch(what)
	
                {	case 1:
					{
					System.out.println("\n\n. . . You.Won't.Ever.Get.It.\n* SLASH! *\nAnkoku killed you."+
					"\n\n~~~~~~~~~~~~~~~~~~~~GAME OVER~~~~~~~~~~~~~~~~~~~~~~~~");
					System.exit(0);
					}
					break;
			case 2:
					{
					what=0;
					System.out.println("\n\n. . .She ruled this territory before humans got here. Before she died, she"+
					"      created me to guard her most precious item. I am immortal, and will protect it  for eternity.");
					System.out.println("\n(What will you say?)\n1.Hmm, whatever it is, I'm gonna get it! Out of my way."+
					"\n2.I wonder who she was exactly, someone who can create an 'immortal being' to   protect their treasure after they die.");
					what=input.nextInt();
					if(what==1)
					{
						System.out.println(". . . You.Won't.Ever.Get.It.\n* SLASH! *\nAnkoku killed you."+
						"\n~~~~~~~~~~~~~~~~~~~~GAME OVER~~~~~~~~~~~~~~~~~~~~~~~~");
						System.exit(0);
					}
					else{
					System.out.println("\n\n. . .She was a resident of the planet Saturn.Her treasure is a ring that holds  her powers.\n"+
					"\n"+pl.name+": How did she transfer her powers to a ring before dying?"+"\nAnkoku:. . .You ask too many questions!\n\n"
					+"Ankoku is trying to attack you!");
					}
					}
					break;
		
			default: System.out.println("Invalid command.");
		}
		
		res=RPG.fight(pl,mon);
		if(res==true)
		{	System.out.println("\n\nAnkoku fell to the ground! You quickly run past him, and find the ring inside   a treasure chest which was behind him."+
			" When you look at the ring, you hear the  words 'Help me'. . .You turn back to Ankoku, and ask, \"You're not really a      'guardian created by Hikari',   are you?\" "+
			"\nHe says,\". . .I'm Ankoku..an evil spirit who captured Hikari in a ring! Give it back!\" "+
			"\nHe tries to get up and attack, but seems to be weakened..."+
			"\nYou smash the ring onto a wall.\n"+
			"\n Nooooooo! he screams, as he disappears. You hear a gentle voice say 'Thank you....'.");
			System.out.println("\n\nYou freed Hikari's soul! But that's not all, for something seems"+
			" to be happening to the chest..."+"\n\n");
			
			System.out.println("The treasure chest got filled with gold! YAYY! YOU WON!."+
			"\n\nThanks for playing!");
		}
	}
}

/*Class which contains methods that modify the dungeon(grid).*/

class Dungeon {
	char grid[][]=new char[5][5];	//dungeon grid.
	int enem_loc[][]=new int[3][2];		//stores enemy locations that are generated randomly.
	int row,col;	//co-ordinates entered by the user.
	
	public void inGrid()	//to initialize the dungeon grid.
	{
		int i,j;
		for(i=0; i<5; ++i)
		{
			for(j=0; j<5; ++j)
			{
				grid[i][j]='.';
			}
		}
	}
	public void displayGrid()
	{
		int m,n;
		System.out.println("\t0\t1\t2\t3\t4\n");
		for(m=0; m<5; ++m)
		{
			System.out.print(m+"\t");
			for(n=0; n<5; ++n)
			{
				System.out.print(grid[m][n]+"\t");
			}		
			System.out.println();
		}
		System.out.println("Indicators:\n. -- Undiscovered location\nX -- You've defeated an enemy here\nO -- There's no enemy here\n");
	}
	public void chooseEnloc()	//to choose locations of the enemies randomly.
	{
		Random r = new Random();
		int i;
		for(i=0; i<3; ++i)
		{
			enem_loc[i][0]=r.nextInt(5);
			enem_loc[i][1]=r.nextInt(5);
		}
		for(i=0;i<2;++i)
		{
			if(enem_loc[i][0]==enem_loc[i+1][0])
			{
				if(enem_loc[i][1]==enem_loc[i+1][1])
				{
					if(enem_loc[i][1]!=0)
					{ --enem_loc[i+1][1]; }
					else {++enem_loc[i+1][1];}
				}
			}
		}
		if(enem_loc[0][0]==enem_loc[2][0])
		{
			if(enem_loc[0][1]==enem_loc[2][1])
			{
				if(enem_loc[0][1]!=0)
				{--enem_loc[2][1];}
				else {++enem_loc[2][1];}
			}
					
		}
	}
	public void playLoc()	//accepts co-ordinates from the user.
	{
		Scanner sc= new Scanner(System.in);
		System.out.println("Where would you like to move to? (Enter the co-ordinates[only numbers])\n");
		row=sc.nextInt(); col=sc.nextInt();
	}
	public boolean check()	 //checks if there's an enemy at the entered co-ordinates.
	{	boolean flag=false;
		int i,cnt=0;
		for(i=0; i<3; ++i)
		{	
			if(enem_loc[i][0]==row && enem_loc[i][1]==col)
			{
				flag = true;
				++cnt;
			}
			
		}
		if((grid[row][col])=='X')
		{
			if(cnt>1)
			{	
				flag = true;
				--cnt;
			}
			else {flag=false;}
		}		
		return(flag);
	}
	public void update(int ou)	//will mark Xs and Os depending on the user's move.
	{
		/* outcome 1 : There was an enemy, and user defeated it.
		   outcome 2 : There was nothing there.
		*/
		if(ou==1)
		{
			grid[row][col]='X';
		}
		else { grid[row][col]='O'; }	
	}
}

/*class containing main, mechanism and fight static methods.*/

class RPG {
	public static void mechanism(Players pl)
	{
		Dungeon d = new Dungeon();
		Random r1 = new Random();
		int chosenOne;
		d.inGrid();
		int enemdef=0; //number of enemies defeated.
		boolean won, isit;
		System.out.println("\nA top view map of the castle:\n");
		d.displayGrid();
		d.chooseEnloc();
		while(enemdef!=3)
		{
			System.out.println("You see some monsters at these locations:");
			for(int z=0; z<3; ++z)
			{
				System.out.println(d.enem_loc[z][0]+" "+d.enem_loc[z][1]);
			}
			d.playLoc();
			isit = d.check();
			if(isit==true)
			{
			  chosenOne=r1.nextInt(5);
			  Enemies Mon = new Enemies(chosenOne);
			  System.out.println("\n \"I'm "+Mon.name+"! \", says the monster, as it tries to attack you!");
			  won=fight(pl,Mon);
				if(won==true)
				{
					d.update(1);
					++enemdef;
					pl.exp+=10;
					if((pl.exp%3)==0)
					{
						pl.maxHP+=5;
						pl.maxMP+=5;
						pl.attack+=3;
						pl.defense+=3;
						pl.magattk+=3;
						System.out.println("\nYou've defeated all monsters you could see, and have become stronger!\n"+pl.name+
						"'s attributes are now:\nmaxHP:"+pl.maxHP+"\nmaxMP:"+pl.maxMP+"\nAttack:"+
						pl.attack+"\nDefense:"+pl.defense+"\nMagic attack:"+pl.magattk);
					}
				}
			}
			else{
			System.out.println("There's nothing here. Try another location.\n");
			d.update(2);
			}
		    d.displayGrid();
		}
	}
	public static boolean fight(Players pl,Enemies en)
	{
		Random r1 = new Random();
		Scanner in = new Scanner(System.in);
		int usermove, damTaken, damGiven, effdamTaken;
		float redPerc, redBy;
		boolean win = true;
		System.out.println("\nWhat will you do?");
		while(en.HP>0 && pl.HP>0) {
			System.out.println("Choose what to do now: (Enter 1/2/3) \n1.Physical attack\n2.Magic attack\n3.Drink "+
			"a health potion\n[You have "+pl.NumofHealthPotions+" health potion(s) left.]");
			usermove=in.nextInt();
			if(usermove==1)
			{
				damGiven=r1.nextInt(pl.attack);
				damTaken=r1.nextInt(en.attack);
				System.out.println("\n"+"You caused "+damGiven+" damage to "+en.name+" 's HP.");
				en.HP-=damGiven;
				if(en.HP>0)
				{	redPerc = (pl.defense/100.0F);
					redBy=redPerc*damTaken;
					effdamTaken=(int)(damTaken-redBy);
					pl.HP-=effdamTaken;
					System.out.println(en.name+" struck back, and caused "+effdamTaken+" damage to your HP.");
					System.out.println("Your HP: "+pl.HP+"\n");
				}
			}
			else if(usermove==2)
			{
				if(pl.MP>0)
				{
					damGiven=r1.nextInt(pl.magattk);
					damTaken=r1.nextInt(en.magattk);
					System.out.println("\n"+"You caused "+damGiven+" damage to "+en.name+" 's HP.");
					en.HP-=damGiven;
					if(en.HP>0)
					{	redPerc =(pl.defense/100.0F);
						redBy=redPerc*damTaken;
						effdamTaken=(int)(damTaken-redBy);
						pl.HP-=effdamTaken;
						System.out.println(en.name+" struck back, and caused "+effdamTaken+" damage to your HP.");
						pl.MP-=10;
						en.MP-=10;
						System.out.println("Your HP: "+pl.HP+"\n");
						System.out.println("Your MP: "+pl.MP+"\n");
					}			
				}
				else {
					System.out.println("You don't have enough magic points!");
				}
			}
			else if(usermove==3)
			{
				if(pl.NumofHealthPotions>0)
				{
					pl.HP=pl.maxHP;
					--pl.NumofHealthPotions;
					System.out.println("\nYou drank a health potion.\nYour HP: "+pl.HP);
					System.out.println("You have "+pl.NumofHealthPotions+" health potion(s) left.\n");
				}
			}
			else {
			System.out.println("Invalid command");
			}
		}
		if(pl.HP<=0)
		{
			System.out.println(en.name+" killed you! :( \n~~~~~~~~~~~~~~~~~~~~GAME OVER~~~~~~~~~~~~~~~~~~~~~~~~");
			win=false;
			System.exit(0);
		}
		else {
			System.out.println("You defeated "+en.name+" ! \n");
			if(r1.nextInt(100)<50)
			{
				++pl.NumofHealthPotions;
				System.out.println(en.name+" dropped a health potion!\nYou now have ");
				System.out.print(pl.NumofHealthPotions+" health potion(s).\n");
				win=true;
			}
		}
		return(win);
	}
	
    public static void main(String[] args) throws IOException
	{
        Scanner sc = new Scanner(System.in);
        String ch="n";
        int choice=1;
		int perm=0;
		boolean f1 = false;
        Players[] play = new Players[5];     //Array of objects that holds 5 players.
        for(int i=0; i<5; ++i)
        {
            play[i]=new Players(i);          //Each player object is created separately.
        }
               
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~Welcome to the Adventure RPG~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		FileReader fr = new FileReader("intro.txt");
		int i = fr.read();
		while(i!=-1)
		{
			System.out.print((char)i);
			i=fr.read();
			try { Thread.sleep(50);}
			catch(InterruptedException e)
			{}
		}
		fr.close();
                
		
        do {			/*Loop to choose character*/
		
        System.out.println("\nChoose a character to see their details: (Enter 1/2/3/4/5)");
		
        System.out.println("1.Yui Izumi -- The elemental magician\n2.Akari Tsukino -- The shrine priestess "+
		"with divine powers\n3.Terra Felis -- The swordswoman\n4.Nyanne Flores -- The gunslinger\n"+
		"5.May Haan -- The martial artist\n");
		
			choice = sc.nextInt()-1;
		
		if(choice<0||choice>4)
		{ System.out.println("Invalid choice! Please enter one of the options that have been given.\n");
		  continue;
		}
		
		System.out.println(play[choice].name+"'s details are:\n"
        +"maxHP:"+play[choice].HP+"\n"+"maxMP:"+play[choice].MP+"\n"+"Attack:"+play[choice].attack
		+"\nDefense:"+play[choice].defense+"\n"+"Magic Attack:"+play[choice].magattk+"\n");
		
        System.out.println("Do you want to select "+play[choice].name+" as your character? (Enter y/n)"+
		"\n[You can see details of other characters if you enter n.]\n");
		
        try {
			ch = sc.next();
		}
		catch(InputMismatchException ime)
		{
			System.out.println("Invalid choice! Please enter one of the options that have been given.\n");
		}
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		if(ch.equals("y")==true)
        {
			perm=choice;
			System.out.println("You have chosen "+play[perm].name+" as your character!\n"+"Press enter to continue.\n");
			br.read();
            break;
        }
        }while(ch.equals("y")==false);   

		FileReader fr1 = new FileReader("jourstart.txt");
		i = fr1.read();
		while(i!=-1)
		{
			System.out.print((char)i);
			i=fr1.read();
			try { Thread.sleep(50);}
			catch(InterruptedException e)
			{}
		}
		fr1.close();
		
		mechanism(play[perm]);
		System.out.println("Press enter to continue.");
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		br.read();
		System.out.println("\nThe monsters vanish, and an opening is revealed. You enter it,"+
		" and find yourself in another room.");
		System.out.print("\n . . But this time, something feels different. You hear an eerie voice. . .\n");
		Enemies boss = new Enemies(5);
		boss.Bossfight(play[perm],boss);
    }   
}
