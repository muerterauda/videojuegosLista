package com.crudvideojuegos.model.language;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private long id;
    @NotEmpty
    @Length (max = 2)
    private String locale;
    @NotEmpty
    @Length (max = 5)
    private String extendlocal;
    @NotEmpty
    @Length(max = 30)
    private String name;
    @Length(max = 50)
    private String srcimage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getExtendLocal() {
        return extendlocal;
    }

    public void setExtendLocal(String extendLocal) {
        this.extendlocal = extendLocal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrcImage() {
        return srcimage;
    }

    public void setSrcImage(String srcImage) {
        this.srcimage = srcImage;
    }
}
