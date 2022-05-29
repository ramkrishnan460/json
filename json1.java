import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import com.github.cliftonlabs.json_simple.JsonArray;

public class Json1 {
	
	public static void main(String[] args) {
		ArrayList<String> clas = new ArrayList<String>();
		ArrayList<String> staff = new ArrayList<String>();
		ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();
		
		//csv......................................................
		
		try {
			CSVReader reader1 = new CSVReader(new FileReader("ClassName_Table.csv"));
			String[] line;
			while((line = reader1.readNext()) != null) {
				clas.add(line[1]);
			}
			
			CSVReader reader2 = new CSVReader(new FileReader("StaffName_Table.csv"));
			while((line = reader2.readNext()) != null) {
				staff.add(line[1]);
			}
			
			CSVReader reader3 = new CSVReader(new FileReader("Student_Data.csv"));
			int count = 0;
			while((line = reader3.readNext()) != null) {
				ArrayList<String> student = new ArrayList<String>();
				if(count < 1) {
					student.add(line[1]);
					student.add(clas.get(0));
					student.add(staff.get(0));
					ans.add(student);
				}
				else {
					student.add(line[1]);
					int cid = Integer.parseInt(line[2]);
					student.add(clas.get(cid));
					int stid = Integer.parseInt(line[3]);
					student.add(staff.get(stid));
					ans.add(student);
				}
				count++;
				
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//json........................................................
		
		JsonArray ar = new JsonArray();
		for(ArrayList<String> p : ans) {
			JsonObject js = new JsonObject();
			js.put("StudentName", p.get(0));
			js.put("ClassName", p.get(1));
			js.put("StaffName", p.get(2));
			
			ar.add(js);
		}
		
		try {
			FileWriter fr = new FileWriter("StudentResult.json");
			fr.write(Jsoner.prettyPrint(ar.toJson()));
			fr.close();
			System.out.println("json file created");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
