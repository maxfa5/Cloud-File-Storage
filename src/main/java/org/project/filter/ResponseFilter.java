package org.project.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class ResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Создаем обертку для перехвата ответа
        ResponseWrapper responseWrapper = new ResponseWrapper(httpResponse);
        
        chain.doFilter(request, responseWrapper);
        
        // Получаем содержимое ответа
        String responseBody = responseWrapper.getContentAsString();
        
        // Если это ошибка валидации, модифицируем её
        if (responseBody.contains("Validation failed")) {
            System.out.println("=== Filter intercepted validation error ===");
            
            // Очищаем старый ответ
            httpResponse.reset();
            httpResponse.setStatus(400);
            httpResponse.setContentType("application/json");
            
            // Отправляем новый ответ
            String customResponse = "{\"error\": \"Validation failed\"}";
            httpResponse.getWriter().write(customResponse);
        } else {
            // Отправляем оригинальный ответ
            httpResponse.getWriter().write(responseBody);
        }
    }
    
    // Обертка для перехвата содержимого ответа
    private static class ResponseWrapper extends jakarta.servlet.http.HttpServletResponseWrapper {
        private final java.io.ByteArrayOutputStream buffer = new java.io.ByteArrayOutputStream();
        private final java.io.PrintWriter writer = new java.io.PrintWriter(buffer);
        
        public ResponseWrapper(jakarta.servlet.http.HttpServletResponse response) {
            super(response);
        }
        
        @Override
        public java.io.PrintWriter getWriter() {
            return writer;
        }
        
        public String getContentAsString() {
            writer.flush();
            return buffer.toString();
        }
    }
} 