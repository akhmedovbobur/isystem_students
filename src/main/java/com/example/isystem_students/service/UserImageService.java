package com.example.isystem_students.service;

import com.example.isystem_students.dto.UserImageDto;
import com.example.isystem_students.exception.ServerBadRequestException;
import com.example.isystem_students.model.UserImage;
import com.example.isystem_students.repository.UserImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
public class UserImageService {

    @Autowired
    private UserImageRepository userImageRepository;
    @Value("${serverAddress}")
    private String serverAddress;
    private final  String fileUrl = "uploads/images/";

    public UserImageDto saveToSystem(MultipartFile file){

            try {
                String YMD = getYMD();// year month day
                String type = file.getContentType().split("/")[1];
                String token = UUID.randomUUID().toString();

                String URL = YMD + "/" + token + "." + type;
                File folder = new File(fileUrl + YMD);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                Path path = Paths.get(fileUrl).resolve(URL);
                Files.copy(file.getInputStream(), path);

                return createImage(YMD, type, file.getSize(), token);
            } catch (IOException e) {
                throw new ServerBadRequestException("File not created");
            }
        }


    public Resource load(String filename) {
            try {
                UserImage entity = getImage(filename);
                Path file = Paths.get(fileUrl).resolve(entity.getPath() + "/" + entity.getToken() + "." + entity.getType());
                Resource resource = new UrlResource(file.toUri());
                if (resource.exists() || resource.isReadable()) {
                    return resource;
                } else {
                    throw new RuntimeException("Could not read the file!");
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error: " + e.getMessage());
            }
        }


    public boolean delete(Integer id) {
            UserImage userImage = getEntity(id);
            String token = userImage.getToken();
            String ymd = userImage.getPath();
            File folder = new File(fileUrl + ymd);
            if (folder.exists()) {
                folder.delete();
            }
            String imagePath = fileUrl+ymd+"/"+token+"."+userImage.getType();
            try {
                Files.deleteIfExists(
                        Paths.get(imagePath));
            } catch (NoSuchFileException e) {
                System.out.println(
                        "No such file/directory exists");
            } catch (DirectoryNotEmptyException e) {
                System.out.println("Directory is not empty.");
            } catch (IOException e) {
                System.out.println("Invalid permissions.");
            }
            userImage.setDeletedAt(LocalDateTime.now());
            userImageRepository.delete(userImage);
            return true;
        }

    public UserImage getEntity(Integer id) {
        Optional<UserImage> optional = userImageRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("Image not found");
        }
        return optional.get();
    }

    public String getYMD() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        return String.format("%s/%s/%s", year, month + 1, day);
    }

    private UserImageDto createImage(String ymd, String type, long size, String token) {
        UserImage image = new UserImage();
        image.setPath(ymd);
        image.setSize(size);
        image.setToken(token);
        image.setType(type);
        image.setStatus(true);
        image.setUrl(serverAddress + "api/v1/image/get/" + token);
        image.setCreatedAt(LocalDateTime.now());
        userImageRepository.save(image);
        UserImageDto imageDto = new UserImageDto();
        imageDto.setSize(size);
        imageDto.setPath(image.getPath());
        imageDto.setUrl(image.getUrl());
        imageDto.setId(image.getId());
        imageDto.setToken(token);
        imageDto.setType(type);
        return imageDto;
}

    private UserImage getImage(String token) {
    return userImageRepository.findByToken(token).orElseThrow(() -> new IllegalArgumentException("Image not found"));
    }
    public byte[] getImg(String token) {
        try {
            UserImage entity = getImage(token);
            String path = fileUrl + "/" + entity.getPath() + "/" + entity.getToken() + "." + entity.getType();

            byte[] imageInByte;
            BufferedImage originalImage;
            try {
                originalImage = ImageIO.read(new File(path));
            } catch (Exception e) {
                return new byte[0];
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ImageIO.write(originalImage, "png", baos);

            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public UserImageDto get(Integer id) {
        UserImage userImage = getEntity(id);
        UserImageDto dto = new UserImageDto();
        dto.setId(userImage.getId());
        dto.setUrl(userImage.getUrl());
        dto.setType(userImage.getType());
        dto.setPath(userImage.getPath());
        dto.setToken(userImage.getToken());
        return dto;
    }
}

