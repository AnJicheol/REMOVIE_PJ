package com.example.removie.aws.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.example.removie.log.LogGroup;
import com.example.removie.retry.IORetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * AWS S3에 이미지를 업로드하는 서비스 클래스입니다.
 *
 * <p>
 * {@link AmazonS3} 클라이언트를 사용하여 AWS S3에 이미지 파일을 업로드하고,
 * 업로드된 이미지의 URL을 반환합니다.
 * </p>
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Service
public class AWSS3Uploader implements AWSIMGUploader<AWSMovieIMGRequest> {
    private final Logger logger = LoggerFactory.getLogger(AWSS3Uploader.class);
    private final AmazonS3 amazonS3;
    private final String bucketName;

    @Autowired
    public AWSS3Uploader(AmazonS3 amazonS3, @Value("${aws.s3.bucketName}") String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }


    /**
     * AWS S3에 업로드하고, 업로드된 이미지의 URL을 반환합니다.
     *
     * <p>
     * S3에 업로드된 파일은 버킷 내에서 고유한 키를 가지며,
     * 업로드가 완료되면 해당 객체의 URL을 반환합니다.
     * </p>
     *
     * @param awsMovieIMGRequest AWS S3 업로드 요청 객체 (이미지 스트림, 메타데이터 포함)
     * @return 업로드된 이미지의 URL (실패 시 {@code null} 반환)
     * @throws AmazonServiceException AWS 서버 측 오류 발생 시 예외 처리됨
     * @throws SdkClientException AWS SDK와 클라이언트 간 통신 문제 발생 시 예외 처리됨
     */
    @LogGroup
    @IORetry
    public String uploadIMG(AWSMovieIMGRequest awsMovieIMGRequest){

        try {
            amazonS3.putObject(bucketName,
                    awsMovieIMGRequest.getKey(),
                    awsMovieIMGRequest.getImgStream(),
                    awsMovieIMGRequest.getImageMetaData());

            return amazonS3.getUrl(bucketName,
                            awsMovieIMGRequest.getKey())
                    .toString();

        } catch (AmazonServiceException e) {
            logger.error("AmazonServiceException - AWS 서버 비즈니스 문제 발생");
            logger.error("AmazonServiceException 상세 메시지: {}", e.getMessage());

        } catch (SdkClientException e) {
            logger.error("SdkClientException - AWS SDK - Client 통신 문제 발생");
            logger.error("SdkClientException 상세 메시지: {}", e.getMessage());
        }
        return null;
    }
}
