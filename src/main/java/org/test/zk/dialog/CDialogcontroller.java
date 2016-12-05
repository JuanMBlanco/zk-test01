package org.test.zk.dialog;

import org.test.zk.dao.CPerson;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


public class CDialogcontroller extends SelectorComposer<Component> {


    private static final long serialVersionUID = -1260793905224887396L;
    
    protected ListModelList <String> datamodel = new ListModelList <String>();
    
    @Wire
    Window Windowperson;
    @Wire
    Button buttonaccept;
    @Wire
    Button buttoncancel;
    @Wire
    Label LabelId;
    @Wire
    Textbox TextboxId;
    @Wire
    Label Labelfirstname;
    @Wire
    Textbox Textboxfirstname;
    @Wire
    Label Labellastname;
    @Wire
    Textbox Textboxlastname;
    @Wire
    Label Tabelsex;
    @Wire
    Selectbox Selectboxsex;
    @Wire
    Label Labelbirthdate;
    @Wire
    Datebox Dateboxbirthdate;
    @Wire
    Label Labelcomment;
    @Wire
    Textbox Textboxcomment;
    @Override
    public void doAfterCompose( Component comp ) {
        
        try {
            super.doAfterCompose( comp );
            Dateboxbirthdate.setFormat( "dd/MM/yyyy" );
            datamodel.add( "female" );
            datamodel.add( "male" );
            Selectboxsex.setModel( datamodel );
            Selectboxsex.setSelectedIndex( 0 );
            datamodel.addToSelection( "female" );
            final Execution execution = Executions.getCurrent();
            
            CPerson personToModify = (CPerson) execution.getArg().get( "personToModify" );
            TextboxId.setValue( personToModify.getID() );
            Textboxfirstname.setValue( personToModify.getfirstname() );
            Textboxlastname.setValue( personToModify.getlastname() );
            Textboxcomment.setValue( personToModify.getcomment() );
            if (personToModify.getgender()== 0){
                
                
              datamodel.addToSelection( "female" );
            
            
            }
            else{
                
              datamodel.addToSelection( "male" );  
                
            }
           
            Dateboxbirthdate.setValue( java.sql.Date.valueOf(personToModify.getbirthdate()) );

            
            
        }
        catch ( Exception e ) {
            
            e.printStackTrace();
        }
    }
    @Listen ( "onClick=#buttonaccept" )
    public void onClickbuttonaccept (Event event){
     
        Messagebox.show("id="+TextboxId.getValue() , "accept", Messagebox.OK, Messagebox.INFORMATION);
        Windowperson.detach();
    }
    @Listen ( "onClick=#buttoncancel" )
    public void onClickbuttoncancel (Event event){
        
        Messagebox.show("cancel duh" , "cancel", Messagebox.OK, Messagebox.EXCLAMATION);
        Windowperson.detach();
    }
}
