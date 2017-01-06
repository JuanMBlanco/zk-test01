package org.test.zk.datamodel;

import java.io.Serializable;
import java.time.LocalDate;

public class TBLPerson extends CAuditableDataModel implements Serializable{

    
    private static final long serialVersionUID = -5616080817635217719L;
    
    protected String strID;
    protected String strfirstname;
    protected String strlastname;
    protected int intgender; //0 female 1 male
    protected LocalDate birthdate = null;
    protected String strcomment;
    
    //constructor
    public TBLPerson (String strID, String strfirstname, String strlastname, int intgender, LocalDate birthdate, String strcomment){
        
        this.strID = strID;
        this.strfirstname = strfirstname;
        this.strlastname = strlastname;
        this.intgender = intgender;
        this.birthdate = birthdate;
        this.strcomment = strcomment;
    }
    
    public int getgender() {
        
        return intgender;
    }



    
    public void setgender( int intgender ) {
        
        this.intgender = intgender;
    }



    
    public LocalDate getbirthdate() {
        
        return birthdate;
    }



    
    public void setbirthdate( LocalDate birthdate ) {
        
        this.birthdate = birthdate;
    }



    
    public String getcomment() {
        
        return strcomment;
    }



    
    public void setcomment( String strcomment ) {
        
        this.strcomment = strcomment;
    }


    public String getID() {
        
        return strID;
    }
    
    public void setID( String strID ) {
        
        this.strID = strID;
    }
    
    public String getfirstname() {
        
        return strfirstname;
    }
    
    public void setfirstname( String strfirstname ) {
        
        this.strfirstname = strfirstname;
    }
    
    public String getlastname() {
        
        return strlastname;
    }
    
    public void setlastname( String strlastname ) {
        
        this.strlastname = strlastname;
    }
    
    
}
