package com.example.demo.store;

import javax.persistence.*;

@Table(name = "all_users")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;
    
    public UserEntity(){
    	
    }
    

    public UserEntity(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", email=" + email + "]";
	}

   
}
