/*
 * @author suchao
 * http://my.oschina.net/liangtee/blog/124987
 */


public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataSet train = new DataSet("train.txt");
		DataSet test = new DataSet("test.txt");
		
		Evaluation eva = new Evaluation(train, test);
		eva.start();
		
	}

}
