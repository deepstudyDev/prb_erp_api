package com.prb.erp.domain.file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.chequer.axboot.core.code.AXBootTypes;
import com.chequer.axboot.core.code.Types;
import com.chequer.axboot.core.parameter.RequestParams;
import com.chequer.axboot.core.utils.EncodeUtils;
import com.prb.erp.domain.BaseService;
import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;


@Service
@Slf4j
public class CommonFileService extends BaseService<CommonFile, Long> implements InitializingBean {
    private CommonFileRepository commonFileRepository;

    @Value("${axboot.upload.repository}")
    public String uploadRepository;

    @Inject
    public CommonFileService(CommonFileRepository commonFileRepository) {
        super(commonFileRepository);
        this.commonFileRepository = commonFileRepository;
    }

    public void createBaseDirectory() {
        try {
            FileUtils.forceMkdir(new File(uploadRepository));
        } catch (IOException e) {
        }
    }

    public String getTempDir() {
        return System.getProperty("java.io.tmpdir");
    }

    public File multiPartFileToFile(MultipartFile multipartFile) throws IOException {
        String baseDir = getTempDir() + "/" + UUID.randomUUID().toString();

        FileUtils.forceMkdir(new File(baseDir));

        String tmpFileName = baseDir + "/" + FilenameUtils.getName(multipartFile.getOriginalFilename());

        File file = new File(tmpFileName);

        multipartFile.transferTo(file);
        return file;
    }

    @Transactional
    public CommonFile upload(MultipartFile multipartFile, String targetType, String targetId, int sort) throws IOException {
        return upload(multiPartFileToFile(multipartFile), targetType, targetId, sort);
    }

    @Transactional
    public CommonFile upload(File file, String targetType, String targetId, int sort) throws IOException {
        UploadParameters uploadParameters = new UploadParameters();
        uploadParameters.setFile(file);
        uploadParameters.setTargetType(targetType);
        uploadParameters.setTargetId(targetId);
        uploadParameters.setSort(sort);

        return upload(uploadParameters);
    }

    @Transactional
    public CommonFile upload(UploadParameters uploadParameters) throws IOException {
        File uploadFile = uploadParameters.getFile();

        if (uploadFile == null && uploadParameters.getMultipartFile() != null) {
            uploadFile = multiPartFileToFile(uploadParameters.getMultipartFile());
        }

        String targetType = uploadParameters.getTargetType();
        String targetId = uploadParameters.getTargetId();
        String targetId2 = uploadParameters.getTargetId2();
        String targetId3 = uploadParameters.getTargetId3();
        String tempYn = uploadParameters.getTempYn();
        String desc = uploadParameters.getDesc();

        boolean deleteIfExist = uploadParameters.isDeleteIfExist();
        boolean thumbnail = uploadParameters.isThumbnail();

        int sort = uploadParameters.getSort();
        int thumbnailWidth = uploadParameters.getThumbnailWidth();
        int thumbnailHeight = uploadParameters.getThumbnailHeight();

        String fileName = FilenameUtils.getName(uploadFile.getName());
        String extension = FilenameUtils.getExtension(fileName);
        String fileType = getFileType(extension);

        String baseName = UUID.randomUUID().toString();
        String saveName = baseName + "." + extension;
        String savePath = getSavePath(saveName);

        File file = new File(savePath);
        FileUtils.copyFile(uploadFile, file);

        if (deleteIfExist) {
            deleteByTargetTypeAndTargetId(targetType, targetId ,targetId2,targetId3);
        }

        CommonFile commonFile = new CommonFile();
        commonFile.setTargetType(targetType);
        commonFile.setTargetId(targetId);
        commonFile.setTargetId2(targetId2);
        commonFile.setTargetId3(targetId3);
        commonFile.setFileNm(fileName);
        commonFile.setSaveNm(saveName);
        commonFile.setSort(sort);
        commonFile.setDesc(desc);
        commonFile.setTempYn(tempYn);
        commonFile.setFileType(fileType);
        commonFile.setExtension(FilenameUtils.getExtension(fileName).toUpperCase());
        commonFile.setFileSize(file.length());

        if (fileType.equals(Types.FileType.IMAGE) && thumbnail) {
            try {
                Thumbnails.of(file)
                        .crop(Positions.CENTER)
                        .size(thumbnailWidth, thumbnailHeight)
                        .toFiles(new File(getBasePath()), Rename.SUFFIX_HYPHEN_THUMBNAIL);
            } catch (Exception e) {
            }
        }

        FileUtils.deleteQuietly(uploadFile);

        save(commonFile);

        return commonFile;
    }

    private String getFileType(String extension) {
        switch (extension.toUpperCase()) {
            case Types.FileExtensions.PNG:
            case Types.FileExtensions.JPG:
            case Types.FileExtensions.JPEG:
            case Types.FileExtensions.GIF:
            case Types.FileExtensions.BMP:
            case Types.FileExtensions.TIFF:
            case Types.FileExtensions.TIF:
                return Types.FileType.IMAGE;

            case Types.FileExtensions.PDF:
                return Types.FileType.PDF;

            default:
                return Types.FileType.ETC;
        }
    }

    public ResponseEntity<byte[]> downloadById(Long id) throws IOException {
        CommonFile commonFile = findOne(id);
        return download(commonFile);
    }

    public ResponseEntity<byte[]> downloadByTargetTypeAndTargetId(String targetType, String targetId , String targetId2 , String targetId3) throws IOException {
        CommonFile commonFile = get(targetType, targetId , targetId2 , targetId3);
        return download(commonFile);
    }

    public ResponseEntity<byte[]> download(CommonFile commonFile) throws IOException {
        if (commonFile == null)
            return null;

        byte[] bytes = FileUtils.readFileToByteArray(new File(getSavePath(commonFile.getSaveNm())));

        String fileName = EncodeUtils.encodeDownloadFileName(commonFile.getFileNm());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

    public void preview(HttpServletResponse response, Long id, String type) throws IOException {
        CommonFile commonFile = findOne(id);

        if (commonFile == null)
            return;

        MediaType mediaType = null;
        String imagePath = "";

        switch (commonFile.getExtension()) {
            case Types.FileExtensions.JPEG:
            case Types.FileExtensions.JPG:
                mediaType = MediaType.IMAGE_JPEG;
                break;

            case Types.FileExtensions.PNG:
                mediaType = MediaType.IMAGE_PNG;
                break;

            case Types.FileExtensions.GIF:
                mediaType = MediaType.IMAGE_GIF;
                break;

            default:
        }

        switch (type) {
            case Types.ImagePreviewType.ORIGIN:
                imagePath = getSavePath(commonFile.getSaveNm());
                break;

            case Types.ImagePreviewType.THUMBNAIL:
                imagePath = getSavePath(commonFile.getThumbnailFileName());
                break;
        }

        if (mediaType != null) {
            byte[] bytes = FileUtils.readFileToByteArray(new File(imagePath));

            response.setContentType(mediaType.toString());
            response.setContentLength(bytes.length);
            IOUtils.copy(FileUtils.openInputStream(new File(imagePath)), response.getOutputStream());
        }
    }

    public void preview(HttpServletResponse response, Long id) throws IOException {
        preview(response, id, Types.ImagePreviewType.ORIGIN);
    }

    public void thumbnail(HttpServletResponse response, Long id) throws IOException {
        preview(response, id, Types.ImagePreviewType.THUMBNAIL);
    }

    public String getBasePath() {
        return uploadRepository;

    }

    public String getSavePath(String saveName) {
        return getBasePath() + "/" + saveName;
    }

    public byte[] getFileBytes(String saveName) throws IOException {
        return FileUtils.readFileToByteArray(new File(getSavePath(saveName)));
    }

    public Page<CommonFile> getList(RequestParams<CommonFile> requestParams) {
        String targetType = requestParams.getString("targetType", "");
        String targetId = requestParams.getString("targetId", "");
        String targetId2 = requestParams.getString("targetId2", "");
        String targetId3 = requestParams.getString("targetId3", "");
        String delYn = requestParams.getString("delYn", "");
        String targetIds = requestParams.getString("targetIds", "");
        
        requestParams.addSort("sort", Sort.Direction.ASC);
        requestParams.addSort("id", Sort.Direction.DESC);

        Pageable pageable = requestParams.getPageable();

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(targetType)) {
            builder.and(qCommonFile.targetType.eq(targetType));
        }

        if (isNotEmpty(targetId)) {
            builder.and(qCommonFile.targetId.eq(targetId));
        }

        if (isNotEmpty(targetId2)) {
            builder.and(qCommonFile.targetId2.eq(targetId2));
        }

        if (isNotEmpty(targetId3)) {
            builder.and(qCommonFile.targetId3.eq(targetId3));
        }

        if (isNotEmpty(delYn)) {
            AXBootTypes.Deleted deleted = AXBootTypes.Deleted.get(delYn);
            builder.and(qCommonFile.delYn.eq(deleted));
        }

        if (isNotEmpty(targetIds)) {
            Set<String> _ids = Arrays.stream(targetIds.split(",")).collect(Collectors.toSet());
            builder.and(qCommonFile.targetId.in(_ids));
        }

        return findAll(builder, pageable);
    }
    
    public List<CommonFile> gets(RequestParams<CommonFile> requestParams) {
        String targetType = requestParams.getString("targetType", "");
        String targetId = requestParams.getString("targetId", "");
        String targetId2 = requestParams.getString("targetId2", "");
        String targetId3 = requestParams.getString("targetId3", "");
        String delYn = requestParams.getString("delYn", "");
        String targetIds = requestParams.getString("targetIds", "");
        
        requestParams.addSort("sort", Sort.Direction.ASC);
        requestParams.addSort("id", Sort.Direction.DESC);

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(targetType)) {
            builder.and(qCommonFile.targetType.eq(targetType));
        }

        if (isNotEmpty(targetId)) {
            builder.and(qCommonFile.targetId.eq(targetId));
        }

        if (isNotEmpty(targetId2)) {
            builder.and(qCommonFile.targetId2.eq(targetId2));
        }

        if (isNotEmpty(targetId3)) {
            builder.and(qCommonFile.targetId3.eq(targetId3));
        }

        if (isNotEmpty(delYn)) {
            AXBootTypes.Deleted deleted = AXBootTypes.Deleted.get(delYn);
            builder.and(qCommonFile.delYn.eq(deleted));
        }

        if (isNotEmpty(targetIds)) {
            Set<String> _ids = Arrays.stream(targetIds.split(",")).collect(Collectors.toSet());
            builder.and(qCommonFile.targetId.in(_ids));
        }

        return findAll(builder);
    }

    public CommonFile get(RequestParams<CommonFile> requestParams) {
        List<CommonFile> commonFiles = getList(requestParams).getContent();
        return isEmpty(commonFiles) ? null : commonFiles.get(0);
    }

    public CommonFile get(String targetType, String targetId, String targetId2, String targetId3) {
        RequestParams<CommonFile> requestParams = new RequestParams<>(CommonFile.class);
        requestParams.put("targetType", targetType);
        requestParams.put("targetId", targetId);
        requestParams.put("targetId2", targetId2);
        requestParams.put("targetId3", targetId3);
        return get(requestParams);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        createBaseDirectory();
    }

    @Transactional
    public void deleteFile(Long id) {
        delete(qCommonFile).where(qCommonFile.id.eq(id)).execute();
    }

    @Transactional
    public void deleteByTargetTypeAndTargetIds(String targetType, Set<String> targetIds) {
        delete(qCommonFile).where(qCommonFile.targetType.eq(targetType).and(qCommonFile.targetId.in(targetIds))).execute();
    }

    @Transactional
    private void deleteByTargetTypeAndTargetId(String targetType, String targetId, String targetId2, String targetId3) {
    	

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(targetType)) {
            builder.and(qCommonFile.targetType.eq(targetType));
        }
        
        if (isNotEmpty(targetId)) {
            builder.and(qCommonFile.targetId.eq(targetId));
        }

        if (isNotEmpty(targetId2)) {
            builder.and(qCommonFile.targetId2.eq(targetId2));
        }

        if (isNotEmpty(targetId3)) {
            builder.and(qCommonFile.targetId3.eq(targetId3));
        }
        delete(qCommonFile).where(builder).execute();
    }

    @Transactional
    public void updateOrDelete(List<CommonFile> commonFileList) {
        for (CommonFile file : commonFileList) {
            if (file.isDeleted()) {
                deleteFile(file.getId());
            } else {
                deleteFile(file.getId());
                //update(qCommonFile).set(qCommonFile.targetType, file.getTargetType()).set(qCommonFile.targetId, file.getTargetId()).where(qCommonFile.id.eq(file.getId())).execute();
            }
        }
    }
}
