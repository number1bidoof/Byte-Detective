import java.io.*;

/**
 * Lab 1: Byte Detective
 * 
 * Practice reading raw bytes from a file using FileInputStream.
 * Complete the methods below to read, interpret, and analyze binary data.
 * 
 * @author Ayah Abdalla
 */
public class ByteDetective {
    
    // Path to the mystery file (same directory as this .java file)
    private static final String MYSTERY_FILE = "mystery.bin";
    
    /**
     * Task 1: Read and return the first byte from the file.
     * 
     * THIS METHOD IS FULLY IMPLEMENTED AS AN EXAMPLE.
     * Study this code carefully - you'll use this pattern in other methods!
     * 
     * @return the first byte as an int (0-255), or -1 if file is empty
     */
    public static int readFirstByte() {
        // Try-with-resources automatically closes the stream
        try (FileInputStream fis = new FileInputStream(MYSTERY_FILE)) {
            
            // read() returns an int: 0-255 for a byte, or -1 for EOF
            int firstByte = fis.read();
            return firstByte;
            
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + MYSTERY_FILE);
            return -1;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return -1;
        }
    }
    
    /**
     * Task 2: Read and display ALL bytes from the file.
     * 
     * TODO: Implement this method
     * 
     * Requirements:
     * - Use a while loop with the EOF pattern: while ((b = fis.read()) != -1)
     * - Print each byte value as a number
     * - Count and display the total number of bytes read
     * - Use try-with-resources
     * 
     * Hint: Review Lecture 1, Slide 3 for the standard reading loop pattern
     */
    public static void readAllBytes() {
        int byteVal = 0;

            try (FileInputStream input = new FileInputStream(MYSTERY_FILE)) {
                int b;
                while ((b = input.read()) != -1) {
                    byteVal++;
                    System.out.println("Byte " + byteVal + ": " + b);
                }
                System.out.println("Total bytes read: " + byteVal);
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
    }
    
    /**
     * Task 3: Display all bytes in hexadecimal format.
     * 
     * TODO: Implement this method
     * 
     * Requirements:
     * - Read all bytes (same loop as Task 2)
     * - Convert each byte to hex using: String.format("%02X", byteValue)
     * - Display in format: "48 65 6C 6C 6F ..."
     * - Use try-with-resources
     * 
     * Hint: %02X means "2-digit uppercase hexadecimal"
     * Example: byte value 72 becomes "48" in hex
     */
    public static void displayAsHex() {
        try (FileInputStream input = new FileInputStream(MYSTERY_FILE)) {
            int c;
            while ((c = input.read()) != -1) {
                System.out.printf("%02X ", c);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    /**
     * Task 4: Attempt to display bytes as ASCII characters.
     * 
     * TODO: Implement this method
     * 
     * Requirements:
     * - Read all bytes (same loop pattern)
     * - Cast each byte to char: (char) byteValue
     * - Print the character
     * - Use try-with-resources
     * 
     * Important: This demonstrates the "broken mental model"!
     * - Works for ASCII (65-90, 97-122, etc.)
     * - Breaks for non-ASCII bytes (128-255)
     * - This is why we use FileReader for text files!
     * 
     * Hint: If mystery.bin contains "Hello", you should see those letters
     */
    public static void attemptAsAscii() {
        try (FileInputStream input = new FileInputStream(MYSTERY_FILE)) {
            int d;
            while ((d = input.read()) != -1) {
                System.out.println((char) d);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    /**
     * Task 5: Analyze the file and display statistics.
     * 
     * TODO: Implement this method
     * 
     * Requirements:
     * - Count total bytes
     * - Track minimum byte value (hint: start at 256)
     * - Track maximum byte value (hint: start at -1)
     * - Calculate average byte value (hint: keep sum, divide by count)
     * - Display all statistics
     * - Use try-with-resources
     * 
     * Hint: Read file once, updating min/max/sum as you go
     * 
     * Example output:
     * Total bytes: 42
     * Min value: 32
     * Max value: 122
     * Average: 78.5
     */
    public static void analyzeFile() {
       int count = 0;
       int min = 256;
       int max = -1;
       long sum = 0;
        try (FileInputStream input = new FileInputStream(MYSTERY_FILE)) {

            int e;
            while ((e = input.read()) != -1) {
                count++;
                sum += e;
                if (e < min) {
                    min = e;
                }
                if (e > max) {
                    max = e;
                }
            }

            if (count > 0) {
                double average = (double) sum / count;
                System.out.println("Total bytes: " + count);
                System.out.println("Min value: " + min);
                System.out.println("Max value: " + max);
                System.out.println("Average: " + average);
            } else {
                System.out.println("File is empty");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    /**
     * Main method - runs all tasks in sequence.
     * 
     * DO NOT MODIFY THIS METHOD.
     * Implement the methods above, then run this to test your code.
     */
    public static void main(String[] args) {
        System.out.println("=== Lab 1: Byte Detective ===\n");
        
        System.out.println("=== Task 1: First Byte (Example) ===");
        int firstByte = readFirstByte();
        if (firstByte != -1) {
            System.out.println("First byte: " + firstByte);
            System.out.println("As hex: " + String.format("%02X", firstByte));
            System.out.println("As char: '" + (char) firstByte + "'");
        }
        System.out.println();
        
        System.out.println("=== Task 2: All Bytes ===");
        readAllBytes();
        System.out.println();
        
        System.out.println("=== Task 3: Hex Display ===");
        displayAsHex();
        System.out.println();
        
        System.out.println("=== Task 4: ASCII Attempt ===");
        attemptAsAscii();
        System.out.println();
        
        System.out.println("=== Task 5: File Analysis ===");
        analyzeFile();
        System.out.println();
        
        System.out.println("=== Lab Complete! ===");
        System.out.println("Review your output and discuss the questions in README.md");
    }
}