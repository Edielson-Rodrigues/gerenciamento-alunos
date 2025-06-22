package br.com.gerenciamento.service;

import br.com.gerenciamento.enums.Curso;
import br.com.gerenciamento.enums.Status;
import br.com.gerenciamento.enums.Turno;
import br.com.gerenciamento.model.Aluno;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlunoServiceTest {

    @Autowired
    private ServiceAluno serviceAluno;

    @Test
    public void getById() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Vinicius");
        aluno.setTurno(Turno.NOTURNO);
        aluno.setCurso(Curso.ADMINISTRACAO);
        aluno.setStatus(Status.ATIVO);
        aluno.setMatricula("123456");
        this.serviceAluno.save(aluno);

        Aluno alunoRetorno = this.serviceAluno.getById(1L);
        Assert.assertTrue(alunoRetorno.getNome().equals("Vinicius"));
    }

    @Test
    public void salvarSemNome() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setTurno(Turno.NOTURNO);
        aluno.setCurso(Curso.ADMINISTRACAO);
        aluno.setStatus(Status.ATIVO);
        aluno.setMatricula("123456");
        Assert.assertThrows(ConstraintViolationException.class, () -> {
                this.serviceAluno.save(aluno);});
    }

    @Test
    public void deleteById() {
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno para deletar");
        aluno.setTurno(Turno.MATUTINO);
        aluno.setCurso(Curso.BIOMEDICINA);
        aluno.setStatus(Status.ATIVO);
        aluno.setMatricula("333");

        serviceAluno.save(aluno);
        Long id = aluno.getId();

        serviceAluno.deleteById(id);

        Assert.assertTrue(serviceAluno.findAll().stream().noneMatch(a -> a.getId().equals(id)));
    }

    @Test
    public void findByStatusAtivo() {
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno Ativo");
        aluno.setTurno(Turno.MATUTINO);
        aluno.setCurso(Curso.ADMINISTRACAO);
        aluno.setStatus(Status.ATIVO);
        aluno.setMatricula("444");

        serviceAluno.save(aluno);

        List<Aluno> ativos = serviceAluno.findByStatusAtivo();
        Assert.assertTrue(ativos.stream().anyMatch(a -> a.getMatricula().equals("444")));
    }

    @Test
    public void findByStatusInativo() {
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno Inativo");
        aluno.setTurno(Turno.MATUTINO);
        aluno.setCurso(Curso.ADMINISTRACAO);
        aluno.setStatus(Status.INATIVO);
        aluno.setMatricula("444");

        serviceAluno.save(aluno);

        List<Aluno> inativos = serviceAluno.findByStatusInativo();
        Assert.assertTrue(inativos.stream().anyMatch(a -> a.getMatricula().equals("444")));
    }

    @Test
    public void findByNomeContainingIgnoreCase() {
        Aluno aluno = new Aluno();
        aluno.setNome("Lucas Andrade");
        aluno.setTurno(Turno.NOTURNO);
        aluno.setCurso(Curso.ADMINISTRACAO);
        aluno.setStatus(Status.ATIVO);
        aluno.setMatricula("555");

        serviceAluno.save(aluno);

        List<Aluno> encontrados = serviceAluno.findByNomeContainingIgnoreCase("lucas");
        Assert.assertFalse(encontrados.isEmpty());
    }
}