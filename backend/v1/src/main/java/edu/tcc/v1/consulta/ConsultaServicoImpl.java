package edu.tcc.v1.consulta;

import edu.tcc.v1.cliente.Cliente;
import edu.tcc.v1.medico.Medico;
import lombok.AllArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConsultaServicoImpl implements ConsultaServico {

    private ConsultaRepositorio repositorio;

    @Override
    public void associarCliente(@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataAgendamento, 
                                Cliente cliente) 
    {
        Consulta consulta = exibirConsultaPelaDataDeAgendamento(dataAgendamento);
        consulta.setCliente(cliente);
        repositorio.saveAndFlush(consulta);
    }

    @Override
    public void associarMedico(@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataAgendamento, 
                            Medico medico)
    {
        Consulta consulta = exibirConsultaPelaDataDeAgendamento(dataAgendamento);
        consulta.setMedico(medico);
        repositorio.saveAndFlush(consulta);
    }

    @Override
    public void cadastrarConsulta(AgendarConsultaDTO dto) {
        Consulta consulta = new Consulta(dto);
        repositorio.save(consulta);
    }

    @Override
    public void cancelarConsulta(@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataAgendamento) {
        Consulta consulta = exibirConsultaPelaDataDeAgendamento(dataAgendamento);
        consulta.setDataCancelamento(LocalDateTime.now());
        repositorio.saveAndFlush(consulta);
    }

    @Override
    public Consulta exibirConsultaPelaDataDeAgendamento(@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataAgendamento) 
    {
        Consulta consulta = null;
        Optional<Consulta> consultaOptional = repositorio.findByDataAgendamento(dataAgendamento);
        if (consultaOptional.isPresent()) consulta = consultaOptional.get();
        return consulta;
    }

    @Override
    public List<Consulta> exibirTodasAsConsultas() {
        return repositorio.findAll();
    }

    @Override
    public List<Consulta> exibirConsultasAgendadas() {
        return repositorio.exibirConsultasAgendadas();
    }

    @Override
    public List<Consulta> exibirConsultasCanceladas() {
        return repositorio.exibirConsultasCanceladas();
    }

    @Override
    public List<Consulta> exibirConsultasEntreDatas(@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataInicial, 
                                                    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataFinal) 
    {
        return repositorio.exibirConsultasEntreDatas(dataInicial, dataFinal);
    }

    @Override
    public List<Consulta> exibirConsultasAgendadasEntreDatas(@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataInicial, 
                                                            @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataFinal) 
    {
        return repositorio.exibirConsultasAgendadasEntreDatas(dataInicial, dataFinal);
    }

    @Override
    public List<Consulta> exibirConsultasCanceladasEntreDatas(@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataInicial, 
                                                            @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataFinal) 
    {
        return repositorio.exibirConsultasCanceladasEntreDatas(dataInicial, dataFinal);
    }

    @Override
    public List<Consulta> exibirConsultasPeloNomeDoMedico(String nomeMedico) {
        return repositorio.exibirConsultasPeloNomeDoMedico(nomeMedico);
    }

    @Override
    public List<Consulta> exibirConsultasPeloNomeDoCliente(String nomeCliente) {
        return repositorio.exibirConsultasPeloNomeDoCliente(nomeCliente);
    }

    @Override
    public List<Consulta> exibirConsultasAgendadasPeloNomeDoCliente(String nomeCliente) {
        return repositorio.exibirConsultasAgendadasPeloNomeDoCliente(nomeCliente);
    }

    @Override
    public List<Consulta> exibirConsultasCanceladasPeloNomeDoCliente(String nomeCliente) {
        return repositorio.exibirConsultasCanceladasPeloNomeDoCliente(nomeCliente);
    }

    @Override
    public List<Consulta> exibirConsultasEntreDatasPeloNomeDoCliente(String nomeCliente, 
                                                                    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataInicial, 
                                                                    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataFinal) 
    {
        return repositorio.exibirConsultasEntreDatasPeloNomeDoCliente(nomeCliente, dataInicial, dataFinal);
    }

    @Override
    public List<Consulta> exibirConsultasAgendadasEntreDatasPeloNomeDoCliente(String nomeCliente, 
                                                                            @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataInicial, 
                                                                            @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataFinal) 
    {
        return repositorio.exibirConsultasAgendadasEntreDatasPeloNomeDoCliente(nomeCliente, dataInicial, dataFinal);
    }

    @Override
    public List<Consulta> exibirConsultasCanceladasEntreDatasPeloNomeDoCliente(String nomeCliente, 
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataInicial, 
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataFinal) 
    {
        return repositorio.exibirConsultasCanceladasEntreDatasPeloNomeDoCliente(nomeCliente, dataInicial, dataFinal);
    }

    @Override
    public void adicionarObservacoesMedicas(@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dataAgendamento, 
                                            String observacoes) 
    {
        Consulta consulta = exibirConsultaPelaDataDeAgendamento(dataAgendamento);
        consulta.setObservacoesMedicas(observacoes);
        repositorio.saveAndFlush(consulta);
    }

}
