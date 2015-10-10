import java.util.Scanner;

public class Narnia{
  static boolean armor = false;
  static boolean traitor = false;
  static boolean weapon = false;
  static int yourHP = 100;
  
  public static void main(String [] args){
    //intro
    String s;
    print("This is a Text-Based Game. To be more specific, it's a Narnia themed rpg.");
    print("You are home, sitting on the couch contemplating life in the middle of summer. You feel like moving and getting your blood pumping.");
    print("Which room do you decide to go to? The kitchen, the bathroom, or your room?");
    //printed next to your input, pseudo-terminal
    System.out.print(">>");
    Scanner in = new Scanner(System.in);
    s = in.nextLine();
    pickRoom(s);
    print("The End!");
    print("Bonus Quest! What kind of a Narnian are you?");
    narniaPersonalityQuiz();
    print("Thank you for playing, but remember that the fun doesn't have to end here! This game is randomized so every time you play it's a different experience, even when you make the same choices. In addition, the choices you make can lead you down vastly different paths. Don't like where you ended up or just want to find out where else you could go? Play again!");
  }
  
  //printing function to help with readability
  public static void print(String toPrint){
    System.out.println(toPrint);
  }
  
  public static void pickRoom(String s){
    //the kitchen
    if (s.toLowerCase().contains("kitchen")){
      print("You raid your kitchen. All you find is a yuca root and a smelly durian fruit. Which do you eat, if you eat at all?");
      System.out.print(">>");
      Scanner in = new Scanner(System.in);
      String n = in.nextLine();
      if ((n.toLowerCase().contains("yuca") || (n.toLowerCase().contains("root")))){
        print("The root was poisonous. You die.");
      }
      else if ((n.toLowerCase().contains("durian")) || (n.toLowerCase().contains("fruit"))){
        print("You ate the durian. The fruit was smelly, but you feel more full. Where to next?");
        System.out.print(">>");
        in = new Scanner(System.in);
        String x = in.nextLine();
        pickRoom(x);
      }
      else{
        print("Where to next?");
        System.out.print(">>");
        in = new Scanner(System.in);
        String x = in.nextLine();
        pickRoom(x);
      }
    }
    //the bathroom
    else if (s.toLowerCase().contains("bathroom")){
      print("You take a satisfying dump. Where to next?");
      System.out.print(">>");
      Scanner in = new Scanner(System.in);
      String n = in.nextLine();
      pickRoom(n);
    }
    //where you end up, no matter what
    else{
      print("For some reason, your gut tells you to check out the closet in your room.");
      print("You see that your clothes are a mess, but you are also intrigued by a light coming from the back.");
      print("What do you do: organize your closet, close the closet, or follow the light?");
      System.out.print(">>");
      Scanner in = new Scanner(System.in);
      String p = in.nextLine();
      pickCloset(p);
    }
  }
  
  public static void pickCloset(String p){
    //organizing gets you rewards, in game and irl
    if (p.toLowerCase().contains("organize")){
      Narnia.armor = true;
      print("You find a shiny armor suit, probably your dad's. What next?");
      System.out.print(">>");
      Scanner in = new Scanner(System.in);
      String l = in.nextLine();
      //but you can only organize once; takes care of cheats
      if (l.toLowerCase().contains("organize")){
        print("Your closet is already organized!");
        System.out.print(">>");
        in = new Scanner(System.in);
        l = in.nextLine();
        pickCloset(l);
      }
      else{
        pickCloset(l);
      }
    }
    //you close the closet like a wimp and get DEAD
    else if (p.toLowerCase().contains("close "))
      print("You close the closet door, have an aneurism, and die. Your game is OVER.");
    //go to Narnia
    else{
      print("You find yourself in the wintery world of Narnia, overtaken by the Evil White Queen!");
      print("Do you look around, or talk to the friendly looking goatman?");
      System.out.print(">>");
      Scanner in = new Scanner(System.in);
      p = in.nextLine();
      tumnus(p);
    }
  }
  
  public static void tumnus(String x){
    //check out your surroundings
    if (x.toLowerCase().contains("look")){
      print("You look around. It's winter.");
      print("What now?");
      System.out.print(">>");
      Scanner in = new Scanner(System.in);
      String p = in.nextLine();    
      tumnus(p);
    }
    else{
      //Tumnus gets kidnapped. What do you do?
      print("Apparently the goatman's name is Tumnus. He invites you to his house for tea. He is very nice but he seems distracted. He keeps looking around.");
      print("Suddenly, a gigantic troll jumps out and steals your new friend. What do you do: go after him or pretend that it never happened and stay back?");
      System.out.print(">>");
      Scanner in = new Scanner(System.in);
      String w = in.nextLine();    
      saveTumnus(w);
    }
  }
  
  
  
  
  //DO YOU SAVE TUMNUS OR STAY BEHIND?
  public static void saveTumnus(String w){
    if ((w.toLowerCase().contains("pretend")) || (w.toLowerCase().contains("stay"))){
      Narnia.traitor = true;
      // do you find a weapon?
      int n = (int) (Math.random()*2);
      if (n == 0){
        Narnia.weapon = true;
        print("You found a weird looking dagger expelling magical dust with every swing.");
      }
      troll();
    }
    
    else{
      miniGameChase();
    }
  }
  
  //WHAT HAPPENS WHEN YOU STAY BEHIND
  public static void troll(){
    boolean die;
    
    print("Suddenly a troll pops up out of nowhere. You start to battle!");
    //fight the troll if you have the weapon
    
    die = battleGen(100);
    if (die){
      
      print("Oh No! The troll killed you!");
    }
    else{
      print("Good job! You slayed the troll!");
      print("The White Queen sees how cowardly you are for not saving Tumnus and summons you to her.");
      print("Do you fight her to prove your worth, or not?");
      System.out.print(">>");
      Scanner in = new Scanner(System.in);
      String z = in.nextLine();
      whiteQueen(z);
      
    }   
  }
  ///////////////////////////////////////////////////////////////////////////////// 
  
  
  
  public static boolean battleGen(int oppHP){
    int heroHP = Narnia.yourHP; // 100 +? 100 (from armor)
    int extradmg = 0;
    
    if (Narnia.armor == true){
      heroHP += 100;
    }
    if (Narnia.weapon == true){
      extradmg += 15;
    }
    
    return attack(heroHP, oppHP, extradmg);
    
  }
  
  //////////////////////////////////////////////////////////////////////////////////////////
  
  ////////////////////////ATTACK FUNCTION/////////////////////////////////////////////
  public static boolean attack(int heroHP, int oppHP, int extradmg){
    boolean ret = true;
    print("Your Health: " + heroHP);
    print("Opponent's Health: " + oppHP);
    print("");
    
    while (heroHP>0 && oppHP>0){
      int damage = 0;
      print("Choose an Attack: \nSlap \tKick \nPunch \tSlam");
      System.out.print(">>");
      Scanner in = new Scanner(System.in);
      String attack = in.nextLine();
      
      damage += extradmg; // if applicable, +25
      
      
      if (attack.toLowerCase().contains("slap")){ // 0 - 10 damage
        int damagenew = extradmg + ((int) (Math.random() * 11));
        oppHP -= damagenew; 
      }
      else if (attack.toLowerCase().contains("kick")){ // 10-15
        int damagenew = extradmg + ((int) (Math.random() * 6)+10);
        oppHP -= damagenew; 
      }
      else if (attack.toLowerCase().contains("punch")){// 10 - 15
        int damagenew = extradmg + ((int) (Math.random() * 6)+10);
        oppHP -= damagenew; 
      }
      else  { // If they dont choose, or choose 'slam', they automatically do the slam attack:  0 - 25 damage
        int damagenew = extradmg + ((int) (Math.random() * 21));
        oppHP -= damagenew; 
      }
      
      int oppdmg = (int) (Math.random() * 21);
      heroHP -= oppdmg;
      print("You used " + attack + ". Your opponent used " + attack + " as well.");
      print("Your Health: " + heroHP);
      if (oppHP >= 0){
        print("Opponent's Health: " + oppHP);
      }
      else{
        print("Opponent's Health: 0");
        print("");
      }
      
      if (heroHP <= 0) ret = true;
      else if (oppHP <= 0) ret = false;
    }
    return ret;
  }
  
  
  //////////////////////////////////////////////////////////////
  //WHAT HAPPENS IF YOU CHOOSE TO SAVE TUMNUS
  public static void miniGameChase(){
    print("To save Tumnus, you must answer at least 2 of the following 4 questions correctly.");
    int score = 0;
    //riddles
    String[] door0 = {"If I drink, I die. If I eat, I'm fine. What am I?","What gets more wet the more it dries?","What's at the end of a rainbow?","What has a face and two hands but no arms or legs?"};
    String[] door1 = {"Poor people have it, rich people need it, if you eat it you die. What is it?","What goes up and does not come back down?","Brothers and sisters I have none, but this man's father is my father's son. Who is this man?","A man walks into the bar, goes up to the bartender, the bartender scares him, the man says thank you and walks out. What happened?" };
    String[] door2 = {"Where is most of the world's fresh water located?","What is the capital of Canada?","What's the capital of California?","What is the capital of Australia?"};
    String[] door3 = {"What is converted into alcohol during brewing?","In which city was Martin Luther King assasinated?","What day of the week are you most likely to have a heart attack?","What do you call an infant whale?"};
    //answers
    String[] door0ans = {"fire","towel","w","clock"};
    String[] door1ans = {"nothing","age","my son","hiccup"};
    String[] door2ans = {"antarctica","ottowa","sacramento","canberra"};
    String[] door3ans = {"sugar","memphis","monday","calf"};
    //randomly choose which riddle
    int num0 = (int) (Math.random()*4);
    print(door0[num0]);
    System.out.print(">>");
    Scanner in = new Scanner(System.in);
    String d0 = in.nextLine();
    if (d0.toLowerCase().contains(door0ans[num0])){
      print("Correct!");
      ++score;
    }
    else{
      print("Incorrect! The correct answer was " + door0ans[num0]);
    }
    int num1 = (int) (Math.random()*4);
    print(door1[num1]);
    System.out.print(">>");
    in = new Scanner(System.in);
    String d1 = in.nextLine();
    if (d1.toLowerCase().contains(door1ans[num1])){
      print("Correct!");
      ++score;
    }
    else{
      print("Incorrect!  The correct answer was " + door1ans[num1]);
    }
    int num2 = (int) (Math.random() * 4);
    print(door2[num2]);
    System.out.print(">>");
    in = new Scanner(System.in);
    String d2 = in.nextLine();
    if (d2.toLowerCase().contains(door2ans[num2])){
      print("Correct!");
      ++score;
    }
    else{
      print("Incorrect!  The correct answer was " + door2ans[num2]);
    }
    int num3 = (int) (Math.random() * 4);
    print(door3[num3]);
    System.out.print(">>");
    String d3 = in.nextLine();
    if (d3.toLowerCase().contains(door3ans[num3])){
      print("Correct!");
      ++score;
    }
    else{
      print("Incorrect!  The correct answer was " + door3ans[num3]);
    }
    if (score >= 1){
      print("Congratulations! You won! You got " + score + " correct!");
      print("You have arrived at the White Queen's castle. You will fight her to get back Tumnus.");
      whiteQueen("fight");
    }
    else{
      print("You lost and died! Your game is over!");
    }
  }
  
  //WHAT HAPPENS IF YOU SAVE TUMNUS/BEAT THE MINIGAME
  public static void whiteQueen(String z){
    if (z.toLowerCase().contains("fight")){
      //If you win I think?
      if (battleGen(200) == false){
        aslan();
      }
      else print("You died!");
    }
    else{
    //Queen kills you
      print("With a swing of her magical wand, she freezes you and then drops you on the floor...shattering your body.");
    }
  }
  
  
  //WHAT HAPPENDS IF YOU DEFEAT THE QUEEN
  public static void aslan(){
    //Aslan comes to see you
    print("You beat the queen. Now, you see a strange animal figure appearing through the castle doors. It's Aslan, the Great Lion!");
    print("He appears to you because you were courageous enough to fight the queen.");
    //if you suck and are a traitor who didn't save Tumnus originally
    if (Narnia.traitor == true){
      print("Oh no! Who's that?! Its Tumnus. He's come back to get revenge. How could you not save him?");
      print("Are you sorry for abandoning him?");
      Scanner in = new Scanner(System.in);
      System.out.print(">>");
      String sorry = in.nextLine();
      //if you're sorry
      if (sorry.toLowerCase().contains("no")){
        print("Tumnus takes out his dagger and stabs you to death.\nYou died.");
      }
      //or you suck SO BAD
      else{
        print("He forgives you because he trusts in you now. After all, you killed the White Queen.");
        print("Aslan then comes up to you and requests that you stay in Narnia. Do you decide to go home, or stay in Narnia?");
        endChoice();
      }
    }
    //or you weren't horrible
    else{
      print("Tumnus thanks you for all of your bravery it took to save him. Aslan then comes up to you and");
      print("requests that you stay in Narnia. Do you decide to go home, or stay in Narnia?");
      endChoice();
    }
  }
  //deciding your general future
  public static void endChoice(){
    System.out.print(">>");
    Scanner in = new Scanner(System.in);
    String endlife = in.nextLine();
    //go home
    if (endlife.toLowerCase().contains("home")){
      print("As soon as you go back through the closet and return home, you have a massive heart attack and die.\nYOU DIED.");
    }
    //stay in Narnia
    else narniaLife();
  }
  //deciding your future in Narnia
  public static void narniaLife(){
    print("As being the hero of Narnia, you have the choice of living different lifestyles here. Would you like to be an adventurer, high royalty, or just a commoner of Narnia?");
    System.out.print(">>");
    Scanner in = new Scanner(System.in);
    String choice = in.nextLine();
    //adventurer
    if (choice.toLowerCase().contains("adventure")){
      print("You live out the rest of your years in Narnia looking for the thrill of life. You scavenge through the forests, the oceans, and the skies of Narnia.");
    }
    //royal
    else if ((choice.toLowerCase().contains("royalty")) || (choice.toLowerCase().contains("king")) || (choice.toLowerCase().contains("queen"))){
      print("You live out the rest of your years loved by the people and ruling fairly in the High Court of Narnia.");
    }
    //normal
    else{
      print("You live out your life with content. You have a house. a steady source of income, amd many friends in Narnia.");
    }
  }
  public static void narniaPersonalityQuiz(){
    print("The game might be over, but the fun never ends! Take our Narnia Personality Quiz to find out where you belong!");
    int aCount,bCount,cCount,other;
    aCount = bCount = cCount = other = 0;
    print("Question 1: A friend of yours is being bullied. What do you do?");
    print("\ta) Jump in immediately and defend them");
    print("\tb) Have others stop the bully");
    print("\tc) Do nothing but offer support afterwards");
    System.out.print(">>");
    Scanner in = new Scanner(System.in);
    String s = in.nextLine();
    if (s.toLowerCase().contains("a")){
      ++aCount;
    }
    else if (s.toLowerCase().contains("b")){
      ++bCount;
    }
    else if (s.toLowerCase().contains("c")){
      ++cCount;
    }
    else ++other;
    print("Question 2: If you could have any superpower, which would you pick?");
    print("\ta) Superstrength or invicibility");
    print("\tb) Mind control");
    print("\tc) Invisibility");
    System.out.print(">>");
    in = new Scanner(System.in);
    s = in.nextLine();
    if (s.toLowerCase().contains("a")){
      ++aCount;
    }
    else if (s.toLowerCase().contains("b")){
      ++bCount;
    }
    else if (s.toLowerCase().contains("c")){
      ++cCount;
    }
    else ++other;
    print("Question 3: What would you say is your worst trait?");
    print("\ta) Recklessness");
    print("\tb) I have no bad traits");
    print("\tc) I'm too average");
    System.out.print(">>");
    in = new Scanner(System.in);
    s = in.nextLine();
    if (s.toLowerCase().contains("a")){
      ++aCount;
    }
    else if (s.toLowerCase().contains("b")){
      ++bCount;
    }
    else if (s.toLowerCase().contains("c")){
      ++cCount;
    }
    else ++other;
    print("Question 4: How do you take your coffee?");
    print("\ta) Black");
    print("\tb) I drink tea");
    print("\tc) With milk and sugar");
    System.out.print(">>");
    in = new Scanner(System.in);
    s = in.nextLine();
    if (s.toLowerCase().contains("a")){
      ++aCount;
    }
    else if (s.toLowerCase().contains("b")){
      ++bCount;
    }
    else if (s.toLowerCase().contains("c")){
      ++cCount;
    }
    else ++other;
    print("Question 5: How would you like to be remembered in history?");
    print("\ta) The Bold");
    print("\tb) The Great");
    print("\tc) The Humble");
    System.out.print(">>");
    in = new Scanner(System.in);
    s = in.nextLine();
    if (s.toLowerCase().contains("a")){
      ++aCount;
    }
    else if (s.toLowerCase().contains("b")){
      ++bCount;
    }
    else if (s.toLowerCase().contains("c")){
      ++cCount;
    }
    else ++other;
    String adventurer = "You love to explore and try new things. In your free time, you roam the world around you. You're always up for something daring!";
    String royalty = "You were born for the castle! Ruling comes naturally to you. You are charismatic, intelligent, and capable.";
    String commoner = "You are content with what you have. Your humility attracts many friends and you live a happy life.";
    String rebel = "Looks like our quiz can't quite keep you down! This result is for those who are different than everyone else.";
    int res = Math.max(Math.max(Math.max(aCount,bCount),cCount),other);
    System.out.print("You got: ");
    if (res == aCount){
      print("Adventurer!");
      print(adventurer);
  }
    else if (res == bCount){
      print("Royalty!");
      print(royalty);
    }
    else if (res == cCount){
      print("Commoner!");
      print(commoner);
    }
    else{
      print("Rebel!");
      print(rebel);
    }
  }
}