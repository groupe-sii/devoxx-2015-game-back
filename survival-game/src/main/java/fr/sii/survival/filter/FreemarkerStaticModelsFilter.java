package fr.sii.survival.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;

public class FreemarkerStaticModelsFilter extends OncePerRequestFilter {

	@Override
	@SuppressWarnings("deprecation")
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		BeansWrapper freemarkerDefaultBeansWrapper = BeansWrapper.getDefaultInstance();
		TemplateHashModel freemarkerStaticModels = freemarkerDefaultBeansWrapper.getStaticModels();
		request.setAttribute("statics", freemarkerStaticModels);
		filterChain.doFilter(request, response);
	}

}
