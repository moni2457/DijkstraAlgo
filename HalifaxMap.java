import java.util.*;

class HalifaxMap {
	//DataStructure for storing the intersection of the city
	public static ArrayList<ArrayList<Integer>> intersectionsDataStruct = new ArrayList<>();
	
	//DataStructure for storing the adjacency list
		List<Road> AList[];
	public  int intersectionCount = 0; //total number of intersection count is maintained
	
	class Road{
		
		int end, weight;
		/*Constructor invoked */
		public Road(int end, int weight) {
			this.end = end; this.weight = weight;
		}
	}
	
	@SuppressWarnings("unchecked")
	/*Constructor invoked and the adjacency list in initialized*/
	public  HalifaxMap() {
		int roadCount = 1000;  //user could provide the roadCount while creating object in main method 
		AList = new LinkedList[roadCount]; //initializing the adjacency list 
			for(int i =0; i < AList.length; i++) {
				AList[i] = new LinkedList<Road>();
				AList[i].add(new Road(i, 0));
			}
	}
	
	/*Method invoked for 
	 * adding new Road length
	 * in adjacency list*/
	boolean addRoad(int u, int v, int w) {
		boolean addedRoad = false;
		//Validation for duplicate road entry
		for(int i = 0; i < AList.length; i++) {
			for(Road j: AList[i]) {
				if(i==u && j.end==v) { 
					addedRoad = true;
				}
		}
	}
		//Adding the road in the adjacency matrix (undirected graph)
		if(addedRoad == false) {
			AList[u].add(new Road(v,w));
			AList[v].add(new Road(u, w));
			return true;
		} else {
			//System.out.println("Road already exist.");
			return false;
		}
	}
	
	/*Method is invoked when
	 * user adds newIntersection 
	 * in the city
	 * */
	public  boolean newIntersection(int x, int y) {
		int alreadyExist = 0;
		if(x >= 0 && y >= 0 ) {						//Validation for negative coordinates
			if(intersectionCount == 0) {					//Adding the intersection for first time
				intersectionsDataStruct.add(new ArrayList<Integer>());  //initializing a new ArrayList for the coordinates
				intersectionsDataStruct.get(intersectionCount).add(x);			//adding x coordinate
				intersectionsDataStruct.get(intersectionCount).add(y);			//adding y coordinate
				intersectionCount++;
				return true;
			} else {
				for(int i = 0; i < intersectionCount; i++) {		//Validation for new intersection already exists
					if(intersectionsDataStruct.get(i).get(0) == x && intersectionsDataStruct.get(i).get(1) == y) {
						alreadyExist++;						//incrementing counter if intersection exists
					}
				}
				if(alreadyExist == 0) {						//if new intersection doesn't exists
					intersectionsDataStruct.add(new ArrayList<Integer>());		//initializing new ArrayLsit
					intersectionsDataStruct.get(intersectionCount).add(x);				//adding x coordinate
					intersectionsDataStruct.get(intersectionCount).add(y);				//adding y coordinate
					intersectionCount++;
					return true;
				} else {
					return false;
				}
			}
		} else {
			//System.out.println("Enter proper intersections.");
			return false;
		}
	}
	
	
	/*Method is invoked when
	 * user defines new Roads 
	 * in the city
	 * */
	/*References: 
	 * https://www.youtube.com/watch?v=fjT3WDKiAkI*/
	public  boolean defineRoad(int x1, int y1, int x2, int y2) {
		  	int startNode = 0;
		  	int endNode = 0;
		  	int weight = 0;
		  	boolean startNodeExist= false;
		  	boolean endNodeExist= false;
		  
		  	//Checking whether both intersections are valid or not 
		for (int i = 0; i < intersectionCount; i++) {
	        	if(intersectionsDataStruct.get(i).get(0) == x1 && intersectionsDataStruct.get(i).get(1) == y1) {
	        		 startNode = i;	 
	        		 startNodeExist= true;
	        } 
	    }
		for (int i = 0; i < intersectionCount; i++) {
        	if(intersectionsDataStruct.get(i).get(0) == x2 && intersectionsDataStruct.get(i).get(1) == y2) {
        		 endNode = i;
        		 endNodeExist= true;
        }
	} 
		//Validating for source intersection different from destination intersection
		if(startNodeExist && endNodeExist && startNode == endNode) {
			System.out.println("Enter different coordinates");
			return false;
		}
		//Adding the road in the adjacency matrix
		if(startNodeExist && endNodeExist && x1>=0 && x2>=0 && y1>=0 && y2>=0) {	//Validation for negative coordinates
			weight = ((x2-x1)*(x2-x1)) + ((y2-y1)*(y2-y1));							//Calculating the road length for the road
			boolean returnResponse = addRoad(startNode, endNode, weight);
			return returnResponse;
	} else {
		//System.out.println("Enter proper intersections");
		return false;
	}	
}
	
	/*Method is invoked when user wants 
	 * to find the shortest path  from source to  
	 * destination in the city
	 * */
	/*References: https://www.geeksforgeeks.org/java-program-for-dijkstras-algorithm-with-path-printing/
	 * https://www.geeksforgeeks.org/printing-paths-dijkstras-shortest-path-algorithm/
	 * https://www.geeksforgeeks.org/dijkstras-algorithm-for-adjacency-list-representation-greedy-algo-8/
	 * https://www.youtube.com/watch?v=d6ZFqjH63vo
	 * */
public  void navigate( int x1, int y1, int x2, int y2 ) {
	 int startNode = 0;
	 int endNode = 0;
	 boolean startNodeExist= false;
	 boolean endNodeExist= false;
	//Checking whether both source and destination are valid or not
	 for (int i = 0; i < intersectionCount; i++) {
     	if(intersectionsDataStruct.get(i).get(0) == x1 && intersectionsDataStruct.get(i).get(1) == y1) {
     		 startNode = i;	 
     		 startNodeExist= true;
     } 
 }
	for (int i = 0; i < intersectionCount; i++) {
 	if(intersectionsDataStruct.get(i).get(0) == x2 && intersectionsDataStruct.get(i).get(1) == y2) {
 		 endNode = i;
 		 endNodeExist= true;
 }
}
	//Validation for negative coordinates
	if(startNodeExist && endNodeExist  && x1>=0 && x2>=0 && y1>=0 && y2>=0) {
		int countStart = 0;
		int countEnd= 0;
		//Validation if No Path exists
		for(int i=0;i<AList.length;i++) {
			if(startNode == i && AList[i].size() == 1 ) {
				System.out.println("no path");
				countStart++;
			} if(endNode == i && AList[i].size() ==1 ) {
				System.out.println("no path");
				countEnd++;
			}
		}
		// Validation for source intersection should be different then destination intersection
		if(startNode == endNode) {
			System.out.println("Enter different source and destination");
		}
		//Dijkstra Algorithm method invokes
		if(countStart == 0 && countEnd == 0) {
			findDistance(startNode, endNode);
		}
		
} else {
		System.out.println("Enter proper instersections");
	}
}

public void findDistance(int x, int y) {
	
		int totalVertices = AList.length;  							//total intersection in the city
        int[] shortestRoadDistances = new int[totalVertices]; 		//array of shortest distance
        boolean[] visitedIntersections = new boolean[totalVertices]; //array of unVisted intersections
       // initializing the arrays 
        for (int vertexIndex = 0; vertexIndex < totalVertices;  vertexIndex++)  { 
        	shortestRoadDistances[vertexIndex] = Integer.MAX_VALUE; // initializing to an infinity value 
        	visitedIntersections[vertexIndex] = false; 				//initializing to false 
        }
        
        shortestRoadDistances[x] = 0; 				 //shortest distance of source to source 
        int[] shortestPath = new int[totalVertices]; //array for getting path
        shortestPath[x] = -1; 						 // initializing path from source to source
        
        
        //
        for (int i = 1; i < totalVertices; i++) {
            int nearestIntersection = -1; 						//nearest Intersection for current intersection
            int shortestDistance = Integer.MAX_VALUE; 			//shortest distance for current intersection
            for (int vertexIntersection = 0;  vertexIntersection < totalVertices;   vertexIntersection++) { 
                if (!visitedIntersections[vertexIntersection] && shortestRoadDistances[vertexIntersection] < shortestDistance)  
                { 
                	nearestIntersection = vertexIntersection; 
                    shortestDistance = shortestRoadDistances[vertexIntersection]; 
                } 
            } 
            
            if(nearestIntersection == -1) {
            	nearestIntersection = x;
            }
            
            visitedIntersections[nearestIntersection] = true; 		//flag visited vertices 
            // finding the shortest distance 
            for (int vertexIntersection = 0;vertexIntersection < totalVertices;  vertexIntersection++)  { 
            	int roadDistance = 0;
            		if(vertexIntersection == nearestIntersection) {
            			for(Road j: AList[nearestIntersection]) {
            				roadDistance = j.weight;				//getting road length from adjacency matrix
                if (roadDistance > 0 && ((shortestDistance + roadDistance) < shortestRoadDistances[j.end]))  
                { 
                	shortestPath[j.end] = nearestIntersection; 						
                    shortestRoadDistances[j.end] = shortestDistance + roadDistance; 		//assigning shortest distance from source to destination
                } 
           }
       }
 } 
}
        //printing the shortest path 
        int nVertices = shortestRoadDistances.length; 
        for (int roadIndex = 0;  
        		roadIndex < nVertices;  
        		roadIndex++)  
        { 
            if (roadIndex != x)  
            { 
            	if(roadIndex == y ) {
            		printShortestPath(roadIndex, shortestPath);
            	}
                
            } 
        }
    } 
    
    /*print the shortest
     * path from source to destination
     * */
    private static void printShortestPath(int currentVertex, 
                                  int[] shortestPath) {  
        if (currentVertex == -1) 
        { 
            return; 
        } 
        //recursively invoked
        printShortestPath(shortestPath[currentVertex], shortestPath); 
        for(int i=0;i<2;i++) {
        		//desired output
        	System.out.print(intersectionsDataStruct.get(currentVertex).get(i) + "\t"); 
        }
        System.out.println();
    } 
}
