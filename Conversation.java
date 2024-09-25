import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Conversation {

  Scanner input; // user input number of rounds 
  Random choice; // random to grab different outputs from lists 

  ArrayList<String> chat_log; // array to hold convo strings in ,
  // response variabes
  HashMap<String, String> chat_dict; 
  String[] intros; // not changing by program so static 
  String[] non_responses;
  String[] goodbyes; 

/**
 * Constructor
 * @param input Scanner to read number of rounds input 
 * @param chat_log empty ArrayList to add conversation scripts to
 * @param choice Random class to generate random indexes for lists of filler words/goodbyes 
 * @param chat_dict HashMap (like Python dictionary) containing input keys and their corresponding output values 
 * @param intros string list of different introduction words
 * @param non_responses string list of different mid-convo statements
 * @param goodbyes string list of different goodbyes
 */

public Conversation(){ // Constructor does not take any inputs 

  this.input = new Scanner(System.in); // initilizes scanner 
  this.chat_log = new ArrayList<>(); // initilizes empty list, arraylist lets be variable in size
  this.choice = new Random(); // random class to generate indices for random response lists 

  // sets all the chatbot's vocabulary 

  // inputs keys/values into HashMap to access when a key is seen in the input -> print a value in the output 
  this.chat_dict = new HashMap<>(); // initilizes dictionary (map) 
  this.chat_dict.put("YOUR", "my");  
  this.chat_dict.put("YOU", "i"); 
  this.chat_dict.put("I ", "you "); 
  this.chat_dict.put("ME", "you"); 
  this.chat_dict.put("AM", "are");  
  this.chat_dict.put("MY", "your");
  
  // sets static conversation lists to reference when no keywords are in the user input 
  this.intros = new String[]{"oh hey man! what's up?", "Hello!!", "Hi!", "Hey.", "What's on your mind?", "Tell me something cool"}; 
  this.non_responses = new String[]{"neato", "cool", "suure", "if you say so", "umm ok", "dude what"}; 
  this.goodbyes = new String[]{"ok bye", "see you", "Bye!!", "gooooodbye"}; 
}
/**
 * Chats with the chatbot -> asks for a number of rounds n, then accepts user input 
 * for n rounds. If the chatbot detects a keyword in the user input, it will 
 * respond by replacing the keyword with a different. If the chatbot does not 
 * detect any keywords, it will print out a random, non-related response. 
 * Chat transcript is stored in an ArrayList and is printed at the end of the conversation. 
 */
public void letsChat(){
  // first we need to figure out how many rounds to chat for 
  // ask the user for a number of rounds 
  System.out.println("ENTER NUMBER OF ROUNDS:");
  int num_rounds = this.input.nextInt(); // saves user input to num_rounds variable 

  // picks introduction phrase 
  int intro_index = this.choice.nextInt(this.intros.length); // random index for list 
  String intro = this.intros[intro_index]; // ok picks random item in list for bot response 

  // adds intro to chat log + prints 
  this.chat_log.add(intro); // adds to chatlog  
  System.out.println(intro);  // prints out intro 

  // iterates over chat code for number of conversation rounds 
  for (int i = 0; i < num_rounds; i++) { // iterates numbers of rounds 
     Scanner text = new Scanner(System.in);  // new scanner for text (why can't I use the old one?)
     String user_input = text.nextLine(); // gets user input 

     this.chat_log.add(user_input); // adds to log 
     user_input = user_input.toUpperCase(); // makes input uppercase to deal with not replacing words already replaced 
    
     // new variable, turns to true if any keywords are detected in the user input 
      boolean yes_words = false; // to check if the user input had any keywords in it, bc will change response type  
      // iterates over keys in HashMap to check if any are in the string 
      for (String word: this.chat_dict.keySet()) {// ok iterate over all the keys in dictionary 
          if (user_input.contains(word)) { // issue is replaxing words weirdly // check so not in middle 
            user_input = user_input.replace(word, this.chat_dict.get(word)); // replace key with value
            user_input = user_input.replace("?", "."); // also replace any ? at end with a . 
            yes_words = true;  // changes the no_words variable to false 
          }
        }
        // makes user_input (with or without replaced characters) lowercase again 
        user_input = user_input.toLowerCase(); 

        // generates random choice for bot response 
       int randomindx = this.choice.nextInt(this.non_responses.length); // random index for list 
       String bot_response = this.non_responses[randomindx]; // ok picks random item in list for bot response 

       // if any keywords have been found, sets the bot response to the altered user input, 
       if (yes_words){ // this would mean no substitution happened bc no question 
         bot_response = user_input; // if replaced words, make the bot response be the modified user input
          }

        // prints out bot response - either a random unrelated phrase or an altered version of the user's last input
        System.out.println(bot_response);
        this.chat_log.add(bot_response); // adds to chat log
  }
   // ends loop over number of rounds - time to say goodbye 
  // picks random goodbye same way picked random response 
  int indxgoodbye = this.choice.nextInt(goodbyes.length);
  String bot_bye = goodbyes[indxgoodbye]; // assigns string to bot_bye 

  // says goodbye 
  System.out.println(bot_bye); // prints goodbye
  this.chat_log.add(bot_bye); // adds goodbye to list 

  // prints out transcript once done iterating over number of conversations + said goodbye 
  System.out.println(""); // prints empty space 
  System.out.println("TRANSCRIPT: "); // prints text indicating transcript printing 
  for (int i = 0; i < this.chat_log.size(); i++) { // iterates over strings in array and prints them 
    System.out.println(this.chat_log.get(i)); // prints strings in log 
}}

/**
 * Chats with chatbot by creating an instance of the Conversation class 
 * and calling the letsChat() function
 */

public static void main(String[] args) {
  System.out.println("Welcome to CHATBOT: ");
  Conversation chatbot = new Conversation();  // initilize class to call 
    // chats 
    chatbot.letsChat(); // calls function to do that 
}
}

