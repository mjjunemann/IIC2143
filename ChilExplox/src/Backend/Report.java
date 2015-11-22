/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class Report {
    private ArchiveType type;
    private ArrayList<Record> records;
    
    public Report(ArchiveType type){
        this.type=type;
        records = new ArrayList();
    }
    public Report(ArchiveType type, User responsable){
        this.type=type;
        records = new ArrayList();
    }
    public Report(ArchiveType type, Parcel parcel){
        this.type=type;
        records = new ArrayList();
    }
    public void addRecord(Record r){
        this.records.add(r);
    }
    
    public ArchiveType getType(){
        return this.type;
    }
    public ArrayList<Record> getRecords(){
        return this.records;
    }
}
