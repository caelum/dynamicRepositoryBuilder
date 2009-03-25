package br.com.caelum.recorder.modelo;

import java.io.Serializable;
import java.util.List;

public interface RestauranteRepository {

    public List<Restaurante> findAllByNome(String nome);

    public List<Restaurante> findAllByNomeAndEspecialidade(String nome, String especialidade);

    public Restaurante find(Serializable id);

    public Restaurante findByNome(String nome);
}
