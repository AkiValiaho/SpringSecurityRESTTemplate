package com.valiaho.Permission;

/**
 * Created by akivv on 17.2.2016.
 */
public class Permission {
    private String permissionName;

    public Permission(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public int hashCode() {
        return permissionName.hashCode() * 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        Permission that = (Permission) obj;
        if (permissionName.equals(that.permissionName)) {
            return false;
        }
        return true;
    }
}
