/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.Date;

/**
 *
 * @author Fernando
 */

public class Record implements java.io.Serializable{
    
    private ArchiveType type;
    private String content;
    private User responsable;
    private Parcel parcel;
    private Date date;
    
    public Record(ArchiveType type, String content, User responsable,
                    Parcel parcel){
        this.type = type;
        this.content = content;
        this.responsable = responsable;
        this.parcel = parcel;
        this.date = new Date();
        this.parcel.addRecord(this);
        this.responsable.addRecord(this);
    }
    
    public ArchiveType getType(){
        return this.type;
    };
    public String getContent(){
        return this.content;
    };
    public User getResponsable(){
        return this.responsable;
    };
    public Parcel getParcel(){
        return this.parcel;
    };
    public Date getDate(){
        return this.date;
    };
    
}
