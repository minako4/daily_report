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


    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException

    public void follow() throws ServletException, IOException {



        //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        // Followのインスタンスを生成
        Follow f = new Follow();

        // セッションスコープからIDを取得して
        // 該当のIDの1件のみをデータベースから取得
        Employee emp_id = em.find(Employee.class, (Integer)(request.getSession().getAttribute("id")));

        Employee emp_follower = new Employee();

        Employee emp_followee = new Employee();

        f.setFollower(emp_follower);

        f.setFollowee(emp_followee);



        // データベースに保存
        em.persist(f);
        em.getTransaction().commit();

        // 自動採番されたIDの値を表示
        response.getWriter().append(Integer.valueOf(f.getId()).toString());

        em.close();
*/

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

            //動作確認メッセージを出力
            System.out.println("FollowAction::create を実行");

            //セッションからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();
        Employee followee = service2.getEmployee(toNumber(getRequestParam(AttributeConst.EMP_ID)));

     // Followのインスタンスを生成
        Follow f = new Follow(
                null,
                ev, //ログインしている従業員を、followerとして登録する
                followee
                );

        //Follow情報登録
        service.create(f);


            //セッションに登録完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
        }
    }


