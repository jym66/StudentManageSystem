package com.example.StudentManageSystem.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "url")
    private String url;
    @NotNull(message = "权限不能为空")
    @Column(name = "allow_user")
    private Integer allowUser;
    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "parent_id")
    private String parentId;
    @Column(name = "status")
    private Integer status;
//    此注解表示不与数据库字段对应
    @Transient
    private List<Role> childMenus;

    @Column(name = "icon")
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Role> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<Role> childMenus) {
        this.childMenus = childMenus;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public Integer getAllowUser() {
//        return allowUser;
//    }

    public void setAllowUser(Integer allowUser) {
        this.allowUser = allowUser;
    }
}
