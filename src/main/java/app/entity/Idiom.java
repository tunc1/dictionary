package app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Idiom implements Serializable
{
    @Id
    @SequenceGenerator(name="Idiom_SEQUENCE_GENERATOR",sequenceName="Idiom_SEQUENCE",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Idiom_SEQUENCE_GENERATOR")
    private Long id;
    private String idiom,meaning;
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id=id;
    }
    public String getIdiom()
    {
        return idiom;
    }
    public void setIdiom(String idiom)
    {
        this.idiom=idiom;
    }
    public String getMeaning()
    {
        return meaning;
    }
    public void setMeaning(String meaning)
    {
        this.meaning=meaning;
    }
}