package postingapp.finalsreproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postingapp.finalsreproject.model.dto.LoginRequest;
import postingapp.finalsreproject.model.dto.UserDTO;
import postingapp.finalsreproject.model.entity.User;
import postingapp.finalsreproject.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    User registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }
    @PostMapping("/login")
    ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("wkfkfkdk");
        try {
            boolean isAuthenticated = userService.authenticateUser(loginRequest);
            if (isAuthenticated) {
                return new ResponseEntity<>("Successful login.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid username/password.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
