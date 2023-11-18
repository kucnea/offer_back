package com.phy.Entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@SequenceGenerator(
        name = "SV_User_SEQ_GENERATOR",
        sequenceName = "SV_User_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class SV_User_Hist_SuperKey implements Serializable {

    @Column( name = "id_user", length = 10)
    private String idUser;

    @Column( name = "id_user_seq", length = 10 )
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idUserSeq;

    public SV_User_Hist_SuperKey(String idUser, int idUserSeq){
        this.idUser = idUser;
        this.idUserSeq = idUserSeq;
    }

    @Override
    public boolean equals(Object o){
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        SV_User_Hist_SuperKey superKey_o = (SV_User_Hist_SuperKey) o;
        return this.idUser.equals(superKey_o.idUser) && this.idUserSeq == superKey_o.idUserSeq;
    }

    @Override
    public int hashCode(){
        return Objects.hash(idUser, idUserSeq);
    }
}
