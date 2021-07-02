package br.com.jmsdevelopment.ecom.builder;

import br.com.jmsdevelopment.ecom.dto.cliente.ClienteCadastroDto;
import br.com.jmsdevelopment.ecom.dto.cliente.ClienteDto;

public class ClienteCadastroDtoBuilder extends ClienteDtoBuilder {

    private String senha;

    public ClienteCadastroDtoBuilder comSenha(String senha) {
        this.senha = senha;
        return this;
    }

    @Override
    public ClienteCadastroDtoBuilder comId(Long id) {
        super.comId(id);
        return this;
    }

    @Override
    public ClienteCadastroDtoBuilder comNome(String nome) {
        super.comNome(nome);
        return this;
    }

    @Override
    public ClienteCadastroDtoBuilder comCpf(String cpf) {
        super.comCpf(cpf);
        return this;
    }

    @Override
    public ClienteCadastroDtoBuilder comEmail(String email) {
        super.comEmail(email);
        return this;
    }

    @Override
    public ClienteCadastroDtoBuilder comDataNascimento(String dataNascimento) {
        super.comDataNascimento(dataNascimento);
        return this;
    }

    @Override
    public ClienteCadastroDto build() {
        ClienteDto clienteDto = super.build();

        ClienteCadastroDto clienteCadastroDto = new ClienteCadastroDto();
        clienteCadastroDto.setSenha(this.senha);
        clienteCadastroDto.setNome(clienteDto.getNome());
        clienteCadastroDto.setCpf(clienteDto.getCpf());
        clienteCadastroDto.setEmail(clienteDto.getEmail());
        clienteCadastroDto.setDataNascimento(clienteDto.getDataNascimento());

        return clienteCadastroDto;
    }
}
