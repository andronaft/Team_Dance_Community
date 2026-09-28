package com.zuk.rest.user;

import com.zuk.model.User;
import com.zuk.model.UserProfile;
import com.zuk.service.UserProfileService;
import com.zuk.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Map;
import java.util.UUID;

/**
 * Uploads the current user's profile photo. The file name is generated on the server,
 * so a crafted name like "../../etc/passwd" can't write outside the upload directory.
 */
@RestController
@RequestMapping(value = "/api/v1/users/upload/")
public class UploadUserControllerV1 {

    private static final Map<String, String> ALLOWED_TYPES = Map.of(
            "image/jpeg", ".jpg",
            "image/png", ".png",
            "image/webp", ".webp");

    private final Path uploadDir;
    private final UserService userService;
    private final UserProfileService userProfileService;

    public UploadUserControllerV1(@Value("${app.upload.dir}") String uploadDir,
                                  UserService userService,
                                  UserProfileService userProfileService) {
        this.uploadDir = Path.of(uploadDir).toAbsolutePath().normalize();
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    @PostMapping("upload/")
    public ResponseEntity<Map<String, String>> singleFileUpload(@RequestParam("file") MultipartFile file, Principal principal) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Please select a file to upload");
        }
        String extension = ALLOWED_TYPES.get(file.getContentType());
        if (extension == null) {
            throw new IllegalArgumentException("Only JPEG, PNG and WebP images are allowed");
        }

        User user = userService.findByUsername(principal.getName());
        String fileName = user.getId() + "-" + UUID.randomUUID() + extension;
        Path target = uploadDir.resolve(fileName).normalize();
        if (!target.startsWith(uploadDir)) {
            throw new IllegalArgumentException("Invalid file name");
        }

        try (InputStream in = file.getInputStream()) {
            Files.createDirectories(uploadDir);
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new UncheckedIOException("Could not store the file", e);
        }

        UserProfile userProfile = userProfileService.findById(user.getId());
        if (userProfile != null) {
            userProfile.setImgUrl(fileName);
            userProfileService.update(userProfile);
        }
        return ResponseEntity.ok(Map.of("message", "File uploaded", "img_url", fileName));
    }
}
