package edu.tcc.v1.consulta;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConsultaServicoImpl implements ConsultaServico {

    static ConsultaRepositorio repositorio;

    public static ResponseEntity<Consulta> buscarConsultaPeloMedico(LocalDateTime dataHora, Medico medico) {
        Optional<Consulta> consulta = repositorio
                .findAll()
                .stream()
                .filter(
                        e -> e.getDataAgendamento().equals(dataHora)
                        && e.getDataCancelamento() == null
                        && e.getMedico().equals(medico)
                ).findFirst();
        return consulta.isPresent() ? ResponseEntity.ok(consulta.get()) : ResponseEntity.badRequest().build();
    }

    public static ResponseEntity<Consulta> buscarConsultaPeloCliente(LocalDateTime dataHora, Cliente cliente) {
        Optional<Consulta> consulta = repositorio
                .findAll()
                .stream()
                .filter(
                        e -> e.getDataAgendamento().equals(dataHora)
                                && e.getDataCancelamento() == null
                                && e.getCliente().equals(cliente)
                ).findFirst();
        return consulta.isPresent() ? ResponseEntity.ok(consulta.get()) : ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Void> agendarConsulta(AgendarConsultaDTO dto, Cliente cliente, Medico medico) {
        Consulta consulta = ConsultaRepositorio.instanciar(dto, cliente, medico);
        repositorio.save(consulta);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> cancelarConsulta(LocalDateTime dataAgendamento, Cliente cliente) {
        Consulta consulta = buscarConsultaPeloCliente(dataAgendamento, cliente).getBody();
        consulta.setDataCancelamento(LocalDateTime.now());
        repositorio.save(consulta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> adicionarObservacoesMedicasAConsulta(Medico medico, LocalDateTime dataAgendamento, String observacoes) {
        Consulta consulta = buscarConsultaPeloMedico(dataAgendamento, medico).getBody();
        consulta.setObservacoesMedicas(observacoes);
        repositorio.saveAndFlush(consulta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Consulta> buscarConsultas() {
        return repositorio.buscarConsultasAgendadas();
    }

    @Override
    public List<Consulta> buscarConsultasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return repositorio.buscarConsultasEntreDatas(dataInicial, dataFinal);
    }

    @Override
    public List<Consulta> buscarConsultasAgendadas() {
        return repositorio.buscarConsultasAgendadas();
    }

    @Override
    public List<Consulta> buscarConsultasAgendadasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return repositorio.buscarConsultasAgendadasEntreDatas(dataInicial, dataFinal);
    }

    @Override
    public List<Consulta> buscarConsultasCanceladas() {
        return repositorio.buscarConsultasCanceladas();
    }

    @Override
    public List<Consulta> buscarConsultasCanceladasEntreDatas(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return repositorio.buscarConsultasCanceladasEntreDatas(dataInicial, dataFinal);
    }

}