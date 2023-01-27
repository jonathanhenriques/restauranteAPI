package com.jonathanhenriques.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.jonathanhenriques.domain.model.Cozinha;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

/**
 * Classe criada para modificar nome do XML
 * que é retornado no response
 * 
 * EXEMPLO:
 * 
 * <CozinhasXmlWrapper>
    <cozinhas>
        <cozinhas>
            <id>1</id>
            <titulo>Tailandesa</titulo>
        </cozinhas>
        <cozinhas>
            <id>2</id>
            <titulo>Indiana</titulo>
        </cozinhas>
    </cozinhas>
</CozinhasXmlWrapper>
 * 
 * @author jonathan
 *
 */

@JacksonXmlRootElement(localName = "cozinhas")
//@JsonRootName(value = "cozinhas") //faz o mesmo
@Data
public class CozinhasXmlWrapper {
	
	@JsonProperty("cozinha")
	@JacksonXmlElementWrapper(useWrapping = false) //desabilita o "embrulho" do xml
	@NonNull //sinaliza que a propriedade e obrigatoria
	private List<Cozinha> cozinhas;

}
