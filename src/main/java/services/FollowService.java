package services;



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

}

