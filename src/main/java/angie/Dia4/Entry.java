package angie.Dia4;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Scanner;


class Entry {
	private String title;
	private String text;
	private Calendar date;
	private ArrayList<Tag> tag;
	private int id;
	private int idUser;
	
	
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String  getTitle(){
		return title;
	}
	public void setText(String text){
		this.text=text;
	}
	public String getText(){
		return text;
	}
	public void setCalendar(Calendar date){
		this.date=date;
	}
	public Calendar getCalendar(){
		return date;
	}
	public void setArrayList(ArrayList<Tag> list){
		this.tag=list;
	}
	
	public ArrayList<Tag> getArrayList(){
		return tag;
	}

	public void setIdUser(int idUser){
		this.idUser=idUser;
	}
	
	public int getIdUser(){
		return idUser;
	}
	@SuppressWarnings("resource")
	public ArrayList<Tag> seleccionarTags(User u){
		System.out.println ("Se muestran los tags existentes:");
		ArrayList<Tag> rta = new ArrayList<Tag>();
		
		if(u.getTag()!=null && !u.getTag().isEmpty()){
			for (int i=0;i<u.getTag().size();i++){
				System.out.print(u.getTag().get(i).getText());
				if(i!=u.getTag().size()-1){
					System.out.print(" ; ");
				}
				else{
					System.out.println ();
				}
			}
		}
		
		System.out.println ("Ingresar los tags deseados separados por ';'. Es posible elegir ninguno");
		Scanner cont= new Scanner (System.in);
		String continua=String.valueOf(cont.nextLine());
		
		if (continua!=null && !continua.isEmpty()){
			Tag t;
			int pos=continua.indexOf(";");
			int i=0;
			String texto;
			while(i>=0){
				
				if(pos!=-1){
					texto=continua.substring(i, pos);
					i=pos+1;
					pos=continua.substring(i, continua.length()).indexOf(";");
					if(pos!=-1){
						pos+=i;
					}
				}
				else{
					texto=continua.substring(i, continua.length());
					i=pos;
				}
				
				t=new Tag();
				t.setText(texto);
				TagManager tm=TagManager.getInstance();
				tm.setTag(t);
				rta.add(t);
			}
			
		}

		return rta;
	}
	
public void setDatos(User u){
		
		System.out.println ("Ingrese el titulo");
		Scanner entrada = new Scanner (System.in);
		this.setTitle(String.valueOf(entrada.nextLine()));
		
		
		
		System.out.println ("Ingrese el texto");
		Scanner entrada1 = new Scanner (System.in);
		this.setText(String.valueOf(entrada1.nextLine()));
		
		this.setCalendar(Calendar.getInstance());
		this.setArrayList(this.seleccionarTags(u));
		
		EntryManager em=EntryManager.getInstance();
		this.setIdUser(u.getId());
		
		if(em.getEntries()!=null){
			this.setId(em.getEntries().size()+1);
		}
		else{
			this.setId(1);
		}
		
		u.setEntry(this);
		em.setEntry(this);
		
		entrada.close();
		entrada1.close();
		
	}
}