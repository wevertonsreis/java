package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Projeto;

import com.thoughtworks.xstream.XStream;

public class ProjetoResourceTest {
	
	@Before
	public void startServidor() {
		Servidor.start();
	}
	
	@After
	public void stopServidor() {
		Servidor.stop();
	}
	
	@Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/projetos").request().get(String.class);

		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);

		System.out.println(projeto);

		Assert.assertTrue(projeto.getNome().equals("Minha loja"));
	}
}
