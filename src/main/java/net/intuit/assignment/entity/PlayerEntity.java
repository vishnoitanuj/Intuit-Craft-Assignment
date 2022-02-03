package net.intuit.assignment.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "PLAYER")
public class PlayerEntity implements Serializable {

//    private static final long serialVersionUID = 4408418647685225829L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
//    private String id;

    @NotBlank
    private String userId;

    @Column(name = "NAME", nullable = false)
    private String name;

//    @Column(name = "EMAIL", nullable = false, unique = true)
//    private String email;
//
//    @Column(name = "IS_EMAIL_VERIFIED")
//    private boolean isEmailVerified;
//
//    @Column(name = "ISSUER")
//    private String issuer;

}
