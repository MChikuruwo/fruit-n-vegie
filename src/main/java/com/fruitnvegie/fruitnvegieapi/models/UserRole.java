package com.fruitnvegie.fruitnvegieapi.models;

import javax.persistence.*;

@Entity
@Table(name = "user_role", schema = "fruit_n_veg_shopping_cart")
public class UserRole {
    private Integer id;
    private User user;
    private Role role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole that = (UserRole) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUserByUserId() {
        return user;
    }

    public void setUserByUserId(User user) {
        this.user= user;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public Role getRoleByRoleId() {
        return role;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.role = roleByRoleId;
    }
}
