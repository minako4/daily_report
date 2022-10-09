package models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



    /**
     *  フォローデータのDTOモデル
     *
     */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
    @Entity
    @Table(name = "follow")
public class Follow {


        /**
         * id
         */
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        /**
         * フォローする人
         */
        @ManyToOne
        @JoinColumn(name = "follower", nullable = false)
        private Employee follower;

        /**
         * フォローされる人
         */
        @ManyToOne
        @JoinColumn(name = "followee", nullable = false)
        private Employee followee;


    }