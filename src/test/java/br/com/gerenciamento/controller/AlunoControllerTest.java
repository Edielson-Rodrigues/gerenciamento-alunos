package br.com.gerenciamento.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AlunoControllerTest {

    @Autowired
    private AlunoController alunoController;

    @Test
    public void insertAlunosForm() {
        ModelAndView modelAndView = alunoController.insertAlunos();
        Assert.assertEquals("Aluno/formAluno", modelAndView.getViewName());
        Assert.assertNotNull(modelAndView.getModel().get("aluno"));
    }

    @Test
    public void listagemAlunos() {
        ModelAndView modelAndView = alunoController.listagemAlunos();
        Assert.assertEquals("Aluno/listAlunos", modelAndView.getViewName());
        Assert.assertTrue(modelAndView.getModel().containsKey("alunosList"));
    }

    @Test
    public void filtroAlunos() {
        ModelAndView modelAndView = alunoController.filtroAlunos();
        Assert.assertEquals("Aluno/filtroAlunos", modelAndView.getViewName());
    }

    @Test
    public void pesquisarAlunoNomeVazio() {
        ModelAndView modelAndView = alunoController.pesquisarAluno("");
        Assert.assertEquals("Aluno/pesquisa-resultado", modelAndView.getViewName());
        Assert.assertTrue(modelAndView.getModel().containsKey("ListaDeAlunos"));
    }
}
