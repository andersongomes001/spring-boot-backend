package eti.andersongomes.cursomc.services;

import eti.andersongomes.cursomc.domain.Cidade;
import eti.andersongomes.cursomc.domain.Cliente;
import eti.andersongomes.cursomc.domain.Endereco;
import eti.andersongomes.cursomc.domain.enums.Perfil;
import eti.andersongomes.cursomc.domain.enums.TipoCliente;
import eti.andersongomes.cursomc.dto.ClienteDTO;
import eti.andersongomes.cursomc.dto.ClienteNewDTO;
import eti.andersongomes.cursomc.repositories.CidadeRepository;
import eti.andersongomes.cursomc.repositories.ClienteRepository;
import eti.andersongomes.cursomc.repositories.EnderecoRepository;
import eti.andersongomes.cursomc.security.UserSS;
import eti.andersongomes.cursomc.services.exceptions.AuthorizationException;
import eti.andersongomes.cursomc.services.exceptions.DataIntegrityException;
import eti.andersongomes.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;
    public  List<Cidade> findByEstado(Integer estadoId){
        return cidadeRepository.findCidades(estadoId);
    }
}
