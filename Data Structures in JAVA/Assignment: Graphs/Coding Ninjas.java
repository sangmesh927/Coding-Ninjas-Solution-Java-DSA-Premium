import java.util.*;

public class Solution {
	

	boolean isNeighbour(int x1, int y1, int x2, int y2){
          return  Math.abs(x1-x2) <=1 &&  Math.abs(y1-y2)<=1 ;
	}
	int solve(String[] Graph , int N, int M,String sentence,Map<Character,List<AbstractMap.SimpleEntry<Integer,Integer>>> characterIndexMap,boolean[][] visited, int start){

	   if(start ==  sentence.length()-1){
		    List<AbstractMap.SimpleEntry<Integer,Integer>> index=characterIndexMap.get(sentence.charAt(start));
            for(AbstractMap.SimpleEntry<Integer,Integer> map:index){
				int v1=map.getKey();
				int v2=map.getValue();
				if((isNeighbour(v1,v2,N,M) || start==0) && !visited[v1][v2]){
					return 1;
				}
			}
			return 0;
         
	   }

	   	List<AbstractMap.SimpleEntry<Integer,Integer>> index=characterIndexMap.get(sentence.charAt(start));
            for(AbstractMap.SimpleEntry<Integer,Integer> map:index){
				int v1=map.getKey();
				int v2=map.getValue();
				// System.out.println(""+sentence.charAt(start)+v1+":"+v2);
				if((isNeighbour(v1,v2,N,M) || start==0) && !visited[v1][v2]){
					 visited[v1][v2]=true;
					 int value=solve(Graph, v1, v2,sentence,characterIndexMap,visited,start+1);
					 if(value ==1){
						 return 1;
					 } 
					  visited[v1][v2]=false;
				}
			}
       return 0;
	}
	int solve(String[] Graph , int N, int M)
	{
		String sentence="CODINGNINJA";

		ArrayList<AbstractMap
                      .SimpleEntry<String, String> >
            arrayList
            = new ArrayList<AbstractMap
                                .SimpleEntry<String, String> >();
		Map<Character,List<AbstractMap.SimpleEntry<Integer,Integer>>> characterIndexMap=new HashMap<>();
		for(int i=0;i<Graph.length;i++){
			for(int j=0;j<Graph[i].length();j++){
				AbstractMap.SimpleEntry<Integer,Integer> pair= new AbstractMap
                          .SimpleEntry(i, j);
				characterIndexMap.computeIfAbsent(Graph[i].charAt(j),k -> new ArrayList<>()).add(pair);
			}
          
		} 
		for(int i=0; i<sentence.length();i++){
			if(!characterIndexMap.containsKey(sentence.charAt(i))){
                  return 0;
			}
		}
		// for(Map.Entry<Character , List<AbstractMap.SimpleEntry<Integer,Integer>>> entry:characterIndexMap.entrySet()){
		// 	//System.out.print(entry.getKey()+" ");
		// 	for(AbstractMap.SimpleEntry<Integer,Integer> pair:entry.getValue()){
		// 		//System.out.print("["+pair.getKey()+" , "+pair.getValue()+"]" +" ");
		// 	}
		// }
		boolean[][] visited=new boolean[N][M];
		return solve(Graph,N,M,sentence,characterIndexMap,visited,0);
	}
	
	
}
