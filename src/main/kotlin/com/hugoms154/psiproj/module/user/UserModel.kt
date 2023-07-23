package com.hugoms154.psiproj.module.user


import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(
  name = "\"user\"", uniqueConstraints = [UniqueConstraint(columnNames = ["email"])]
)
data class UserModel(
  @Column var name: String,

  @Column var surname: String,

  @Column(name = "password") var pass: String,

  @Column var email: String,

  @Enumerated(EnumType.STRING)
  var role: Role? = Role.USER,

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) : UserDetails {
  override fun getAuthorities(): List<GrantedAuthority> {
    return listOf(SimpleGrantedAuthority(role?.name ?: ""))
  }

  override fun getPassword(): String {
    return pass
  }

  override fun getUsername(): String {
    return email
  }

  override fun isAccountNonExpired(): Boolean {
    return true
  }

  override fun isAccountNonLocked(): Boolean {
    return true
  }

  override fun isCredentialsNonExpired(): Boolean {
    return true
  }

  override fun isEnabled(): Boolean {
    return true
  }
}
