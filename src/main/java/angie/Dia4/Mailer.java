package angie.Dia4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;

public class Mailer {
	private String dir;
	private String body;
	
	public void setDir(String direccion){
		this.dir=direccion;
	}
	public String getDir(){
		return dir;
	}
	
	public void setBody(String body){
		this.body=body;
	}
	public String getBody(){
		return body;
	}
	public void readMail(String mailto) throws Exception{
		
		try {
			FileReader aux= new FileReader(mailto);
		
			 BufferedReader inAux  = new BufferedReader(aux);
			 String texto=inAux.readLine();
			 System.out.println(texto);
			 inAux.close();
		 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendMail(String mailto, Entry text){
		
		try {
			
			File f=new File(mailto); 
			
			FileReader aux = new FileReader(f);
			BufferedReader inAux = new BufferedReader(aux);
			String texto=inAux.readLine();
			
			String mes;
			
			switch (text.getCalendar().get(Calendar.MONTH)){
				case 1: mes="Jan ";
					break;
				case 2: mes="Feb ";
					break;
				case 3: mes="Mar ";
					break;
				case 4: mes="Apr ";
					break;
				case 5: mes="May ";
					break;
				case 6: mes="Jun ";
					break;
				case 7: mes="Jul ";
					break;
				case 8: mes="Aug ";
					break;
				case 9: mes="Sep ";
					break;
				case 10: mes="Oct ";
					break;
				case 11: mes="Nov ";
					break;
				case 12: mes="Dec ";
					break;
				default: mes=""; 
					break;
			}			
			String fecha=mes+text.getCalendar().get(Calendar.DAY_OF_MONTH)+", "+text.getCalendar().get(Calendar.YEAR);
			
			FileWriter fw=new FileWriter(f);
			fw.append(text.getTitle()+" -@- "+text.getText() + " -@- " + fecha + " -@- " +text.getArrayList().toString()+ " -@- "+texto);
			fw.close();
			inAux.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@SuppressWarnings("resource")
	public void sendMail(String mailto, String text){
		
		try {
			
			File f=new File(mailto);
			
			FileWriter fw = new FileWriter(f,true);
			fw.write(text);
			fw.close();
						
			FileReader aux = new FileReader(f);
			BufferedReader inAux = new BufferedReader(aux);
			String texto=inAux.readLine();
			System.out.println("texto del archivo: " + texto);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
