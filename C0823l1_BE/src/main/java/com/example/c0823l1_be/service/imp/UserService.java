package com.example.c0823l1_be.service.imp;

import com.example.c0823l1_be.dto.ReqRes;
import com.example.c0823l1_be.dto.UserDto;
import com.example.c0823l1_be.entity.Role;
import com.example.c0823l1_be.entity.Staff;
import com.example.c0823l1_be.entity.User;
import com.example.c0823l1_be.repository.RoleRepository;
import com.example.c0823l1_be.repository.StaffRepository;
import com.example.c0823l1_be.repository.UserRepository;
import com.example.c0823l1_be.security.ChangePasswordRequest;
import com.example.c0823l1_be.service.IUserService;
import com.example.c0823l1_be.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ReqRes register(ReqRes registrationRequest) {
        ReqRes resp = new ReqRes();

        try {
            User user = new User();
            Staff staff = new Staff();
            Role role = roleRepository.findByName(registrationRequest.getRole());
            user.setUsername(registrationRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setRole(role);
            staff.setUser(user);
            staff.setFullName(registrationRequest.getFullName());
            staff.setAddress(registrationRequest.getAddress());
            staff.setDob(registrationRequest.getDob());
            staff.setPhoneNumber(registrationRequest.getPhoneNumber());
            User usersResult = userRepository.save(user);
            Staff staffResult = staffRepository.save(staff);
            if (user.getId()>0 && staff.getId()>0) {
                resp.setStaff((staffResult));
                resp.setUser((usersResult));
                resp.setMessage("User Saved Successfully");
                resp.setMessage("Staff Saved Successfully");
                resp.setStatusCode(200);
            }

        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes login(ReqRes loginRequest) {
        ReqRes response = new ReqRes();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));

            var user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole().getName());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully Logged In");

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public ReqRes refreshToken(ReqRes refreshTokenReqiest) {
        ReqRes response = new ReqRes();
        try{
            String ourUsername = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
            User users = userRepository.findByUsername(ourUsername).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenReqiest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);
            return response;

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    @Override
    public ReqRes logout(ReqRes logoutRequest) {
        ReqRes response = new ReqRes();
        try {
            // Perform any necessary cleanup or token invalidation here
            // For example, you might want to remove the token from a token store or blacklist it

            response.setStatusCode(200);
            response.setMessage("Successfully Logged Out");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while logging out: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ReqRes getAllUsers() {
        ReqRes reqRes = new ReqRes();

        try {
            List<User> result = userRepository.findAll();
            if (!result.isEmpty()) {
                reqRes.setUserList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
            return reqRes;
        }
    }



    @Override
    public ReqRes deleteUser(Integer userId) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                userRepository.deleteById(userId);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }


    @Override
public ReqRes updateUserInfo(Integer userId, UserDto updatedUser) {
    ReqRes reqRes = new ReqRes();
    try {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not found"));
        Staff staff = staffRepository.findByUser_Username(user.getUsername());
        if(user != null) {

            staff.setFullName(updatedUser.getFullName());
            staff.setAddress(updatedUser.getAddress());
            staff.setDob(updatedUser.getDob());
            staff.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setUsername(updatedUser.getUsername());
            Role newRole = roleRepository.findByName(updatedUser.getRole());
            user.setRole(newRole);
            User savedUser = userRepository.save(user);
            Staff savedStaff = staffRepository.save(staff);
                reqRes.setUser(savedUser);
                reqRes.setStaff(savedStaff);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
    return reqRes;
}

   @Override
public ReqRes getMyInfo(String username) {
    ReqRes reqRes = new ReqRes();
    try {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User Not found"));
        Staff staff = staffRepository.findByUser_Username(user.getUsername());
        Role role = roleRepository.findById(user.getRole().getId()).orElseThrow(() -> new RuntimeException("Role Not found"));
        UserDto userDto = new UserDto();
        {
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            userDto.setRole(role.getName());
            userDto.setFullName(staff.getFullName());
            userDto.setAddress(staff.getAddress());
            userDto.setDob(staff.getDob());
            userDto.setPhoneNumber(staff.getPhoneNumber());
            reqRes.setUserDto(userDto);
            reqRes.setStatusCode(200);
            reqRes.setMessage("successful");
        }
    } catch (Exception e) {
        reqRes.setStatusCode(500);
        reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
    }
    return reqRes;
}
    @Override
    public ReqRes getMyPassword(String username) {
        ReqRes reqRes = new ReqRes();
        try {
            User usersById = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User Not found"));
            reqRes.setPassword(usersById.getPassword());
            reqRes.setId(Long.valueOf(usersById.getId()));
            reqRes.setStatusCode(200);
            reqRes.setMessage("Users with username : '" + username + "' found successfully");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }
    @Override
public ReqRes updateUserPassword(Integer userId, ChangePasswordRequest updatedPassword) {
    ReqRes reqRes = new ReqRes();
    try {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not found"));
        // Check if the old password matches
        if (!passwordEncoder.matches(updatedPassword.getCurrentPassword(), user.getPassword())) {
            reqRes.setStatusCode(400);
            reqRes.setMessage("Old password is incorrect");
            return reqRes;
        }
        // Encode the new password and update it
        user.setPassword(passwordEncoder.encode(updatedPassword.getNewPassword()));
        userRepository.save(user);

        reqRes.setStatusCode(200);
        reqRes.setMessage("Password updated successfully");
    } catch (Exception e) {
        reqRes.setStatusCode(500);
        reqRes.setMessage("Error occurred while updating password: " + e.getMessage());
    }
    return reqRes;
}
}
