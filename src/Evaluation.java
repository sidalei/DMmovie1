import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Evaluation {
	private int[][] train;
	private int[][] test;
	private int testNum;
	private int trainNum;
	
	private HashMap<Integer, HashMap> u2m;
	private HashMap<Integer, HashMap> m2u;
	
	
//	public ArrayList<Integer> lineNo = new ArrayList<Integer>();
//	public String flag="not found";
	
	public Evaluation(DataSet train1, DataSet test1) {
		// TODO Auto-generated constructor stub
		train = train1.getFeatures();
		test = test1.getFeatures();
		trainNum=train1.getNumInstance();
		testNum=test1.getNumInstance();
		
		
		u2m = new HashMap<Integer, HashMap>();
		m2u = new HashMap<Integer, HashMap>();
	}

	public void start() {
		// TODO Auto-generated method stub
		train();
		predict();
		
	}
	
	private void train() {
		// TODO Auto-generated method stub
		for(int i=0; i<trainNum; i++){
			
			int Uid = train[i][0];
			int Mid = train[i][1];
			double score = train[i][2];
			
//			if(Uid==219 && Mid==4827){
//				flag="found!";
//				lineNo.add(i);
//			}
			
			
			
			if(u2m.get(Uid) == null){ 		//haven't watched any movie yet
				HashMap<Integer, Double> m_p= new HashMap<Integer, Double>();
				m_p.put(Mid, score);
				u2m.put(Uid, m_p);
			}else{							//already watched some movies
				HashMap<Integer, Double> m_p = u2m.get(Uid);
				if(m_p.get(Mid) == null){
					m_p.put(Mid, score);
				}else{
//					System.out.println("another score !");
//					System.out.println(Uid +"   "+ Mid +"   "+i);
//					System.out.println();
				}
				u2m.put(Uid, m_p);
			}
			
			
			
			if(m2u.get(Mid) == null){ 
				HashMap<Integer, Double> u_p= new HashMap<Integer, Double>();
				u_p.put(Uid, score);
				m2u.put(Uid, u_p);
			}else{
				HashMap<Integer, Double> u_p = m2u.get(Mid);
				if(u_p.get(Uid) == null){
					u_p.put(Uid, score);
				}else{
//					System.out.println("another score!");
//					System.out.println(Uid +"   "+ Mid +"   "+i);
//					System.out.println();
				}
				m2u.put(Mid, u_p);
			}
			
			
			
		}
		
//		System.out.println("end1");
//		System.out.println(flag);
//		for(int j=0; j<lineNo.size(); j++){
//			System.out.println(lineNo.get(j));
//			
//		}
	}

	private void predict() {
		// TODO Auto-generated method stub
		int testUid=0;
		int testMid=0;
		
		
		
		for(int i=0; i<testNum; i++){
			testUid=test[i][0];
			testMid=test[i][1];
			double myScore=0;
			
			
			ArrayList<Double> scoreList = new ArrayList<Double>();
			
			HashMap<Integer, Double> myMovie = u2m.get(testUid);			//movies that User A watched before
			if(myMovie==null){
				myScore=0;
			}else{
				Iterator iter = myMovie.entrySet().iterator(); 
				while (iter.hasNext()) { 
				    Map.Entry entry = (Map.Entry) iter.next(); 
				    int thisMid = (int) entry.getKey();				 		//who also watched this movie before
				    HashMap<Integer, Double> myUser = m2u.get(thisMid);		//<UserId, Score>
				    
				    if(myUser != null){
				    	Iterator iter2 = myUser.entrySet().iterator(); 
				    	while (iter2.hasNext()) { 
				    	    Map.Entry entry2 = (Map.Entry) iter2.next(); 
				    	    int uid2 = (int) entry.getKey(); 
				    	    if(u2m.get(uid2)!=null){
				    	    	if(u2m.get(uid2).containsKey(testMid)){
				    	    		scoreList.add((Double) u2m.get(uid2).get(testMid));
				    	    	}
				    	    }
				    	} 
				    }
				     
				} 
			}
			
			
			
		}
		
	}

	
	
	

}
