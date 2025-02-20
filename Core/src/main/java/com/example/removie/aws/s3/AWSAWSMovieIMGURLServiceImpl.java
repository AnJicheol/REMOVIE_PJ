package com.example.removie.aws.s3;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.removie.kobis.parser.KOBISMovieIMGUriParser;
import com.example.removie.log.LogGroup;
import com.example.removie.movie.exception.InvalidImageException;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 *  파싱 된 이미지를 AWS S3에 업로드 후 업로드된 이미지의 URL을 리턴합니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */

@Service
public class AWSAWSMovieIMGURLServiceImpl implements AWSMovieIMGURLService {
    private final Logger logger = LoggerFactory.getLogger(AWSAWSMovieIMGURLServiceImpl.class);

    private final KOBISMovieIMGUriParser kobisMovieIMGUriParser;
    private final com.example.removie.aws.s3.AWSS3Uploader AWSS3Uploader;


    @Autowired
    public AWSAWSMovieIMGURLServiceImpl(KOBISMovieIMGUriParser kobisMovieIMGUriParser, AWSS3Uploader AWSS3Uploader) {
        this.kobisMovieIMGUriParser = kobisMovieIMGUriParser;
        this.AWSS3Uploader = AWSS3Uploader;
    }

    /**
     * 영화 포스터 이미지를 AWS S3에 업로드하고, 업로드된 이미지의 URL을 반환합니다.
     *
     * <p>
     * 이미지를 AWS S3에 업로드합니다, 업로드에 성공하면 S3 URL을 반환하고,
     * 실패 시 {@code null}을 반환합니다.
     * </p>
     *
     * <h3>예외 처리:</h3>
     * <ul>
     *     <li>이미지가 존재하지 않거나 유효하지 않으면 {@link InvalidImageException}을 발생시키고 {@code null}을 반환합니다.</li>
     *     <li>네트워크 오류 또는 S3 업로드 실패 시 {@link IOException}을 발생시키고 {@code null}을 반환합니다.</li>
     * </ul>
     *
     * @param movieCode 파싱 대상이 되는 영화의 식별 코드
     * @return AWS S3에 업로드된 이미지의 URL (실패 시 {@code null} 반환)
     * @throws InvalidImageException 이미지 형식이 올바르지 않을 경우 발생
     */
    @LogGroup
    @Override
    public @Nullable String uploadMovieImageAndReturnURL(String movieCode) {
        String imgURLData = kobisMovieIMGUriParser.getParsingResult(movieCode);
        if(imgURLData == null) return null;

        try {
            URL imgUrl = createURL(imgURLData);
            return AWSS3Uploader.uploadIMG(createRequest(imgUrl, movieCode));


        } catch (InvalidImageException e) {
            logger.error("이미지 형식이 맞지 않음: MovieCode : {}", movieCode);
            logger.debug("디테일 : {}", e.getMessage(), e);

        } catch (IOException e) {
            logger.error("이미지 불러 오기 실패: {}", e.getMessage(), e);
        }
        return null;
    }


    /**
     * 주어진 이미지 URL을 바탕으로 AWS S3 업로드 요청을 생성합니다.
     *
     * @param url 업로드할 이미지의 원본 URL
     * @param key S3에 저장될 이미지 파일의 키 (영화 코드)
     * @return AWS S3에 업로드할 이미지 요청 객체
     */
    private AWSMovieIMGRequest createRequest(URL url, String key){
        return new AWSIMGRequestBuilder().createAWSMovieIMGRequest(url, key);
    }

    private URL createURL(String imgURL) throws IOException {
        return URI.create(imgURL).toURL();
    }


    /**
     * AWS S3에 업로드할 이미지를 처리하고, S3 업로드 요청을 생성하는 빌더 클래스입니다.
     *
     * <p>
     * 1. 주어진 이미지 URL에서 이미지를 로드합니다. <br>
     * 2. 이미지를 바이트 스트림으로 변환합니다. <br>
     * 3. AWS S3 업로드를 위한 메타데이터를 설정합니다. <br>
     * 4. {@link AWSMovieIMGRequest} 객체를 생성하여 반환합니다.
     * </p>
     *
     * @author An_Jicheol
     * @version 1.0
     */

    public static class AWSIMGRequestBuilder {
        private final static String IMG_CONTENT_TYPE = "image/jpeg";


        /**
         * 주어진 이미지 URL을 바탕으로 AWS S3 업로드 요청 객체를 생성합니다.
         *
         * <p>
         * {@link ByteArrayOutputStream}을 먼저 사용하여 이미지 파일을 검증한 후,
         * 내부적으로 {@link ByteArrayInputStream}으로 변환하여 적은 비용으로 안정적인 데이터 처리를 수행합니다.
         * 이를 통해 이미지가 유효한지 확인한 후 S3에 업로드할 수 있도록 보장합니다.
         * </p>
         *
         * @param url 업로드할 이미지의 원본 URL
         * @param key S3에 저장될 이미지 파일의 키 (영화 코드)
         * @return AWS S3 업로드 요청 객체
         * @throws InvalidImageException 이미지 형식이 올바르지 않거나 로드에 실패한 경우 발생
         */
        public AWSMovieIMGRequest createAWSMovieIMGRequest(URL url, String key){

            ByteArrayOutputStream byteArrayOutputStream = createImgByteStream(url);
            ObjectMetadata objectMetadata = createMetaData(byteArrayOutputStream);

            return new AWSMovieIMGRequest(
                    getImgInputStream(byteArrayOutputStream),
                    objectMetadata,
                    key
            );
        }

        private ByteArrayInputStream getImgInputStream(ByteArrayOutputStream byteArrayOutputStream){
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }

        private ByteArrayOutputStream createImgByteStream(URL url){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            try {
                ImageIO.write(imgLoad(url), "jpg", byteArrayOutputStream);

            } catch (IOException e) {
                throw new InvalidImageException("AWSIMGRequestBuilder - 이미지 형식이 맞지 않음 : 이미지 로드 IOException", e);
            }
            return byteArrayOutputStream;
        }


        private ObjectMetadata createMetaData(ByteArrayOutputStream byteArrayOutputStream){
            return new ObjectMetadata() {{
                setContentType(IMG_CONTENT_TYPE);
                setContentLength(byteArrayOutputStream.size());
            }};
        }

        private BufferedImage imgLoad(URL url) throws InvalidImageException, IOException {

            BufferedImage image = ImageIO.read(url);

            if (image == null || image.getWidth() <= 0 || image.getHeight() <= 0) {
                throw new InvalidImageException("이미지 형식이 맞지 않음 : 이미지 NULL");
            }
            return image;

        }
    }
}
