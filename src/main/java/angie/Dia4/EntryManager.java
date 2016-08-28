package angie.Dia4;

import java.io.IOException;
import java.util.*;
//import java.util.stream.*;

public class EntryManager {
	private static EntryManager uniqueInstance;
	private ArrayList<Entry> entry;
	
	public static EntryManager getInstance(){
		if(uniqueInstance==null){
			uniqueInstance=new EntryManager();
		}
		return uniqueInstance;
	}
	private EntryManager(){
		
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
	
	public ArrayList<Entry> searchByTag(Tag t){
		ArrayList<Entry> rta= new ArrayList<Entry>();
		Entry e;
		int x;
		if(entry != null && !entry.isEmpty()){
			for(int i=0;i<entry.size();i++){
				e=entry.get(i);
				for(x=0;x<e.getArrayList().size();x++){
					if(e.getArrayList().get(x).getText().contains(t.getText())){
						rta.add(e);
					}
				}
			}
			
		}
		
		return rta;
	}
	
	public ArrayList<Entry> getEntries(){
		return entry;
	}
	
	public ArrayList<Entry> searchByTitle(String text, String order){
		
		ArrayList<Entry> rta= new ArrayList<Entry>();
		
		Entry e;
		if(entry != null && !entry.isEmpty()){
			for(int i=0;i<entry.size();i++){
				e=entry.get(i);
				if(e.getTitle().contains(text)){
					rta.add(entry.get(i));
				}
			}
			if(order.equalsIgnoreCase("asc") || order.contains("asc")){
				ArrayList<Entry> sorted= new ArrayList<Entry>();
				sorted=sort(rta);
				return sorted;
			}
		}
		return rta;
	}
	
	public ArrayList<Entry> searchByUser(User u){
		return u.getEntry();
	}
	
	public ArrayList<Entry> searchBetweenDates(Calendar fechaIni, Calendar fechaFinal, String order){
		ArrayList<Entry> rta= new ArrayList<Entry>();
		
		Entry e;
		if(entry!= null && !entry.isEmpty()){
			System.out.println("1er if");
			for(int i=0;i<entry.size();i++){
				e=entry.get(i);
				if((e.getCalendar().after(fechaIni) && e.getCalendar().before(fechaFinal))){
					rta.add(e);
				}
				else{
					if(e.getCalendar().get(Calendar.YEAR)==fechaIni.get(Calendar.YEAR) && e.getCalendar().get(Calendar.MONTH)==fechaIni.get(Calendar.MONTH) && e.getCalendar().get(Calendar.DATE)==fechaIni.get(Calendar.DATE)){
						rta.add(e);
					}
					else{
						if(e.getCalendar().get(Calendar.YEAR)==fechaFinal.get(Calendar.YEAR) && e.getCalendar().get(Calendar.MONTH)==fechaFinal.get(Calendar.MONTH) && e.getCalendar().get(Calendar.DATE)==fechaFinal.get(Calendar.DATE)){
							rta.add(e);
						}
					}
				}
			}
			if(order.equalsIgnoreCase("asc") || order.contains("asc")){
				ArrayList<Entry> sorted= new ArrayList<Entry>();
				sorted=sort(rta);
				return sorted;
			}
		}
		
		return rta;
	}
	
	
	public void postEntries(ArrayList<Entry> e){
		if(e!=null && !e.isEmpty()){
			for(int i=e.size()-1; i>=0;i--){
				postEntry(e.get(i));
			}
		}
		else{
			System.out.println("No se encontraron posts para el/los parametro/s deseados");
		}
	}
	
	public void postEntry(Entry e){
		System.out.println();
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
			System.out.println("-------------------------------------------");
			System.out.println();
		}
		
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
	@SuppressWarnings("resource")
	public void createEntry(Entry e, User u){
		e=new Entry();
		
		
		System.out.println ("Ingrese el titulo");
		Scanner entrada = new Scanner (System.in);
		e.setTitle(String.valueOf(entrada.nextLine()));
		
		System.out.println ("Ingrese el texto");
		entrada = new Scanner (System.in);
		e.setText(String.valueOf(entrada.nextLine()));
		
		e.setCalendar(Calendar.getInstance());
		e.setArrayList(e.seleccionarTags(u));
		e.setIdUser(u.getId());
		
		if(this.getEntries()!=null){
			e.setId(this.getEntries().size()+1);
		}
		else{
			e.setId(1);
		}
		
		u.setEntry(e);
		this.setEntry(e);
		u.postEntry(e);
	}
	
	public ArrayList<Entry> sort(ArrayList<Entry> array){
		
		ArrayList<Entry> rta= new ArrayList<Entry>();
		ArrayList<Entry> aux=new ArrayList<Entry>();
		
		for(int x=array.size()-1;x>=0; x--){
			aux.add(array.get(x));
		}
		
		for(int i=0;i<aux.size(); i++){
			rta.add(aux.get(i));
		}
		
		return rta;
		
	}
}