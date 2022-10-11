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

    /**
     * followerIDとfolloweeIDを条件に検索し、データが存在するか
     * @param follower フォロワー
     * @param followee フォロイー
     *
     * @return 認証結果を返却する（成功（登録されてない：true 失敗 (登録されている:false）
     *

     public Boolean relation(Employee follower, Employee followee) {

         boolean noRelation = true;
         if (follower != null && followee != null) {
             ;

             if (wer.getId() != null && f.getId() != null) {

                 //データが取得できた場合、登録されてます
                 noRelation = false;
             }
         }

         //認証結果を返却する
         return noRelation;
     }

     /**
      * followerのidを条件にデータを1件取得し、followのインスタンスで返却する
      * @param id
      * @return 取得データのインスタンス
      *
    private Follow findFollower(Employee follower) {
            Follow wer = em.find(Follow.class,follower);

            return wer;
    }
            /**
             * followeeのidを条件にデータを1件取得し、followのインスタンスで返却する
             * @param id
             * @return 取得データのインスタンス
             *
           private Follow findFollowee(Employee followee) {
                   Follow wee = em.find(Follow.class,followee);

                   return wee;
    }
  */





}

