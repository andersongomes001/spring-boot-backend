package eti.andersongomes.cursomc.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;

@Service
public class S3Service {
    private Logger LOG = LoggerFactory.getLogger(S3Service.class);
    @Autowired
    private AmazonS3 s3cliente;
    @Value("${s3.bucket}")
    private String bucketName;

    /*public void uploadFile(String localFilePath){
        try {
            File file = new File(localFilePath);

            FileReader fr = new FileReader(new File(localFilePath));
            FileWriter fw = new FileWriter(new File(localFilePath.concat(".jpg")));
            int c;
            while((c = fr.read()) != -1){
                fw.write(c);
            }
            fr.close();
            fw.close();
            s3cliente.putObject(new PutObjectRequest(bucketName,"teste", file));
        }catch (AmazonServiceException e){
            LOG.info("AmazonServiceException: "+ e.getErrorMessage());
            LOG.info("STATUS code" + e.getErrorCode());
        }
        catch (AmazonClientException e){
            LOG.info("AmazonClientException: "+ e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    public void uploadFile(String localFilePath) throws IOException {
        FileReader fr = new FileReader(new File(localFilePath));
        FileWriter fw = new FileWriter(new File(localFilePath.concat(".txt")));
        int c;
        while((c = fr.read()) != -1){
            fw.write(c);
        }
        fr.close();
        fw.close();
    }
}
