import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A small class to print the output of ProVeif in a prettier way.
 * It will find all entries with a number and assign the lowest possible instead.
 */
public class PrettyProVerif {

    public static void main(String[] args) throws IOException {
        String s = readInput();
        String r = replaceAll(s, findVals(s));
        System.out.println(r);
        System.out.println(createOverview(r));
    }

    /**
     * Reading the input from System.in
     * @return
     * @throws IOException
     */
    static String readInput() throws IOException {
        String inputStr = "";
        StringBuilder sb = new StringBuilder();

        InputStreamReader isReader = new InputStreamReader(System.in);
        BufferedReader bufReader = new BufferedReader(isReader);

        while (inputStr != null)
            if ((inputStr = bufReader.readLine()) != null)
                sb.append(inputStr).append("\n");

        return sb.toString();
    }

    /**
     * Finding the values to be changed.
     * @param s
     * @return
     */
    static Map<String, Set<String>> findVals(String s) {
        Map<String, Set<String>> vals = new HashMap<>();


        Pattern p = Pattern.compile("([a-zA-Z_\\d]*)_(\\d\\d\\d*)");
        Matcher m = p.matcher(s);

        while (m.find()) {
            if (vals.get(m.group(1)) == null) vals.put(m.group(1), new HashSet<>());
            vals.get(m.group(1)).add(m.group(2));
        }

        return vals;
    }

    /**
     * Replaces all entries with the lowest possible non-negative number
     * @param s
     * @param vals
     * @return
     */
    static String replaceAll(String s, Map<String, Set<String>> vals) {

        for (Map.Entry<String, List<String>> e : convertToList(vals).entrySet()) {
            for (int i = 0; i < e.getValue().size(); i++) {
                String ori = e.getKey() + "_" + e.getValue().get(i);
                String newName = e.getKey() + "_" + (i + 1);

                s = s.replaceAll(ori, newName);
            }
        }
        return s;
    }

    /**
     * Covering the values to String, List<String>
     * @param vals
     * @return
     */
    static Map<String, List<String>> convertToList(Map<String, Set<String>> vals) {
        Map<String, List<String>> l = new HashMap<>();

        for (Map.Entry<String, Set<String>> entry : vals.entrySet()) {
            l.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return l;
    }

    static String createOverview(String s){
        StringBuilder sb = new StringBuilder();

        sb.append("-----");
        sb.append("Pretty Overview");
        sb.append("-----");
        sb.append("\n");


        Scanner scanner = new Scanner(s);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.startsWith("RESULT")) sb.append(line+"\n");
        }
        scanner.close();


        return sb.toString();
    }


}
