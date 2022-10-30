import java.util.*;
import java.io.*;

public class WordCounter 
{
    String file;

    public WordCounter (String file)
    {
        this.file = file;
    }

    
    /** 
     * A method to begin and carry out the processing of the file.
     * This method calls for the string to be trimmed and filtered so only the words remain. 
     * Next, it calls for the words to be placed in a list.
     * Finally it calls for the word frequency to be counted and only the top 20 to be returned.
     * @param file
     * @return String[]
     */
    static String[] begin (String file) // make this return an item
    {
        String fullText = ReadFile("WordCounter\\TheRavenByEdgarAllanPoe.html");

        // Remove HTML tags
        String content = removeHTML(fullText);

        //Trim the string before the poem's start, and after the poem's end
        content = trimStringBefore(content, "[ #45484 ]"); // Last text before the poem's title
        content = trimStringAfter(content);

        // Remove special characters
        content = characterRemoval(content);

        // Organize the content
        List<String> wordList = stringToList(content); 
        
        // make this a returnable
        String[] wordArray = wordFrequency(wordList); 
        return wordArray;
    }
    
    /** 
     * A method to read the file
     * @param file
     * @return String
     */
    private static String ReadFile(String file)
    {
        String result = null;

        // String Builder to append the string into the html
        StringBuilder html = new StringBuilder();

        try
        {
            //Read the file
            FileReader fr = new FileReader(file);

            // Initialize the buffered reader to get the String append to the String builder
            BufferedReader br = new BufferedReader(fr);

            String val;

            // Read the String until we get the null string and appending string
            while((val = br.readLine()) != null)
            {
                html.append(val);
            }

            // Convert the HTML to a string
           result = html.toString();
            
            br.close();

        } catch (Exception e)
        {
            System.out.println("Error reading file.");
            e.getStackTrace();
        }
        
        return result;
    }

    
    /** 
     * A method to remove HTML tags
     * @param content
     * @return String
     */
    public static String removeHTML(String content)
    {
        String htmlTagPattern = "\\<.*?\\>";

        return content.replaceAll(htmlTagPattern, " ");
    }

    
    /** 
     * Remove string prior to title of the poem
     * @param content
     * @param start
     * @return String
     */
    public static String trimStringBefore(String content, String start)
    {
        return content.substring(content.indexOf(start) + 10);
    }

    
    /** 
     * A method to remove everything after the poem's end
     * @param content
     * @return String
     */
    public static String trimStringAfter(String content)
    {
        int index = content.indexOf("***"); // First unique characters after poem

        String result = content.substring(0, index);

        return result;
    }

    
    /** 
     * A method to clean up the string
     * @param content
     * @return String
     */
    public static String characterRemoval(String content)
    {
        content = content.replaceAll("[^a-zA-Z0-9\\s]", " ");

        return content;
    }
    
    
    /** 
     * A method to put the words from the poem into an array
     * @param content
     * @return List<String>
     */
    public static List<String> stringToList(String content)
    {
        String splitHere = " ";
        String[] temp = content.split(splitHere);

        ArrayList<String> tempList = new ArrayList<>(); // temporaty list

        for (int i = 0; i < temp.length; i++) 
        {
            tempList.add(temp[i].toLowerCase());
            spaceSaver(tempList);
        }

        return tempList;
    }

    
    /**
     * A method to remove blank spaces 
     * @param wordList
     * @return List<String>
     */
    static List<String> spaceSaver(List<String> wordList)
    {
        try
        {
            for(int i = 0; i < wordList.size(); i++)
            {
                if(wordList.get(i).isBlank())
                {
                    wordList.remove(i);
                }
            }

            for(int i = 0; i < wordList.size(); i++)
            {
                if(wordList.get(i).isEmpty())
                {
                    wordList.remove(i);
                }
            }

            for(int i = 0; i < wordList.size(); i++)
            {
                if(wordList.get(i).contains("\\s"))
                {
                    wordList.remove(i);
                }

            } 
        } catch (Exception e)
        {
            System.out.println("Cleaning error.");
            e.getStackTrace();
        }
        return wordList;
    }

    
    /** 
     * A method to count word frequencies
     * @param wordList
     * @return String[]
     */
    static String[] wordFrequency(List<String> wordList)
    {
        HashMap<String, Integer> hash = new HashMap<>();

        String[] wordArray = new String[20];

        // Count and store the frequency of each element
        for (String i : wordList)
        {
            Integer j = hash.get(i);
            hash.put(i, (j == null) ? 1 : j + 1); 
        }

        Map<String, Integer> hashSorted = sortByValue(hash);

        // Store only the first 20 values
        for (int i = 0; i < 20; i++)
        {
            String word = hashSorted.keySet().stream().skip(i).findFirst().get();

            Integer frequency = hashSorted.values().stream().skip(i).findFirst().get();

            wordArray[i] = word + "\t\t" + frequency;
        }
        return wordArray;
    }

    
    /** 
     * A method to sort the hashmap for word frequency
     * @param hash
     * @return HashMap<String, Integer>
     */
    static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hash)
    {
        // Create a list using the HashMap
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hash.entrySet());

        // Sort the list using lambda expression
        Collections.sort(list, (i1, i2) -> i1.getValue().compareTo(i2.getValue()));

        // Reverse the order of the list
        Collections.reverse(list);

        // Put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> aa : list)
        {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
