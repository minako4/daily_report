package models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


    /**
     *  フォローデータのDTOモデル
     *
     */
    @Table(name = JpaConst.TABLE_FOL)
    @NamedQueries({
        @NamedQuery(
                name = JpaConst.Q_FOL_GET_ALL_MINE,
                query =JpaConst.Q_FOL_GET_ALL_MINE_DEF)
    })

    @Getter //すべてのクラスフィールドについてgetterを自動生成する(Lombok)
    @Setter//すべてのクラスフィールドについてsetterを自動生成する(Lombok)
    @NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
    @AllArgsConstructor //すべてのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
    @Entity
public class Follow {
        /**
         * id
         */
        @Id
        @Column(name = JpaConst.FOL_COL_ID)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        /**
         * フォローする人
         */
        @Column(name = JpaConst.FOL_COL_USER, nullable = false)
        private String user_id;

        /**
         * フォローされる人
         */
        @Column(name = JpaConst.FOL_COL_FOLLOW, nullable = false)
        private String follow_id;
}
