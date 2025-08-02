package org.project.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ResponseInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
                              Object handler, Exception ex) throws Exception {
        
        // Проверяем, есть ли ошибка валидации в атрибутах запроса
        Object validationError = request.getAttribute("validation.error");
        if (validationError != null) {
            System.out.println("=== Interceptor detected validation error ===");
            
            // Модифицируем ответ
            response.reset();
            response.setStatus(400);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Validation failed\"}");
        }
    }
} 