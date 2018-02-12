package eti.andersongomes.cursomc.services;

import eti.andersongomes.cursomc.domain.Cidade;
import eti.andersongomes.cursomc.domain.Cliente;
import eti.andersongomes.cursomc.domain.Endereco;
import eti.andersongomes.cursomc.domain.PagamentoComBoleto;
import eti.andersongomes.cursomc.domain.enums.TipoCliente;
import eti.andersongomes.cursomc.dto.ClienteDTO;
import eti.andersongomes.cursomc.dto.ClienteNewDTO;
import eti.andersongomes.cursomc.repositories.CidadeRepository;
import eti.andersongomes.cursomc.repositories.ClienteRepository;
import eti.andersongomes.cursomc.repositories.EnderecoRepository;
import eti.andersongomes.cursomc.services.exceptions.DataIntegrityException;
import eti.andersongomes.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoComBoleto pagamentoComBoleto, Date instante) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(instante);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagamentoComBoleto.setDataVencimento(cal.getTime());
    }
}
