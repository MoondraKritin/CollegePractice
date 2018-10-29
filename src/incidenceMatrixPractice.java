import java.util.ArrayList;

public class incidenceMatrixPractice {
    String[] myDocs;
    ArrayList<String> termList;
    ArrayList<int[]> docLists;

    public incidenceMatrixPractice(String[] docs){
        myDocs=docs;
        termList= new ArrayList<String>();
        docLists= new ArrayList<int[]>();
        for(int i=0;i<myDocs.length;i++){
            String[] tokens= myDocs[i].split(" ");
            String token;
            for(int j=0;j<tokens.length;j++){
                token=tokens[j];
                if(!termList.contains(token)){
                    termList.add(token);
                    int[] docList= new int[myDocs.length];
                    docList[i]=1;
                    docLists.add(docList);
                }
                else{
                    int index=termList.indexOf(token);
                    int[] docList= docLists.remove(index);
                    docList[i]=1;
                    docLists.add(index, docList);
                }
            }
        }
    }
    public String toString(){
        String matrixString = new String();
        for(int i=0;i<termList.size();i++){
            matrixString += String.format("%-15s", termList.get(i));
            int[] docList = docLists.get(i);
            for(int j=0;j<docList.length;j++){
                matrixString += docList[j] + "\t";
            }
            matrixString += "\n";
        }
        return matrixString;
    }

    public ArrayList<Integer> search(String token){
        int index=termList.indexOf(token);
        ArrayList<Integer>result= new ArrayList<Integer>();
        int[] docList= docLists.get(index);
        for(int i=0;i<docList.length;i++){
            if(docList[i]==1)result.add(i);
        }
        return result;
    }

    public static void main(String[] args){
        String[] docs = {"new home sales top forcasts",
                "home sales rise in july",
                "increase in home sales in july",
                "july new home sales rise"};

        incidenceMatrixPractice i= new incidenceMatrixPractice(docs);
        System.out.println(i);
        ArrayList<Integer> r= i.search("new");
        System.out.println(r);
    }
}
