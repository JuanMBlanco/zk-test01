package org.test.zk.datamodel;

import java.time.LocalDate;
import java.time.LocalTime;


public class CAuditableDataModel implements IAuditableDataModel {
    
    private static final long serialVersionUID = -874275859545975951L;
    
    protected String strCreatedBy;
    protected LocalDate CreatedAtDate;
    protected LocalTime CreatedAtTime;
    
    protected String strUpdatedBy;
    protected LocalDate UpdatedAtDate;
    protected LocalTime UpdatedAtTime;
    
    @Override
    public String getCreatedBy() {
        

        return strCreatedBy;
    }
    
    @Override
    public void setCreatedBy( String strCreatedBy ) {
        
        this.strCreatedBy = strCreatedBy;
        
    }
    
    @Override
    public LocalDate getCreatedAtDate() {
        

        return CreatedAtDate;
    }
    
    @Override
    public void setCreatedAtDate( LocalDate CreatedAtDate ) {
        
       this.CreatedAtDate = CreatedAtDate;  
        
    }
    
    @Override
    public LocalTime getCreatedAtTime() {

        
        return CreatedAtTime;
    }
    
    @Override
    public void setCreatedAtTime( LocalTime CreatedAtTime ) {
        
       this.CreatedAtTime = CreatedAtTime;  
        
    }
    
    @Override
    public String getUpdatedBy() {
        

        return strUpdatedBy;
    }
    
    @Override
    public void setUpdatedBy( String strUpdatedBy ) {
        
        this.strUpdatedBy = strUpdatedBy;
        
    }
    
    @Override
    public LocalDate getUpdateAtDate() {
        
        
        return UpdatedAtDate;
    }
    
    @Override
    public void setUpdateAtDate( LocalDate UpdatedAtDate ) {
        
        this.UpdatedAtDate = UpdatedAtDate; 
        
    }
    
    @Override
    public LocalTime getUpdateAtTime() {
        
 
        return UpdatedAtTime;
    }
    
    @Override
    public void setUpdateAtTime( LocalTime UpdatedAtTime ) {
        
        this.UpdatedAtTime = UpdatedAtTime;
        
    }
    
}
