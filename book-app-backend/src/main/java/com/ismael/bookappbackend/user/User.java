package com.ismael.bookappbackend.user;

import com.ismael.bookappbackend.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
@EntityListeners(AuditingEntityListener.class)
/*
 * Clase User que implementa UserDetails y Principal para su integración con Spring Security.
 *
 * - UserDetails: Proporciona las credenciales y atributos de seguridad esenciales para la autenticación y
 *   autorización dentro de Spring Security. Esto incluye información como el nombre de usuario, la contraseña,
 *   la actividad de la cuenta (si está habilitada, expirada, bloqueada o las credenciales han expirado) y los roles/permisos.
 *
 * - Principal: Sirve para identificar al usuario actual dentro de la aplicación, permitiendo su acceso en contextos
 *   donde se requiere la identidad autenticada del usuario, como en la auditoría, control de acceso y registro de actividades.
 *
 * Estas implementaciones son críticas para manejar de manera efectiva las operaciones de seguridad y gestión de identidades
 * en la aplicación, asegurando que la información del usuario se maneje de manera coherente y segura en toda la arquitectura.
 */
public class User implements UserDetails, Principal {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstname;

    private String lastname;

    private LocalDate dateOfBirth;

    @Column(unique = true)
    private String email;

    private String password;

    private Boolean accountLocked;

    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    /*
     * Fecha y hora de creación del registro. Esta propiedad es gestionada automáticamente por Spring Data JPA.
     *
     * @CreatedDate indica a JPA que esta columna debe ser automáticamente poblada con la fecha y hora actuales
     * al crear el registro.
     * - 'nullable = false': Especifica que esta columna no puede contener valores nulos, asegurando que siempre habrá una fecha de creación.
     * - 'updatable = false': Asegura que una vez creada, la fecha de creación no puede ser modificada.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    /*
     * Fecha y hora de la última modificación del registro. Esta propiedad es gestionada automáticamente por Spring Data JPA.
     *
     * @LastModifiedDate: indica a JPA que esta columna debe ser automáticamente actualizada con la fecha y hora
     * actuales cada vez que el registro es modificado.
     * - 'insertable = false': Asegura que la fecha de última modificación no se incluya en las operaciones de inserción de SQL,
     *   lo que significa que el campo solo se utilizará y actualizará en operaciones de actualización.
     */
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    @Override
    public String getName() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private String fullName() {
        return firstname + " " + lastname;
    }
}
