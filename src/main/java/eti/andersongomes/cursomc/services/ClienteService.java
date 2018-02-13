package eti.andersongomes.cursomc.services;

import eti.andersongomes.cursomc.domain.Categoria;
import eti.andersongomes.cursomc.domain.Cidade;
import eti.andersongomes.cursomc.domain.Cliente;
import eti.andersongomes.cursomc.domain.Endereco;
import eti.andersongomes.cursomc.domain.enums.TipoCliente;
import eti.andersongomes.cursomc.dto.CategoriaDTO;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private BCryptPasswordEncoder pe;

    public Cliente find(Integer id){
        Cliente cliente = repo.findOne(id);
        if(cliente == null){
            throw new ObjectNotFoundException("Objeto não encontrador! Id: "+id +", TIPO:"+Cliente.class.getName()  );
        }
        return cliente;
    }

    public Cliente update(Cliente cliente) {
        Cliente objCliente = find(cliente.getId());
        updateData(objCliente, cliente);
        return repo.save(objCliente);
    }

    private void updateData(Cliente objCliente, Cliente cliente) {
        objCliente.setNome(cliente.getNome());
        objCliente.setEmail(cliente.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.delete(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir porque há pedidos relacionados");
        }

    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = new PageRequest(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }
    public Cliente fronDTO(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null,null);
    }
    public Cliente fronDTO(ClienteNewDTO clienteNewDTO){
        Cliente cli = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteNewDTO.getTipo()), pe.encode(clienteNewDTO.getSenha()));
        Cidade cidade = cidadeRepository.findOne(clienteNewDTO.getCidadeId());
        Endereco end = new Endereco(null,clienteNewDTO.getLogradouro(),clienteNewDTO.getNumero(),clienteNewDTO.getComplemento(),clienteNewDTO.getBairro(),clienteNewDTO.getCep(),cli,cidade);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(clienteNewDTO.getTelefone1());
        if(clienteNewDTO.getTelefone2()!= null){
            cli.getTelefones().add(clienteNewDTO.getTelefone2());
        }
        if(clienteNewDTO.getTelefone3()!= null){
            cli.getTelefones().add(clienteNewDTO.getTelefone3());
        }
        return cli;
    }

    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = repo.save(cliente);
        enderecoRepository.save(cliente.getEnderecos());
        return cliente;
    }
}
