package angie.Dia4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;



public class User {
	private int id;
	private String nombre;
	private ArrayList<Tag> tag=null;
	private ArrayList<Entry> entry=null;
	private ArrayList<Group> group=null;
	private String mail;
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	public String getNombre(){
		return nombre;
	}
	public void setMail(String mail){
		this.mail=mail;
	}
	public String getMail(){
		return mail;
	}
	
	public void setEntry(Entry entry1)throws NullPointerException{
		try{
			if(entry==null){
				entry=new ArrayList<Entry>();
			}
			this.entry.add(entry1);
		}
		catch(Exception e){
			System.out.println("Error: el Array de Tags esta vacio");
		}
		
	}
	public ArrayList<Entry> getEntry(){
		return entry;
	}
	
	public void setTag(Tag tag1) throws NullPointerException{
		try{
			if(tag==null){
				tag=new ArrayList<Tag>();
			}
			int z=0;
			boolean existe=false;
			while(!existe && z<tag.size()){
				if(!tag.get(z).getText().equalsIgnoreCase(tag1.getText())){
					z++;
				}
				else{
					existe=true;
					System.out.println("El tag ya existe");
				}
			}
			if(!existe){
				tag.add(tag1);
			}
			
		}
		catch(Exception e){
			System.out.println("Error: el Array de Tags esta vacio");
		}
		
	}
	public ArrayList<Tag> getTag(){
		return tag;
	}
	public void deleteEntry(Entry entrada) throws IOException{
		try{
			int i=-1;
			boolean encontrado=false;
			
			while(!encontrado && i<entry.size()){
				i++;
				if(entry.get(i).getId()==entrada.getId()){
					encontrado=true;
				}
			}
			
			entry.remove(i);
		}
		catch (Exception e){
			System.out.println("ERROR: El post que se desea borrrar no existe");
		}
		
	}
	
	public void postEntry(Entry e){
		System.out.println("Titulo: " + e.getTitle());
		System.out.println(e.getText());
		int mes=e.getCalendar().get(Calendar.MONTH) +1;
		System.out.println("Fecha: "+ e.getCalendar().get(Calendar.DAY_OF_MONTH) + " / " + mes + " / " + e.getCalendar().get(Calendar.YEAR));
		
		if(e.getArrayList()!=null){
			Tag tag;
			for(int t=0;t<e.getArrayList().size();t++){
				tag=e.getArrayList().get(t);
				System.out.print(tag.getText() + " ");
			}
			System.out.println();
		}
		
		
		
	}
	public User(){
		
	}
	public void setGroup(Group group1)throws NullPointerException{
		try{
			if(group==null){
				group=new ArrayList<Group>();
			}
			this.group.add(group1);
		}
		catch(Exception e){
			System.out.println("Error: el Array de Groups esta vacio");
		}
		
	}
	public ArrayList<Group> getGroups(){
		return group;
	}
	
	public Entry getEntryByTitle(String post){
		if(this.getEntry()!=null){
			int i=0;
			while(i<this.getEntry().size()){
				if(this.getEntry().get(i).getTitle().equalsIgnoreCase(post)){
					return getEntry().get(i);
				}
				i++;
			}
		}
		
		return null;
	}
	
	@SuppressWarnings("resource")
	public void setDatos(){
		User u=this;
		System.out.println("Ingrese su nombre");
		Scanner scan = new Scanner(System.in);
		u.setNombre(String.valueOf(scan.nextLine()));
		
		System.out.println("Ingrese su mail");
		Scanner scan1 = new Scanner(System.in);
		String mail=String.valueOf(scan1.nextLine());
		u.setMail(mail);
		
		
		UserManager ua=UserManager.getInstance();
		ua.setUser(u);
		u.setId(ua.getAllUsers().size());
		
		/*scan.close();
		scan1.close();*/
	}
	
}
