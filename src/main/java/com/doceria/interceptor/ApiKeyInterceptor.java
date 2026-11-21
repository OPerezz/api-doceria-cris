package com.doceria.interceptor;
 
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
 
import java.util.Arrays;
import java.util.List;
import java.util.Set;
 
@Component
public class ApiKeyInterceptor implements HandlerInterceptor {
 
	 private final List<String> publicPaths = Arrays.asList(
		        "/public/**",
		        "/auth/login",
		        "/auth/register",
		        "/api/v1/produtos"
	 );
	
	//solução que intercepta qualquer chamada nos controles
	//simples no controle de acesso, mas utiliza chaves estáticas
	private final Set<String> CHAVES_VALIDAS = Set.of(
			"cco123",
			"azul123"
	);
	
	@Override
   public boolean preHandle(HttpServletRequest request,
                          HttpServletResponse response,
                          Object handler) throws Exception {
		
		//receber o path da requisicao
		String path = request.getRequestURI();
		System.out.println("Passando rota: " + path);
		
       String apiKey = request.getHeader("X-API-KEY");
       
       if (apiKey == null || !isValidApiKey(apiKey)) {
           response.setStatus(HttpStatus.UNAUTHORIZED.value());
           response.getWriter().write("API Key invalida");
           return false;
       }
       
       return true;
   }
   
   private boolean isValidApiKey(String apiKey) {
   	if (!CHAVES_VALIDAS.contains(apiKey))
			return false;
       return true;
   }
}