import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.util.ArrayList;

public class Interface extends JFrame implements ActionListener {

    private JLabel texto;   
    private JLabel texto2;  
    private JLabel textoFiltro1; 
    private JLabel textoFiltro2;
    private JLabel textoFiltro3
    ;
    private JTextField box;        
    private JButton boton; 				//mostrar info
    private JButton boton2; 			//reset
    private JButton boton3; 			//aplicar
    private int cont;
    
    private JComboBox select;			//bibioteca
    private JComboBox select2;			//distrito
    private JComboBox select3;			//barrio
    private JComboBox select4;			//bibliotecas filtradas

    private ImageIcon image = new ImageIcon("C:\\Users\\Marta\\eclipse-workspace-2\\PuntosWifi\\src\\portada.png");
    private ImageIcon image2 = new ImageIcon("C:\\Users\\Marta\\eclipse-workspace-2\\PuntosWifi\\src\\ventana2.png");

    private JLabel imageLabel;
    
        
    public Interface(String titulo,int x, int y) {
        super();                    
        setFrame(titulo,x,y); 
        cont = 0;
    }

    private void setFrame(String titulo, int x, int y) {
        this.setTitle(titulo);              
        this.setSize(x,y);                                 
        this.setLocationRelativeTo(null);                       
        //this.setLayout(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    }

    public void init(ArrayList<String> list1, ArrayList<String> list2) {
    	
    
    	//interface components
        texto = new JLabel();
        boton = new JButton();								
        select = new JComboBox();						
        imageLabel = new JLabel(image);		
        

        texto2 = new JLabel();
        textoFiltro1 = new JLabel();
        textoFiltro2 = new JLabel();
        textoFiltro3 = new JLabel();

        boton2 = new JButton();								
        select2 = new JComboBox();	
        
        select3 = new JComboBox();	
        select4 = new JComboBox();	
        boton3 = new JButton();	
        //interface attributes 
        texto.setText("Seleccionar Punto Wifi");    
        texto.setBounds(400, 300, 200, 60);   
        boton.setText("Mostrar Informacion");   
        boton.setBounds(490, 350, 200, 26);  
        boton.addActionListener(this);      
        select.setBounds(270, 350, 200, 25);
        imageLabel.setBounds(-10 ,0, 950, 650);
        
        fillList1(list1);
        
        
        texto2.setText("B�squeda por Localizacion"); 
        texto2.setBounds(385, 400, 200, 60);  
        
        textoFiltro1.setText("Seleccionar Distrito:"); 
        textoFiltro1.setBounds(140, 440, 200, 60); 
        
        textoFiltro2.setText("Seleccionar Barrio:"); 
        textoFiltro2.setBounds(360, 440, 200, 60); 
        
        textoFiltro3.setText("Seleccionar Biblioteca:"); 
        textoFiltro3.setBounds(580, 440, 200, 60); 
        
        boton2.setText("Aplicar");   
        boton2.setBounds(470, 520, 200, 30);  
        boton2.addActionListener(this);     
        boton3.setText("Reset");  
        boton3.setBounds(250, 520, 200, 30);
        boton3.addActionListener(this);     

        select2.setBounds(140, 480, 200, 25);

        fillList2(list2);
        
        
        select3.setBounds(360, 480, 200, 25);
        select4.setBounds(580, 480, 200, 25);
        

   
        //add components
        this.add(texto);
        this.add(boton);
        this.add(select);
        
  
        this.add(texto2);
        
        this.add(textoFiltro1);
        this.add(textoFiltro2);
        this.add(textoFiltro3);
        
        this.add(boton2);
        this.add(boton3);
        this.add(select2);
        
        
        this.add(select3);
        this.add(select4);

        
        this.add(imageLabel);

        select2.addActionListener(new ActionListener() {

    		public void actionPerformed(ActionEvent arg0) {
    		
    			
    			cont = 1;
    			Location loc = new Location();
    			String seleccion2 = select2.getSelectedItem().toString();
    	        
    	       		ArrayList<String> list3 = loc.getListNeighborhood(seleccion2);
    			fillList3(list3);
    			if (cont != 0) {
    	        	select4.removeAllItems();
    	       		} 

    		}

    	});
        
        
        select3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (cont == 2){
					Space edificios = new Space();
					String seleccion3 = select3.getSelectedItem().toString();
			        System.out.println(seleccion3);
			        ArrayList<String> list4 = edificios.getListSpacesNeighboord(seleccion3);
					fillList4(list4);
					
				}
				
				cont = 2;
			}

		});

   


    } 
    
    @Override
    public void actionPerformed(ActionEvent e) {
                                     
        
        
        
     

        
        
        Space edificio = new Space();
        
        Location localizacion = new Location();
        
        
        //Button 1 action
        if (e.getActionCommand().equals("Mostrar Informacion")) {
        	
        	String seleccion = select.getSelectedItem().toString(); 
        	createWindow2(edificio,localizacion, seleccion);
    		
  
        	
        }
        
        //Button 2 action
        if (e.getActionCommand().equals("Aplicar")) {
        	if(cont == 2) {
        		
        		
        		String seleccion4 = select4.getSelectedItem().toString();
            	createWindow2(edificio,localizacion, seleccion4);

        		
        	}



            //JOptionPane.showMessageDialog(this, "Hello " + resultado + ".");    					//print message with the text from the selection box
           
        	
        	
        }
        
        //Button 3 action
        if (e.getActionCommand().equals("Reset")) {
        	cont = 0;
        	select3.removeAllItems();
        	select4.removeAllItems();
 
        	
        }
 
        
    }
    
    
    private void fillList1(ArrayList<String> list1) {
    	for (int i=0;i<list1.size();i++) {
			select.addItem(list1.get(i));
    	}
	}

    private void fillList2(ArrayList<String> list2) {
    	for (int i=0;i<list2.size();i++) {
			select2.addItem(list2.get(i));
    	}
	}

    private void fillList3(ArrayList<String> list3) {
    	select3.removeAllItems();
    	for (int i=0;i<list3.size();i++) {
			select3.addItem(list3.get(i));
    	}    	
    	
	}
    
    private void fillList4(ArrayList<String> list4) {
    	select4.removeAllItems();
    	for (int i=0;i<list4.size();i++) {
			select4.addItem(list4.get(i));
    	}    	
    	
	}
    
    private void createWindow2(Space edificio, Location localizacion, String seleccion) {
    	
    	//inicializacion de parametros
    	Interface ventana2 = new Interface(seleccion,1500,750);
    	ventana2.setBackground(Color.BLUE.brighter());
    	Font infoFont= new Font("Helvetica Neue", Font.PLAIN, 14);
    	Font infoAdicional = new Font("Verdana", Font.PLAIN, 18);
    	Font infoTitulo = new Font("Verdana", Font.BOLD, 34);
    	edificio.setHasName(seleccion);
    	edificio.getListAttSpace(seleccion);
    	//localizacion.getListAttLocation(seleccion);
    	
    	//TITULO
    	JLabel titulo = new JLabel ("INFORMACI�N");
    	titulo.setForeground(Color.WHITE);
    	titulo.setFont(infoTitulo);
    	
    	//Name
    	JLabel hasName = new JLabel("<html>" +"<B>" + "Nombre: "+"</B>" + seleccion +"</html>");
    	hasName.setFont(infoFont);
    	
    	//LOCALIZACION (calle + barrio + distrito (coordenadas))
    	
    	//Schedule
    	if(edificio.getHasSchedule()==null) {
    		edificio.setHasSchedule("Sin informaci�n");
    	}
    	JLabel hasSchedule = new JLabel("<html>" + "<B>"+ "Horario: " + "</B>" +edificio.getHasSchedule() +"</html>");
    	hasSchedule.setFont(infoFont);
    	
    	//Transporte
    	JLabel hasRoute = new JLabel("<html>" + "<B>" +"Transporte: "+ "</B>"+ edificio.getHasRoute()+"</html>");
    	hasRoute.setFont(infoFont);
    	
    	//Street
    	//JLabel hasAddress = new JLabel("Calle: "+ localizacion.getAddress());
    	
    	//Informaci�n de Contacto
    	if(edificio.getHasContact()==null) {
    		edificio.setHasContact("Sin informacion");
    	}
    	JLabel hasContact = new JLabel("<html>"+ "<B>"+"Informaci�n de contacto: " +"</B>"+edificio.getHasContact()+"</html>");
    	hasContact.setFont(infoFont);
    	
    	//URL
    	JLabel hasURL = new JLabel("<html>" + "<B>" + "URL: " + "</B>" + edificio.getHasURL() + "</html>");
    	hasURL.setFont(infoFont);
    	
    	
    	//INFORMACION ADICIONAL
    	
    	//Titulo info adicional
    	JLabel tituloInfo = new JLabel("INFORMACI�N ADICIONAL");
    	tituloInfo.setForeground(Color.WHITE);
    	tituloInfo.setFont(infoAdicional);
    	
    	//Equipamiento
    	JLabel hasEquipment = new JLabel ("<html>" + "<B>" + "Equipamiento: "+ "</B>" + edificio.getHasEquipment() +"</html>");
    	hasEquipment.setFont(infoFont);
    	
    	//Descripci�n
    	
    	//Accesibilidad
    	
    	//Coordenadas
    	
    	
    	
 
    	imageLabel = new JLabel(image2);
        
        //Aniadido a la segunda ventana: Coordenadas en la interfaz de las etiquetas
    	titulo.setBounds(50, 20, 1400, 60);
        hasName.setBounds(50, 70, 1400, 50);   
        hasSchedule.setBounds(50, 120, 1400, 50);
        hasRoute.setBounds(50, 170,1400,50);
        //hasAddress.setBounds(50, 150, 900, 50);
        hasContact.setBounds(50, 200, 1400, 50);
        hasURL.setBounds(50,220,1400, 50);
        tituloInfo.setBounds(50,280, 1400, 50);
        hasEquipment.setBounds(50,340,1400,50);
        
        
        
        imageLabel.setBounds(-10 ,0, 1500, 750);
        
        
        
        ventana2.setResizable(true);
        ventana2.setVisible(true); 
        
        
        ventana2.add(titulo);
        ventana2.add(hasName);
        ventana2.add(hasSchedule);
        ventana2.add(hasRoute);
        //ventana2.add(hasAddress);
        ventana2.add(hasContact);
        ventana2.add(hasURL);
        ventana2.add(tituloInfo);
        ventana2.add(hasEquipment);
    	
        
        ventana2.add(imageLabel);
    	
    	
    }
 
}