package angie.Dia4;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


public class Blog {

	public static void main(String[] args) {
		
		
		Scanner textoTag;
		String nuevoTag;
		Scanner cont;
		Scanner opcion;
		
		Entry e=null;
		Tag tag;
		
		EntryManager em=EntryManager.getInstance();
		GroupManager gm=GroupManager.getInstance();
		UserManager au=UserManager.getInstance();
		TagManager tm=TagManager.getInstance();
		
		
		ArrayList<Entry> busqueda;
		
		boolean pararSistema=false, logIn=false;
		
		while (!pararSistema){
			User logged =au.loginUser();
			logIn=true;
			do{
				
				
				System.out.println ("Si desea ingresar un post, seleccione 1");
				System.out.println ("Si desea ver los 10 post mas recientes, seleccione 2");
				System.out.println ("Si desea borrar un post, seleccione 3");
				System.out.println ("Si desea agregar Tag, seleccione 4");
				System.out.println ("Si desea crear grupo, seleccione 5");
				System.out.println ("Si desea suscribirse a un grupo, seleccione 6");
				System.out.println ("Si desea ver los posts -opcion de filtrar- , seleccione 7");
				System.out.println ("Si desea mandar mail a un usuario, seleccione 8");
				System.out.println ("Si desea hacer logout, seleccione 100");
				System.out.println ("Si desea salir del sistema, seleccione 200");
				
				
				
				
				cont= new Scanner (System.in);
				int i=Integer.valueOf(cont.nextLine());
				
				switch(i){
					case 1:
						em.createEntry(e,logged);
						
						break;
					case 2:
						if(logged.getEntry()!=null){
					
							int x= logged.getEntry().size();
							if(logged.getEntry().size()>10){
								for(int y=(x-1);y>=(x-10);y--){
									logged.postEntry(logged.getEntry().get(y));
									
								}
							}
							else{
								for(int y=(x-1);y>=0;y--){
									logged.postEntry(logged.getEntry().get(y));
								}
							}
						}
				
						 
						break;
					case 3:
						System.out.println("Ingrese el titulo del post que desee eliminar");
						Scanner xx= new Scanner (System.in);
						String post=String.valueOf(xx.nextLine());
						Entry delEntry=logged.getEntryByTitle(post);
						if(delEntry!=null){
							
							try{
								System.out.println();
								logged.deleteEntry(delEntry);
								em.deleteEntry(delEntry);
								System.out.println("El post se ha borrado con éxito");
							}
							catch(Exception ex){
								System.out.println("El listado de posts esta vacio");
							}
							
						}
						else{
							System.out.println("No hay o no se encontró el post elegido para borrar");
						}
						
						break;
					case 4:
						String continuar="y";
						while(continuar.equalsIgnoreCase("y")){
							
								System.out.println ("Ingrese nuevo tag");
								textoTag = new Scanner (System.in);
								nuevoTag=String.valueOf(textoTag.nextLine());
								
								tag=new Tag();
								tag.setText(nuevoTag);
								logged.setTag(tag);
								tm.setTag(tag);
								System.out.println ("Desea ingresar otro tag? (Y/N)");
								cont = new Scanner (System.in);
								continuar=String.valueOf(cont.nextLine());
							
						}
						
						break;
					case 5: 
						Group g=new Group();
						g.setDatos();
					
					break;
					case 6: 
						Group gr;
						System.out.println ("Ingrese el nombre del grupo al cual desee suscribirse");
						opcion=new Scanner(System.in);
						gr=gm.findGroup(String.valueOf(opcion.nextLine()));
						if(gr!=null){
							gr.setSuscriptor(logged);
							logged.setGroup(gr);
						}
						else{
							System.out.println ("Error: El grupo no existe");
						}
						break;
					case 7:
						boolean seguir=true;
						int option;
						do{
							System.out.println ("Si desea ver todos los posts sin filtrar, seleccione 1");
							System.out.println ("Si desea ver posts de un usuario, seleccione 2");
							System.out.println ("Si desea ver posts filtrados por un tag, seleccione 3");
							System.out.println ("Si desea ver posts filtrados por titulo, seleccione 4");
							System.out.println ("Si desea ver los posts entre 2 fechas, seleccione 5");
							System.out.println ("Si desea volver al menu anterior, seleccione 6");
							
							opcion=new Scanner(System.in);
							option=Integer.valueOf(opcion.nextLine());
							
							switch (option){
							case 1:
								em.postEntries(em.getEntries());
								break;
							case 2:
								System.out.println ("Ingrese el nombre del usuario");
								opcion=new Scanner(System.in);
								User u=au.findUser(String.valueOf(opcion.nextLine()));
								if(u!=null){
									em.postEntries(u.getEntry());
								}
								else{
									System.out.println ("Error: El usuario no existe");
								}
								break;
							case 3:
								System.out.println ("Ingrese el tag que se desea buscar");
								opcion=new Scanner(System.in);
								tag=tm.findTag(String.valueOf(opcion.nextLine()));
								if(tag!=null){
									em.postEntries(em.searchByTag(tag));
								}
								else{
									System.out.println ("Error: El tag no existe");
								}
								break;
							case 4:
								System.out.println ("Ingrese el titulo que se desea buscar");
								opcion=new Scanner(System.in);
								String casa=String.valueOf(opcion.nextLine());
								
								System.out.println ("Indique el ordenamiento en que se van a mostrar:  asc: mostrando primero los posts mas antiguos o desc: mostrando los post mas recientes. Se muestran los mas recientes por defecto)");
								opcion=new Scanner(System.in);
								String sortTitle=String.valueOf(opcion.nextLine());
								
								if(em.searchByTitle(casa,sortTitle)!=null){
									em.postEntries(em.searchByTitle(casa,sortTitle));
								}
								else{
									System.out.println ("Error: No se encontraron coincidencias con el titulo introducido");
								}
								break;
							case 5:
								System.out.println ("Ingrese una de las fechas deseadas dd/mm/aaaa");
								opcion=new Scanner(System.in);
								String fechaInput1=String.valueOf(opcion.nextLine());
								
								if(fechaInput1.length()<10){
									if(fechaInput1.charAt(1)=='/'){
										fechaInput1="0".concat(fechaInput1);
									}
									if(fechaInput1.charAt(4)=='/'){
										fechaInput1=fechaInput1.substring(0, 3).concat("0".concat(fechaInput1.substring(3, fechaInput1.length())));
									}
								}
								
								
								Calendar fecha1 = Calendar.getInstance();
								
								
								int anio= Integer.valueOf(fechaInput1.substring(6, fechaInput1.length()));
								int mes= Integer.valueOf(fechaInput1.substring(3, 5))-1;
								int dia= Integer.valueOf(fechaInput1.substring(0, 2));
								
								fecha1.set(anio, mes, dia);
								
								System.out.println ("Ingrese la 2da fecha deseada dd/mm/aaaa");
								opcion=new Scanner(System.in);
								String fechaInput2=String.valueOf(opcion.nextLine());
			
								if(fechaInput2.length()<10){
									if(fechaInput2.charAt(1)=='/'){
										fechaInput2="0".concat(fechaInput2);
									}
									if(fechaInput2.charAt(4)=='/'){
										fechaInput2=fechaInput2.substring(0, 3).concat("0".concat(fechaInput2.substring(3, fechaInput2.length())));
									}
								}
								
								
								Calendar fecha2 = Calendar.getInstance();
								anio= Integer.valueOf(fechaInput2.substring(6, fechaInput2.length()));
								mes= Integer.valueOf(fechaInput2.substring(3, 5))-1;
								dia= Integer.valueOf(fechaInput2.substring(0, 2));
								fecha2.set(anio, mes, dia);
								
								System.out.println ("Indique el ordenamiento en que se van a mostrar:  asc: mostrando primero los posts mas antiguos  o desc: mostrando los post mas recientes. Se muestran los mas recientes por defecto)");
								opcion=new Scanner(System.in);
								String sortDate=String.valueOf(opcion.nextLine());
								
								busqueda=em.searchBetweenDates(fecha1, fecha2, sortDate);
								if(fecha2.after(fecha1)){
									busqueda=em.searchBetweenDates(fecha1, fecha2, sortDate);
								}
								else{
									busqueda=em.searchBetweenDates(fecha2, fecha1, sortDate);
								}
								em.postEntries(busqueda);
								break;
							case 6:seguir=false;
								break;
							}
						}while (seguir);
						break;
					case 8:
						System.out.println ("Ingrese el nombre del usuario a quien quiere mandar el mensaje");
						opcion=new Scanner(System.in);
						User user=au.findUser(String.valueOf(opcion.nextLine()));
						if(user!=null){
							System.out.println ("Ingrese el texto del mail");
							opcion=new Scanner(System.in);
							String texto=String.valueOf(opcion.nextLine());
							Mailer m=new Mailer();
							m.sendMail(user.getMail(), texto);
						}
						else{
							System.out.println ("Error: El usuario no existe");
						}
						break;
					case 12:
						System.out.println ("Ingrese el nombre del usuario a quien quiere mandar el mensaje");
						opcion=new Scanner(System.in);
						User getUser=au.findUser(String.valueOf(opcion.nextLine()));
						if(getUser!=null){
							System.out.println ("Ingrese el texto del mail");
							opcion=new Scanner(System.in);
							String texto=String.valueOf(opcion.nextLine());
							Mailer m=new Mailer();
							m.sendMail(getUser.getMail(), texto);
						}
						else{
							System.out.println ("Error: El usuario no existe");
						}
						break;
					case 100:
						logIn=false;
						break;
					default: 	logIn=false; pararSistema=true; 
					break;
				
				}
			}while(logIn);
			if(!pararSistema){
				System.out.println("Ha salido con exito");
			}
			else{
				System.out.println("EL programa ha finalizado");
			}
		}
			
	}
	
	
}
