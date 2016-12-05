package org.test.zk;

import java.time.LocalDate;

import org.test.zk.dao.CPerson;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.ItemRenderer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Window;


public class Ctest01Controller extends SelectorComposer<Component> implements ItemRenderer<CPerson> {


    private static final long serialVersionUID = -8770470515037000449L;
    
    @Wire
    Button buttontest01;    
    
    @Wire
    Window windowtest01;
    
    @Wire( "#dabutton" )
    Button dasuperbutton;
    
    @Wire
    Selectbox selectbox01;
    
    @Wire
    Selectbox selectbox02;
    
    @Wire
    Button dabutton2;
    
    protected ListModelList <String> datamodel = new ListModelList <String>();
   
    protected ListModelList <CPerson> datamodelperson = new ListModelList <CPerson>();
    
    @Listen( "onClick=#buttontest01" )
    public void onClickButtontest01 (Event event){
        
        windowtest01.setTitle( "Click on button test01" );
        datamodelperson.add(new CPerson ("123","yo","tu", 0, LocalDate.parse( "1999-12-10"), "un zapato"));
        datamodelperson.add(new CPerson ("321","el","nosotros", 1, LocalDate.parse( "1989-03-12"), "un supah zapato" ));
        datamodelperson.add(new CPerson ("231","ustedes","ellos", 0, LocalDate.parse( "1985-06-09"), "un deluxe zapato"));        
        datamodel.add( "1" );
        datamodel.add( "2" );
        datamodel.add( "3" );
        datamodel.add( "4" );
        datamodel.add( "5" );
        selectbox01.setModel( datamodel );
        selectbox02.setModel( datamodelperson );
        selectbox02.setItemRenderer( this );
        datamodel.addToSelection( "1" );
        selectbox01.setSelectedIndex( 0 ); //investigar mas sobre esto
        selectbox02.setSelectedIndex( 0 );
        
        
        
    }
    
    @Listen( "onClick=#dabutton" )
    public void onClickDabutton (Event event){
        
        dasuperbutton.setLabel( "Datbutton" );
        
    }
    
    @Listen( "onClick=#dabutton2" )
    public void onClickDabutton2 (Event event){
        
        windowtest01.doModal();
        
    }
    @Listen ( "onSelect=#selectbox01" )
    public void onSelectselectbox01(Event event){
        
        if (selectbox01.getSelectedIndex()>= 0){
            
           windowtest01.setTitle( datamodel.get( selectbox01.getSelectedIndex() ) ); 
            
        }
        
    }
    
    @Listen ( "onSelect=#selectbox02" )
    public void onSelectselectbox02(Event event){
        
        if (selectbox02.getSelectedIndex()>= 0){
           
           CPerson selected = datamodelperson.get( selectbox02.getSelectedIndex()); 
           windowtest01.setTitle( selected.getID()); 
            
        }
        
    }

    @Override
    public String render( Component arg0, CPerson arg1, int arg2 ) throws Exception {
        
        return arg1.getfirstname()+ " " +arg1.getlastname();
    }
    
    
}
