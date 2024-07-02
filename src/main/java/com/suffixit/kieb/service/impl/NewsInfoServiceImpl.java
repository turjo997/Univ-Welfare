package com.suffixit.kieb.service.impl;

import com.suffixit.kieb.dto.ConvertNewsInfoDTO;
import com.suffixit.kieb.dto.NewsInfoDTO;
import com.suffixit.kieb.dto.UpdateNewsInfoDTO;
import com.suffixit.kieb.entities.NewsCategory;
import com.suffixit.kieb.entities.NewsInfo;
import com.suffixit.kieb.repository.NewsCategoryRepository;
import com.suffixit.kieb.repository.NewsInfoRepository;
import com.suffixit.kieb.service.NewsInfoService;
import com.suffixit.kieb.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class NewsInfoServiceImpl implements NewsInfoService {
    private final NewsInfoRepository newsInfoRepository;
    private final NewsCategoryRepository newsCategoryRepository;

    private final String uploadDir;


    public NewsInfoServiceImpl(NewsInfoRepository newsInfoRepository, NewsCategoryRepository newsCategoryRepository, @Value("${fileStore.directory}") String uploadDir) {
        this.newsInfoRepository = newsInfoRepository;
        this.newsCategoryRepository = newsCategoryRepository;
        this.uploadDir = uploadDir;
    }

    @Override
    public String createNewsInfo(NewsInfoDTO infoDTO) throws IOException {
        boolean image = FileUploadUtil.isImageFile(infoDTO.getImage1());
        String newsTitle = infoDTO.getNewsTitle();
        Optional<NewsInfo> optional = newsInfoRepository.findByNewsTitle(newsTitle);
        if (optional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "news title already exist");
        }
        if (!image) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "only image files are allowed");
        }
        String categoryName = "/news/" + infoDTO.getCategoryName();
        Path categoryDir = createNewsDirectory(categoryName);
        String imageName = infoDTO.getImage1().getOriginalFilename();
        saveImage(categoryDir, imageName, infoDTO.getImage1());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        NewsCategory category = new NewsCategory();
        category.setCategoryName(infoDTO.getCategoryName());
        category.setCategoryShortName(infoDTO.getCategoryShortName());
        newsCategoryRepository.save(category);

        NewsInfo info = new NewsInfo();
        info.setNewsCategory(category);
        info.setNewsTitle(infoDTO.getNewsTitle());
        info.setNewsDate(infoDTO.getNewsDate());
        info.setImage1(imageName);
        info.setAddDate(LocalDateTime.now().toString());
        info.setAddUser(authentication.getName());
        info.setNewsShortDesc(infoDTO.getNewsShortDesc());
        info.setPublished(info.getPublished());
        info.setIsSticky(infoDTO.getIsSticky());
        newsInfoRepository.save(info);
        return "news info create successfully";

    }

    @Override
    public Page<ConvertNewsInfoDTO> getAll(Integer pageNo, Integer size) {

        Pageable pageable = PageRequest.of(pageNo, size, Sort.by("id").descending());
        Page<NewsInfo> newsPage;


        newsPage = newsInfoRepository.findAll(pageable);

        if (newsPage.isEmpty()) {
            return Page.empty();
        }

        List<ConvertNewsInfoDTO> resultList = new ArrayList<>();


        newsPage.getContent().forEach(newsInfo -> {

            ConvertNewsInfoDTO newsInfoDTO = ConvertNewsInfoDTO.builder().id(newsInfo.getId()).newsTitle(newsInfo.getNewsTitle()).categoryId(newsInfo.getNewsCategory().getId()).newsCategoryName(newsInfo.getNewsCategory().getCategoryName()).categoryShortName(newsInfo.getNewsCategory().getCategoryShortName()).newsShortDesc(newsInfo.getNewsShortDesc()).image1(newsInfo.getImage1()).isSticky(newsInfo.getIsSticky()).addDate(newsInfo.getAddDate()).newsDate(newsInfo.getNewsDate()).addUser(newsInfo.getAddUser()).modDate(newsInfo.getModDate()).modUser(newsInfo.getModUser()).build();

            resultList.add(newsInfoDTO);
        });

        return new PageImpl<>(resultList, newsPage.getPageable(), newsPage.getTotalElements());

    }

    @Override
    public Optional<ConvertNewsInfoDTO> getById(Integer newsId) {
        Optional<NewsInfo> newsInfo = newsInfoRepository.findById(newsId);
        return newsInfo.map(this::convertDTo);
    }

    @Override
    public Page<ConvertNewsInfoDTO> getByCategoryId(Integer catId, Pageable pageable) {
        NewsCategory newsCategory = newsCategoryRepository.findById(catId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "news category id not found"));
        Page<NewsInfo> newsInfos = newsInfoRepository.findAllByNewsCategoryOrderByNewsTitle(newsCategory, pageable);
        return newsInfos.map(this::convertDTo);

    }

    @Override
    public String updateNewsInfo(UpdateNewsInfoDTO updateDTO) throws IOException {
        NewsInfo newsInfo = newsInfoRepository.findById(updateDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NewsInfo not found for  id"));
        NewsCategory updateNews = newsCategoryRepository.findById(updateDTO.getCategoryId()).orElseThrow(null);
//        if (!FileUploadUtil.isImageFile(updateDTO.getImage1())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "only image files are allowed");
//        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String categoryName = "/news/" + updateDTO.getCategoryName();
        Path categoryDir = createNewsDirectory(categoryName);
        String imageName = null;
        if (updateDTO.getImage1() != null) {
            imageName = updateDTO.getImage1().getOriginalFilename();
            saveImage(categoryDir, imageName, updateDTO.getImage1());
        }
        updateNews.setCategoryName(updateDTO.getCategoryName());
        updateNews.setCategoryShortName(updateDTO.getCategoryShortName());
        newsCategoryRepository.save(updateNews);
        newsInfo.setNewsCategory(updateNews);
        newsInfo.setPublished(updateDTO.getPublished());
        newsInfo.setModDate(LocalDateTime.now().toString());
        newsInfo.setModUser(authentication.getName());
        if (imageName != null) {
            newsInfo.setImage1(imageName);
        }
        newsInfo.setNewsTitle(updateDTO.getNewsTitle());
        newsInfo.setNewsShortDesc(updateDTO.getNewsShortDesc());
        newsInfo.setNewsDate(updateDTO.getNewsDate());
        newsInfoRepository.save(newsInfo);
        return "news info updated successfully";
    }

    @Override
    public ResponseEntity<InputStreamResource> getImageForNews(String newsCategory, String imageName) {
        try {
            String imageDirectory = uploadDir + "/news/" + newsCategory;

            if (imageName != null) {
                Path imagePath = Paths.get(imageDirectory, imageName);

                if (Files.exists(imagePath)) {
                    InputStreamResource resource = new InputStreamResource(new FileInputStream(imagePath.toFile()));

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.IMAGE_JPEG);

                    return ResponseEntity.ok().headers(headers).body(resource);
                } else {

                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
                }
            } else {

                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image name is null");
            }

        } catch (IOException e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Something bad occurred");
        }
    }


    private ConvertNewsInfoDTO convertDTo(NewsInfo newsInfo) {
        ConvertNewsInfoDTO infoDTO = new ConvertNewsInfoDTO();
//        byte[] imgaeBytes = newsInfo.getImage1().getBytes();
        infoDTO.setId(newsInfo.getId());
        infoDTO.setCategoryId(newsInfo.getNewsCategory().getId());
        infoDTO.setNewsCategoryName(newsInfo.getNewsCategory().getCategoryName());
        infoDTO.setCategoryShortName(newsInfo.getNewsCategory().getCategoryShortName());
        infoDTO.setNewsTitle(newsInfo.getNewsTitle());
        infoDTO.setNewsShortDesc(newsInfo.getNewsShortDesc());
        infoDTO.setIsSticky(newsInfo.getIsSticky());
        infoDTO.setAddDate(newsInfo.getAddDate());
        infoDTO.setModDate(newsInfo.getModDate());
        infoDTO.setNewsDate(newsInfo.getNewsDate());
        infoDTO.setAddUser(newsInfo.getAddUser());
        infoDTO.setModUser(newsInfo.getModUser());
        infoDTO.setImage1(newsInfo.getImage1());
        return infoDTO;
    }

    private Path createNewsDirectory(String categoryName) throws IOException {
        Path categoryDir = Paths.get(uploadDir, categoryName);
        return Files.createDirectories(categoryDir);
    }

    private void saveImage(Path categoryDir, String fileName, MultipartFile image1) throws IOException {
        if (image1 != null && !image1.isEmpty()) {
            FileUploadUtil.saveFile(categoryDir.toString(), fileName, image1);
        }
    }
}

