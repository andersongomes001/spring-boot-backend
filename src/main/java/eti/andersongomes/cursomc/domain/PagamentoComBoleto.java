package eti.andersongomes.cursomc.domain;

import eti.andersongomes.cursomc.domain.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PagamentoComBoleto extends  Pagamento {
    private static final long serialVersionUID = 1L;

    private Date dataVencimento;
    private Date dataPagemanto;

    public PagamentoComBoleto(){ }

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagemanto) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagemanto = dataPagemanto;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagemanto() {
        return dataPagemanto;
    }

    public void setDataPagemanto(Date dataPagemanto) {
        this.dataPagemanto = dataPagemanto;
    }
}
