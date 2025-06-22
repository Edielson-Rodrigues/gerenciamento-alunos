package br.com.gerenciamento.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gerenciamento.enums.Curso;
import br.com.gerenciamento.enums.Status;
import br.com.gerenciamento.enums.Turno;
import br.com.gerenciamento.model.Aluno;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    public void salvarEBuscarPorId() {
        Aluno aluno = new Aluno();
        aluno.setNome("Carlos");
        aluno.setMatricula("111");
        aluno.setStatus(Status.ATIVO);
        aluno.setTurno(Turno.MATUTINO);
        aluno.setCurso(Curso.ADMINISTRACAO);

        aluno = alunoRepository.save(aluno);

        Aluno encontrado = alunoRepository.findById(aluno.getId()).orElse(null);
        
        Assert.assertNotNull(encontrado);
        Assert.assertEquals("Carlos", encontrado.getNome());
    }

    @Test
    public void findByStatusAtivo() {
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno Ativo");
        aluno.setMatricula("222");
        aluno.setStatus(Status.ATIVO);
        aluno.setTurno(Turno.NOTURNO);
        aluno.setCurso(Curso.ADMINISTRACAO);

        alunoRepository.save(aluno);

        List<Aluno> ativos = alunoRepository.findByStatusAtivo();
        Assert.assertTrue(ativos.stream().anyMatch(a -> a.getNome().equals("Aluno Ativo")));
    }


    @Test
    public void findByStatusInativo() {
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno Inativo");
        aluno.setMatricula("333");
        aluno.setStatus(Status.INATIVO);
        aluno.setTurno(Turno.NOTURNO);
        aluno.setCurso(Curso.BIOMEDICINA);

        alunoRepository.save(aluno);

        List<Aluno> inativos = alunoRepository.findByStatusInativo();
        Assert.assertTrue(inativos.stream().anyMatch(a -> a.getNome().equals("Aluno Inativo")));
    }

    @Test
    public void findByNomeContainingIgnoreCase() {
        Aluno aluno = new Aluno();
        aluno.setNome("Fernanda Silva");
        aluno.setMatricula("444");
        aluno.setStatus(Status.ATIVO);
        aluno.setTurno(Turno.MATUTINO);
        aluno.setCurso(Curso.ADMINISTRACAO);

        alunoRepository.save(aluno);

        List<Aluno> encontrados = alunoRepository.findByNomeContainingIgnoreCase("fernanda");
        Assert.assertFalse(encontrados.isEmpty());
    }
}
