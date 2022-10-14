package services;





import java.util.List;

import javax.persistence.NoResultException;

import constants.JpaConst;
import models.Employee;
import models.Follow;


/**
 * フォローテーブルの操作に関わる処理を行うクラス
 *
 */
public class FollowService extends ServiceBase {


    /**
     * フォローの内容を元にデータを1件作成し、フォローテーブルに登録する
     * @param f フォローの登録内容
     */
    public String create(Follow f) {

            createInternal(f);

        return null;

    }


    /**
     * followデータを1件登録する
     * @param f フォローデータ
     */
    private void createInternal(Follow f) {

        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
    }

    /**
    * follow、followeeを条件に取得したデータをEmployeeViewのインスタンスで返却する

    * @return 取得データのインスタンス 取得できない場合null
    */
    public Follow findRelation(Employee follower,Employee followee) {
        Follow f = null;
        try {


            //followerIDとfolloweeIDを条件に検索し、登録されているrelation
            f = em.createNamedQuery(JpaConst.Q_FOLLOW_GET_BY_FOLLOWER_AND_FOLLOWEE, Follow.class)
                    .setParameter(JpaConst.JPQL_PARM_FOLLOWER, follower)
                    .setParameter(JpaConst.JPQL_PARM_FOLLOWEE, followee)
                    .getSingleResult();

        } catch (NoResultException ex) {
        }

        return (f);

    }
    /**
     * idを条件にフォローデータを削除する
     * @param id
     */
    public void destroy(Employee follower, Employee followee) {

        //followerとfolloweeを条件に登録済みのフォローを取得する
        Follow f = findRelation(follower,followee);

        //フォローデータを削除する

        em.getTransaction().begin();
        em.remove(f);       // データ削除
        em.getTransaction().commit();

    }

    /**
     * 指定されたページ数の一覧画面に表示するフォローデータを取得し、Followのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<Follow> getAllPerPage(Employee follower ,int page) {
        List<Follow> follows = em.createNamedQuery(JpaConst.Q_FOLLOW_GET_ALL_FOLLOW, Follow.class)
                .setParameter(JpaConst.JPQL_PARM_FOLLOW, follower)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return (follows);

    }


}

