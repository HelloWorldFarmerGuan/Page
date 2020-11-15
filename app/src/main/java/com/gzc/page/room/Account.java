package com.gzc.page.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * User: Administrator
 * Date: 2020-11-15 20:59
 * Describe:
 */
@Entity(tableName = "account")
public class Account {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "format_create_time")
    public String formatCreateTime;

    @ColumnInfo(name = "address")
    public String address;

    public Account(long id, String name, String formatCreateTime, String address) {
        this.id = id;
        this.name = name;
        this.formatCreateTime = formatCreateTime;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormatCreateTime() {
        return formatCreateTime;
    }

    public void setFormatCreateTime(String formatCreateTime) {
        this.formatCreateTime = formatCreateTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                Objects.equals(name, account.name) &&
                Objects.equals(formatCreateTime, account.formatCreateTime) &&
                Objects.equals(address, account.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, formatCreateTime, address);
    }
}
