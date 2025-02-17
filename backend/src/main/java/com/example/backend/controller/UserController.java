package com.example.backend.controller;

import com.example.backend.domain.entity.User;
import com.example.backend.domain.service.UserService;
import com.example.backend.utils.BadRequestException;
import com.example.backend.utils.InternalServerException;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;  // 5MB
    private static final String UPLOAD_DIR = "backend/src/main/images";

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return ResponseEntity.ok(service.findAllUsers());
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable @Positive int id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable @Positive int id) {
        try {
            int affectedRows = service.deleteById(id);
            if (affectedRows == 0) {
                throw new InternalServerException("user not found");
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getMe(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Map<String, Object> me = Map.of(
                "id", user.getId(),
                "name", user.getUsername(),
                "email", user.getEmail(),
                "isAdmin", user.isAdmin()
        );
        return ResponseEntity.ok(me);
    }

    @PostMapping("/image")
    public ResponseEntity<Map<String, String>> updateImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new BadRequestException("File is empty");
        }

        if (!isSupportedContentType(file.getContentType())) {
            throw new BadRequestException("Only JPEG and PNG images are allowed");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new BadRequestException("File size exceeds the 5MB limit");
        }

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFileName = Objects.requireNonNull(file.getOriginalFilename());
            String uniqueFileName = "img_" + UUID.randomUUID() + "_" + originalFileName;

            Path destinationFile = uploadPath.resolve(uniqueFileName).normalize().toAbsolutePath();
            if (!destinationFile.getParent().startsWith(uploadPath.toAbsolutePath())) {
                throw new SecurityException("Invalid file path");
            }

            file.transferTo(destinationFile.toFile());

            return ResponseEntity.ok(Map.of(
                    "message", "File uploaded successfully",
                    "filePath", destinationFile.toString()
            ));
        } catch (IOException | SecurityException e) {
            throw new InternalServerException("Failed to upload file", e);
        }
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png"));
    }
}
