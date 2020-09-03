public class Graph_UI {
	
	public static boolean  newIntersectionFlag = false;
	public static boolean defineRoadFlag = false;
	public static void main(String[] args) {
		HalifaxMap hm = new HalifaxMap();
		
		newIntersectionFlag = hm.newIntersection(0, 1);
		System.out.println(newIntersectionFlag);
		newIntersectionFlag = hm.newIntersection(0, 2);
		System.out.println(newIntersectionFlag);
		newIntersectionFlag = hm.newIntersection(2, 1);
		System.out.println(newIntersectionFlag);
		newIntersectionFlag = hm.newIntersection(3, 1);
		System.out.println(newIntersectionFlag);
		newIntersectionFlag = hm.newIntersection(3, 2);
		System.out.println(newIntersectionFlag);
		newIntersectionFlag = hm.newIntersection(3, 3);
		System.out.println(newIntersectionFlag);
		newIntersectionFlag = hm.newIntersection(5, 5);
		System.out.println(newIntersectionFlag);
		newIntersectionFlag = hm.newIntersection(0, 0);
		System.out.println(newIntersectionFlag);
		
		defineRoadFlag = hm.defineRoad(0, 1, 0, 2);
		System.out.println(defineRoadFlag);
		defineRoadFlag = hm.defineRoad(0, 1, 3, 2);
		System.out.println(defineRoadFlag);
		defineRoadFlag = hm.defineRoad(0, 1, 3, 3);
		System.out.println(defineRoadFlag);
		defineRoadFlag = hm.defineRoad(0, 2, 3, 3);
		System.out.println(defineRoadFlag);
		defineRoadFlag = hm.defineRoad(0, 2, 3, 1);
		System.out.println(defineRoadFlag);
		defineRoadFlag = hm.defineRoad(0, 2, 2, 1);
		System.out.println(defineRoadFlag);
		defineRoadFlag = hm.defineRoad(3, 2, 3, 3);
		System.out.println(defineRoadFlag);
		defineRoadFlag = hm.defineRoad(3, 2, 3, 1);
		System.out.println(defineRoadFlag);
		defineRoadFlag = hm.defineRoad(3, 1, 3, 3);
		System.out.println(defineRoadFlag);
		defineRoadFlag = hm.defineRoad(3, 1, 2, 1);
		System.out.println(defineRoadFlag);
		defineRoadFlag = hm.defineRoad(3, 1, 0, 0);
		System.out.println(defineRoadFlag);
		defineRoadFlag = hm.defineRoad(2, 1, 0, 0);
		System.out.println(defineRoadFlag);
		defineRoadFlag = hm.defineRoad(0, 1, 5, 5);
		System.out.println(defineRoadFlag);
		
		hm.navigate(0, 0, 3, 3);
	}
}