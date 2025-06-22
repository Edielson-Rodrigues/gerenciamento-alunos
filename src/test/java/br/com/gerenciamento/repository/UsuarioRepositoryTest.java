package br.com.gerenciamento.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gerenciamento.model.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void salvarEBuscarPorId() {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste1@email.com");
        usuario.setUser("usuario1");
        usuario.setSenha("senha123");

        usuario = usuarioRepository.save(usuario);

        Usuario encontrado = usuarioRepository.findById(usuario.getId()).orElse(null);
        Assert.assertNotNull(encontrado);
        Assert.assertEquals("usuario1", encontrado.getUser());
    }

    @Test
    public void findByEmail() {
        Usuario usuario = new Usuario();
        usuario.setEmail("busca@email.com");
        usuario.setUser("buscaUser");
        usuario.setSenha("senha123");

        usuarioRepository.save(usuario);

        Usuario encontrado = usuarioRepository.findByEmail("busca@email.com");
        Assert.assertNotNull(encontrado);
        Assert.assertEquals("buscaUser", encontrado.getUser());
    }

    @Test
    public void buscarLoginComSucesso() {
        Usuario usuario = new Usuario();
        usuario.setEmail("login@email.com");
        usuario.setUser("loginuser");
        usuario.setSenha("senha123");

        usuarioRepository.save(usuario);

        Usuario encontrado = usuarioRepository.buscarLogin("loginuser", "senha123");
        Assert.assertNotNull(encontrado);
        Assert.assertEquals("loginuser", encontrado.getUser());
    }

    @Test
    public void buscarLoginComDadosInvalidos() {
        Usuario encontrado = usuarioRepository.buscarLogin("invalido", "senhainvalida");
        Assert.assertNull(encontrado);
    }
}
