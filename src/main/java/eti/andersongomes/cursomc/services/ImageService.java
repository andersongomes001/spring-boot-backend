package eti.andersongomes.cursomc.services;

import eti.andersongomes.cursomc.services.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {
    public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile){
        String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
        if(!"png".equals(ext) && !"jpg".equals(ext)){
            throw  new FileException("Somente imagens PNG e JPG são permitidos");
        }
        try {
            BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
            if("png".equals(ext)){
                img = pngToJpg(img);
            }
            return img;
        } catch (IOException e) {
            throw  new FileException("Erro ao ler arquivo");
        }
    }

    private BufferedImage pngToJpg(BufferedImage img) {
        BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(img, 0,0, Color.WHITE,null);
        return jpgImage;
    }

    public InputStream getInputStream(BufferedImage img, String extesion){
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, extesion, os);
            return new ByteArrayInputStream(os.toByteArray());

        } catch (IOException e) {
            throw  new FileException("Erro ao ler arquivo");
        }

    }
}
