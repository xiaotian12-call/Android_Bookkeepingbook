package com.me.familybookkeepingbook;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AccountRecord {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "uid")
    private int uid;
    @ColumnInfo(name = "cost_time")
    private String costTime;
    @ColumnInfo(name = "cost_type")
    private String costType;
    @ColumnInfo(name = "cost_money")
    private Double costMoney;

    public AccountRecord(int uid, String costTime, String costType, Double costMoney) {
        this.uid = uid;
        this.costTime = costTime;
        this.costType = costType;
        this.costMoney = costMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public Double getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(Double costMoney) {
        this.costMoney = costMoney;
    }
}
