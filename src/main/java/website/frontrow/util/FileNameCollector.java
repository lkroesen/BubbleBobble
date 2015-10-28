package website.frontrow.util;

import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * A method for indexing the levels in the resources and returning them.
 */
public final class FileNameCollector
    implements Logable
{
    private static final FileNameCollector INSTANCE = new FileNameCollector();

    /**
     * Private constructor because Singleton.
     */
    private FileNameCollector()
    {
    }

    /**
     * Get the Instance of FileNameCollector.
     * @return Returns the Instance.
     */
    public static FileNameCollector getInstance()
    {
        return INSTANCE;
    }

    /**
     * This method is used for obtaining the files names in a directory.
     * @param dir Input a string of the directory, levels are in: /level/.
     * @return Returns a String array with the directory + their file names.
     * @throws URISyntaxException Throws URISyntaxException when URI is not valid.
     * @throws IOException Some files might not be found.
     */
    public String[] obtain(String dir) throws URISyntaxException, IOException
    {
        ArrayList<String> stringList;
        File jarFile = new File(FileNameCollector.class.getProtectionDomain().getCodeSource()
                .getLocation().getPath());
        if(jarFile.isFile())
        {
            JarFile jar = new JarFile(jarFile);
            stringList = loadFilesFromJar(jar, dir);
        }
        else // Because of Java we need 2 different loaders for IDE's and Jars.
        {
            stringList = loadFilesForIDE(dir);
        }

        String[] levels = new String[stringList.size()];

        for (int c = 0; c < levels.length; c++)
        {
            levels[c] = stringList.get(c);
        }

        return levels;
    }

    /**
     * Log actions in the FileNameCollector.
     * @param action Input a String that is the action performed.
     */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }

    /**
     * Method for loading files in a JAR.
     * @param jar Input the Jar file.
     * @param folder Input the folder URI.
     * @return Returns the levels in an ArrayList.
     */
    private ArrayList<String> loadFilesFromJar(JarFile jar, String folder)
    {
        ArrayList<String> stringList = new ArrayList<>();
        Enumeration<JarEntry> entries = jar.entries();
        while(entries.hasMoreElements())
        {
            JarEntry entry = entries.nextElement();
            System.out.println(entry.getName());
            if(entry.getName().startsWith(folder) && entry.getName().endsWith(".txt"))
            {
                stringList.add("/" + entry.getName());
            }
        }
        return stringList;
    }

    /**
     * Method for loading files in the IDE.
     * @param folder Input the folder URI.
     * @return Returns the levels in an ArrayList.
     * @throws URISyntaxException Throws exception when triggered.
     */
    private ArrayList<String> loadFilesForIDE(String folder) throws URISyntaxException
    {
        if (folder == null)
        {
            addToLog("[FNC]\t[ERROR]\tDirectory is null.");
            throw new URISyntaxException("null", "Null is not an accepted Syntax as a directory.");
        }
        ArrayList<String> stringList = new ArrayList<>();
        InputStream inputStream = FileNameCollector.class.getResourceAsStream("/" + folder);

        Scanner scanner = new Scanner(inputStream, "UTF-8");

        while(scanner.hasNextLine())
        {
            stringList.add("/" + folder + scanner.nextLine());
        }
        scanner.close();

        return stringList;
    }
}
