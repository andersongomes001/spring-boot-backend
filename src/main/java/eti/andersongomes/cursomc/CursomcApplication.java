package eti.andersongomes.cursomc;


import eti.andersongomes.cursomc.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		s3Service.uploadFile("/home/anderson/Modelos/cursomc/src/main/java/eti/andersongomes/cursomc/danied.png");
	}
}
