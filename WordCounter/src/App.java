import java.util.*;
import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {

        String fullText = ReadFile("TheRavenByEdgarAllanPoe.html");

        // Remove HTML tags
        String content = removeHTML(fullText);

        //Trim the string before the poem's start, and after the poem's end
        content = trimStringBefore(content, "[ #45484 ]"); // Last text before the poem's title
        content = trimStringAfter(content);

        // Remove special characters
        content = characterRemoval(content);

        // Organize the content
        List<String> wordList = stringToList(content); 
        
        wordFrequency(wordList);
        
    } 

    // A method to read the file 
    private static String ReadFile(String file){
        
        String result = "null";

        // String Builder to append the string into the html
        StringBuilder html = new StringBuilder();

        try{
            //Read the file
            FileReader fr = new FileReader(file);

            // Initialize the buffered reader to get the String append to the String builder
            BufferedReader br = new BufferedReader(fr);

            String val;

            // Read the String until we get the null string and appending string
            while((val = br.readLine()) != null){

                html.append(val);

            }

            // Convert the HTML to a string
            result = html.toString();
            
            br.close();

        } catch (Exception e){
            System.out.println("Error reading file.");
            e.getStackTrace();
        }
        
        return result;

    }

    // A mthod to remove HTML tags
    public static String removeHTML(String content){

        String htmlTagPattern = "\\<.*?\\>";

        return content.replaceAll(htmlTagPattern, " ");

    }

    // Remove string prior to title of the poem
    public static String trimStringBefore(String content, String start){

        return content.substring(content.indexOf(start) + 10);

    }

    // A method to remove everything after the poem's end
    public static String trimStringAfter(String content){

        int index = content.indexOf("***"); // First unique characters after poem

        String result = content.substring(0, index);

        return result;

    }

    // A method to clean up the string
    public static String characterRemoval(String content){

        content = content.replaceAll("[^a-zA-Z0-9\\s]", " ");

        return content;
    }
    
    // A method to put the words from the poem into an array
    public static List<String> stringToList(String content){

        String splitHere = " ";
        String[] temp = content.split(splitHere);

        ArrayList<String> tempList = new ArrayList<>(); // temporaty list

        for (int i = 0; i < temp.length; i++) {

            tempList.add(temp[i].toLowerCase());
            spaceSaver(tempList);
        }

        return tempList;
        
    }

    // A method to remove blank spaces
    static List<String> spaceSaver(List<String> wordList){
        
        try{

            for(int i = 0; i < wordList.size(); i++){

                if(wordList.get(i).isBlank()){

                    wordList.remove(i);
                }
            }

            for(int i = 0; i < wordList.size(); i++){

                if(wordList.get(i).isEmpty()){

                    wordList.remove(i);
                }
            }

            for(int i = 0; i < wordList.size(); i++){

                if(wordList.get(i).contains("\\s")){

                    wordList.remove(i);
                }

            }
            //System.out.println("This is a space test: after."); // Remove before submitting  
        } catch (Exception e){

            System.out.println("Cleaning error.");
            e.getStackTrace();
        }

        return wordList;
        
    }

    // A method to count word frequencies
    static void wordFrequency(List<String> wordList){

        HashMap<String, Integer> hash = new HashMap<>();

        // Count and store the frequency of each element
        for (String i : wordList){
            Integer j = hash.get(i);
            hash.put(i, (j == null) ? 1 : j + 1); 
        }

        Map<String, Integer> hashSorted = sortByValue(hash);

        // Print only the first 20 values
        for (int i = 0; i < 20; i++){

            String word = hashSorted.keySet().stream().skip(i).findFirst().get();

            Integer frequency = hashSorted.values().stream().skip(i).findFirst().get();

            System.out.println("Word:\t" + word + "\t\tFrequency: " + frequency);
        }
        
    }

    // A method to sort the hashmap for word frequency
    static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hash){

        // Create a list using the HashMap
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hash.entrySet());

        // Sort the list using lambda expression
        Collections.sort(list, (i1, i2) -> i1.getValue().compareTo(i2.getValue()));

        // Reverse the order of the list
        Collections.reverse(list);

        // Put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> aa : list){
            temp.put(aa.getKey(), aa.getValue());
        }
        
        return temp;

    }
}
