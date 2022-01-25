package br.com.alura.forum_alura.config.validacao;

public class ErroDeFormularioDto {

	// Esta class aqui vai representar um erro de validação esta class que vai
	// mostrar o que vai mostrar para o usuário

	private String campo;
	private String erro;

	public ErroDeFormularioDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

}
