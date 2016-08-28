package angie.Dia4;
import java.util.ArrayList;
import java.util.Scanner;

public class GroupManager {
	private static GroupManager uniqueInstance;
	private ArrayList<Group> group;
	
	public static GroupManager getInstance(){
		if(uniqueInstance==null){
			uniqueInstance=new GroupManager();
		}
		return uniqueInstance;
	}
	private GroupManager(){
		
	}
	public void setGroup(Group group1)throws NullPointerException{
		try{
			if(group==null){
				group=new ArrayList<Group>();
			}
			group.add(group1);
		}
		catch(Exception e){
			System.out.println("Error: el Array de Tags esta vacio");
		}
		
	}
	
	
	
	public ArrayList<Group> getGroups(){
		return group;
	}
	
	@SuppressWarnings("resource")
	public Group findGroup(String nombre){
		
		ArrayList<Group> p=null;
		for(int i=0; i<group.size();i++){
			if(nombre.equals(group.get(i).getNombre())){
				return group.get(i);
			}
			else{
				p=new ArrayList<>();
				if(group.get(i).getNombre().contains(nombre)){
					p.add(group.get(i));
				}
			}
		}
		if(p!=null){
			if(p.size()==1){
				return p.get(0);
			}
			else{
				if(!p.isEmpty()){
					System.out.println("Elegir el numero del grupo deseado:");
					for(int j=0; j<p.size();j++){
						System.out.println(j+1+" - " + p.get(j).getNombre());
					}
					Scanner scan=new Scanner(System.in);
					int pos= Integer.valueOf(scan.nextLine());
					return p.get(pos-1);
				}
				
			}
		}
		return null;
	}
	
	
	
	
}


