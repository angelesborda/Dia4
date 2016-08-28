package angie.Dia4;
import java.util.ArrayList;
import java.util.Scanner;


public class UserManager {
	private static UserManager uniqueInstance;
	private ArrayList<User> user;
	
	public static UserManager getInstance(){
		if(uniqueInstance==null){
			uniqueInstance=new UserManager();
		}
		return uniqueInstance;
	}
	private UserManager(){
		
	}
	public void setUser(User user1)throws NullPointerException{
		try{
			if(user==null){
				user=new ArrayList<User>();
			}
			this.user.add(user1);
		}
		catch(Exception e){
			System.out.println("Error: el Array de Usuarios esta vacio");
		}
		
	}
	
	public ArrayList<User> getAllUsers(){
		return user;
	}
	@SuppressWarnings("resource")
	public User findUser(String nombre){
		ArrayList<User> rta=null;
		if(user!= null && !user.isEmpty()){
			rta=new ArrayList<>();
			for(int i=0;i<user.size() ;i++){
				
				if(user.get(i).getNombre().contains(nombre)){
					rta.add(user.get(i));
				}
			}
			if(rta!=null){
				if(rta.size()==1){
					return rta.get(0);
				}
				else{
					if(!rta.isEmpty()){
						System.out.println("Elegir el numero del usuario deseado:");
						for(int j=0; j<rta.size();j++){
							System.out.println(j+1+" - " + rta.get(j).getNombre());
						}
						Scanner scan=new Scanner(System.in);
						int pos= Integer.valueOf(scan.nextLine());
						
						return rta.get(pos-1);
					}
					
				}
			}
		}
		return null;
	}
	
	@SuppressWarnings("resource")
	public User loginUser(){
		UserManager au= UserManager.getInstance();
		
		System.out.println ("Ingrese su nombre para entrar al sistema");
		Scanner opcion=new Scanner(System.in);
		String nombre =String.valueOf(opcion.nextLine());
		User log=au.findUser(nombre);
		
		if(log==null){
			log=au.createUser(log);
		}
		return log;
		
	}
	
	@SuppressWarnings("resource")
	public User createUser(User u){
		u = new User();
		
		System.out.println("El usuario no existe o ingreso mal sus datos, por favor ingrese los datos pedidos:");
		
		System.out.println("Ingrese su nombre");
		Scanner scan = new Scanner(System.in);
		String name=String.valueOf(scan.nextLine());
		
		
		u.setNombre(name);
		
		System.out.println("Ingrese su mail");
		Scanner scan1 = new Scanner(System.in);
		String mail=String.valueOf(scan1.nextLine());
		u.setMail(mail);
		
		this.setUser(u);
		u.setId(this.getAllUsers().size());
		return u;
	}

}






	