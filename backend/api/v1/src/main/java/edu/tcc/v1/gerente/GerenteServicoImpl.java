package edu.tcc.v1.gerente;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.cliente.ClienteServicoImp;
import edu.tcc.v1.medico.CadastrarMedicoDTO;
import edu.tcc.v1.medico.Medico;
import edu.tcc.v1.medico.MedicoServicoImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GerenteServicoImpl implements GerenteServico {

    private MedicoServicoImpl medicoServico;
    private ClienteServicoImp clienteServicoImp;

    @Override
    public ResponseEntity<Void> cadastrarMedico(CadastrarMedicoDTO dto) {
        medicoServico.cadastrarMedico(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> demitirMedico(String crm) {
        Medico medico = MedicoServicoImpl.buscarMedicoPeloCRM(crm).getBody();
        if (medico == null) return ResponseEntity.badRequest().build();
        medicoServico.demitirMedico(medico);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Medico>> buscarMedicos() {
        return medicoServico.buscarMedicos();
    }

    @Override
    public ResponseEntity<Medico> buscarMedicoPeloCRM(String crm) {
        return MedicoServicoImpl.buscarMedicoPeloCRM(crm);
    }

    @Override
    public ResponseEntity<List<Cliente>> buscarClientes() {
        return clienteServicoImp.exibirClientes();
    }

}