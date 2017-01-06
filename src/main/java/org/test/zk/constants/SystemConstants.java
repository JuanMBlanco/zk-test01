package org.test.zk.constants;

import java.io.File;

import com.fasterxml.jackson.databind.ser.std.FileSerializer;

public class SystemConstants {
    
    public static final String _Libs_Dir ="lisbs" + File.separator;
    
    public static final String _Lib_Ext = ".jar";
    
    public static final String _Langs_Dir = "langs" + File.separator;
    
    public static final String _Lang_Ext = "init.lang";
    
    public static final String _Common_Lang_File = "Common.init.lang";
    
    public static final String _Logs_Dir = "logs" + File.separator;
    
    public static final String _Temp_Dir = "temp" + File.separator;
    
    public static final String _Security_Dir = "security" + File.separator;
    
    public static final String _DB_Connection_Session_Key = "dbconnection";
    
    public static final String _WEB_INF_Dir = "WEB-INF";
    
    public static final String _Config_Dir = "config";
    
    public static final String _Database_Connection_Config_File_Name = "database.config.xml";
    
    public static final String _Database_Connection_Production_File_Name = "database.production.config.xml";
    
    public static final String _Logger_Config_File_Name = "logger.config.xml";
    
    public static final String _Logger_Config_Production_File_Name = "logger.production.config.xml";
    
    public static final String _User_Unknown = "unknow@unknown.com";
    
    public static final String _User_Credential_Session_Key = "userCredential";
    
    public static final String _Login_Date_Time_Session_Key = "loginDateTime";
    
    public static final String _Log_Path_Session_Key = "logPath";
    
    public static final String _Webapp_Logger_App_Attribute_Key = "webAppLogger";
    
    public static final String _Webapp_Logger_Name = "webapplogger";
    
    public static final String _Webapp_Logger_File_Log = "webapplogger.log";
    
}
