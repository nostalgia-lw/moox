package com.moox.system.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_user_role")
public class UserRole implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4074046065956611680L;
	/**
     * ID
     */
    @Column(name = "id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;
    
    /**
     * 角色
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "role_id")
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    

}
