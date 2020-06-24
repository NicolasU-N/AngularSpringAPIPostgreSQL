package com.example.demo.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    String username;

    @NotNull
    @NotEmpty
    String password;

    @ManyToMany
    private Set<Rol> roles;

    @OneToMany
    private Set<Server> servers;

    private boolean enable;

    private Date createAt;

    private Date updateAt;

    @PrePersist
    public void init() {

        Date date = new Date();

        this.createAt = date;
        this.updateAt = this.createAt;
        this.enable = true;

    }


    public Usuario() {
    }

    public Usuario(String username, String password, Set<Rol> roles){

        this.username = username.trim();
        this.password = password;
        this.roles = roles;

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Server> getServers() {
        return this.servers;
    }

    public void setServers(Set<Server> servers) {
        this.servers = servers;
    }

    public Set<Rol> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Date getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return this.updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    // Buenas prácticas

    public void addRol(Rol rol){

        this.roles.add(rol);

    }

    public void addServer(Server server){

        this.servers.add(server);

    }
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(username, usuario.username) && Objects.equals(password, usuario.password) && Objects.equals(servers, usuario.servers) && Objects.equals(roles, usuario.roles) && enable == usuario.enable && Objects.equals(createAt, usuario.createAt) && Objects.equals(updateAt, usuario.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, servers, roles, enable, createAt, updateAt);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", servers='" + getServers() + "'" +
            ", roles='" + getRoles() + "'" +
            ", enable='" + isEnable() + "'" +
            ", createAt='" + getCreateAt() + "'" +
            ", updateAt='" + getUpdateAt() + "'" +
            "}";
    }
   
}