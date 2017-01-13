package org.test.zk.controllers.login;

import java.io.File;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.test.zk.constants.SystemConstants;
import org.test.zk.dao.OperatorDAO;
import org.test.zk.database.CDatabaseConnection;
import org.test.zk.database.CDatabaseConnectionConfig;
import org.test.zk.datamodel.TBLOperator;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import commonlibs.commonclasses.CLanguage;
import commonlibs.commonclasses.ConstantsCommonClasses;
import commonlibs.extendedlogger.CExtendedLogger;
import commonlibs.utils.Utilities;
import commonlibs.utils.ZKUtilities;


public class CLoginController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 2607061613647188753L;
    
    protected CExtendedLogger controllerLogger = null;
    
    protected CLanguage controllerLanguage = null;
    
    @Wire
    Textbox textboxOperator;
    @Wire
    Textbox textboxPassword;
    @Wire
    Label labelMessage;    
    @Override
    public void doAfterCompose (Component comp){
        
       try {
           
           super.doAfterCompose( comp );
           
           controllerLogger = (CExtendedLogger) Sessions.getCurrent().getWebApp().getAttribute( ConstantsCommonClasses._Webapp_Logger_App_Attribute_Key  );
           
       }
       
       catch (Exception e) {
           
        if (controllerLogger != null){
               
           controllerLogger.logException( "-1021",e.getMessage(), e );  
        } 
        
       }
        
    } 
    
    @Listen ("onChanging=#textboxOperator; onChanging=#textboxPassword")
    public void onChangeTextbox ( Event event ) {
        /*
        if ( event.getTarget().equals( textboxOperator ) ) {
        
           system.out.println ( "textbox operator" );
           
        }
        else if (event.getTarget().equals( textboxOperator ) ) {
        
           system.out.println ("textbox password");
        
        }
        */
        labelMessage.setValue( "" ); //cuando se cambie el texto en cualquiera de los dos textbox quitamos el mensaje
        
    }
    
    @Listen ("onClick=#buttonLogin; onOK=#windowLogin")
    public void onClickButtonLogin (Event event){
       
       try {
         final String strOperator = ZKUtilities.getTextBoxValue( textboxOperator, controllerLogger );  
         final String strPassword = ZKUtilities.getTextBoxValue( textboxPassword, controllerLogger );
         if (strOperator.isEmpty() == false && strPassword.isEmpty() == false ) {
            CDatabaseConnection dbconnection = new CDatabaseConnection();
            
            CDatabaseConnectionConfig databaseconnectionconfig = new CDatabaseConnectionConfig();
             
            String strRunningpath = Sessions.getCurrent().getWebApp().getRealPath( SystemConstants._WEB_INF_Dir ) + File.separator;
                          
            if (databaseconnectionconfig.loadConfig( strRunningpath + SystemConstants._Config_Dir + File.separator + SystemConstants._Database_Connection_Config_File_Name, controllerLogger, controllerLanguage )) {
               
                if (dbconnection.makeConnectionToDB( databaseconnectionconfig, controllerLogger, controllerLanguage  )){
                 
                  TBLOperator tblOperator = OperatorDAO.checkValid( dbconnection, strOperator, strPassword, controllerLogger, controllerLanguage ); 
                  
                  if (tblOperator != null) {
                     
                     labelMessage.setSclass( "" ); //cambia el color a negro del color rojo puesto por el css
                     labelMessage.setValue( "Welcome " + tblOperator.getName() + "!" );
                      
                     Session currentSession = Sessions.getCurrent(); 
                    
                     currentSession.setAttribute( SystemConstants._DB_Connection_Session_Key, dbconnection); 
                      
                     //se salva la entidad del operador en la sesion
                     currentSession.setAttribute( SystemConstants._Operator_Credential_Session_Key, tblOperator );
                     
                     controllerLogger.logMessage( "1" , CLanguage.translateIf( controllerLanguage, "Saved user credential in session for user [%s]", tblOperator.getName() ) );

                     //Obtenemos la fecha y la hora en el formato yyyy-MM-dd-HH-mm-ss
                     String strDateTime = Utilities.getDateInFormat( ConstantsCommonClasses._Global_Date_Time_Format_File_System_24, null );
                     
                     //Creamos la variable del logpath
                     String strLogPath = strRunningpath + SystemConstants._Logs_Dir + strOperator + File.separator + strDateTime + File.separator;
                     
                     //La guardamos en la sesion
                     currentSession.setAttribute( SystemConstants._Log_Path_Session_Key, strLogPath );

                     controllerLogger.logMessage( "1" , CLanguage.translateIf( controllerLanguage, "Saved user log path [%s] in session for user [%s]", strLogPath, strOperator ) );
                     
                     //se salva la hora y fecha de la session
                     currentSession.setAttribute( SystemConstants._Login_Date_Time_Session_Key, LocalDateTime.now().toString());
                     
                     controllerLogger.logMessage( "1" , CLanguage.translateIf( controllerLanguage, "Saved user login date time [%s] in session for user [%s]", strDateTime, strOperator ) );
                     
                     //Creamos la lista de logger de esta sesion
                     List<String> loggedSessionLoggers = new LinkedList<String>();
                     
                     //Guardamos la lista vacia en la sesion
                     currentSession.setAttribute( SystemConstants._Logged_Session_Loggers, loggedSessionLoggers );
                     
                     //actualizamos en bd el ultimo inicio de sesion
                     OperatorDAO.updateLastLogin( dbconnection, tblOperator.getID(), controllerLogger, controllerLanguage );
                     
                     //redireccionamos hacia el home.zul
                     Executions.sendRedirect( "/views/home/home.zul" );
                     
                     
                  }
                  else {
                      
                     labelMessage.setValue( "Invalid operator name or password" ); 
                      
                     //Messagebox.show( "Invalid operator name or password" ); 
                      
                  }
                    
                }
                
                else {
                    
                    Messagebox.show( "Error conexion fallida" );
                    
                }
                
            }
            else {
                
               Messagebox.show( "Error al leer el archivo de configuracion" ); 
                               
            }
                
         }
 
       }
           
        
       catch (Exception ex) {
           
          if (controllerLogger != null){
               
            controllerLogger.logException( "-1021",ex.getMessage(), ex );  
          }
           
       }
            
    }
    @Listen ( "onTimer=#timerKeepAliveSession" )
    public void onTimer ( Event event ) {
        
        //muestra un tablero en la parte superior de la pantalla y se ejecutara cada 120000 milisegundos
        Clients.showNotification( "Automatic renewal of the session successful", "info",  null, "before_center", 2000, true ); 
        
    }
}
