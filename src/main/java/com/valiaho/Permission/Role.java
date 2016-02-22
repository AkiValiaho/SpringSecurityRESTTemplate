package com.valiaho.Permission;

import java.util.Set;

/**
 * Created by akivv on 17.2.2016.
 */
public class Role {
    private String roleName;
    public Role(String roleName, Set<Permission> permissionSet) {
        this.roleName = roleName;
        this.permissionSet = permissionSet;
    }
    private Set<Permission> permissionSet;
}
