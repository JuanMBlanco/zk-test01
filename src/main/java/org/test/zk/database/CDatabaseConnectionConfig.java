package org.test.zk.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Properties;

import commonlibs.commonclasses.CLanguage;
import commonlibs.extendedlogger.CExtendedLogger;

public class CDatabaseConnectionConfig implements Serializable {



    private static final long serialVersionUID = -292714503181660487L;
    
    protected String strDriver = null;
    protected String strPrefix = null;
    protected String strHost= null;
    protected String strPort= null;
    protected String strDatabase= null;
    protected String strUser= null;
    protected String strPassword= null;
    
    public CDatabaseConnectionConfig (String strDriver, String strPrefix, String strHost, String strPort, String strDatabase, String strUser, String strPassword) {
      this.strDriver = strDriver;  
      this.strPrefix = strPrefix;
      this.strHost = strHost;
      this.strPort = strPort;
      this.strDatabase = strDatabase;
      this.strUser = strUser;
      this.strPassword = strPassword;
        
    }
    public CDatabaseConnectionConfig(){
        
        
    }

    public boolean loadConfig (String strconfigPath, CExtendedLogger localLogger, CLanguage localLanguage){
      
       boolean bresult = false;
       
       try{
           
          File configFilePath = new File(strconfigPath); 
          if (configFilePath.exists()){
            
              Properties configData = new Properties(); 
            
              FileInputStream inputStream = new FileInputStream( configFilePath );
              
              configData.loadFromXML( inputStream ); //se lee el archivo
              //codigo para mensajes
              localLogger.logMessage( "1", CLanguage.translateIf( localLanguage, "Readed config values from file [%s]", strconfigPath));
              
              this.strDriver = (String) configData.get( "driver" );
              localLogger.logMessage( "1", CLanguage.translateIf( localLanguage, "Readed config values for [%s] [%s]", "driver", this.strDriver) );
              this.strPrefix = configData.getProperty( "prefix" );
              localLogger.logMessage( "1", CLanguage.translateIf( localLanguage, "Readed config values for [%s] [%s]", "prefix", this.strPrefix) );
              this.strHost = configData.getProperty( "host" );
              localLogger.logMessage( "1", CLanguage.translateIf( localLanguage, "Readed config values for [%s] [%s]", "host", this.strHost) );
              this.strPort = configData.getProperty( "port" );
              localLogger.logMessage( "1", CLanguage.translateIf( localLanguage, "Readed config values for [%s] [%s]", "port", this.strPort) );
              this.strDatabase = configData.getProperty( "database" );
              localLogger.logMessage( "1", CLanguage.translateIf( localLanguage, "Readed config values for [%s] [%s]", "database", this.strDatabase ));
              this.strUser = configData.getProperty( "user" );
              localLogger.logMessage( "1", CLanguage.translateIf( localLanguage, "Readed config values for [%s] [%s]", "user", this.strUser) );
              this.strPassword = configData.getProperty( "password" );
              localLogger.logMessage( "1", CLanguage.translateIf( localLanguage, "Readed config values for [%s] [%s]", "password", this.strPassword) );
              
              inputStream.close();
              bresult = true;
              
          }
          else if (localLogger != null) {
            localLogger.logError( "-1001", CLanguage.translateIf( localLanguage, "config file in path [%s] not found") );  //codigo para  errores
          }
              
       }
       catch(Exception ex){
           
           if (localLogger != null) {
             
           localLogger.logException( "-1021", ex.getMessage(), ex); //codigo para exepciones
           }
       }
       
       return bresult;
        
    }
    
    public String getDriver() {
        
        return strDriver;
    }

    
    public void setDriver( String strDriver ) {
        
        this.strDriver = strDriver;
    }

    
    public String getPrefix() {
        
        return strPrefix;
    }

    
    public void setPrefix( String strPrefix ) {
        
        this.strPrefix = strPrefix;
    }

    
    public String getHost() {
        
        return strHost;
    }

    
    public void setHost( String strHost ) {
        
        this.strHost = strHost;
    }

    
    public String getPort() {
        
        return strPort;
    }

    
    public void setPort( String strPort ) {
        
        this.strPort = strPort;
    }

    
    public String getDatabase() {
        
        return strDatabase;
    }

    
    public void setDatabase( String strDatabase ) {
        
        this.strDatabase = strDatabase;
    }

    
    public String getUser() {
        
        return strUser;
    }

    
    public void setUser( String strUser ) {
        
        this.strUser = strUser;
    }

    
    public String getPassword() {
        
        return strPassword;
    }

    
    public void setPassword( String strPassword ) {
        
        this.strPassword = strPassword;
    }
}
