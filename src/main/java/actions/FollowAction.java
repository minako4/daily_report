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
import utils.DBUtil;

public class FollowAction extends ActionBase {

    private FollowService service;
    private EmployeeService service2;

    @Override
    public void process() throws ServletException, IOException {

        service = new FollowService();
        service2 = new EmployeeService();

        //メソッドを実行
        invoke();
        service.close();
        service2.close();
    }

    public void create() throws ServletException, IOException {

            //動作確認メッセージを出力
            //System.out.println("FollowAction::create を実行");

            //セッションからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

            Employee follower =service2.getEmployee((ev.getId()));



        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();


        //フォローしたい従業員のidを取得
        Employee followee = service2.getEmployee(toNumber(getRequestParam(AttributeConst.EMP_ID)));


     // Followのインスタンスを生成
        Follow f = new Follow(
               null,
                follower, //ログインしている従業員を、followerとして登録する
                followee
                );
        
        //follower,followeeより、フォローテーブルを検索して
        //フォロー情報の存在チェック
        
        
        //存在したらエラー表示に使うメッセージをスコープにセット
        //存在しなければFollow情報登録
        service.create(f);


            //セッションに登録完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
        }
    }


