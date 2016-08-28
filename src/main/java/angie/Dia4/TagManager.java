package angie.Dia4;

import java.util.ArrayList;
import java.util.Scanner;

public class TagManager {
	private static TagManager uniqueInstance;
	private ArrayList<Tag> tag;
	
	
	
	public static TagManager getInstance(){
		if(uniqueInstance==null){
			uniqueInstance=new TagManager();
		}
		return uniqueInstance;
	}
	private TagManager(){
		
	}
	public void setTag(Tag tag1)throws NullPointerException{
		try{
			if(tag==null){
				tag=new ArrayList<Tag>();
			}
			this.tag.add(tag1);
		}
		catch(Exception e){
			System.out.println("Error: el Array de Tags esta vacio");
		}
		
	}
	public ArrayList<Tag> getTags(){
		return tag;
	}
	@SuppressWarnings("resource")
	public Tag findTag(String texto){
			if(tag!=null){
				ArrayList<Tag> rta=new ArrayList<>();
				for(int i=0; i<tag.size();i++){
					if(texto.equals(tag.get(i).getText())){
						return tag.get(i);
					}
					else{
						if(tag.get(i).getText().contains(texto)){
							rta.add(tag.get(i));
						}
					}
				}
				if(rta.size()>0){
					if(rta.size()==1){
						return rta.get(0);
					}
					else{
						System.out.println("Elegir el numero del tag deseado:");
						for(int j=0; j<rta.size();j++){
							System.out.println(j+1+" - " + rta.get(j).getText());
						}
						Scanner scan=new Scanner(System.in);
						int pos= Integer.valueOf(scan.nextLine());
						
						return rta.get(pos-1);
					}
				}
			}
			return null;
		}
}
