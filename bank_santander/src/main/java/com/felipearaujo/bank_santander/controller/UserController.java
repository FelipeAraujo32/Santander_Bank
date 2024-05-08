package com.felipearaujo.bank_santander.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipearaujo.bank_santander.controller.dto.UserDto;
import com.felipearaujo.bank_santander.domain.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "Users Controller", description = "RESTfull API for managing users.")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get a user by UUID", description = "Retrieve a specific user based on its UUID")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "200", description= "Retrieve a specific user based on its UUID"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDto> findByUUID(@PathVariable UUID uuid) {
        var user = userService.findById(uuid);
        return ResponseEntity.ok(new UserDto(user));
    }


    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user and return the created user's data")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        var user = userService.create(userDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).body(new UserDto(user));
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<UserDto>> findAll(){
        var users = userService.findAll();
        var userDto = users.stream().map(UserDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(userDto);
    }


    @PutMapping("/{uuid}")
    @Operation(summary = "Update a user", description = "Update the data of an existing user based on its UUID")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<UserDto> update(@PathVariable UUID uuid, @RequestBody UserDto userDto){
        var user = userService.update(uuid, userDto.toModel());
        return ResponseEntity.ok(new UserDto(user));
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Delete a user", description = "Delete an existing user based on its UUID")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "204", description = "User deleted successfully"),
        @ApiResponse(responseCode = "404", description = "User not found")
})
    public ResponseEntity<Void> delete(@PathVariable UUID uuid){
        userService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
