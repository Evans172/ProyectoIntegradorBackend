package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="ROLES_USUARIOS")
public class RolUsuario {
	
	private static final long serialVersionUID = 2000567672298399538L;

	@Id
	@Column(name = "ROL_USUARIO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRol_Usuario")
    @SequenceGenerator(name = "seqRol_Usuario", allocationSize = 1, sequenceName = "SEQ_ROL_USUARIO")
    @Builder.Default
    private Long id = 0L;
    
    //!ManyToOne 's
    @ManyToOne(fetch = FetchType.EAGER)                                          //*Verificado
    @JoinColumn(name = "ROL_ID", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Rol rol;

    @ManyToOne(fetch = FetchType.EAGER)                                          //*Verificado
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Usuario usuario;

}
