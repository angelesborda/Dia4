package angie.Dia4;

import java.util.ArrayList;
import java.util.Scanner;

public class Group {
	
	private  int id;
	private ArrayList<User> users=null;
	private ArrayList<User> suscriptores=null;
	private String nombre;
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setSuscriptor(User u){
		Mailer mai=new Mailer();
		mai.sendMail(u.getMail(),"Hola "+u.getNombre() + "! Bienvenido al grupo");
			if(this.suscriptores==null){
				this.suscriptores=new ArrayList<>();
			}
			this.suscriptores.add(u);
	}
	public ArrayList<User> getSuscriptores(){
		return suscriptores;
	}
	public void viewSuscriptores(){
		if(this.getSuscriptores()!=null && ! !this.getSuscriptores().isEmpty()){
			for(int i=0; i<this.getSuscriptores().size();i++){
				System.out.println(this.getSuscriptores().get(i).getNombre());
			}
		}
		
	}
	public void setUser(User u){
		if(this.users==null){
			this.users=new ArrayList<>();
		}
		this.users.add(u);
	}
	public ArrayList<User> getUserS(){
		return users;
	}
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	public String getNombre(){
		return nombre;
	}
	
	
	@SuppressWarnings("resource")
	public void setDatos(){
			
			System.out.println ("Ingrese el nombre del grupo");
			Scanner entrada = new Scanner (System.in);
			this.setNombre(String.valueOf(entrada.nextLine()));
			
			GroupManager gm=GroupManager.getInstance();
			gm.setGroup(this);
			this.setId(gm.getGroups().size());
			
	}
}
