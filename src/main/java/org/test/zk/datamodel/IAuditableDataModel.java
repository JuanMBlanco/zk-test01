package org.test.zk.datamodel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public interface IAuditableDataModel extends Serializable {
    
    public String getCreatedBy();
    public void setCreatedBy( final String strCreatedBy);
    
    public LocalDate getCreatedAtDate();
    public void setCreatedAtDate( final LocalDate CreatedAtDate);
    
    public LocalTime getCreatedAtTime();
    public void setCreatedAtTime( final LocalTime CreatedAtTime);

    public String getUpdatedBy();
    public void setUpdatedBy( final String strUpdatedBy);
    
    public LocalDate getUpdateAtDate();
    public void setUpdateAtDate( final LocalDate UpdatedAtDate);
    
    public LocalTime getUpdateAtTime();
    public void setUpdateAtTime( final LocalTime UpdatedAtTime);


}
