package constants;

public interface JpaConst {

  //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "daily_report_system";

    //データ取得件数の最大値
    int ROW_PER_PAGE = 15; //1ページに表示するレコードの数

    //従業員テーブル
    String TABLE_EMP = "employees"; //テーブル名
    //従業員テーブルカラム
    String EMP_COL_ID = "id"; //id
    String EMP_COL_CODE = "code"; //社員番号
    String EMP_COL_NAME = "name"; //氏名
    String EMP_COL_PASS = "password"; //パスワード
    String EMP_COL_ADMIN_FLAG = "admin_flag"; //管理者権限
    String EMP_COL_CREATED_AT = "created_at"; //登録日時
    String EMP_COL_UPDATED_AT = "updated_at"; //更新日時
    String EMP_COL_DELETE_FLAG = "delete_flag"; //削除フラグ

    int ROLE_ADMIN = 1; //管理者権限ON(管理者）
    int ROLE_GENERAL = 0; //管理者権限OFF(一般)
    int EMP_DEL_TRUE = 1; //削除フラグON(削除済み)
    int EMP_DEL_FALSE = 0; //削除フラグOFF(現役)

    //日報テーブル
    String TABLE_REP = "reports"; //テーブル名
    //日報テーブルカラム
    String REP_COL_ID = "id";//id
    String REP_COL_EMP = "employee_id";//日報を作成した従業員のid
    String REP_COL_REP_DATE = "report_date";//いつの日報かを示す日付
    String REP_COL_TITLE = "title";//日報のタイトル
    String REP_COL_CONTENT = "content";//日報の内容
    String REP_COL_CREATED_AT = "created_at";//登録日時
    String REP_COL_UPDATED_AT = "updated_at";//更新日時

    //フォロー・フォロワーテーブル(追記）
    String TABLE_FOLLOW = "follows";//テーブル名
    //フォロー・フォロワーテーブルカラム
    String FOL_COL_ID = "id"; //id
    String FOL_COL_FOLLOWER = "follower"; //フォローする側のid
    String FOL_COL_FOLLOWEE = "followee"; //フォローされる側のid


    //Entity名
    String ENTITY_EMP = "employee"; //従業員
    String ENTITY_REP = "report";//日報

    String ENTITY_FOLLOW = "follow";//フォロー

    //JPQL内パラメータ
    String JPQL_PARM_CODE = "code";//社員番号
    String JPQL_PARM_PASSWORD = "password";//パスワード
    String JPQL_PARM_EMPLOYEE = "employee";//従業員
    String JPQL_PARM_FOLLOW = "follow";//フォロー
    String JPQL_PARM_FOLLOWER = "follower";//フォロワー
    String JPQL_PARM_FOLLOWEE = "followee";//フォロイー


    //NamedQueryの nameとquery
    //すべての従業員をidの降順に取得する
    String Q_EMP_GET_ALL = ENTITY_EMP + ".getAll";//name
    String Q_EMP_GET_ALL_DEF = "SELECT e FROM Employee AS e ORDER BY e.id DESC";//query
    //すべての従業員の件数を取得する
    String Q_EMP_COUNT = ENTITY_EMP + ".count";
    String Q_EMP_COUNT_DEF = "SELECT COUNT(e) FROM Employee AS e";
    //社員番号とハッシュ化済パスワードを条件に未削除の従業員を取得する
    String Q_EMP_GET_BY_CODE_AND_PASS = ENTITY_EMP + ".getByCodeAndPass";
    String Q_EMP_GET_BY_CODE_AND_PASS_DEF = "SELECT e FROM Employee AS e WHERE e.deleteFlag = 0 AND e.code = :" + JPQL_PARM_CODE + " AND e.password = :" + JPQL_PARM_PASSWORD;
    //指定した社員番号を保持する従業員の件数を取得する
    String Q_EMP_COUNT_REGISTERED_BY_CODE = ENTITY_EMP + ".countRegisteredByCode";
    String Q_EMP_COUNT_REGISTERED_BY_CODE_DEF = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :" + JPQL_PARM_CODE;

    //すべての日報をidの降順に取得する
    String Q_REP_GET_ALL = ENTITY_REP + ".getAll";
    String Q_REP_GET_ALL_DEF = "SELECT r FROM Report AS r ORDER BY r.id DESC";
    //すべての日報の件数を取得する
    String Q_REP_COUNT = ENTITY_REP + ".count";
    String Q_REP_COUNT_DEF = "SELECT COUNT(r) FROM Report AS r";
    //指定した従業員が作成した日報を全件idの降順で取得する
    String Q_REP_GET_ALL_MINE = ENTITY_REP + ".getAllMine";
    String Q_REP_GET_ALL_MINE_DEF = "SELECT r FROM Report AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE + " ORDER BY r.id DESC";
    //指定した従業員が作成した日報の件数を取得する
    String Q_REP_COUNT_ALL_MINE = ENTITY_REP + ".countAllMine";
    String Q_REP_COUNT_ALL_MINE_DEF = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE;


    //指定した従業員がフォローした人を全件idの降順で取得する
    String Q_FOLLOW_GET_ALL_FOLLOW = ENTITY_REP + ".getAllMineFollow";
    String Q_FOLLOW_GET_ALL_FOLLOW_DEF = "SELECT f FROM Follow AS f WHERE f.follower = :" + JPQL_PARM_FOLLOW + " ORDER BY f.follower DESC";
    //指定した従業員がフォローした人の件数を取得する
    String Q_FOLLOW_COUNT_ALL_FOLLOW = ENTITY_REP + ".countAllMineFollow";
    String Q_FOLLOW_COUNT_ALL_FOLLOW_DEF = "SELECT COUNT (f) FROM Follow AS f WHERE f.follower = :" + JPQL_PARM_FOLLOW;
    //followerIDとfolloweeIDを条件に未削除のrelationを取得する
    String Q_FOLLOW_GET_BY_FOLLOWER_AND_FOLLOWEE = ENTITY_FOLLOW + ".getByFollowerFollowee";
    String Q_FOLLOW_GET_BY_FOLLOWER_AND_FOLLOWEE_DEF = "SELECT f  FROM Follow AS f WHERE f.follower = :" + JPQL_PARM_FOLLOWER + " AND f.followee = :" + JPQL_PARM_FOLLOWEE;

    //フォローした人の日報の件数を取得する
    String Q_REP_COUNT_ALL_FOLLOW = ENTITY_REP + ".countAllFollow";
    String Q_REP_COUNT_ALL_FOLLOW_DEF =  "SELECT COUNT (r) FROM Report r INNER JOIN Follow f  ON f.followee = r.employee WHERE f.follower = :" +  JPQL_PARM_FOLLOWEE ;

    //フォローした人の日報情報を取得する
    String Q_REP_GET_ALL_FOLLOW = ENTITY_REP + ".getAllFollow";
    String Q_REP_GET_ALL_FOLLOW_DEF = "SELECT r FROM Report r INNER JOIN Follow f  ON f.followee = r.employee WHERE f.follower = :" +  JPQL_PARM_FOLLOWEE ;

}
