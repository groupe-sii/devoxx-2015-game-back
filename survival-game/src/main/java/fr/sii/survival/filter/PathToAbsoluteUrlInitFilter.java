package fr.sii.survival.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import fr.sii.survival.util.ImageUrlUtil;

@Component
public class PathToAbsoluteUrlInitFilter extends OncePerRequestFilter {

	private boolean initialized = false;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if(!initialized) {
			ImageUrlUtil.init();
			initialized = true;
		}
		filterChain.doFilter(request, response);
	}

}
