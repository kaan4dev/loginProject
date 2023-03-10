package kc.loginUI.appuser;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class AppUser implements UserDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="register_sequence")
	@SequenceGenerator(name="register_sequence",sequenceName ="register_sequence",allocationSize =1)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;
	
	private Boolean locked=false;
	
	private Boolean enabled=false;
	
	public AppUser() 
	{
		super();
	}
	
	public AppUser(String firstName, String lastName, String email, String password, AppUserRole appUserRole) 
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(appUserRole.name());
		return Collections.singletonList(simpleGrantedAuthority);
	}

	public String getPassword() 
	{
		return password;
	}

	public String getUsername() 
	{
		return email;
	}

	public boolean isAccountNonExpired() 
	{
		return true;
	}

	public boolean isAccountNonLocked() 
	{
		return !locked;
	}

	public boolean isCredentialsNonExpired() 
	{
		return true;
	}

	public boolean isEnabled() 
	{
		return enabled;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public AppUserRole getAppUserRole() 
	{
		return appUserRole;
	}

	public void setAppUserRole(AppUserRole appUserRole) 
	{
		this.appUserRole = appUserRole;
	}

	public Boolean getLocked() 
	{
		return locked;
	}

	public void setLocked(Boolean locked) 
	{
		this.locked = locked;
	}

	public Boolean getEnabled()
	{
		return enabled;
	}

	public void setEnabled(Boolean enabled) 
	{
		this.enabled = enabled;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}
}
