package Common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HistoryFieldInput implements Serializable {

    public static String fileTemp = "lib/Strawberry.tmp";

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // variable : history String
    public Map<String, List<String>> map = new HashMap<>();;

    public void add(String variable, String historyStr) {
        if (map == null) {
            map = new HashMap<>();
        }

        List<String> h = map.get(variable);

        if (h != null) {
            h.add(0, historyStr);

            // remove duplicate
            for (int i = h.size() - 1; i > 0; i--) {
                if (h.get(i).equals(historyStr)) {
                    h.remove(i);
                }
            }

            // limit 5 record
            while (h.size() > 5) {
                h.remove(h.size() - 1);
            }
        } else {
            h = new ArrayList<>();
            h.add(0, historyStr);
        }

        map.put(variable, h);

        try (FileOutputStream fos = new FileOutputStream(fileTemp); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(this);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public List<String> getSuggestions(String[] params) {
        // the suggestion provider can control text search related stuff, e.g case
        // insensitive match, the search limit etc.
        String variable = params[0];
        String input = params[1];

        List<String> h = map.get(variable);
        if (h == null || h.size() == 0) {
            return null;
        }

        return h.stream().filter(item -> item.toLowerCase().indexOf(input.toLowerCase()) > -1).collect(Collectors.toList());
    }

    public Map<String, List<String>> getHistorys() {
        return map;
    }
}
