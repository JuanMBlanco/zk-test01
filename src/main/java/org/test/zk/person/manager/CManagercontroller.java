package org.test.zk.manager;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.test.zk.dao.CPerson;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;


public class CManagercontroller extends SelectorComposer<Component> {


    private static final long serialVersionUID = -6898635722548039557L;
    
    protected ListModelList <CPerson> datamodel = new ListModelList <CPerson>();
    
    public class rendererHelper implements ListitemRenderer<CPerson> {

        @Override
        public void render( Listitem listitem, CPerson person, int intIndex ) throws Exception {
            
           try{
               
             Listcell cell = new Listcell ();  
             cell.setLabel( person.getID() );
             listitem.appendChild( cell );
             cell = new Listcell ();  
             cell.setLabel( person.getfirstname() );
             listitem.appendChild( cell );
             cell = new Listcell ();  
             cell.setLabel( person.getlastname() );
             listitem.appendChild( cell );
             cell = new Listcell ();  
             cell.setLabel( person.getgender() == 0 ? "female" : "male" );
             listitem.appendChild( cell );
             cell = new Listcell ();  
             cell.setLabel( person.getbirthdate().toString() );
             listitem.appendChild( cell );
             cell = new Listcell ();  
             cell.setLabel( person.getcomment() );
             listitem.appendChild( cell );
           } 
           
           catch (Exception ex){
              ex.printStackTrace( ); 
               
           }
            
            
        }
        
        
    }
    
    
    @Wire
    Window windowmanager;
    @Wire
    Listbox listboxPerson;
    @Wire 
    Button buttonmodify;
    @Wire 
    Button buttonadd;
    @Override
    public void doAfterCompose( Component comp ) {
        
        try {
            super.doAfterCompose( comp );
            CPerson person01 = new CPerson ("111", "yo", "tu", 0, LocalDate.parse( "1999-12-10"), "un zapato");
            CPerson person02 = new CPerson ("222", "el", "nosotros", 1, LocalDate.parse( "1989-03-12"), "un supah zapato" );
            CPerson person03 = new CPerson ("333", "ustedes", "ellos", 0, LocalDate.parse( "1985-06-09"), "un deluxe zapato");
            datamodel.add( person01 );
            datamodel.add( person02 );
            datamodel.add( person03 );
            /*final Execution execution = Executions.getCurrent();
            
            CPerson personToModify = (CPerson) execution.getArg().get( "personToModify" );  
            CPerson personToDelete = (CPerson) execution.getArg().get( "personToDelete" );
            datamodel.add( personToModify );
            datamodel.remove( personToDelete );*/
            listboxPerson.setModel( datamodel );
            listboxPerson.setMultiple( true ); //para selecciones multiples
            listboxPerson.setItemRenderer( new rendererHelper() );
            
        }
        catch ( Exception e ) {
            
            e.printStackTrace();
        }   
        
    }
    @Listen ( "onClick=#buttonadd" )
    public void onClickbuttonadd (Event event){
       Map<String,Object> arg = new HashMap<String,Object>();
       arg.put( "buttonadd", buttonadd );
       Window win = ( Window ) Executions.createComponents("/dialog.zul", null, arg); 
       win.doModal();
    }
    
    @Listen ( "onClick=#buttonmodify" )
    public void onClickbuttonmodify (Event event){
        Set<CPerson> Selecteditems = datamodel.getSelection();
        
        if (Selecteditems != null && Selecteditems.size() > 0){
            
           CPerson person = Selecteditems.iterator().next(); 
           Map<String,Object> arg = new HashMap<String,Object>();
           arg.put("personToModify", person);
           arg.put( "buttonmodify", buttonmodify );
           Window win = ( Window ) Executions.createComponents("/dialog.zul", null, arg);
           win.doModal();
           
           /*final Execution execution = Executions.getCurrent();
           
           CPerson personToModify = (CPerson) execution.getArg().get( "personToModify" );     
           if (datamodel.add( personToModify ) == true){ 
           datamodel.remove( person );
           listboxPerson.setModel( datamodel );
           listboxPerson.setItemRenderer( new rendererHelper() );
           }*/
           //windowmanager.detach();
        }
        else{
            
            Messagebox.show( "no hay seleccion");
            
        } 
        
    }
    /*@Listen ("onClick=#buttonrefresh")
    public void onClickbuttonrefresh (Event event){
      
      Set<CPerson> Selecteditems = datamodel.getSelection();
     
      final Execution execution = Executions.getCurrent();      
      CPerson personToModify = (CPerson) execution.getArg().get( "personToModify" );  
      datamodel.add( personToModify ); 
      //rendererHelper renderer = new rendererHelper();
      //renderer.render(, personToModify, listboxPerson.getSelectedIndex() );
      CPerson person = Selecteditems.iterator().next();
      datamodel.remove( person );
      listboxPerson.setItemRenderer( new rendererHelper() );
    }*/
    
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    @Listen ( "onClick=#buttondelete" )
    public void onClickbuttondelete (Event event){
      Set<CPerson> Selecteditems = datamodel.getSelection();
       
      if (Selecteditems != null && Selecteditems.size() > 0){
          
          String strbuffer = null;
          for (CPerson person : Selecteditems ){
           
            if (strbuffer==null){
              strbuffer = person.getID() + " " + person.getfirstname() +  " " + person.getlastname();   
            }  
            else{  
              strbuffer = strbuffer + "\n" + person.getID() + " " + person.getfirstname() +  " " + person.getlastname();
            }
          } 
          Messagebox.show("you are deleting " + Integer.toString( Selecteditems.size() ) + " register/s, you want to continue? \n"+strbuffer , "Eliminar", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
              public void onEvent(Event evt) throws InterruptedException {
                  if (evt.getName().equals("onOK")) {
                      /*alert("Data Saved !");
                  } else if (evt.getName().equals("onIgnore")) {
                      Messagebox.show("Ignore Save", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
                  } else {
                      alert("Save Canceled !");*/
                     while (Selecteditems.iterator().hasNext() ){
                        CPerson person = Selecteditems.iterator().next(); 
                        datamodel.remove( person );
                         
                     } 
                  
                  }
                  
              }
          });
            
       }  
       else{
           
           
           Messagebox.show( "no hay seleccion");
       } 
        
        
    }
    @Listen ("onDialogClose=#buttonmodify")
    public void onDialogCloseModify (Event event){
     Set<CPerson> Selecteditems = datamodel.getSelection();  
     CPerson person = Selecteditems.iterator().next();
     CPerson personToModify = (CPerson) event.getData();
     datamodel.add( personToModify );
     datamodel.remove( person );
     listboxPerson.setModel( datamodel );
     listboxPerson.setItemRenderer( new rendererHelper() );
    }
    @Listen ("onDialogClose=#buttonadd")
    public void onDialogCloseAdd (Event event){
     CPerson personToAdd = (CPerson) event.getData();
     datamodel.add( personToAdd );
     listboxPerson.setModel( datamodel );
     listboxPerson.setItemRenderer( new rendererHelper() );
    }
    
}
