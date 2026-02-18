import java.util.*;
import java.io.*;

public class HashTable {
    int tableSize = 13;
    String[][] keys = new String[tableSize][];
    int currentHashes = 0;

// Methods you have to supply:

  public void put(String value) {
    put(value, "");
  }

  public void put(String key, String value) {
    if (currentHashes > tableSize * 0.66) {
        int newSize = tableSize * 2;
        resize(newSize);
    }

    int hashCode = key.hashCode();
    int arrayIndex = Math.abs(hashCode) % tableSize;

    // linear probing to find slot or update existing key
    int start = arrayIndex;
    while (keys[arrayIndex] != null && !keys[arrayIndex].equals(key)) {
      arrayIndex = (arrayIndex + 1) % tableSize;
      if (arrayIndex == start) break; // table full (shouldn't happen because of resize)
    }

    if (keys[arrayIndex] == null) {
      keys[arrayIndex] = new String[1];
      keys[arrayIndex][0] = key;
      currentHashes++;
    } else {

    }
  }

  private void resize(int newSize) {
    String[][] newKeys = new String[newSize][];
    for (int i = 0; i < keys.length; i++) {
      if (keys[i] != null) {
        String k = keys[i][0];
        int hashCode = k.hashCode();
        int arrayIndex = Math.abs(hashCode) % newSize;
        while (newKeys[arrayIndex] != null) {
          arrayIndex = (arrayIndex + 1) % newSize;
        }
        newKeys[arrayIndex] = keys[i];
      }
    }
    tableSize = newSize;
    keys = newKeys;
  }

  public String get(String key) {
    int hashCode = key.hashCode();
    int arrayIndex = Math.abs(hashCode) % tableSize;
    int start = arrayIndex;
    while (keys[arrayIndex] != null) {
        for (int i = 0; i < keys[arrayIndex].length; i++) {
            if (key.equals(keys[arrayIndex][i])) {
                return keys[arrayIndex][i]; // Return value if key is found
            }
        }
      arrayIndex = (arrayIndex + 1) % tableSize;
      if (arrayIndex == start) break;
    }
    return null;
  }

  private void rehashFrom(int startIndex) {
    int idx = startIndex;
    while (keys[idx] != null) {
      String k = keys[idx][0];
      keys[idx] = null;
      currentHashes--;
      put(k);
      idx = (idx + 1) % tableSize;
    }
  }

  public String remove(String key){
    int hashCode = key.hashCode();
    int arrayIndex = Math.abs(hashCode) % tableSize;
    int start = arrayIndex;
    while (keys[arrayIndex] != null) {
        for (int i = 0; i < keys[arrayIndex].length; i++) {
            if (key.equals(keys[arrayIndex][i])) {
                keys[arrayIndex][i] = null;
                currentHashes--;
                // Rehash the cluster following the removed slot
                rehashFrom((arrayIndex + 1) % tableSize);
            }
        }
      arrayIndex = (arrayIndex + 1) % tableSize;
      if (arrayIndex == start) break;
    }
    return null;
  }

// This iterator should be able to retrieve the next key in your hashtable. 
// Note that specifically this part of your program is not going to be O(1) 
// while the above should be relatively close to it. That means you SHOULD 
// NOT use the iterator inside the above methods, it should only be used for 
// the save and load functions already provided as well as the print function 
// described below.

  public Iterator keys() {
    ArrayList<String> keys = new ArrayList<String>();
    for (int i = 0; i < this.keys.length; i++) {
      if (this.keys[i] != null) {
        for (int j = 0; j < this.keys[i].length; j++) {
            if (this.keys[i][j] != null) {
                keys.add(this.keys[i][j]);
            }
            }
      }
    }
    return keys.iterator();
  }

// Use the iterator to iterate through the hashTable and System.out.println 
// all the key value pairs. Each key value pair should go on it’s own line.

  public void print(){
    Iterator iterator = keys();
    java.util.LinkedHashMap<String,String> map = new java.util.LinkedHashMap<>();
    while (iterator.hasNext()) {
      String key = (String)iterator.next();
      if (key == null) continue;
      if (!map.containsKey(key)) {
        map.put(key, get(key));
      }
    }
    for (java.util.Map.Entry<String,String> e : map.entrySet()) {
      System.out.print(e.getValue() + ", ");
    }
  }


	/*
	 * Loads this HashTable from a file named "Lookup.dat".
	 */
     public void load() {
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        
        // Open the file for reading
        try {
            File f = new File(System.getProperty("user.home"), "Lookup.dat");
            fileReader = new FileReader(f);
            bufferedReader = new BufferedReader(fileReader);
        }
        catch (FileNotFoundException e) {
            System.err.println("Cannot find input file \"Lookup.dat\"");
        }
        
        // Read the file contents and save in the HashTable
        try {
            while (true) {
                String key = bufferedReader.readLine();
                if (key == null) return;
                String value = bufferedReader.readLine();
                if (value == null) {
                    System.out.println("Error in input file");
                    System.exit(1);
                }
                String blankLine = bufferedReader.readLine();
                if (!"".equals(blankLine)) {
                    System.out.println("Error in input file");
                    System.exit(1);
                }
                put(key, value);
            }
        }
        catch (IOException e) {
            e.printStackTrace(System.out);
        }
        
        // Close the file when we're done
        try {
            bufferedReader.close( );
        }
        catch(IOException e) {
            e.printStackTrace(System.out);
        }
    }

	/**
	 * Saves this HashTable onto a file named "Lookup.dat".
	 */
	public void save() {
        FileOutputStream stream;
        PrintWriter printWriter = null;
        Iterator iterator;
        
        // Open the file for writing
        try {
            File f = new File(System.getProperty("user.home"), "Lookup.dat");
            stream = new FileOutputStream(f);
            printWriter = new PrintWriter(stream);
        }
        catch (Exception e) {
            System.err.println("Cannot use output file \"Lookup.dat\"");
        }
       
        // Write the contents of this HashTable to the file
        iterator = keys();
        while (iterator.hasNext()) {
            String key = (String)iterator.next();
            printWriter.println(key);
            String value = (String)get(key);
            value = removeNewlines(value);
            printWriter.println(value);
            printWriter.println();
        }
       
        // Close the file when we're done
        printWriter.close( );
    }
    
    /**
     * Replaces all line separator characters (which vary from one platform
     * to the next) with spaces.
     * 
     * @param value The input string, possibly containing line separators.
     * @return The input string with line separators replaced by spaces.
     */
    private String removeNewlines(String value) {
        return value.replaceAll("\r|\n", " ");
    }
}




