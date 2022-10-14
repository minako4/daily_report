package actions;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;

import actions.views.EmployeeView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import models.Employee;
import models.Follow;
import services.EmployeeService;
import services.FollowService;
import services.ReportService;
import utils.DBUtil;

public class FollowAction extends ActionBase {

    private FollowService serviceF;
    private EmployeeService serviceE;
    private ReportService serviceR;

    @Override
    public void process() throws ServletException, IOException {

        serviceF = new FollowService();
        serviceE = new EmployeeService();
        serviceR = new ReportService();

        //メソッドを実行
        invoke();
        serviceF.close();
        serviceE.close();
        serviceR.close();
    }

    public void create() throws ServletException, IOException {

        //動作確認メッセージを出力
        //System.out.println("FollowAction::create を実行");

        //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        Employee follower = serviceE.getEmployee((ev.getId()));

        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        //フォローしたい従業員のidを取得
        Employee followee = serviceE.getEmployee(toNumber(getRequestParam(AttributeConst.EMP_ID)));




       //follower,followeeより、フォローテーブルを検索して
        //フォロー情報の存在チェック
        Follow f = serviceF.findRelation(follower , followee );

        if (f != null && f.getFollower().getId()== follower.getId() && f.getFollowee().getId() == followee.getId()) {
            //存在したなら何もせず、一覧画面にリダイレクト

            redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);

        } else {
            // Followのインスタンスを生成
            Follow f1 = new Follow(
                null,
                follower, //ログインしている従業員を、followerとして登録する
                followee);
          //存在しなければFollow情報登録
            serviceF.create(f1);

          //セッションに登録完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_FOLLOWED.getMessage());


            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
        }
    }

        /**
         * 削除を行う
         * @throws ServletException
         * @throws IOException
         */
        public void destroy() throws ServletException, IOException {

          //動作確認メッセージを出力
            System.out.println("FollowAction::destroy を実行");

          //セッションからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

            Employee follower = serviceE.getEmployee((ev.getId()));

            EntityManager em = DBUtil.createEntityManager();
            em.getTransaction().begin();

            //フォローを外したい従業員のidを取得
            Employee followee = serviceE.getEmployee(toNumber(getRequestParam(AttributeConst.EMP_ID)));





                //idを条件に従業員データを削除する
                serviceF.destroy(follower, followee);

                //セッションに削除完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UNFOLLOWED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
            }



    }



