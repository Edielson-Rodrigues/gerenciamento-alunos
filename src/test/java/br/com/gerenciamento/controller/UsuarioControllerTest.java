package br.com.gerenciamento.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import br.com.gerenciamento.model.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioControllerTest {

    @Autowired
    private UsuarioController usuarioController;

    @Test
    public void telaIndex() {
        ModelAndView modelAndView = usuarioController.index();
        Assert.assertEquals("home/index", modelAndView.getViewName());
        Assert.assertNotNull(modelAndView.getModel().get("aluno"));
    }

    @Test
    public void telaLogin() {
        ModelAndView modelAndView = usuarioController.login();
        Assert.assertEquals("login/login", modelAndView.getViewName());
        Assert.assertNotNull(modelAndView.getModel().get("usuario"));
    }

    @Test
    public void telaCadastro() {
        ModelAndView modelAndView = usuarioController.cadastrar();
        Assert.assertEquals("login/cadastro", modelAndView.getViewName());
        Assert.assertNotNull(modelAndView.getModel().get("usuario"));
    }


    @Test
    public void cadastrarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        usuario.setUser("testelogin");
        usuario.setSenha("123456");

        ModelAndView modelAndView = usuarioController.cadastrar(usuario);
        Assert.assertEquals("redirect:/", modelAndView.getViewName());
    }
}
