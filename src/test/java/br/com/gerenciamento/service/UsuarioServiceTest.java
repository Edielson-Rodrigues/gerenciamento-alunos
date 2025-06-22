package br.com.gerenciamento.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gerenciamento.exception.EmailExistsException;
import br.com.gerenciamento.model.Usuario;
import br.com.gerenciamento.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private ServiceUsuario serviceUsuario;

    @Test
    public void salvarUsuarioComSucesso() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        usuario.setUser("testeuser");
        usuario.setSenha("123456");

        serviceUsuario.salvarUsuario(usuario);

        Usuario usuarioSalvo = serviceUsuario.loginUser("testeuser", Util.md5("123456"));
        Assert.assertNotNull(usuarioSalvo);
    }

    @Test
    public void salvarUsuarioEmailDuplicado() throws Exception {
        Usuario usuario1 = new Usuario();
        usuario1.setEmail("duplicado@email.com");
        usuario1.setUser("user1");
        usuario1.setSenha("senha123");
        serviceUsuario.salvarUsuario(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setEmail("duplicado@email.com");
        usuario2.setUser("user2");
        usuario2.setSenha("senha456");

        Assert.assertThrows(EmailExistsException.class, () -> {
            serviceUsuario.salvarUsuario(usuario2);
        });
    }

    @Test
    public void loginComSucesso() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("login@email.com");
        usuario.setUser("loginuser");
        usuario.setSenha("minhasenha");
        serviceUsuario.salvarUsuario(usuario);

        Usuario usuarioLogado = serviceUsuario.loginUser("loginuser", Util.md5("minhasenha"));
        Assert.assertNotNull(usuarioLogado);
    }

    @Test
    public void loginComCredenciaisInvalidas() {
        Usuario usuarioLogado = serviceUsuario.loginUser("usuarioinvalido", "senhainvalida");
        Assert.assertNull(usuarioLogado);
    }
}
