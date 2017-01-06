package org.test.zk.zksubsystem;

import java.util.List;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.DesktopCleanup;
import org.zkoss.zk.ui.util.DesktopInit;
import org.zkoss.zk.ui.util.ExecutionCleanup;
import org.zkoss.zk.ui.util.ExecutionInit;
import org.zkoss.zk.ui.util.SessionCleanup;
import org.zkoss.zk.ui.util.SessionInit;
import org.zkoss.zk.ui.util.WebAppCleanup;
import org.zkoss.zk.ui.util.WebAppInit;

import commonlibs.commonclasses.CLanguage;
import commonlibs.commonclasses.ConstantsCommonClasses;
import commonlibs.extendedlogger.CExtendedConfigLogger;
import commonlibs.extendedlogger.CExtendedLogger;

public class CZKSubsystemEvents implements DesktopInit, DesktopCleanup, SessionInit, SessionCleanup, WebAppInit, WebAppCleanup, ExecutionInit, ExecutionCleanup {

    @Override
    public void cleanup( Execution execution0, Execution execution1, List<Throwable> arg2 ) throws Exception {
        
        System.out.println( "execution cleanup" ); 
        
    }

    @Override
    public void init( Execution execution0, Execution execution1 ) throws Exception {
        
        System.out.println( "execution init" ); 
        
    }

    @Override
    public void cleanup( WebApp webApp ) throws Exception {
        
        System.out.println( "web app cleanup" );  
        
        
        try {
           CExtendedLogger webAppLogger = CExtendedLogger.getLogger( ConstantsCommonClasses._Webapp_Logger_Name );
           
           if (webAppLogger != null) {
               
               webAppLogger.logMessage( "1",CLanguage.translateIf( null, "Webapp ending now." ) );
               
               webAppLogger.flushAndClose();
           }
           webApp.removeAttribute( ConstantsCommonClasses._Webapp_Logger_Name );
           
        }
        catch (Exception ex) {
            
            System.out.println( ex.getMessage()  );
        }
        
    }

    @Override
    public void init( WebApp webApp) throws Exception {
        
  
        System.out.println( "web app init" );
        
        try {
            
            String strRunningPath = webApp.getRealPath(ConstantsCommonClasses._WEB_INF_Dir)  + "/";
            
            CExtendedConfigLogger configLogger = new CExtendedConfigLogger();
            
            String strConfigPath = strRunningPath + ConstantsCommonClasses._Config_Dir + ConstantsCommonClasses._Logger_Config_File_Name;
            
            if (configLogger.loadConfig( strConfigPath, null, null )){
                
               CExtendedLogger webAppLogger = CExtendedLogger.getLogger( ConstantsCommonClasses._Webapp_Logger_Name );
               
               if (webAppLogger.getSetupSet() == false) {
                   
                   String strLogPath = strRunningPath + ConstantsCommonClasses._Logs_Dir + ConstantsCommonClasses._System_Dir;
                  
                   webAppLogger.setupLogger( configLogger.getInstanceID(), configLogger.getLogToScreen(), strLogPath, ConstantsCommonClasses._Webapp_Logger_File_Log, configLogger.getClassNameMethodName(), configLogger.getExactMatch(), configLogger.getLevel(), configLogger.getLogIP(), configLogger.getLogPort(), configLogger.getHTTPLogURL(), configLogger.getHTTPLogUser(), configLogger.getHTTPLogPassword(), configLogger.getProxyIP(), configLogger.getProxyPort(), configLogger.getProxyUser(), configLogger.getProxyPassword() );
                   
                   webApp.setAttribute( ConstantsCommonClasses._Webapp_Logger_App_Attribute_Key, webAppLogger );
               }
               
               webAppLogger.logMessage( "1", CLanguage.translateIf( null, "Webapp logger loaded and configured [%s]. ", ConstantsCommonClasses._Webapp_Logger_Name) );
            }
            
        }
        catch (Exception ex) {
            
            System.out.println( ex.getMessage()  );
            
        }
  
        
        
        
        
    }

    @Override
    public void cleanup( Session session ) throws Exception {
        
        System.out.println( "session cleanup" ); //al terminar el servidor

        
    }

    @Override
    public void init( Session session, Object object ) throws Exception {
        
        System.out.println( "session init" ); //al iniciar el servidor
        
    }

    @Override
    public void cleanup( Desktop desktop ) throws Exception {
        
        System.out.println( "desktop cleanup" ); 
        
    }

    @Override
    public void init( Desktop desktop, Object object ) throws Exception {
        
        System.out.println( "desktop init" ); 
        
    }
    
}
