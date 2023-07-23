package com.hugoms154.psiproj.configuration

import com.hugoms154.psiproj.module.user.UserModel
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource


import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
  val jwtService: JwtService,
  val userDetailsService: UserDetailsService,
) : OncePerRequestFilter() {
  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    val authHeader = request.getHeader("authorization")

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      println("header not start with Bearer")
      filterChain.doFilter(request, response)
      return
    }

    println("header starts with Bearer")

    val jwtToken = authHeader.substring(7)
    val userEmail = jwtService.extractUsername(jwtToken)
    if (SecurityContextHolder.getContext().authentication == null) {
      val user: UserModel = this.userDetailsService.loadUserByUsername(userEmail) as UserModel
      if (jwtService.isValidToken(jwtToken, user)) {
        val authToken: UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
          user,
          null,
          user.authorities
        )

        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

        SecurityContextHolder.getContext().authentication = authToken
      }
    }
    filterChain.doFilter(request, response)
  }
}