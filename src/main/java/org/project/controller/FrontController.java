// package org.project.controller;

// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.multipart.MultipartFile;

// import jakarta.validation.Valid;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.jpa.repository.Query;
// import org.project.DTO.ResourceInfoRequest;
// import org.project.service.FileService;
// import java.util.List;

// @Controller
// @RequestMapping("")
// public class FileController {
    
//     private final AuthController authController;
//     private final TestController testController;
    
    
//     @PostMapping("/signup")
//     public ResponseEntity<RegistrationResponseDTO> signupUser(@Valid @RequestBody UserRegistrationDto registrationDto) {

//         authController.register(registrationDto);
//         return ResponseEntity.ok(new RegistrationResponseDTO("User registered successfully"));
//     }


   
//     @PostMapping("/signin")
//     public ResponseEntity<AuthResponse> signinUser(@Valid @RequestBody UserRegistrationDto registrationDto, HttpServletRequest request) {
//         System.out.println("signinUser");
//         log.info("Attempting to sign in user: {}", registrationDto.getUsername());
//         Authentication authentication;
//         try {
//             authentication = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(
//                     registrationDto.getUsername(), 
//                     registrationDto.getPassword()
//                 )
//             );
//             log.debug("Authentication successful for user: {}", registrationDto.getUsername());
//         } catch (Exception e) {
//             log.error("Login failed for user: {}", registrationDto.getUsername(), e);
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                     .body(new AuthResponse("Login failed: " + e.getMessage()));
//         }
//         SecurityContext context = SecurityContextHolder.getContext();
//         context.setAuthentication(authentication);
//             HttpSession session = request.getSession(true);
//             session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            
//             return ResponseEntity.ok(new AuthResponse("Session created successfully"));
//     }
    
// @GetMapping("/check-session")
// public ResponseEntity<String> checkSession() {
//     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
//         return ResponseEntity.ok("Active session for: " + auth.getName());
//     }
//     return ResponseEntity.status(401).body("No active session");
// }

//     @PostMapping("/sign-out")
//     public ResponseEntity<String> logoutUser(HttpServletRequest request) {
//         SecurityContextHolder.clearContext();
//         HttpSession session = request.getSession(false);
//         if (session != null) {
//             session.invalidate();
//         }
//         System.out.println("It looks like you’re working on a Spring Boot");
//         return ResponseEntity.noContent().build();
//     }


// }