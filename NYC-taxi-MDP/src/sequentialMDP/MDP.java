package sequentialMDP;
import java.io.*;

public class MDP {
	
	public MDP(String file) {
		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader(file));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
	
	public static void main(String[] args){
		MDP mdp = new MDP(args[0]);
	}
}
