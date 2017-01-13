package org.test.zk.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.test.zk.database.CDatabaseConnection;
import org.test.zk.datamodel.TBLPerson;

public class TBLPersonDAO {
    
    public static TBLPerson loadData(final CDatabaseConnection dbconnection, final String strId) {
        
      TBLPerson result = null;
      
      try{
          
          if (dbconnection != null && dbconnection.getDbconnection() != null) {
            
            Statement statement = dbconnection.getDbconnection().createStatement();
            
            ResultSet resultset = statement.executeQuery( "Select * From tblperson Where ID = '" + strId + "'");
            
            if (resultset.next()){
               result = new TBLPerson(resultset.getString( "ID" ), resultset.getString( "FirstName" ), resultset.getString( "LastName" ), resultset.getInt( "Gender" ), resultset.getDate( "BirthDate" ).toLocalDate(), resultset.getString( "Comment" )); 
               //todo lo siguiente viene del CAuditableDatamodel
               result.setCreatedBy( resultset.getString( "CreatedBy" ) );
               result.setCreatedAtDate( resultset.getDate( "CreatedAtDate").toLocalDate() );
               result.setCreatedAtTime( resultset.getTime( "CreatedAtTime" ).toLocalTime() );
               result.setUpdatedBy( resultset.getString( "UpdatedBy" ) );
               result.setUpdateAtDate(resultset.getDate( "UpdatedAtDate" ) != null ? resultset.getDate( "UpdatedAtDate" ).toLocalDate() : null );
               result.setUpdateAtTime(resultset.getTime( "UpdatedAtTime" ) != null ? resultset.getTime( "UpdatedAtTime" ).toLocalTime() : null);
               
               
            }
           resultset.close(); 
           statement.close(); 
            
          }
        }
      catch (Exception ex) {
          
        ex.printStackTrace ();  
      }
      
      return result;
        
    }
    
    public static boolean deleteData (final CDatabaseConnection dbconnection, final String strId){
        
       boolean bresult = false; 
        
       try {
           
           if (dbconnection != null && dbconnection.getDbconnection() != null) {
               
               final String strSQL = "Delete * FROM tblperson Where ID = '" + strId + "'";   
               
               Statement statement = dbconnection.getDbconnection().createStatement();
               statement.executeUpdate(strSQL);
               dbconnection.getDbconnection().commit();
               bresult = true;
               statement.close();
           }    
           
       }
       catch (Exception ex) {
           if (dbconnection != null && dbconnection.getDbconnection() != null) {
               
               try {
                   
                   dbconnection.getDbconnection().rollback(); //echa para atras los cambios
                   
               }
               catch (Exception e ) {
                   
                   e.printStackTrace();
               } 
           }
           
           
           ex.printStackTrace();
           
       }
       
       return bresult;
       
    }
    
    public static boolean insertData (final CDatabaseConnection dbconnection, final TBLPerson tblperson){
        
        boolean bresult = false; 
         
        try {
           
            if (dbconnection != null && dbconnection.getDbconnection() != null) {
                
                final String strSQL = "Insert Into tblperson(ID, FirstName, LastName, Gender, BirthDate, Comment, CreatedBy, CreatedAtDate, CreatedAtTime, UpdatedBy, UpdatedAtDate, UpdatedAtTime) Values ('" + tblperson.getID() + "', '" +tblperson.getfirstname()+"',' " + tblperson.getlastname() + "', " + tblperson.getgender() + ", '" + tblperson.getbirthdate().toString() + "', '" + tblperson.getcomment() + "', 'test', '" + LocalDate.now().toString() + "', '" + LocalTime.now().toString() + "', null, null, null )";   
                
                Statement statement = dbconnection.getDbconnection().createStatement();
                statement.executeUpdate(strSQL);
                dbconnection.getDbconnection().commit();
                
                bresult = true;
                statement.close();
            }    
            
        }
        catch (Exception ex) {
            
            if (dbconnection != null && dbconnection.getDbconnection() != null) {
                
                try {
                    
                    dbconnection.getDbconnection().rollback(); //echa para atras los cambios
                    
                }
                catch (Exception e ) {
                    
                    e.printStackTrace();
                } 
            }
            
         ex.printStackTrace ();
            
        }
        
        
        return bresult;
        
     }
    
    public static boolean updateData (final CDatabaseConnection dbconnection, final TBLPerson tblperson){
        
        boolean bresult = false; 
        
        try {
           
            if (dbconnection != null && dbconnection.getDbconnection() != null) {
                
                final String strSQL = "Update tblperson Set ID = '" + tblperson.getID() + "', FirstName = '"+ tblperson.getfirstname() + "', LastName = '" + tblperson.getlastname() + "', Gender = " + tblperson.getgender() + ", BirthDate = '" + tblperson.getbirthdate().toString() + "', Comment = '" + tblperson.getcomment() + "', UpdatedBy = 'test01', UpdatedAtDate = '" + LocalDate.now().toString() + "', UpdatedAtTime = '" + LocalTime.now().toString() + "')";   
                
                Statement statement = dbconnection.getDbconnection().createStatement();
                statement.executeUpdate(strSQL);
                dbconnection.getDbconnection().commit();
                
                bresult = true;
                statement.close();
            }    
            
        }
        catch (Exception ex) {
            
            if (dbconnection != null && dbconnection.getDbconnection() != null) {
                
                try {
                    
                    dbconnection.getDbconnection().rollback(); //echa para atras los cambios
                    
                }
                catch (Exception e ) {
                    
                    e.printStackTrace();
                } 
            }
            
         ex.printStackTrace ();
            
        }
        
        
        return bresult;
        
     }
    
    public static List<TBLPerson> searchtData (final CDatabaseConnection dbconnection){
        
        List<TBLPerson> result = new ArrayList<TBLPerson>(); 
       
        try{
        
          if (dbconnection != null && dbconnection.getDbconnection() != null) {
            
            Statement statement = dbconnection.getDbconnection().createStatement();
            
            ResultSet resultset = statement.executeQuery( "Select * From tblperson" );
            
            while (resultset.next()){
               TBLPerson tblPerson = new TBLPerson(resultset.getString( "ID" ), resultset.getString( "FirstName" ), resultset.getString( "LastName" ), resultset.getInt( "Gender" ), resultset.getDate( "BirthDate" ).toLocalDate(), resultset.getString( "Comment" )); 
               //todo lo siguiente viene del CAuditableDatamodel
               tblPerson.setCreatedBy( resultset.getString( "CreatedBy" ) );
               tblPerson.setCreatedAtDate( resultset.getDate( "CreatedAtDate").toLocalDate() );
               tblPerson.setCreatedAtTime( resultset.getTime( "CreatedAtTime" ).toLocalTime() );
               tblPerson.setUpdatedBy( resultset.getString( "UpdatedBy" ) );
               tblPerson.setUpdateAtDate(resultset.getDate( "UpdatedAtDate" ) != null ? resultset.getDate( "UpdatedAtDate" ).toLocalDate() : null );
               tblPerson.setUpdateAtTime(resultset.getTime( "UpdatedAtTime" ) != null ? resultset.getTime( "UpdatedAtTime" ).toLocalTime() : null);
               result.add( tblPerson );
      
            }
           resultset.close(); 
           statement.close(); 
            
          }
        }
        catch (Exception ex) {
            
            ex.printStackTrace();
            
        }
        
        return result;
        
     }
}
