package services;

import javax.persistence.EntityManager;

import utils.DBUtil;

/**
 *
 * DB接続にかかわる共通処理を行うクラス
 *
 */
public class ServiceBase {

    /**
     * EntitiyManagerインスタンス
     */
    protected EntityManager em = DBUtil.createEntityManager();

    /**
     * EntitiyManagerのクローズ
     */
    public void close() {
        if (em.isOpen()) {
            em.close();

        }
    }
}
