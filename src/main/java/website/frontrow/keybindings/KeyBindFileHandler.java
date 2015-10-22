package website.frontrow.keybindings;

import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Load and Save key binding configurations.
 */
public final class KeyBindFileHandler
        implements Logable,
                   KeyBindsObserver
{
    private static final KeyBindFileHandler INSTANCE = new KeyBindFileHandler();
    private static String filename = "bindings.txt";

    /**
     * Private constructor, Singleton.
     */
    private KeyBindFileHandler()
    {
        KeyBinds.addObserver(this);
    }

    /**
     * Returns the instance of the object.
     * @return Returns the instance of KeyBindFileHandler.
     */
    public static KeyBindFileHandler getInstance()
    {
        return INSTANCE;
    }

    /**
     * Save the current bindings to the file.
     * @throws IOException Throws exception when triggered.
     */
    public void saveBindings() throws IOException
    {
        File bindingFile = fileChecker(filename, true);
        fileWrite(bindingFile);
    }

    /**
     * Load bindings from file.
     * @throws IOException Throws exception when triggered.
     */
    public void loadBindings() throws IOException
    {
        File bindingFile = fileChecker(filename, false);

        if (!bindingFile.exists())
        {
            KeyBinds.createDefaultKeyMapping();
            return;
        }

        fileParse(bindingFile);
    }

    /**
     * Check if the file exists and if so, return the File.
     * @param fileToCheck Input the filename
     * @param createFile True: whether file has to be generated if it doesn't exist.
     * @return returns the File.
     * @throws IOException Throws exception when triggered.
     */
    public File fileChecker(String fileToCheck, boolean createFile)
            throws IOException
    {
        if (fileToCheck == null)
        {
            addToLog("[KBFH]\t[ERROR] Filename is null");
            throw new IllegalArgumentException("Filename is null");
        }

        File bindingFile = new File(fileToCheck);

        if (!bindingFile.exists() & createFile)
        {
            System.out.println("File does not exist, generating...");
            assert bindingFile.createNewFile();
        }

        return bindingFile;
    }

    /**
     * Write current keybindings to file.
     * @param file Input a file to write to.
     * @throws FileNotFoundException Throws when triggered.
     * @throws UnsupportedEncodingException Throws when triggered.
     */
    private void fileWrite(File file)
            throws FileNotFoundException,
                   UnsupportedEncodingException
    {
        addToLog("[KBFH]\tWriting to: " + file.getName());
        PrintWriter printWriter = new PrintWriter(file, "UTF-8");
        KeyBinds.printTo(printWriter);
        printWriter.close();
    }

    /**
     * Read key bindings from file.
     * @param file Input a file to be parsed.
     * @throws FileNotFoundException Throws when triggered.
     */
    private void fileParse(File file) throws FileNotFoundException
    {
        addToLog("[KBFH]\tParsing from: " + file.getName());
        Scanner scanner = new Scanner(file, "UTF-8");
        KeyBinds.readFrom(scanner);
        scanner.close();
    }

    /**
     * Get the filename of the bindings file.
     * @return Returns a string with the filename.
     */
    public static String getFilename()
    {
        return filename;
    }

    /**
     * Set the filename of the bindings file, by default "binds.txt".
     * @param filename Input the filename example: "binds.txt".
     */
    public static void setFilename(String filename)
    {
        KeyBindFileHandler.filename = filename;
    }

    /**
     * Every class that implements Logable, must add actions to the log.
     *
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }

    /**
     * Is called when a key binding has changed.
     */
    @Override
    public void keyBindingChanged()
    {
        try
        {
            saveBindings();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
