package edu.tcc.v1.cliente;

import edu.tcc.v1.consulta.AgendarConsultaDTO;
import edu.tcc.v1.consulta.Consulta;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
@AllArgsConstructor
public class ClienteControladorImpl implements ClienteControlador {
    
    private ClienteServicoImpl servico;
    
    @Override
    @PostMapping("cliente")
    public ResponseEntity<Void> cadastrarCliente(@RequestBody CadastrarClienteDTO dto) {
        return servico.cadastrarCliente(dto);
    }

    @Override
    @PostMapping("consulta/{cpf}/{crm}")
    public ResponseEntity<Void> agendarConsulta(@RequestBody AgendarConsultaDTO dto, 
                                                @PathVariable(name = "crm") String crm, 
                                                @PathVariable(name = "cpf") String cpf) {
        return servico.agendarConsulta(dto, crm, cpf);
    }

    @Override
    @PatchMapping("consulta/{cpf}/{dataAgendamento}")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable(name = "cpf") String cpf,
                                          @PathVariable(name = "dataAgendamento") String dataAgendamento) {
        return servico.cancelarConsulta(cpf, dataAgendamento);
    }

    @Override
    @GetMapping("consultas/{cpf}")
    public ResponseEntity<List<Consulta>> buscarConsultas(@PathVariable(name = "cpf") String cpf) {
        return servico.buscarConsultas(cpf);
    }

    @Override
    @GetMapping("consultas/agendadas/{cpf}")
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadas(@PathVariable(name = "cpf") String cpf) {
        return servico.buscarConsultasAgendadas(cpf);
    }

    @Override
    @GetMapping("consultas/canceladas/{cpf}")
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladas(@PathVariable(name = "cpf") String cpf) {
        return servico.buscarConsultasCanceladas(cpf);
    }

    @Override
    @GetMapping("consultas/entre-datas/{cpf}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> buscarConsultasEntreDatas(@PathVariable(name = "cpf") String cpf, 
                                                                    @PathVariable(name = "dataInicial") String dataInicial, 
                                                                    @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasEntreDatas(cpf, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/agendadas/entre-datas/{cpf}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasEntreDatas(@PathVariable(name = "cpf") String cpf, 
                                                                             @PathVariable(name = "dataInicial") String dataInicial, 
                                                                             @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasAgendadasEntreDatas(cpf, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/canceladas/entre-datas/{cpf}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasEntreDatas(@PathVariable(name = "cpf") String cpf,
                                                                              @PathVariable(name = "dataInicial") String dataInicial, 
                                                                              @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasCanceladasEntreDatas(cpf, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/entre-datas/{cpf}/{nome}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> buscarConsultasEntreDatasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf, 
                                                                                    @PathVariable(name = "nome") String nome,
                                                                                    @PathVariable(name = "dataInicial") String dataInicial, 
                                                                                    @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasEntreDatasPeloNomeDoMedico(cpf, nome, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/entre-datas/agendadadas/{cpf}/{nome}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasEntreDatasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf,
                                                                                             @PathVariable(name = "nome") String nome,
                                                                                             @PathVariable(name = "dataInicial") String dataInicial, 
                                                                                             @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasAgendadasEntreDatasPeloNomeDoMedico(cpf, nome, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/canceladas/entre-datas/{cpf}/{nome}/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasEntreDatasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf,
                                                                                              @PathVariable(name = "nome") String nome,
                                                                                              @PathVariable(name = "dataInicial") String dataInicial,
                                                                                              @PathVariable(name = "dataFinal") String dataFinal) {
        return servico.buscarConsultasCanceladasEntreDatasPeloNomeDoMedico(cpf, nome, dataInicial, dataFinal);
    }

    @Override
    @GetMapping("consultas/{cpf}/{nome}")
    public ResponseEntity<List<Consulta>> buscarConsultasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf,
                                                                          @PathVariable(name = "nome") String nome) {
        return servico.buscarConsultasPeloNomeDoMedico(cpf, nome);
    }

    @Override
    @GetMapping("consultas/agendadas/{cpf}/{nome}")
    public ResponseEntity<List<Consulta>> buscarConsultasAgendadasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf,
                                                                                   @PathVariable(name = "nome") String nome) {
        return servico.buscarConsultasAgendadasPeloNomeDoMedico(cpf, nome);
    }

    @Override
    @GetMapping("consultas/canceladas/{cpf}/{nome}")
    public ResponseEntity<List<Consulta>> buscarConsultasCanceladasPeloNomeDoMedico(@PathVariable(name = "cpf") String cpf,
                                                                                    @PathVariable(name = "nome") String nome) {
        return servico.buscarConsultasCanceladasPeloNomeDoMedico(cpf, nome);
    }
    
}
