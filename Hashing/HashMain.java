public class HashMain {
    
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        hashTable.put("Alice");
        hashTable.put("30");
        hashTable.put("New York");
        System.out.println("Contents of the HashTable:");
        hashTable.save();
        hashTable.print();

        System.out.println(" ");
        System.out.println(" ");

        HashTable newHashTable = new HashTable();
        newHashTable.put("Bob");
        newHashTable.put("Kim");
        newHashTable.put("Jim");
        newHashTable.put("Paul");
        newHashTable.put("Mike");
        newHashTable.put("GamGam");
        newHashTable.save();
        System.out.println("Contents of the new HashTable:");
        newHashTable.print();

        System.out.println(" ");
        System.out.println(" ");

        newHashTable.put("Frank");
        newHashTable.put("Sally");
        newHashTable.put("Tom");
        newHashTable.put("Jerry");
        newHashTable.put("Spike");
        newHashTable.put("Tyke");
        newHashTable.save();
        System.out.println("Contents of the new HashTable after adding more entries (Over the original 2/3 of hash table length):");
        newHashTable.print();
        System.out.println(" ");
        System.out.println(" ");

        newHashTable.put("Larry");
        newHashTable.put("Curly");
        newHashTable.put("Moe");
        newHashTable.put("Shemp");
        System.out.println("Contents of the new HashTable after adding even more entries (Over the original hash table length):");
        newHashTable.print();
        System.out.println(" ");
        System.out.println(" ");



        hashTable.load();
        System.out.println("Contents of the HashTable after loading:");
        hashTable.print();
        System.out.println(" ");
        // Why does it do that???? I don't understand...

    }
}

