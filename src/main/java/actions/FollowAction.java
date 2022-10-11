package actions;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
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

        // Followのインスタンスを生成
        Follow f = new Follow(
                null,
                follower, //ログインしている従業員を、followerとして登録する
                followee);

        serviceF.create(f);

      //セッションに登録完了のフラッシュメッセージを設定
        putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());
        //一覧画面にリダイレクト
        redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
    }
        /**
        //follower,followeeより、フォローテーブルを検索して
        //フォロー情報の存在チェック
        //有効な従業員か認証する
        Boolean noRelation = service.relation(follower, followee);

        if (noRelation) {
            //存在しなければFollow情報登録
            service.create(f);

          //セッションに登録完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());
          //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);

        } else {
            //存在したなら何もしない


            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
        }*/

        /**
         * 一覧画面を表示する
         *
        public void index() throws ServletException, IOException {



            //セッションからログイン中の従業員情報を取得
            EmployeeView loginEmployee = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);



            //ログイン中の従業員がフォローしたデータを、指定されたページ数の一覧画面に表示する分取得する
            int page = getPage();
            List<ReportView> reports = serviceR.getMinePerPage(loginEmployee, page);

            //ログイン中の従業員が作成した日報データの件数を取得
            long myReportsCount = service.countAllMine(loginEmployee);

            putRequestScope(AttributeConst.REPORTS, reports); //取得した日報データ
            putRequestScope(AttributeConst.REP_COUNT, myReportsCount); //ログイン中の従業員が作成した日報の数
            putRequestScope(AttributeConst.PAGE, page); //ページ数
            putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

            //↑ここまで追記

            //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
            String flush = getSessionScope(AttributeConst.FLUSH);
            if (flush != null) {
                putRequestScope(AttributeConst.FLUSH, flush);
                removeSessionScope(AttributeConst.FLUSH);
            }

            //一覧画面を表示
            forward(ForwardConst.FW_TOP_INDEX);
        }
*/

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示するフォローデータを取得
        int page = getPage();
        List<ReportView> follow = serviceF.getAllPerPage(page);

        //全フォローデータの件数を取得
        long followCount = serviceF.countAll();

        putRequestScope(AttributeConst.FOLLOW, follow); //取得したフォローデータ
        putRequestScope(AttributeConst.FOLLOW_COUNT, followCount); // 全ての日報データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
        //一覧画面を表示
        forward(ForwardConst.FW_REP_INDEX);
    }


}
