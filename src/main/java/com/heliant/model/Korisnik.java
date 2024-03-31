package com.heliant.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "korisnik")
public class Korisnik extends AbstractConfigurationEntity implements UserDetails {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "korisnicko_ime")
	private String korisnickoIme;
	
	@NotNull
	@Column(name = "lozinka")
	private String lozinka;
	
	@OneToMany(mappedBy = "korisnik")
	private List<Token> tokens;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return lozinka;
	}

	@Override
	public String getUsername() {
		return korisnickoIme;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
