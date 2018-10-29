import javax.print.DocFlavor;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class invertedIndexPractice {
    ArrayList<String> termList;
    ArrayList<ArrayList<Integer>> docLists;
    String[] myDocs;

    public invertedIndexPractice(String[] docs) {
        termList = new ArrayList<String>();
        myDocs = docs;
        docLists = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> docList;
        for (int i = 0; i < myDocs.length; i++) {
            String[] tokens = myDocs[i].split(" ");
            String token;
            for (int j = 0; j < tokens.length; j++) {
                token = tokens[j];
                if (!termList.contains(token)) {
                    termList.add(token);
                    docList = new ArrayList<Integer>();
                    docList.add(new Integer(i));
                    docLists.add(docList);
                } else {
                    int index = termList.indexOf(token);
                    docList = docLists.get(index);
                    if (!docList.contains(new Integer(i))) {
                        docList.add(new Integer(i));
                        docLists.set(index, docList);
                    }

                }
            }
        }
    }

    public String toString() {
        String matrixString = new String();
        ArrayList<Integer> docList = new ArrayList<Integer>();
        for (int i = 0; i < termList.size(); i++) {
            matrixString += String.format("%-15s", termList.get(i));
            docList = docLists.get(i);
            for (int j = 0; j < docList.size(); j++) {
                matrixString += docList.get(j) + "\t";

            }
            matrixString += "\n";

        }
        return matrixString;
    }

    public ArrayList<Integer> search(String query) {
        int index = termList.indexOf(query);
        if (index < 0) return null;
        return docLists.get(index);
    }

    public ArrayList<Integer> search(String[] query) {
        ArrayList<Integer> result = search(query[0]);
        int termId = 1;
        System.out.println(result);
        while (termId < query.length) {
            ArrayList<Integer> result1 = search(query[termId]);
            result = union(result, result1);
            termId++;
        }
        System.out.println(result);
        return result;
    }

    public ArrayList<Integer> merge(ArrayList<Integer> l1, ArrayList<Integer> l2) {
        ArrayList<Integer> mergedList = new ArrayList<Integer>();
        int id1 = 0, id2 = 0;
        while (id1 < l1.size() && id2 < l2.size()) {
            if (l1.get(id1).intValue() == l2.get(id2).intValue()) {
                mergedList.add(l1.get(id1).intValue());
                id1++;
                id2++;
            } else if (l1.get(id1) < l2.get(id2)) id1++;
            else if (l1.get(id1) > l2.get(id2)) id2++;
        }
        return mergedList;
    }

    public ArrayList<Integer> union(ArrayList<Integer> l1, ArrayList<Integer> l2) {
        ArrayList<Integer> unionList = new ArrayList<Integer>();
        int id1 = 0, id2 = 0;
        while (id1 < l1.size()) {
            if (!unionList.contains(l1.get(id1).intValue())) {
                unionList.add(l1.get(id1).intValue());
                id1++;
            } else
                id1++;
        }
        while (id2 < l2.size()) {
            if (!unionList.contains(l2.get(id2).intValue())) {
                unionList.add(l2.get(id2).intValue());
                id2++;
            } else
                id2++;
        }
        return unionList;
    }

    public static void main(String[] args) {
        String[] docs = {"new home sales top forecasts",
                "home sales rise in july",
                "increase in home sales in july",
                "july new home sales rise"
        };

        invertedIndexPractice v = new invertedIndexPractice(docs);
        System.out.println(v);
        ArrayList<Integer> r = v.search("forecasts");
        //System.out.println(r);
        String[] query = {"new", "top"};
        System.out.println("before sending the query");
        ArrayList<Integer> r1 = v.search(query);
        System.out.println("after sending the query");
        System.out.println("the union is:" + r1);
        //ArrayList<Integer> r2= v.union()
    }

}
