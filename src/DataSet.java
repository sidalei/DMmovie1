import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;




public class DataSet {
	private int features[][];
	private int numAttributes;
	private int numInstnaces;
	
	public DataSet(String path) {
		// TODO Auto-generated constructor stub
		numAttributes=0;
		numInstnaces = 0;
		try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String tmp = reader.readLine();
//            System.out.println("tmp =  = "+tmp);
            
            StringTokenizer tokenizer=new StringTokenizer(tmp);
            numAttributes=tokenizer.countTokens();   
            while(tokenizer.hasMoreTokens()){   
               String str=tokenizer.nextToken();    
            }
            

            

            
            while (reader.readLine() != null) {
                numInstnaces++;
            }
            numInstnaces++;
            
//            System.out.println("numAttributes = "+numAttributes);
//            System.out.println("numInstance = "+numInstnaces);
            
            features = new int[numInstnaces][numAttributes];
            

            reader = new BufferedReader(new FileReader(path));
            
            String line;
            int ind = 0;
            while ((line = reader.readLine()) != null) {
            	StringTokenizer tokenizer2=new StringTokenizer(line);
                for (int i = 0; i < numAttributes; i++) {
                    features[ind][i] = Integer.parseInt(tokenizer2.nextToken());
                }
                
                ind++;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataSet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataSet.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		
		

//		for(int m=0; m<5; m++){
//			for(int n=0; n<numAttributes; n++){
//				System.out.println(features[m][n]);
//			}
//		}
		
	}
	
	
	public int[][] getFeatures() {
        return features;
    }
	
	public int getNumAttributes() {
		return numAttributes;
	}
	
	public int getNumInstance() {
		return numInstnaces;
	}
	
}
