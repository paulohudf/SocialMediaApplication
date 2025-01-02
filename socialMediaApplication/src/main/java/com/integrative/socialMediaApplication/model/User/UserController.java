package com.integrative.socialMediaApplication.model.User;

// import com.integrative.socialMediaApplication.model.User.User;
// import com.integrative.socialMediaApplication.model.User.UserService;
//import com.example.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    // @Autowired
    // private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    //@Autowired
    //private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody User user) {
    //     Authentication authentication = authenticationManager.authenticate(
    //         new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
    //     );
    //     //String token = jwtUtil.generateToken(user.getUsername());
    //     return ResponseEntity.ok(token);
    // }
}

